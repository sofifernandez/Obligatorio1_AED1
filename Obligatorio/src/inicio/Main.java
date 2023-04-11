package inicio;
import sistemaAutogestion.*;
import clases.*;
import tads.*;

public class Main {

    public static void main(String[] args) {
        /*EJEMPLOS:
        ListaSimple listaEjemplo= new ListaSimple();
        listaEjemplo.agregarInicio(10);
        
        ListaSimple listaClientes= new ListaSimple();
        Cliente cl= new Cliente("Maria", "12345");
        */
        Prueba p= new Prueba();
        Sistema s=new Sistema();
        p.inicializarResultadosPrueba();
        p1_creacionSistema(p,s);
        p2_agregarCliente(p,s);
        p3_eliminarCliente(p,s);
        p4_agregarProducto(p,s);
        p5_eliminarProducto(p,s);
        p.imprimirResultadosPrueba();
    } 
        public static void p1_creacionSistema(Prueba p, Sistema s){
            p.ver(s.crearSistemaDeAutoservicio(4).resultado, Retorno.Resultado.OK, "Sistema inicializado correctamente");
            p.ver(s.crearSistemaDeAutoservicio(3).resultado, Retorno.Resultado.ERROR_1, "Sistema no inicializado");
            p.ver(s.crearSistemaDeAutoservicio(0).resultado, Retorno.Resultado.ERROR_1, "Sistema no inicializado");
            p.ver(s.crearSistemaDeAutoservicio(-1).resultado, Retorno.Resultado.ERROR_1, "Sistema no inicializado");
        }
        
        public static void p2_agregarCliente(Prueba p, Sistema s){
            p.ver(s.agregarCliente("Ana", "12345678", 0).resultado, Retorno.Resultado.OK, "Cliente agregado");
            p.ver(s.agregarCliente("José", "12345678", 0).resultado, Retorno.Resultado.ERROR_1, "Ya existe un cliente ingresado con esa cédula");
            p.ver(s.agregarCliente("José", "87654321", 0).resultado, Retorno.Resultado.OK, "Cliente agregado");
            //s.mostrarLista();
        }
        
        public static void p3_eliminarCliente(Prueba p, Sistema s){
            p.ver(s.eliminarCliente("12345678").resultado, Retorno.Resultado.OK, "Cliente eliminado");
            p.ver(s.eliminarCliente("1").resultado, Retorno.Resultado.ERROR_1, "El cliente no existe");
            //s.mostrarLista();
        }   
        
        public static void p4_agregarProducto(Prueba p, Sistema s){
            p.ver(s.agregarProducto("Producto1", "Descripcion1").resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProducto("Producto2", "Descripcion2").resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProducto("Producto2", "Descripcion2").resultado, Retorno.Resultado.ERROR_1, "El producto ya existe");
            s.mostrarLista();
        }
        
        public static void p5_eliminarProducto(Prueba p, Sistema s){
            p.ver(s.eliminarProducto("Producto1").resultado, Retorno.Resultado.OK, "Producto eliminado");
            p.ver(s.eliminarProducto("Producto3").resultado, Retorno.Resultado.ERROR_1, "El producto no existe");
        }
    
}
