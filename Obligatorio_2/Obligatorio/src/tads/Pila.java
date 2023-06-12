
package tads;

public class Pila<T> implements IPila<T> {
    
    private Nodo tope;
    private int cantidad;
    
    public void Pila(){
        this.setTope(null);
        setCantidad(0);
    }
    
    @Override
    public boolean esVacia(){
        return getTope() == null;
    }
    
    @Override
    public void push(T n) {
        Nodo nuevo = new Nodo(n);
        if(esVacia()){
            setTope(nuevo);
        }
        else{
            nuevo.setSiguiente(getTope());
            setTope(nuevo);
        }
        setCantidad(getCantidad() + 1); 
        
    }

    @Override
    public void pop() {
        if(!esVacia()){
            setTope(getTope().getSiguiente());
            setCantidad(getCantidad() - 1);
        }
    }

    @Override
    public Nodo verTope() {
        if(!esVacia()){
            return getTope();
        }
        else{
          return null;
        } 
    }

    @Override
    public boolean existeElemento(T n) {
        Nodo aux = getTope();
        boolean existe = false;
        
        while(existe != true && aux!=null){
            if(n.equals(aux.getDato())){
                existe = true;
            }
            else{
                aux.getSiguiente();
            }
        }
        return existe;
    }
    
    public Nodo obtenerElemento(T n) {
        if (!esVacia()) {
            Nodo aux = getTope();
            //Nodo nuevo = new Nodo(n);
            while(aux != null && !aux.getDato().equals(n)){
                aux=aux.getSiguiente();
            }
            if(aux!=null && aux.getDato().equals(n)){
                return aux;
            }
        }
        return null;
    }

    @Override
    public void vaciar() {
        setTope(null);
        setCantidad(0);
    }

    @Override
    public void mostrar() {
        Nodo aux = getTope();
        while(aux != null){
            System.out.println(aux.getDato() + "-");
            aux = aux.getSiguiente();
        }
    }

    /**
     * @return the tope
     */
    public Nodo getTope() {
        return tope;
    }

    /**
     * @param tope the tope to set
     */
    public void setTope(Nodo tope) {
        this.tope = tope;
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
        

