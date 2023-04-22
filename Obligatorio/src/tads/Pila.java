/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tads;

/**
 *
 * @author lucas
 */
public class Pila<T> implements IPila<T> {
    
    private Nodo tope;
    private int cantidad;
    
    public void Pila(){
        this.tope = null;
        cantidad = 0;
    }
    
    @Override
    public boolean esVacia(){
        return tope == null;
    }
    
    @Override
    public void push(T n) {
        Nodo nuevo = new Nodo(n);
        if(esVacia()){
            tope = nuevo;
        }
        else{
            nuevo.setSiguiente(tope);
            tope = nuevo;
        }
        cantidad++; 
        
    }

    @Override
    public void pop(T n) {
        if(!esVacia()){
            tope = tope.getSiguiente();
            cantidad--;
        }
    }

    @Override
    public Nodo verTope() {
        if(!esVacia()){
            return tope;
        }
        else{
          return null;
        } 
    }

    @Override
    public boolean buscarElemento(T n) {
        Nodo aux = tope;
        boolean existe = false;
        
        while(existe != true && aux!=null){
            if(n == aux.getDato()){
                existe = true;
            }
            else{
                aux.getSiguiente();
            }
        }
        return existe;
    }

    @Override
    public void vaciar() {
        tope = null;
        cantidad = 0;
    }

    @Override
    public void mostrar() {
        Nodo aux = tope;
        while(aux != null){
            System.out.println(aux.getDato() + "-");
            aux = aux.getSiguiente();
        }
    }
}
        

