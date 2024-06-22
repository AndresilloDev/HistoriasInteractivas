<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es-MX">
<head>
  <title>Historias Interactivas</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta charset="utf-8">
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/fonts.css">
  <link rel="stylesheet" href="css/nav.css">
  <link rel="stylesheet" href="css/login.css">
  <link rel="stylesheet" href="css/wave_animation.css">
</head>
<body class="light-mode">
<nav class="navbar navbar-expand">
  <div class="container-fluid">
    <a class="brand" href="index.jsp">LOGO</a>
  </div>
</nav>

<button id="themeSwitch" class="floating-button">
  <img id="themeIcon" src="resources/img/moon_icon.png" style="filter: invert(0); padding: 0 0 0 2px;" alt="Switch Theme">
</button>

<section class="hero">
  <div class="container">
    <div class="row justify-content-center">
      <div class="col-md-6">
        <div class="card mx-auto">
          <div class="card-body">
            <h1 class="card-title text-left">Iniciar Sesión</h1>
            <form id="loginForm" action="login" method="post" autocomplete="off">
              <label class="label">
                <input name="user" type="text" placeholder=" " class="input" required>
                <span class="label_name">Usuario o Correo electrónico</span>
              </label>
              <label class="label">
                <input name="password" type="password" placeholder=" " class="input" required>
                <span class="label_name">Contraseña</span>
                <span class="text-end"><a href="#" class="forgot">¿Olvidaste tu contraseña?</a></span>
              </label>
              <button id="login-button" class="login" type="submit">Iniciar sesión</button>
              <span class="text-end"><a href="register.jsp" class="register">¿No tienes cuenta? Regístrate</a></span>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<section class="waves-container">
  <div class="wave wave1"></div>
  <div class="wave wave2"></div>
  <div class="wave wave3"></div>
</section>

<script src="js/fake_authentication.js"></script>
<script src="js/theme_switch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
