const defaultFile = 'resources/img/userIcon.png'
const file = document.getElementById("userPicture");
const img = document.getElementById("img");
file.addEventListener("change", e => {
    if(e.target.files[0]){
        reader = new FileReader();
        reader.onload = function(e) {
            img.src = e.target.result;
        }
        reader.readAsDataURL(e.target.files[0]);
    }else{
        img.src = defaultFile;
    }
});

const userLabel = document.getElementById("user");
const nameLabel = document.getElementById("name");
const paternalNameLabel = document.getElementById("paternalName");
const maternalNameLabel = document.getElementById("maternalName");

userLabel.addEventListener("keydown", function (e) {
    if (this.value.length >= 20 && e.key !== "Backspace") {
        e.preventDefault();
    }
})
nameLabel.addEventListener("keydown", function (e) {
    if (this.value.length >= 20 && e.key !== "Backspace") {
        e.preventDefault();
    }
})
paternalNameLabel.addEventListener("keydown", function (e) {
    if (this.value.length >= 20 && e.key !== "Backspace") {
        e.preventDefault();
    }
})
maternalNameLabel.addEventListener("keydown", function (e) {
    if (this.value.length >= 20 && e.key !== "Backspace") {
        e.preventDefault();
    }
})
