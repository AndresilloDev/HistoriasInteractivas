<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es-MX">
<head>
    <title>Historias Interactivas</title>
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

<div class="container d-flex justify-content-center align-items-center">
    <div class="row p-3 shadow box-area">
        <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
            <div class="featured-image mb-3">
                <img alt="featuredImage" src="https://app.dubbpie.com/assets/images/login-img.png" class="img-fluid">
            </div>
        </div>

        <div class="col-md-6 right-box d-flex justify-content-center align-items-center">
            <div class="row align-items-center">
                <div class="header-text mb-4">
                    <h1>Registrate</h1>
                </div>

                <form action="register" method="post" autocomplete="off">
                    <label class="label">
                        <input id="email" name="email" type="email" placeholder=" " maxlength="50" class="input input-group mb-3 fs-6" required>
                        <span class="labelName fs-6">Correo electrónico</span>
                    </label>
                    <label class="label">
                        <input name="password" type="password" placeholder=" " maxlength="50" class="input input-group mb-3 fs-6" required>
                        <span class="labelName fs-6">Contraseña</span>
                    </label>
                    <label class="label">
                        <input name="confirmPassword" type="password" placeholder=" " maxlength="50" class="input input-group mb-3 fs-6" required>
                        <span class="labelName fs-6">Confirmar contraseña</span>
                    </label>
                    <input class="button pt-1 pb-1 mb-2" type="submit" value="Registrate" name="register"/>
                </form>

                <div class="input-group mb-3 d-flex justify-content-end">
                    <small><a href="login.jsp">¿Ya tienes una cuenta? Inicia sesión</a></small>
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

<script src="components/navComponent/themeSwitch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/register.js"></script>
</body>
</html>












































<!--
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
<link rel="stylesheet" href="css/waveAnimation.css">
</head>

<body class="light-mode">
<nav class="navbar navbar-expand" id="navbarSection">
<div class="container-fluid">
<a class="brand" href="index.jsp">LOGO</a>
<div class="justify-content-end">
<ul class="navbar-nav">
<li class="nav-item" id="navThemeButton">
<button id="themeButton">
<img id="themeIcon" src="resources/img/moonIcon.png" style="filter: invert(0); padding: 0 0 0 2px;" alt="Switch Theme">
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
<script src="js/themeSwitch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
-->