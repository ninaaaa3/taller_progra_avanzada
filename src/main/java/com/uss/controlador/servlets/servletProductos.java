package com.uss.controlador.servlets;

import dao.ArticuloDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Articulo;

@WebServlet(name = "servletProductos", urlPatterns = {"/servletProductos"})
public class servletProductos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);
        } else {
            switch (accion) {
                case "agregar":
                    request.getRequestDispatcher("vista/agregar_articulo.jsp").forward(request, response);
                    break;
                case "borrar":
                    request.setAttribute("mensaje", "Funcionalidad pendiente: Borrar Producto");
                    request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);
                    break;
                case "actualizar":
                    request.setAttribute("mensaje", "Funcionalidad pendiente: Actualizar Producto");
                    request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);
                    break;
                case "volver":
                    request.getRequestDispatcher("vista/menu_principal.jsp").forward(request, response);
                    break;
                default:
                    response.sendRedirect("vista/menu_principal.jsp");
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        if (accion == null || accion.isEmpty()) {
            request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);
        } else {
            switch (accion) {
                case "agregar":
                    agregarArticulo(request, response);
                    break;
                case "borrar":
                    request.setAttribute("mensaje", "Funcionalidad pendiente: Borrar Producto");
                    request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);
                    break;
                case "actualizar":
                    request.setAttribute("mensaje", "Funcionalidad pendiente: Actualizar Producto");
                    request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);
                    break;
                case "volver":
                    request.getRequestDispatcher("vista/menu_principal.jsp").forward(request, response);
                    break;
                default:
                    response.sendRedirect("vista/menu_principal.jsp");
                    break;
            }
        }
    }

    private void agregarArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArticuloDAO articuloDAO = new ArticuloDAO();
            Articulo articulo = new Articulo();
            
            articulo.setTrackName(request.getParameter("trackName"));
            articulo.setDescription(request.getParameter("description"));
            articulo.setUnitPrice(Double.parseDouble(request.getParameter("unitPrice")));
            articulo.setStock(Integer.parseInt(request.getParameter("stock")));
            articulo.setCategory(request.getParameter("category"));
            articulo.setCurrencyType(request.getParameter("currencyType"));

            if (articuloDAO.agregarArticulo(articulo)) {
                request.setAttribute("mensaje", "Artículo agregado con éxito.");
            } else {
                request.setAttribute("mensaje", "No se pudo agregar el artículo.");
            }
        } catch (SQLException e) {
            request.setAttribute("mensaje", "Error al agregar el artículo: " + e.getMessage());
            throw new ServletException("Error al agregar artículo", e);
        }

        request.getRequestDispatcher("vista/resultado_agregar_articulo.jsp").forward(request, response);
    }
}
