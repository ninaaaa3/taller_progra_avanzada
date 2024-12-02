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
                <div class="box">
                    <h1 class="title has-text-centered">Factura #${invoiceID}</h1>
                    <div class="columns">
                        <div class="column is-half">
                            <p><strong>ID Cliente:</strong> ${customerID}</p>
                            <p><strong>Fecha:</strong> ${invoiceDate}</p>
                        </div>
                        <div class="column is-half">
                            <p><strong>Moneda de Pago:</strong> ${paymentCurrency}</p>
                            <p><strong>Estado de la Factura:</strong> ${status}</p>
                        </div>
                    </div>
                </div>

                <div class="box">
                    <h2 class="subtitle">Detalle de la Venta</h2>
                    <table class="table is-striped is-fullwidth">
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
                </div>

                <div class="box">
                    <h2 class="subtitle">Totales</h2>
                    <p><strong>Total en CLP:</strong> ${totalCLP}</p>
                </div>

                <div class="has-text-centered">
                    <form action="Servlet" method="get">
                        <button class="button is-primary" name="accion" value="volver">Volver al menú</button>
                    </form>
                </div>
            </div>
        </section>
    </body>
</html>
