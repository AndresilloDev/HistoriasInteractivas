<nav class="navbar navbar-expand" id="navbarSection">
    <div class="container-fluid">
        <a class="brand" href="index.jsp"><img id="logo" src="resources/img/logoHistoriasInteractivas.png"> STORY</a>
        <div class="justify-content-end">
            <ul class="navbar-nav">
                <li class="nav-item" id="navThemeButton">
                    <button id="themeButton">
                        <img id="themeIcon" src="resources/icons/moonIcon.png" style="filter: invert(0); padding: 0 0 0 2px;" alt="Switch Theme">
                    </button>
                </li>
                <li class="nav-item" id="navLoginButton">
                    <a class="nav-link btn rounded-pill" href="login.jsp" id="loginButton">
                        <img src="resources/img/userIcon.png" id="loginIcon" alt="iconoAcceder"> Acceder
                    </a>
                </li>
                <li class="nav-item dropdown" id="profileDropdown" style="display: none;">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <img src="resources/img/fakeUser.jpeg" class="rounded-circle" width="40" height="40" alt="Imagen de perfil">
                    </a>
                    <ul class="dropdown-menu  dropdown-menu-end" aria-labelledby="navbarDropdownMenuLink">
                        <li><a class="dropdown-item" href="#">Tus historias</a></li>
                        <li><a class="dropdown-item" href="#">Crear una historia</a></li>
                        <li><a class="dropdown-item" href="#">Cerrar sesión</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>