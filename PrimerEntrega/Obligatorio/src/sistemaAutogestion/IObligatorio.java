package sistemaAutogestion;


public interface IObligatorio {
    
    /*
    **************** REGISTRO DE CLIENTES Y PRODUCTOS **************************************
    */
    
    /*
    PRE: maxUnidadesDePedido es cualquier numero entero que representa la unidades
    máximas permitidas por pedido.
    POST: Si maxUnidadesDePedido > 3, crea listas vacias de 
    clientes, productos, pedidosParaEntregar y colaPedidosCerrados.
    Si maxUnidadesDePedido <= 3 se devuelve ERROR_1
    */
    public Retorno crearSistemaDeAutoservicio(int maxUnidadesDePedido);
    
    /*
    PRE: se pasan 2 String cualquiera que representan el nombre y la cédula de un cliente nuevo y un entero cualquiera que representa el teléfono.    
    POST: Si ci no pertence a ningun cliente, se agrega nuevo cliente a listaClientes.
    Si ya hay un cliente ingresado con ese ci se devuelve ERROR_1 y no se agrega el cliente creado.
    */
    public Retorno agregarCliente(String nombre,String ci,int tel);
    
    
    /*
    PRE: Se pasa un string cualquiera que representa la cédula del cliente que se quiere eliminar.
    POST: Si ci pertenece a un cliente y ademas no tiene pedidos (cerrados o abiertos), se elimina al cliente de listaClietes.
    Si ci no pertence a ningun cliente se devuelve ERROR_1 y no se elimina ningún cliente.
    Si ci pertenece a un cliente pero tiene pedidos (cerrados o abiertos) se devuelve ERROR_2 y el cliente no es eliminado.
    */
    public Retorno eliminarCliente(String ci);
    
    /*
    PRE: se pasan dos string cualquiera que representan el nombre y la descripción de un producto nuevo que se quiere agregar al Sistema.
    POST: Si nombre no pertence a ningun producto, se agrega el producto a listaProductos. 
    Si nombre pertence a un producto se devuelve ERROR_1 y no se agrega a la lista.
    */
    public Retorno agregarProducto(String nombre, String descripcion); 
    
    /*
    PRE: se pasa un string cualquiera que representa el nombre del producto que se quiere eliminar. 
    POST: si nombre pertenece a un producto, y este no esta incluido en ningun pedido abierto o cerrado, 
    se elimina el producto de la listaProductos del Sistema.
    Si nombre no pertenece a ningún producto, se devuelve ERROR_1 y no se elimina nada de la lista.
    Si nombre pertenece a un producto pero este se encuentra en algún pedido aboerto o cerrado, se devuelve ERROR_2 y no se elimina el producto de la lista.
    */
    public Retorno eliminarProducto(String nombre);
    
    /*
    PRE: nroProducto entero(? dice String pero asumimos que es un error) que representa a un producto en la lista del Sistema y
    las unidades como un entero que representan las unidades a sumarle al stock del producto.
    POST: si nroProducto pertenece a un producto y unidades > 0, se incrementa el stock del pedido.
    Si nroProducto no pertenece a ningun producto se devuelve ERROR_1.
    Si nroProducto pertenece a un producto y unidades <= 0 se devuelve ERROR_2.
    */
    public Retorno altaStockProducto(String nroProducto, int unidades);
    
     /*
    **************** GESTIÓN DE PEDIDOS **************************************
    */
    
    /*
    PRE: Se ingresa un string que representa la cédula de un cliente    
    POST: si ciCliente pertenece a un cliente y su pedidoAbierto == null, se crea un pedido abierto para el cliente.
    Si ciCliente no pertenece a ningun cliente se devulve ERROR_1.
    Si ciCliente pertenece a un cliente pero su pedidoAbierto != null, se devuelve ERROR_2.
    */
    public Retorno aperturaDePedido(String ciCliente);
    
