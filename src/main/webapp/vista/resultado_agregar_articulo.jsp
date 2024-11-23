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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title has-text-centered">Resultado de la Operación</h1>

            <!-- Mostrar el mensaje pasado desde el servlet -->
            <div class="notification is-primary has-text-centered">
                ${mensaje}
            </div>

            <!-- Botón para regresar al formulario principal -->
            <div class="has-text-centered">
                <a href="Servlet" class="button is-link is-light">Volver al formulario</a>
            </div>
        </div>
    </section>
</body>
</html>
