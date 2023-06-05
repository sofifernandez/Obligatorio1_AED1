
package clases;
import tads.Nodo;
import tads.ListaSimple;

public class Cliente implements Comparable<Cliente> {

    private String nombre;
    private String ci;
    private ListaSimple listaPedidosCerrados;
    private Pedido pedidoAbierto;
    //Este atributo es para forzar el ERROR2 de eliminarCliente
    //private String pedidoAbiertoString;

     public Cliente(String nombre, String ci) {
        this.setNombre(nombre);
        this.setCi(ci);
        listaPedidosCerrados = new ListaSimple(20);
        pedidoAbierto = null;
        
    }
    
    public Cliente(String ci) {
        this.setNombre("");
        this.setCi(ci);
        listaPedidosCerrados = null;
        pedidoAbierto = null;
    }
    
    
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

    public int unidadesTotalesProducto(int IDProducto){
         Nodo auxPedidos = listaPedidosCerrados.getInicio();
         int unidadesTot=0;
         while(auxPedidos!=null){ //recorrer la lista de pedidosCerrados 
             Pedido pedido = (Pedido) auxPedidos.getDato();
             unidadesTot=unidadesTot+pedido.cantProducto(IDProducto); //sumarle las unidades de ese producto, si no está devuelve un 0
             auxPedidos=auxPedidos.getSiguiente();
         }
         return unidadesTot;
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
