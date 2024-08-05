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
<%
    session = request.getSession(false);
    if(session != null && session.getAttribute("user") != null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<body class="light-mode">
<jsp:include page="components/navComponent/nav.jsp" />

<div class="container d-flex justify-content-center align-items-center">
    <div class="row p-3 shadow box-area" style="width: 500px;">
        <div class="col-12 rounded-4 d-flex justify-content-center align-items-center flex-column left-box">
            <img src="resources/img/featuredImageVerifyAccount.png" width="256" alt="Verificar cuenta">
            <div class="header-text mb-2 mt-4">
                <h1>Cambiar contraseña</h1>
            </div>
            <p class="fs-6 mb-4 " style="font-family: Noto Sans JP, sans-serif; text-align: center;">Ingrese la nueva contraseña.</p>
        </div>
        <form id="otpForm" method="post" action="changePassword">
            <div class="d-flex justify-content-center mb-4">
                <input type="hidden" name="token" value="${token}">
                <input type="hidden" name="email" value="${email}">
                <input type="password" class="form-control" name="newPassword" placeholder="Nueva contraseña" required>
            </div>
            <div class="d-flex justify-content-center mb-4">
                <button type="submit" class="btn btn-primary">Cambiar contraseña</button>
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

<jsp:include page="components/footerComponent/footer.jsp" />
</body>
</html>