    /*
    PRE: ciCliente es un string cualquiera que representa una cédula de cliente, nroProducto es un entero > 0 que
    representa a un producto y unidades es un entero > 0 que representa las unidades de dicho producto que se quieren agregar al 
    pedido del cliente.
    POST: si ciCliente pertenece a la lista de clientes, nroProducto pertenece a la lista de productos y unidades <= maxUnidadesDePedido,
    se agrega producto al pedido abierto del cliente.
    Si ciCliente no pertenece a ningun cliente de la lista de clientes, se devuelve ERROR_1 y no se agrega el producto a ningún pedido.
    Si nroProducto no pertenece a ningun producto de la lista de productos, se devuelve ERROR_2 y no se agrega el producto.
    Si unidades > maxUnidadesDePedido se devuelve ERROR_3 y no se agrega el producto.
    */
    public Retorno agregarProductoAPedido(String ciCliente, int nroProducto, int unidades); 
    
    /*
    PRE: ciCliente es un string cualquiera que representa una cédula y cantAccionesDeshacer entero cualquiera que representa la cantidad de 
    productos a ser eliminados del pedido abierto.    
    POST: si ciCliente pertenece a un cliente de la lista de clientes y cantAccionesDeshacer > 0, 
    se eliminan los últimos (cantAccionesDeshacer) productos agregados con sus unidades.
    Si ciCliente no pertenece a ningun cliente de la lista, se devuelve ERROR_1.
    Si cantAccionesDeshacer <= 0, se devuelve ERROR_2.
    Si cantAcciones > a cantidad de productos agregados al pedido se devuelve ERROR_3.
    */
    public Retorno deshacerPedido(String ciCliente, int cantAccionesDeshacer);
   
    /*
    PRE: ciCliente es un string cualquiera que representa una cédula de cliente.     
    POST: si ciCliente pertenece a un cliente de la lista, y este tiene un pedido abierto,
    1) el estado del pedido pasa a cerrado
    2) pedidoAbierto del cliente pasa a ser null
    3) se pasa el pedido a la listaPedidosCerrados del Cliente
    4) se agrega a colaPedidosCerrados del Sistema.
    Si ciCliente no pertenece a ningun cliente de la lista, se devuelve ERROR_1.
    
    (Faltaría implementar el error de que no tenga pedido abierto?? En la letra no está.)
    */
    public Retorno cerrarPedido(String ciCliente); 
    
    /*
    PRE: cantPedidos es un entero que debería ser menor o igual a la cantidad de Pedidos 
    en colaPedidosCerrados. 
    POST: Si cantPedidos > 0, se procesan los primeros (cantPedidos) de la colaPedidosCerrados
    Si cantPedidos <= 0 se devuelve ERROR_1.
    
    (Faltaría implementar el error de que se pase un número mayor a la cantidade de pedidos en la cola?)
    */
    public Retorno procesarPedido(int cantPedidos); 
    
    /*
    **************** LISTADO Y REPORTES **************************************
    */
    
    /*
    PRE: listaClientes != null
    POST: Se muestran todos los clientes ordenados alfabeticamente según su nombre.
    De no haber clientes ingresados se mostrará el mensaje correspondiente.
    */
    public Retorno listarClientes();
    
    /*
    PRE: listaProductos != null
    POST: Se muestran todos los productos en orden que fueron registrados.
    De no haber productos ingresados se mostrará el mensaje correspondiente.
    */
    public Retorno listarProductos();
    
    /*PRE: listaClientes != null
    POST: Se muestran todos los pedidos abiertos de cada cliente.
    Si no hay clientes con pedidos abiertos se muestra el mensaje correspondiente
    */
    public Retorno listarPedidosAbiertos();
    
    /*
    PRE: ci es string cualquiera que representa la cédula de un cliente.
    POST: si ci pertenece a un cliente de la lista, se muestran su listaPedidosCerrados.
    Si ci no se encuentra en la lista de clientes, se devuelve ERROR_1. 
    */
    public Retorno pedidosCerradosDeClientes(int ci);
    
    
    /*
    PRE: listaPedidosParaEntregar != null     
    POST: se muestran todos los pedidos listos para entregar. 
    De no haber pedidos para entregar se mostrará el mensaje correspondiente.
    */
    public Retorno productosParaEntregar();
    
    /*
    PRE: listaClientes != null, listaProductos != null, colaPedidosCerrados!=null, listaPedidos !=null.
    POST: se muestra por cada cliente, cuantos productos de cada tipo ha solicitado.
    Si no hay pedidos se muestra el mensaje correspondiente. 
    */
    public Retorno reporteDePedidosSolicitadosXCliente();
    
}
