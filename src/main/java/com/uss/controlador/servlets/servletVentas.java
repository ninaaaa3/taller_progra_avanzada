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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.DetalleFactura;
import modelo.Factura;
import modelo.InvoiceLine;
import utilidad.ExchangeRateService;

@WebServlet(name = "servletVentas", urlPatterns = {"/servletVentas"})
public class servletVentas extends HttpServlet {

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
                case "registrarVenta":
                    registrarVenta(request, response);
                    break;
                default:
                    response.sendRedirect("vista/menu_principal.jsp");
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion == null || accion.isEmpty()) {
            request.getRequestDispatcher("vista/menu_principal.jsp").forward(request, response);
        } else {
            switch (accion) {
                case "registrar_venta_bd":
                {
                    try {
                        registrarVentaEnBD(request, response);
                    } catch (SQLException ex) {
                        Logger.getLogger(servletVentas.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    break;

                default:
                    response.sendRedirect("vista/menu_principal.jsp");
                    break;
            }
        }
    }

    private void registrarVenta(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CustomerDAO clienteDAO = new CustomerDAO();
            ArticuloDAO productoDAO = new ArticuloDAO();
            ExchangeRateService exchangeRateService = new ExchangeRateService();

            List<Customer> clientes = clienteDAO.listarClientes();
            List<Articulo> productos = productoDAO.obtenerTodosLosArticulos();

            Map<String, Double> exchangeRates = exchangeRateService.getExchangeRates("CLP");

            request.setAttribute("clientes", clientes);
            request.setAttribute("productos", productos);
            request.setAttribute("exchangeRates", exchangeRates);

            RequestDispatcher dispatcher = request.getRequestDispatcher("vista/registrarVenta.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            throw new ServletException("Error al cargar datos para la venta", e);
        } catch (Exception e) {
            throw new ServletException("Error al obtener tasas de cambio", e);
        }
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

    private void registrarVentaEnBD(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            int customerID = Integer.parseInt(request.getParameter("customerId"));
            String paymentCurrency = request.getParameter("paymentCurrency");
            String[] productIds = request.getParameterValues("productId");
            String[] quantities = request.getParameterValues("quantity");
            String[] lineTotals = request.getParameterValues("lineTotal");
            float totalCLP = Float.parseFloat(request.getParameter("totalCLP"));

            InvoiceDAO invoiceDAO = new InvoiceDAO();
            Factura factura = new Factura();

            factura.setCustomerID(customerID);
            factura.setPaymentCurrency(paymentCurrency);
            factura.setTotal_CLP(totalCLP);

            invoiceDAO.agregarFactura(factura);

            int id_ultima_factura = invoiceDAO.obtenerUltimaFacturaID();
            InvoiceLineDAO invoiceLineDAO = new InvoiceLineDAO();
            ArticuloDAO articuloDAO = new ArticuloDAO();
            InvoiceLine invoiceLine = new InvoiceLine();
            Articulo articulo = new Articulo();

            if (productIds != null && quantities != null && lineTotals != null) {
            for (int i = 0; i < productIds.length; i++) {
                int productId = Integer.parseInt(productIds[i]);
                int quantity = Integer.parseInt(quantities[i]);
                float lineTotal = Float.parseFloat(lineTotals[i]);


                articulo = articuloDAO.obtenerArticuloPorId(productId);

                float unitPrice = (float) articulo.getUnitPrice();

                invoiceLine.setInvoiceId(id_ultima_factura);
                invoiceLine.setTrackId(productId);
                invoiceLine.setQuantity(quantity);
                invoiceLine.setUnitPrice(unitPrice);
                invoiceLine.setLineTotal(lineTotal);

                invoiceLineDAO.insertarDetalle(invoiceLine);
            
            
        }
            request.setAttribute("mensaje", "Factura ingresada con exito");
        
        }      
        
    }
        catch (SQLException e) {
            request.setAttribute("mensaje", "Error al ingresar factura: " + e.getMessage());
            throw new ServletException("Error al ingresar factura", e);
        }
        request.getRequestDispatcher("vista/resultado_ingresar_venta.jsp").forward(request, response);
    }
}
