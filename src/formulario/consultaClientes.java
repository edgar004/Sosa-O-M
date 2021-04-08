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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectyofinalangelsosa.conectar;

/**
 *
 * @author Jorge L. Guzmán M.
 */
public class consultaClientes extends javax.swing.JFrame {
int p=0;
    /**
     * Creates new form registroClientes
     */
    public consultaClientes() {
        initComponents();
        id.requestFocus(true);
        cargarClientes("");
    }
    
    void cargarcodigo(){
        
        String[] registro=new String [5];
        String sql="Select nomcli,rnccedula,telefono,sexo,limcre from cliente where idcliente='"+id.getText()+"'";
        
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            
            while (rs.next()){
                registro[0]=rs.getString("nomcli");
                registro[1]=rs.getString("rnccedula");
                registro[2]=rs.getString("telefono");
                registro[3]=rs.getString("sexo");
                registro[4]=rs.getString("limcre");
            }
            nombre.setText(registro[0]);
            rncced.setText(registro[1]);
            tel.setText(registro[2]);
            sexo.setText(registro[3]);
            limcre.setText(registro[4]);
            
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            
        }  
    }
    
    void limpiarCampos(){
        id.setText("");
        nombre.setText("");
        rncced.setText("");
        tel.setText("");
        sexo.setText("");
        limcre.setText("");
        
    }
    void comprobarCampos(){
        if (nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del nombre del cliente esta vacio");
            nombre.requestFocus(true);
            return;
//            p++;
              }
               if (rncced.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del rnc o la decula del cliente esta vacio");
            rncced.requestFocus(true);
            return;
//            p++;
              }
               
               String rnc=rncced.getText();
               if (rnc.length()>=12){
                   JOptionPane.showMessageDialog(null, "debe introducir un dato menor a 12 digitos");
                   rncced.setText("");
                   rncced.requestFocus(true);
                   return;
               }
               
               if (tel.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del telefono del cliente esta vacio");
            tel.requestFocus(true);
            return;
//            p++;
              }
               String telefono=tel.getText();
               if (telefono.length()>=12){
                   JOptionPane.showMessageDialog(null, "debe introducir un dato menor a 12 digitos");
                   tel.setText("");
                   tel.requestFocus(true);
                   return;
               }
               if (sexo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del genero del cliente esta vacio");
            sexo.requestFocus(true);
            return;
//            p++;
              }
               if (limcre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del limite de credito del cliente esta vacio");
            limcre.requestFocus(true);
            return;
//            p++;
              }
       
        p++;
    }
    
    
    void guardarDatos(){
        try {
            String sql;
             sql = "INSERT INTO cliente(nomcli,rnccedula,telefono,sexo,limcre)Values('"+nombre.getText()+"','"+rncced.getText()+"','"+tel.getText()+"','"+sexo.getText()+"','"+limcre.getText()+"')";
             
             PreparedStatement ps=cn.prepareStatement(sql);
             
             int n=ps.executeUpdate();
             
            
        }catch (Exception ex){
           JOptionPane.showInputDialog(null, ex);
            
        }
    }
    
    void cargarClientes(String valor){
        DefaultTableModel modelo= (DefaultTableModel)tablaclientes.getModel();
        modelo.getDataVector().clear();
        
        String[] registros=new String[8];
        String sql="SELECT idcliente,nomcli,rnccedula,telefono,sexo,limcre FROM cliente WHERE nomcli like '%"+valor+"%' ";
        
        
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            
            while (rs.next()){
                registros[0]=rs.getString("idcliente");
                registros[1]=rs.getString("nomcli");
                registros[2]=rs.getString("rnccedula");
                registros[3]=rs.getString("telefono");
                registros[4]=rs.getString("sexo");
                registros[5]=rs.getString("limcre");
                modelo.addRow(registros);
            }
            tablaclientes.setModel(modelo);
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            
        }
    }
    
    void bucarCliente(){
        String[] registro= new String[6];
        String sql2="SELECT nomcli,rnccedula,telefono,sexo,limcre FROM cliente WHERE idcliente='"+id.getText()+"'";
        
        try{
            Statement st2=cn.createStatement();
            ResultSet rs2=st2.executeQuery(sql2);
            
            if(rs2.next()){
                registro[0]=rs2.getString("nomcli");
                registro[1]=rs2.getString("rnccedula");
                registro[2]=rs2.getString("telefono");
                registro[3]=rs2.getString("sexo");
                registro[4]=rs2.getString("limcre");
               nombre.setText(registro[0]);
               rncced.setText(registro[1]);
               tel.setText(registro[2]);
               sexo.setText(registro[3]);
               limcre.setText(registro[4]);
                
            }
            
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    void modificarCliente(){
        
try{
    PreparedStatement ps3= cn.prepareStatement("UPDATE cliente SET nomcli='"+nombre.getText()+"',rnccedula='"+rncced.getText()+"',telefono='"+tel.getText()+"',sexo='"+sexo.getText()+"',limcre='"+limcre.getText()+"' where idcliente='"+id.getText()+"'");
    ps3.executeUpdate();
     JOptionPane.showMessageDialog(null, "cambios guardados");
      cargarClientes("");

}catch (SQLException ex){
Logger.getLogger(consultaClientes.class.getName()).log(Level.SEVERE, null, ex);
}
    }
    
     void eliminarCliente(){
        
try{
    PreparedStatement ps3= cn.prepareStatement("delete from cliente where idcliente='"+id.getText()+"'");
    ps3.executeUpdate();
       JOptionPane.showMessageDialog(null, "cliente eliminado");
        cargarClientes("");
        limpiarCampos();

}catch (SQLException ex){
Logger.getLogger(consultaClientes.class.getName()).log(Level.SEVERE, null, ex);
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
        jLabel5 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rncced = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tel = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        sexo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        limcre = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaclientes = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        buscarnombre = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consulta de clientes");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Cliente"));

        jLabel5.setText("ID");

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

        jLabel6.setText("Nombre");

        jLabel7.setText("RNC/Cedula");

        jLabel8.setText("Telefono");

        jButton2.setText("Seleccionar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel13.setText("Sexo");

        jLabel14.setText("Limite de credito");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sexo)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                    .addComponent(nombre)
                    .addComponent(rncced)
                    .addComponent(tel)
                    .addComponent(limcre))
                .addGap(21, 21, 21))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(rncced, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(limcre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tablaclientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "RNC/Cedula", "Telefono", "sexo", "limite de credito"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaclientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaclientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaclientes);
        if (tablaclientes.getColumnModel().getColumnCount() > 0) {
            tablaclientes.getColumnModel().getColumn(0).setMinWidth(55);
            tablaclientes.getColumnModel().getColumn(0).setMaxWidth(55);
            tablaclientes.getColumnModel().getColumn(2).setMinWidth(88);
            tablaclientes.getColumnModel().getColumn(2).setMaxWidth(88);
            tablaclientes.getColumnModel().getColumn(3).setMinWidth(88);
            tablaclientes.getColumnModel().getColumn(3).setMaxWidth(88);
            tablaclientes.getColumnModel().getColumn(4).setMinWidth(33);
            tablaclientes.getColumnModel().getColumn(4).setMaxWidth(33);
            tablaclientes.getColumnModel().getColumn(5).setMinWidth(88);
            tablaclientes.getColumnModel().getColumn(5).setMaxWidth(88);
        }

        jButton3.setText("Guardar");
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

        jButton5.setText("Eliminar");
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Buscar");
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel1.setText("Buscar");

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

        jButton1.setText("Menu principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(buscarnombre))
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton3)
                        .addGap(18, 18, 18)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(buscarnombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(28, 28, 28)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
 comprobarCampos();
        if (p>0){
        guardarDatos();
        limpiarCampos();
        cargarClientes("");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 // TODO add your handling code here:
         if (id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del codigo del cliente esta vacio");
            id.requestFocus(true);
            return;
        }
        comprobarCampos();
        if (p>0){
        modificarCliente();
      
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        if(!id.getText().equals("")) eliminarCliente();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tablaclientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaclientesMouseClicked
        // TODO add your handling code here:
                // TODO add your handling code here:
        int fila=tablaclientes.getSelectedRow();
        if (fila>=0){
            id.setText(tablaclientes.getValueAt(fila, 0).toString());
            nombre.setText(tablaclientes.getValueAt(fila, 1).toString());
            rncced.setText(tablaclientes.getValueAt(fila, 2).toString());
            tel.setText(tablaclientes.getValueAt(fila, 3).toString());
            sexo.setText(tablaclientes.getValueAt(fila, 4).toString());
            limcre.setText(tablaclientes.getValueAt(fila, 5).toString());
            
        }
    }//GEN-LAST:event_tablaclientesMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (id.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Escriba el codigo del cliente");
            id.requestFocus(true);
            return;
            
        }
        bucarCliente();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void buscarnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarnombreKeyReleased
        // TODO add your handling code here:
        cargarClientes(buscarnombre.getText());
    }//GEN-LAST:event_buscarnombreKeyReleased

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
comprobarCampos();
if (p>0){
    new facturacion().setVisible(true);
        facturacion.idcliente.setText(id.getText());
        facturacion.nombre.setText(nombre.getText());
        facturacion.rncced.setText(rncced.getText());
        facturacion.tel.setText(tel.getText());
        facturacion.sexo.setText(sexo.getText());
        facturacion.limcre.setText(limcre.getText());
        this.dispose();
    
}
    }//GEN-LAST:event_jButton2ActionPerformed

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
        if(id.getText().equals("")){
            return;
        }
        cargarcodigo();
        comprobarCampos();
if (p>0){
    new facturacion().setVisible(true);
        facturacion.idcliente.setText(id.getText());
        facturacion.nombre.setText(nombre.getText());
        facturacion.rncced.setText(rncced.getText());
        facturacion.tel.setText(tel.getText());
        facturacion.sexo.setText(sexo.getText());
        facturacion.limcre.setText(limcre.getText());
        this.dispose();
         } 
    }//GEN-LAST:event_idActionPerformed

    private void buscarnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarnombreActionPerformed

    private void idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_idKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
new menu().setVisible(true);
        this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(consultaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultaClientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consultaClientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscarnombre;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField limcre;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField rncced;
    private javax.swing.JTextField sexo;
    private javax.swing.JTable tablaclientes;
    private javax.swing.JTextField tel;
    // End of variables declaration//GEN-END:variables

    conectar cc = new conectar();
    Connection cn = cc.conexion();
    
}
