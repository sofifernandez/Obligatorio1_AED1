/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author sofia
 */
public class StockProdutco {
    private int nroProducto;
    private String descripcion;
    
    public StockProdutco (int nroProducto, String descripcion){
        this.setNroProducto(nroProducto);
        this.setDescripcion(descripcion);
        //listaPedidos = new ListaSimple();
    }

    /**
     * @return the nroProducto
     */
    public int getNroProducto() {
        return nroProducto;
    }

    /**
     * @param nroProducto the nroProducto to set
     */
    public void setNroProducto(int nroProducto) {
        this.nroProducto = nroProducto;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
