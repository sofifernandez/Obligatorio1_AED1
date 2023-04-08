package sistemaAutogestion;


public interface IObligatorio {
    
    /*
    **************** REGISTRO DE CLIENTES Y PRODUCTOS **************************************
    */
    
    //pre:      post:
    public Retorno crearSistemaDeAutoservicio(int maxUnidadesDePedido);
     //pre:      post:
    public Retorno agregarCliente(String nombre,String ci,int tel);
     //pre:      post:
    public Retorno eliminarCliente(String ci);
     //pre:      post:
    public Retorno agregarProducto(String nombre, String descripcion); 
     //pre:      post:
    public Retorno eliminarProducto(String nombre);
     //pre:      post:
    public Retorno altaStockProducto(String nroProducto, int unidades);
    
     /*
    **************** GESTIÓN DE PEDIDOS **************************************
    */
    
     //pre:      post:
    public Retorno aperturaDePedido(String ciCliente);
     //pre:      post:
    public Retorno agregarProductoAPedido(String ciCliente, int nroProducto, int unidades); 
     //pre:      post:
    public Retorno deshacerPedido(String ciCliente, int cantAccionesDeshacer);
     //pre:      post:
    public Retorno cerrarPedido(String ciCliente); 
     //pre:      post:
    public Retorno procesarPedido(int cantPedidos); 
    
      /*
    **************** LISTADO Y REPORTES **************************************
    */
    
     //pre:      post:
    public Retorno listarClientes();
     //pre:      post:
    public Retorno listarProductos();
     //pre:      post:
    public Retorno listarPedidosAbiertos();
     //pre:      post:
    public Retorno pedidosCerradosDeClientes(int ci);
    //pre:      post:
    public Retorno productosParaEntregar();
    //pre:      post:
    public Retorno reporteDePedidosSolicitadosXCliente();
    
}
