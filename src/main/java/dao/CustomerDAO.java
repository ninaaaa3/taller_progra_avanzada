package dao;

import modelo.Customer;
import utilidad.ConexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    public List<Customer> listarClientes() throws SQLException {
        List<Customer> clientes = new ArrayList<>();
        String sql = "SELECT * FROM Customer";

        try (Connection conn = ConexionBD.getInstance().getConexion(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Customer cliente = new Customer();
                cliente.setCustomerId(rs.getInt("CustomerID"));
                cliente.setCustomerName(rs.getString("CustomerName"));
                cliente.setCustomerPhone(rs.getString("CustomerPhone"));
                cliente.setCustomerEmail(rs.getString("CustomerEmail"));
                cliente.setCustomerAddress(rs.getString("CustomerAddress"));
                clientes.add(cliente);
            }
        }
        return clientes;
    }
}