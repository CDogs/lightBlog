<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>个人信息</title>

    <link rel="shortcut icon" href="${rc.contextPath}/images/favicon.ico" type="image/x-icon" />
    <!-- Bootstrap core CSS -->
    <link rel="icon" href="${rc.contextPath}/images/bokeicon.png">

    <link href="${rc.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/user_info.css" rel="stylesheet">

    <link href="${rc.contextPath}/css/user-page.css" rel="stylesheet">

    <link href="${rc.contextPath}/css/navbar.css" rel="stylesheet">

</head>

<body>
<div class="body_type">
<#include "../layout/front_nav.ftl">
    <div class="container">
        <!-- Static navbar -->

        <!--顶部菜单兰结束-->

        <!--个人模块实现-->

        <div class="infoContainer">
            <div class="col-sm-2"></div>

            <!--一个信息模块开始-->
            <div class="col-sm-8">
                <div class="panel panel-default">



                        <div class="panel-body">
                            <div class="user_info">
                                <input class="file_style" type="file" id="file" name="file">
<#if user.image?exists>
                                <img id="image" src="${rc.contextPath}/${user.image}" onclick="onfile()">
<#else>
    <img id="image" src="${rc.contextPath}/images/portriat.jpg" onclick="onfile()">
</#if>
                            </div>
                            <button class="btn btn-default text-center hidden" id="upload">&nbsp;&nbsp;确定修改</button>
                        </div>
                    <form action="${rc.contextPath}/user/update.htm" method="post" enctype="multipart/form-data" id="myform">

                        <input type="hidden" name="id" value="${user.id}">
                        <div class="panel-body text-center">
                            <span>名称：</span>
                            <input type="text" style="border: none" name="name" value="${user.name}">
                        </div>
                        <div class="panel-body text-center">
                            <span>昵称：</span>
                            <input type="text" style="border: none" name="showName" value="${user.showName!''}">
                        </div>
                        <div class="panel-body text-center">
                            <span>邮箱：</span>
                            <input type="text" style="border: none" name="email" value="${user.email!''}">
                        </div>
                        <div class="panel-body text-center">
                            <span>描述：</span>
                            <input type="text" style="border: none" name="description" value="${user.description!''}">
                        </div>
                        <div class="panel-body">
                            <button type="submit" class="btn btn-default"><img src="${rc.contextPath}/images/write.png">修改</button>
                        </div>
                    </form>


                </div>
            </div><!--/.col-xs-12.col-sm-9-->
            <!--一个信息模块结束-->

            <div class="col-sm-2"></div>
            <hr/>
        </div>
        <!--个人模块结束-->
    </div> <!-- /container -->

</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${rc.contextPath}/js/jquery-1.11.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${rc.contextPath}/js/bootstrap.min.js"></script>

<script src="${rc.contextPath}/js/edit.js"></script>
<script src="${rc.contextPath}/js/ajaxfileupload.js"></script>

<script>
    function onfile() {
        document.getElementById("file").click();
    }
</script>

<script>
    //将form转为AJAX提交
    function ajaxSubmit(frm, fn) {
        var data0 = getFormJson(frm);
        //alert(data0);
        var dataPara = {'user': data0};
        //console.log(dataPara);
        $.ajax({
            url: frm.action,
            type: frm.method,
            data: JSON.stringify(dataPara),
            //dataType: "json",
            contentType: "application/json",
            success: fn
        });
    }

    //将form中的值转换为键值对。
    function getFormJson(frm) {
        var o = {};
        var a = $(frm).serializeArray();
        $.each(a, function () {
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
    //调用
    $(document).ready(function(){
        $('#myform').bind('submit', function(){
            ajaxSubmit(this, function(data){
                //alert(data);
                console.log(data);
                if(data == "SUCCESS"){
                    alert("修改成功");
                }else{
                    alert("修改失败");
                }
            });
            return false;
        });
    });
    //图片上传预览
    $("#file").change(function(){
        var objUrl = getObjectURL(this.files[0]) ;
        console.log("objUrl = "+objUrl) ;
        if (objUrl) {
            $("#image").attr("src", objUrl) ;
            $("#upload").removeClass("hidden");
        }
    }) ;
    //建立一個可存取到該file的url
    function getObjectURL(file) {
        var url = null ;
        if (window.createObjectURL!=undefined) { // basic
            url = window.createObjectURL(file) ;
        } else if (window.URL!=undefined) { // mozilla(firefox)
            url = window.URL.createObjectURL(file) ;
        } else if (window.webkitURL!=undefined) { // webkit or chrome
            url = window.webkitURL.createObjectURL(file) ;
        }
        return url ;
    }
    //图片上传
    $("#upload").click(function(){
        $.ajaxFileUpload({
                    url:'portrait_upload.htm',
                    dataType : 'text',
                    fileElementId:'file',
                    success: function (data, status){

                        if("SUCCESS" == data){
                            alert("上传成功！")
                        }else{
                            alert("上传失败！")
                        }


                    },
                    error: function (data, status)
                    {
                        alert("失败" + data);
                    }
                }
        )});
</script>

</body>
</html>
