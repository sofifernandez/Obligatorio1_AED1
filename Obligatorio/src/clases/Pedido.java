
package clases;

import tads.Pila;

class Pedido {

    public static int getID() {
        return ID;
    }

    public static void setID(int ID) {
        Pedido.ID = ID;
    }

    public int getIDPedido() {
        return IDPedido;
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
    private static int ID = 1;
    private int IDPedido;
    private enum estado {ABIERTO , CERRADO , ProntoParaEntregar}
    private Cliente cli;
    private Pila pilaProductos;
    
    public Pedido(){
        IDPedido = this.IDPedido++;
        pilaProductos = new Pila();
    }
        
    }

 