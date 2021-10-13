/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.servicios;

import com.arelance.datos.ClienteDaoCrudImp;
import com.arelance.dto.ClienteDto;
import com.arelance.interfaces.ClienteCrudServicios;

/**
 *
 * @author Pedro
 */
public class ClienteCrudServiciosImp implements ClienteCrudServicios{
    
    @Override
    public int registroCliente(ClienteDto cliente) {
        int inserciones;
        inserciones = new ClienteDaoCrudImp().registroCliente(cliente);
        return inserciones;
    }
    
}
