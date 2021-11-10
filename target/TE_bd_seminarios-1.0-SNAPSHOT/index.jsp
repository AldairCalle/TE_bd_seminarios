<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>  
<%@page import="com.emergentes.modelo.Seminario"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Seminario> lista=(List<Seminario>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP - EVENTOS</title>
    </head>
    <body>
        <table border="1">
            <tr><th>              
                SEGUNDO PARCIAL TEM-742<br>
                Nombre: Aldair Calle Q.<br>
                Carnet: 9889103
            </tr></th>
        </table>
        <h1>Registro de Seminarios</h1>
        <p><a href="MainController?op=nuevo">Nuevo</a></p>
        <table border="2" rules="all">
            <tr>
                <th>Id^</th>
                <th>Titulo</th>
                <th>Expositor</th>
                <th>Fecha</th>
                <th>Horas</th>
                <th>Cupos</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${lista}">
                <tr>
                    <td>${item.id}</td>
                    <td>${item.titulo}</td>
                    <td>${item.expositor}</td>
                    <td>${item.fecha}</td>
                    <td>${item.hora}</td>
                    <td>${item.cupo}</td>
                    <td><a href="MainController?op=editar&id=${item.id}">Editar</a></td>
                    <td><a href="MainController?op=eliminar&id=${item.id}" onclick="return(confirm('Está seguro ???'))">Eliminar</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>