<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es-MX">
<head>
    <title>Historias Interactivas</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/fonts.css">
    <link rel="stylesheet" href="css/nav.css">
    <link rel="stylesheet" href="css/form.css">
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

<div class="container d-flex justify-content-center align-items-center">
    <div class="row p-3 shadow box-area">
        <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
            <div class="featured-image mb-3">
                <img alt="featured_picture" src="resources/img/icon-256x256.png" class="img-fluid" style="width: 250px; filter: brightness(10);">
            </div>
        </div>

        <div class="col-md-6 right-box">
            <div class="row align-items-center">
                <div class="header-text mb-4">
                    <h1>Restablecer contraseña</h1>
                </div>

                <form action="verify_email" method="post" autocomplete="off">
                    <label class="label">
                        <input name="email" type="text" placeholder=" " class="input input-group mb-3 fs-6" required>
                        <span class="label_name fs-6">Correo electrónico o Usuario</span>
                    </label>
                    <div class="button-container mb-3">
                        <span class="mas">Enviar verificación</span>
                        <button type="submit" name="send_verification">Enviar verificación</button>
                    </div>
                </form>

                <div class="input-group mb-3 d-flex justify-content-end">
                    <small><a href="register.jsp">¿No tienes una cuenta? registrate</a></small>
                </div>
            </div>
        </div>
    </div>
</div>

<section class="waves-container1">
    <div class="wave wave1" style="position: fixed"></div>
    <div class="wave wave2" style="position: fixed"></div>
    <div class="wave wave3" style="position: fixed"></div>
</section>

<script src="js/theme_switch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>