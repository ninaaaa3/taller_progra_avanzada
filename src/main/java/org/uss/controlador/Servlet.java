/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.uss.controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilidad.ConexionBD;
import dao.ArticuloDAO;
import dao.InvoiceDAO;
import modelo.Articulo;
import modelo.Invoice;
import modelo.InvoiceLine;

/**
 * Servlet principal para la gestión de artículos y ventas
 * 
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    private ArticuloDAO articuloDao;
    private InvoiceDAO invoiceDao;

    @Override
    public void init() throws ServletException {
        articuloDao = new ArticuloDAO();
        invoiceDao = new InvoiceDAO(); // Se añade el DAO para manejar facturas
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("consultarVentas".equals(accion)) {
            try {
                // Obtener la lista de facturas para consultar las ventas
                List<Invoice> facturas = invoiceDao.obtenerFacturas();
                request.setAttribute("facturas", facturas);

                // Redirige a la vista para consultar ventas
                request.getRequestDispatcher("vista/consultar_ventas.jsp").forward(request, response);
            } catch (SQLException e) {
                request.setAttribute("mensaje", "Error al consultar ventas: " + e.getMessage());
                request.getRequestDispatcher("vista/error.jsp").forward(request, response);
            }
        } else {
            // Redirige al formulario para agregar artículos
            request.getRequestDispatcher("vista/agregar_articulo.jsp").forward(request, response);
        }
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
        } else if ("registrarVenta".equals(accion)) {
            // Manejo de registrar ventas
            try {
                int clienteId = Integer.parseInt(request.getParameter("cliente"));
                String fecha = request.getParameter("fecha");

                // Crear la factura
                Invoice factura = new Invoice();
                factura.setCustomerID(clienteId);
                factura.setInvoiceDate(fecha);
                factura.setPaymentCurrency("CLP");
                factura.setStatus("Pagado");

                // Procesar las líneas de detalle
                String[] articulos = request.getParameterValues("articulo[]");
                String[] cantidades = request.getParameterValues("cantidad[]");

                List<InvoiceLine> lineas = new ArrayList<>();
                for (int i = 0; i < articulos.length; i++) {
                    InvoiceLine linea = new InvoiceLine();
                    linea.setTrackID(Integer.parseInt(articulos[i]));
                    linea.setQuantity(Integer.parseInt(cantidades[i]));
                    linea.setLineTotal(0.0); // Calcular el total por línea (puedes ajustar esto)
                    lineas.add(linea);
                }

                // Guardar la factura y sus líneas en la base de datos
                invoiceDao.registrarFactura(factura, lineas);
                request.setAttribute("mensaje", "Venta registrada con éxito.");
            } catch (SQLException e) {
                request.setAttribute("mensaje", "Error al registrar la venta: " + e.getMessage());
            }

            // Redirige a una vista JSP para mostrar el resultado
            request.getRequestDispatcher("vista/resultado_registrar_venta.jsp").forward(request, response);
        }
    }
}
