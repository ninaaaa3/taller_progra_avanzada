<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
<head>
    <title>Agregar Artículo</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.9.3/css/bulma.min.css">
</head>
<body>
    <section class="section">
        <div class="container">
            <h1 class="title has-text-centered">Agregar Nuevo Artículo</h1>
            <form action="servletProductos" method="post" class="box">
                <input type="hidden" name="accion" value="agregar">

                <div class="field">
                    <label class="label">Nombre del Artículo</label>
                    <div class="control">
                        <input class="input" type="text" name="trackName" placeholder="Ingrese el nombre del artículo" required>
                    </div>
                </div>

                <div class="field">
                    <label class="label">Descripción</label>
                    <div class="control">
                        <input class="input" type="text" name="description" placeholder="Ingrese una descripción" required>
                    </div>
                </div>

                <div class="field">
                    <label class="label">Precio Unitario</label>
                    <div class="control">
                        <input class="input" type="number" name="unitPrice" placeholder="Ingrese el precio unitario" required>
                    </div>
                </div>

                <div class="field">
                    <label class="label">Stock</label>
                    <div class="control">
                        <input class="input" type="number" name="stock" placeholder="Ingrese el stock" required>
                    </div>
                </div>

                <div class="field">
                    <label class="label">Categoría</label>
                    <div class="control">
                        <input class="input" type="text" name="category" placeholder="Ingrese la categoría" required>
                    </div>
                </div>

                <div class="field">
                    <div class="control has-text-centered">
                        <button class="button is-primary">Agregar</button>
                    </div>
                </div>
            </form>
        </div>
    </section>
</body>
</html>


