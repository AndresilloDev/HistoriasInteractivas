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
    String idStory = request.getParameter("id_story");
    if(session.getAttribute("user") == null || idStory == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    User user = (User) session.getAttribute("user");
    StoryDao dao = new StoryDao();
    Story story = null;
    try {
        story = dao.findByCode(idStory, user);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    String storyTitle = story.getStory_title();
    String diagram = story.getJson();
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
        <input name="storyTitle" id="storyTitle" type="text" placeholder=" " maxlength="20" class="input input-group mb-3 fs-6" required style="background-color: var(--mode-background-color); width: 300px" value="<%= storyTitle %>">
        <span class="labelName fs-6" style="background-color: var(--mode-background-color)">Titulo de la historia</span>
    </label>
    <div id="storyDiagram" class="storyDiagram-container"></div>
    <div id="buttonsBar" class="buttonsBar-container"></div>
</div>

<a id="PreviewButton" onclick="saveStory('<%=idStory%>')" href="previewStory?id_story=<%=idStory%>">Preview <i>></i></a>
<button id="SaveButton" onclick="saveStory('<%=idStory%>')">Guardar historia</button>
<br>
<!-- Estp esta oculto pero contiene el diagrama que se carga al abrir la historia
     Aqui deberiamos cargar el json de lahistoria cargado -->
<textarea id="mySavedModel" style="width: 100%; height: 300px; background-color: transparent; display: none">
    <%= diagram %>
</textarea>

<!-- Modal de Edición -->
<div id="editModal">
    <div id="modalContent">
        <span class="close" onclick="closeModal()">&times;</span>
        <h2 class="mb-2">Editar Escena</h2>
        <form id="editForm" enctype="multipart/form-data">
            <div id="dropArea" class="mb-4">
                <p>Arrastra y suelta archivos aquí o haz clic para seleccionar archivos</p>
                <input type="file" id="fileUpload" name="fileUpload" style="display: none; " accept="image/*,audio/*,video/*" multiple/>
            </div>
            <!-- Contenedor de las vistas previas -->
            <div id="imagePreviewContainer" class="preview-container">📷 Imagen</div>
            <div id="audioPreviewContainer" class="preview-container">🔉 Audio</div>
            <div id="videoPreviewContainer" class="preview-container mb-4">📽️ Video</div>

            <label class="label" id="decision">
                <input name="nodeText" id="nodeText" type="text" placeholder=" " class="input input-group mb-3 fs-6" required>
                <span class="labelName fs-6">Decisión:</span>
            </label>
            <label class="label">
                <textarea name="nodeDescription" id="nodeDescription" type="text" placeholder=" " class="input input-group mb-3 fs-6" style="height: 60px" required></textarea>
                <span class="labelName fs-6">Descripción:</span>
            </label>
            <div class="row">
                <button class="button" type="button" onclick="clearFileUpload()" style="margin-left: 13px; padding: 1rem;">Borrar Archivos</button>
                <button class="button" type="button" onclick="saveNodeData()" style="margin-right: 13px; padding: 1rem;">Aceptar</button>
            </div>

        </form>
    </div>
</div>

<script src="js/createStory.js"></script>
<script src="components/navComponent/themeSwitch.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="js/go.js"></script>
<script src="js/autoScroller.js"></script>
</body>
</html>