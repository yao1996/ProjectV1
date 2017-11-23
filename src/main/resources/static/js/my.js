function my(data) {
    window.location.href = "/detail/" + data;
}

window.onload = function () {
    // var arr=document.getElementsByClassName('qq');
    // for (var i = 0;i < arr.length; i++) {
    //   arr[i].onclick=function(){
    //      window.location.href="detail.html";
    //   }
    // }

    document.getElementById("myClick").onclick = function () {
        var value = document.getElementById("inner").value;
        if (value === "" || value === " ") {
            alert("can not be null!");
        } else {
            window.location.href = "/?search=" + value;
        }
    };

};