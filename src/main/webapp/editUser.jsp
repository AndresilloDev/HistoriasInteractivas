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
            <label class="featured-image mb-3">
                <img alt="userPicture" src="resources/img/userIcon.png" class="img-fluid" style="width: 100px; height: 100px"/>
            </label>
            <input type="file" name="userPicture"/>
        </div>
        <div class="col-md-6">
            <div class="row align-items-center" style="padding-top: calc(8%)">
                <form action="editUser" method="post" autocomplete="off" enctype="multipart/form-data">
                    <label class="label">
                        <input disabled value="aqui va el correo de usuario obtenido" type="email" placeholder=" " class="input input-group mb-4 fs-6" required style="color: rgb(115, 115, 115);">
                        <span class="labelName fs-6">Correo electroníco</span>
                    </label>
                    <label class="label">
                        <input name="user" type="text" placeholder=" " class="input input-group mb-4 fs-6" required>
                        <span class="labelName fs-6">Nombre de usuario</span>
                    </label>
                    <label class="label">
                        <input name="name" type="text" placeholder=" " class="input input-group mb-4 fs-6" required>
                        <span class="labelName fs-6">Nombre</span>
                    </label>
                    <label class="label">
                        <input name="paternalName" type="text" placeholder=" " class="input input-group mb-4 fs-6" required>
                        <span class="labelName fs-6">Apellido paterno</span>
                    </label>
                    <label class="label">
                        <input name="maternalName" type="text" placeholder=" " class="input input-group mb-4 fs-6" required>
                        <span class="labelName fs-6">Apellido materno</span>
                    </label>
                    <div class="button-container mb-3">
                        <span class="mas">Actualizar información</span>
                        <button type="submit" name="updateInformation">Actualizar información</button>
                    </div>
                </form>
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