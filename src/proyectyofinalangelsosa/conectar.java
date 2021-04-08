/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectyofinalangelsosa;
import java.sql.*;
import javax.swing.*;

/**
 *
 * @author Jorge L. Guzmán M.
 */
public class conectar {
    
    Connection conect = null;
    
    public Connection conexion(){
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
            conect =DriverManager.getConnection("jdbc:mysql://localhost/proyectofinalangel", "root","");
            
           // JOptionPane.showMessageDialog(null,"Conectado con exito");
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Error" + e);
        }
        return conect;
    }
    
}
