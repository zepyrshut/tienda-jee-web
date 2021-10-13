/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.controlador;

import com.arelance.dto.ArticuloDto;
import com.arelance.dto.ClienteDto;
import com.arelance.servicios.GestionCestaServiciosImp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class CestaCompraControl {

    private List<ArticuloDto> cestaCompra = new ArrayList<>();
    private final GestionCestaServiciosImp servicio = new GestionCestaServiciosImp();
    private ArticuloDto articulo = new ArticuloDto();

    public ClienteDto recuperacionCesta(ClienteDto cliente) {
        int idCliente = cliente.getIdCliente();
        cestaCompra = servicio.generarListaCesta(idCliente);
        cliente.setCestaCompra((ArrayList) cestaCompra);
        return cliente;
    }

    public List<ArticuloDto> cestaCompraListar(ClienteDto cliente) {
        cestaCompra = cliente.getCestaCompra();
        return cestaCompra;
    }

    public List<ArticuloDto> anadirArticulosCesta(List<ArticuloDto> cestaCompra, int idArticulo) {
        articulo = servicio.obtenerArticuloCestaPorId(idArticulo);
        if (cestaCompra.contains(articulo)) {
            int index = cestaCompra.indexOf(articulo);
            int cantidad = cestaCompra.get(index).getCantidad();
            cantidad++;
            cestaCompra.get(index).setCantidad(cantidad);
        } else {
            articulo.setCantidad(1);
            cestaCompra.add(articulo);
        }
        return cestaCompra;
    }

    public List<ArticuloDto> eliminarArticulosCesta(List<ArticuloDto> cestaCompra, int idArticulo) {
        articulo = servicio.obtenerArticuloCestaPorId(idArticulo);
        int index = cestaCompra.indexOf(articulo);
        cestaCompra.remove(index);
        return cestaCompra;
    }

    public List<ArticuloDto> eliminarCestaCompleta(List<ArticuloDto> cestaCompra) {
        return cestaCompra = null;
    }

    public int guardarCestaBbdd(List<ArticuloDto> cestaCompra, ClienteDto cliente) {
        int inserciones;
        inserciones = servicio.registrarCestaBddd(cestaCompra, cliente);
        return inserciones;
    }

}
