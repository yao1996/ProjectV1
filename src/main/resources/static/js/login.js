function checkname() {
    var username=document.getElementById('username').value;
    var nameTip=document.getElementById('nameTip');
    var reg=/^[a-zA-Z0-9]{2,6}$/;
    if (reg.test(username)) {
        nameTip.innerHTML="Available".fontcolor("green");
        return true;
    }else{
        nameTip.innerHTML="Malformed".fontcolor("red");
        return false;
    }
}

function checkpassword(){
    var userpassword=document.getElementById('password').value;
    var passTip=document.getElementById('passTip');
    var reg=/^[a-zA-Z0-9]{6,12}$/;
    if (reg.test(userpassword)) {
        passTip.innerHTML="Available".fontcolor("green");
        return true;
    }else{
        passTip.innerHTML="Malformed".fontcolor("red");
        return false;
    }
}



function checkAll(){
    if (checkname() && checkpassword()) {
        return true;
    } else{
        return false;
    }
}

function demandname(){
    var nameTip=document.getElementById('nameTip');
    nameTip.innerHTML="2-6 characters";
}

function demandpass(){
    var passTip=document.getElementById('passTip');
    passTip.innerHTML="6-12 characters";
}
