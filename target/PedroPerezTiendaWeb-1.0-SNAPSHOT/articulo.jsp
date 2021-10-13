<%-- 
    Document   : articulo
    Created on : 30-ago-2021, 12:25:25
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
        <h1>Detalles del articulo</h1>
        ${articulo.idArticulo}
        ${articulo.nombreArticulo}
        ${articulo.precioArticulo}
        ${articulo.descripcionArticulo}
        <img src="img/${articulo.idArticulo}.jpg" alt="${articulo.nombreArticulo}">
    </body>
</html>
