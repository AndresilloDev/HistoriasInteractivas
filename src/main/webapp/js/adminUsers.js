document.addEventListener('DOMContentLoaded', function() {
    // Código para el modal
    const modal = document.getElementById('confirmModal');
    const closeBtn = document.querySelector('.close-btn');
    const confirmYes = document.getElementById('confirmYes');
    const confirmNo = document.getElementById('confirmNo');
    let formToSubmit = null;
    
    // Mostrar el modal
    function showModal(message, form) {
        modal.classList.add('show');
        document.getElementById('modalMessage').innerHTML = message;
        formToSubmit = form;
    }
    
    // Ocultar el modal
    function closeModal() {
        modal.classList.remove('show');
    }
    
    // clic en el botón de confirmar
    confirmYes.addEventListener('click', () => {
        if (formToSubmit) {
            formToSubmit.submit();
        }
        closeModal();
    });
    
    // clic en el botón de cancelar
    confirmNo.addEventListener('click', closeModal);
    
    // clic en el botón de cerrar
    closeBtn.addEventListener('click', closeModal);
    
    // clic fuera del modal
    window.addEventListener('click', (event) => {
        if (event.target === modal) {
            closeModal();
        }
    });
    
    const forms = document.querySelectorAll('form.confirm-form');
    forms.forEach(form => {
        form.addEventListener('submit', (event) => {
            event.preventDefault();
            const action = form.querySelector('input[name="action"]').value;
            const email = form.querySelector('input[name="email"]').value;
            const message = `¿Estás seguro? <br> ${action === 'deshabilitar' ? 'Deshabilitarás' : 'Habilitarás'} al usuario con email <strong>${email}</strong>`;
            showModal(message, form);
        });
    });
});