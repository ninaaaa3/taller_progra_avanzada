<form action="Servlet" method="post">
    <input type="hidden" name="accion" value="registrarVenta">
    <label for="cliente">Cliente:</label>
    <input type="number" name="cliente" required><br>
    <label for="fecha">Fecha:</label>
    <input type="date" name="fecha" required><br>
    <h3>Detalles de Venta</h3>
    <div id="articulos">
        <div>
            <label for="articulo">Art�culo:</label>
            <input type="number" name="articulo[]" required>
            <label for="cantidad">Cantidad:</label>
            <input type="number" name="cantidad[]" required>
        </div>
    </div>
    <button type="button" onclick="agregarLinea()">Agregar L�nea</button><br>
    <button type="submit">Registrar Venta</button>
</form>
<script>
    function agregarLinea() {
        const div = document.createElement("div");
        div.innerHTML = `
            <label for="articulo">Art�culo:</label>
            <input type="number" name="articulo[]" required>
            <label for="cantidad">Cantidad:</label>
            <input type="number" name="cantidad[]" required>
        `;
        document.getElementById("articulos").appendChild(div);
    }
</script>
