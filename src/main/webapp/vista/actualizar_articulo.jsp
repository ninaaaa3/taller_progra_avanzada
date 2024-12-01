<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Actualizar Producto</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
    </head>
    <body>
        <section class="section">
            <div class="container">
                <h1 class="title">Actualizar Producto</h1>

                <form action="servletProductos" method="post">
                    <input type="hidden" name="trackID" value="${articulo.trackID}" />
                    <div class="field">
                        <label class="label">Nombre</label>
                        <div class="control">
                            <input class="input" type="text" name="trackName" value="${articulo.trackName}" required />
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Descripción</label>
                        <div class="control">
                            <input class="input" type="text" name="description" value="${articulo.description}" required />
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Precio Unitario</label>
                        <div class="control">
                            <input class="input" type="number" name="unitPrice" value="${articulo.unitPrice}" required />
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Stock</label>
                        <div class="control">
                            <input class="input" type="number" name="stock" value="${articulo.stock}" required />
                        </div>
                    </div>
                    <div class="field">
                        <label class="label">Categoría</label>
                        <div class="control">
                            <input class="input" type="text" name="category" value="${articulo.category}" required />
                        </div>
                    </div>

                    <div class="control">
                        <button class="button is-primary" type="submit" name="accion" value="guardarActualizacion">Guardar Cambios</button>
                    </div>
                </form>
            </div>
        </section>
    </body>
</html>
