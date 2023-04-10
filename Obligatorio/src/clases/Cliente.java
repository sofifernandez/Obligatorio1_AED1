/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;



/**
 *
 * @author sofia
 */
public class Cliente {

    private String nombre;
    private String ci;
    //private ListaSimple listaPedidos;

    public Cliente(String nombre, String ci) {
        this.setNombre(nombre);
        this.setCi(ci);
        //listaPedidos = new ListaSimple();
    }

    //public boolean equals(Cliente obj) {
       // if (obj == null) {
          //  return false;
       // }
      //  return this.getCi().equals((obj).getCi());
   // }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cliente that = (Cliente) o;
        System.out.println("*****************************************PRUEBAAAAAAAA");
        System.out.println(getCi());
        return getCi().equals(that.getCi());
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
