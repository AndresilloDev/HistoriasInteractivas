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
    <div class="row p-3 shadow box-area" style="width: 700px;">
        <div class="col-md-12">
            <div class="row align-items-center">
                <form action="EditUserServlet" method="post" autocomplete="off" enctype="multipart/form-data">
                    <div class="d-flex justify-content-center align-items-center mb-2">
                        <img alt="userPicture" id="img" src="${pageContext.request.contextPath}/${user.profilePicture != null ? user.profilePicture : 'resources/img/userIcon.png'}" class="img-fluid" style="width: 200px; height: 200px; border-radius: 50%; object-fit: cover"/>
                    </div>
                    <div class="d-flex justify-content-center align-items-center mb-4">
                        <label for="userPicture" class="custom-file-upload">Subir foto</label>
                        <input type="file" name="userPicture" id="userPicture" accept="image/*" style="display: none"/>
                    </div>

                    <label class="label">
                        <input name="email" type="hidden" value="${user.email}">
                        <input type="text" value="${user.email}" placeholder=" " class="input input-group mb-4 fs-6" disabled style="color: rgb(115, 115, 115);">
                        <span class="labelName fs-6">Correo electrónico</span>
                    </label>
                    <label class="label">
                        <input name="user" type="text" placeholder=" " maxlength="20" class="input input-group mb-4 fs-6" value="${user.user}" required>
                        <span class="labelName fs-6">Nombre de usuario</span>
                    </label>
                    <label class="label">
                        <input name="name" type="text" placeholder=" " maxlength="20" class="input input-group mb-4 fs-6" value="${user.name}" required>
                        <span class="labelName fs-6">Nombre</span>
                    </label>
                    <label class="label">
                        <input name="paternalName" type="text" placeholder=" " maxlength="20" class="input input-group mb-4 fs-6" value="${user.paternalSurname}" required>
                        <span class="labelName fs-6">Apellido paterno</span>
                    </label>
                    <label class="label">
                        <input name="maternalName" type="text" placeholder=" " maxlength="20" class="input input-group mb-4 fs-6" value="${user.maternalSurname}" required>
                        <span class="labelName fs-6">Apellido materno</span>
                    </label>
                    <input class="button pt-1 pb-1 mb-2" type="submit" value="Actualiza tu información" name="updateInformation"/>
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

<script src="components/navComponent/themeSwitch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/editUser.js"></script>
</body>
</html>
