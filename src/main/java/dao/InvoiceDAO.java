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
    
    public boolean agregarFactura (Factura factura) throws SQLException {
        String insert = "INSERT INTO Invoice(CustomerID,PaymentCurrency,Total_CLP,Status) VALUES (?,?,?,?);";
        boolean resultado = false;
        try (Connection connection = ConexionBD.getInstance().getConexion(); 
             PreparedStatement statement = connection.prepareStatement(insert))
        {
            statement.setInt(1,factura.getCustomerID());
            statement.setString(2,factura.getPaymentCurrency());
            statement.setFloat(3,factura.getTotal_CLP());
            statement.setString(4,factura.getStatus());
            
            resultado = statement.executeUpdate() > 0;
        }
        catch(SQLException e){
            System.err.println("Error al agregar factura en dao"+e);
            throw e;
        }
        return resultado;
    }
    
    public Factura obtenerFacturaPorID (int id) throws SQLException {
        String query = "SELECT * FROM Invoice WHERE InvoiceID = ?;";
        
        try(Connection connection = ConexionBD.getInstance().getConexion(); 
            PreparedStatement statement = connection.prepareStatement(query)) 
        {
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
            System.err.println("Error al obtener factura en FacturaDAO: "+e);
            throw e;
        }
        return null;
    }
}
