<%@ page import="mx.edu.utez.historiasinteractivas.dao.StoryDao" %>
<%@ page import="mx.edu.utez.historiasinteractivas.model.Story" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="mx.edu.utez.historiasinteractivas.model.User" %>
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
    <link rel="stylesheet" href="css/form.css">
    <link rel="stylesheet" href="css/waveAnimation.css">
    <link rel="stylesheet" href="css/themeSwitch.css">
    <link rel="stylesheet" href="css/createStory.css">
</head>
<%
    session = request.getSession(false);
    if(session.getAttribute("user") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<body class="light-mode" style="height: 100vh">
<jsp:include page="components/navComponent/nav.jsp" />
<jsp:include page="components/alertComponent/alert.jsp" />

<section class="waves-container1">
    <div class="wave wave1" style="position: fixed"></div>
    <div class="wave wave2" style="position: fixed"></div>
    <div class="wave wave3" style="position: fixed"></div>
</section>

<div class="container-vertical">
    <label class="label" style="font-family: 'Noto Sans JP', sans-serif">
        <input name="storyTitle" id="storyTitle" type="text" placeholder=" " maxlength="20" class="input input-group mb-3 fs-6" required style="background-color: var(--mode-background-color); width: 300px" value="Historia 1">
        <span class="labelName fs-6" style="background-color: var(--mode-background-color)">Titulo de la historia</span>
    </label>
    <div id="storyDiagram" class="storyDiagram-container"></div>
    <div id="buttonsBar" class="buttonsBar-container"></div>
</div>

<button id="PreviewButton" onclick="">Preview <i>></i></button>
<button id="SaveButton" onclick="saveStory()">Guardar historia</button>
<br>
<!-- Estp esta oculto pero contiene el diagrama que se carga al abrir la historia
     Aqui deberiamos cargar el json de lahistoria cargado -->
<c:choose>
    <c:when test="${not empty param.id_story}">
        <%
            String idStory = request.getParameter("id_story");
            User user = (User) session.getAttribute("user");
            StoryDao dao = new StoryDao();
            Story story = dao.findByCode(idStory, user);
            String diagram = (story != null) ? story.getJson() : "{}";
        %>
        <textarea id="mySavedModel" style="width: 100%; height: 300px; background-color: transparent; display: none">
            <%= diagram %>
        </textarea>
    </c:when>
    <c:otherwise>
            <textarea id="mySavedModel" style="width: 100%; height: 300px; background-color: transparent; display: none">
                { "class": "GraphLinksModel",
                "nodeDataArray": [{"category":"startEvent","key":-1,"loc":"0 0"}],
                "linkDataArray": []}
            </textarea>
    </c:otherwise>
</c:choose>

<!-- Modal de Edición -->
<div id="editModal">
    <div id="modalContent">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2 class="mb-2">Editar Evento</h2>
        <form id="editForm">
            <div id="dropArea" class="mb-4">
                <p>Arrastra y suelta archivos aquí o haz clic para seleccionar archivos</p>
                <input type="file" id="fileUpload" name="fileUpload" multiple style="display: none;" />
            </div>
            <!-- Contenedor de las vistas previas -->
            <div id="imagePreviewContainer" class="preview-container">📷 Imagen</div>
            <div id="audioPreviewContainer" class="preview-container">🔉 Audio</div>
            <div id="videoPreviewContainer" class="preview-container mb-4">📽️ Video</div>

            <label class="label">
                <input name="nodeText" id="nodeText" type="text" placeholder=" " class="input input-group mb-3 fs-6" required>
                <span class="labelName fs-6">Nombre del evento</span>
            </label>
            <label class="label">
                <textarea name="nodeDescription" id="nodeDescription" type="text" placeholder=" " class="input input-group mb-3 fs-6" style="height: 60px"></textarea>
                <span class="labelName fs-6">Descripción</span>
            </label>
            <button class="button" type="button" onclick="saveNodeData()">Aceptar</button>
        </form>
    </div>
</div>

<script src="js/createStory.js"></script>
<script src="components/navComponent/themeSwitch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/go.js"></script>

</body>
</html>