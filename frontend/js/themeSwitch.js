const themeSwitch = document.getElementById('themeSwitch');
const themeIcon = document.getElementById('themeIcon');
const body = document.body;

themeSwitch.addEventListener('click', () => {
    // const body.classList.toggle('light-mode');

    // if (body.classList.contains('light-mode')) {
    //     themeIcon.src = 'resources/img/sunIcon.png';
    //     themeIcon.style.filter = 'invert(0)';
    //     themeIcon.style.padding = '0 0 0 0';
    // } else {
    //     themeIcon.src = 'resources/img/moonIcon.png';
    //     themeIcon.style.filter = 'invert(1)';
    //     themeIcon.style.padding = '0 0 0 2px';
    // }

    const body = document.body;

    if (body.classList.contains('light-mode')) {
        body.classList.remove('light-mode');
        body.classList.add('dark-mode');
        themeIcon.src = 'resources/img/sunIcon.png';
        themeIcon.style.filter = 'invert(1)';
        themeIcon.style.padding = '0 0 0 0';
    } else {
        body.classList.remove('dark-mode');
        body.classList.add('light-mode');
        themeIcon.src = 'resources/img/moonIcon.png';
        themeIcon.style.filter = 'invert(0)';
        themeIcon.style.padding = '0 0 0 2px';
    }
});
