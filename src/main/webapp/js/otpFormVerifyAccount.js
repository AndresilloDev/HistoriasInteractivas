const otpInputs = document.querySelectorAll(".otp-input");

otpInputs.forEach((input, index) => {
    input.addEventListener("input", function() {
        this.value = this.value.replace(/\D/g, ""); // Solo permitir números
        if (this.value !== "") {
            if (index < otpInputs.length - 1) {
                otpInputs[index + 1].focus(); // Pasar al siguiente campo
            } else {
                document.getElementById("otpForm").submit(); // Enviar al completar
            }
        }
    });

    input.addEventListener("keydown", function(event) {
        if (event.key === "Backspace" && this.value === "" && index > 0) {
            otpInputs[index - 1].focus(); // Volver al campo anterior si se borra
        }
    });

    input.addEventListener("keypress", function (event){
        if (this.value !== "") event.preventDefault();
    });
    input.onpaste = function(event) {
        event.preventDefault();
    }
});