package dao;

import modelo.InvoiceLine;
import utilidad.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InvoiceLineDAO {
    public void insertarDetalle(InvoiceLine detalle) throws SQLException {
        String sql = "INSERT INTO InvoiceLine (InvoiceID, TrackID, Quantity, UnitPrice, LineTotal) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.getInstance().getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, detalle.getInvoiceId());
            stmt.setInt(2, detalle.getTrackId());
            stmt.setInt(3, detalle.getQuantity());
            stmt.setDouble(4, detalle.getUnitPrice());
            stmt.setDouble(5, detalle.getLineTotal());

            stmt.executeUpdate();
        }
    }
}