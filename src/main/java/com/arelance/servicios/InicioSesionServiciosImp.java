/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.servicios;

import com.arelance.datos.ClienteDaoInicioSesionImp;
import com.arelance.dto.ClienteDto;
import com.arelance.interfaces.InicioSesionServicios;

/**
 *
 * @author Pedro
 */
public class InicioSesionServiciosImp implements InicioSesionServicios {

    @Override
    public ClienteDto verificarDatos(ClienteDto cliente) {
        cliente = new ClienteDaoInicioSesionImp().verificarDatos(cliente);
        return cliente;
    }

    @Override
    public ClienteDto seleccionarCliente(ClienteDto cliente) {
        cliente = new ClienteDaoInicioSesionImp().seleccionarCliente(cliente);
        return cliente;
    }
    
}
