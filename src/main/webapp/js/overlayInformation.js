// Función para abrir el menú desplegable y ajustar opciones según la sección
function openMenu(seccion, title, date, description, thumbnail, url, id_story) {
    var overlay = document.getElementById('overlay');

    // Mostrar el overlay
    overlay.classList.add('show');

    // Actualizar el contenido de la historia
    document.getElementById('story-title').innerText = title;
    document.getElementById('thumbnail').src = thumbnail;
    document.getElementById('date').innerText = date;
    document.getElementById('description').innerText = description;

    // Actualizar las opciones del menú según la sección
    var option1 = document.getElementById('option1');
    var option2 = document.getElementById('option2');
    var option3 = document.getElementById('option3');

    switch (seccion) {
        case 'publica':
            option1.textContent = 'Restringir historia';
            break;
        case 'restringida':
            option1.textContent = 'Publicar historia';
            break;
        case 'borrador':
            option1.textContent = 'Publicar historia';
            break;
    }

    option2.textContent = 'Editar historia';
    option3.textContent = 'Compartir historia';

    option2.onclick = function() {
        window.location.href = url;
    };

    // Agregar el evento para copiar el id_story al portapapeles en option3
    option3.onclick = function() {
        navigator.clipboard.writeText(id_story)
            .then(() => {
                alert('ID de la historia copiado al portapapeles');
            })
            .catch(err => {
                console.error('Error al copiar: ', err);
            });
    };
}

// Función para cerrar el menú desplegable
function closeMenu() {
    var overlay = document.getElementById('overlay');

    // Ocultar el overlay
    overlay.classList.remove('show');
}