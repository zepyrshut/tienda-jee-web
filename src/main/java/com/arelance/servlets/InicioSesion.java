/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arelance.servlets;

import com.arelance.controlador.*;
import com.arelance.dto.*;
import java.io.IOException;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 *
 * @author Pedro
 */
@WebServlet(name = "InicioSesion", urlPatterns = {"/InicioSesion"})
public class InicioSesion extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("usuario");
        String contrasena = request.getParameter("contrasena");
        ClienteDto dto;
        List<ArticuloDto> cestaCompra;

        InicioSesionControl inicioSesion = new InicioSesionControl();
        dto = inicioSesion.validacionUsuario(usuario, contrasena);

        if (dto != null) {
            CestaCompraControl cestaCompraControl = new CestaCompraControl();
            dto = cestaCompraControl.recuperacionCesta(dto);
            cestaCompra = (ArrayList<ArticuloDto>) cestaCompraControl.cestaCompraListar(dto);
            HttpSession session = request.getSession(true);
            //te sobra la tienes en el ClienteDTO: cliente
            session.setAttribute("cestaCompra", cestaCompra);
            session.setAttribute("cliente", dto);//<--aquí la tienes
            request.getRequestDispatcher("CargadorTienda").forward(request, response);
        } else {
            String datoIncorrecto = "Error, has introducido algún dato incorrecto.";
            request.setAttribute("datoIncorrecto", datoIncorrecto);
            request.getRequestDispatcher("CargadorTienda").forward(request, response);
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
