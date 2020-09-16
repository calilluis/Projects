
//----------LOGIN------------
function getcontent() {
    var username = document.getElementById("usernameInput").value;
    localStorage.setItem("username", username);
    var password = document.getElementById("passwordInput").value;
    localStorage.setItem("password", password);
}
Storage.prototype.setObj = function(key, obj) {
    return this.setItem(key, JSON.stringify(obj))
};

function getinterests() {
    var interests=[];
    for (var i = 1; i < 8; i++) {
        var box = document.getElementById("box-" + i);
        console.log(box.className);
        if(box.checked===true){
            console.log(box.className);
            interests.push(box.className);
        }
    }
    localStorage.setObj("interests", interests);
    window.location.href = 'main.html';
}

//---------------------------
