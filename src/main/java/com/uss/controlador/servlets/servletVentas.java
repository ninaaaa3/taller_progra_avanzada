package com.uss.controlador.servlets;

import modelo.Customer;
import modelo.Articulo;
import dao.CustomerDAO;
import dao.ArticuloDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "servletVentas", urlPatterns = {"/servletVentas"})
public class servletVentas extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CustomerDAO clienteDAO = new CustomerDAO();
            ArticuloDAO productoDAO = new ArticuloDAO();

            List<Customer> clientes = clienteDAO.listarClientes();
            List<Articulo> productos = productoDAO.obtenerTodosLosArticulos();

            request.setAttribute("clientes", clientes);
            request.setAttribute("productos", productos);

            RequestDispatcher dispatcher = request.getRequestDispatcher("vista/registrarVenta.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al cargar datos para la venta", e);
        }
    }
}