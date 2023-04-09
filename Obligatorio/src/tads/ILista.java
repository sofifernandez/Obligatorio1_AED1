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
    
    public boolean existeDato(T dato);

}
