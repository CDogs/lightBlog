
//将form转为AJAX提交
function ajaxSubmit(frm, fn) {
    var dataPara = getFormJson(frm);
    //alert(frm.action);
    //console.log(frm.action);
    $.ajax({
        url: frm.action,
        type: frm.method,
        data: dataPara,
        dataType: "text",
        // contentType: "application/json",
        success: fn,
        error: fn
    });
}

//将form中的值转换为键值对。
function getFormJson(frm) {
    var o = {};
    var a = $(frm).serializeArray();
    $.each(a, function () {
        //alert(this.value);
        if (o[this.name] !== undefined) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });

    return o;
}
//登录调用
$(document).ready(function(){

    $('#login').attr('action','/lightBlog/user/login.htm');
    //alert($('input:radio:checked').val());
    $(":radio").change(function(){
       // alert("nihao");
        //alert($('input:radio:checked').val());
        if($('input:radio:checked').val() == "User"){
            $('#login').attr('action','/lightBlog/user/login.htm');
        }else{
            $('#login').attr('action','/lightBlog/admin/login.htm');
        }
    });


    $('#login').bind('submit', function(){
        ajaxSubmit(this, function(data){
            //console.log(data);
            // alert(data.responseText);
            if(data == "ERROR" || data == "INVALID_USER"){
                $("#loginMessage").text("登录失败，请检查账户和密码");
            }else{
                //alert("成功啦");
                window.location.assign(data);
            }
        });
        return false;
    });


    $('#registerForm').bind('submit', function(){
        ajaxSubmit(this, function(data){
            //console.log(data);
            // alert(data.responseText);
            if(data == "ERROR"){
                $("#registerMessage").text("注册失败，请重新注册");
            }else if(data == "INVALID_USER"){
                $("#registerMessage").text("用户名已存在，请重新注册");
            }else{
                //alert("成功啦");
                $("#register").modal('hide');
                $("#log-reg").modal('show');
                $("#loginMessage").text("注册成功，请登录");
                // $("#registerMessage").html("注册成功，现在 <a data-toggle='modal' href='index.ftl#log-reg' class='btn log-reg'>登录</a>");
            }
        });
        return false;
    });
});
<!-- 登录提交脚本结束 -->

