function checkname() {
  var username=document.getElementById('username').value;
  var nameTip=document.getElementById('nameTip');
  var reg=/^((?:-?0)|(?:-?[1-9]\d*))(?:\.\d{1,2})?$/;
  if (reg.test(username)) {
    nameTip.innerHTML="Available".fontcolor("green");
    return true;
  }else{
    nameTip.innerHTML="Malformed".fontcolor("red");
    return false;
  }
}

function checkpassword(){
  var userpassword=document.getElementById('userpassword').value;
  var passTip=document.getElementById('passTip');
  var reg=/^[1-9]$/;
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
        alert("the input is illegal");
        return false;
    }
}

function demandname(){
  var nameTip=document.getElementById('nameTip');
  nameTip.innerHTML="Decimal or integer";
}

function demandpass(){
  var passTip=document.getElementById('passTip');
  passTip.innerHTML="Integer";
}
