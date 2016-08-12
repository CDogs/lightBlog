<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>博客系统后台管理</title>
    <link rel="shortcut icon" href="${rc.contextPath}/images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="${rc.contextPath}/css/main.css"/>
    <script type="text/javascript" src="${rc.contextPath}/js/libs/modernizr.min.js"></script>
</head>
<body>
<#include "../layout/admin_front.ftl">
<div class="container clearfix">
<#include "../layout/admin_sidebar.ftl">
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="#">常用操作</a><span class="crumb-step">&gt;</span><span>发布公告</span></div>
        </div>
        <div class="result-wrap" style="padding: 5%">
            <div class="result-content">
                <form action="${rc.contextPath}/notice/add.htm" method="post" id="myform" name="myform" enctype="multipart/form-data">
                    <table class="insert-tab" width="95%">
                        <tbody>
                        <tr>
                            <th style="text-align: center;" width="10%" ><i class="require-red">*</i>标题：</th>
                            <td>
                                <input class="common-text required" id="title" name="title" size="50" value="" type="text">
                            </td>
                        </tr>

                        <tr>
                            <th style="text-align: center"><i class="require-red">*</i>内容：</th>
                            <td><textarea name="content" class="common-textarea" id="content" cols="30" style="width: 98%;" rows="10"></textarea></td>
                        </tr>
                        <tr>
                            <th></th>
                            <td>
                                <input class="btn btn-primary btn6 mr10" value="发布" type="submit">
                            </td>
                        </tr>
                        </tbody></table>
                </form>
            </div>
        </div>

    </div>
</div>
</body>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${rc.contextPath}/js/jquery-1.11.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${rc.contextPath}/js/bootstrap.min.js"></script>
<script src="${rc.contextPath}/javascripts/common.js"></script>
<script src="${rc.contextPath}/javascripts/back-main.js"></script>
<script>
    //将form转为AJAX提交
    function ajaxSubmit(frm, fn) {
        var data0 = getFormJson(frm);
        //alert(data0);
        var dataPara = {'notice': data0};
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
                    alert("添加成功");
                }else{
                    alert("添加失败");
                }
            });
            return false;
        });
    });
</script>
</html>