document.addEventListener("DOMContentLoaded", function() {
    const searchInput = document.getElementById('search');
    const animatedText = document.querySelector('.animated-text');
    const waves = document.querySelectorAll('.waves-container1 .wave, .waves-container2 .wave');

    function checkConditions() {
        const isMobileView = window.innerWidth <= 767;
        const isSearchFocused = document.activeElement === searchInput;

        if (isMobileView && isSearchFocused) {
            animatedText.style.display = 'none';
            waves.forEach(wave => wave.style.display = 'none');
        } else {
            animatedText.style.display = '';
            waves.forEach(wave => wave.style.display = '');
        }
    }

    // Add event listeners
    searchInput.addEventListener('focus', checkConditions);
    searchInput.addEventListener('blur', checkConditions);
    window.addEventListener('resize', checkConditions);
    window.addEventListener('load', checkConditions);
});


function scrollToNextSection() {
    const nextSection = document.querySelector('.two');
    if (nextSection) {
        nextSection.scrollIntoView({ behavior: 'smooth' });
    }
}