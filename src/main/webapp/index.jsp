<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="es-MX">
<head>
    <title>Historias Interactivas</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/fonts.css">
    <link rel="stylesheet" href="components/navComponent/nav.css">
    <link rel="stylesheet" href="css/indexLayout.css">
    <link rel="stylesheet" href="css/waveAnimation.css">
    <link rel="stylesheet" href="css/themeSwitch.css">
</head>

<body class="light-mode">

<div class="scroll-container">
    <section class="one">
        <!-- NAVBAR -->
        <jsp:include page="components/navComponent/nav.jsp" />

        <section class="hero">
            <div class="row">
                <div class="col-md-7 text-center text-md-start">
                    <h1 class="display-1 fw-bold" id="title">
                        <span class="word">Historias</span>
                        <span class="word">Interactivas</span>
                    </h1>
                    <div class="row">
                        <div class="search-input position-relative">
                            <form method="get" id="search-form">
                                <fieldset>
                                    <input name="storyCode" type="search" id="search" placeholder="Escribe el codigo" maxlength="20" class="pt-1 pb-1 mb-4">
                                    <input class="button pt-1 pb-1 mb-4" type="submit" value="Buscar" name="searchStory"/>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="animated-text">
                        <span id="typewriter1"></span>
                        <span id="typewriter2"></span>
                        <span id="typewriter3"></span>
                    </div>
                </div>
            </div>
        </section>

        <!-- WAVES ANIMATION -->
        <section class="waves-container1">
            <div class="wave wave1"></div>
            <div class="wave wave2"></div>
            <div class="wave wave3"></div>
        </section>
    </section>

    <section class="two">
        <section class="waves-container2" style="rotate: 180deg">
            <div class="wave wave1"></div>
            <div class="wave wave2"></div>
            <div class="wave wave3"></div>
        </section>
    </section>
</div>

<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="js/searchStory.js"></script>
<script src="js/writingAnimation.js"></script>
<script src="components/navComponent/themeSwitch.js"></script>
</body>
</html>