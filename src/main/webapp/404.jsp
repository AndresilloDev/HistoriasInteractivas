<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>404 - Página no encontrada</title>
    <link rel="stylesheet" href="components/navComponent/nav.css">
    <link rel="stylesheet" href="css/waveAnimation.css">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/form.css">
    <link rel="stylesheet" href="css/themeSwitch.css">

    <style>
        .container {
            height: 100vh;
            display: flex;
            align-items: center;
            justify-content: center;
        }
        .display-1 {
            font-size: 10rem;
        }
        .lead {
            font-size: 2rem;
        }
        .btn {
            font-size: 1.25rem;
        }
    </style>

</head>

<body class="light-mode">

<div class="container text-center">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <h1 class="display-1">404</h1>
            <p class="lead">Oops! La página que buscas no existe.</p>
            <a href="index.jsp" class="btn btn-primary">Volver al Inicio</a>
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

</body>
</html>
