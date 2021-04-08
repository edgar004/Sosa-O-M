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
import java.util.ArrayList;
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
import proyectyofinalangelsosa.listaConsultaArticulos;

/**
 *
 * @author Jorge L. Guzmán M.
 */
public class consultar_articulos extends javax.swing.JFrame {

   
    public consultar_articulos() {
        initComponents();
        buscarnombre.requestFocus();
        cargarArticulos("");
        tipoarticulo();
        
    }
       int p;
       int x;
    
    void guardarDatos(){
        try {
            String sql;
             sql = "INSERT INTO articulo(desart,cantidad,precom,preven,reorden)Values('"+desart.getText()+"','"+cantidad.getText()+"','"+precom.getText()+"','"+preven.getText()+"','"+reorden.getText()+"')";
             
             PreparedStatement ps=cn.prepareStatement(sql);
             
             int n=ps.executeUpdate();
             
            
        }catch (Exception ex){
           JOptionPane.showInputDialog(null, ex);
            
        }
    }
    
    void limpiarCampos(){
        if (x==0){
        id.setText("");
        desart.setText("");
        cantidad.setText("");
        precom.setText("");
        preven.setText("");
        reorden.setText("");
        id.requestFocus(true);
    }
    }
    
    void comprobarCampos(){
        if (desart.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo de la descripcion del articulo esta vacio");
            desart.requestFocus(true);
            return;
//            p++;
              }
        if (cantidad.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo de la cantidad del articulo esta vacio");
            cantidad.requestFocus(true);
//            p++;
            return;
        }
        if (precom.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del precio de compra del articulo esta vacio");
            precom.requestFocus(true);
//            p++;
            return;
        }
        if (preven.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del precio de venta del articulo esta vacio");
            preven.requestFocus(true);
//            p++;
            return;
        }
        if (reorden.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del reorden del articulo esta vacio");
            reorden.requestFocus(true);
//            p++;
            return;
        }
        p++;
    }
    
    void bucarArticulo(){
        String[] registro= new String[6];
        String sql2="SELECT desart,cantidad,precom,preven,reorden FROM articulo WHERE idarticulo='"+id.getText()+"'";
        
        try{
            Statement st2=cn.createStatement();
            ResultSet rs2=st2.executeQuery(sql2);
            
            while (rs2.next()){
                registro[0]=rs2.getString("desart");
                registro[1]=rs2.getString("cantidad");
                registro[2]=rs2.getString("precom");
                registro[3]=rs2.getString("preven");
                registro[4]=rs2.getString("reorden");
                desart.setText(registro[0]);
               cantidad.setText(registro[1]);
               precom.setText(registro[2]);
               preven.setText(registro[3]);
               reorden.setText(registro[4]);
               
                
            }
            
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    void tipoarticulo() {
        //tipo.removeAllItems();
        String sql = "select idtipo,destipo from tipoarticulo";

        try {

            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                tipo.addItem(rs.getString("idtipo") + "-" + rs.getString("destipo"));
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }
    
    void cargarArticulos(String valor){
        DefaultTableModel modelo= (DefaultTableModel)tablaarticulos.getModel();
        modelo.getDataVector().clear();
        
        String[] registros=new String[8];
        String sql="SELECT desart,idarticulo,cantidad,precom,preven,reorden,tipoarticulo FROM articulo WHERE concat(desart) like '%"+valor+"%' ";
        
        
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            
            while (rs.next()){
                registros[0]=rs.getString("idarticulo");
                registros[1]=rs.getString("desart");
                registros[2]=rs.getString("cantidad");
                registros[3]=rs.getString("precom");
                registros[4]=rs.getString("preven");
                registros[5]=rs.getString("reorden");
                registros[6]=rs.getString("tipoarticulo");
                
                modelo.addRow(registros);
            }
            tablaarticulos.setModel(modelo);
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            
        }
    }
    
    void modificarArticulo(){
        
try{
    PreparedStatement ps3= cn.prepareStatement("UPDATE articulo SET desart='"+desart.getText()+"',cantidad='"+cantidad.getText()+"',precom='"+precom.getText()+"',preven='"+preven.getText()+"',reorden='"+reorden.getText()+"' where idarticulo='"+id.getText()+"'");
    ps3.executeUpdate();

}catch (SQLException ex){
Logger.getLogger(consultar_articulos.class.getName()).log(Level.SEVERE, null, ex);
}
    }
    
    void eliminarArticulo(){
        
try{
    PreparedStatement ps3= cn.prepareStatement("delete from articulo where idarticulo='"+id.getText()+"'");
    ps3.executeUpdate();

}catch (SQLException ex){
Logger.getLogger(consultar_articulos.class.getName()).log(Level.SEVERE, null, ex);
}
    }
    
    void imprimir() {

        DecimalFormat formateador = new DecimalFormat("#,###,###,###.##");

        ArrayList lista2 = new ArrayList();

        for (int i = 0; i < tablaarticulos.getRowCount(); i++) {

            listaConsultaArticulos mortizar = new listaConsultaArticulos(tablaarticulos.getValueAt(i, 0) + "",tablaarticulos.getValueAt(i, 1) + "",tablaarticulos.getValueAt(i, 2) + "",tablaarticulos.getValueAt(i, 3) + "",tablaarticulos.getValueAt(i, 4) + "",tablaarticulos.getValueAt(i, 5) + "",tablaarticulos.getValueAt(i, 6) + "");
            lista2.add(mortizar);

        }
        

        JasperReport jr = null;

        try {
            jr = (JasperReport) JRLoader.loadObjectFromFile("consultaArticulos.jasper");

            HashMap parametro = new HashMap();

            parametro.put("nombreempresa", "Empresa x");
            
            JasperPrint jp = JasperFillManager.fillReport(jr, parametro, new JRBeanCollectionDataSource(lista2));

            JasperViewer jv = new JasperViewer(jp, false);
            jv.setVisible(true);

        } catch (JRException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error\n" + ex.getMessage());

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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        id = new javax.swing.JTextField();
        desart = new javax.swing.JTextField();
        cantidad = new javax.swing.JTextField();
        precom = new javax.swing.JTextField();
        preven = new javax.swing.JTextField();
        reorden = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaarticulos = new javax.swing.JTable();
        buscarnombre = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        v1 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        tipo = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consulta de articulos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Informacion de articulo"));

        jLabel1.setText("ID");

        jLabel6.setText("Descripcion");

        jLabel7.setText("Cantidad");

        jLabel8.setText("Precio de compra");

        jLabel9.setText("Precio de venta");

        jLabel10.setText("Reorden");

        jButton1.setText("Guardar");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Menu principal");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Eliminar");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Actualizar");
        jButton4.setEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Buscar");
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        id.setEditable(false);
        id.setEnabled(false);
        id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idActionPerformed(evt);
            }
        });

