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

                <div class="notification is-primary has-text-centered">
                    ${mensaje}
                </div>

                <form action="servletProductos" method="get" class="box">
                    <div class="has-text-centered">
                        <button name="accion" value="volver">Volver al menú</button>
                    </div>
                </form>
            </div>
        </section>
    </body>
</html>
