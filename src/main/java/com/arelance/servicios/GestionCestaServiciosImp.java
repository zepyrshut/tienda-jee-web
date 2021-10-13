/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.servicios;

import com.arelance.datos.CestaDaoImp;
import com.arelance.dto.*;
import com.arelance.interfaces.GestionCestaServicios;
import java.util.List;

/**
 *
 * @author Pedro
 */
public class GestionCestaServiciosImp implements GestionCestaServicios {

    @Override
    public List<ArticuloDto> generarListaCesta(int idCliente) {
        List<ArticuloDto> cestaCliente = new CestaDaoImp().generarListaCesta(idCliente);
        return cestaCliente;
    }

    @Override
    public ArticuloDto obtenerArticuloCestaPorId(int idArticulo) {
        ArticuloDto articulo = new CestaDaoImp().obtenerArticuloCestaPorId(idArticulo);
        return articulo;
    }

    @Override
    public int registrarCestaBddd(List<ArticuloDto> cestaCompra, ClienteDto cliente) {
        int inserciones;
        inserciones = new CestaDaoImp().registrarCestaBddd(cestaCompra, cliente);
        return inserciones;
    }
    
}
