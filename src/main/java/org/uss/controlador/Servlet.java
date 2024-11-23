/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.uss.controlador;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.ArticuloDAO;
import modelo.Articulo;

@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    private ArticuloDAO articuloDao;

    @Override
    public void init() throws ServletException {
        articuloDao = new ArticuloDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        // Redirige según la acción especificada
        if (accion == null || accion.isEmpty()) {
            // Redirige al menú principal si no hay acción
            request.getRequestDispatcher("vista/menu_principal.jsp").forward(request, response);
        } else {
            switch (accion) {
                case "manejoInventario":
                    request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);
                    break;
                case "listarProductos":
                    // Implementar la funcionalidad para listar productos más adelante
                    request.getRequestDispatcher("vista/listar_productos.jsp").forward(request, response);
                    break;
                case "ingresoVentas":
                    // Implementar la funcionalidad para ingreso de ventas más adelante
                    request.getRequestDispatcher("vista/ingreso_ventas.jsp").forward(request, response);
                    break;
                case "listarVentas":
                    // Implementar la funcionalidad para listar ventas más adelante
                    request.getRequestDispatcher("vista/listar_ventas.jsp").forward(request, response);
                    break;
                default:
                    // Si la acción no es reconocida, vuelve al menú principal
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

        switch (accion) {
            case "agregar":
                agregarArticulo(request, response);
                break;
            case "borrar":
                // Implementar la funcionalidad de borrar producto más adelante
                request.setAttribute("mensaje", "Funcionalidad pendiente: Borrar Producto");
                request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);
                break;
            case "actualizar":
                // Implementar la funcionalidad de actualizar producto más adelante
                request.setAttribute("mensaje", "Funcionalidad pendiente: Actualizar Producto");
                request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);
                break;
            default:
                response.sendRedirect("vista/menu_principal.jsp");
                break;
        }
    }

    private void agregarArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Articulo articulo = new Articulo();
            articulo.setTrackName(request.getParameter("trackName"));
            articulo.setDescription(request.getParameter("description"));
            articulo.setUnitPrice(Double.parseDouble(request.getParameter("unitPrice")));
            articulo.setStock(Integer.parseInt(request.getParameter("stock")));
            articulo.setCategory(request.getParameter("category"));
            articulo.setCurrencyType(request.getParameter("currencyType"));

            if (articuloDao.agregarArticulo(articulo)) {
                request.setAttribute("mensaje", "Artículo agregado con éxito.");
            } else {
                request.setAttribute("mensaje", "No se pudo agregar el artículo.");
            }
        } catch (SQLException e) {
            request.setAttribute("mensaje", "Error al agregar el artículo: " + e.getMessage());
        }

        // Redirige a una vista JSP para mostrar el mensaje
        request.getRequestDispatcher("vista/resultado_agregar_articulo.jsp").forward(request, response);
    }
}
