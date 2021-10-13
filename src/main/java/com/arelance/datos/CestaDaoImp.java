/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.datos;

import static com.arelance.datos.ConexionDB.getConnection;
import java.sql.SQLException;
import com.arelance.interfaces.*;
import com.arelance.dto.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

/**
 *
 * @author Pedro
 */
public class CestaDaoImp implements CestaDao {

    private static final String RECOGER_DATOS_CESTA = "SELECT id_articulo, nombre_articulo, precio_articulo, cantidad_cesta FROM articulo INNER JOIN detalle_cesta ON articulo.id_articulo = detalle_cesta.articulo_id_articulo LEFT JOIN cesta_compra ON detalle_cesta.cesta_compra_id_cesta_compra = cesta_compra.id_cesta_compra WHERE cesta_compra.cliente_id_cliente = ?;";
    private static final String SELECCIONAR_ARTICULO_ID = "SELECT id_articulo, nombre_articulo, precio_articulo, descripcion_articulo FROM articulo WHERE id_articulo = ?;";
    private static final String REGISTRAR_DATOS_CESTA = "INSERT INTO detalle_cesta (articulo_id_articulo, cesta_compra_id_cesta_compra, cantidad_cesta) VALUES(?, ?, ?);";
    private static final String REINICIAR_CESTA = "DELETE FROM detalle_cesta WHERE cesta_compra_id_cesta_compra IN (?);";

    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;
    private ArticuloDto articulo = new ArticuloDto();

    @Override
    public List<ArticuloDto> generarListaCesta(int idCliente) {
        List<ArticuloDto> cestaCliente = new ArrayList<>();
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(RECOGER_DATOS_CESTA);
            pstmt.setInt(1, idCliente);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                int idArticulo = rs.getInt("id_articulo");
                String nombre = rs.getString("nombre_articulo");
                double precio = rs.getDouble("precio_articulo");
                int cantidad = rs.getInt("cantidad_cesta");
                articulo = new ArticuloDto();
                articulo.setIdArticulo(idArticulo);
                articulo.setNombreArticulo(nombre);
                articulo.setPrecioArticulo(precio);
                articulo.setCantidad(cantidad);
                cestaCliente.add(articulo);
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
        return cestaCliente;
    }

    @Override
    public ArticuloDto obtenerArticuloCestaPorId(int idArticulo) {
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(SELECCIONAR_ARTICULO_ID);
            pstmt.setInt(1, idArticulo);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                articulo.setIdArticulo(rs.getInt("id_articulo"));
                articulo.setNombreArticulo(rs.getString("nombre_articulo"));
                articulo.setPrecioArticulo(rs.getDouble("precio_articulo"));
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

    @Override
    public int registrarCestaBddd(List<ArticuloDto> cestaCompra, ClienteDto cliente) {
        int inserciones = 0;
        try {
            vaciarCestaCliente(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(CestaDaoImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        conn = getConnection();
        try (PreparedStatement registroCesta = conn.prepareStatement(REGISTRAR_DATOS_CESTA)) {
            conn.setAutoCommit(false);
            for (int i = 0; i < cestaCompra.size(); i++) {
                registroCesta.setInt(1, cestaCompra.get(i).getIdArticulo());
                registroCesta.setInt(2, cliente.getIdCliente());
                registroCesta.setInt(3, cestaCompra.get(i).getCantidad());
                registroCesta.executeBatch();
                inserciones = registroCesta.executeUpdate();
            }
            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(CestaDaoImp.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ClienteDaoCrudImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserciones;
    }

    public ArticuloDto recuperacionArticulos(List<ArticuloDto> cestaCompra) {
        for (int i = 0; i < cestaCompra.size(); i++) {
            articulo.setIdArticulo(cestaCompra.get(i).getIdArticulo());
            articulo.setNombreArticulo(cestaCompra.get(i).getNombreArticulo());
            articulo.setCantidad(cestaCompra.get(i).getCantidad());
        }
        return articulo;
    }

    public void vaciarCestaCliente(ClienteDto cliente) throws SQLException {
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(REINICIAR_CESTA);
            pstmt.setInt(1, cliente.getIdCliente());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoCrudImp.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conn.close();
            pstmt.close();
        }
    }
    
}
