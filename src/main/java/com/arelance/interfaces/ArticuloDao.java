/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.interfaces;

import com.arelance.dto.ArticuloDto;
import java.util.List;

/**
 *
 * @author Pedro
 */
public interface ArticuloDao {

    public List<ArticuloDto> generarListaArticulos();

    public ArticuloDto seleccionarArticuloId(ArticuloDto articulo);

}
