/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

/**
 *
 * @author sofia
 */
public class Producto {

    private String nombre;
    private String descripcion;
    private int ID;
    private static int IDClase;

    public Producto(String nombre, String descripcion) {
        this.setNombre(nombre);
        this.setDescripcion(descripcion);
        this.setID(IDClase++);

    }

    public Producto(String nombre) {
        this.setNombre(nombre);
    }

    @Override
    public String toString() {
        return "Producto: " + this.getNombre() + ", ID: " + this.getID();
    }
    
    @Override
    public boolean equals(Object o) {
        Producto that = (Producto) o;
        return getNombre().equals(that.getNombre());
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
     * @return the IDClase
     */
    public static int getIDClase() {
        return IDClase;
    }

}
