package tads;


public interface ILista<T> {

    //post: retorna verdadero cuando la lista no tiene elementos
    public boolean esVacia();

    //pre: recibe un entero
    //post: agrega el elemento en el primer lugar de la lista
    public void agregarInicio(T n);

    //pre: recibe un entero
    //post: agrega en el ultimo lugar disponible de la lista
    public void agregarFinal(T n);

    public void borrarInicio();

    public void borrarFin();

    public void vaciar();

    public void mostrar();
    
     public void agregarOrd(T n);
    
    public int cantidadElementos();
    
    public boolean existeElemento(T n);
    
    public Nodo obtenerElemento(T n);
    
    public void eliminarElemento(T n);

}
