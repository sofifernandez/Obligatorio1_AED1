package tads;

public class ListaSimple<T> implements ILista<T> {

    private Nodo inicio;
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
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//        if (verificarCantidad()) {
//            if (esVacia() || inicio.getDato() >= n) {
//                agregarInicio(n);
//            } else {
//
//                Nodo actual = inicio;
//
//                while (actual.getSiguiente() != null && actual.getSiguiente().getDato() < n) {
//                    actual = actual.getSiguiente();
//                }
//
//                if (actual.getSiguiente() == null) {
//                    agregarFinal(n);
//                } else {
//                    Nodo nuevo = new Nodo(n);
//                    Nodo sig = actual.getSiguiente();
//                    actual.setSiguiente(nuevo);
//                    nuevo.setSiguiente(sig);
//                    cantidad++;
//                }
//
//            }
//        }
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
            while(aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(nuevo.getDato())){
                aux=aux.getSiguiente();
            }
            if(aux.getSiguiente()!=null && aux.getSiguiente().getDato().equals(nuevo.getDato())){
                aux.setSiguiente(aux.getSiguiente().getSiguiente());
                cantidad--;
            }
        }
    }
    
    
    
    
    
        /**
     * @return the inicio
     */
    public Nodo getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Nodo inicio) {
        this.inicio = inicio;
    }
}
