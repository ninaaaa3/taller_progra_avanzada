<%-- 
    Document   : agregar_articulo
    Created on : 22-11-2024, 18:47:26
    Author     : kbarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Artículo</title>
</head>
<body>
    <h1>Agregar Nuevo Artículo</h1>
    <form action="Servlet" method="post">
        <input type="hidden" name="accion" value="agregar">
        Nombre del Artículo: <input type="text" name="trackName"><br>
        Descripción: <input type="text" name="description"><br>
        Precio Unitario: <input type="text" name="unitPrice"><br>
        Stock: <input type="number" name="stock"><br>
        Categoría: <input type="text" name="category"><br>
        Tipo de Moneda: <input type="text" name="currencyType"><br>
        <input type="submit" value="Agregar">
    </form>
</body>
</html>

