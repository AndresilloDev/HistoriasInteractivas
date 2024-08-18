document.addEventListener('DOMContentLoaded', function() {
    const themeButton = document.getElementById('themeButton');
    const themeIcon = document.getElementById('themeIcon');
    const body = document.body;
    
    // Verificar que los elementos existen
    console.log('themeButton:', themeButton);
    console.log('themeIcon:', themeIcon);
    
    // Cargar el tema al iniciar la página
    const savedTheme = localStorage.getItem('theme');
    const loginIcon = document.getElementById('loginIcon');
    if (loginIcon) {
        loginIcon.style.filter = 'brightness(0)';
    }
    
    if (savedTheme === 'dark') {
        switchTheme(); // Llamar a switchTheme para aplicar el tema oscuro al cargar
    }
    
    if (themeButton) {
        themeButton.addEventListener('click', () => {
            console.log('Theme button clicked');
            if (document.startViewTransition) {
                document.startViewTransition(() => switchTheme());
            } else {
                switchTheme();
            }
        });
    } else {
        console.error('themeButton not found');
    }
    
    function switchTheme() {
        console.log('Switching theme');
        if (body.classList.contains('light-mode')) {
            body.classList.remove('light-mode');
            body.classList.add('dark-mode');
            themeIcon.src = 'resources/icons/sunIcon.png';
            themeIcon.style.filter = 'invert(1)';
            themeIcon.style.padding = '0 0 0 0';
            localStorage.setItem('theme', 'dark');
            
            const loginIcon = document.getElementById('loginIcon');
            if (loginIcon) {
                loginIcon.style.filter = 'brightness(1000)';
            }
        } else {
            body.classList.remove('dark-mode');
            body.classList.add('light-mode');
            themeIcon.src = 'resources/icons/moonIcon.png';
            themeIcon.style.filter = 'invert(0)';
            themeIcon.style.padding = '0 0 0 2px';
            localStorage.setItem('theme', 'light');
            
            const loginIcon = document.getElementById('loginIcon');
            if (loginIcon) {
                loginIcon.style.filter = 'brightness(0)';
            }
        }
    }
});

// CODIGO PARA EL MODAL DE CREAR HISTORIA
function openCreateStoryModal() {
    // Verificar si la página actual es index.jsp
    if (window.location.pathname.includes('index.jsp')) {
        // Si ya estamos en index.jsp, simplemente abrir el modal
        document.getElementById('storyModal').classList.add('show');
    } else {
        // Si no estamos en index.jsp, redirigir a index.jsp con un parámetro de consulta
        localStorage.setItem('openModal', 'true');
        window.location.href = 'index.jsp';
    }
}

// Función para verificar y abrir el modal cuando la página se carga
function checkAndOpenModal() {
    if (localStorage.getItem('openModal') === 'true') {
        // Limpiar el estado del modal en localStorage
        localStorage.removeItem('openModal');
        // Abrir el modal
        document.getElementById('storyModal').classList.add('show');
    }
}

// Llamar a la función cuando la página se carga
document.addEventListener('DOMContentLoaded', checkAndOpenModal);

function closeStoryModal() {
    document.getElementById('storyModal').classList.remove('show');
}

function previewImage() {
    const input = document.getElementById('coverImage');
    const preview = document.getElementById('thumbnailPreview');
    
    const file = input.files[0];
    const reader = new FileReader();
    
    reader.onload = function(e) {
        preview.src = e.target.result;
    }
    
    if (file) {
        reader.readAsDataURL(file);
    }
}

window.addEventListener('click', (event) => {
    let overlay = document.getElementById('storyModal');
    if (event.target === overlay) {
        closeStoryModal();
    }
});