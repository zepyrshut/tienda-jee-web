<%-- 
    Document   : registro
    Created on : 26 ago. 2021, 18:24:56
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
        <h1>Registrar usuario</h1>
        <form action="RegistroUsuario" method="GET">
            <p>Nombre: <input type="text" name="nombre"></p>
            <p>Apellido: <input type="text" name="apellido"></p>
            <p>Usuario: <input type="text" name="usuario"></p>    
            <p>Contraseña: <input type="text" name="contrasena"></p>  
            <p> 
                <input type="submit" value="Registrar">
                <input type="reset" value="Borrar">
            </p>
        </form>
        <% if (request.getAttribute("yaExiste") != null) {
        %> <p>${yaExiste}</p> <% }%>
    </body>
</html>
