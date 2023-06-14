
package clases;
import tads.Nodo;
import tads.ListaSimple;

public class Cliente implements Comparable<Cliente> {

    private String nombre;
    private String ci;
    private ListaSimple listaPedidos;
    private Pedido pedidoAbierto;

     public Cliente(String nombre, String ci) {
        this.setNombre(nombre);
        this.setCi(ci);
        listaPedidos = new ListaSimple(20);
        pedidoAbierto = null;
        
    }
    
    public Cliente(String ci) {
        this.setNombre("");
        this.setCi(ci);
        listaPedidos = new ListaSimple(20);
        pedidoAbierto = null;
    }
    
    
    public ListaSimple getlistaPedidos() {
        return listaPedidos;
    }

    public void setlistaPedidos(ListaSimple listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public Pedido getPedidoAbierto() {
        return pedidoAbierto;
    }

    public void setPedidoAbierto(Pedido pedidoAbierto) {
        this.pedidoAbierto = pedidoAbierto;
    }
    
    public int cantidadProdTotal(int IDProd){
        int cantidadTotal=0;
        if (!listaPedidos.esVacia()) {
            Nodo aux = listaPedidos.getInicio();
            
            while(aux != null){
                Pedido pedido = (Pedido) aux.getDato();
                cantidadTotal=cantidadTotal+pedido.cantProducto(IDProd);
                aux=aux.getSiguiente();
                //if(aux!=null){
                //    pedido = (Pedido) aux.getDato();
               // }
            } 
        } else {
            return 0;
        }
        return cantidadTotal;
    }

    @Override
    public boolean equals(Object o) {
        Cliente that = (Cliente) o;
        return getCi().equals(that.getCi());
    }
    
    @Override
    public String toString() {
        return "Cedula: " + this.getCi() + " Nombre: " + this.getNombre();
    }
    
    @Override
    public int compareTo(Cliente o) {
        //return this.getTel() - o.getTel(); 
        return this.getNombre().compareTo(o.getNombre());
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
