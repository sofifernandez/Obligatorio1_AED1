package sistemaAutogestion;
import tads.*;
import clases.*;

public class Sistema implements IObligatorio {
    
    private ListaSimple listaClientes;
    private ListaSimple listaProductos;
    private ListaSimple listaStockProducto;

    @Override
    public Retorno crearSistemaDeAutoservicio(int maxUnidadesDePedido) {
        Retorno r= new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if(maxUnidadesDePedido<=3){
            r.resultado= Retorno.Resultado.ERROR_1;
        } else{
            listaClientes= new ListaSimple();
            r.resultado= Retorno.Resultado.OK;
        }
        return r;
    }

    @Override
    public Retorno agregarCliente(String nombre, String ci, int tel) {
        Retorno r= new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if(listaClientes.existeDato(new Cliente(nombre, ci))){
            r.resultado= Retorno.Resultado.ERROR_1;
        } else{
            listaClientes.agregarInicio(new Cliente(nombre, ci));
            //System.out.println("PRUEBA");
            r.resultado= Retorno.Resultado.OK;
        }
        return r;
    }

    @Override
    public Retorno eliminarCliente(String ci) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno agregarProducto(String nombre, String descripcion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno eliminarProducto(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno altaStockProducto(String nroProducto, int unidades) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno aperturaDePedido(String ciCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno agregarProductoAPedido(String ciCliente, int nroProducto, int unidades) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno deshacerPedido(String ciCliente, int cantAccionesDeshacer) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno cerrarPedido(String ciCliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Retorno procesarPedido(int cantPedidos) {
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
    public Retorno pedidosCerradosDeClientes(int ci) {
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
