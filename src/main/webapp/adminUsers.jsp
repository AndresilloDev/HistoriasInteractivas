<%@ page import="mx.edu.utez.historiasinteractivas.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <link rel="stylesheet" href="css/adminUsers.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css" crossorigin="anonymous">
</head>
<%
    session = request.getSession(false);
    User user = (User) session.getAttribute("user");
    if(user == null || !user.isAdmin()) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<body class="light-mode">
<jsp:include page="components/navComponent/nav.jsp" />
<jsp:include page="components/alertComponent/alert.jsp" />

<div class="container my-5">
    <h1>Consulta de usuarios</h1>

    <div class="row mb-4">
        <div class="col">
            <form id="searchForm" action="adminUsers" method="post">
                <input type="hidden" name="action" value="buscar">
                <div class="input-group">
                    <input type="text" name="email" id="emailSearch" class="form-control input" placeholder="Buscar por correo electrónico" required>
                    <div class="input-group-append">
                        <button type="submit" class="btn btn-primary search-button button">Buscar</button>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-auto">
            <button type="button" class="btn btn-primary button reload" onclick="reloadUsers()">
                <i class="fas fa-redo"></i>
            </button>
        </div>
    </div>

    <table class="table table-striped">
        <thead>
        <tr id="headRow">
            <th>Email</th>
            <th>Usuario</th>
            <th>Nombre</th>
            <th>Apellido Paterno</th>
            <th>Apellido Materno</th>
            <th>Estado</th>
            <th>Acción</th>
        </tr>
        </thead>
        <tbody id="userTableBody">
        <c:forEach var="user" items="${sessionScope.usuarios}" varStatus="status">
            <tr>
                <td data-label="Email">${user.email}</td>
                <td data-label="Usuario">${user.user}</td>
                <td data-label="Nombre">${user.name}</td>
                <td data-label="Apellido Paterno">${user.paternalSurname}</td>
                <td data-label="Apellido Materno">${user.maternalSurname}</td>
                <td data-label="Estado">${user.status ? "Habilitado" : "Deshabilitado"}</td>
                <td data-label="Estatus">
                    <form action="adminUsers" method="post" onsubmit="return confirm('¿Estás seguro de que quieres cambiar el estado de este usuario?');">
                        <input type="hidden" name="action" value="${user.status ? "deshabilitar" : "habilitar"}">
                        <input type="hidden" name="email" value="${user.email}">
                        <button type="submit" class="btn ${user.status ? "btn-danger" : "btn-success"}">
                                ${user.status ? "Deshabilitar" : "Habilitar"} Usuario
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <ul class="pagination">
        <c:forEach var="i" begin="1" end="${sessionScope.totalPaginas}">
            <li class="page-item ${i == sessionScope.paginaActual ? 'active' : ''}">
                <a class="page-link" href="adminUsers?page=${i}">${i}</a>
            </li>
        </c:forEach>
    </ul>

</div>

<section class="waves-container1">
    <div class="wave wave1" style="position: fixed"></div>
    <div class="wave wave2" style="position: fixed"></div>
    <div class="wave wave3" style="position: fixed"></div>
</section>

<script src="components/navComponent/themeSwitch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/adminUsers.js"></script>
<script>
    function reloadUsers() {
        window.location.href = 'adminUsers';
    }
</script>
</body>
</html>
