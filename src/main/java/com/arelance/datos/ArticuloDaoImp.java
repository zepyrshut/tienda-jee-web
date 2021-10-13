/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.datos;

import static com.arelance.datos.ConexionDB.getConnection;
import com.arelance.dto.*;
import com.arelance.interfaces.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Pedro
 */
public class ArticuloDaoImp implements ArticuloDao {

    private static final String LISTA_ARTICULOS = "SELECT id_articulo, nombre_articulo, precio_articulo FROM articulo;";
    private static final String SELECCIONAR_ARTICULO_ID = "SELECT id_articulo, nombre_articulo, precio_articulo, descripcion_articulo FROM articulo WHERE id_articulo = ?;";

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private ArticuloDto articulo = null;

    @Override
    public List<ArticuloDto> generarListaArticulos() {
        List<ArticuloDto> articulos = new ArrayList<>();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(LISTA_ARTICULOS);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int idArticulo = rs.getInt("id_articulo");
                String nombre = rs.getString("nombre_articulo");
                double precio = rs.getDouble("precio_articulo");
                articulo = new ArticuloDto();
                articulo.setIdArticulo(idArticulo);
                articulo.setNombreArticulo(nombre);
                articulo.setPrecioArticulo(precio);
                articulos.add(articulo);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ArticuloDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return articulos;
    }

    @Override
    public ArticuloDto seleccionarArticuloId(ArticuloDto articulo) {
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SELECCIONAR_ARTICULO_ID);
            pstmt.setInt(1, articulo.getIdArticulo());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                articulo.setNombreArticulo(rs.getString("nombre_articulo"));
                articulo.setPrecioArticulo(rs.getDouble("precio_articulo"));
                articulo.setDescripcionArticulo(rs.getString("descripcion_articulo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoCrud.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return articulo;
    }

}
