/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.controlador;

import com.arelance.dto.ClienteDto;
import com.arelance.servicios.InicioSesionServiciosImp;

/**
 *
 * @author Pedro
 */
//debe de implementar un interfaz para darle la oportinidad de diversas implementaciones en el inicio de session
public class InicioSesionControl {
    //Las propiedades nos las usas más allá del método. Esto indica que son variables locales y no propiedades
    //además  esta clase sobra casi seguro ya que puedes usar directamente el servivio:InicioSesionServiciosImp
    private final ClienteDto cliente = new ClienteDto();
    private ClienteDto dto = new ClienteDto();
    // la variable debe ser del tipo de la interfaz InicioSesionServicios
    private final InicioSesionServiciosImp servicio = new InicioSesionServiciosImp();
    
    public ClienteDto validacionUsuario(String usuario, String contrasena) {
        cliente.setUsuario(usuario);
        dto.setUsuario(usuario);
        dto.setContrasena(contrasena);
        servicio.verificarDatos(cliente);
        if (cliente.equals(dto)) {
            dto = servicio.seleccionarCliente(dto);
            return dto;
        } else {
            return null;
        }
    }

}
