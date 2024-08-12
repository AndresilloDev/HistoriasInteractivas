<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    if(user == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    System.out.println(request.getAttribute("text"));
    System.out.println(request.getAttribute("description"));
    System.out.println(request.getAttribute("image"));
    System.out.println(request.getAttribute("link"));


%>
<body class="light-mode">
<jsp:include page="components/navComponent/nav.jsp" />
<jsp:include page="components/alertComponent/alert.jsp" />

<div class="container d-flex justify-content-center align-items-center">
    <div class="row p-3 shadow box-area">
        <div class="container container-custom">
            <c:if test="${requestScope.image != null or requestScope.image.equals('null')}">
                <img src="${requestScope.image}" alt="Scene Image" class="img-fluid">
            </c:if>

            <c:if test="${requestScope.audio != null or requestScope.audio.equals('null')}">
                <audio controls>
                    <source src="${requestScope.audio}" type="audio/mpeg">
                    Your browser does not support the audio element.
                </audio>
            </c:if>

            <c:if test="${requestScope.video != null or requestScope.video.equals('null')}">
                <video width="100%" controls>
                    <source src="${requestScope.video}" type="video/mp4">
                    Your browser does not support the video tag.
                </video>
            </c:if>

            <c:if test="${requestScope.link != null or requestScope.link.equals('null')}">
                <div class="embed-responsive embed-responsive-16by9">
                    <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${requestScope.link}" allowfullscreen></iframe>
                </div>
            </c:if>

            <div class="text-container">
                <p><%=request.getParameter("description")%></p>
            </div>

            <!-- Contenedor de Botones -->


            <div class="buttons-container">
                <form action="previewStory" method="get">
                    <input type="hidden" name="option" value="option1">
                    <button type="submit" class="btn btn-primary btn-custom"><%=request.getParameter("option1")%></button>
                </form>
                <form action="previewStory" method="get">
                    <input type="hidden" name="option" value="option2">
                    <button type="submit" class="btn btn-secondary btn-custom"><%=request.getParameter("option2")%></button>
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
