<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ page import="mx.edu.utez.historiasinteractivas.model.User" %>
<%@ page import="mx.edu.utez.historiasinteractivas.dao.UsuarioDao" %>
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
    UsuarioDao usuarioDao = new UsuarioDao();

    String id_story = (String) request.getAttribute("id_story");

    if(user == null ||
            usuarioDao.hasStory(user, id_story) ||
            request.getAttribute("event_id") == null
    ){
        request.setAttribute("message", "Se ha encontrado un error en el link");
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    int event_id = (int) request.getAttribute("event_id");

    String text = (String) request.getAttribute("text");
    String description = (String) request.getAttribute("description");
    String image = (String) request.getAttribute("image");
    String link = (String) request.getAttribute("link");
    String video = (String) request.getAttribute("video");
    String audio = (String) request.getAttribute("audio");
    String option1 = (String) request.getAttribute("option1");
    String option2 = (String) request.getAttribute("option2");
%>
<body class="light-mode">
<jsp:include page="components/navComponent/nav.jsp" />
<jsp:include page="components/alertComponent/alert.jsp" />

<div class="container d-flex justify-content-center align-items-center">
    <div class="row p-3 shadow box-area">
        <div class="container container-custom">
            <div class="row">
                <a href="createStory.jsp?id_story=<%=id_story%>">Ir a la edición de historia</a>
            </div>

            <c:if test="${image != null}">
                <div class="image-container">
                    <img src="${image}" alt="Scene Image" class="img-fluid">
                </div>
            </c:if>

            <c:if test="${audio != null}">
                <div class="audio-container">
                    <audio controls>
                        <source src="${audio}" type="audio/mpeg">
                        Your browser does not support the audio element.
                    </audio>
                </div>
            </c:if>

            <c:if test="${video != null}">
                <div class="video-container">
                    <video width="100%" controls>
                        <source src="${video}" type="video/mp4">
                        Your browser does not support the video tag.
                    </video>
                </div>
            </c:if>

            <c:if test="${link != null}">
                <div class="embed-responsive embed-responsive-16by9">
                    <iframe class="embed-responsive-item" src="https://www.youtube.com/embed/${link}" allowfullscreen></iframe>
                </div>
            </c:if>

            <div class="text-container">
                <p><%=description%></p>
            </div>

            <!-- Contenedor de Botones -->
            <div class="buttons-container">
                <c:if test="${option1 != null}">
                    <form action="previewStory" method="get">
                        <input type="hidden" name="id_story" value="<%=id_story%>">
                        <input type="hidden" name="event_id" value="<%=event_id%>">
                        <input type="hidden" name="option" value="option1">
                        <button href="#" class="cta" type="submit">
                            <svg class="svg1" width="13px" height="10px" viewBox="0 0 13 10px" style="rotate: 180deg">
                                <polyline points="8 1 12 5 8 9"></polyline>
                            </svg>
                            <span><%=option1%></span>
                        </button>
                    </form>
                </c:if>
                <c:if test="${option2 != null}">
                    <form action="previewStory" method="get">
                        <input type="hidden" name="id_story" value="<%=id_story%>">
                        <input type="hidden" name="event_id" value="<%=event_id%>">
                        <input type="hidden" name="option" value="option2">
                        <button href="#" class="cta" type="submit">
                            <span><%=option2%></span>
                            <svg class="svg2" width="13px" height="10px" viewBox="0 0 13 10px">
                                <polyline points="8 1 12 5 8 9"></polyline>
                            </svg>
                        </button>
                    </form>
                </c:if>
                <c:if test="${option1 == null && option2 == null}">
                    <a href="index.jsp">
                        <button class="btn btn-primary btn-custom">Volver a la página principal</button>
                    </a>
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
<script src="js/autoScroller.js"></script>
</body>
</html>