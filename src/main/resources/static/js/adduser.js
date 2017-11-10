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

function checkage() {
  var userage=document.getElementById('age').value;
  var ageTip=document.getElementById('ageTip');
  var reg=/^[0-9][1-9]{0,2}$/;
  if (reg.test(userage)) {
    ageTip.innerHTML="Available".fontcolor("green");
    return true;
  }else{
    ageTip.innerHTML="Malformed".fontcolor("red");
     return false;
  }
}

function checkphone() {
  var userphone=document.getElementById('phone').value;
  var phoneTip=document.getElementById('phoneTip');
  var reg=/^[1][0-9]{10}$/;
  if (reg.test(userphone)) {
    phoneTip.innerHTML="Available".fontcolor("green");
    return true;
  }else{
    phoneTip.innerHTML="Malformed".fontcolor("red");
     return false;
  }
}

function checkemail() {
  var useremail=document.getElementById('email').value;
  var emailTip=document.getElementById('emailTip');
  var reg=/^[a-zA-Z0-9_]+@[a-zA-Z0-9]+(\.[a-zA-Z]+)+$/;
  if (reg.test(useremail)) {
    emailTip.innerHTML="Available".fontcolor("green");
    return true;
  }else{
    emailTip.innerHTML="Malformed".fontcolor("red");
     return false;
  }
}

function checkAll(){
  return !!(checkname() && checkpassword());
}

function demandname(){
  var nameTip=document.getElementById('nameTip');
  nameTip.innerHTML="2-6 characters";
}

function demandpass(){
  var passTip=document.getElementById('passTip');
  passTip.innerHTML="6-12 characters";
}

function demandage(){
  var ageTip=document.getElementById('ageTip');
  ageTip.innerHTML="1-3 numbers";
}

function demandphone(){
  var phoneTip=document.getElementById('phoneTip');
  phoneTip.innerHTML="11 valid digits";
}

function demandemail(){
  var emailTip=document.getElementById('emailTip');
  emailTip.innerHTML="Valid email address";
}