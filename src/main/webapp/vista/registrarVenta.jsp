<%@page import="java.util.Map"%>
<%@page import="modelo.Customer"%>
<%@page import="modelo.Articulo"%>
<%@page import="java.util.List"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registrar Venta</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container mt-5">
            <h1>Registrar Venta</h1>
            <form action="servletVentas" method="post">
                <div class="mb-3">
                    <label for="customerId" class="form-label">Cliente</label>
                    <select class="form-select" id="customerId" name="customerId" required>
                        <%
                            List<Customer> clientes = (List<Customer>) request.getAttribute("clientes");
                            if (clientes != null && !clientes.isEmpty()) {
                                for (Customer cliente : clientes) {
                        %>
                        <option value="<%= cliente.getCustomerId()%>"><%= cliente.getCustomerName()%></option>
                        <%
                            }
                        } else {
                        %>
                        <option value="" disabled>No hay clientes disponibles</option>
                        <%
                            }
                        %>
                    </select>
                </div>
                <div class="mb-3">
                    <label for="paymentCurrency" class="form-label">Moneda de Pago</label>
                    <select id="paymentCurrency" name="paymentCurrency" class="form-select" onchange="updateTotalInSelectedCurrency()" required>
                        <option value="CLP" selected>CLP</option>
                        <option value="USD">USD</option>
                        <option value="EUR">EUR</option>
                    </select>
                </div>

                <div id="products">
                    <h4>Productos</h4>
                </div>
                <button type="button" id="addProduct" class="btn btn-secondary mb-3">Agregar Producto</button>

                <div class="mb-3">
                    <p>Total en CLP: <span id="totalCLP">0.00</span> CLP</p>
                </div>

                <div id="otherCurrencyContainer" style="display: none;">
                    Total en <span id="selectedCurrency"></span>: 
                    <span id="totalInSelectedCurrency"></span> <span id="currencySymbol"></span>
                </div>

                <input type="hidden" id="hiddenTotalCLP" name="totalCLP" value="0.00">

                <button type="submit" class="btn btn-primary" name="accion" value="registrar_venta_bd">Registrar Venta</button>
            </form>
        </div>
        <script>
            let exchangeRates = <%= new com.google.gson.Gson().toJson(request.getAttribute("exchangeRates"))%>;

            function updateTotal() {
                let totalCLP = 0;

                document.querySelectorAll('.line-total').forEach(input => {
                    totalCLP += parseFloat(input.value || 0);
                });

                document.getElementById('totalCLP').textContent = totalCLP.toFixed(2);

                document.getElementById('hiddenTotalCLP').value = totalCLP.toFixed(2);

                updateTotalInSelectedCurrency();
            }

            function addProductRow() {
                const products = document.getElementById('products');
                const row = document.createElement('div');
                row.classList.add('row', 'g-3', 'align-items-center', 'mb-3');

                const index = document.querySelectorAll('.row.g-3').length;

                row.innerHTML = `
                    <div class="col-md-4">
                        <label class="form-label">Producto</label>
                        <select class="form-select track-select" name="productId" required>
                            <option value="" data-price="0" data-currency="" disabled selected>Seleccione un producto</option>
                        <%
                            List<Articulo> productos = (List<Articulo>) request.getAttribute("productos");
                            if (productos != null && !productos.isEmpty()) {
                                for (Articulo producto : productos) {
                        %>
                            <option value="<%= producto.getTrackID()%>" 
                                    data-price="<%= producto.getUnitPrice()%>" 
                                    data-currency="<%= producto.getCurrencyType()%>">
                        <%= producto.getTrackName()%> - <%= producto.getUnitPrice()%> - <%= producto.getCurrencyType()%>
                            </option>
                        <%
                            }
                        } else {
                        %>
                            <option value="" disabled>No hay productos disponibles</option>
                        <%
                            }
                        %>
                        </select>
                    </div>
                    <div class="col-md-2">
                        <label class="form-label">Cantidad</label>
                        <input type="number" class="form-control quantity-input" name="quantity" min="1" value="1" required>
                    </div>
                    <div class="col-md-3">
                        <label class="form-label">Subtotal (CLP)</label>
                        <input type="text" class="form-control line-total" name="lineTotal" value="0" readonly>
                    </div>
                    <div class="col-md-3">
                        <button type="button" class="btn btn-danger remove-product">Eliminar</button>
                    </div>
                `;

                products.appendChild(row);

                row.querySelector('.track-select').addEventListener('change', updateLineTotal);
                row.querySelector('.quantity-input').addEventListener('input', updateLineTotal);
                row.querySelector('.remove-product').addEventListener('click', () => {
                    row.remove();
                    updateTotal();
                });
            }


            function updateLineTotal(event) {
                const row = event.target.closest('.row');
                const trackSelect = row.querySelector('.track-select');
                const quantityInput = row.querySelector('.quantity-input');
                const lineTotalInput = row.querySelector('.line-total');

                const price = parseFloat(trackSelect.selectedOptions[0].dataset.price || 0);
                const currency = trackSelect.selectedOptions[0].dataset.currency;
                const quantity = parseInt(quantityInput.value || 1);

                const priceInCLP = price / (exchangeRates[currency] || 1);

                const lineTotal = priceInCLP * quantity;

                lineTotalInput.value = lineTotal.toFixed(2);

                updateTotal();
            }


            function updateTotalInSelectedCurrency() {
                const paymentCurrency = document.getElementById('paymentCurrency').value;
                const totalCLP = parseFloat(document.getElementById('totalCLP').textContent || 0);

                const exchangeRates = JSON.parse('<%= new com.google.gson.Gson().toJson((Map<String, Double>) request.getAttribute("exchangeRates"))%>');

                if (paymentCurrency !== "CLP") {
                    const rate = exchangeRates[paymentCurrency] || 1;
                    const totalInCurrency = totalCLP * rate;

                    document.getElementById('selectedCurrency').textContent = paymentCurrency;
                    document.getElementById('currencySymbol').textContent = paymentCurrency;
                    document.getElementById('totalInSelectedCurrency').textContent = totalInCurrency.toFixed(2);

                    document.getElementById('otherCurrencyContainer').style.display = "block";
                } else {
                    document.getElementById('otherCurrencyContainer').style.display = "none";
                }
            }

            document.getElementById('paymentCurrency').addEventListener('change', updateTotalInSelectedCurrency);

            document.getElementById('paymentCurrency').addEventListener('change', updateTotalInSelectedCurrency);

            document.addEventListener('DOMContentLoaded', () => {
                document.getElementById('addProduct').addEventListener('click', function () {
                    addProductRow();
                });
            });
        </script>
    </body>
</html>
