// Simulación del estado de inicio de sesión
var isLoggedIn = false; // true si el usuario está autenticado

if (isLoggedIn) {
  loginButton.style.display = 'none';
  profileDropdown.style.display = 'block';
} else {
  loginButton.style.display = 'block';
  profileDropdown.style.display = 'none';
}