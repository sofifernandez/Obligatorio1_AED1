package tads;

public class ListaSimple<T extends Comparable> implements ILista<T> {

    private Nodo<T> inicio;
    private int cantidad;
    private int cantidadMax;

    public ListaSimple(int cantMax) {
        inicio = null;
        cantidad = 0;
        cantidadMax = cantMax;
    }

    public boolean verificarCantidad() {
        return cantidad < cantidadMax;
    }

    @Override
    public boolean esVacia() {
        return getInicio() == null;
    }

    @Override
    public void agregarInicio(T n) {
        if (verificarCantidad()) {
            Nodo nuevo = new Nodo(n);
            nuevo.setSiguiente(getInicio());
            setInicio(nuevo); //inicio = nuevo;  
            cantidad++;
        } else {
            System.out.println("Lista llena, no se pueden agregar más elementos");
        }
    }

    @Override
    public void agregarFinal(T n) {
        if (esVacia()) {
            agregarInicio(n);
        } else {

            if (verificarCantidad()) {
                Nodo nuevo = new Nodo(n);

                Nodo aux = inicio;

                while (aux.getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }

                aux.setSiguiente(nuevo);
                cantidad++;
            }
        }
    }

    @Override
    public void borrarInicio() {

        if (!esVacia()) {
            inicio = inicio.getSiguiente();
            cantidad--;
        }
    }

    @Override
    public void borrarFin() {
        Nodo aux = inicio;
        if (!esVacia()) {
            while (aux.getSiguiente().getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(null);
            cantidad--;
        }
    }

    @Override
    public void vaciar() {
        inicio = null;
        cantidad = 0;
    }

    @Override
    public void mostrar() {

        Nodo aux = inicio;

        if (!esVacia()) {
            while (aux != null) {
                System.out.println(aux.getDato() + " ");
                aux = aux.getSiguiente();
            }
        } else {
            System.out.println("Esta vacia!");
        }

    }

    @Override
    public boolean existeElemento(T n) {
        boolean existe = false;
        if (!esVacia()) {
            Nodo nuevo = new Nodo(n);
            Nodo aux = inicio;
            while (aux != null) {
                if (aux.getDato().equals(nuevo.getDato())) {
                    return true;
                } else {
                    aux = aux.getSiguiente();
                }
            }
        }
        return existe;
    }


    @Override
    public void agregarOrd(T n) {
        if (this.verificarCantidad()) {
            Nodo<T> nuevo = new Nodo(n);
            if (this.esVacia()) {
                agregarInicio(n);
            } else {
                if (getInicio().getDato().compareTo(n) > 0) {
                    agregarInicio(n);
                } else {
                    
                        Nodo<T> actual = getInicio();

                        while (actual.getSiguiente() != null && actual.getSiguiente().getDato().compareTo(n) < 0) {
                            actual = actual.getSiguiente();
                        }

                        nuevo.setSiguiente(actual.getSiguiente());
                        actual.setSiguiente(nuevo);
                        cantidad++;
                    }
                }
            }
        else{
            System.out.println("La lista esta llena");
        }

    }


    @Override
    public int cantidadElementos() {
        return cantidad;
    }

    @Override
    public Nodo obtenerElemento(T n) {
        if (!esVacia()) {
            Nodo aux = inicio;
            Nodo nuevo = new Nodo(n);
            while(aux != null && !aux.getDato().equals(nuevo.getDato())){
                aux=aux.getSiguiente();
            }
            if(aux!=null && aux.getDato().equals(nuevo.getDato())){
                return aux;
            }
        }
        return null;
    }

    @Override
    public void eliminarElemento(T n) {
        if (!esVacia()) {
            Nodo aux = inicio;
            Nodo nuevo = new Nodo(n);
            
            if(inicio.getDato().equals(nuevo.getDato())){
                borrarInicio();
            }
            else{
                while(aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(nuevo.getDato())){
                    aux=aux.getSiguiente();
                }
                if(aux.getSiguiente()!=null && aux.getSiguiente().getDato().equals(nuevo.getDato())){
                    aux.setSiguiente(aux.getSiguiente().getSiguiente());
                    cantidad--;
                }
            }
        }
    }
    
    
    
    
    
        /**
     * @return the inicio
     */
    public Nodo<T> getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }
}
