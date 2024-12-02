<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Ventas</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    </head>
    <body>
        <section class="section">
            <div class="container">
                <h1 class="title">Lista de Ventas</h1>

                <c:if test="${not empty mensaje}">
                    <div class="notification is-danger is-light">
                        ${mensaje}
                    </div>
                </c:if>

                <table class="table is-striped is-hoverable is-fullwidth">
                    <thead>
                        <tr>
                            <th>ID factura</th>
                            <th>ID cliente</th>
                            <th>Fecha factura</th>
                            <th>Ver detalle</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="venta" items="${lista_de_facturas}">
                            <tr>
                                <td>${venta.invoiceID}</td>
                                <td>${venta.customerID}</td>
                                <td>${venta.invoiceDate}</td>
                                <td>
                                    <form action="NewServlet" method="get" class="box">
                                        <input type="hidden" name="InvoiceID" value="${venta.invoiceID}" />
                                        <button name="accion" value="detalleVenta">Ver detalle</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </section>
    </body>
</html>