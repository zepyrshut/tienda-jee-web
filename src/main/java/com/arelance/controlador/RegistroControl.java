/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.controlador;

import com.arelance.dto.ClienteDto;
import com.arelance.servicios.ClienteCrudServiciosImp;

/**
 *
 * @author Pedro
 */
public class RegistroControl {

    private final ClienteDto cliente = new ClienteDto();
    private final ClienteCrudServiciosImp servicio = new ClienteCrudServiciosImp();
    private int registro = 0;

    public int registroUsuario(String nombre, String apellido, String usuario, String contrasena) {
        cliente.setNombreCliente(nombre);
        cliente.setApellidoCliente(apellido);
        cliente.setUsuario(usuario);
        cliente.setContrasena(contrasena);
        registro = servicio.registroCliente(cliente);
        return registro;
    }

}
