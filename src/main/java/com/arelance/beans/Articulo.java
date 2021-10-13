/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.beans;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Pedro
 */
public class Articulo implements Serializable, Comparable<Articulo> {

    private static final long serialVersionUID = 1L;
    private int idArticulo;
    private String nombreArticulo;
    private double precioArticulo;
    private String descripcionArticulo;

    public Articulo() {
    }

    public Articulo(int idArticulo, String nombreArticulo, double precioArticulo, String descripcionArticulo) {
        this.idArticulo = idArticulo;
        this.nombreArticulo = nombreArticulo;
        this.precioArticulo = precioArticulo;
        this.descripcionArticulo = descripcionArticulo;
    }

    public String getNombreArticulo() {
        return nombreArticulo;
    }

    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }

    public double getPrecioArticulo() {
        return precioArticulo;
    }

    public void setPrecioArticulo(double precioArticulo) {
        this.precioArticulo = precioArticulo;
    }

    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getDescripcionArticulo() {
        return descripcionArticulo;
    }

    public void setDescripcionArticulo(String descripcionArticulo) {
        this.descripcionArticulo = descripcionArticulo;
    }

    @Override
    public int compareTo(Articulo o) {
        if (this.precioArticulo == o.getPrecioArticulo())
            return 0;
        else if (this.precioArticulo > o.getPrecioArticulo())
            return 1;
        else
            return -1;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.idArticulo;
        hash = 71 * hash + Objects.hashCode(this.nombreArticulo);
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
        final Articulo other = (Articulo) obj;
        if (this.idArticulo != other.idArticulo) {
            return false;
        }
        return Objects.equals(this.nombreArticulo, other.nombreArticulo);
    }

}
