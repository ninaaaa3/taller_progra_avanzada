<%@page import="modelo.InvoiceLine"%>
<%@page import="modelo.Factura"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Confirmar Venta</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1>Confirmación de Venta</h1>
        
        <div class="card mb-4">
            <div class="card-body">
                <h4>Detalles de la Factura</h4>
                <p><strong>ID de Factura:</strong> <%= request.getParameter("invoiceId") %></p>
                <p><strong>Cliente:</strong> <%= (String) request.getAttribute("customerName") %></p>
                <p><strong>Fecha:</strong> <%= (String) request.getAttribute("invoiceDate") %></p>
                <p><strong>Total (CLP):</strong> $<%= (Float) request.getAttribute("totalCLP") %></p>
                <p><strong>Estado:</strong> <%= (String) request.getAttribute("status") %></p>
            </div>
        </div>

        <h4>Productos</h4>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Producto</th>
                    <th>Cantidad</th>
                    <th>Precio Unitario (CLP)</th>
                    <th>Subtotal (CLP)</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<InvoiceLine> detalles = (List<InvoiceLine>) request.getAttribute("detalles");
                    if (detalles != null && !detalles.isEmpty()) {
                        for (InvoiceLine detalle : detalles) {
                            String producto = (String) request.getAttribute("producto" + detalle.getTrackId());
                %>
                <tr>
                    <td><%= producto %></td>
                    <td><%= detalle.getQuantity() %></td>
                    <td>$<%= detalle.getLineTotal() / detalle.getQuantity() %></td>
                    <td>$<%= detalle.getLineTotal() %></td>
                </tr>
                <% 
                        }
                    } else { 
                %>
                <tr>
                    <td colspan="4" class="text-center">No hay detalles disponibles.</td>
                </tr>
                <% 
                    } 
                %>
            </tbody>
        </table>

        <div class="mt-4">
            <a href="servletVentas" class="btn btn-primary">Volver al Registro de Ventas</a>
        </div>
    </div>
</body>
</html>
