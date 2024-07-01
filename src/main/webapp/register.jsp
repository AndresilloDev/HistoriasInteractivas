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
    <link rel="stylesheet" href="css/register.css">
    <link rel="stylesheet" href="css/wave_animation.css">
</head>

<body class="light-mode">
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
            </ul>
        </div>
    </div>
</nav>

<div class="row mx-0 justify-content-center">
    <div class="card" id="card">
        <h1 class="card-title text-left" id="card-title">Registrarse</h1>
        <form id="registerForm" action="register" method="post" autocomplete="off">
            <label class="label">
                <input name="user" type="text" placeholder=" " class="input" required>
                <span class="label_name">Usuario</span>
            </label>
            <label class="label">
                <input name="name" type="text" placeholder=" " class="input">
                <span class="label_name">Nombre</span>
            </label>
            <label class="label">
                <input name="email" type="email" placeholder=" " class="input" required>
                <span class="label_name">Correo</span>
            </label>
            <div class="verification-group">
                <label class="label">
                    <input name="verification_code" type="text" placeholder=" " class="input" required>
                    <span class="label_name">Codigo de verificación</span>
                </label>
                <input class="verify-button" type="submit" value="Enviar codigo">
            </div>

            <label class="label">
                <input name="password" type="password" placeholder=" " class="input"  required>
                <span class="label_name">Contraseña</span>
            </label>

            <label class="label">
                <input name="confirm_password" type="password" placeholder=" " class="input" required>
                <span class="label_name">Confirmar contraseña</span>
            </label>

            <button class="register" type="submit" value="Register">Registrarse</button>
            <span class="text-end"><a href="login.jsp" class="login">¿Ya tienes cuenta? Inicia sesión</a></span>
        </form>
    </div>
</div>

<section class="waves-container1">
    <div class="wave wave1"></div>
    <div class="wave wave2"></div>
    <div class="wave wave3"></div>
</section>

<script src="js/fake_authentication.js"></script>
<script src="js/theme_switch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
