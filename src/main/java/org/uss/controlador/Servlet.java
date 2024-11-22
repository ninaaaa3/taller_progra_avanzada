/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package org.uss.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utilidad.ConexionBD;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author kbarr
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Probar conexión</title>");
            out.println("</head>");
            out.println("<body>");

            // Intentar conectar a la base de datos
            try {
                ConexionBD conexionBD = ConexionBD.getInstance();
                Connection connection = conexionBD.getConexion();

                if (connection != null && !connection.isClosed()) {
                    out.println("<h1>¡Conexión exitosa a la base de datos!</h1>");
                } else {
                    out.println("<h1>No se pudo establecer la conexión a la base de datos.</h1>");
                }
            } catch (SQLException e) {
                out.println("<h1>Error al conectar con la base de datos</h1>");
                out.println("<p>Detalles del error: " + e.getMessage() + "</p>");
                e.printStackTrace(out);
            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para probar la conexión a la base de datos";
    }
}
