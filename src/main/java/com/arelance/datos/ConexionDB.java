/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.datos;

import java.sql.*;
import java.util.logging.*;
import javax.naming.*;
import javax.sql.DataSource;

/**
 *
 * @author Pedro
 */
class ConexionDB {

    protected static Connection getConnection() {
        try {
            Context ctx = new InitialContext();
            //lo he cambiado para que funcione en mi servidor en las correcciones
            DataSource ds = (DataSource) ctx.lookup("jdbc/_tienda_db");
            Connection conn = ds.getConnection();
            return conn;
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
