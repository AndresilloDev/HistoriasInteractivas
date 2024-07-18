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
    <div class="row p-3 shadow box-area" style="width: 500px;">
        <div class="col-12 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
            <img src="resources/img/featuredImageVerifyAccount.png" width="256" alt="Verificar cuenta">
            <div class="header-text mb-2 mt-4">
                <h1>Verifica tu cuenta</h1>
            </div>
            <p class="fs-6 mb-4 " style="font-family: Noto Sans JP, sans-serif; text-align: center;">Ingresa el código de verificación que te enviamos a tu correo electrónico.</p>
        </div>
        <form id="otpForm" method="post" action="verifyAccount">
            <div class="d-flex justify-content-center mb-4">
                <input name="num1" type="number" class="form-control otp-input" maxlength="1" pattern="[0-9]" required>
                <input name="num2" type="number" class="form-control otp-input" maxlength="1" pattern="[0-9]" required>
                <input name="num3" type="number" class="form-control otp-input" maxlength="1" pattern="[0-9]" required>
                <input name="num4" type="number" class="form-control otp-input" maxlength="1" pattern="[0-9]" required>
                <input name="num5" type="number" class="form-control otp-input" maxlength="1" pattern="[0-9]" required>
                <input name="num6" type="number" class="form-control otp-input" maxlength="1" pattern="[0-9]" required>
            </div>
        </form>
    </div>
</div>

<section class="waves-container1">
    <div class="wave wave1" style="position: fixed"></div>
    <div class="wave wave2" style="position: fixed"></div>
    <div class="wave wave3" style="position: fixed"></div>
</section>

<script src="components/navComponent/themeSwitch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/otpFormVerifyAccount.js"></script>
</body>
</html>