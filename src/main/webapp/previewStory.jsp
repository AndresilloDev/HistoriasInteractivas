<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="mx.edu.utez.historiasinteractivas.model.User" %>
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
    System.out.println(user.toString());


    String text = (String) request.getAttribute("text");
    String description = (String) request.getAttribute("description");
    String image = (String) request.getAttribute("image");
    String link = (String) request.getAttribute("link");
    String video = (String) request.getAttribute("video");
    String audio = (String) request.getAttribute("audio");
    String option1 = (String) request.getAttribute("option1");
    String option2 = (String) request.getAttribute("option2");

    System.out.println("text " + text);
    System.out.println("description " + description);
    System.out.println("image " + image);
    System.out.println("link " + link);
    System.out.println("video " + video);
    System.out.println("Audio " + audio);
    System.out.println("Option 1 " + option1);
    System.out.println("Option 2 " + option2);
%>
<body class="light-mode">
<jsp:include page="components/navComponent/nav.jsp" />
<jsp:include page="components/alertComponent/alert.jsp" />

<div class="container d-flex justify-content-center align-items-center">
    <div class="row p-3 shadow box-area">
        <div class="container container-custom">
            <c:if test="${image != null or !image.equals('null')}">
                <img src="${image}" alt="Scene Image" class="img-fluid">
            </c:if>

            <c:if test="${audio != null or !audio.equals('null')}">
                <audio controls>
                    <source src="${audio}" type="audio/mpeg">
                    Your browser does not support the audio element.
                </audio>
            </c:if>

            <c:if test="${video != null or !video.equals('null')}">
                <video width="100%" controls>
                    <source src="${video}" type="video/mp4">
                    Your browser does not support the video tag.
                </video>
            </c:if>

            <c:if test="${link != null or !link.equals('null')}">
                <div class="embed-responsive embed-responsive-16by9">
                    <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${link}" allowfullscreen></iframe>
                </div>
            </c:if>

            <div class="text-container">
                <p><%=description%></p>
            </div>

            <!-- Contenedor de Botones -->


            <div class="buttons-container">
                <c:if test="${option1 != null or !option1.equals('null')}">
                    <form action="previewStory" method="get">
                        <input type="hidden" name="option" value="option1">
                        <button type="submit" class="btn btn-primary btn-custom"><%=option1%></button>
                    </form>
                </c:if>
                <c:if test="${option2 != null or !option2.equals('null')}">
                    <form action="previewStory" method="get">
                        <input type="hidden" name="option" value="option2">
                        <button type="submit" class="btn btn-secondary btn-custom"><%=option2%></button>
                    </form>
                </c:if>
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
</body>
</html>
