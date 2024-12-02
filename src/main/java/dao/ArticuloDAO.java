package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Articulo;
import utilidad.ConexionBD;

public class ArticuloDAO {
    public boolean agregarArticulo(Articulo articulo) throws SQLException {
        String query = "INSERT INTO Track (TrackName, Description, UnitPrice, Stock, Category, CurrencyType) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConexionBD.getInstance().getConexion(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, articulo.getTrackName());
            statement.setString(2, articulo.getDescription());
            statement.setDouble(3, articulo.getUnitPrice());
            statement.setInt(4, articulo.getStock());
            statement.setString(5, articulo.getCategory());
            statement.setString(6, articulo.getCurrencyType());
            return statement.executeUpdate() > 0;
        }
    }
    public Articulo obtenerArticuloPorId(int id) throws SQLException {
        String query = "SELECT * FROM Track WHERE TrackID = ?";
        try (Connection connection = ConexionBD.getInstance().getConexion(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Articulo articulo = new Articulo();
                    articulo.setTrackID(resultSet.getInt("trackID"));
                    articulo.setTrackName(resultSet.getString("trackName"));
                    articulo.setDescription(resultSet.getString("description"));
                    articulo.setUnitPrice(resultSet.getDouble("unitPrice"));
                    articulo.setStock(resultSet.getInt("stock"));
                    articulo.setCategory(resultSet.getString("category"));
                    return articulo;
                }
            }
        }
        return null;
    }
    public void actualizarArticulo(Articulo articulo) throws SQLException {
        String query = "UPDATE Track SET TrackName = ?, Description = ?, UnitPrice = ?, Stock = ?, Category = ? WHERE TrackID = ?";
        try (Connection connection = ConexionBD.getInstance().getConexion(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, articulo.getTrackName());
            statement.setString(2, articulo.getDescription());
            statement.setDouble(3, articulo.getUnitPrice());
            statement.setInt(4, articulo.getStock());
            statement.setString(5, articulo.getCategory());
            statement.setInt(6, articulo.getTrackID());
            statement.executeUpdate();
        }
    }
    public void eliminarArticulo(int id) throws SQLException {
        String query = "DELETE FROM Track WHERE trackID = ?";
        try (Connection connection = ConexionBD.getInstance().getConexion(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
    public List<Articulo> obtenerTodosLosArticulos() throws SQLException {
        String query = "SELECT * FROM Track";
        List<Articulo> listaArticulos = new ArrayList<>();
        try {
            
            Connection connection = ConexionBD.getInstance().getConexion();
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                Articulo articulo = new Articulo();
                articulo.setTrackID(resultSet.getInt("TrackID"));
                articulo.setTrackName(resultSet.getString("TrackName"));
                articulo.setDescription(resultSet.getString("Description"));
                articulo.setUnitPrice(resultSet.getDouble("UnitPrice"));
                articulo.setStock(resultSet.getInt("Stock"));
                articulo.setCategory(resultSet.getString("Category"));
                articulo.setCurrencyType(resultSet.getString("CurrencyType"));

                listaArticulos.add(articulo);
            }
            System.out.println("Lista de artículos en dao"+listaArticulos);
        }
        catch (SQLException e) {
            System.out.println("Ocurrió un error al obtener la lista de artículos en DAO: "+e);
            throw e;
        }
        return listaArticulos;
    }
}
