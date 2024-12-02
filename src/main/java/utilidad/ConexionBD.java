package utilidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {

    private static ConexionBD instance;
    private Connection conexion;
    private static final String ruta = "jdbc:sqlite:C:\\Users\\kbarr\\Desktop\\taller_progra_avanzada\\taller_progra.db";

    private ConexionBD() throws SQLException {
        inicializarConexion();
    }

    private void inicializarConexion() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            this.conexion = DriverManager.getConnection(ruta);
            System.out.println("Conexión inicializada exitosamente.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el driver JDBC de SQLite", e);
        } catch (SQLException e) {
            throw new SQLException("Error al establecer conexión con la base de datos", e);
        }
    }

    public static synchronized ConexionBD getInstance() throws SQLException {
        if (instance == null) {
            instance = new ConexionBD();
        }
        return instance;
    }

    public Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            System.out.println("La conexión estaba cerrada. Reestableciendo conexión...");
            inicializarConexion();
        } else {
            System.out.println("Reutilizando conexión existente.");
        }
        return conexion;
    }

    public void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
            System.out.println("Conexión cerrada exitosamente.");
        }
    }
}

