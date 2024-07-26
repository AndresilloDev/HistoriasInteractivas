const labelEmail = document.getElementById("email");
labelEmail.addEventListener("keypress", (e) => {
    const allowedCharacters = /[a-zA-Z0-9@.]/;
    if(!allowedCharacters.test(e.key)) e.preventDefault();
})