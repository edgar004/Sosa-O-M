/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectyofinalangelsosa;

/**
 *
 * @author Jorge L. Guzmán M.
 */
public class listaEstadoCuentas {
    private String fecha;
    private String numfactura;
    private String balance;
    
    
    public listaEstadoCuentas(String fecha,String numfactura,String balance){
        
        this.fecha=fecha;
        this.numfactura=numfactura;
        this.balance=balance;
        
    
    
}
  public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public String getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(String numfactura) {
        this.numfactura = numfactura;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

  


    
}
