//Un codigo para pasar a uppercase el codigo del index
// Obtener el campo de entrada por su ID
const inputField = document.getElementById('search');

// Añadir un escuchador de eventos para el evento 'input'
inputField.addEventListener('input', function() {
    // Convertir el texto ingresado a mayúsculas y actualizar el valor del campo
    inputField.value = inputField.value.toUpperCase();
});