/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import static java.time.Instant.now;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import proyectyofinalangelsosa.conectar;
import proyectyofinalangelsosa.listaArticulos;

/**
 *
 * @author Jorge L. Guzmán M.
 */
public class facturacion extends javax.swing.JFrame {

    /**
     * Creates new form facturacion
     */
    Calendar fechaactual = new GregorianCalendar();

    public facturacion() {
        initComponents();
        id.requestFocus(true);
        cargarArticulos("");
        numeroFactura();
        contado.setSelected(true);
        validacion="";
        cogeme.setVisible(false);
        palcuadre.setVisible(false);
//        tablabalance.setVisible(false);
         
         // cargarBalance();
         
         if(ventas==0){
             Login saldo=new Login();
         
         palcuadre.setText(String.valueOf(saldo.getSaldoinicial()));
         
         }
         Login coger=new Login();
         cogeme.setText(String.valueOf(coger.getMandado()));
         

        fecha.setCalendar(fechaactual);
    }

    void cargarArticulos(String valor) {
        DefaultTableModel modelo = (DefaultTableModel) tablaarticulos.getModel();
        modelo.getDataVector().clear();

        String[] registros = new String[6];
        String sql = "SELECT desart,idarticulo,cantidad,precom,preven,reorden FROM articulo WHERE concat(desart) like '%" + valor + "%' ";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("idarticulo");
                registros[1] = rs.getString("desart");
                registros[2] = rs.getString("cantidad");
                registros[4] = rs.getString("precom");
                registros[3] = rs.getString("preven");
                registros[5] = rs.getString("reorden");
                modelo.addRow(registros);
            }
            tablaarticulos.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }
    
    void cargarBalance() {
        DefaultTableModel modelo2 = (DefaultTableModel) tablabalance.getModel();
        modelo2.getDataVector().clear();

        String[] registros = new String[2];
        String sql = "SELECT balance FROM factura WHERE idcliente='" + Integer.parseInt(idcliente.getText()) + "' ";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("balance");
                modelo2.addRow(registros);
            }
            tablabalance.setModel(modelo2);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    void bucarArticulo() {
        String[] registro = new String[6];
        String sql2 = "SELECT desart,cantidad,precom,preven,reorden FROM articulo WHERE idarticulo='" + id.getText() + "'";

        try {
            Statement st2 = cn.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);

            while (rs2.next()) {
                registro[0] = rs2.getString("desart");
                registro[1] = rs2.getString("cantidad");
                registro[2] = rs2.getString("precom");
                registro[3] = rs2.getString("preven");
                registro[4] = rs2.getString("reorden");
                desart.setText(registro[0]);
                cantidad.requestFocus();
                precom = (registro[2]);
                preven.setText(registro[3]);
                reorden2 = (registro[4]);

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    void cargarcodigo() {

        String[] registro = new String[5];
        String sql = "Select nomcli,rnccedula,telefono,sexo from cliente where idcliente='" + idcliente.getText() + "'";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registro[0] = rs.getString("nomcli");
                registro[1] = rs.getString("rnccedula");
                registro[2] = rs.getString("telefono");
                registro[3] = rs.getString("sexo");
            }
            nombre.setText(registro[0]);
            rncced.setText(registro[1]);
            tel.setText(registro[2]);
            sexo.setText(registro[3]);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

    void agregar() {
//        int value=Integer.parseInt(itbisf.getText());
//        if (value>0){
//            return;
//        }

        String pedazo = "1." + itbisf.getText() + "f";
        DecimalFormat formateador = new DecimalFormat("##########.##");
        float costo = Float.parseFloat(precom);
        float preven2 = Float.parseFloat(preven.getText());
        float itbis = preven2 / Float.parseFloat(pedazo);
        float cantidad2 = Float.parseFloat(cantidad.getText());
        float importe = preven2 * cantidad2;
        costo = costo * cantidad2;
        itbis = (preven2 - itbis) * cantidad2;

        DefaultTableModel modelo = (DefaultTableModel) tablafactura.getModel();

        String[] registro = new String[8];
        registro[0] = id.getText();
        registro[1] = desart.getText();
        registro[2] = cantidad.getText();
        registro[3] = preven.getText();
        registro[4] = formateador.format(itbis);
        registro[5] = formateador.format(importe - itbis);
        registro[6] = formateador.format(importe);
        registro[7] = formateador.format(costo);
        modelo.addRow(registro);
        tablafactura.setModel(modelo);

    }

    void sumarProductos() {
        DecimalFormat formateador = new DecimalFormat("#,###,###,###.##");
        DecimalFormat formateador2 = new DecimalFormat("##########.##");
      
        String subtotal2;
        float subtotal3 = 0;
        String jitbis;
        float itbisgeneral = 0f;

        for (int i = 0; i < tablafactura.getRowCount(); i++) {
            jitbis = (tablafactura.getValueAt(i, 4).toString());
     
            String coma = ",";
            String punto = ".";
            String replace = jitbis.replace(coma, punto);
            float jitbis2;
            jitbis2 = Float.parseFloat(replace);

            itbisgeneral = itbisgeneral + jitbis2;

            subtotal2 = (tablafactura.getValueAt(i, 5).toString());
            String subtotal25 = subtotal2.replace(coma, punto);
            float subtotal26 = Float.parseFloat(subtotal25);
            subtotal3 = subtotal3 + subtotal26;

        }
        totalitbis.setText(formateador.format(itbisgeneral));
        subtotalfinal.setText(formateador.format(subtotal3));
        totalfinal.setText(formateador.format(subtotal3 + itbisgeneral));
 
        totalitbis2=formateador2.format(itbisgeneral);
        subtotalfinal2=formateador2.format(subtotal3);
        totalfinal2=formateador2.format(subtotal3 + itbisgeneral);
        totalapagar=subtotal3+itbisgeneral;
    }

    void sumarbalance() {
        
        DecimalFormat formateador2 = new DecimalFormat("##########.##");
        float balancegeneral = 0f;
        String jitbis;
        totalcredito=0;
        
         for (int i = 0; i < tablabalance.getRowCount(); i++) {
            jitbis = (tablabalance.getValueAt(i, 0).toString());
            balancegeneral=Float.parseFloat(jitbis);
            totalcredito=totalcredito+balancegeneral;
            
         }
         
         String coma = ",";
            String punto = ".";
            String otro;
            
         float b=Float.parseFloat(limcre.getText())-totalcredito-totalapagar;
         
       otro=formateador2.format(b);
       restante.setText(otro.replace(coma, punto));
    }

    void limpiar() {
        id.setText("");
        desart.setText("");
        cantidad.setText("");
        preven.setText("");
//        itbisf.setText("");
        id.requestFocus(true);
    }

    void imprimir() {

        ArrayList lista = new ArrayList();

        for (int i = 0; i < tablafactura.getRowCount(); i++) {

            listaArticulos mortizar = new listaArticulos(tablafactura.getValueAt(i, 2) + "", tablafactura.getValueAt(i, 1) + "", tablafactura.getValueAt(i, 3) + "", tablafactura.getValueAt(i, 4) + "", tablafactura.getValueAt(i, 5) + "");
            lista.add(mortizar);

        }

        JasperReport jr = null;

        try {
            jr = (JasperReport) JRLoader.loadObjectFromFile("factura.jasper");

            HashMap parametro = new HashMap();

            parametro.put("nombreempresa", "Empresa x");
            parametro.put("numerofactura", numerofactura.getText());
            parametro.put("itbis", totalitbis.getText());
            parametro.put("total", totalfinal.getText());
            parametro.put("subtotal", subtotalfinal.getText());
            parametro.put("efectivo", efectivo.getText());
            parametro.put("devuelta", devuelta.getText());

            JasperPrint jp = JasperFillManager.fillReport(jr, parametro, new JRBeanCollectionDataSource(lista));

            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (JRException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error\n" + ex.getMessage());

        }

    }

    void numeroFactura() {
        String idfactura = "";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery("select idfactura from contador");
            rs.next();
            idfactura = rs.getString("idfactura");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        int secuencia = Integer.parseInt(idfactura);
        secuencia = secuencia + 1;
        numerofactura.setText("PC1-" + String.valueOf(secuencia));
    }
    
    void guardarDatos(){
        String coma=",";
        String punto=".";
        String replacesubtotal=subtotalfinal2.replace(coma, punto);
        String replaceitbis=totalitbis2.replace(coma, punto);
        String replacetotal=totalfinal2.replace(coma, punto);
        
        float balanceado=0;
        float lim=Float.parseFloat(restante.getText());
        
        if(deuda.equals("credito")){
            balanceado=totalapagar;
            if(totalapagar>lim){
                JOptionPane.showMessageDialog(null, "El total sobrepasa credito restante");
                return;
            }
            
        }
        if(deuda.equals("contado")){
            balanceado=0;
        }
        
        
        
        
        try {
            String sql;
             sql = "INSERT INTO factura(numerofactura,tipofactura,fecha,subtotal,itbis,total,idcliente,nomcli,balance)"
                     + "Values('"+numerofactura.getText()+"','"+deuda+"',now(),'"+replacesubtotal+"','"+replaceitbis+"','"+replacetotal+"','"+idcliente.getText()+"','"+nombre.getText()+"','"+balanceado+"')";
             
             PreparedStatement ps=cn.prepareStatement(sql);
             
             int n=ps.executeUpdate();
             
            
        }catch (Exception ex){
           JOptionPane.showInputDialog(null, ex);
            
        }
        
//        String[] registros = new String[8];
//        String sql2 = "SELECT limcre FROM cliente WHERE concat(idcliente) like '%" + idcliente.getText() + "%' ";
//
//        try {
//            Statement st = cn.createStatement();
//            ResultSet rs = st.executeQuery(sql2);
//
//            while (rs.next()) {
//                limite=Float.parseFloat(rs.getString("limcre"));
//               
//            }
//            
//
//        } catch (Exception ex) {
//            JOptionPane.showMessageDialog(null, ex);
//
//        }
//        limitef=Float.parseFloat(limcre.getText())-totalapagar;
//        
//        try{
//            PreparedStatement sql3 = cn.prepareStatement("UPDATE cliente SET limcre='" + limitef + "' where idcliente='" + idcliente.getText() + "'");
//sql3.executeUpdate();
//}
//catch (SQLException ex){
//Logger.getLogger(registrar_articulos.class.getName()).log(Level.SEVERE, null, ex);
//}

if (contado.isSelected()){
    efectivoencaja=totalapagar;
}else{efectivoencaja=0;}

try {
            String sql2;
             sql2 = "INSERT INTO sesion(idsesion,numerofactura,total)"
                     + "Values('"+Integer.parseInt(cogeme.getText())+"','"+numerofactura.getText()+"','"+efectivoencaja+"')";
             
             PreparedStatement ps=cn.prepareStatement(sql2);
             
             int n=ps.executeUpdate();
             
            
        }catch (Exception ex){
           JOptionPane.showInputDialog(null, ex);
            
        }
    }
   
    void insertarDetalleFactura(){
        
        for (int i = 0; i < tablafactura.getRowCount(); i++) {
            
            try {
            String sql;
             sql = "INSERT INTO detallefactura(idfactura,idarticulo,desart,cantidad,precio,itbis,importe,subtotal)"
                     + "Values('"+numerofactura.getText()+"','"+tablafactura.getValueAt(i, 0)+"','"+tablafactura.getValueAt(i, 1)+"','"+tablafactura.getValueAt(i, 2)+"','"+tablafactura.getValueAt(i, 3)+"','"+tablafactura.getValueAt(i, 4)+"','"+tablafactura.getValueAt(i, 5)+"','"+tablafactura.getValueAt(i, 6)+"')";
             
             PreparedStatement ps=cn.prepareStatement(sql);
             
             int n=ps.executeUpdate();
             
            
        }catch (SQLException ex){
           JOptionPane.showInputDialog(null, ex);
            
        }
            
            
        }
    }
    
    void modificarContador(){
        
try{
    PreparedStatement ps3= cn.prepareStatement("UPDATE contador SET idfactura=idfactura+1");
    //PreparedStatement ps3= cn.prepareStatement("UPDATE articulo SET desart='"+desart.getText()+"' where idarticulo='"+id.getText()+"'");
    ps3.executeUpdate();

}catch (SQLException ex){
Logger.getLogger(registrar_articulos.class.getName()).log(Level.SEVERE, null, ex);
}
    }
    
    void validarCamposFactura(){
        
        if(idcliente.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No ha registrado el codigo del cliente");
            idcliente.requestFocus();
            return;
        }
        if(nombre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No ha registrado el nombre del cliente");
            nombre.requestFocus();
            return;
        }
        if(rncced.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No ha registrado el rnc o la cedula del cliente");
            rncced.requestFocus();
            return;
        }
        if(tel.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No ha registrado el telefono del cliente");
            tel.requestFocus();
            return;
        }
        if(sexo.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No ha registrado el sexo del cliente");
            sexo.requestFocus();
            return;
        }
        if(limcre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "No ha registrado el limite de credito del cliente");
            limcre.requestFocus();
            return;
        }
        
        
        
        if(validacion.equals("")){
            JOptionPane.showMessageDialog(null, "No ha facturado ningun articulo");
            id.requestFocus();
            return;
        }
        if (contado.isSelected()){
            verificarEfectivo();
        }
        s++;
        
    }
    
    void verificarEfectivo(){
        if(efectivo.getText().equals("")){
                JOptionPane.showMessageDialog(null, "Introduzca la cantidad en efectivo con la que pagara o seleccione hacer la factura a credito");
            efectivo.requestFocus();
            ret=3;
            return;
            }
    }
    
    void modificarArticulo() {
        float rebaja;
        
        for (int i = 0; i < tablafactura.getRowCount(); i++) {
            try {
                rebaja=Float.parseFloat(tablafactura.getValueAt(i, 2).toString());
            PreparedStatement ps3 = cn.prepareStatement("UPDATE articulo SET cantidad=cantidad-'" + rebaja + "' where idarticulo='" + tablafactura.getValueAt(i, 0) + "'");
            ps3.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(registrar_articulos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
    void limpiarcampos(){
        idcliente.setText("");
        nombre.setText("");
        rncced.setText("");
        tel.setText("");
        sexo.setText("");
        limcre.setText("");
        restante.setText("");
        idcliente.enable(true);
        nombre.enable(true);
        rncced.enable(true);
        tel.enable(true);
        sexo.enable(true);
        limcre.enable(true);
        restante.enable(true);
        tablafactura.removeAll();
         DefaultTableModel modelo=(DefaultTableModel) tablafactura.getModel();
        int a =modelo.getRowCount()-1;

for(int i=a; i>=0; i--){

modelo.removeRow(i );
}

 DefaultTableModel modelo2=(DefaultTableModel) tablabalance.getModel();
        int z =modelo2.getRowCount()-1;

for(int i=z; i>=0; i--){

modelo.removeRow(i );
}
        
        
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idcliente = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rncced = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tel = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        sexo = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        limcre = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        restante = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        id = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaarticulos = new javax.swing.JTable();
        desart = new javax.swing.JTextField();
        cantidad = new javax.swing.JTextField();
        preven = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        itbisf = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablafactura = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        numerofactura = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        credito = new javax.swing.JRadioButton();
        contado = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        subtotalfinal = new javax.swing.JTextField();
        totalitbis = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        totalfinal = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        efectivo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        devuelta = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablabalance = new javax.swing.JTable();
        fecha = new com.toedter.calendar.JDateChooser();
        jButton5 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        t = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        palcuadre = new javax.swing.JTextField();
        jButton8 = new javax.swing.JButton();
        cogeme = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Facturacion");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Cliente"));

        jLabel1.setText("ID");

        idcliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idclienteActionPerformed(evt);
            }
        });

        jLabel2.setText("Nombre");

        jLabel3.setText("RNC/Cedula");

        jLabel4.setText("Telefono");

        jButton1.setText("Buscar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel12.setText("Sexo");

        jButton3.setText("Cliente generico");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel13.setText("Limite de credito");

        jLabel20.setText("Credito restrante");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sexo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(idcliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addComponent(nombre)
                    .addComponent(rncced)
                    .addComponent(tel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(limcre, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(restante, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rncced, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(limcre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(restante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos articulo"));

        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });
        id.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                idKeyReleased(evt);
            }
        });

        tablaarticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripcion", "Cantidad", "Precio", "costo"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaarticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaarticulosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaarticulos);
        if (tablaarticulos.getColumnModel().getColumnCount() > 0) {
            tablaarticulos.getColumnModel().getColumn(0).setMinWidth(41);
            tablaarticulos.getColumnModel().getColumn(0).setMaxWidth(41);
            tablaarticulos.getColumnModel().getColumn(2).setMinWidth(88);
            tablaarticulos.getColumnModel().getColumn(2).setMaxWidth(88);
            tablaarticulos.getColumnModel().getColumn(3).setMinWidth(88);
            tablaarticulos.getColumnModel().getColumn(3).setMaxWidth(88);
            tablaarticulos.getColumnModel().getColumn(4).setMinWidth(0);
            tablaarticulos.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        desart.setEnabled(false);

        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });

        preven.setEnabled(false);
        preven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevenActionPerformed(evt);
            }
        });

        jButton2.setText("Buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel14.setText("Descripcion");

        jLabel15.setText("Cantidad");

        jLabel16.setText("Precio");

        jLabel17.setText("ITBIS");

        itbisf.setText("18");
        itbisf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itbisfActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(desart, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel16)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(preven, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel17)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(itbisf, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(desart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(preven, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(itbisf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tablafactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripcion", "Cantidad", "Precio por unidad", "ITBIS", "Importe", "Subtotal", "costo"
            }
        ));
        jScrollPane2.setViewportView(tablafactura);
        if (tablafactura.getColumnModel().getColumnCount() > 0) {
            tablafactura.getColumnModel().getColumn(0).setMinWidth(66);
            tablafactura.getColumnModel().getColumn(0).setMaxWidth(66);
            tablafactura.getColumnModel().getColumn(2).setMinWidth(99);
            tablafactura.getColumnModel().getColumn(2).setMaxWidth(99);
            tablafactura.getColumnModel().getColumn(3).setMinWidth(99);
            tablafactura.getColumnModel().getColumn(3).setMaxWidth(99);
            tablafactura.getColumnModel().getColumn(4).setMinWidth(99);
            tablafactura.getColumnModel().getColumn(4).setMaxWidth(99);
            tablafactura.getColumnModel().getColumn(5).setMinWidth(99);
            tablafactura.getColumnModel().getColumn(5).setMaxWidth(99);
            tablafactura.getColumnModel().getColumn(6).setMinWidth(99);
            tablafactura.getColumnModel().getColumn(6).setMaxWidth(99);
            tablafactura.getColumnModel().getColumn(7).setMinWidth(0);
            tablafactura.getColumnModel().getColumn(7).setMaxWidth(0);
        }

        numerofactura.setEnabled(false);

        jLabel5.setText("Numero de factura");

        jLabel7.setText("Fecha");

        buttonGroup1.add(credito);
        credito.setText("Credito");
        credito.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                creditoMouseClicked(evt);
            }
        });
        credito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditoActionPerformed(evt);
            }
        });

        buttonGroup1.add(contado);
        contado.setText("Contado");
        contado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contadoMouseClicked(evt);
            }
        });

        jLabel9.setText("Sub total");

        subtotalfinal.setEnabled(false);

        totalitbis.setEnabled(false);

        jLabel10.setText("ITBIS");

        totalfinal.setEnabled(false);

        jLabel11.setText("TOTAL");

        jLabel18.setText("Total recibido");

        efectivo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                efectivoKeyReleased(evt);
            }
        });

        jLabel19.setText("Devuelta");

        devuelta.setEnabled(false);

        tablabalance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "balance"
            }
        ));
        jScrollPane3.setViewportView(tablabalance);
        if (tablabalance.getColumnModel().getColumnCount() > 0) {
            tablabalance.getColumnModel().getColumn(0).setMinWidth(0);
            tablabalance.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addGap(44, 44, 44)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(devuelta)
                    .addComponent(efectivo)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(totalfinal)
                            .addComponent(totalitbis)
                            .addComponent(subtotalfinal, javax.swing.GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subtotalfinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalitbis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalfinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(efectivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(devuelta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        fecha.setDateFormatString("YYYY/MM/d");
        fecha.setEnabled(false);

        jButton5.setText("Guardar factura");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel6.setText("Factura a");

        jButton6.setText(" Menu principal");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        t.setText("jTextField1");

        jButton7.setText("Retirar articulo");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        palcuadre.setText("0");

        jButton8.setText("Cuadre");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        cogeme.setText("0");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(108, 108, 108)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(palcuadre, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6)
                                .addComponent(jLabel5)
                                .addComponent(jLabel7)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(credito)
                                .addGap(18, 18, 18)
                                .addComponent(contado))
                            .addComponent(numerofactura, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cogeme, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(165, 165, 165))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(credito)
                    .addComponent(contado)
                    .addComponent(jLabel6))
                .addGap(13, 13, 13)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addComponent(t, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(numerofactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel7)))
                .addGap(19, 19, 19)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(palcuadre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cogeme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new consultar_articulos().setVisible(true);  // TODO add your handling code here:
        v=2;
        t.setText("t");
        consultar_articulos.v1.setText("t");
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed


        
        cargarArticulos("");
        
        
        
        int cod;

        cod=tablaarticulos.getRowCount()+1;
       int dijitado=Integer.parseInt(id.getText());

        if(dijitado>cod){
            JOptionPane.showMessageDialog(null, "El codigo digitado no pertenece a ningun articulo");
            id.setText("");
            id.requestFocus();
            return;
        }

        bucarArticulo();        // TODO add your handling code here:
        
    }//GEN-LAST:event_idActionPerformed

    private void idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyReleased
        cargarArticulos(id.getText());        // TODO add your handling code here:
    }//GEN-LAST:event_idKeyReleased

    private void tablaarticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaarticulosMouseClicked
        // TODO add your handling code here:

        int fila = tablaarticulos.getSelectedRow();
        if (fila >= 0) {
            id.setText(tablaarticulos.getValueAt(fila, 0).toString());
            id.requestFocus();
        }
    }//GEN-LAST:event_tablaarticulosMouseClicked

    private void prevenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prevenActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        if (itbisf.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Escriba un valor para el ITBIS");
            return;
        }
        
        if (desart.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Escriba la descripcion");
            return;
        }
        
        if (cantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Escriba la cantidad");
            return;
        }
        
        if (preven.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Escriba el precio de venta");
            return;
        }
        
        

        int value = Integer.parseInt(itbisf.getText());
        if (value < 0) {
            JOptionPane.showMessageDialog(null, "el ITBIS debe ser igual o mayor a cero");
            return;
        }
        agregar();         // TODO add your handling code here:
        sumarProductos();
        limpiar();
        validacion="bien";
        cargarBalance();
        sumarbalance();
    }//GEN-LAST:event_cantidadActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        new consultaClientes().setVisible(true);

        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        idcliente.setText("0");
        nombre.setText("N/A");
        rncced.setText("N/A");
        tel.setText("N/A");
        sexo.setText("N/A");
        limcre.setText("0");
        idcliente.enable(false);
        nombre.enable(false);
        rncced.enable(false);
        tel.enable(false);
        sexo.enable(false);
        limcre.enable(false);


    }//GEN-LAST:event_jButton3ActionPerformed

    private void idclienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idclienteActionPerformed
        cargarcodigo();        // TODO add your handling code here:
        idcliente.enable(false);
        nombre.enable(false);
        rncced.enable(false);
        tel.enable(false);
        sexo.enable(false);
        

    }//GEN-LAST:event_idclienteActionPerformed

    private void itbisfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itbisfActionPerformed
 
        
        
        
        if (itbisf.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Escriba un valor para el ITBIS");
            return;
        }
        
        if (desart.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Escriba la descripcion");
            return;
        }
        
        if (cantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Escriba la cantidad");
            return;
        }
        
        if (preven.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Escriba el precio de venta");
            return;
        }
        
        

        int value = Integer.parseInt(itbisf.getText());
        if (value < 0) {
            JOptionPane.showMessageDialog(null, "el ITBIS debe ser igual o mayor a cero");
            return;
        }
        agregar();        
        sumarProductos();
        limpiar();
        validacion="bien"; 
        cargarBalance();
    }//GEN-LAST:event_itbisfActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        validarCamposFactura();
                          
        if(s>0){
         if (credito.isSelected()){
            deuda="credito";
            
        }
         if (contado.isSelected()){
            deuda="contado";
            balance=0;
        }
          if (credito.isSelected()){
           if(totalapagar>Float.parseFloat(restante.getText())){
             JOptionPane.showMessageDialog(null, "Esta factura a credito supera su limite de credito");
             return;
             }
          }
          
          if(ret>1){
              return;
          }
         
         guardarDatos();
        
         insertarDetalleFactura();
         
         modificarContador();
         
         modificarArticulo();
         
         JOptionPane.showMessageDialog(null, "Factura Registrada");
         
         ventas++;
         vendido=vendido+totalapagar;
            
           imprimir();

         this.dispose();
      
         new facturacion().setVisible(true);
         
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
new menu().setVisible(true);
        this.dispose();         // TODO add your handling code here:
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void creditoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_creditoMouseClicked
        // TODO add your handling code here:
        efectivo.enable(false);
    }//GEN-LAST:event_creditoMouseClicked

    private void contadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contadoMouseClicked
        // TODO add your handling code here:
        efectivo.enable(true);
    }//GEN-LAST:event_contadoMouseClicked

    private void creditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_creditoActionPerformed
        // TODO add your handling code here:
        efectivo.enable(false);
    }//GEN-LAST:event_creditoActionPerformed

    private void efectivoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_efectivoKeyReleased
        // TODO add your handling code here:
        if(efectivo.getText().equals("")) return;
        DecimalFormat formateador2 = new DecimalFormat("##########.##");
        
        int a=Integer.parseInt(efectivo.getText());
        devuelta.setText(formateador2.format(a-totalapagar));
    }//GEN-LAST:event_efectivoKeyReleased

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        
        DefaultTableModel modelo=(DefaultTableModel)tablafactura.getModel();

        int c=tablafactura.getSelectedRow();
        modelo.removeRow(c);
        sumarProductos();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
