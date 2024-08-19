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

    var basePath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/'));
    var fullUrl = basePath + url;
    console.log(fullUrl)

    // Actualizar las opciones del menú según la sección
    var option1 = document.getElementById('option1');
    var option2 = document.getElementById('option2');

    switch (seccion) {
        case 'publica':
            option1.textContent = 'Restringir historia';
            option1.onclick = function() {
                updateStoryStatus(id_story, 'restringir');
            };
            option2.textContent = 'Compartir historia';
            option2.onclick = function() {
                navigator.clipboard.writeText(id_story)
                    .then(() => {
                        alert('ID de la historia copiado al portapapeles');
                    })
                    .catch(err => {
                        console.error('Error al copiar: ', err);
                    });
            };
            break;
        case 'restringida':
            option1.textContent = 'Publicar historia';
            option1.onclick = function() {
                updateStoryStatus(id_story, 'publicar');
            };
            option2.textContent = 'Editar historia';
            option2.onclick = function() {
                window.location.href = fullUrl;
            };
            break;
        case 'borrador':
            option1.textContent = 'Publicar historia';
            option1.onclick = function() {
                updateStoryStatus(id_story, 'publicar');
            };
            option2.textContent = 'Editar historia';
            option2.onclick = function() {
                window.location.href = fullUrl;
            };
            break;
    }
}

function updateStoryStatus(id_story, action) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "updateStoryStatus", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");

    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                location.reload();
            } else {
                location.reload();
            }
        }
    };

    xhr.send("id_story=" + encodeURIComponent(id_story) + "&action=" + encodeURIComponent(action));
}

// Función para cerrar el menú desplegable
function closeMenu() {
    var overlay = document.getElementById('overlay');

    // Ocultar el overlay
    overlay.classList.remove('show');
}

window.addEventListener('click', (event) => {
    if (event.target === overlay) {
        closeMenu();
    }
});