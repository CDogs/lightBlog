function checkid() {
    var text1 = document.getElementById("inputName");
    var text2 = document.getElementById("inputPassword");
    if(text1.value == ""){
        alert("账号为空！");
    }else if(text2.value ==""){
        alert("密码为空！");
    }
}
function checkid1() {
    var text1 = document.getElementById("registerName");
    var text2 = document.getElementById("registerPassword");
    var text3 = document.getElementById("re-input");
    if(text1.value == ""){
        alert("账号为空！");
    }else if(text2.value ==""){
        alert("密码为空！");
    }else if(text3.value ==""){
        alert("密码为空！");
    }else if(text2.value != text3.value){
        alert("两次输入的密码不一致，请重新输入");
        text3.value = "";
        document.getElementById("re-input").onfocus();
    }else {
        //document.getElementById("register-btn").setAttribute("data-dismiss","modal");
        //document.getElementById("register-btn").setAttribute("href","#log-reg");
    }
}



function s(e,a) {
    if(e && e.preventDefault)
        e.preventDefault();
    else
        window.event.returnValue = false;
    a.focus();
}