
package tads;

public class Cola<T extends Comparable> implements ICola<T> {

    private Nodo<T> primero;
    private Nodo<T> ultimo;
    private int cantidad;
    
    public Cola() {
        this.primero = null;
        this.ultimo = null;
        cantidad=0;
    }
    
    @Override
    public boolean estaVacia() {
        return getPrimero() == null;
    }

    @Override
    public void encolar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (estaVacia()) {
            setPrimero(nuevoNodo);
        } else {
            getUltimo().setSiguiente(nuevoNodo);
        }
        setUltimo(nuevoNodo);
        cantidad++;
    }

    @Override
    public T desencolar() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        T dato = getPrimero().getDato();
        setPrimero(getPrimero().getSiguiente());
        if (getPrimero() == null) {
            setUltimo(null);
        }
        cantidad--;
        return dato;
    }

    @Override
    public T obtenerPrimero() {
        if (estaVacia()) {
            throw new IllegalStateException("La cola está vacía");
        }
        return getPrimero().getDato();
    }

    @Override
    public void mostrarCola() {
        
        Nodo<T> aux = getPrimero();
        
        System.out.println("Muestro Cola:");
        
        while(aux !=  null){
            System.out.print(aux.getDato() + " - ");
            aux = aux.getSiguiente();
        }  
    }

    /**
     * @return the primero
     */
    public Nodo<T> getPrimero() {
        return primero;
    }

    /**
     * @param primero the primero to set
     */
    public void setPrimero(Nodo<T> primero) {
        this.primero = primero;
    }

    /**
     * @return the ultimo
     */
    public Nodo<T> getUltimo() {
        return ultimo;
    }

    /**
     * @param ultimo the ultimo to set
     */
    public void setUltimo(Nodo<T> ultimo) {
        this.ultimo = ultimo;
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
    
}
