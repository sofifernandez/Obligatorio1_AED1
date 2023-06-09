
package clases;

import tads.*;

public class Pedido implements Comparable<Pedido>{
    
    private static int IDClase = 1;
    private int IDPedido;
    private String estado; //{ABIERTO , CERRADO , ProntoParaEntregar} //ES NECESARIO ESTO? O LO VAMOS MOVIENDO DE LISTA EN LISTA?
    private Cliente cli;
    private Pila pilaProductos; //class: ProductoCantidad
    private int unidadesTotales;
    
    public Pedido(){
        IDPedido = this.IDClase++;
        pilaProductos = new Pila();
        unidadesTotales=0;
    }  
    
    public String getEstado(){
        return estado;
    }
       
    public void setEstado(String estado){
        this.estado = estado;
    }

    public int getIDPedido() {
        return IDPedido;
    }
    
    public Cliente getCliente(){
        return cli;
    }

    public void setCliente(Cliente cli){
        this.cli = cli;
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
        this.unidadesTotales = unidades;
    }
    
     public int getUnidadesTotales(){
         return unidadesTotales;
    }
     
    public void actualizarUnidades(int unidades, String tipo){
        if(tipo=="ELIMINAR"){
            this.setUnidadesTotales(this.getUnidadesTotales()-unidades);
        }
        if(tipo=="AGREGAR"){
            this.setUnidadesTotales(this.getUnidadesTotales()+unidades);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        Pedido that = (Pedido) o;
        return getIDPedido() == that.getIDPedido();
    }
    
    @Override
    public int compareTo(Pedido o) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
    
    public int cantProducto(int IDProd){ //devuelve la cantidad de unidades del producto tal, si no está devuelve 0
        
        int cantidad=0;
        Nodo nodoProd= pilaProductos.obtenerElemento(new ProductoCantidad(IDProd));
        
        if(nodoProd!=null){
            ProductoCantidad prodCant = (ProductoCantidad) nodoProd.getDato();
            cantidad= prodCant.getCantidad();
        }
        return cantidad;
    }
    @Override  
    public String toString(){
        //getPilaProductos().mostrar();
        return "Pedido ID: " + this.getIDPedido() + " Estado: " + this.getEstado() + " Cliente: " + this.getCli().getCi();
    }
}

 