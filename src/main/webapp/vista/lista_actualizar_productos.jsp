<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Lista de Productos</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    </head>
    <body>
        <section class="section">
            <div class="container">
                <h1 class="title">Lista de Productos</h1>

                <c:if test="${not empty mensaje}">
                    <div class="notification is-danger is-light">
                        ${mensaje}
                    </div>
                </c:if>

                <table class="table is-striped is-hoverable is-fullwidth">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Precio Unitario</th>
                            <th>Stock</th>
                            <th>Categoría</th>
                            <th>Moneda</th>
                            <th>Actualizar</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="articulo" items="${lista_de_articulos}">
                            <tr>
                                <td>${articulo.trackID}</td>
                                <td>${articulo.trackName}</td>
                                <td>${articulo.description}</td>
                                <td>${articulo.unitPrice}</td>
                                <td>${articulo.stock}</td>
                                <td>${articulo.category}</td>
                                <td>${articulo.currencyType}</td>
                                <td>
                                    <form action="servletProductos" method="get">
                                        <input type="hidden" name="trackID" value="${articulo.trackID}" />
                                        <button name="accion" value="actualizar">Actualizar</button>
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