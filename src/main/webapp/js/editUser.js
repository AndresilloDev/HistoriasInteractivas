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