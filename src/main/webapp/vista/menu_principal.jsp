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
                <div class="box has-text-centered">
                    <h1 class="title">Menú Principal</h1>
                    <h2 class="subtitle">Selecciona una opción:</h2>
                    <form action="${pageContext.request.contextPath}/Servlet" method="GET">
                        <div class="field">
                            <button type="submit" name="accion" value="manejoInventario" class="button is-primary is-fullwidth mb-2">
                                Manejo de Inventario
                            </button>
                        </div>
                        <div class="field">
                            <button type="submit" name="accion" value="listarProductos" class="button is-link is-fullwidth mb-2">
                                Listar Productos
                            </button>
                        </div>
                        </form>
                        <form action="servletVentas" method="GET">
                        <div class="field">
                            <button type="submit" name="accion" value="registrarVenta" class="button is-success is-fullwidth mb-2">
                                Ingresar Venta
                            </button>
                        </div>
                        <div class="field">
                            <button type="submit" name="accion" value="listarVentas" class="button is-danger is-fullwidth mb-2">
                                Listar Ventas
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </section>
    </body>
</html>
