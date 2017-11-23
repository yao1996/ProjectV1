  window.onload=function(){
  document.getElementById("BookID").onclick=function(){
            var myin=document.getElementById("change"); 
            myin.innerHTML="";
            var input=document.createElement('input');
            input.type="text";
            input.name="bookId";
            input.setAttribute("class","my_input1");
            myin.innerHTML="BookID：";
            myin.appendChild(input);
       }
         document.getElementById("ISBN").onclick=function(){
            var myin=document.getElementById("change"); 
            myin.innerHTML="";
            var input=document.createElement('input');
            input.type="text";
            input.name="isbn13";
            input.setAttribute("class","my_input1");
            myin.innerHTML="Isbn13：";
            myin.appendChild(input);
       }
      document.getElementById('myrecord').onclick=function(){
          window.location.href="/manage/deleteRecord";
      }
  }
