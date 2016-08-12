
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
            <div class="crumb-list"><i class="icon-font"></i><a href="#">常用操作</a><span class="crumb-step">&gt;</span><span class="crumb-name">公告列表</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="${rc.contextPath}/admin/tag/add.htm" method="post" id="myform">
                    <table class="search-tab">
                        <tr>
                            <th width="70">标签添加:</th>
                            <td><input class="common-text" placeholder="标签名" name="name" value="" id="" type="text"></td>
                            <td><input class="common-text" placeholder="描述" name="description" value="" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="添加" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">


            <div class="result-content">
                <table class="result-tab" width="100%">
                    <tr>

                        <th>标签</th>
                        <th>描述</th>
                        <th>操作</th>
                    </tr>
                <#if tags?exists>
                    <#list tags as tag>
                        <tr>
                            <td>${tag.name}</td>
                            <td>${tag.description!""}</td>

                            <td><input type="image" src="${rc.contextPath}/images/icn_edit.png" title="编辑">
                                &nbsp;&nbsp;&nbsp;&nbsp;<a href=""><img  src="${rc.contextPath}/images/icn_trash.png" title="删除"></a></td>
                        </tr>
                    </#list>
                <#else>
                    <tr align="center" clospan="4">还没有标签</tr>
                </#if>
                </table>

            </div>

        </div>
    </div>
    <!--/main-->

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
        var dataPara = {'tag': data0};
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
                    window.location.reload();
                }else{
                    alert("添加失败");
                }
            });
            return false;
        });
    });
</script>
</html>