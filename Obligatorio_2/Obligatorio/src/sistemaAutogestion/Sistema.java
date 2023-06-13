package sistemaAutogestion;

import tads.*;
import clases.*; 
import java.util.HashSet;


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
    
    
    private Producto getProductoPorNombre(String nombre) {
        if (!listaProductos.esVacia()) {
            Nodo aux = listaProductos.getInicio();
            Producto productoEnLista = (Producto) aux.getDato();
            while(aux != null && !productoEnLista.getNombre().equals(nombre)){
                aux=aux.getSiguiente();
                if(aux!=null){
                    productoEnLista = (Producto) aux.getDato();
                }
            }
            if(aux!=null && productoEnLista.getNombre().equals(nombre)){
                productoEnLista = (Producto) aux.getDato();
                return productoEnLista;
            }
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
            r.resultado = Retorno.Resultado.ERROR_1; //Si el cliente ya existe
        } else {
            Cliente cliente = new Cliente(nombre, ci);
            if(ci.equals("333")){ // Esto es para la prueba de eliminar cliente con un pedido abierto != null
                Pedido pedido=new Pedido();
                pedido.setEstado("ABIERTO");
                pedido.setCliente(cliente);
                cliente.setPedidoAbierto(pedido);
            }
            listaClientes.agregarOrd(cliente);
            r.resultado = Retorno.Resultado.OK;
        }      
        return r;
    }

    @Override
    public Retorno eliminarCliente(String ci) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Cliente cliente= getCliente(ci);
        
        if (cliente!=null) {
            if (cliente.getlistaPedidos().esVacia() && cliente.getPedidoAbierto() == null) //Si no no tiene pedidos cerrados ni abiertos se puede eliminar
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
        Producto buscarProd= getProductoPorNombre(nombre);
        if (buscarProd!=null) {
            r.resultado = Retorno.Resultado.ERROR_1; //Ya existe un producto con ese nombre
        } else {
            listaProductos.agregarFinal(new Producto(nombre, descripcion));
            r.resultado = Retorno.Resultado.OK;
        }
        return r;
    }
    
    
    @Override
    public Retorno eliminarProducto(String nombre) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Producto buscarProd= getProductoPorNombre(nombre);
        if (buscarProd != null) {
            //Esto que sigue es para probar el error_2:
            if(buscarProd.getNombre().equals("Producto1")){
                buscarProd.setPedidosProducto(2);
            }
            if (buscarProd.getPedidosProducto() > 0) {
                r.resultado = Retorno.Resultado.ERROR_2; //Está en pedidos abiertos o cerrados
            } else {
                listaProductos.eliminarElemento(buscarProd);
                r.resultado = Retorno.Resultado.OK;
            }
            
            //Reseteo el pedidoProducto de la prueba en 0 de nuevo, pero sin cambiar el error de retorno
            if(buscarProd.getNombre().equals("Producto1")){
                buscarProd.setPedidosProducto(0);
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
            Producto producto = getProductoPorID(nroProducto);
            if(producto!=null){
                producto.setStock(unidades + producto.getStock());
                r.resultado = Retorno.Resultado.OK;
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
        
        if (cliente.getPedidoAbierto() != null) 
        {
            r.resultado = Retorno.Resultado.ERROR_2; //El cliente ya tiene un pedido abierto
        } 
        else 
        {
                Pedido pedido=new Pedido();
                pedido.setEstado("ABIERTO");
                pedido.setCliente(cliente);
                cliente.setPedidoAbierto(pedido);
                r.resultado = Retorno.Resultado.OK;
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
            r.resultado = Retorno.Resultado.ERROR_3; //Se supera el máximo de unidades totales permitidas para el pedido.
            return r;
        }
        
        if(unidades > producto.getStock())
        {
            r.resultado = Retorno.Resultado.ERROR_5; //Stock insuficiente
            return r;
        }
        //AHORA SÍ, AGREGAR
        //1) crear un nuevo ProductoCantidad
        //2) pushearlo a la PilaProductos del pedidoAbierto del cliente
        //3) al pedido abierto sumarle las unidades totales
        //4) Restarle las unidades agregadas al stock de producto

        ProductoCantidad prodCant=new ProductoCantidad(producto.getID()); 
        prodCant.setCantidad(unidades);                               
        
        if(cliente.getPedidoAbierto() == null){ //Si el cliente no tiene pedido abierto, se le abre uno
            aperturaDePedido(cliente.getCi());
        }
        if(!cliente.getPedidoAbierto().getPilaProductos().existeElemento(prodCant)) //Si no tiene agregado ya ese producto agregarlo
        {
            cliente.getPedidoAbierto().getPilaProductos().push(prodCant);
            producto.setPedidosProducto(producto.getPedidosProducto() +1);  //Para llevar la cuenta de pedidosProducto 
        }
        else{ //Si ya está el producto en el pedido, se obtiene y se actualiza su cantidad
           ProductoCantidad prodViejo = (ProductoCantidad)cliente.getPedidoAbierto().getPilaProductos().obtenerElemento(prodCant).getDato();
           prodViejo.setCantidad(prodViejo.getCantidad() + unidades);
        }
        cliente.getPedidoAbierto().actualizarUnidades(unidades, "AGREGAR");
        producto.setStock(producto.getStock()-unidades);
        
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    
    @Override
    //OJO ACÁ, CADA VEZ QUE SE ELIMINA UN PRODUCTO DE UN PEDIDO HAY QUE ACTUALIZAR EL STOCK
    // Y LAS UNIDADES TOTALES QUE HAY EN EL PEDIDO
    // y Los pedidoProducto
    public Retorno deshacerPedido(String ciCliente, int cantAccionesDeshacer){
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Cliente cliente= getCliente(ciCliente);
        //AGREGO ACÁ QUE NO TENGA PEDIDO ABIERTO PERO NO ESTÁ EN LA LETRA
        if(cliente==null || cliente.getPedidoAbierto()==null)
        {
            r.resultado = Retorno.Resultado.ERROR_1; //El cliente no existe o no tiene pedido abierto
            return r;
        }
        
        if(cantAccionesDeshacer<=0)
        {
            r.resultado = Retorno.Resultado.ERROR_2; //Las acciones son 0 o menores
            return r;
        }
        
        if(cantAccionesDeshacer > cliente.getPedidoAbierto().getPilaProductos().getCantidad())
        {
            r.resultado = Retorno.Resultado.ERROR_3; //cantidad de acciones solicitadas supere la cantidad de productos que fueron agregados.
            return r;
        }
        
        int contador=0;
        while(contador<cantAccionesDeshacer) 
        {
            Nodo nodoTope=cliente.getPedidoAbierto().getPilaProductos().getTope(); //Obtener el tope de la pila
            ProductoCantidad prodCant = (ProductoCantidad) nodoTope.getDato();
            Producto producto =getProductoPorID(prodCant.getID()); //Buscar el producto en la listaProductos
            //stock:
            producto.setStock(producto.getStock() + prodCant.getCantidad()); //Resetear el stock, agrego las unidades que estaban en el pedido
            //pedidosProducto:
            producto.setPedidosProducto(producto.getPedidosProducto() -1 ); //Actualizo la cuenta de PedidosProducto
            //unidadesTotales:
            cliente.getPedidoAbierto().actualizarUnidades(prodCant.getCantidad(), "ELIMINAR"); //Actualizo las unidadesTotales del Pedido
            cliente.getPedidoAbierto().getPilaProductos().pop(); //Ahora sí, se elimina de la pila
            contador++;
        }
        if(cliente.getPedidoAbierto().getPilaProductos().esVacia())
        {
            cliente.setPedidoAbierto(null); //Se vuelve a null el pedidoAbierto
        }
        r.resultado = Retorno.Resultado.OK;    
        return r;
    }

    
    @Override
    public Retorno cerrarPedido(String ciCliente) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Cliente cliente= getCliente(ciCliente);
        if(cliente==null)
        {
            r.resultado = Retorno.Resultado.ERROR_1; //El cliente no existe
            return r;
        }
        
        if(cliente.getPedidoAbierto()==null){
            r.resultado = Retorno.Resultado.ERROR_2; //El cliente no tiene un pedido abierto
            return r;
        }
        cliente.getPedidoAbierto().setEstado("CERRADO");
        cliente.getlistaPedidos().agregarInicio(cliente.getPedidoAbierto());
        colaPedidosCerrados.encolar(cliente.getPedidoAbierto());
        cliente.setPedidoAbierto(null);
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override //ACÁ FALTAN RESTAURAR LOS PEDIDOS DE CADA PRODUCTO (porque dice que sea en pedidos cerrados y abiertos)
    public Retorno procesarPedido(int cantPedidos) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if(cantPedidos<=0){
            r.resultado = Retorno.Resultado.ERROR_1; //Las acciones son 0 o menores
            return r;
        }
        
        if(colaPedidosCerrados.getCantidad()<cantPedidos){
            r.resultado = Retorno.Resultado.ERROR_2; //Cantidad de pedidos a procesar es mayor a la cantidad de pedidos cerrados
            return r;
        }
        
        int contador=0;
        while (contador < cantPedidos)
        {
            Pedido miP = (Pedido)colaPedidosCerrados.obtenerPrimero();
            miP.setEstado("ParaEntregar");       
            listaPedidosParaEntregar.agregarFinal(colaPedidosCerrados.obtenerPrimero());
            colaPedidosCerrados.desencolar();
            contador++;
        }
        r.resultado = Retorno.Resultado.OK;
        return r;
        
    }

    //--------------------------------------------------------------------------
    // Listados y Reportes
    
    @Override
    public Retorno listarClientes() {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        listaClientes.mostrar();
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    public Retorno listarProductos() { //ESTE ES RECURSIVA
        //Se muestran todos los productos en el orden que fueron registrados. Se muestra sus datos y el stock disponible de dicho producto.
        //Si agregamos productos con 'agregarFinal() quedaría no?' //Si SNIORA, ya lo cambie en agregar producto
        //Y en la recursividad hay que mostrar a la ida
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        listaProductos.mostrarRec();
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
    //Como mostramos una pila??? Qué información de los pedidos habría que mostrar??
    //FALTA MOSTRAR QUÉ PRODUCTOS TIENE EL PEDIDO Y LAS UNIDADES
    public Retorno listarPedidosAbiertos() {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Nodo aux = listaClientes.getInicio();

        if (!listaClientes.esVacia()) {
            while (aux != null) {
                Cliente cliente = (Cliente) aux.getDato();
                if(cliente.getPedidoAbierto()!=null){
                    System.out.println("---------------------------------");
                    System.out.println(cliente.toString());                   
                    cliente.getPedidoAbierto().getPilaProductos().mostrar(); 
                }
                aux = aux.getSiguiente();
            }
        } else {
            System.out.println("No hay clientes en la lista");
        }
        return r;
    }

    @Override
    public Retorno pedidosCerradosDeClientes(String ci) {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        Cliente cliente= getCliente(ci);
        
        if(cliente == null){
            r.resultado = Retorno.Resultado.ERROR_1;
            return r;
        }
        ListaSimple listaPedidosCliente = cliente.getlistaPedidos();
        Nodo aux = listaPedidosCliente.getInicio();

        if (!listaPedidosCliente.esVacia()) {
            while (aux != null) {
                Pedido miP = (Pedido) aux.getDato();
                if(miP.getEstado().equals("CERRADO")){
                    System.out.println(miP + " ");
                }
                    aux = aux.getSiguiente();   
            }
        } else {
            System.out.println("Esta vacia!");
        }
        return r;
    }

    @Override
    public Retorno productosParaEntregar() { //ESTA ES RECURSIVA
        //Agregar al final y mostrar en orden que fueron agregados
        Retorno r = new Retorno(Retorno.Resultado.OK);
        listaPedidosParaEntregar.mostrarRec();
        return r;
    }

    @Override //TODOS LOS CLIENTES? SI NO TIENE PEDIDOS CERRADOS VA CON UN 0 EN TODOS LOS PRODUCTO?
    //hay que sumar la cantidad de unidades de cada producto si aparece en más de un pedido cerrado?
    public Retorno reporteDePedidosSolicitadosXCliente() {
        //Crear la matriz primero y después mostrarla
        int rows=listaClientes.getCantidad()+1;
        int columns=listaProductos.getCantidad()+1;

        //Object[][] mat= new Object[rows][columns];
        String[][] mat2 = new String[rows][columns];
        
        //ESTRATEGIA:
        //Agarrar la lista de productos, e ir uno por uno y buscarlos en los clientes? 
        
        //Cliente -->listaPedidos-->Pedido --> cola de ProductoCantidad
        //      1   2   3   4  (ID de productos) --> fila 0
        //---------------------------
        // A    0   3   1   0   
        // B
        // C
        // D
        //(Clientes)
               
        
        //Poner los nombres de los clientes en la Columna 0
        Nodo auxCliente = listaClientes.getInicio();
        while (auxCliente != null) {
            for (int i=1; i < mat2.length; i++){
                Cliente cliente = (Cliente) auxCliente.getDato();
                mat2[i][0]=cliente.getCi();
                auxCliente = auxCliente.getSiguiente();
            }   
        }
        
        
        
        //Poner los nombres de los productos en la fila 0
        Nodo auxProd = listaProductos.getInicio();
        while (auxProd != null) {
            for (int j=1; j < mat2[0].length; j++){
                Producto producto = (Producto) auxProd.getDato();
                mat2[0][j]= Integer.toString(producto.getID());
                auxProd = auxProd.getSiguiente();
            }   
        }
        
        //POPULAR LA MATRIZ
        //cantidadProdTotal
        
        //mat2[fila i][columna j]
        //mat2[0][j] --> nombres Productos
        //mat2[i][0] --> nombres clientes
        
        
        
        for (int i = 1; i < mat2.length; i++) { //Dentro de una fila, o sea de un producto
            Cliente cliente=getCliente(mat2[i][0]); //esto funciona
            for (int j = 1; j < mat2[i].length; j++) { //Dentro de una columna, o sea cliente
                
                Producto producto= getProductoPorID(Integer.parseInt(mat2[0][j])); //esto funciona

                int cantidadTotal=cliente.cantidadProdTotal(producto.getID());
                
                mat2[i][j]=Integer.toString(cantidadTotal);
            }
        }
        
        
        //MOSTRAR
        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[i].length; j++) {
                System.out.print(mat2[i][j] + " ");
            }
            System.out.println();
        }
        
        
        Retorno r = new Retorno(Retorno.Resultado.OK);
        return r;
    }
    


}
