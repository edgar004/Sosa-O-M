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
public class listaArticulos {
    private String cantidad;
    private String descripcion;
    private String precio;
    private String itbis;
    private String subtotal;
    
    public listaArticulos(String cantidad,String descripcion,String precio,String itbis,String subtotal){
        
        this.cantidad=cantidad;
        this.descripcion=descripcion;
        this.precio=precio;
        this.itbis=itbis;
        this.subtotal=subtotal;
        
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDesart(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getItbis() {
        return itbis;
    }

    public void setItbis(String itbis) {
        this.itbis = itbis;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
    
    
    
    
}
