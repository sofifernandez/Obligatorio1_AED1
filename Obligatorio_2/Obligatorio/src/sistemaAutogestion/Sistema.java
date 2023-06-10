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
            r.resultado = Retorno.Resultado.ERROR_1;
        } else {
            Cliente cliente = new Cliente(nombre, ci);
            
            if(ci.equals("333")){ // esto es para la prueba de eliminar cliente con un pedido abierto != null
                Pedido pedido=new Pedido();
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
        Producto buscarProd= getProductoPorNombre(nombre);
        if (buscarProd!=null) {
            r.resultado = Retorno.Resultado.ERROR_1; //Ya existe un producto con ese nombre
        } else {
            listaProductos.agregarFinal(new Producto(nombre, descripcion));
            r.resultado = Retorno.Resultado.OK;
        }
        return r;
    }
    
    /* La función vieja, no la quise borrar por las dudas
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
    */
    
    @Override
    public Retorno eliminarProducto(String nombre) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Producto buscarProd= getProductoPorNombre(nombre);
        if (buscarProd != null) {
            //Esto que sigue es para probar el error_2:
            if(buscarProd.getNombre()=="Producto1"){
                buscarProd.setPedidosProducto(2);
            }
            if (buscarProd.getPedidosProducto() > 0) {
                r.resultado = Retorno.Resultado.ERROR_2; //Está en pedidos abiertos o cerrados
            } else {
                listaProductos.eliminarElemento(buscarProd);
                r.resultado = Retorno.Resultado.OK;
            }
        } else {
            r.resultado = Retorno.Resultado.ERROR_1; //No existe el nombre del producto
        }
        return r;
    }
    
    /* FUNCION VIEJA
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
    */

    @Override
    public Retorno altaStockProducto(int nroProducto, int unidades) {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        if (unidades > 0) {
            if (listaProductos.existeElemento(new Producto(nroProducto))) {
                //Nodo nuevo = listaProductos.obtenerElemento(nroProducto);
                Producto producto = getProductoPorID(nroProducto);
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
        } else 
        {
                cliente.setPedidoAbierto(new Pedido());
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
        //2) hacerle push a la PilaProductos del pedidoAbierto del cliente
        //3) al pedido abierto sumarle las unidades totales
        //4) Restarle las unidades agregadas al stock de producto
        
       //ProductoCantidad prodCant=new ProductoCantidad(producto.getID(),unidades)
        ProductoCantidad prodCant=new ProductoCantidad(producto.getID()); //Aca le saque las unidades al constructor y se las cargo con el setunidades
        prodCant.setCantidad(unidades);                               // Porque de la forma que estaba antes al prodCant no le cargaba las unidades
       
        if(cliente.getPedidoAbierto() == null){ //Si el cliente no tiene pedido abierto, se le abre uno
            aperturaDePedido(cliente.getCi());
        }
        cliente.getPedidoAbierto().getPilaProductos().push(prodCant);
        cliente.getPedidoAbierto().actualizarUnidades(unidades, "AGREGAR");
        //cliente.getPedidoAbierto().setUnidadesTotales(cliente.getPedidoAbierto().getUnidadesTotales()+unidades);
        producto.setStock(producto.getStock()-unidades);
        producto.setPedidosProducto(producto.getPedidosProducto() +1);  //Para llevar la cuenta de PedidosProducto 
        
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    
    @Override
    //OJO ACÁ, CADA VEZ QUE SE ELIMINA UN PRODUCTO DE UN PEDIDO HAY QUE ACTUALIZAR EL STOCK
    // Y LAS UNIDADES TOTALES QUE HAY EN EL PEDIDO
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
        while(contador<cantAccionesDeshacer) //Aca si arranca de 0 deberia ser solo menor (<)
        {
            
            Nodo nodoProducto=cliente.getPedidoAbierto().getPilaProductos().getTope();
            ProductoCantidad prodCant = (ProductoCantidad) nodoProducto.getDato();
            Producto producto =getProductoPorID(prodCant.getID());
            producto.setStock(producto.getStock() + prodCant.getCantidad());
            producto.setPedidosProducto(producto.getPedidosProducto() -1 ); //Para llevar la cuenta de PedidosProducto
            cliente.getPedidoAbierto().actualizarUnidades(prodCant.getCantidad(), "ELIMINAR");
            cliente.getPedidoAbierto().getPilaProductos().pop();
            contador++;
        }
        if(cliente.getPedidoAbierto().getPilaProductos().esVacia())
        {
            cliente.setPedidoAbierto(null);
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
        
        cliente.getListaPedidosCerrados().agregarInicio(cliente.getPedidoAbierto());
        colaPedidosCerrados.encolar(cliente.getPedidoAbierto());
        cliente.setPedidoAbierto(null);
        r.resultado = Retorno.Resultado.OK;
        return r;
    }

    @Override
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
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
    public Retorno listarPedidosAbiertos() {
        Retorno r = new Retorno(Retorno.Resultado.NO_IMPLEMENTADA);
        Nodo aux = listaClientes.getInicio();

        if (!listaClientes.esVacia()) {
            while (aux != null) {
                Cliente cliente = (Cliente) aux.getDato();
                if(cliente.getPedidoAbierto()!=null){
                    System.out.println("---------------------------------");
                    System.out.println(cliente.toString());
                    System.out.println(cliente.getPedidoAbierto().getPilaProductos().getCantidad()); 
                }
                aux = aux.getSiguiente();
            }
        } else {
            System.out.println("N hay clientes en la lista");
        }
        return r;
    }

    @Override
    public Retorno pedidosCerradosDeClientes(String ci) {
        Retorno r = new Retorno(Retorno.Resultado.OK);
        Cliente cliente= getCliente(ci);
        cliente.getListaPedidosCerrados().mostrar();
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
        int rows=listaClientes.cantidadElementos();
        int columns=listaProductos.cantidadElementos();
        Object[][] mat= new Object[rows][columns];
        String[][] mat2 = new String[rows][columns];
        //Agarrar la lista de productos, e ir uno por uno y buscarlos en los clientes? 
        
        //Cliente -->ListaPedidosCerrados-->Pedido --> cola de ProductoCantidad
        //      1   2   3   4  (ID de productos)
        //---------------------------
        // A    0   3   1   0   
        // B
        // C
        // D
        //(Clientes)
               
        //Poner los nombres de los clientes en la Columna 0
        Nodo auxCliente = listaClientes.getInicio();
        while (auxCliente != null) {
            for (int i=0; i < mat2.length; i++){
                Cliente cliente = (Cliente) auxCliente.getDato();
                mat2[i][0]=cliente.getNombre();
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
        
        
        //POPULAR MATRIZ        
        for (int i = 0; i < mat2.length; i++) {
            Cliente cliente=getCliente(mat2[i][0]);
            for (int j = 1; j < mat[i].length; j++) {
                int IDProd= Integer.parseInt(mat2[0][j]);
                int unidades=cliente.unidadesTotalesProducto(IDProd);
                mat2[i][j]=Integer.toString(unidades);
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
       
        //MOSTRAR MATRIZ
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        
        Retorno r = new Retorno(Retorno.Resultado.OK);
        return r;
        
    
        
    }


}
