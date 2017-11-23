window.onload=function() {
    var checkname=document.getElementById('username').onblur=function(){
    var username = document.getElementById('username').value;
    var nameTip = document.getElementById('nameTip');
    var reg = /^[0-9]{10,12}$/;
        if (reg.test(username)) {
            nameTip.innerHTML = "Available".fontcolor("green");
            return true;
        } else {
            nameTip.innerHTML = "Malformed".fontcolor("red");
            return false;
        }
     }
    var checkpassword=document.getElementById('userpassword').onblur=function(){
            var userpassword = document.getElementById('userpassword').value;
            var passTip = document.getElementById('passTip');
            var reg = /^[a-zA-Z0-9]{6,12}$/;
            if (reg.test(userpassword)) {
                 passTip.innerHTML = "Available".fontcolor("green");
                return true;
            } else {
                passTip.innerHTML = "Malformed".fontcolor("red");
                return false;
            }
        }
        document.getElementById('username').onfocus=function(){
         var nameTip = document.getElementById('nameTip');
         nameTip.innerHTML = "student ID";
        }
        document.getElementById('userpassword').onfocus=function(){
          var passTip = document.getElementById('passTip');
          passTip.innerHTML = "6-12 acharacters";
        }
        document.getElementById('jiaoyan').onsubmit=function(){
             if (checkname() && checkpassword()) {
                return true;
            } else {
                return false;
            }
        }
    document.getElementById('reader').onclick=function(){
        var checkname=document.getElementById('username').onblur=function(){
            var username = document.getElementById('username').value;
            var nameTip = document.getElementById('nameTip');
            var reg = /^[0-9]{10,12}$/;
            if (reg.test(username)) {
                nameTip.innerHTML = "Available".fontcolor("green");
                return true;
            } else {
                nameTip.innerHTML = "Malformed".fontcolor("red");
                return false;
            }
        }
        var checkpassword=document.getElementById('userpassword').onblur=function(){
             var userpassword = document.getElementById('userpassword').value;
            var passTip = document.getElementById('passTip');
            var reg = /^[a-zA-Z0-9]{6,12}$/;
            if (reg.test(userpassword)) {
                 passTip.innerHTML = "Available".fontcolor("green");
                return true;
            } else {
                passTip.innerHTML = "Malformed".fontcolor("red");
                return false;
            }
        }
        document.getElementById('username').onfocus=function(){
         var nameTip = document.getElementById('nameTip');
         nameTip.innerHTML = "student ID";
        }
        document.getElementById('userpassword').onfocus=function(){
          var passTip = document.getElementById('passTip');
          passTip.innerHTML = "6-12 acharacters";
        }
        document.getElementById('jiaoyan').onsubmit=function(){
             if (checkname() && checkpassword()) {
                return true;
            } else {
                return false;
            }
        }
    }


     document.getElementById('admin').onclick=function(){
        var checkname=document.getElementById('username').onblur=function(){
            var username = document.getElementById('username').value;
            var nameTip = document.getElementById('nameTip');
            var reg = /^[a-zA-Z0-9]{1,12}$/;
            if (reg.test(username)) {
                nameTip.innerHTML = "Available".fontcolor("green");
                return true;
            } else {
                nameTip.innerHTML = "Malformed".fontcolor("red");
                return false;
            }
        }
        var checkpassword=document.getElementById('userpassword').onblur=function(){
             var userpassword = document.getElementById('userpassword').value;
            var passTip = document.getElementById('passTip');
            var reg = /^[a-zA-Z0-9]{4,12}$/;
            if (reg.test(userpassword)) {
                 passTip.innerHTML = "Available".fontcolor("green");
                return true;
            } else {
                passTip.innerHTML = "Malformed".fontcolor("red");
                return false;
            }
        }
        document.getElementById('username').onfocus=function(){
         var nameTip = document.getElementById('nameTip');
         nameTip.innerHTML = "Admin Name";
        }
        document.getElementById('userpassword').onfocus=function(){
          var passTip = document.getElementById('passTip');
          passTip.innerHTML = "4-12 characters";
        }
       document.getElementById('jiaoyan').onsubmit=function(){
             if (checkname() && checkpassword()) {
                return true;
            } else {
                return false;
            }
        }
    } 
}



