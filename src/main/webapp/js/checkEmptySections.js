window.addEventListener('DOMContentLoaded', (event) => {
    checkEmptySections();
});

function checkEmptySections() {
    // Verificamos la sección de historias públicas
    let publicStoriesContainer = document.querySelector('.container .card-container');
    if (publicStoriesContainer && publicStoriesContainer.children.length === 0) {
        publicStoriesContainer.innerHTML = '<div class="empty-card">No hay historias públicas por el momento.</div>';
    }

    // Verificamos la sección de historias restringidas
    let restrictedStoriesContainer = document.querySelectorAll('.container .card-container')[1];
    if (restrictedStoriesContainer && restrictedStoriesContainer.children.length === 0) {
        restrictedStoriesContainer.innerHTML = '<div class="empty-card">No hay historias restringidas por el momento.</div>';
    }

    // Verificamos la sección de borradores de historias
    let draftStoriesContainer = document.querySelectorAll('.container .card-container')[2];
    if (draftStoriesContainer && draftStoriesContainer.children.length === 0) {
        draftStoriesContainer.innerHTML = '<div class="empty-card">No hay borradores por el momento.</div>';
    }
}