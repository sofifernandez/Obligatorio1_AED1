
package clases;

public class ProductoCantidad {
   // private String nombre;
    private int ID;
    private int cantidad;
     
     public ProductoCantidad(int ID) {
       // this.setNombre(nombre);
        this.setID(ID);
        this.setCantidad(0);
    }
     
    @Override
    public String toString() {
        return "ProductoID: " + this.getID() + "-->"+ " Cantidad: " + this.getCantidad();
    }

    /**
     * @return the nombre
     */
     
     /*
    public String getNombre() {
        return nombre;
    }
     */
    /**
     * @param nombre the nombre to set
     */
     /*
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    */

    /**
     * @return the ID
     */
    public int getID() {
        return ID;
    }

    /**
     * @param ID the ID to set
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * @return the cantidad
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    @Override
    public boolean equals(Object o) {
        ProductoCantidad that = (ProductoCantidad) o;
        return getID()==that.getID();
    }
    
}
