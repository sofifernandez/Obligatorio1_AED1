package sistemaAutogestion;

import tads.*;
import clases.*; 


public class Sistema implements IObligatorio {

    public ListaSimple listaClientes; 
    public ListaSimple listaProductos; // class: Producto
    public ListaSimple listaPedidosParaEntregar; 
    public Cola colaPedidosCerrados;
    public int maxUnidadesDePedido;
   
    //FUNCIONES AUXILIARES------------------------------------------------------
    private Cliente getCliente(String ci){
        if (listaClientes.existeElemento(new Cliente(ci))) {
            Nodo nodoCliente = listaClientes.obtenerElemento(new Cliente(ci));
            Cliente cliente = (Cliente) nodoCliente.getDato();
            return cliente;
        }
        return null;
    }
    
    private Producto getProductoPorID (int ID){
        if (listaProductos.existeElemento(new Producto(ID))) {
            Nodo nodoProducto = listaProductos.obtenerElemento(new Producto(ID));
            Producto producto = (Producto) nodoProducto.getDato();
            return producto;
        }
        return null;
    }
    
    
    //--------------------------------------------------------------------------
    //Registros de Clientes y Productos
    @Override
    public Retorno crearSistemaDeAutoservicio(int maxUnidadesDePedido) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (maxUnidadesDePedido <= 3) {
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {
            listaClientes = new ListaSimple(10);
            listaProductos = new ListaSimple(10);
            listaPedidosParaEntregar = new ListaSimple(10);
            colaPedidosCerrados= new Cola(); 
            this.maxUnidadesDePedido=maxUnidadesDePedido; //HABRÁ QUE HACER UN SET ACÁ?
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
        Cliente cliente= getCliente(ci);
        if (cliente!=null) {
            if (cliente.getListaPedidosCerrados().esVacia() && cliente.getPedidoAbierto() == null) //Si no no tiene pedidos cerrados ni abiertos se puede eliminar
            {
                listaClientes.eliminarElemento(cliente);
                r.resultado = Retorno.Resultado.OK;
            } else {
                r.resultado = Retorno.Resultado.ERROR_2; //No se puede eliminar
            }
        } else {
            r.resultado = Retorno.Resultado.ERROR_1; //El cliente no existe
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
                r.resultado = Retorno.Resultado.ERROR_2; //Está en pedidos abiertos o cerrados
            } else {
                listaProductos.eliminarElemento(new Producto(nombre));
                r.resultado = Retorno.Resultado.OK;
            }
        } else {
            r.resultado = Retorno.Resultado.ERROR_1; //No existe el nombre del producto
        }
        return r;
    }

    @Override
    public Retorno altaStockProducto(int nroProducto, int unidades) {

        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (unidades > 0) {
            if (listaProductos.existeElemento(nroProducto)) {
                Nodo nuevo = listaProductos.obtenerElemento(nroProducto);
                Producto nuevoProd = (Producto) nuevo.getDato();
                nuevoProd.setStock(unidades + nuevoProd.getStock());
            } else {
                r.resultado = Retorno.Resultado.ERROR_1; //No existe el producto
            }
        } else {
            r.resultado = Retorno.Resultado.ERROR_2; //Las unidades ingresadas son menores o iguales a 0
        }
        return r;
    }

    //--------------------------------------------------------------------------
    // Gestión de pedidos
    @Override
    public Retorno aperturaDePedido(String ciCliente) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Cliente cliente= getCliente(ciCliente);
        if(cliente==null)
        {
            r.resultado = Retorno.Resultado.ERROR_1; //El cliente no existe
            return r;
        }
        
        if (cliente.getPedidoAbierto()!=null) 
        {
            r.resultado = Retorno.Resultado.ERROR_2; //El cliente ya tiene un pedido abierto
        } else 
        {
                cliente.setPedidoAbierto(new Pedido());
        }
        
        
        return r;
    }

    @Override
    public Retorno agregarProductoAPedido(String ciCliente, int nroProducto, int unidades) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Cliente cliente= getCliente(ciCliente);
        Producto producto=getProductoPorID(nroProducto);
        if(cliente==null)
        {
            r.resultado = Retorno.Resultado.ERROR_1; //El cliente no existe
            return r;
        }
        if(producto==null)
        {
            r.resultado = Retorno.Resultado.ERROR_2; //El producto no existe
            return r;
        }
        
        if(unidades<=0)
        {
            r.resultado = Retorno.Resultado.ERROR_4; //Unidades <=0
            return r;
        }
        
        if(cliente.getPedidoAbierto().getUnidadesTotales() + unidades > this.maxUnidadesDePedido)
        {
            r.resultado = Retorno.Resultado.ERROR_3; //Se supere el máximo de unidades totales permitidas para el pedido.
            return r;
        }
        
        if(unidades > producto.getStock())
        {
            r.resultado = Retorno.Resultado.ERROR_5; //r.resultado = Retorno.Resultado.ERROR_3; //Se supere el máximo de unidades totales permitidas para el pedido.
            return r;
        }
        //AHORA SÍ, AGREGAR
        //1) crear un nuevo ProductoCantidad
        //2) hacerle push a la PilaProductos del pedidoAbierto del cliente
        //3) al pedido abierto sumarle las unidades totales
        ProductoCantidad prodCant=new ProductoCantidad(producto.getNombre(), producto.getID(), unidades);
        cliente.getPedidoAbierto().getPilaProductos().push(prodCant);
        cliente.getPedidoAbierto().setUnidadesTotales(unidades);
        return r;
    }

    @Override
    public Retorno deshacerPedido(String ciCliente, int cantAccionesDeshacer) 
    {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Cliente cliente= getCliente(ciCliente);
        if(cliente==null)
        {
            r.resultado = Retorno.Resultado.ERROR_1; //El cliente no existe
            return r;
        }
        
        if(cantAccionesDeshacer<=0)
        {
            r.resultado = Retorno.Resultado.ERROR_2; //Las acciones son 0 o menores
            return r;
        }
        
        if(cantAccionesDeshacer>cliente.getPedidoAbierto().getPilaProductos().length())
        {
        //--------------
        }
        
        return r;
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
