/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Pedro
 */
public class Cliente implements Serializable, Comparable<Cliente> {

    private static final long serialVersionUID = 1L;
    private String idCliente;
    private String usuario;
    private String nombreCliente;
    private String apellidoCliente;
    private ArrayList cestaCompra;

    public Cliente() {
    }

    public Cliente(String idCliente, String usuario, String nombreCliente, String apellidoCliente, ArrayList cestaCompra) {
        this.idCliente = idCliente;
        this.usuario = usuario;
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.cestaCompra = cestaCompra;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    public ArrayList getCestaCompra() {
        return cestaCompra;
    }

    public void setCestaCompra(ArrayList cestaCompra) {
        this.cestaCompra = cestaCompra;
    }
    
    
    @Override
    public int compareTo(Cliente o) {
        throw new UnsupportedOperationException("No implementado aun.");
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.idCliente);
        hash = 53 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.idCliente, other.idCliente)) {
            return false;
        }
        return Objects.equals(this.usuario, other.usuario);
    }

}
