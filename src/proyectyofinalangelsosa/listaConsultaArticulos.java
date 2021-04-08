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
public class listaConsultaArticulos {
    private String id;
    private String descripcion;
    private String cantidad;
    private String precom;
    private String preven;
    private String reorden;
    private String tipo;
    
    public listaConsultaArticulos(String id,String descripcion,String cantidad,String precom,String preven,String reorden,String tipo){
        
        this.id=id;
        this.descripcion=descripcion;
        this.cantidad=cantidad;
        this.precom=precom;
        this.preven=preven;
        this.reorden=reorden;
        this.tipo=tipo;
        
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecom() {
        return precom;
    }

    public void setPrecom(String precom) {
        this.precom = precom;
    }

    public String getPreven() {
        return preven;
    }

    public void setPreven(String preven) {
        this.preven = preven;
    }

    public String getReorden() {
        return reorden;
    }

    public void setReorden(String reorden) {
        this.reorden = reorden;
    }
public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    }