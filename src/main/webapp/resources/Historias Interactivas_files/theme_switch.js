const themeSwitch = document.getElementById('themeSwitch');
const themeIcon = document.getElementById('themeIcon');
const body = document.body;

// Cargar el tema al iniciar la página (en AMBOS archivos)
const savedTheme = localStorage.getItem('theme');
if (savedTheme === 'dark') {
    body.classList.remove('light-mode');
    body.classList.add('dark-mode');
    themeIcon.src = '../resources/img/sunIcon.png';
    themeIcon.style.filter = 'invert(1)';
    themeIcon.style.padding = '0 0 0 0';
}

themeSwitch.addEventListener('click', () => {
    const body = document.body;

    if (body.classList.contains('light-mode')) {
        body.classList.remove('light-mode');
        body.classList.add('dark-mode');
        themeIcon.src = '../resources/img/sunIcon.png';
        themeIcon.style.filter = 'invert(1)';
        themeIcon.style.padding = '0 0 0 0';
        localStorage.setItem('theme', 'dark'); // Guardar en localStorage
    } else {
        body.classList.remove('dark-mode');
        body.classList.add('light-mode');
        themeIcon.src = '../resources/img/moonIcon.png';
        themeIcon.style.filter = 'invert(0)';
        themeIcon.style.padding = '0 0 0 2px';
        localStorage.setItem('theme', 'light'); // Guardar en localStorage
    }
});
