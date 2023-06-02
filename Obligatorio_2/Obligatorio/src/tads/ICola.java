package tads;

public interface ICola<T> {

    public boolean estaVacia();

    public void encolar(T dato);

    public T desencolar();

    public T obtenerPrimero();
    
    public void mostrarCola();

}