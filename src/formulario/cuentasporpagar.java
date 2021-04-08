/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package formulario;

import static formulario.cuadrecaja.dineroinicial;
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
import proyectyofinalangelsosa.listaCuadre;

/**
 *
 * @author Jorge L. Guzmán M.
 */
public class cuentasporpagar extends javax.swing.JFrame {

   
    public cuentasporpagar() {
        initComponents();
        cero=0;
        deuda="pago de cuentas";
        cargarClientes("");
    }
void cargarClientes(String valor) {
        DefaultTableModel modelo = (DefaultTableModel) tablaclientes.getModel();
        modelo.getDataVector().clear();

        String[] registros = new String[6];
        String sql = "SELECT idcliente,nomcli,limcre FROM cliente ";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("idcliente");
                registros[1] = rs.getString("nomcli");
                registros[2] = rs.getString("limcre");
                modelo.addRow(registros);
            }
            tablaclientes.setModel(modelo);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }

void cargarBalance() {
    if(idcliente.getText().equals("")) return;
        DefaultTableModel modelo2 = (DefaultTableModel) tablabalance.getModel();
        modelo2.getDataVector().clear();

        String[] registros = new String[2];
        String sql;
        sql= "SELECT balance FROM factura WHERE idcliente='" + Integer.parseInt(idcliente.getText()) + "' and balance >'"+cero+"' ";

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
        
        DefaultTableModel modelo21 = (DefaultTableModel) tablabalance1.getModel();
        modelo21.getDataVector().clear();

        String[] registros2 = new String[2];
        String sql2;
        sql2= "SELECT balance FROM factura WHERE idcliente='" + Integer.parseInt(idcliente.getText()) + "'";

        try {
            Statement st3 = cn.createStatement();
            ResultSet rs3 = st3.executeQuery(sql2);

            while (rs3.next()) {
                registros2[0] = rs3.getString("balance");
                modelo21.addRow(registros2);
            }
            tablabalance1.setModel(modelo21);
        sumarbalance();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        
    }

void sumarbalance() {
         DecimalFormat formateador = new DecimalFormat("##########.##");
        
        float balancegeneral = 0f;
        String jitbis;
        totalcredito=0;
        
         for (int i = 0; i < tablabalance1.getRowCount(); i++) {
            jitbis = (tablabalance1.getValueAt(i, 0).toString());
            balancegeneral=Float.parseFloat(jitbis);
            totalcredito=totalcredito+balancegeneral;
            
         }
         float b=Float.parseFloat(limcre.getText())-totalcredito;
       restante.setText(formateador.format(b));
            
         
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
        numerofactura=("PC1-" + String.valueOf(secuencia));
    }

void guardarDatos() {
        try {
            String sql;
           sql = "INSERT INTO factura(numerofactura,tipofactura,fecha,subtotal,itbis,total,idcliente,nomcli,balance)"
                     + "Values('"+numerofactura+"','"+deuda+"',now(),'"+cero+"','"+cero+"','"+abono.getText()+"','"+idcliente.getText()+"','"+nomcli.getText()+"','"+negativo+"')";
             
            PreparedStatement ps = cn.prepareStatement(sql);

            int n = ps.executeUpdate();

        } catch (Exception ex) {
            JOptionPane.showInputDialog(null, ex);

        }
    }
  void modificarContador(){
        
try{
    PreparedStatement ps3= cn.prepareStatement("UPDATE contador SET idfactura=idfactura+1");
    ps3.executeUpdate();

}catch (SQLException ex){
Logger.getLogger(registrar_articulos.class.getName()).log(Level.SEVERE, null, ex);
}
    }
  
  void imprimir() {
      String vacio="";
 ArrayList lista3 = new ArrayList();
      
      for (int i = 0; i < 2; i++) {

            listaCuadre mortizar = new listaCuadre(vacio + "",vacio + "");
            lista3.add(mortizar);

        }
      JasperReport jr = null;

      finai=Float.parseFloat(restante.getText())+Float.parseFloat(abono.getText());
      
        try {
            jr = (JasperReport) JRLoader.loadObjectFromFile("cuentasXPagar.jasper");

            HashMap parametro = new HashMap();

            parametro.put("nombreempresa", "Empresa x");
            parametro.put("limcre", limcre.getText());
            parametro.put("abono", abono.getText());
            parametro.put("restante", String.valueOf(finai));
            
            JasperPrint jp = JasperFillManager.fillReport(jr, parametro, new JRBeanCollectionDataSource(lista3));

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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        idcliente = new javax.swing.JTextField();
        nomcli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        limcre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        restante = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaclientes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablabalance = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        abono = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablabalance1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Cuentas por pagar");

        jLabel1.setText("ID");

        jLabel2.setText("Nombre cliente");

        idcliente.setEnabled(false);

        nomcli.setEnabled(false);

        jLabel3.setText("Limite de credito");

        limcre.setEnabled(false);

        jLabel4.setText("Credito restante");

        restante.setEnabled(false);

        tablaclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre del cliente", "Limite de credito"
            }
        ));
        tablaclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaclientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaclientes);
        if (tablaclientes.getColumnModel().getColumnCount() > 0) {
            tablaclientes.getColumnModel().getColumn(0).setMinWidth(35);
            tablaclientes.getColumnModel().getColumn(0).setMaxWidth(35);
            tablaclientes.getColumnModel().getColumn(2).setMinWidth(99);
            tablaclientes.getColumnModel().getColumn(2).setMaxWidth(99);
        }

        tablabalance.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cuentas"
            }
        ));
        jScrollPane2.setViewportView(tablabalance);

        jButton1.setText("Menu principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("abono");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel5.setText("Cantidad que va a abonar");

        tablabalance1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cuentas"
            }
        ));
        jScrollPane3.setViewportView(tablabalance1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(restante, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(abono))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(limcre, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jLabel5))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(idcliente, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(nomcli, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(531, 531, 531)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(242, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel1))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idcliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton1)))
                .addGap(4, 4, 4)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nomcli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton2)))
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel3))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(limcre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(restante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(abono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaclientesMouseClicked
        // TODO add your handling code here:
       int fila = tablaclientes.getSelectedRow();
        if (fila >= 0) {
            idcliente.setText(tablaclientes.getValueAt(fila, 0).toString());
            nomcli.setText(tablaclientes.getValueAt(fila, 1).toString());
            limcre.setText(tablaclientes.getValueAt(fila, 2).toString());
            
        }
        cargarBalance();
    }//GEN-LAST:event_tablaclientesMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
new menu().setVisible(true);        // TODO add your handling code here:
this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        
        if (idcliente.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Seleccione un cliente");
            idcliente.requestFocus();
            return;
        }
         if (nomcli.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Seleccione un cliente");
            nomcli.requestFocus();
            return;
        }
          if (limcre.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Seleccione un cliente");
            limcre.requestFocus();
            return;
        }
          
        if (abono.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Escriba la cantidad que va a abonar");
            abono.requestFocus();
            return;
        }
        negativo=Float.parseFloat(String.valueOf(abono.getText()))*-1;
        numeroFactura();
        guardarDatos();
        modificarContador();
        imprimir();
        this.dispose();
        new cuentasporpagar().setVisible(true);
        
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(cuentasporpagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cuentasporpagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cuentasporpagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cuentasporpagar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cuentasporpagar().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField abono;
    private javax.swing.JTextField idcliente;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField limcre;
    private javax.swing.JTextField nomcli;
    private javax.swing.JTextField restante;
    private javax.swing.JTable tablabalance;
    private javax.swing.JTable tablabalance1;
    private javax.swing.JTable tablaclientes;
    // End of variables declaration//GEN-END:variables

conectar cc = new conectar();
    Connection cn = cc.conexion();
    float totalcredito;
    String numerofactura;
    String deuda ;
    float cero=0;
    float negativo;
    float finai= 0;
}
