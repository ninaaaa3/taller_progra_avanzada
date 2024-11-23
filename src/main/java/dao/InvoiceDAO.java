package dao;

import modelo.Invoice;
import modelo.InvoiceLine;
import utilidad.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO {

    public int registrarFactura(Invoice factura, List<InvoiceLine> lineas) throws SQLException {
        String queryFactura = "INSERT INTO Invoice (CustomerID, InvoiceDate, Total, PaymentCurrency, Status) VALUES (?, ?, ?, ?, ?)";
        String queryLinea = "INSERT INTO InvoiceLine (InvoiceID, TrackID, Quantity, LineTotal, CurrencyType, Discount) VALUES (?, ?, ?, ?, ?, ?)";

        int invoiceId;

        try (Connection connection = ConexionBD.getInstance().getConexion()) {
            connection.setAutoCommit(false);

            try (PreparedStatement statementFactura = connection.prepareStatement(queryFactura, Statement.RETURN_GENERATED_KEYS)) {
                statementFactura.setInt(1, factura.getCustomerID());
                statementFactura.setString(2, factura.getInvoiceDate());
                statementFactura.setDouble(3, factura.getTotal());
                statementFactura.setString(4, factura.getPaymentCurrency());
                statementFactura.setString(5, factura.getStatus());
                statementFactura.executeUpdate();

                ResultSet generatedKeys = statementFactura.getGeneratedKeys();
                if (generatedKeys.next()) {
                    invoiceId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID de la factura generada.");
                }
            }

            try (PreparedStatement statementLinea = connection.prepareStatement(queryLinea)) {
                for (InvoiceLine linea : lineas) {
                    statementLinea.setInt(1, invoiceId);
                    statementLinea.setInt(2, linea.getTrackID());
                    statementLinea.setInt(3, linea.getQuantity());
                    statementLinea.setDouble(4, linea.getLineTotal());
                    statementLinea.setString(5, linea.getCurrencyType());
                    statementLinea.setDouble(6, linea.getDiscount());
                    statementLinea.addBatch();
                }
                statementLinea.executeBatch();
            }

            connection.commit();
            return invoiceId;

        } catch (SQLException e) {
            throw new SQLException("Error al registrar la factura y sus líneas", e);
        }
    }

    public List<Invoice> obtenerFacturas() throws SQLException {
        String query = "SELECT * FROM Invoice";
        List<Invoice> facturas = new ArrayList<>();

        try (Connection connection = ConexionBD.getInstance().getConexion();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Invoice factura = new Invoice();
                factura.setInvoiceID(resultSet.getInt("InvoiceID"));
                factura.setCustomerID(resultSet.getInt("CustomerID"));
                factura.setInvoiceDate(resultSet.getString("InvoiceDate"));
                factura.setTotal(resultSet.getDouble("Total"));
                factura.setPaymentCurrency(resultSet.getString("PaymentCurrency"));
                factura.setStatus(resultSet.getString("Status"));
                facturas.add(factura);
            }
        }
        return facturas;
    }
}
