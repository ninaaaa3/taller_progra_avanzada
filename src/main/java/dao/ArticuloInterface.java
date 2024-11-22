/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import modelo.Articulo;

/**
 *
 * @author kbarr
 */
public interface ArticuloInterface {
    public boolean agregarArticulo (Articulo articulo) throws SQLException;
    public Articulo obtenerArticuloPorId(int id) throws SQLException;
    public void actualizarArticulo(Articulo articulo) throws SQLException;
    public void eliminarArticulo(int id) throws SQLException;
    public List<Articulo> obtenerTodosLosArticulos() throws SQLException;
}