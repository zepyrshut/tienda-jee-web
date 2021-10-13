/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.datos;

import static com.arelance.datos.ConexionDB.*;
import com.arelance.dto.ClienteDto;
import java.sql.*;
import java.util.logging.*;
import com.arelance.interfaces.ClienteDaoCrud;

/**
 *
 * @author Pedro
 */
public class ClienteDaoCrudImp implements ClienteDaoCrud {
    
    private static final String REGISTRO_CLIENTE_PERSONAL = "INSERT INTO cliente (nombre_cliente, apellido_cliente) VALUES(?, ?);";
    private static final String REGISTRO_CLIENTE_USUARIO = "INSERT INTO datos_sesion (usuario, contrasena, cliente_id_cliente) VALUES(?, ?, ?);";
    private static final String CREACION_CESTA_COMPRA = "INSERT INTO cesta_compra (id_cesta_compra, cliente_id_cliente) VALUES (?, ?);";
    
    private Connection conn = null;
    
    @Override
    public int registroCliente(ClienteDto cliente) {
        int inserciones = 0;
        conn = getConnection();
        try (PreparedStatement registroPersonal = conn.prepareStatement(REGISTRO_CLIENTE_PERSONAL, Statement.RETURN_GENERATED_KEYS);
                PreparedStatement registroUsuario = conn.prepareStatement(REGISTRO_CLIENTE_USUARIO);
                PreparedStatement creacionCesta = conn.prepareStatement(CREACION_CESTA_COMPRA)) {
            conn.setAutoCommit(false);
            registroPersonal.setString(1, cliente.getNombreCliente());
            registroPersonal.setString(2, cliente.getApellidoCliente());
            registroPersonal.executeUpdate();
            try (ResultSet claveGenerada = registroPersonal.getGeneratedKeys()) {
                if (claveGenerada.next()) {
                    registroUsuario.setString(1, cliente.getUsuario());
                    registroUsuario.setString(2, cliente.getContrasena());
                    registroUsuario.setInt(3, claveGenerada.getInt(1));
                    inserciones = registroUsuario.executeUpdate();
                    creacionCesta.setInt(1, claveGenerada.getInt(1));
                    creacionCesta.setInt(2, claveGenerada.getInt(1));
                    creacionCesta.executeUpdate();
                }
            }
            conn.commit();
        } catch (SQLException ex) {
            try {
                conn.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ClienteDaoCrudImp.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(ClienteDaoCrudImp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return inserciones;
    }
    
}
