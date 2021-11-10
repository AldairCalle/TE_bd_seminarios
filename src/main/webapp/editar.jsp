<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.Seminario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Seminario semi=(Seminario)request.getAttribute("semi");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSTL - EVENTOS</title>
    </head>
    <body>
        <h1>
            <c:if test="${semi.id==0}">Nuevo</c:if>
            <c:if test="${semi.id!=0}">Editar</c:if>
            Seminario
        </h1>
        <form action="MainController" method="post">
            <table>
                <input type="hidden" name="id" value="${semi.id}">
                <tr>
                    <td>Titulo</td>
                    <td><input type="text" name="titulo" value="${semi.titulo}" required></td>
                </tr>
                <tr>
                    <td>Expositor</td>
                    <td><input type="text" name="expositor" value="${semi.expositor}"></td>
                </tr>
                <tr>
                    <td>Fecha</td>
                    <td><input type="date" name="fecha" value="${semi.fecha}"></td>
                </tr>
                <tr>
                    <td>Horas</td>
                    <td><input type="text" name="hora" value="${semi.hora}" placeholder="00:00 - 00:00"></td>
                </tr>
                <tr>
                    <td>Cupos</td>
                    <td><input type="number" name="cupo" min="0" value="${semi.cupo}"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Enviar"/></td>
                </tr>
                <tr>
            </table>          
        </form>
    </body>
</html>