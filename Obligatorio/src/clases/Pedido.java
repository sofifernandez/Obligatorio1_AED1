
package clases;

import tads.Pila;

class Pedido {
    private static int ID = 1;
    private int IDPedido;
    private enum estado {ABIERTO , CERRADO , ProntoParaEntregar}
    private Cliente cli;
    Pila pilaProductos = new Pila();
    
    public Pedido(){
        IDPedido = this.IDPedido++;
    }
        
    }

 