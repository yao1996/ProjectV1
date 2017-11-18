window.onload=function(){
  var arr=document.getElementsByClassName('qq');
  for (var i = 0;i < arr.length; i++) {
    arr[i].onclick=function(){
       window.location.href="detail.html";
    }
  }

  document.getElementById("myClick").onclick=function(){
    var value=document.getElementById("inner").value;
    window.location.href="xxx?value="+value;
  }


  document.getElementById("BookID").onclick=function(){
            alert("ok");
            // var in=document.getElementById("my_change"); 
            // in.innerHTML="";
            // var input=document.createElement('input');
            // input.type="text";
            // input.name="BookID";
            // input.class="my_input1";
            // in.appendChild(input);
       }

}