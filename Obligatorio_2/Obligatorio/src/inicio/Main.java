package inicio;
import sistemaAutogestion.*;
import clases.*;
import tads.*;

public class Main {

    public static void main(String[] args) {
        Prueba p= new Prueba();
        Sistema s=new Sistema();
        p.inicializarResultadosPrueba();
        p1_creacionSistema(p,s);
        p2_agregarCliente(p,s);
        p3_eliminarCliente(p,s);
        p4_agregarProducto(p,s);
        p5_eliminarProducto(p,s);
        p6_altaStockProducto(p,s);
        p7_aperturaDePedido(p,s);
        p8_agregarProductoAPedido(p,s);
        p9_deshacerPedido(p, s);
        p.imprimirResultadosPrueba();
    } 
        public static void p1_creacionSistema(Prueba p, Sistema s){
            p.ver(s.crearSistemaDeAutoservicio(4).resultado, Retorno.Resultado.OK, "Sistema inicializado correctamente");
            p.ver(s.crearSistemaDeAutoservicio(3).resultado, Retorno.Resultado.ERROR_1, "Sistema no inicializado");
            p.ver(s.crearSistemaDeAutoservicio(0).resultado, Retorno.Resultado.ERROR_1, "Sistema no inicializado");
            p.ver(s.crearSistemaDeAutoservicio(-1).resultado, Retorno.Resultado.ERROR_1, "Sistema no inicializado");
        }
        
        public static void p2_agregarCliente(Prueba p, Sistema s){
            p.ver(s.agregarCliente("C", "222", 0).resultado, Retorno.Resultado.OK, "Cliente agregado");
            p.ver(s.agregarCliente("D", "444", 0).resultado, Retorno.Resultado.OK, "Cliente agregado");
            p.ver(s.agregarCliente("B", "111", 0).resultado, Retorno.Resultado.OK, "Cliente agregado");
            p.ver(s.agregarCliente("Z", "111", 0).resultado, Retorno.Resultado.ERROR_1, "Ya existe un cliente ingresado con esa cédula");
            p.ver(s.agregarCliente("E", "555", 0).resultado, Retorno.Resultado.OK, "Cliente agregado");
            p.ver(s.agregarCliente("A", "333", 0).resultado, Retorno.Resultado.OK, "Cliente agregado");
            System.out.println("PRUEBA AGREGAR CLIENTE");
            s.listaClientes.mostrar();
        }
        
        public static void p3_eliminarCliente(Prueba p, Sistema s){
            p.ver(s.eliminarCliente("444").resultado, Retorno.Resultado.OK, "Cliente eliminado");
            p.ver(s.eliminarCliente("333").resultado, Retorno.Resultado.ERROR_2, "Cliente tiene pedido abierto o cerrado");
            p.ver(s.eliminarCliente("1").resultado, Retorno.Resultado.ERROR_1, "El cliente no existe");
            p.ver(s.eliminarCliente("555").resultado, Retorno.Resultado.OK, "Cliente eliminado");
            System.out.println("PRUEBA ELIMINAR CLIENTE");
            s.listaClientes.mostrar();
        }   
        
        public static void p4_agregarProducto(Prueba p, Sistema s){
            p.ver(s.agregarProducto("Producto1", "Descripcion1").resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProducto("Producto2", "Descripcion2").resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProducto("Producto3", "Descripcion3").resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProducto("Producto2", "Descripcion2").resultado, Retorno.Resultado.ERROR_1, "El producto ya existe");
            System.out.println("PRUEBA AGREGAR PRODUCTO");
            s.listaProductos.mostrar();
        }
             
        public static void p5_eliminarProducto(Prueba p, Sistema s){
            p.ver(s.eliminarProducto("Producto2").resultado, Retorno.Resultado.OK, "Producto eliminado");
            p.ver(s.eliminarProducto("Producto5").resultado, Retorno.Resultado.ERROR_1, "El producto no existe");
            p.ver(s.eliminarProducto("Producto1").resultado, Retorno.Resultado.ERROR_2, "El producto no se puede eliminar porque está en un pedido abierto o cerrado");
            System.out.println("PRUEBA ELIMINAR PRODUCTO");
            s.listaProductos.mostrar();
        }

        //1: Producto1
        //2: Producto2 (ELIMINADO)
        //3: Producto3
        public static void p6_altaStockProducto(Prueba p, Sistema s){
            p.ver(s.altaStockProducto(1,-1).resultado, Retorno.Resultado.ERROR_2, "Número de unidades incorrecto");
            p.ver(s.altaStockProducto(2,4).resultado, Retorno.Resultado.ERROR_1, "El producto no existe");
            p.ver(s.altaStockProducto(1,4).resultado, Retorno.Resultado.OK, "Stock actualizado");
            p.ver(s.altaStockProducto(3,4).resultado, Retorno.Resultado.OK, "Stock actualizado");
            p.ver(s.altaStockProducto(3,2).resultado, Retorno.Resultado.OK, "Stock actualizado");
            s.listaProductos.mostrar();
        }
        
        public static void p7_aperturaDePedido(Prueba p, Sistema s){
            p.ver(s.aperturaDePedido("1").resultado, Retorno.Resultado.ERROR_1, "El cliente no existe");
            p.ver(s.aperturaDePedido("333").resultado, Retorno.Resultado.ERROR_2, "El cliente ya tiene un pedido abierto");
            p.ver(s.aperturaDePedido("111").resultado, Retorno.Resultado.OK, "Se abrió un nuevo pedido");
            p.ver(s.aperturaDePedido("111").resultado, Retorno.Resultado.ERROR_2, "El cliente ya tiene un pedido abierto");
        }
        
        public static void p8_agregarProductoAPedido(Prueba p,Sistema s){
            p.ver(s.agregarProductoAPedido("1", 1,2).resultado, Retorno.Resultado.ERROR_1, "El cliente no existe");
            p.ver(s.agregarProductoAPedido("111", 6,2).resultado, Retorno.Resultado.ERROR_2, "El producto no existe");
            p.ver(s.agregarProductoAPedido("111", 1,-2).resultado, Retorno.Resultado.ERROR_4, "Unidades incorrectas");
            //Cliente 333 ya tiene pedido abierto pero vacío
            p.ver(s.agregarProductoAPedido("333", 1,3).resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProductoAPedido("333", 3,4).resultado, Retorno.Resultado.ERROR_3, "Solo se puede agregar 3 unidades por producto");
           //Cliente 111 no tiene pedido abierto, se abre en la función
            p.ver(s.agregarProductoAPedido("111", 3,1).resultado, Retorno.Resultado.OK, "Nuevo pedido y producto agregado correctamente");
            p.ver(s.agregarProductoAPedido("111", 1,2).resultado, Retorno.Resultado.ERROR_5, "El stock es insuficiente");
        }
        
        public static void p9_deshacerPedido(Prueba p, Sistema s){
            p.ver(s.deshacerPedido("1", 1).resultado, Retorno.Resultado.ERROR_1, "El cliente no existe");
            p.ver(s.deshacerPedido("222", 1).resultado, Retorno.Resultado.ERROR_1, "El cliente no tiene pedido abierto");
            p.ver(s.deshacerPedido("111", -1).resultado, Retorno.Resultado.ERROR_2, "Numero de acciones incorrecto");
        }
}
