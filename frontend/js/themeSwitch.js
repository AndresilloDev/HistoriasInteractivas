const themeSwitch = document.getElementById('themeSwitch');
const themeIcon = document.getElementById('themeIcon');
const body = document.body;

themeSwitch.addEventListener('click', () => {
    body.classList.toggle('light-mode');

    if (body.classList.contains('light-mode')) {
        themeIcon.src = 'https://static.thenounproject.com/png/765894-200.png';
        themeIcon.style.filter = 'invert(0)';
    } else {
        themeIcon.src = 'https://static.thenounproject.com/png/4629509-200.png';
        themeIcon.style.filter = 'invert(1)';
    }
});
