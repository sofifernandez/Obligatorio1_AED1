/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;
import tads.ListaSimple;

/**
 *
 * @author sofia
 */
public class Cliente {
    private String nombre;
    private String rut;
    private ListaSimple listaPedidos;
    
    public Cliente (String nombre, String rut){
        this.setNombre(nombre);
        this.setRut(rut);
        listaPedidos = new ListaSimple();
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
     * @return the rut
     */
    public String getRut() {
        return rut;
    }

    /**
     * @param rut the rut to set
     */
    public void setRut(String rut) {
        this.rut = rut;
    }
    
}
