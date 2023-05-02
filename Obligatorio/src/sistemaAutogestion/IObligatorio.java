package sistemaAutogestion;


public interface IObligatorio {
    
    /*
    **************** REGISTRO DE CLIENTES Y PRODUCTOS **************************************
    */
    
    //pre: maxUnidadesDePedido es cualquier numero entero
    //post: Si maxUnidadesDePedido > 3, crea listas vacias de 
    //clientes, productos, pedidosParaEntregar y colaPedidosCerrados
    // Si maxUnidadesDePedido <= 3, error 1
    public Retorno crearSistemaDeAutoservicio(int maxUnidadesDePedido);
    
    //pre: se pasan 2 String cualquiera y un entero cualquiera    
    //post: Si ci no pertence a ningun cliente, se agrega nuevo cliente a listaClientes
    // Si ci ya pertenece a un cliente, error 1
    public Retorno agregarCliente(String nombre,String ci,int tel);
    
    
    //pre: se pasa un string cualquiera
    //post: Si ci ya pertenece a un cliente y ademas no tiene pedidos, se elimina al cliente de listaClietes
    //Si ci ya pertenece a un cliente pero tiene pedidos, error 2
    //Si ci no pertence a ningun cliente, error 1
    public Retorno eliminarCliente(String ci);
    
    //pre: se pasan dos string cualquiera
    //post: si nombre no pertence a ningun producto, se agrega el producto a listaProductos 
    // si nombre pertence a un producto, error 1
    public Retorno agregarProducto(String nombre, String descripcion); 
    
    //pre: se pasa un string cualquiera 
    //post: si nombre pertenece a un producto, y este no esta incluido en ningun pedido, se elimina el pedido de la listaPedidos
    // si nombre pertenece a un producto, y este esta incluido en algun pedido, 
    public Retorno eliminarProducto(String nombre);
    
    //pre: nroProducto entero mayor a cero, unidades un entero cualquiera
    //post: si nroProducto pertenece a un producto y unidades > 0, se incrementa el stock del pedido
    // si nroProducto no pertenece a ningun producto, error 1
    //si nroProducto pertenece a un producto y unidades <= 0, error 2
    public Retorno altaStockProducto(String nroProducto, int unidades);
    
     /*
    **************** GESTIÓN DE PEDIDOS **************************************
    */
    
    //pre: ciCliente un string cualqueira    
    //post: si ciCliente pertenece a un cliente y su pedidoAbierto = null, se crea un pedido abierto para el cliente pedidoAbierto = Pedido()
    // si ciCliente no pertenece a ningun Cliente, error 1
    // si ciCliente pertenece a un cliente y su pedidoAbierto = Pedido(), error 2
    public Retorno aperturaDePedido(String ciCliente);
    
    //pre: ciCliente un string cualquiera, nroProducto un enetero > 0, unidades un entero > 0   
    //post: si ciCliente pertenece a un Cliente, nroProducto pertenece a un Producto y unidades <= maxUnidadesDePedido, se agrega producto al Pedido
    //si ciCliente no pertenece a ningun Cliente, error 1
    // si nroProducto no pertenece a ningun producto, error 2
    // si unidades > maxUnidadesDePedido, error 3
    public Retorno agregarProductoAPedido(String ciCliente, int nroProducto, int unidades); 
    
    //pre: ciCliente un string cualquiera, cantAccionesDeshacer entero cualquiera    
    //post: si ciCliente pertenece a un Cliente, cantAccionesDeshacer > 0, se eliminan los ultimos (cantAccionesDeshacer) productos agregados con sus unidades
    //si ciCliente no pertenece a ningun Cliente, error 1
    //si cantAccionesDeshacer <= 0, error 2
    //si cantAcciones > a cantidad de productos agregados al pedido
    public Retorno deshacerPedido(String ciCliente, int cantAccionesDeshacer);
   
    //pre: ciCliente un string cualquiera     
    //post: si ciCliente pertenece a un Cliente, el estado del pedido pasa a cerrado y se agrega a colaPedidosCerrados
    //si ciCliente no pertenece a ningun Cliente, error 1
    public Retorno cerrarPedido(String ciCliente); 
    
    //pre: cantPedidos un entero <= cantidad de Pedidos en colaPedidosCerrados 
    //post: si cantPedidos > 0, se procesan los primeros (cantPedidos) de la colaPedidosCerrados
    // si cantPedidos <= 0, error 1
    public Retorno procesarPedido(int cantPedidos); 
    
      /*
    **************** LISTADO Y REPORTES **************************************
    */
    
    //pre: listaClientes != null
    //post: Se muestran todos los clientes ordenados alfabeticamente
    public Retorno listarClientes();
    
    //pre: listaProductos != null
    //post: Se muestran todos los productos en orden que fueron registrados
    public Retorno listarProductos();
    
    //Para hcer esta me parece que seria bueno tener una lista de pedidos abiertos en sistema, asi solamente seria mostrar toda la lista aca
    // y cuando el pedido pase a cerrado lo volamos de esta lista y lo pasamos a la colaPedidosCerrados
    //pre: listaPedidosAbiertos != null
    //post: Se muestran todos los pedidos abiertos
    public Retorno listarPedidosAbiertos();
    
    //pre: ci string cualquiera
    //post: si ci pertenece a un Cliente, se mustran su listaPedidosCerrados
    public Retorno pedidosCerradosDeClientes(int ci);
    
    
    //pre: listaPedidosParaEntregar != null     
    //post: se muestran todos los pedidos listos para entregar 
    public Retorno productosParaEntregar();
    
    //pre: listaClientes != null, listaProductos != null, 
    //post: se muestra por cada cliente, cuantos productos de cada tipo ha solicitado
    public Retorno reporteDePedidosSolicitadosXCliente();
    
}