new cuadrecaja().setVisible(true);
       cuadrecaja.dineroinicial.setText(palcuadre.getText());
       cuadrecaja.sesion.setText(cogeme.getText());

this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(facturacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new facturacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField cogeme;
    private javax.swing.JRadioButton contado;
    private javax.swing.JRadioButton credito;
    private javax.swing.JTextField desart;
    private javax.swing.JTextField devuelta;
    private javax.swing.JTextField efectivo;
    private com.toedter.calendar.JDateChooser fecha;
    public static javax.swing.JTextField id;
    public static javax.swing.JTextField idcliente;
    private javax.swing.JTextField itbisf;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    public static javax.swing.JTextField limcre;
    public static javax.swing.JTextField nombre;
    private javax.swing.JTextField numerofactura;
    private javax.swing.JTextField palcuadre;
    private javax.swing.JTextField preven;
    private javax.swing.JTextField restante;
    public static javax.swing.JTextField rncced;
    public static javax.swing.JTextField sexo;
    private javax.swing.JTextField subtotalfinal;
    public static javax.swing.JTextField t;
    private javax.swing.JTable tablaarticulos;
    public static javax.swing.JTable tablabalance;
    private javax.swing.JTable tablafactura;
    public static javax.swing.JTextField tel;
    private javax.swing.JTextField totalfinal;
    private javax.swing.JTextField totalitbis;
    // End of variables declaration//GEN-END:variables

    conectar cc = new conectar();
    Connection cn = cc.conexion();

    String precom = "0";
    String reorden2 = "0";
    String deuda;
    
    String totalitbis2="";
    String     subtotalfinal2="";
    String     totalfinal2="";
    
    String validacion="";
    int s=0;
    
    int v=1;
    
    float totalapagar=0;
    float limite;
    float limitef;
    
    float balance;
    float totalcredito;
    
    int ventas=0;
    float vendido;
    
    float efectivoencaja=0;
    int ret=1;
}
