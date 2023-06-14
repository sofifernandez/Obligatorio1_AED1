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
        p10_cerrarPedido(p,s);
        p11_procesarPedido(p,s);
        p.imprimirResultadosPrueba();
        
        //Prueba para que el Cliente 333 tenga un pedido procesado y otro cerrado con un mismo producto, para se sumen en la matriz.
        s.aperturaDePedido("333");
        s.agregarProductoAPedido("333",3,1);
        s.cerrarPedido("333");
        
        listados(s);
       
       
    } 
        public static void p1_creacionSistema(Prueba p, Sistema s){
            p.ver(s.crearSistemaDeAutoservicio(8).resultado, Retorno.Resultado.OK, "Sistema inicializado correctamente");
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
            p.ver(s.agregarCliente("F", "666", 0).resultado, Retorno.Resultado.OK, "Cliente agregado");
            System.out.println("PRUEBA AGREGAR CLIENTE");
            s.listaClientes.mostrar();
        }
        
        public static void p3_eliminarCliente(Prueba p, Sistema s){
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
            p.ver(s.agregarProducto("Producto4", "Descripcion4").resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
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
            p.ver(s.altaStockProducto(1,7).resultado, Retorno.Resultado.OK, "Stock actualizado");
            p.ver(s.altaStockProducto(3,4).resultado, Retorno.Resultado.OK, "Stock actualizado");
            p.ver(s.altaStockProducto(3,2).resultado, Retorno.Resultado.OK, "Stock actualizado");
            p.ver(s.altaStockProducto(4,6).resultado, Retorno.Resultado.OK, "Stock actualizado");
            s.listaProductos.mostrar();
        }
        
        public static void p7_aperturaDePedido(Prueba p, Sistema s){
            p.ver(s.aperturaDePedido("1").resultado, Retorno.Resultado.ERROR_1, "El cliente no existe");
            p.ver(s.aperturaDePedido("333").resultado, Retorno.Resultado.ERROR_2, "El cliente ya tiene un pedido abierto");
            p.ver(s.aperturaDePedido("666").resultado, Retorno.Resultado.OK, "Se abrió un nuevo pedido");
            p.ver(s.aperturaDePedido("111").resultado, Retorno.Resultado.OK, "Se abrió un nuevo pedido");
            p.ver(s.aperturaDePedido("222").resultado, Retorno.Resultado.OK, "Se abrió un nuevo pedido");
            p.ver(s.aperturaDePedido("111").resultado, Retorno.Resultado.ERROR_2, "El cliente ya tiene un pedido abierto");
        }
        
        public static void p8_agregarProductoAPedido(Prueba p,Sistema s){
            
            p.ver(s.agregarProductoAPedido("1", 1,2).resultado, Retorno.Resultado.ERROR_1, "El cliente no existe");
            p.ver(s.agregarProductoAPedido("111", 6,2).resultado, Retorno.Resultado.ERROR_2, "El producto no existe");
            p.ver(s.agregarProductoAPedido("111", 1,-2).resultado, Retorno.Resultado.ERROR_4, "Unidades incorrectas");
            //Cliente 333 
            p.ver(s.agregarProductoAPedido("333", 1,2).resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProductoAPedido("333", 3,3).resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProductoAPedido("333", 3,7).resultado, Retorno.Resultado.ERROR_3, "Supera la cantidad maxima de unidades por pedido");
            p.ver(s.agregarProductoAPedido("333", 4,1).resultado, Retorno.Resultado.OK, "Producto agregado correctamente");            
            //Cliente 111  
            p.ver(s.agregarProductoAPedido("111", 3,1).resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProductoAPedido("111", 1,2).resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProductoAPedido("111", 3,1).resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProductoAPedido("111", 3,4).resultado, Retorno.Resultado.ERROR_5, "El stock es insuficiente");
            //Cliente 666   
            p.ver(s.agregarProductoAPedido("666", 3,1).resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProductoAPedido("666", 4,1).resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            //Cliente 222 
            p.ver(s.agregarProductoAPedido("222", 1,2).resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
            p.ver(s.agregarProductoAPedido("222", 4,3).resultado, Retorno.Resultado.OK, "Producto agregado correctamente");
        }
        
        public static void p9_deshacerPedido(Prueba p, Sistema s){
            p.ver(s.deshacerPedido("1", 1).resultado, Retorno.Resultado.ERROR_1, "El cliente no existe");
            p.ver(s.deshacerPedido("444", 1).resultado, Retorno.Resultado.ERROR_1, "El cliente no tiene pedido abierto");
            p.ver(s.deshacerPedido("111", -1).resultado, Retorno.Resultado.ERROR_2, "Numero de acciones incorrecto");
            p.ver(s.deshacerPedido("111", 5).resultado, Retorno.Resultado.ERROR_3, "La cantidad de acciones solicitadas supere la cantidad de productos ");
            p.ver(s.deshacerPedido("111", 2).resultado, Retorno.Resultado.OK, "Pedido restaurado correctamente");
            p.ver(s.deshacerPedido("333", 1).resultado, Retorno.Resultado.OK, "Pedido restaurado correctamente"); 
        }
        
        public static void p10_cerrarPedido(Prueba p, Sistema s){
            
            p.ver(s.cerrarPedido("1").resultado,Retorno.Resultado.ERROR_1, "El cliente no existe");
            p.ver(s.cerrarPedido("111").resultado,Retorno.Resultado.ERROR_2,  "El cliente no tiene pedido abierto");
            p.ver(s.cerrarPedido("333").resultado,Retorno.Resultado.OK,  "Pedido cerrado correctamente"); 
            p.ver(s.cerrarPedido("222").resultado,Retorno.Resultado.OK,  "Pedido cerrado correctamente");  
        }
        
        public static void p11_procesarPedido(Prueba p, Sistema s){
            s.listaProductos.mostrar();
            p.ver(s.procesarPedido(0).resultado,Retorno.Resultado.ERROR_1, "Cantidad de pedidos incorrecta");
            p.ver(s.procesarPedido(5).resultado,Retorno.Resultado.ERROR_2, "Cantidad de pedidos a procesar mayor a cantidad de pedidos cerrados");
            p.ver(s.procesarPedido(1).resultado,Retorno.Resultado.OK, "Pedidos procesados correctamente");      
            s.listaProductos.mostrar();
        }
        
        public static void listados(Sistema s){

        System.out.println("************************************************************");
        System.out.println("************************************************************");
        System.out.println("************************************************************");
        System.out.println("**********************LISTADOS********************************");
        System.out.println(" \n");
        System.out.println(" \n*--------CLIENTES---------------*");
        System.out.println(" \n");
        s.listarClientes();
        System.out.println(" \n");
        System.out.println(" \n*--------PRODUCTOS---------------*");
        System.out.println(" \n");
        s.listarProductos();
        System.out.println(" \n");
        System.out.println(" \n*--------PEDIDOS ABIERTOS---------------*");
        System.out.println(" \n");
        s.listarPedidosAbiertos();
        System.out.println(" \n");
        System.out.println(" \n*--------PEDIDOS CERRADOS DE CLIENTE 111---------------*");
        System.out.println(" \n");
        s.pedidosCerradosDeClientes("111");
        
        System.out.println(" \n");
        System.out.println(" \n*--------PEDIDOS CERRADOS DE CLIENTE 222---------------*");
        System.out.println(" \n");
        s.pedidosCerradosDeClientes("222");
        
        System.out.println(" \n");
        System.out.println(" \n*--------PEDIDOS CERRADOS DE CLIENTE 333---------------*");
        System.out.println(" \n");
        s.pedidosCerradosDeClientes("333");
        
        System.out.println(" \n");
        System.out.println(" \n*--------PEDIDOS CERRADOS DE CLIENTE 444---------------*");
        System.out.println(" \n");
        s.pedidosCerradosDeClientes("444");
        
        System.out.println(" \n");
        System.out.println(" \n*--------PEDIDOS CERRADOS DE CLIENTE 666---------------*");
        System.out.println(" \n");
        s.pedidosCerradosDeClientes("666");
        
        System.out.println(" \n");
        System.out.println(" \n*--------PRODUCTOS PARA ENTREGAR---------------*");
        System.out.println(" \n");
        s.productosParaEntregar();
        System.out.println(" \n");
        System.out.println(" \n*-------------------MATRIZ--------------------------*");
        System.out.println(" \n");
        s.reporteDePedidosSolicitadosXCliente();
        }
        

    
}
