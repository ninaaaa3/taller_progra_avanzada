<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Detalle de Venta</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    </head>
    <body>
        <section class="section">
            <div class="container">
                <h1 class="title">Detalle de la Venta #${invoiceID}</h1>

                <c:if test="${not empty mensaje}">
                    <div class="notification is-danger is-light">
                        ${mensaje}
                    </div>
                </c:if>

                <div class="box">
                    <h2 class="subtitle">Detalles de la Factura</h2>
                    <p><strong>ID cliente:</strong> ${customerID}</p>
                    <p><strong>Fecha de la Factura:</strong> ${invoiceDate}</p>
                    <p><strong>Moneda de Pago:</strong> ${paymentCurrency}</p>
                </div>

                <!-- Botón de regreso -->
                <a href="invoices.jsp" class="button is-link">Regresar al listado de ventas</a>

                <!-- Tabla de las líneas de la factura -->
                <table class="table is-striped is-hoverable is-fullwidth">
                    <thead>
                        <tr>
                            <th>ID Línea</th>
                            <th>Producto</th>
                            <th>Cantidad</th>
                            <th>Precio Unitario</th>
                            <th>Total Línea</th>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Mostrar cada línea de la factura -->
                        <c:forEach var="line" items="${invoiceLines}">
                            <tr>
                                <td>${line.InvoiceLineID}</td>
                                <td>${line.TrackName}</td>
                                <td>${line.Quantity}</td>
                                <td>${line.UnitPrice}</td>
                                <td>${line.LineTotal}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <!-- Pie de la factura -->
                <div class="box">
                    <h2 class="subtitle">Totales</h2>
                    <p><strong>Total en CLP:</strong> ${totalCLP}</p>

                    <c:if test="${not empty totalForeign}">
                        <p><strong>Total en ${paymentCurrency}:</strong> ${totalForeign}</p>
                    </c:if>

                    <p><strong>Estado de la Factura:</strong> ${status}</p>
                </div>
            </div>
        </section>
    </body>
</html>
