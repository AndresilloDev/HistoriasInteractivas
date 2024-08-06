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
    <link rel="stylesheet" href="components/sliderComponent/slider.css">
    <link rel="stylesheet" href="css/form.css">
    <link rel="stylesheet" href="css/waveAnimation.css">
    <link rel="stylesheet" href="css/themeSwitch.css">
</head>
<%
    session = request.getSession(false);
    if(session == null && session.getAttribute("user") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<body class="light-mode">
<jsp:include page="components/navComponent/nav.jsp" />
<jsp:include page="components/alertComponent/alert.jsp" />

<div class="container d-flex justify-content-center align-items-center">
    <div class="row p-3 shadow box-area">
        <div class="col-md-6 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
            <div class="featured-image mb-3">
                <img alt="featured_image" src="resources/img/featuredImageRecoverPassword.png" class="img-fluid">
            </div>
        </div>
        <div class="col-md-6 right-box d-flex justify-content-center align-items-center">
            <div class="row align-items-center">
                <div class="header-text mb-4">
                    <h1>Recuperar Contraseña</h1>
                </div>
                <form action="recoverPassword" method="post" autocomplete="off">
                    <label class="label">
                        <input id="email" name="email" type="email" placeholder=" " maxlength="50" class="input input-group mb-1 fs-6" required>
                        <span class="labelName fs-6">Correo electrónico</span>
                    </label>
                    <div class="input-group mb-3 d-flex justify-content-end">
                        <small><a href="login.jsp">Ir a iniciar sesión</a></small>
                    </div>
                    <input class="button pt-1 pb-1 mb-2" type="submit" value="Enviar verificación" name="recoverPassword"/>
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

<script src="components/navComponent/themeSwitch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/recoverPassword.js"></script>
</body>
</html>