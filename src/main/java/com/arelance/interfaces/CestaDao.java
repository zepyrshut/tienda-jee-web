/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.interfaces;

import com.arelance.dto.*;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface CestaDao {

    public List<ArticuloDto> generarListaCesta(int idCliente);

    public ArticuloDto obtenerArticuloCestaPorId(int idArticulo);

    public int registrarCestaBddd(List<ArticuloDto> cestaCompra, ClienteDto cliente);

}
