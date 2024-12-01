package com.uss.controlador.servlets;

import dao.ArticuloDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Articulo;

@WebServlet(name = "servletProductos", urlPatterns = {"/servletProductos"})
public class servletProductos extends HttpServlet {

    private ArticuloDAO articuloDAO = null;
    private List<Articulo> articulos = null;

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
                case "lista_eliminar_articulos":
                    articuloDAO = new ArticuloDAO();
                    try {
                        articulos = articuloDAO.obtenerTodosLosArticulos();
                    } catch (SQLException ex) {
                        Logger.getLogger(servletProductos.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    request.setAttribute("lista_de_articulos", articulos);
                    request.getRequestDispatcher("vista/lista_eliminar_productos.jsp").forward(request, response);
                    break;
                case "lista_actualizar_productos":
                    articuloDAO = new ArticuloDAO();
                    try {
                        articulos = articuloDAO.obtenerTodosLosArticulos();
                    } catch (SQLException ex) {
                        Logger.getLogger(servletProductos.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    request.setAttribute("lista_de_articulos", articulos);
                    request.getRequestDispatcher("vista/lista_actualizar_productos.jsp").forward(request, response);
                    break;

                case "actualizar":
                    int trackID = Integer.parseInt(request.getParameter("trackID"));
                    Articulo articulo = null;
                    try {
                        articuloDAO = new ArticuloDAO();
                        articulo = articuloDAO.obtenerArticuloPorId(trackID);
                    } catch (SQLException ex) {
                        Logger.getLogger(servletProductos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    request.setAttribute("articulo", articulo);
                    request.getRequestDispatcher("vista/actualizar_articulo.jsp").forward(request, response);
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
                case "modal_borrar":
                    request.setAttribute("mensaje", "Funcionalidad pendiente: Borrar Producto");
                    request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);
                    break;
                case "borrar":
                    eliminarArticulo(request,response);
                    break;

                case "guardarActualizacion":
                    actualizarArticulo(request, response);
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

    private void actualizarArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArticuloDAO articuloDAO = new ArticuloDAO();
            Articulo articulo = new Articulo();

            articulo.setTrackID(Integer.parseInt(request.getParameter("trackID")));
            articulo.setTrackName(request.getParameter("trackName"));
            articulo.setDescription(request.getParameter("description"));
            articulo.setUnitPrice(Double.parseDouble(request.getParameter("unitPrice")));
            articulo.setStock(Integer.parseInt(request.getParameter("stock")));
            articulo.setCategory(request.getParameter("category"));

            articuloDAO.actualizarArticulo(articulo);

            request.setAttribute("mensaje", "Artículo actualizado con éxito.");
            request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);

        } catch (SQLException e) {
            request.setAttribute("mensaje", "Error al actualizar el artículo: " + e.getMessage());
            request.getRequestDispatcher("vista/manejo_inventario.jsp").forward(request, response);
        }
    }

    private void eliminarArticulo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int trackID = Integer.parseInt(request.getParameter("trackID"));

        try {
            articuloDAO.eliminarArticulo(trackID);
            request.setAttribute("mensaje", "Artículo eliminado con éxito.");
            request.getRequestDispatcher("vista/resultado_eliminar_producto.jsp").forward(request, response);
        } catch (SQLException ex) {
            request.setAttribute("mensaje", "Error al eliminar el artículo: " + ex.getMessage());
            request.getRequestDispatcher("vista/resultado_eliminar_producto.jsp").forward(request, response);
        }

    }
}
