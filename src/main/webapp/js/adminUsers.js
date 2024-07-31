document.addEventListener('DOMContentLoaded', function() {
    console.log('El DOM ha sido completamente cargado y analizado');
    fetch('/Historias_Interactivas_war_exploded/adminUsers')
        .then(response => {
            if (response.ok) {
                console.log('Salio bien :D');
                return response.text();
            }
            throw new Error('Error en la solicitud: ' + response.statusText);
        })
        .then(data => {
            if (!localStorage.getItem('pageReloaded')) {
                // Establecer un marcador en localStorage para indicar que la página se ha recargado
                localStorage.setItem('pageReloaded', 'true');
                location.reload();

            } else {
                // Eliminar el marcador para permitir recargas futuras si es necesario
                localStorage.removeItem('pageReloaded');
            }
        })
        .catch(error => console.error('Error:', error));
});