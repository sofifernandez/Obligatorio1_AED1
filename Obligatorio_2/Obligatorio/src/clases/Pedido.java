
package clases;

import tads.Pila;

public class Pedido implements Comparable<Pedido>{
    
    private static int IDClase = 1;
    private int IDPedido;

    private enum estado {ABIERTO , CERRADO , ProntoParaEntregar} //ES NECESARIO ESTO? O LO VAMOS MOVIENDO DE LISTA EN LISTA?
    private Cliente cli;
    private Pila pilaProductos; //class: ProductoCantidad
    private int unidadesTotales;
    
    public Pedido(){
        IDPedido = this.IDClase++;
        pilaProductos = new Pila();
        unidadesTotales=0;
    }
    

    public int getIDPedido() {
        return IDPedido;
    }

    public void setIDPedido(int IDPedido) {
        this.IDPedido = IDPedido;
    }

    public Cliente getCli() {
        return cli;
    }

    public void setCli(Cliente cli) {
        this.cli = cli;
    }

    public Pila getPilaProductos() {
        return pilaProductos;
    }

    public void setPilaProductos(Pila pilaProductos) {
        this.pilaProductos = pilaProductos;
    }
    
     public void setUnidadesTotales(int unidades) {
        this.unidadesTotales = this.unidadesTotales+unidades;
    }
    
     public int getUnidadesTotales(){
         return unidadesTotales;
    }
    
    @Override
    public boolean equals(Object o) {
        Pedido that = (Pedido) o;
        return getIDPedido() == that.getIDPedido();
    }
    
    @Override
    public int compareTo(Pedido o) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int cantProducto(int IDProd){ //devuelve la cantidad de unidades del producto tal
        throw new UnsupportedOperationException("Not supported yet.");
    }
        
}

 