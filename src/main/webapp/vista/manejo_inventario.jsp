<%-- 
    Document   : manejo_inventario
    Created on : 23-11-2024, 00:53:08
    Author     : kbarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manejo de Inventario</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title has-text-centered mb-5">Manejo de Inventario</h1>
            <div class="columns is-multiline">
                <div class="column is-one-third">
                    <a href="agregar_articulo.jsp" class="button is-primary is-fullwidth">Crear Nuevo Producto</a>
                </div>
                <div class="column is-one-third">
                    <button class="button is-danger is-fullwidth">Borrar Productos</button>
                </div>
                <div class="column is-one-third">
                    <button class="button is-warning is-fullwidth">Actualizar Productos</button>
                </div>
            </div>
            <div class="has-text-centered mt-5">
                <a href="menu_principal.jsp" class="button is-secondary">Volver al Menú Principal</a>
            </div>
        </div>
    </section>
</body>
</html>

