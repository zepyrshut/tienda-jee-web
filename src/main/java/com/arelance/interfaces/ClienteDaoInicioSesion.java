/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.interfaces;

import com.arelance.dto.ClienteDto;

/**
 *
 * @author Pedro
 */
public interface ClienteDaoInicioSesion {

    public ClienteDto seleccionarCliente(ClienteDto cliente);

    public ClienteDto verificarDatos(ClienteDto cliente);

}