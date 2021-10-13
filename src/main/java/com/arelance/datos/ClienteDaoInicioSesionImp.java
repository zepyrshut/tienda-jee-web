/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.datos;

import static com.arelance.datos.ConexionDB.getConnection;
import com.arelance.dto.ClienteDto;
import java.sql.*;
import java.util.logging.*;
import com.arelance.interfaces.*;

/**
 *
 * @author Pedro
 */
public class ClienteDaoInicioSesionImp implements ClienteDaoInicioSesion {

    private static final String INICIAR_SESION = "SELECT nombre_cliente, apellido_cliente, id_cliente FROM cliente INNER JOIN datos_sesion ON cliente.id_cliente = datos_sesion.cliente_id_cliente WHERE datos_sesion.usuario = ?";
    private static final String VERIFICAR_DATOS = "SELECT usuario, contrasena FROM datos_sesion WHERE usuario = ?";
    //deben ser locales a los métodos del DAO
    private Connection conn = null;
    private PreparedStatement pstmt = null;
    private ResultSet rs = null;

    @Override
    public ClienteDto verificarDatos(ClienteDto cliente) {
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(VERIFICAR_DATOS);
            pstmt.setString(1, cliente.getUsuario());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                //no debo recuperar la contraseña solo debo validar el login y además al comprobar que existe ya tengo los datos del usuario
                cliente.setContrasena(rs.getString("contrasena"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoCrud.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            //esta regular debes de usar el ARM de los autocloseables como parametros del try() y eliminar el finally
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cliente;
    }

    @Override
    public ClienteDto seleccionarCliente(ClienteDto cliente) {
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(INICIAR_SESION);
            pstmt.setString(1, cliente.getUsuario());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                cliente.setIdCliente(rs.getInt("id_cliente"));
                cliente.setNombreCliente(rs.getString("nombre_cliente"));
                cliente.setApellidoCliente(rs.getString("apellido_cliente"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDaoCrud.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            //más de lo mismo: finally sobre y el try etc...
            try {
                conn.close();
                pstmt.close();
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(ArticuloDaoImp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return cliente;
    }
    
}
