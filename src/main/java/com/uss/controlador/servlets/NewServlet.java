package com.uss.controlador.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.InvoiceDAO;
import dao.InvoiceLineDAO;
import java.util.List;
import modelo.DetalleFactura;
import modelo.Factura;

@WebServlet(name = "NewServlet", urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

    private InvoiceDAO invoiceDAO = null;
    private List<Factura> facturas = null;
    private InvoiceLineDAO invoiceLineDAO = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            request.getRequestDispatcher("vista/menu_principal.jsp").forward(request, response);
        } else {
            switch (accion) {
                case "listarVentas":
                    listarVentas(request, response);
                    break;
                case "detalleVenta":
                    mostrarDetalle(request, response);
                    break;
                default:
                    response.sendRedirect("vista/menu_principal.jsp");
                    break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    private void listarVentas(HttpServletRequest request, HttpServletResponse responce)
            throws ServletException, IOException {
        try {
            invoiceDAO = new InvoiceDAO();
            facturas = invoiceDAO.obtenerTodasLasFacturas();

            request.setAttribute("lista_de_facturas", facturas);
            request.getRequestDispatcher("vista/lista_ventas.jsp").forward(request, responce);
        } catch (SQLException e) {
            System.err.println("Error al listar venta en ServletVenta: " + e);
        }
    }

    private void mostrarDetalle(HttpServletRequest request, HttpServletResponse responce)
            throws ServletException, IOException {
        int InvoiceID = Integer.parseInt(request.getParameter("InvoiceID"));

        try {
            invoiceLineDAO = new InvoiceLineDAO();
            invoiceDAO = new InvoiceDAO();
            
            List<DetalleFactura> detalleFactura = invoiceLineDAO.obtenerDetallePorInvoiceID(InvoiceID);
            Factura factura = invoiceDAO.obtenerFacturaPorID(InvoiceID);
            
            request.setAttribute("invoiceID", InvoiceID);
            request.setAttribute("customerID", factura.getCustomerID());
            request.setAttribute("invoiceDate", factura.getInvoiceDate());
            request.setAttribute("paymentCurrency", factura.getPaymentCurrency());
            request.setAttribute("totalCLP", factura.getTotal_CLP());
            request.setAttribute("status", factura.getStatus());
            
            
            request.setAttribute("invoiceLines", detalleFactura);
            request.getRequestDispatcher("vista/detalle_venta.jsp").forward(request, responce);
        } catch (SQLException e) {
            System.err.println("Error al mostrar detalle de venta en ServletVenta: " + e);
        }

    }
}
