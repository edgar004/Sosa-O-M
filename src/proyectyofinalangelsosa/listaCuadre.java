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
public class listaCuadre {
    private String numfactura;
    private String efectivocobrado;

    
    public listaCuadre(String numfactura,String efectivocobrado){
        
        this.numfactura=numfactura;
        this.efectivocobrado=efectivocobrado;
    
    
}

    public String getNumfactura() {
        return numfactura;
    }

    public void setNumfactura(String numfactura) {
        this.numfactura = numfactura;
    }

    public String getEfectivocobrado() {
        return efectivocobrado;
    }

    public void setEfectivocobrado(String efectivocobrado) {
        this.efectivocobrado = efectivocobrado;
    }
}
