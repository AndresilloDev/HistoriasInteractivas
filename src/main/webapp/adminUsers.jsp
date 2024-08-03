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
    <style>
        .table-striped tbody tr:nth-of-type(odd) {
            background-color: var(--mode-background-color);
        }
        .pagination {
            display: flex;
            justify-content: center;
        }
        .pagination li {
            margin: 0 5px;
        }
    </style>
</head>
<%
    session = request.getSession(false);
    User user = (User) session.getAttribute("user");
    if(session != null && user != null && !user.isAdmin()) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<body class="light-mode">
<jsp:include page="components/navComponent/nav.jsp" />

<div class="container my-5">
    <h1>Consulta de usuarios</h1>

    <div class="row mb-4">
        <div class="col">
            <form action="adminUsers" method="post">
                <input type="hidden" name="action" value="buscar">
                <label for="emailSearch" class="form-label">Correo electrónico:</label>
                <div class="input-group">
                    <input type="email" name="email" id="emailSearch" class="form-control" required>
                    <button type="submit" class="btn btn-primary"><i class="fa fa-search"></i></button>
                </div>
            </form>
        </div>
    </div>

    <c:if test="${not empty usuario}">
        <h2>Información del Usuario</h2>
        <div class="card">
            <div class="card-body">
                <p><strong>Nombre:</strong> ${usuario.name}</p>
                <p><strong>Apellido Paterno:</strong> ${usuario.paternalSurname}</p>
                <p><strong>Apellido Materno:</strong> ${usuario.maternalSurname}</p>
                <p><strong>Correo Electrónico:</strong> ${usuario.email}</p>
                <p><strong>Estado:</strong> ${usuario.status ? "Habilitado" : "Deshabilitado"}</p>
                <form action="AdminUsersServlet" method="post" onsubmit="return confirm('¿Estás seguro de que quieres cambiar el estado de este usuario?');">
                    <input type="hidden" name="action" value="${usuario.status ? "deshabilitar" : "habilitar"}">
                    <input type="hidden" name="email" value="${usuario.email}">
                    <button type="submit" class="btn ${usuario.status ? "btn-danger" : "btn-success"} mt-2">
                            ${usuario.status ? "Deshabilitar" : "Habilitar"} Usuario
                    </button>
                </form>
            </div>
        </div>
    </c:if>

    <c:if test="${not empty mensaje}">
        <div class="alert alert-info mt-3">
                ${mensaje}
        </div>
    </c:if>

    <h2>Lista de Usuarios</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Email</th>
            <th>Usuario</th>
            <th>Nombre</th>
            <th>Apellido Paterno</th>
            <th>Apellido Materno</th>
            <th>Estado</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody id="userTableBody">
        <c:forEach var="user" items="${sessionScope.usuarios}" varStatus="status">
            <tr>
                <td>${user.email}</td>
                <td>${user.user}</td>
                <td>${user.name}</td>
                <td>${user.paternalSurname}</td>
                <td>${user.maternalSurname}</td>
                <td>${user.status ? "Habilitado" : "Deshabilitado"}</td>
                <td>
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
        <c:forEach var="i" begin="1" end="${sessionScope.usuarios.size() / 15 + 1}">
            <li class="page-item">
                <a class="page-link" href="#" onclick="paginate(${i})">${i}</a>
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
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script src="js/adminUsers.js"></script>
</body>
</html>