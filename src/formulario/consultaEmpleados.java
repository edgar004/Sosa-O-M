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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import proyectyofinalangelsosa.conectar;

/**
 *
 * @author Jorge L. Guzmán M.
 */
public class consultaEmpleados extends javax.swing.JFrame {
int p=0;
    /**
     * Creates new form registroClientes
     */
    public consultaEmpleados() {
        initComponents();
        id.requestFocus(true);
        cargarClientes("");
    }
    
    void cargarcodigo(){
        
        String[] registro=new String [11];
        String sql="Select nombre,cedula,sueldo,fecnac,sexo from empleados where idempleado='"+id.getText()+"'";
        
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            
            while (rs.next()){
                registro[0]=rs.getString("nomcli");
                registro[1]=rs.getString("rnccedula");
                registro[2]=rs.getString("telefono");
                registro[3]=rs.getString("sexo");
            }
            nombre.setText(registro[0]);
            ced.setText(registro[1]);
            sueldo.setText(registro[2]);
            sexo.setText(registro[3]);
            
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            
        }  
    }
    
    void limpiarCampos(){
        id.setText("");
        nombre.setText("");
        ced.setText("");
        sueldo.setText("");
        sexo.setText("");
        
        fecnac.setCalendar(null);
        jTextField1.setText("");
        
    }
    void comprobarCampos(){
        if (nombre.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del nombre del empleado esta vacio");
            nombre.requestFocus(true);
            return;
//            p++;
              }
               if (ced.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo de la decula del empleado esta vacio");
            ced.requestFocus(true);
            return;
//            p++;
              }
               
               String rnc=ced.getText();
               if (rnc.length()>=12){
                   JOptionPane.showMessageDialog(null, "debe introducir un dato menor a 12 dijitos");
                   ced.setText("");
                   ced.requestFocus(true);
                   return;
               }
               
               if (sueldo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del sueldo del empleado esta vacio");
            sueldo.requestFocus(true);
            return;
              }

               if (sexo.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del genero del empleado esta vacio");
            sexo.requestFocus(true);
            return;
              }
               
               if (fecnac.getDate()==null){
                   JOptionPane.showMessageDialog(null, "debe escribir la fecha de nacimiento");
                   return;
                   
               }
               

       
        p++;
    }
    

    void calendario(){
        try{
            String dia=Integer.toString(fecnac.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes=Integer.toString(fecnac.getCalendar().get(Calendar.MONTH)+1);
        String anio=Integer.toString(fecnac.getCalendar().get(Calendar.YEAR));
        fechanac=anio+"/"+mes+"/"+dia;
            
        jTextField1.setText(fechanac);
    }catch (Exception e){
        
    }
    }
    
    void guardarDatos(){
        try {
            
        
            String sql;
             sql = "INSERT INTO empleados(nombre,cedula,sueldo,sexo,fecnac)Values('"+nombre.getText()+"','"+ced.getText()+"','"+sueldo.getText()+"','"+sexo.getText()+"','"+fechanac+"')";
             
             PreparedStatement ps=cn.prepareStatement(sql);
             
             int n=ps.executeUpdate();
             
            
        }catch (Exception ex){
           JOptionPane.showInputDialog(null, ex);
            
        }
    }
    
    void cargarClientes(String valor){
        DefaultTableModel modelo= (DefaultTableModel)tablaempleados.getModel();
        modelo.getDataVector().clear();
        
        String[] registros=new String[6];
        String sql="SELECT idempleado,nombre,cedula,sueldo,sexo,fecnac FROM empleados WHERE concat(nombre) like '%"+valor+"%' ";
        
        
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            
            while (rs.next()){
                registros[0]=rs.getString("idempleado");
                registros[1]=rs.getString("nombre");
                registros[2]=rs.getString("cedula");
                registros[3]=rs.getString("sueldo");
                registros[4]=rs.getString("sexo");
                registros[5]=rs.getString("fecnac");
                modelo.addRow(registros);
            }
            tablaempleados.setModel(modelo);
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
            
        }
    }
    
    void bucarEmpleado(){
        String[] registro= new String[9];
        String sql2="SELECT nombre,cedula,sueldo,fecnac,sexo FROM empleado WHERE idempleado='"+id.getText()+"'";
        
        try{
            Statement st2=cn.createStatement();
            ResultSet rs2=st2.executeQuery(sql2);
            
            while (rs2.next()){
                registro[0]=rs2.getString("nombre");
                registro[1]=rs2.getString("cedula");
                registro[2]=rs2.getString("sueldo");
                registro[3]=rs2.getString("fecnac");
                registro[4]=rs2.getString("sexo");
                nombre.setText(registro[0]);
               ced.setText(registro[1]);
               sueldo.setText(registro[2]);
               
               sexo.setText(registro[4]);
               
                
            }
            
            
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex);
        }
    }
    
    
    void modificarEmpleado(){
        
try{
    
    String dia=Integer.toString(fecnac.getCalendar().get(Calendar.DAY_OF_MONTH));
        String mes=Integer.toString(fecnac.getCalendar().get(Calendar.MONTH)+1);
        String anio=Integer.toString(fecnac.getCalendar().get(Calendar.YEAR));
        String fechanac=anio+"/"+mes+"/"+dia;
            
    PreparedStatement ps3= cn.prepareStatement("UPDATE empleados SET nombre='"+nombre.getText()+"',cedula='"+ced.getText()+"',sueldo='"+sueldo.getText()+"',sexo='"+sexo.getText()+"',fecnac='"+fechanac+"' where idempleado='"+id.getText()+"'");
    ps3.executeUpdate();

}catch (SQLException ex){
Logger.getLogger(consultaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
}
    }
    
     void eliminarEmpleado  (){
        
try{
    PreparedStatement ps3= cn.prepareStatement("delete from empleados where idempleado='"+id.getText()+"'");
    ps3.executeUpdate();

}catch (SQLException ex){
Logger.getLogger(consultaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
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

        jSeparator1 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        id = new javax.swing.JTextField();
        nombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        ced = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        sueldo = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        sexo = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        fecnac = new com.toedter.calendar.JDateChooser();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaempleados = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        buscarnombre = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Consulta empleados");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos Cliente"));

        jLabel5.setText("ID");

        id.setEnabled(false);
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

        nombre.setEnabled(false);

        jLabel6.setText("Nombre");

        ced.setEnabled(false);

        jLabel7.setText("Cedula");

        sueldo.setEnabled(false);

        jLabel8.setText("Sueldo");

        jLabel13.setText("Sexo");

        sexo.setEnabled(false);

        jLabel14.setText("Fecha de nacimiento");

        fecnac.setEnabled(false);
        fecnac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fecnacMouseClicked(evt);
            }
        });
        fecnac.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fecnacPropertyChange(evt);
            }
        });

        jTextField1.setEnabled(false);

        jButton1.setText("Menu principal");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(jLabel6)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sexo)
                            .addComponent(nombre)
                            .addComponent(ced)
                            .addComponent(sueldo)
                            .addComponent(id)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(fecnac, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(ced, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(sexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel14)
                    .addComponent(jTextField1)
                    .addComponent(fecnac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        fecnac.getDateEditor().addPropertyChangeListener(new java.beans.PropertyChangeListener(){
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendario();
            }
        }

        tablaempleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nombre", "Cedula", "Sueldo", "Sexo", "Fecha de nacimiento"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablaempleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaempleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaempleados);
        if (tablaempleados.getColumnModel().getColumnCount() > 0) {
            tablaempleados.getColumnModel().getColumn(0).setMinWidth(55);
            tablaempleados.getColumnModel().getColumn(0).setMaxWidth(55);
            tablaempleados.getColumnModel().getColumn(2).setMinWidth(88);
            tablaempleados.getColumnModel().getColumn(2).setMaxWidth(88);
            tablaempleados.getColumnModel().getColumn(3).setMinWidth(88);
            tablaempleados.getColumnModel().getColumn(3).setMaxWidth(88);
            tablaempleados.getColumnModel().getColumn(4).setMinWidth(45);
            tablaempleados.getColumnModel().getColumn(4).setMaxWidth(45);
            tablaempleados.getColumnModel().getColumn(5).setMinWidth(111);
            tablaempleados.getColumnModel().getColumn(5).setMaxWidth(111);
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

        jButton7.setText("Crear o modificar empleado");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Crear usuario");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 319, Short.MAX_VALUE)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(buscarnombre))
                            .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
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
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
 comprobarCampos();
        if (p>0){
       // tomarfecha();
        guardarDatos();
        limpiarCampos();
        cargarClientes("");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
 // TODO add your handling code here:
         if (id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "el campo del codigo del empleado esta vacio");
            id.requestFocus(true);
//            n++;
            return;
        }
        comprobarCampos();
        modificarEmpleado();
        JOptionPane.showMessageDialog(null, "cambios guardados");
        cargarClientes("");
        limpiarCampos();
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         eliminarEmpleado();
        JOptionPane.showMessageDialog(null, "Empleado eliminado");
        cargarClientes("");
        limpiarCampos();
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tablaempleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaempleadosMouseClicked
        // TODO add your handling code here:
                // TODO add your handling code here:
        int fila=tablaempleados.getSelectedRow();
        if (fila>=0){
            id.setText(tablaempleados.getValueAt(fila, 0).toString());
            nombre.setText(tablaempleados.getValueAt(fila, 1).toString());
            ced.setText(tablaempleados.getValueAt(fila, 2).toString());
            sueldo.setText(tablaempleados.getValueAt(fila, 3).toString());
            sexo.setText(tablaempleados.getValueAt(fila, 4).toString());
            jTextField1.setText(tablaempleados.getValueAt(fila, 5).toString());
            
        }
    }//GEN-LAST:event_tablaempleadosMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (id.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Escriba el codigo del empleado");
            id.requestFocus(true);
            return;
            
        }
        bucarEmpleado();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void buscarnombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buscarnombreKeyReleased
        // TODO add your handling code here:
        cargarClientes(buscarnombre.getText());
    }//GEN-LAST:event_buscarnombreKeyReleased

    private void buscarnombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarnombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_buscarnombreActionPerformed

    private void idKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_idKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_idKeyReleased

    private void idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idActionPerformed
        // TODO add your handling code here:
        cargarcodigo();
    }//GEN-LAST:event_idActionPerformed

    private void fecnacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fecnacMouseClicked
        // TODO add your handling code here:
        jTextField1.setText("");
    }//GEN-LAST:event_fecnacMouseClicked

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
 new registroEmpleados().setVisible(true);
 
 this.dispose();  // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        
        if (id.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Seleccione un empleado");
            return;
        }
        
        new crearUsuario().setVisible(true);  
        crearUsuario.id.setText(id.getText());
        crearUsuario.nombre.setText(nombre.getText());
        crearUsuario.id.enable(false);
        crearUsuario.nombre.enable(false);
        crearUsuario.usuario.requestFocus(true);
        this.dispose();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
new menu().setVisible(true);
        this.dispose();         // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void fecnacPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fecnacPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_fecnacPropertyChange

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
            java.util.logging.Logger.getLogger(consultaEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(consultaEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(consultaEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(consultaEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new consultaEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField buscarnombre;
    private javax.swing.JTextField ced;
    private com.toedter.calendar.JDateChooser fecnac;
    private javax.swing.JTextField id;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField nombre;
    private javax.swing.JTextField sexo;
    private javax.swing.JTextField sueldo;
    private javax.swing.JTable tablaempleados;
    // End of variables declaration//GEN-END:variables

    conectar cc = new conectar();
    Connection cn = cc.conexion();
    
    String fecha="";
   // String fechanac;
    String fechanac;
    
    
}
