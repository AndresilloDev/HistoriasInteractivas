<%@ page import="mx.edu.utez.historiasinteractivas.model.User" %>
<%@ page import="mx.edu.utez.historiasinteractivas.model.Story" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="mx.edu.utez.historiasinteractivas.model.Scene" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es-MX">
<head>
    <title>Preview</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="UTF-8">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/fonts.css">
    <link rel="stylesheet" href="components/navComponent/nav.css">
    <link rel="stylesheet" href="css/form.css">
    <link rel="stylesheet" href="css/waveAnimation.css">
    <link rel="stylesheet" href="css/themeSwitch.css">
    <link rel="stylesheet" href="css/previewStory.css">
</head>
<%
    session = request.getSession(false);
    User user = (User) session.getAttribute("user");
    if(user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    Story story = (Story) session.getAttribute("story");
    Scene scene = (Scene) request.getAttribute("scene");

    String image = (scene.getScene_image() != null && scene.getScene_image().equals("null")) ? null : scene.getScene_image();
    String audio = (scene.getScene_audio() != null && scene.getScene_audio().equals("null")) ? null : scene.getScene_audio();
    String video = (scene.getScene_video() != null && scene.getScene_video().equals("null")) ? null : scene.getScene_video();
    String link = (scene.getScene_link() != null && scene.getScene_link().equals("null")) ? null : scene.getScene_link();

%>
<body class="light-mode">
<jsp:include page="components/navComponent/nav.jsp" />
<jsp:include page="components/alertComponent/alert.jsp" />

<div class="container d-flex justify-content-center align-items-center">
    <div class="row p-3 shadow box-area">
        <div class="container container-custom">
            <c:if test="${not empty image and image != 'null'}">
                <img src="${image}" alt="Scene Image" class="img-fluid">
            </c:if>

            <!-- Mostrar audio si está presente y no es 'null' como cadena -->
            <c:if test="${not empty audio and audio != 'null'}">
                <audio controls>
                    <source src="${audio}" type="audio/mpeg">
                    Your browser does not support the audio element.
                </audio>
            </c:if>

            <!-- Mostrar video si está presente y no es 'null' como cadena -->
            <c:if test="${not empty video and video != 'null'}">
                <video width="100%" controls>
                    <source src="${video}" type="video/mp4">
                    Your browser does not support the video tag.
                </video>
            </c:if>

            <!-- Mostrar video de YouTube si está presente y no es 'null' como cadena -->
            <c:if test="${not empty link and link != 'null'}">
                <div class="embed-responsive embed-responsive-16by9">
                    <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${link}" allowfullscreen></iframe>
                </div>
            </c:if>


            <div class="text-container">
                <p><%=scene.getScene_text()%></p>
            </div>

            <!-- Contenedor de Botones -->
            <form class="buttons-container" action="previewStory" method="post">
                <input type="hidden" name="option" value="option1">
                <button type="submit" class="btn btn-primary btn-custom"><%=scene.getFirst_choice()%></button>
            </form>
            <form class="buttons-container" action="previewStory" method="post">
                <input type="hidden" name="option" value="option2">
                <button type="submit" class="btn btn-secondary btn-custom"><%=scene.getSecond_choice()%></button>
            </form>
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
