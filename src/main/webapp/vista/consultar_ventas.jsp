<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Cliente</th>
            <th>Fecha</th>
            <th>Total</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<Invoice> facturas = (List<Invoice>) request.getAttribute("facturas");
            if (facturas != null) {
                for (Invoice factura : facturas) {
        %>
        <tr>
            <td><%= factura.getInvoiceID() %></td>
            <td><%= factura.getCustomerID() %></td>
            <td><%= factura.getInvoiceDate() %></td>
            <td><%= factura.getTotal() %></td>
        </tr>
        <%
                }
            }
        %>
    </tbody>
</table>
