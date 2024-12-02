package dao;

import modelo.InvoiceLine;
import utilidad.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.DetalleFactura;

public class InvoiceLineDAO {

    public void insertarDetalle(InvoiceLine detalle) throws SQLException {
        String sql = "INSERT INTO InvoiceLine (InvoiceID, TrackID, Quantity, UnitPrice, LineTotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getInstance().getConexion(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detalle.getInvoiceId());
            stmt.setInt(2, detalle.getTrackId());
            stmt.setInt(3, detalle.getQuantity());
            stmt.setDouble(4, detalle.getUnitPrice());
            stmt.setDouble(5, detalle.getLineTotal());

            stmt.executeUpdate();
        }
    }
    
    public List<DetalleFactura> obtenerDetallePorInvoiceID(int InvoiceID) throws SQLException {
        String query = "SELECT * FROM InvoiceLine WHERE InvoiceID = ?;";
        List<DetalleFactura> listaDetalleFacturas = new ArrayList<>();
        try {

            Connection connection = ConexionBD.getInstance().getConexion();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                DetalleFactura detalleFactura = new DetalleFactura();
                
                detalleFactura.setInvoiceLineID(resultSet.getInt("InvoiceLineID"));
                    detalleFactura.setInvoiceID(resultSet.getInt("InvoiceID"));
                    detalleFactura.setTrackID(resultSet.getInt("TrackID"));
                    detalleFactura.setQuantity(resultSet.getInt("Quantity"));
                    detalleFactura.setUnitPrice(resultSet.getFloat("UnitPrice"));
                    detalleFactura.setLineTotal(resultSet.getFloat("LineTotal"));
                    
                listaDetalleFacturas.add(detalleFactura);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el detalle de la factura en InvoiceLineDAO: " + e);
            throw e;
        }
        return listaDetalleFacturas;
    }
}
