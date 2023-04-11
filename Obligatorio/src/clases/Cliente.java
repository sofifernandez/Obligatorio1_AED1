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
    private ListaSimple listaPedidos;

    public Cliente(String nombre, String ci) {
        this.setNombre(nombre);
        this.setCi(ci);
        listaPedidos = new ListaSimple(20);
    }
    
    public Cliente(String ci) {
        this.setNombre("");
        this.setCi(ci);
        listaPedidos = new ListaSimple(20);
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

    /**
     * @return the listaPedidos
     */
    public ListaSimple getListaPedidos() {
        return listaPedidos;
    }

    /**
     * @param listaPedidos the listaPedidos to set
     */
    public void setListaPedidos(ListaSimple listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

}
