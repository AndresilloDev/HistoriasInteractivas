<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .dropdown-toggle::after {
        display: none;
    }
</style>
<nav class="navbar navbar-expand  d-flex align-items-center" id="navbarSection">
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
                <c:when test="${not empty sessionScope.user}">
                <div class="nav-item dropdown" id="profileDropdown">
                    <a class="nav-link dropdown-"  id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown"  aria-expanded="false">
                        <img src="https://mir-s3-cdn-cf.behance.net/project_modules/max_1200/d4ad40103067131.5f450dd53ccd1.png" class="rounded-circle" width="40" height="40" alt="Imagen de perfil">
                    </a>
                    <div class="dropdown-menu dropdown-menu-end profile-dropdown-menu" aria-labelledby="navbarDropdownMenuLink">

                        <img src="${sessionScope.user.profilePicture}" alt="Imagen de perfil" id="profilePicture">
                        <div class="ms-3">
                            <h6>Hector Santiago Villa G.</h6>
                            <p>@vilahectorsillo234</p>
                            <a href="#">Editar cuenta</a>
                            </div>

                        <hr>
                        <a class="dropdown-item" href="#">
                            Crear historia
                        </a>
                        <a class="dropdown-item" href="#">
                            Tus historias
                        </a>
                        <a class="dropdown-item" href="logout.jsp">
                            Cerrar sesión
                        </a>
                    </div>
                </div>
                </c:when>
                <c:otherwise>
                <li class="nav-item" id="navLoginButton">
                    <a class="nav-link btn rounded-pill" href="login.jsp" id="loginButton">
                        <img src="resources/img/userIcon.png" id="loginIcon" alt="iconoAcceder"> Acceder
                    </a>
                </li>
                </c:otherwise>
                </c:choose>
        </div>
    </div>
</nav>