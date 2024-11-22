package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Articulo;
import utilidad.ConexionBD;

public class ArticuloDAO implements ArticuloInterface {

    @Override
    public boolean agregarArticulo(Articulo articulo) throws SQLException {
        String query = "INSERT INTO Track (trackName, description, unitPrice, stock, category, currencyType) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = ConexionBD.getInstance().getConexion();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, articulo.getTrackName());
            statement.setString(2, articulo.getDescription());
            statement.setDouble(3, articulo.getUnitPrice());
            statement.setInt(4, articulo.getStock());
            statement.setString(5, articulo.getCategory());
            statement.setString(6, articulo.getCurrencyType());
            return statement.executeUpdate() > 0;
        }
    }

    @Override
    public Articulo obtenerArticuloPorId(int id) throws SQLException {
        String query = "SELECT * FROM Track WHERE trackID = ?";
        try (Connection connection = ConexionBD.getInstance().getConexion();
             PreparedStatement statement = connection.prepareStatement(query)) {
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
                    articulo.setCurrencyType(resultSet.getString("currencyType"));
                    return articulo;
                }
            }
        }
        return null;
    }

    @Override
    public void actualizarArticulo(Articulo articulo) throws SQLException {
        String query = "UPDATE Track SET trackName = ?, description = ?, unitPrice = ?, stock = ?, category = ?, currencyType = ? WHERE trackID = ?";
        try (Connection connection = ConexionBD.getInstance().getConexion();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, articulo.getTrackName());
            statement.setString(2, articulo.getDescription());
            statement.setDouble(3, articulo.getUnitPrice());
            statement.setInt(4, articulo.getStock());
            statement.setString(5, articulo.getCategory());
            statement.setString(6, articulo.getCurrencyType());
            statement.setInt(7, articulo.getTrackID());
            statement.executeUpdate();
        }
    }

    @Override
    public void eliminarArticulo(int id) throws SQLException {
        String query = "DELETE FROM Track WHERE trackID = ?";
        try (Connection connection = ConexionBD.getInstance().getConexion();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public List<Articulo> obtenerTodosLosArticulos() throws SQLException {
        String query = "SELECT * FROM Track";
        List<Articulo> listaArticulos = new ArrayList<>();
        try (Connection connection = ConexionBD.getInstance().getConexion();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Articulo articulo = new Articulo();
                articulo.setTrackID(resultSet.getInt("trackID"));
                articulo.setTrackName(resultSet.getString("trackName"));
                articulo.setDescription(resultSet.getString("description"));
                articulo.setUnitPrice(resultSet.getDouble("unitPrice"));
                articulo.setStock(resultSet.getInt("stock"));
                articulo.setCategory(resultSet.getString("category"));
                articulo.setCurrencyType(resultSet.getString("currencyType"));
                listaArticulos.add(articulo);
            }
        }
        return listaArticulos;
    }
}
