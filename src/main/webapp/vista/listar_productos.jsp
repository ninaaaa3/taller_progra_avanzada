<%-- 
    Document   : listar_productos
    Created on : 28-11-2024, 13:04:41
    Author     : kbarr
--%>

<%@page import="modelo.Articulo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    </head>
    <body>
        <h1>Lista de productos</h1>
        <%
            List<Articulo> articulo = (List) request.getSession().getAttribute("articulos");
            int count = 1;
            for (Articulo i : articulo) {
        %>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Artículo</th>
                    <th>Descripción</th>
                    <th>Precio unitario</th>
                    <th>Stock</th>
                    <th>Categoría</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="producto" items="${lista_de_articulos}">
                    <tr>
                        <td>${producto.trackID}</td>
                        <td>${producto.trackName}</td>
                        <td>${producto.description}</td>
                        <td>${producto.unitPrice}</td>
                        <td>${producto.stock}</td>
                        <td>${producto.category}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty lista_de_articulos}">
                    <tr>
                        <td colspan="6">No se encontraron productos.</td>
                    </tr>
                </c:if> 
            </tbody>


            <%}%>


        </table>
    </body>
</html>
