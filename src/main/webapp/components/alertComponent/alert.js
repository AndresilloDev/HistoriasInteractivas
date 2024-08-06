document.addEventListener('DOMContentLoaded', function () {
    var messageDiv = document.querySelector('.messageAlert');
    if (messageDiv) {
        setTimeout(function() {
            messageDiv.classList.add('show');
        }, 40);

        // Animación de la progressBar
        var progressBar = messageDiv.querySelector('.progress');
        var duration = 2500;
        var startTime = Date.now();

        function updateProgress() {
            var elapsedTime = Date.now() - startTime;
            var progress = Math.min(elapsedTime / duration * 100, 100);
            progressBar.style.width = progress + '%';

            if (progress < 100) {
                requestAnimationFrame(updateProgress);
            } else {
                messageDiv.classList.remove('show');
                messageDiv.classList.add('hide');
                messageDiv.addEventListener('transitionend', function() {
                    messageDiv.classList.remove('hide');
                }, { once: true });
            }
        }
        requestAnimationFrame(updateProgress);
    }
});