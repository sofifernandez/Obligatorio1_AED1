
package clases;

import tads.ListaSimple;



/**
 *
 * @author sofia
 */
public class Cliente {

    private String nombre;
    private String ci;
    private ListaSimple listaPedidosCerrados;
    private Pedido pedidoAbierto;

    public ListaSimple getListaPedidosCerrados() {
        return listaPedidosCerrados;
    }

    public void setListaPedidosCerrados(ListaSimple listaPedidosCerrados) {
        this.listaPedidosCerrados = listaPedidosCerrados;
    }

    public Pedido getPedidoAbierto() {
        return pedidoAbierto;
    }

    public void setPedidoAbierto(Pedido pedidoAbierto) {
        this.pedidoAbierto = pedidoAbierto;
    }

    public Cliente(String nombre, String ci) {
        this.setNombre(nombre);
        this.setCi(ci);
        listaPedidosCerrados = new ListaSimple(20);
        pedidoAbierto = new Pedido();
    }
    
    public Cliente(String ci) {
        this.setNombre("");
        this.setCi(ci);
        listaPedidosCerrados = new ListaSimple(20);
        pedidoAbierto = new Pedido();
    }

    //public boolean equals(Cliente obj) {
       // if (obj == null) {
          //  return false;
       // }
      //  return this.getCi().equals((obj).getCi());
   // }

    @Override
    public boolean equals(Object o) {
        Cliente that = (Cliente) o;
        return getCi().equals(that.getCi());
    }
    
    @Override
    public String toString() {
        return "Cedula:" + this.getCi();
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the ci
     */
    public String getCi() {
        return ci;
    }

    /**
     * @param ci the ci to set
     */
    public void setCi(String ci) {
        this.ci = ci;
    }

}
