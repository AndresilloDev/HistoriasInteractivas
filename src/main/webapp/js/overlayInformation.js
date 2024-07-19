// Función para abrir el menú desplegable y ajustar opciones según la sección
function openMenu(seccion) {
    var overlay = document.getElementById('overlay');
    overlay.style.display = 'flex'; // Mostrar el overlay

    var option1 = document.getElementById('option1');
    var option2 = document.getElementById('option2');
    var option3 = document.getElementById('option3');

    switch (seccion) {
        case 'publica':
            option1.textContent = 'Restringir historia';
            option2.textContent = 'Editar historia';
            option3.textContent = 'Compartir historia';
            break;
        case 'restringida':
            option1.textContent = 'Publicar historia';
            option2.textContent = 'Editar historia';
            option3.textContent = 'Compartir historia';
            break;
        case 'borrador':
            option1.textContent = 'Publicar historia';
            option2.textContent = 'Editar historia';
            option3.textContent = 'Restringir historia';
            break;
        default:
            break;
    }
}

// Función para cerrar el menú desplegable
function closeMenu() {
    var overlay = document.getElementById('overlay');
    overlay.style.display = 'none';
}
