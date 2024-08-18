<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="mx.edu.utez.historiasinteractivas.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    User user = (User) session.getAttribute("user");
%>

<nav class="navbar navbar-expand" id="navbarSection">
    <div class="container-fluid">
        <a class="brand" href="index.jsp"><img id="logo" src="resources/img/logoHistoriasInteractivas.png"> STORY</a>
        <div class="justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item" id="navThemeButton">
                    <button id="themeButton">
                        <img id="themeIcon" src="resources/icons/moonIcon.png" style="filter: invert(0); padding: 0 0 0 2px;" alt="Switch Theme">
                    </button>
                </li>
                <c:choose>
                    <c:when test="${user == null}">
                        <li class="nav-item" id="navLoginButton">
                            <a class="nav-link btn rounded-pill" href="login.jsp" id="loginButton">
                                <img src="resources/img/userIcon.png" id="loginIcon" alt="iconoAcceder"> Acceder
                            </a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item dropdown" id="profileDropdown">
                            <button id="userButton" class="btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                <img id="userIcon" src="${user.profilePicture != null ? user.profilePicture : 'resources/img/userIcon.png'}" alt="Imagen de perfil">
                            </button>
                            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="userButton">
                                <!-- Contenido del dropdown -->
                                <li class="profile-info">
                                    <div>
                                        <c:if test="${user.name != null}">
                                            <strong>${user.name} ${user.paternalSurname.charAt(0)}.</strong>
                                        </c:if>
                                        <c:if test="${user.name == null}">
                                            <strong>${user.email}</strong>
                                        </c:if>
                                        <c:if test="${user.user != null}">
                                            <small>@${user.user}</small>
                                        </c:if>
                                        <c:if test="${user.user == null}">
                                            <small>@${user.email}</small>
                                        </c:if>
                                    </div>
                                </li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="editUser.jsp">Editar cuenta</a></li>
                                <li><a class="dropdown-item" onclick="scrollToNextSection()">Tus historias</a></li>
                                <li><a class="dropdown-item" onclick="openCreateStoryModal()">Crear una historia</a></li>
                                <li><a class="dropdown-item" href="logout">Cerrar sesión</a></li>
                                <c:if test="${user.admin}">
                                    <li><a class="dropdown-item" href="adminUsers">Administrar</a></li>
                                </c:if>
                            </ul>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>