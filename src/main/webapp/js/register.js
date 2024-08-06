const labelEmail = document.getElementById("email");
labelEmail.addEventListener("keypress", (e) => {
    const allowedCharacters = /[a-zA-Z0-9@.]/;
    if(!allowedCharacters.test(e.key)) e.preventDefault();
})

const form = document.getElementById("registerForm");
form.addEventListener("submit", function(event) {
    const password = document.getElementById('password').value;
    const confirmPassword = document.getElementById('confirmPassword').value;
    if (password !== confirmPassword) {
        event.preventDefault(); // Evita que el formulario se envíe
        alert('Las contraseñas no coinciden. Por favor, inténtalo de nuevo.');
    }
});

const emailLabel = document.getElementById("email");
const passwordLabel = document.getElementById("password");
const confirmPasswordLabel = document.getElementById("confirmPassword");

emailLabel.addEventListener("keydown", function (e) {
    if (this.value.length >= 50 && e.key !== "Backspace") {
        e.preventDefault();
    }
})
passwordLabel.addEventListener("keydown", function (e) {
    if (this.value.length >= 50 && e.key !== "Backspace") {
        e.preventDefault();
    }

})
confirmPasswordLabel.addEventListener("keydown", function (e) {
    if (this.value.length >= 50 && e.key !== "Backspace") {
        e.preventDefault();
    }
})
