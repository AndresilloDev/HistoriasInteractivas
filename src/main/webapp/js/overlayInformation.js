// Función para abrir el menú desplegable y ajustar opciones según la sección
function openMenu(seccion, id, title, date, description, thumbnail) {
    var overlay = document.getElementById('overlay');
    overlay.style.display = 'flex'; // Mostrar el overlay

    // Actualizar el contenido de la historia
    document.querySelector('#overlay h2').innerText = title;
    document.querySelector('#overlay .text-md-end').innerHTML = '<strong>Fecha de publicación:</strong><br>' + date;
    document.querySelector('#overlay .img-fluid').src = thumbnail;
    document.querySelector('#overlay .text-md-start + p').innerText = description;

    // Actualizar las opciones del menú según la sección
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
    option2.onclick = function() {
        window.location.href = `/Historias_Interactivas_war_exploded/createStory.jsp?id_Story=${id}`;
    };
}

// Función para cerrar el menú desplegable
function closeMenu() {
    var overlay = document.getElementById('overlay');
    overlay.style.display = 'none';
}
