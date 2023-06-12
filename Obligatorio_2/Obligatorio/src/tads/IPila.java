package tads;

public interface IPila<T> {

    //post: retorna verdadero cuando la lista no tiene elementos
    public boolean esVacia();

    //pre: recibe un entero
    //post: agrega en el ultimo lugar disponible de la lista
    public void push(T n);
    
    public void pop();
    
    public Nodo verTope();
    
    public boolean existeElemento(T n);
    
    public Nodo obtenerElemento(T n);
    
    public void vaciar();

    public void mostrar();

}