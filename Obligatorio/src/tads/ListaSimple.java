package tads;


public class ListaSimple<T> implements ILista<T> {

    private Nodo inicio;

    public ListaSimple() {
        inicio = null;
    }

    @Override
    public boolean esVacia() {
        return getInicio() == null;
    }

    @Override
    public void agregarInicio(T n) {
        Nodo nuevo = new Nodo(n);
        nuevo.setSiguiente(getInicio());
        setInicio(nuevo); //inicio = nuevo;  
    }

    @Override
    public void agregarFinal(T n) {
        Nodo nuevo = new Nodo(n);
        Nodo aux = inicio;
        if(!esVacia()){
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(nuevo);
        }
    }

    @Override
    public void borrarInicio() {

        if (!esVacia()) {
            inicio = inicio.getSiguiente();
        }
    }

    @Override
    public void borrarFin() {
        Nodo aux = inicio;
        if(!esVacia()){
            while (aux.getSiguiente().getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(null);
        }
    }

    @Override
    public void vaciar() {
        inicio = null;
    }

    @Override
    public void mostrar() {

        Nodo aux = inicio;

        if (!esVacia()) {
            while (aux != null) {
                System.out.print(aux.getDato() + " ");
                aux = aux.getSiguiente();
            }
        }
        else{
            System.out.println("Esta vacia!");
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

    @Override
    public boolean existeDato(T dato) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
