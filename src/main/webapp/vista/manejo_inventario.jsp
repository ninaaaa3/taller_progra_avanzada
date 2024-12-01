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
            <h1 class="title has-text-centered mb-5 has-text-primary">Manejo de Inventario</h1>
            
            <div class="card">
                <div class="card-content">
                    <form action="servletProductos" method="GET">
                        <div class="buttons is-centered">
                            <button 
                                name="accion" 
                                value="agregar" 
                                class="button is-primary is-outlined is-medium">
                                Crear Nuevo Producto
                            </button>
                            <button 
                                name="accion" 
                                value="lista_actualizar_productos" 
                                class="button is-danger is-outlined is-medium">
                                Actualizar Producto
                            </button>
                            <button 
                                name="accion" 
                                value="lista_eliminar_articulos" 
                                class="button is-warning is-outlined is-medium">
                                Borrar producto
                            </button>
                            <button 
                                name="accion" 
                                value="volver" 
                                class="button is-link is-outlined is-medium">
                                Volver al Menú Principal
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>


