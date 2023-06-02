
package tads;

public class Cola<T> implements ICola<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    
    public Cola() {
        this.primero = null;
        this.ultimo = null;
    }
    
    @Override
    public boolean estaVacia() {
        return primero == null;
    }

    @Override
    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (estaVacia()) {
            primero = nuevoNodo;
        } else {
            ultimo.setSiguiente(nuevoNodo);
        }
        ultimo = nuevoNodo;
    }

    @Override
    public T desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        T dato = primero.getDato();
        primero = primero.getSiguiente();
        if (primero == null) {
            ultimo = null;
        }
        return dato;
    }

    @Override
    public T obtenerPrimero() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return primero.getDato();
    }

    @Override
    public void mostrarCola() {
        
        Nodo<T> aux = primero;
        
        System.out.println("Muestro Cola:");
        
        while(aux !=  null){
            System.out.print(aux.getDato() + " - ");
            aux = aux.getSiguiente();
        }  
    }
    
}
