<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-MX">
<head>
    <title>Historias Interactivas</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/fonts.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/index_layout.css">
    <link rel="stylesheet" href="css/wave_animation.css">
</head>

<body class="light-mode">
<!-- NAVBAR -->
<nav class="navbar navbar-expand" id="navbarSection">
    <div class="container-fluid">
        <a class="brand" href="index.jsp">LOGO</a>
        <div class="justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item" id="navThemeButton">
                    <button id="themeButton">
                        <img id="themeIcon" src="resources/img/moon_icon.png" style="filter: invert(0); padding: 0 0 0 2px;" alt="Switch Theme">
                    </button>
                </li>
                <li class="nav-item" id="navLoginButton">
                    <a class="nav-link btn rounded-pill" href="login.jsp" id="loginButton">
                        <img src="resources/img/userIcon.png" id="loginIcon" alt="iconoAcceder"> Acceder
                    </a>
                </li>
                <li class="nav-item dropdown" id="profileDropdown" style="display: none;">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="resources/img/fakeUser.jpeg" class="rounded-circle" width="40" height="40" alt="Imagen de perfil">
                    </a>
                    <ul class="dropdown-menu  dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="#">Tus historias</a></li>
                        <li><a class="dropdown-item" href="#">Crear una historia</a></li>
                        <li><a class="dropdown-item" href="#">Cerrar sesión</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

<!-- TITLE -->
<section class="hero" id="sectionOne">
    <div class="row">
        <div class="col-md-7 text-center text-md-start">
            <h1 class="display-1 fw-bold" id="title">
                <span class="word">Historias</span>
                <span class="word">Interactivas</span>
            </h1>
            <a href="login.jsp" class="mt-3" id="createHistoryButton">¡Crea una historia!</a>
        </div>
        <div class="col-md-5">
            <div class="animated-text">
                <span id="typewriter1"></span>
                <span id="typewriter2"></span>
                <span id="typewriter3"></span>
            </div>
        </div>
    </div>
    <div class="row justify-content-center" id="div-search">
        <input  id="search" class="col-md-10 text-left" placeholder="Ingrese el link de la historia" type="text">
    </div>
</section>

<!-- WAVES ANIMATION -->
<section class="waves-container1">
    <div class="wave wave1"></div>
    <div class="wave wave2"></div>
    <div class="wave wave3"></div>
</section>
<section class="waves-container2" style="rotate: 180deg">
    <div class="wave wave1"></div>
    <div class="wave wave2"></div>
    <div class="wave wave3"></div>
</section>

<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="js/fake_authentication.js"></script>
<script src="js/writing_animation.js"></script>
<script src="js/theme_switch.js"></script>
</body>
</html>