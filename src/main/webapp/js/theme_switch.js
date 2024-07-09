const themeButton = document.getElementById('themeButton');
const themeIcon = document.getElementById('themeIcon');
const body = document.body;

// Cargar el tema al iniciar la página (en AMBOS archivos)
const savedTheme = localStorage.getItem('theme');
if (savedTheme === 'dark') {
    body.classList.remove('light-mode');
    body.classList.add('dark-mode');
    themeIcon.src = 'resources/img/sun_icon.png';
    themeIcon.style.filter = 'invert(1)';
    themeIcon.style.padding = '0 0 0 0';
}

themeButton.addEventListener('click', () => {
    const body = document.body;

    if (body.classList.contains('light-mode')) {
        body.classList.remove('light-mode');
        body.classList.add('dark-mode');
        themeIcon.src = 'resources/img/sun_icon.png';
        themeIcon.style.filter = 'invert(1)';
        themeIcon.style.padding = '0 0 0 0';
        localStorage.setItem('theme', 'dark'); // Guardar en localStorage
    } else {
        body.classList.remove('dark-mode');
        body.classList.add('light-mode');
        themeIcon.src = 'resources/img/moon_icon.png';
        themeIcon.style.filter = 'invert(0)';
        themeIcon.style.padding = '0 0 0 2px';
        localStorage.setItem('theme', 'light'); // Guardar en localStorage
    }
});

//Un codigo para pasar a uppercase el codigo del index
// Obtener el campo de entrada por su ID
const inputField = document.getElementById('search');

// Añadir un escuchador de eventos para el evento 'input'
inputField.addEventListener('input', function() {
    // Convertir el texto ingresado a mayúsculas y actualizar el valor del campo
    inputField.value = inputField.value.toUpperCase();
});