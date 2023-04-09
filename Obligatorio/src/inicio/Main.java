package inicio;
import tads.*;
import clases.*;
import sistemaAutogestion.*;

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
        p.imprimirResultadosPrueba();
    } 
        public static void p1_creacionSistema(Prueba p, Sistema s){
            p.ver(Retorno.Resultado.OK, Retorno.Resultado.ERROR_1, "error");
            p.ver(s.crearSistemaDeAutoservicio(0).resultado, Retorno.Resultado.OK, "Es correcto"));
        }
        
    }
}
