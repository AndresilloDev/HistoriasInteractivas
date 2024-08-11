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
    Scene scene = (Scene) session.getAttribute("scene");

    System.out.println(session.getAttribute("image"));
    System.out.println(session.getAttribute("link"));

%>
<body class="light-mode">
<jsp:include page="components/navComponent/nav.jsp" />
<jsp:include page="components/alertComponent/alert.jsp" />

<div class="container d-flex justify-content-center align-items-center">
    <div class="row p-3 shadow box-area">
        <div class="container container-custom">
            <!--
            <c:if test="${not empty sessionScope.link and sessionScope.link != 'null'}">
                <img src="${sessionScope.image}" alt="Scene Image" class="img-fluid">
            </c:if>

            <c:if test="${not empty sessionScope.link and sessionScope.link != 'null'}">
                <audio controls>
                    <source src="${sessionScope.audio}" type="audio/mpeg">
                    Your browser does not support the audio element.
                </audio>
            </c:if>

            <c:if test="${not empty sessionScope.link and sessionScope.link != 'null'}">
                <video width="100%" controls>
                    <source src="${sessionScope.video}" type="video/mp4">
                    Your browser does not support the video tag.
                </video>
            </c:if>

            <c:if test="${not empty sessionScope.link and sessionScope.link != 'null'}">
                <div class="embed-responsive embed-responsive-16by9">
                    <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${sessionScope.link}" allowfullscreen></iframe>
                </div>
            </c:if>
            -->

            <div class="text-container">
                <p><%=scene.getScene_text()%></p>
            </div>

            <!-- Contenedor de Botones -->


            <div class="buttons-container">
                <form action="viewStory" method="post">
                    <input type="hidden" name="option" value="option1">
                    <button type="submit" class="btn btn-primary btn-custom"><%=scene.getFirst_choice()%></button>
                </form>
                <form action="viewStory" method="post">
                    <input type="hidden" name="option" value="option2">
                    <button type="submit" class="btn btn-secondary btn-custom"><%=scene.getSecond_choice()%></button>
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
</body>
</html>
