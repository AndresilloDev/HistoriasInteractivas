<%@ page import="mx.edu.utez.historiasinteractivas.dao.StoryDao" %>
<%@ page import="mx.edu.utez.historiasinteractivas.model.Story" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mx.edu.utez.historiasinteractivas.model.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.io.File" %>
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
    <link rel="stylesheet" href="css/form.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.8.1/font/bootstrap-icons.min.css" rel="stylesheet">

</head>

<%
    session = request.getSession(false);
    User user = (User) session.getAttribute("user");
%>
<jsp:include page="components/alertComponent/alert.jsp" />
<body class="light-mode">

<div class="scroll-container">
    <section class="one">
        <!-- NAVBAR -->
        <jsp:include page="components/navComponent/nav.jsp" />

        <section class="hero">
            <div class="row">
                <div class="col-md-7 text-center text-md-start">
                    <h1 class="fw-bold">
                        <span class="word">Historias</span>
                        <span class="word">Interactivas</span>
                    </h1>
                    <div class="row">
                        <div class="search-input position-relative">
                            <form method="post" action="searchStory" id="search-form">
                                <fieldset>
                                    <input name="id_story" type="search" id="search" placeholder="Escribe el codigo" maxlength="6" class="pt-1 pb-1 mb-4">
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

        <c:if test="${not empty sessionScope.user}">
            <div class="scroll-arrow" onclick="scrollToNextSection()">
                <svg id="more-arrows" xmlns="http://www.w3.org/2000/svg" width="100" height="64" viewBox="0 0 100 64">
                    <polygon class="arrow-middle" points="50,45.8 0,15 5,13 50,41.2 95,13 100,15"/>
                    <polygon class="arrow-bottom" points="50,64 0,32 10,28 50,56.8 90,28 100,32"/>
                </svg>
            </div>
        </c:if>

        <section class="waves-container1">
            <div class="wave wave1"></div>
            <div class="wave wave2"></div>
            <div class="wave wave3"></div>
        </section>
    </section>

    <c:choose>
    <c:when test="${not empty sessionScope.user}">
    <section id="storieContent" class="two">
        <section class="waves-container2" style="rotate: 180deg; padding-top: 200px;">
            <div class="wave wave1"></div>
            <div class="wave wave2"></div>
            <div class="wave wave3"></div>
        </section>
        <h1 class="display-1 fw-bold mb-3 text-center">Tus historias</h1>
        <div class="container mb-4 text-left" style="min-height: 0">
            <!-- Sección de historias públicas -->
            <h2 class="display-4">Publicas</h2>
            <div class="row">
                <div class="card-container">
                    <%
                            StoryDao dao = new StoryDao();
                            ArrayList<Story> publishedStories = dao.getAllPublicStories(user);

                            for(Story s : publishedStories){
                    %>

                    <div style="background-image: url('<%=s.getStory_thumbnail()%>'); background-size: cover; background-position: center;" class="card"
                         onclick="openMenu('publica',
                                 '<%=s.getStory_title()%>',
                                 '<%=s.getRelease_date()%>',
                                 '<%=s.getStory_description()%>',
                                 '<%=s.getStory_thumbnail()%>',
                                 '<%=request.getContextPath() + File.separator + "/createStory.jsp?id_story=" + s.getId_story()%>',
                                 '<%=s.getId_story()%>')">
                        <h2><%=s.getStory_title()%></h2>
                    </div>
                            <% } %>
                </div>
                </a>
            </div>
        </div>

        <div class="container mb-4 text-left" style="min-height: 0">
            <!-- Sección de historias restringidas -->
            <h2 class="display-4">Restringidas</h2>
            <div class="row">
                <div class="card-container">
                    <%
                            ArrayList<Story> restrictedStories = dao.getAllRestrictedStories(user);

                            for(Story s : restrictedStories){
                    %>
                    <div style="background-image: url('<%=s.getStory_thumbnail()%>'); background-size: cover; background-position: center;" class="card" onclick="openMenu('restringida', '<%=s.getStory_title()%>', '<%=s.getRelease_date()%>', '<%=s.getStory_description()%>', '<%=s.getStory_thumbnail()%>', '<%=request.getContextPath() + File.separator + "/createStory.jsp?id_story=" + s.getId_story()%>', '<%=s.getId_story()%>')">
                        <h2><%=s.getStory_title()%></h2>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>

        <div class="container mb-4 text-left" style="min-height: 0; padding-bottom: 100px">
            <!-- Sección de borradores de historias -->
            <h2 class="display-4">Borradores</h2>
            <div class="row">
                <div class="card-container">
                    <%
                            ArrayList<Story> draftsStories = dao.getAllDraftStories(user);

                            for(Story s : draftsStories){
                    %>
                    <div style="background-image: url('<%=s.getStory_thumbnail()%>'); background-size: cover; background-position: center;" class="card" onclick="openMenu('borrador', '<%=s.getStory_title()%>', '<%=s.getRelease_date()%>', '<%=s.getStory_description()%>', '<%=s.getStory_thumbnail()%>', '<%=request.getContextPath() + File.separator + "/createStory.jsp?id_story=" + s.getId_story()%>', '<%=s.getId_story()%>')">
                        <h2><%=s.getStory_title()%></h2>
                    </div>
                    <% } %>
                </div>
            </div>
        </div>

    </section>

    </c:when>
    </c:choose>
</div>

<div class="overlay" id="overlay">
    <div class="menu container" style="min-height: 0">
        <div class="row" style="width: fit-content; padding-left: 96%">
            <button class="close-btn" onclick="closeMenu()">
                <i class="fas fa-times"></i>
            </button>
        </div>
        <div class="row mt-4">
            <div class="col-md-3 col-12 m-auto" style="width: 125px; height: 150px; border-radius: 15px">
                <img src="https://m.media-amazon.com/images/I/91UaMqkEb5L._AC_UF894,1000_QL80_.jpg" class="img-fluid" id="thumbnail" alt="Portada de la historia">
            </div>
            <div class="col-md-9 col-12">
                <h6 class="text-md-start text-center mt-2"><strong>Título</strong></h6>
                    <h2 id="story-title" class="text-md-start text-center">Titulo</h2>

                <p class="text-md-end text-center"><strong>Fecha de publicación</strong><br><span id="date"></span></p>
            </div>
        </div>
        <div class="row mt-4 p3">
            <h6 class="text-center"><strong>Descripción</strong></h6>
            <div class="col">
                <p class="text-center" id="description">Sin descripción</p>
            </div>
        </div>
        <div class="row mt-2">
            <div class="col text-center">
                <button id="option1" class="btn mx-2">Opcion</button><br>
                <button id="option2" class="btn mx-2">Opcion</button><br>
                <button id="option3" class="btn mx-2">Opcion</button>
            </div>
        </div>
    </div>
</div>

<div class="overlay" id="storyModal">
    <div class="modal-container">
        <div class="row justify-content-end">
            <button class="close-btn" onclick="closeStoryModal()">
                <i class="fas fa-times"></i>
            </button>
        </div>

        <!-- AQUI VA EL FORM PARA CREAR UNA HISTORIA ERICKITO -->
        <form id="createStoryForm" method="post" action="createStory" class="mt-4" enctype="multipart/form-data">
            <div class="row mb-4">
                <div class="col-md-3 col-12 m-auto position-relative" style="width: 125px; height: 150px; border-radius: 15px;">
                    <!-- Contenedor de la imagen y el botón -->
                    <div class="image-upload-container position-relative" style="border-radius: 15px;">
                        <img src="resources/img/thumbnailDefault.png" class="img-fluid" id="thumbnailPreview" alt="Portada de la historia" style="border-radius: 15px;">
                        <!-- Botón de subir imagen -->
                        <label for="coverImage" class="image-upload-btn position-absolute w-100 h-100 top-0 start-0 d-flex align-items-center justify-content-center" style="opacity: 0; cursor: pointer; transition: opacity 0.3s, background-color 0.3s;">
                            <span class="fs-1 text-white">+</span>
                        </label>
                        <input type="file" id="coverImage" name="coverImage" accept="image/*" class="d-none" onchange="previewImage()">
                    </div>
                </div>
                <div class="col-md-9 col-12 mt-md-0 mt-2">
                    <label class="label">
                        <input id="storyTitle" name="storyTitle" type="text" placeholder=" " class="input input-group mb-3 fs-6" required>
                        <span class="labelName fs-6">Titulo</span>
                    </label>
                    <label class="label">
                        <textarea id="storyDescription" name="storyDescription" placeholder=" " class="input input-group mb-3 fs-6" required></textarea>
                        <span class="labelName fs-6">Descripción</span>
                    </label>
                </div>
            </div>
            <div class="row mb-4">
                <div class="col text-center">
                    <button type="submit" class="button pt-1 pb-1 mb-2">Crear Historia</button>
                </div>
            </div>
        </form>
    </div>
</div>


<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="js/searchStory.js"></script>
<script src="js/writingAnimation.js"></script>
<script src="js/overlayInformation.js"></script>
<script src="js/index.js"></script>
<script src="components/navComponent/themeSwitch.js"></script>
</body>
</html>