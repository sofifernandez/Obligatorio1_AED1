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
    private String ci;
    //private ListaSimple listaPedidos;
    
    public Cliente (String nombre, String ci){
        this.setNombre(nombre);
        this.setCi(ci);
        //listaPedidos = new ListaSimple();
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
