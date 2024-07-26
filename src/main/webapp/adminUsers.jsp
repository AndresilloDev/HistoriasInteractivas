<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List, mx.edu.utez.historiasinteractivas.model.User" %>
<!DOCTYPE html>
<html lang="es-MX">
<head>
    <title>Administrar Usuarios</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/fonts.css">
    <link rel="stylesheet" href="components/navComponent/nav.css">
    <link rel="stylesheet" href="css/form.css">
    <link rel="stylesheet" href="css/waveAnimation.css">
    <link rel="stylesheet" href="css/themeSwitch.css">
</head>
<body class="light-mode">
<jsp:include page="components/navComponent/nav.jsp" />

<div class="container my-5">
    <h1>Administrar Usuarios</h1>
    <div class="row mb-4">
        <div class="col">
            <form action="AdminUsersServlet" method="post">
                <input type="hidden" name="action" value="buscar">
                <label for="emailSearch" class="form-label">Correo electrónico:</label>
                <input type="email" name="email" id="emailSearch" maxlength="50" class="form-control" required>
                <button type="submit" class="btn btn-primary mt-2">Buscar Usuario</button>
            </form>
        </div>
        <div class="col">
            <form action="AdminUsersServlet" method="post" onsubmit="return confirm('¿Estás seguro de que quieres deshabilitar este usuario?');">
                <input type="hidden" name="action" value="deshabilitar">
                <label for="emailDisable" class="form-label">Correo electrónico:</label>
                <input type="email" name="email" id="emailDisable" maxlength="50" class="form-control" required>
                <button type="submit" class="btn btn-danger mt-2">Deshabilitar Usuario</button>
            </form>
        </div>
    </div>

    <% if (request.getAttribute("usuarios") != null) { %>
    <h2>Lista de Usuarios</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Correo Electrónico</th>
            <th>Usuario</th>
            <th>Nombre</th>
            <th>Apellido Paterno</th>
            <th>Apellido Materno</th>
            <th>Estado</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<User> usuarios = (List<User>) request.getAttribute("usuarios");
            for (User usuario : usuarios) {
        %>
        <tr>
            <td><%= usuario.getEmail() %></td>
            <td><%= usuario.getUser() %></td>
            <td><%= usuario.getName() %></td>
            <td><%= usuario.getPaternalSurname() %></td>
            <td><%= usuario.getMaternalSurname() %></td>
            <td><%= usuario.isStatus() ? "Habilitado" : "Deshabilitado" %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>

    <% if (request.getAttribute("usuario") != null) { %>
    <h2>Información del Usuario</h2>
    <% User usuario = (User) request.getAttribute("usuario"); %>
    <div class="card">
        <div class="card-body">
            <p><strong>Nombre:</strong> <%= usuario.getName() %></p>
            <p><strong>Apellido Paterno:</strong> <%= usuario.getPaternalSurname() %></p>
            <p><strong>Apellido Materno:</strong> <%= usuario.getMaternalSurname() %></p>
            <p><strong>Correo Electrónico:</strong> <%= usuario.getEmail() %></p>
            <p><strong>Estado:</strong> <%= usuario.isStatus() ? "Habilitado" : "Deshabilitado" %></p>
        </div>
    </div>
    <% } %>

    <% if (request.getAttribute("mensaje") != null) { %>
    <div class="alert alert-info mt-3">
        <%= request.getAttribute("mensaje") %>
    </div>
    <% } %>
</div>

<section class="waves-container1">
    <div class="wave wave1" style="position: fixed"></div>
    <div class="wave wave2" style="position: fixed"></div>
    <div class="wave wave3" style="position: fixed"></div>
</section>

<script src="components/navComponent/themeSwitch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
