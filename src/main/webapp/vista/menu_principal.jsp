<%-- 
    Document   : menu_principal
    Created on : 23-11-2024, 00:51:59
    Author     : kbarr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú Principal</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title has-text-centered mb-5">Menú Principal</h1>
            <div class="columns is-multiline">
                <div class="column is-half">
                    <a href="/taller_progra_avanzada/vista/ingreso_ventas.jsp" class="button is-primary is-fullwidth">Ingreso de Ventas</a>
                </div>
                <div class="column is-half">
                    <a href="/taller_progra_avanzada/vista/listar_ventas.jsp" class="button is-success is-fullwidth">Listar Ventas</a>
                </div>
                <div class="column is-half">
                    <a href="/taller_progra_avanzada/vista/manejo_inventario.jsp" class="button is-warning is-fullwidth">Manejo de Inventario</a>
                </div>
                <div class="column is-half">
                    <a href="/taller_progra_avanzada/vista/listar_productos.jsp" class="button is-info is-fullwidth">Listar Productos</a>
                </div>
            </div>
        </div>
    </section>
</body>
</html>
