// Función para abrir el menú desplegable y ajustar opciones según la sección
function openMenu(seccion, title, date, description, thumbnail, url, id_story) {
    var overlay = document.getElementById('overlay');
    
    // Mostrar el overlay
    overlay.classList.add('show');
    console.log(date)
    
    // Actualizar el contenido de la historia
    document.getElementById('story-title').innerText = title;
    document.getElementById('thumbnail').src = thumbnail;
    document.getElementById('description').innerText = description;
    
    var basePath = window.location.origin + window.location.pathname.substring(0, window.location.pathname.lastIndexOf('/'));
    var fullUrl = basePath + "/createStory?id_story=" + id_story;

    // Actualizar las opciones del menú según la sección
    var option1 = document.getElementById('option1');
    var option2 = document.getElementById('option2');
    
    switch (seccion) {
        case 'publica':
            document.getElementById('date').innerText = date;
            option1.textContent = 'Restringir historia';
            option1.onclick = function() {
                updateStoryStatus(id_story, 'restringir');
            };
            option2.textContent = 'Compartir historia';
            option2.onclick = function() {
                openShareModal(id_story, fullUrl);
            };
            break;
        case 'restringida':
            document.getElementById('date-p').innerText = "";
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
            document.getElementById('date-p').innerText = "";
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

// Función para actualizar el estado de la historia
function updateStoryStatus(id_story, action) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "updateStoryStatus", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                // Estado de la historia actualizado correctamente
                location.reload();
                showAlert('Error al actualizar el estado de la historia.', false);
            } else {
                // Error al actualizar el estado de la historia
                showAlert('Error al actualizar el estado de la historia.', true);
            }
        }
    };
    
    xhr.send("id_story=" + encodeURIComponent(id_story) + "&action=" + encodeURIComponent(action));
}

// Función para mostrar la alerta personalizada
function showAlert(message, isError = false) {
    var alertContainer = document.getElementById('alertContainer');
    
    var alertHtml = `
        <div class="messageAlert">
            <div>${message}</div>
            <div class="progressBar">
                <div class="progress"></div>
            </div>
        </div>
    `;
    
    alertContainer.innerHTML = alertHtml;
    
    var messageDiv = alertContainer.querySelector('.messageAlert');
    
    if (messageDiv) {
        setTimeout(function() {
            messageDiv.classList.add('show');
        }, 40);
        
        // Animación de la progressBar
        var progressBar = messageDiv.querySelector('.progress');
        var duration = 2500;
        var startTime = Date.now();
        
        function updateProgress() {
            var elapsedTime = Date.now() - startTime;
            var progress = Math.min(elapsedTime / duration * 100, 100);
            progressBar.style.width = progress + '%';
            
            if (progress < 100) {
                requestAnimationFrame(updateProgress);
            } else {
                messageDiv.classList.remove('show');
                messageDiv.classList.add('hide');
                messageDiv.addEventListener('transitionend', function() {
                    messageDiv.remove();
                }, { once: true });
            }
        }
        
        requestAnimationFrame(updateProgress);
    }
}

// Función para abrir el modal de compartir
function openShareModal(id_story, fullUrl) {
    var shareModal = document.getElementById('shareModal');
    shareModal.style.display = 'block';
    
    // Añadir clase para animación
    setTimeout(() => {
        shareModal.classList.add('show');
    }, 10);
    
    // Botón para copiar el ID
    document.getElementById('copyIdBtn').onclick = function() {
        navigator.clipboard.writeText(id_story)
            .then(() => showAlert('ID de la historia copiado al portapapeles'))
            .catch(err => showAlert('Error al copiar el ID: ' + err, true));
    };
    
    // Botón para copiar el enlace completo
    document.getElementById('copyLinkBtn').onclick = function() {
        navigator.clipboard.writeText(fullUrl)
            .then(() => showAlert('Enlace completo copiado al portapapeles'))
            .catch(err => showAlert('Error al copiar el enlace: ' + err, true));
    };
    
    // Botón para cerrar el modal
    document.getElementById('closeShareModal').onclick = function() {
        shareModal.classList.remove('show');
        setTimeout(() => {
            shareModal.style.display = 'none';
        }, 300); // Tiempo de la transición antes de ocultarlo
    };
}

// Función para cerrar el menú y el modal de compartir
function closeMenu() {
    var overlay = document.getElementById('overlay');
    var shareModal = document.getElementById('shareModal');
    
    if (overlay) {
        overlay.classList.remove('show');
    }
    
    if (shareModal) {
        shareModal.classList.remove('show');
        setTimeout(() => {
            shareModal.style.display = 'none';
        }, 300);
    }
}

// Cerrar el modal si se hace clic fuera de él
window.onclick = function(event) {
    var overlay = document.getElementById('overlay');
    var shareModal = document.getElementById('shareModal');
    
    if (event.target === overlay) {
        closeMenu();
    } else if (event.target === shareModal) {
        closeMenu();
    }
};