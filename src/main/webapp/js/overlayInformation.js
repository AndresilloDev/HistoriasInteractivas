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

    switch (seccion) {
        case 'publica':
            option1.textContent = 'Restringir historia';
            option2.textContent = 'Compartir historia';
            break;
        case 'restringida':
            option1.textContent = 'Publicar historia';
            option2.textContent = 'Editar historia';
            break;
        case 'borrador':
            option1.textContent = 'Publicar historia';
            option2.textContent = 'Editar historia';
            break;
    }

    // Agregar el evento para copiar el id_story al portapapeles en option3
    option2.onclick = function() {
        if (option2.textContent === "Compartir historia"){
            navigator.clipboard.writeText(id_story)
                .then(() => {
                    alert('ID de la historia copiado al portapapeles');
                })
                .catch(err => {
                    console.error('Error al copiar: ', err);
                });
        } else if (option2.textContent === "Editar historia"){
            window.location.href = url;
        }
    };
}

// Función para cerrar el menú desplegable
function closeMenu() {
    var overlay = document.getElementById('overlay');

    // Ocultar el overlay
    overlay.classList.remove('show');
}