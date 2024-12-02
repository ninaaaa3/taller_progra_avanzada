package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utilidad.ConexionBD;
import modelo.Factura;

public class InvoiceDAO {

    public int agregarFactura(Factura factura) throws SQLException {
        String insert = "INSERT INTO Invoice (CustomerID, PaymentCurrency, Total_CLP, Status) VALUES (?, ?, ?, ?);";
        int invoiceId = -1; 

        try (Connection connection = ConexionBD.getInstance().getConexion(); PreparedStatement statement = connection.prepareStatement(insert, PreparedStatement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, factura.getCustomerID());
            statement.setString(2, factura.getPaymentCurrency());
            statement.setFloat(3, factura.getTotal_CLP());
            statement.setString(4, factura.getStatus());

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        invoiceId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al agregar factura en dao: " + e.getMessage());
            throw e;
        }

        return invoiceId;
    }

    public Factura obtenerFacturaPorID(int id) throws SQLException {
        String query = "SELECT * FROM Invoice WHERE InvoiceID = ?;";

        try (Connection connection = ConexionBD.getInstance().getConexion(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Factura factura = new Factura();
                    factura.setInvoiceID(resultSet.getInt("InvoiceID"));
                    factura.setCustomerID(resultSet.getInt("CustomerID"));
                    factura.setInvoiceDate(resultSet.getString("InvoiceDate"));
                    factura.setPaymentCurrency(resultSet.getString("PaymentCurrency"));
                    factura.setTotal_CLP(resultSet.getFloat("Total_CLP"));
                    factura.setStatus(resultSet.getString("Status"));
                    return factura;
                }
            }
        } catch (Exception e) {
            System.err.println("Error al obtener factura en FacturaDAO: " + e);
            throw e;
        }
        return null;
    }

    public boolean actualizarFactura(Factura factura) throws SQLException {
        String update = "UPDATE Invoice SET Total_CLP = ?, Status = ? WHERE InvoiceID = ?";
        boolean resultado = false;
        try (Connection connection = ConexionBD.getInstance().getConexion(); PreparedStatement statement = connection.prepareStatement(update)) {
            statement.setFloat(1, factura.getTotal_CLP());
            statement.setString(2, factura.getStatus());

            resultado = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar factura en FacturaDAO: " + e);
            throw e;
        }
        return resultado;
    }

    public void eliminarFactura(int id) throws SQLException {
        String query = "DELETE FROM Invoice WHERE InvoiceID = ?";
        try (Connection connection = ConexionBD.getInstance().getConexion(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al eliminar factura en FacturaDAO: " + e);
            throw e;
        }
    }

    public List<Factura> obtenerTodasLasFacturas() throws SQLException {
        String query = "SELECT * FROM Invoice";
        List<Factura> listaFacturas = new ArrayList<>();
        try {

            Connection connection = ConexionBD.getInstance().getConexion();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Factura factura = new Factura();

                factura.setInvoiceID(resultSet.getInt("InvoiceID"));
                factura.setCustomerID(resultSet.getInt("CustomerID"));
                factura.setInvoiceDate(resultSet.getString("InvoiceDate"));
                factura.setPaymentCurrency(resultSet.getString("PaymentCurrency"));
                factura.setTotal_CLP(resultSet.getFloat("Total_CLP"));
                factura.setStatus(resultSet.getString("Status"));

                listaFacturas.add(factura);
            }
        } catch (SQLException e) {
            System.out.println("Ocurrió un error al obtener la lista de Facturas en FacturasDAO: " + e);
            throw e;
        }
        return listaFacturas;
    }

    public void actualizarTotalFactura(int invoiceId, double totalFactura) throws SQLException {
        String query = "UPDATE Invoice SET Total_CLP = ? WHERE InvoiceID = ?";
        try (Connection connection = ConexionBD.getInstance().getConexion(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDouble(1, totalFactura);
            statement.setInt(2, invoiceId);   
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al actualizar el total de la factura en InvoiceDAO: " + e.getMessage());
            throw e;
        }
    }
    
    public int obtenerUltimaFacturaID() throws SQLException {
    String query = "SELECT InvoiceID FROM Invoice ORDER BY InvoiceID DESC LIMIT 1;";
    int lastInvoiceID = -1;

    try (Connection connection = ConexionBD.getInstance().getConexion(); 
         PreparedStatement statement = connection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery()) {

        if (resultSet.next()) {
            lastInvoiceID = resultSet.getInt("InvoiceID");
        }

    } catch (SQLException e) {
        System.err.println("Error al obtener el ID de la última factura: " + e.getMessage());
        throw e;
    }

    return lastInvoiceID;
}

}
