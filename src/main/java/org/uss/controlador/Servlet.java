/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.uss.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilidad.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;
import dao.ArticuloDAO;
import modelo.Articulo;

/**
 *
 * @author kbarr
 */
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
        // Redirige al formulario para agregar artículos
        request.getRequestDispatcher("vista/agregar_articulo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("agregar".equals(accion)) {
            try {
                Articulo articulo = new Articulo();
                articulo.setTrackName(request.getParameter("trackName"));
                articulo.setDescription(request.getParameter("description"));
                articulo.setUnitPrice(Double.parseDouble(request.getParameter("unitPrice")));
                articulo.setStock(Integer.parseInt(request.getParameter("stock")));
                articulo.setCategory(request.getParameter("category"));
                articulo.setCurrencyType(request.getParameter("currencyType"));

                if (articuloDao.agregarArticulo(articulo)) {
                    // Si el artículo se agregó correctamente
                    request.setAttribute("mensaje", "Artículo agregado con éxito.");
                } else {
                    // Si no se pudo agregar
                    request.setAttribute("mensaje", "No se pudo agregar el artículo.");
                }
            } catch (SQLException e) {
                request.setAttribute("mensaje", "Error al agregar el artículo: " + e.getMessage());
            }

            // Redirige a una vista JSP para mostrar el mensaje
            request.getRequestDispatcher("vista/resultado_agregar_articulo.jsp").forward(request, response);
        }
    }
}
