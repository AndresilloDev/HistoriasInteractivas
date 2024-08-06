const passwordLabel = document.getElementById("password");

passwordLabel.addEventListener("keydown", function (e) {
    if (this.value.length >= 50 && e.key !== "Backspace") {
        e.preventDefault();
    }
})