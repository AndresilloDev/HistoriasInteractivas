function scrollToNextSection() {
    // Verifica si la URL actual contiene "index.jsp"
    if (window.location.pathname.includes('index.jsp')) {
        const nextSection = document.querySelector('.two');
        if (nextSection) {
            nextSection.scrollIntoView({ behavior: 'smooth' });
        }
    } else {
        // Si no estás en index.jsp, redirige a index.jsp
        window.location.href = 'index.jsp#storieContent';
    }
}