<%-- 
    Document   : index
    Created on : 25 ago. 2021, 21:12:49
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h2>Listado de artículos</h2>
        <%@include file="bloques/listaarticulos.jspf"%>
        <% if (session.getAttribute("cliente") == null) { %>
        <form action="InicioSesion" method="GET">
            <p>Usuario: <input type="text" name="usuario"></p>
            <p>Contraseña: <input type="text" name="contrasena"></p>
            <p> 
                <input type="submit" value="Iniciar sesión">
                <input type="reset" value="Borrar">
            </p>
        </form>
        <a href="registro.jsp"><h1>Registro de usuario</h1></a> 
        NO hay sesion iniciada
        <% } else { %>
        <p>Bienvenidos ${cliente.usuario}</p>
        <form action="CerrarSesion" method="GET">
            <input type="submit" value="Cerrar sesión" name="cerrar">
        </form>
        <p>Debes cerrar la sesión para guardar la cesta</p>
        <% }%>
        <% if (request.getAttribute("datoIncorrecto") != null) {
        %> <p>${usuarioRegistrado}</p> <% }%>
        <% if (request.getAttribute("usuarioRegistrado") != null) {
        %> <p>${usuarioRegistrado}</p> <% }%>
        <%@include file="bloques/cestacompra.jspf"%>    
    </body>
</html>
