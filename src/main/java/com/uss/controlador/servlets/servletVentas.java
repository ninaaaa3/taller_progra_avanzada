package com.uss.controlador.servlets;

import modelo.Customer;
import modelo.Articulo;
import dao.CustomerDAO;
import dao.ArticuloDAO;
import dao.InvoiceDAO;
import dao.InvoiceLineDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DetalleFactura;
import modelo.Factura;

@WebServlet(name = "servletVentas", urlPatterns = {"/servletVentas"})
public class servletVentas extends HttpServlet {

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
                case "registrarVenta":
                    registrarVenta(request, response);
                    break;
                case "volverLista":
                    listarVentas(request, response);
                    break;
                case "volverMenu":
                    request.getRequestDispatcher("vista/menu_principal.jsp").forward(request, response);
                    break;
                default:
                    response.sendRedirect("vista/menu_principal.jsp");
                    break;
            }
        }
    }
    
    private void listarVentas(HttpServletRequest request, HttpServletResponse responce)
            throws ServletException, IOException {
        try {
            InvoiceDAO invoiceDAO = new InvoiceDAO();
            List <Factura> facturas = invoiceDAO.obtenerTodasLasFacturas();

            request.setAttribute("lista_de_facturas", facturas);
            request.getRequestDispatcher("vista/lista_ventas.jsp").forward(request, responce);
            
        } catch (SQLException e) {
            System.err.println("Error al listar venta en ServletVenta: " + e);
            request.getRequestDispatcher("vista/menu_principal.jsp").forward(request, responce);
        }
    }

    private void mostrarDetalle(HttpServletRequest request, HttpServletResponse responce)
            throws ServletException, IOException {
        int InvoiceID = Integer.parseInt(request.getParameter("InvoiceID"));

        try {
            InvoiceLineDAO invoiceLineDAO = new InvoiceLineDAO();
            InvoiceDAO invoiceDAO = new InvoiceDAO();
            
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
    
    private void registrarVenta(HttpServletRequest request, HttpServletResponse responce)
            throws ServletException, IOException {
        try {
            CustomerDAO clienteDAO = new CustomerDAO();
            ArticuloDAO productoDAO = new ArticuloDAO();

            List<Customer> clientes = clienteDAO.listarClientes();
            List<Articulo> productos = productoDAO.obtenerTodosLosArticulos();

            request.setAttribute("clientes", clientes);
            request.setAttribute("productos", productos);

            RequestDispatcher dispatcher = request.getRequestDispatcher("vista/registrarVenta.jsp");
            dispatcher.forward(request, responce);
        } catch (SQLException e) {
            throw new ServletException("Error al cargar datos para la venta", e);
        }
    }
}