        desart.setEditable(false);
        desart.setEnabled(false);
        desart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desartActionPerformed(evt);
            }
        });

        cantidad.setEditable(false);
        cantidad.setEnabled(false);
        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });

        precom.setEditable(false);
        precom.setEnabled(false);
        precom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                precomActionPerformed(evt);
            }
        });

        preven.setEditable(false);
        preven.setEnabled(false);
        preven.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prevenActionPerformed(evt);
            }
        });

        reorden.setEditable(false);
        reorden.setEnabled(false);
        reorden.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reordenActionPerformed(evt);
            }
        });

        tablaarticulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descripcion", "Cantidad", "Precio de compra", "Precio de venta", "Reorden", "Tipo"
            }
        ));
        tablaarticulos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaarticulosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaarticulos);
        if (tablaarticulos.getColumnModel().getColumnCount() > 0) {
            tablaarticulos.getColumnModel().getColumn(0).setMaxWidth(50);
            tablaarticulos.getColumnModel().getColumn(1).setMinWidth(250);
            tablaarticulos.getColumnModel().getColumn(1).setMaxWidth(300);
            tablaarticulos.getColumnModel().getColumn(2).setMinWidth(125);
            tablaarticulos.getColumnModel().getColumn(2).setMaxWidth(125);
            tablaarticulos.getColumnModel().getColumn(3).setMinWidth(125);
            tablaarticulos.getColumnModel().getColumn(3).setMaxWidth(125);
            tablaarticulos.getColumnModel().getColumn(4).setMinWidth(125);
            tablaarticulos.getColumnModel().getColumn(4).setMaxWidth(125);
            tablaarticulos.getColumnModel().getColumn(5).setMinWidth(125);
            tablaarticulos.getColumnModel().getColumn(5).setMaxWidth(125);
            tablaarticulos.getColumnModel().getColumn(6).setMinWidth(36);
            tablaarticulos.getColumnModel().getColumn(6).setMaxWidth(36);
        }

        buscarnombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarnombreActionPerformed(evt);
            }
        });
        buscarnombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buscarnombreKeyReleased(evt);
            }
        });

        jLabel11.setText("Buscar");

        jButton5.setText("Imprimir listado");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        tipo.setEnabled(false);
        tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoActionPerformed(evt);
            }
        });

        jLabel12.setText("Tipo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(89, 89, 89)
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(v1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(46, 46, 46)
                        .addComponent(desart, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(57, 57, 57)
                        .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(18, 18, 18)
                        .addComponent(precom, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(25, 25, 25)
                        .addComponent(preven, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(buscarnombre, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(6, 6, 6)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel12))
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reorden, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                            .addComponent(tipo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 865, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabel1))
                                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addComponent(v1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jLabel6))
                            .addComponent(desart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel7))
                            .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel8))
                            .addComponent(precom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel9))
                            .addComponent(preven, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel10))
                            .addComponent(reorden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(jButton3))
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton6)
                            .addComponent(jButton4))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscarnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton2)
                            .addComponent(jButton5)))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (id.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Escriba el codigo del articulo");
            id.requestFocus(true);
            return;
            
        }
        bucarArticulo();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        comprobarCampos();
        if (p>0){
        guardarDatos();
        limpiarCampos();
        cargarArticulos("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

     
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        new menu().setVisible(true);
        this.dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void prevenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prevenActionPerformed
reorden.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_prevenActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
        if (id.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Escriba el codigo del articulo");
            return;
        }
        bucarArticulo();
        desart.requestFocus();
    }//GEN-LAST:event_idActionPerformed

    private void buscarnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarnombreKeyReleased
        // TODO add your handling code here:
        cargarArticulos(buscarnombre.getText());
    }//GEN-LAST:event_buscarnombreKeyReleased

    private void tablaarticulosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaarticulosMouseClicked
        // TODO add your handling code here:
        int fila=tablaarticulos.getSelectedRow();
        if (fila>=0){
            id.setText(tablaarticulos.getValueAt(fila, 0).toString());
            desart.setText(tablaarticulos.getValueAt(fila, 1).toString());
            cantidad.setText(tablaarticulos.getValueAt(fila, 2).toString());
            precom.setText(tablaarticulos.getValueAt(fila, 3).toString());
            preven.setText(tablaarticulos.getValueAt(fila, 4).toString());
            reorden.setText(tablaarticulos.getValueAt(fila, 5).toString());
            
            
            if (v1.getText().equals("t")){
                new facturacion().setVisible(true);
            facturacion.id.setText(id.getText());
            facturacion.id.requestFocus(true);
            facturacion.t.setText("");
            
            this.dispose();
            }
            
        }
    }//GEN-LAST:event_tablaarticulosMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
         if (id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del codigo del articulo esta vacio");
            id.requestFocus(true);
//            n++;
            return;
        }
        comprobarCampos();
        modificarArticulo();
        JOptionPane.showMessageDialog(null, "cambios guardados");
        cargarArticulos("");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        eliminarArticulo();
        JOptionPane.showMessageDialog(null, "articulo eliminado");
        cargarArticulos("");
        limpiarCampos();
        
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void buscarnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarnombreActionPerformed

    private void desartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desartActionPerformed
 cantidad.requestFocus();       // TODO add your handling code here:
    }//GEN-LAST:event_desartActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
precom.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void precomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_precomActionPerformed
preven.requestFocus();        // TODO add your handling code here:
    }//GEN-LAST:event_precomActionPerformed

    private void reordenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reordenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_reordenActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
            imprimir();
                  // TODO add your handling code here:
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoActionPerformed
        // TODO add your handling code here:
        String combo="";
        combo= tipo.getSelectedItem().toString();
        String[] arraycombo = combo.split("-");

        tipoarticulo = arraycombo[0];
    }//GEN-LAST:event_tipoActionPerformed

    
    
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
            java.util.logging.Logger.getLogger(consultar_articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultar_articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultar_articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultar_articulos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consultar_articulos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscarnombre;
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField desart;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField precom;
    private javax.swing.JTextField preven;
    private javax.swing.JTextField reorden;
    private javax.swing.JTable tablaarticulos;
    private javax.swing.JComboBox<String> tipo;
    public static javax.swing.JTextField v1;
    // End of variables declaration//GEN-END:variables


    conectar cc = new conectar();
    Connection cn = cc.conexion();

    String tipoarticulo = "";
 
}
