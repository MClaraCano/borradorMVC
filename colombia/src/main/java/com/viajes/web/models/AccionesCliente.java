package com.viajes.web.models;

import com.viajes.web.models.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class AccionesCliente {
//ALTA cliente
public static int registrarCliente(Cliente cliente){
    
    int estado = 0;

    String url = "jdbc:mysql://localhost/colombia";
    String username = "root";
    String password = "root";

    try {
        //en cada conexión, primero cargar la clase para identificar al Driver
        Class.forName("com.mysql.jdbc.Driver"); 
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Conectado a BBDD Colombia: " + url);

        String queryRegistrar = "INSERT INTO clientes(nombre,apellido,contacto) VALUES (?,?,?)";

        //se pasarán los valores luego en una instancia de Cliente, desde el Main
        String nombreCliente = cliente.getNombre();
        String apellidoCliente = cliente.getApellido();
        String contactoCliente = cliente.getContacto();

        //Una PreparedStatement es una sentencia SQL precompilada.
        //Las utilizaremos en lugar de una Statement cuando haya que ejecutar
        // varias veces una misma sentencia SQL con distintos parámetros.

        //sentencias preparadas, es decir, plantillas de instrucciones a las BBDD
        // a las que no se asignan datos hasta el momento de su ejecución
        // se utilizan por SEGURIDAD, y para poder cambiar los valores. Se pueden reutilizar

        // los parámetros relevantes se les asignan los ya mencionados marcadores de posición,
        // En general, estos marcadores se caracterizan por un signo de interrogación (?)
        PreparedStatement pst = connection.prepareStatement(queryRegistrar);
        
        pst.setString(1, nombreCliente);
        pst.setString(2, apellidoCliente);
        pst.setString(3, contactoCliente);

        //al ejecutar pst, devolverá un valor (1 si está ok)
        //entonces cambiamos el estado, que estaba iniciado en 0
        estado = pst.executeUpdate();

        connection.close();


    } catch (Exception e){
        System.out.println(e);
        System.out.println("Error en registro de cliente" + e.getMessage());
    }

    return estado;
}

//ACTUALIZAR cliente
public static int actualizarCliente(Cliente cliente){
    int estado = 0;

    String url = "jdbc:mysql://localhost/colombia";
    String username = "root";
    String password = "root";

    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);
        System.out.println("Conectado a BBDD Colombia: " + url);

        String queryActualizar = "UPDATE clientes SET nombre=?,apellido=?,contacto=? WHERE id=?";

        int idCliente = cliente.getId();
        String nombreCliente = cliente.getNombre();
        String apellidoCliente = cliente.getApellido();
        String contactoCliente = cliente.getContacto();

        PreparedStatement pst = connection.prepareStatement(queryActualizar);
        
        pst.setString(1, nombreCliente);
        pst.setString(2, apellidoCliente);
        pst.setString(3, contactoCliente);
        pst.setInt(4, idCliente);

        estado = pst.executeUpdate();
        System.out.println("Estado: " + estado);

        connection.close();


    } catch (Exception e){
        System.out.println("Error en actualización de cliente" + e.getMessage());
    }

    return estado;
}

// VER cliente por ID
public static Cliente verCliente(int idAConsultar){
    
    Cliente clienteADevolver = new Cliente();

    String url = "jdbc:mysql://localhost/colombia";
    String username = "root";
    String password = "root";
    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String queryVerCliente = "SELECT * FROM clientes WHERE id=?";

        PreparedStatement pst = connection.prepareStatement(queryVerCliente);
        
        pst.setInt(1, idAConsultar);

        ResultSet consultaCliente = pst.executeQuery();

        if (consultaCliente.next()){
            clienteADevolver.setId( consultaCliente.getInt(1));
            clienteADevolver.setNombre( consultaCliente.getString(2));
            clienteADevolver.setApellido( consultaCliente.getString(3));
            clienteADevolver.setContacto( consultaCliente.getString(4));
            connection.close();
        }
    } catch (Exception e){
        System.out.println(e);
        System.out.println("No se pudo recuperar info cliente");
    }

    return clienteADevolver;
}    

//VER TODOS los clientes
public static List<Cliente> verTodosClientes(){
    List<Cliente> listaClientes = new ArrayList<Cliente>();
    
    String url = "jdbc:mysql://localhost/colombia";
    String username = "root";
    String password = "root";

    try {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, username, password);

        String queryVerTodos = "SELECT * FROM clientes";

        PreparedStatement pst = connection.prepareStatement(queryVerTodos);

        //ejecuto la sentencia y la guardo en consultaCliente
        ResultSet consultaCliente = pst.executeQuery();

        while (consultaCliente.next()){
            //en cada línea creamos un objeto cliente vacío
            Cliente clienteADevolver = new Cliente();
            //se setean los valores
            clienteADevolver.setId( consultaCliente.getInt(1));
            clienteADevolver.setNombre( consultaCliente.getString(2));
            clienteADevolver.setApellido( consultaCliente.getString(3));
            clienteADevolver.setContacto( consultaCliente.getString(4));
            //se añaden los objetos a la List
            listaClientes.add(clienteADevolver);
        }
        connection.close();
    } catch (Exception e){
        System.out.println(e);
        System.out.println("ERROR al devolver lista de clientes");
    }

    return listaClientes;
} 
    
}
