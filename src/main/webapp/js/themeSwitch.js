document.addEventListener('DOMContentLoaded', function() {
    const themeButton = document.getElementById('themeButton');
    const themeIcon = document.getElementById('themeIcon');
    const body = document.body;

    // Cargar el tema al iniciar la página (en AMBOS archivos)
    const savedTheme = localStorage.getItem('theme');
    if (savedTheme === 'dark') {
        switchTheme(); // Llamar a switchTheme para aplicar el tema oscuro al cargar
    }

    themeButton.addEventListener('click', () => {
        if (document.startViewTransition) {
            document.startViewTransition(() => switchTheme());
        } else {
            switchTheme();
        }
    });

    function switchTheme() {
        if (body.classList.contains('light-mode')) {
            body.classList.remove('light-mode');
            body.classList.add('dark-mode');
            themeIcon.src = 'resources/icons/sunIcon.png';
            themeIcon.style.filter = 'invert(1)';
            themeIcon.style.padding = '0 0 0 0';
            localStorage.setItem('theme', 'dark');
        } else {
            body.classList.remove('dark-mode');
            body.classList.add('light-mode');
            themeIcon.src = 'resources/icons/moonIcon.png';
            themeIcon.style.filter = 'invert(0)';
            themeIcon.style.padding = '0 0 0 2px';
            localStorage.setItem('theme', 'light');
        }
    }
});
  