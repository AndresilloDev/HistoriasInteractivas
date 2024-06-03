const data = [
    {
        elementId: "typewriter1",
        words: ["Crea", "Diseña", "Construye", "Imagina", "Sorprende"],
        colors: ["#A364FF", "#0075FF", "#64FFBE", "#DBFF00", "#FF7764"]
    },
    {
        elementId: "typewriter2",
        words: ["Comparte", "Diseña", "Informa", "Soluciona", "Construye"],
        colors: ["#DBFF00", "#FF7764", "#A364FF", "#0075FF", "#64FFBE"]
    },
    {
        elementId: "typewriter3",
        words: ["Sorprende", "Imagina", "Comparte", "Diseña", "Soluciona"],
        colors: ["#0075FF", "#64FFBE", "#A364FF", "#FF7764", "#A364FF"]
    }
];

function typewriterEffect(elementId, words, colors, delay = 0) {
    const element = document.getElementById(elementId);
    let currentWordIndex = 0;
    let currentCharIndex = 0;
    let isDeleting = false;

    function type() {
        const currentWord = words[currentWordIndex];
        const currentColor = colors[currentWordIndex];

        if (isDeleting) {
            element.textContent = currentWord.substring(0, currentCharIndex);
            currentCharIndex--;

            if (currentCharIndex === 0) {
                isDeleting = false;
                currentWordIndex = (currentWordIndex + 1) % words.length;
                setTimeout(type, 500);
            } else {
                setTimeout(type, 50);
            }
        } else {
            element.textContent = currentWord.substring(0, currentCharIndex + 1);
            element.style.color = currentColor;
            currentCharIndex++;

            if (currentCharIndex === currentWord.length) {
                isDeleting = true;
                setTimeout(type, 1000);
            } else {
                setTimeout(type, 100);
            }
        }
    }

    setTimeout(type, delay);
}

data.forEach(item => {
    typewriterEffect(item.elementId, item.words, item.colors, Math.random() * 2000);
});