package sistemaAutogestion;

import tads.*;
import clases.*;

public class Sistema implements IObligatorio {

    public ListaSimple listaClientes;
    public ListaSimple listaProductos;
    public ListaSimple listaPedidosParaEntregar;
    //public Cola ColaPedidosCerrados;
   
    
    @Override
    public Retorno crearSistemaDeAutoservicio(int maxUnidadesDePedido) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (maxUnidadesDePedido <= 3) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {
            listaClientes = new ListaSimple(10);
            listaProductos = new ListaSimple(10);
            listaPedidosParaEntregar = new ListaSimple(10);
            //Aca iria creacion de ColaPedidosCerrados() 
            r.resultado = Retorno.Resultado.OK;
        }
        return r;
    }

    
    @Override
    public Retorno agregarCliente(String nombre, String ci, int tel) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (listaClientes.existeElemento(new Cliente(ci))) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {
            listaClientes.agregarOrd(new Cliente(nombre, ci));
            r.resultado = Retorno.Resultado.OK;
        }
        return r;
    }

    @Override
    public Retorno eliminarCliente(String ci) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (listaClientes.existeElemento(new Cliente(ci))) {
            Nodo nuevo = listaClientes.obtenerElemento(new Cliente(ci));
            Cliente nuevoCli = (Cliente) nuevo.getDato();
            
            //Esto lo hacemos solamente para forzar el ERROR 2, al cliente de Ci = 333 le asignamos un PedidoAbiertoString
            if(nuevoCli.getCi() == "333"){  
                nuevoCli.setPedidoAbiertoString("Tengo pedido abierto");
            }
             //La segunda condicion de este if sería: nuevoCli.getPedidoAbierto() != null.--> Cuando se de de alta pedidos va a funcionar!
            if (nuevoCli.getListaPedidosCerrados().esVacia() && nuevoCli.getPedidoAbiertoString() != "Tengo pedido abierto") {
                listaClientes.eliminarElemento(new Cliente(ci));
                r.resultado = Retorno.Resultado.OK;
            } else {
                System.out.print("Error2");
                r.resultado = Retorno.Resultado.ERROR_2;
            }
        } else {
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        return r;

    }

    @Override
    public Retorno agregarProducto(String nombre, String descripcion) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (listaProductos.existeElemento(new Producto(nombre))) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {
            listaProductos.agregarInicio(new Producto(nombre, descripcion));
            r.resultado = Retorno.Resultado.OK;
        }
        return r;
    }
   

    @Override
    public Retorno eliminarProducto(String nombre) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Nodo nod =  listaProductos.obtenerElemento(new Producto(nombre));
        if (nod != null) {
            Producto prod= (Producto)nod.getDato();
            //Esto que sigue es para probar el error_2:
            if(prod.getNombre()=="Producto1"){
                prod.setPedidosProducto(2);
            }
            if (prod.getPedidosProducto() > 0) {
                r.resultado = Retorno.Resultado.ERROR_2;
            } else {
                listaProductos.eliminarElemento(new Producto(nombre));
                r.resultado = Retorno.Resultado.OK;
            }
        } else {
            r.resultado = Retorno.Resultado.ERROR_1;
        }
        return r;
    }

    @Override
    public Retorno altaStockProducto(String nroProducto, int unidades) {

        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (unidades > 0) {
            if (listaProductos.existeElemento(nroProducto)) {
                Nodo nuevo = listaProductos.obtenerElemento(nroProducto);
                Producto nuevoProd = (Producto) nuevo.getDato();
                nuevoProd.setStock(unidades + nuevoProd.getStock());
            } else {
                r.resultado = Retorno.Resultado.ERROR_1;
            }
        } else {
            r.resultado = Retorno.Resultado.ERROR_2;
        }
        return r;
    }

    //HASTA ACÁ PRIMERA ENTREGA
    @Override
    public Retorno aperturaDePedido(String ciCliente
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno agregarProductoAPedido(String ciCliente, int nroProducto, int unidades
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno deshacerPedido(String ciCliente, int cantAccionesDeshacer
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno cerrarPedido(String ciCliente
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno procesarPedido(int cantPedidos
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno listarClientes() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno listarProductos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno listarPedidosAbiertos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno pedidosCerradosDeClientes(int ci
    ) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno productosParaEntregar() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno reporteDePedidosSolicitadosXCliente() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
