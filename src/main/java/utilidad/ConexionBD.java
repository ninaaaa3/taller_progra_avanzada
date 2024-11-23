/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

/**
 *
 * @author kbarr
 */
public class ConexionBD {

    private static ConexionBD instance;
    private Connection conexion;
    private static final String ruta = "jdbc:sqlite:C:/Users/kbarr/Desktop/taller_progra_avanzada/taller_progra.db";

    private ConexionBD() throws SQLException {

        try {
            System.out.println("Conectando a la base de datos en: " + new java.io.File(ruta).getAbsolutePath());
            Class.forName("org.sqlite.JDBC");
            this.conexion = DriverManager.getConnection(ruta);
        } catch (ClassNotFoundException e) {
            throw new SQLException("Error con el uso de JDBC con SQLITE", e);
        } catch (SQLTimeoutException e) {
            e.printStackTrace();
            throw new SQLException("Tiempo de espera agotado al cenectar con base de datos", e);
        } catch (SQLException e) {
            e.printStackTrace();
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
}
