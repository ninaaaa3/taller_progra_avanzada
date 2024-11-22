<%-- 
    Document   : resultado_agregar_articulo
    Created on : 22-11-2024, 18:58:34
    Author     : kbarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Resultado de la Operación</title>
</head>
<body>
    <h1>Resultado de la Operación</h1>

    <!-- Mostrar el mensaje pasado desde el servlet -->
    <p>
        ${mensaje}
    </p>

    <!-- Botón para regresar al formulario principal -->
    <a href="Servlet">Volver al formulario</a>
</body>
</html>