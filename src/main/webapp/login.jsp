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
    <link rel="stylesheet" href="css/waveAnimation.css">
    <link rel="stylesheet" href="css/themeSwitch.css">
</head>

<body class="light-mode">
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
            </ul>
        </div>
    </div>
</nav>

<div class="container d-flex justify-content-center align-items-center">
    <div class="row p-3 shadow box-area">
        <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
            <div class="featured-image mb-3">
                <img alt="featured_image" src="https://cdni.iconscout.com/illustration/premium/thumb/login-10299071-8333958.png" class="img-fluid">
            </div>
        </div>
        <div class="col-md-6 right-box">
            <div class="row align-items-center">
                <div class="header-text mb-4">
                    <h1>Inicia sesión</h1>
                </div>
                <form action="login" method="post" autocomplete="off">
                    <label class="label">
                        <input name="user" type="text" placeholder=" " class="input input-group mb-3 fs-6" required>
                        <span class="labelName fs-6">Correo electrónico o Usuario</span>
                    </label>
                    <label class="label">
                        <input name="password" type="password" placeholder=" " class="input input-group mb-1 fs-6" required>
                        <span class="labelName fs-6">Contraseña</span>
                    </label>
                    <div class="input-group mb-3 d-flex justify-content-end">
                        <small><a href="recoverPassword.jsp">¿Olvidaste tu contraseña?</a></small>
                    </div>
                    <div class="button-container mb-3">
                        <span class="mas">Iniciar sesión</span>
                        <button id='work' type="submit" name="login">Iniciar sesión</button>
                    </div>
                </form>
                <div class="input-group mb-3 d-flex justify-content-end">
                    <small><a href="register.jsp">¿No tienes una cuenta? Registrate</a></small>
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

<script src="js/themeSwitch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>