/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.uss.controlador.servlets;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ArticuloDAO;
import static java.lang.System.out;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import modelo.Articulo;

@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class ServletPrincipal extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            request.getRequestDispatcher("vista/menu_principal.jsp").forward(request, response);
        } else {
            switch (accion) {
                case "manejoInventario":
                    request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);
                    break;
                case "listarProductos":
                    listarProductos(request, response);
                    break;
                case "ingresoVentas":
                    request.getRequestDispatcher("vista/ingreso_ventas.jsp").forward(request, response);
                    break;
                case "listarVentas":
                    request.getRequestDispatcher("vista/listar_ventas.jsp").forward(request, response);
                    break;
                case "volver":
                    request.getRequestDispatcher("vista/menu_principal.jsp").forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("vista/menu_principal.jsp").forward(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            response.sendRedirect("vista/menu_principal.jsp");
            return;
        }
    }

    private void listarProductos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        try {
            ArticuloDAO articuloDAO = new ArticuloDAO();
            
            List<Articulo> articulos = articuloDAO.obtenerTodosLosArticulos();

            
            request.setAttribute("lista_de_articulos", articulos);
            request.getRequestDispatcher("vista/listar_productos.jsp").forward(request, response);
            
        } catch (Exception e) {
            System.err.println("Error al pedir la lista de artículos desde el servletProductos: "+e);
        }
    }
}
