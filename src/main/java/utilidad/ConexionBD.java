package utilidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

public class ConexionBD {

    private static ConexionBD instance;
    private Connection conexion;
    private static final String ruta = System.getProperty("user.dir") + "/" + "taller_progra.db";

    private ConexionBD() throws SQLException {
        try {
            System.out.println("Conectando a la base de datos en: " + ruta);
            Class.forName("org.sqlite.JDBC");
            this.conexion = DriverManager.getConnection("jdbc:sqlite:" + ruta);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el controlador JDBC para SQLite", e);
        } catch (SQLTimeoutException e) {
            throw new SQLException("Tiempo de espera agotado al conectar con la base de datos", e);
        } catch (SQLException e) {
            throw new SQLException("Error de acceso a la base de datos", e);
        }
    }

    public static ConexionBD getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConexionBD();
        }
        return instance;
    }

    public Connection getConexion() {
        return conexion;
    }

    public void closeConexion() {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


