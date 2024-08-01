document.addEventListener('DOMContentLoaded', function() {
    const themeButton = document.getElementById('themeButton');
    const themeIcon = document.getElementById('themeIcon');
    const loginIcon = document.getElementById('loginIcon');
    const body = document.body;
    
    // Verificar que los elementos existen
    console.log('themeButton:', themeButton);
    console.log('themeIcon:', themeIcon);
    console.log('loginIcon:', loginIcon);
    
    // Cargar el tema al iniciar la página
    const savedTheme = localStorage.getItem('theme');
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
            if (loginIcon) {
                loginIcon.style.filter = 'brightness(0)';
            }
        } else {
            body.classList.remove('dark-mode');
            body.classList.add('light-mode');
            themeIcon.src = 'resources/icons/moonIcon.png';
            themeIcon.style.filter = 'invert(0)';
            themeIcon.style.padding = '0 0 0 2px';
            localStorage.setItem('theme', 'light');
            if (loginIcon) {
                loginIcon.style.filter = 'brightness(1000)';
            }
        }
    }
});