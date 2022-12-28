package com.viajes.web.servlets;

import java.io.IOException;

import com.viajes.web.models.AccionesCliente;
import com.viajes.web.models.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/nuevocliente")
public class InsertarServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreCliente;
        String apellidoCliente;
        String contactoCliente;

        nombreCliente = req.getParameter("nombre");
        apellidoCliente = req.getParameter("apellido");
        contactoCliente = req.getParameter("contacto");

        Cliente nuevoCliente = new Cliente();

        nuevoCliente.setNombre(nombreCliente);
        nuevoCliente.setApellido(apellidoCliente);
        nuevoCliente.setContacto(contactoCliente);

        int estado = AccionesCliente.registrarCliente(nuevoCliente);

        if(estado == 1){
            //redirigirá a una vista jsp
            resp.sendRedirect("exito.jsp");
        }else {
            resp.sendRedirect("error.jsp");
        }
    }
}
