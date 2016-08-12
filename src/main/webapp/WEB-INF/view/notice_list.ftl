<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>公告</title>


    <link rel="shortcut icon" href="${rc.contextPath}/images/favicon.ico" type="image/x-icon" />
    <!-- Bootstrap core CSS -->
    <link href="${rc.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <link href="${rc.contextPath}/css/index_add.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${rc.contextPath}/css/navbar.css" rel="stylesheet">

</head>

<body>
<div class="body_type">
<#include "layout/front_nav.ftl">
    <div class="container" style="margin-top: 25px">
        <!-- Static navbar -->


        <!--公告模块实现-->

        <div class="labelContainer">
            <div class="row row-offcanvas row-offcanvas-right">
                <div class="col-xs-1 col-md-2"></div>

                <!--一个公告模块开始-->
                <div class="col-xs-10 col-sm-8 ">

            <#if notices?exists>
                <#list notices as notice>

                    <div class="panel panel-warning jumbotron">
                        <div class="panel-body">
                            <P class="text-center text-primary" style="font-size: xx-large">${notice.title}</P>
                            <p class="text-left text-danger" style="font-size: small">${notice.createTime?string("yyyy-MM-dd")}</p>
                        ${notice.content}


                        </div>

            <span class="pull-right">

                <b class="text-warning">发布人：${notice.authorName}</b>
            </span>

                    </div>

<#--                    <div class="jumbotron">
                        <h4 style="text-align: center;">${notice.title}</h4>
                        <h5 >${notice.createTime?string("yyyy-MM-dd HH:mm")}</h5>
                        <p style="text-indent: 2em">  ${notice.content}</p>
                        <h5 class="text-right">发布人: ${notice.authorName}</h5>
                    </div>-->
                </#list>
            <#else>
            <p>还没有公告，敬请期待</p>
            </#if>
                    <div class="list-page text-center">
                             <span text-align="center">
                            <#if page?exists>
                                <form id="form" action="${rc.contextPath}/notice/notices.htm" method="post">
                                    <input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}"></input>
                                    <input type="hidden" name="pageSize" id="pageSize" value="5"></input>
                                </form>
                            </#if>
                                <!-- 分页 -->
                            <#include "layout/pagination.ftl">
                            <#--                        <p style="text-align: center;text-indent: 0em"><a class="btn btn-default" href="#" role="button"> &laquo;上一页</a>&nbsp;&nbsp;&nbsp;<a class="btn btn-default" href="#" role="button">下一页 &raquo;</a></p>-->
                        </span>
                    </div>
                </div><!--/.col-xs-12.col-sm-9-->
                <!--一个公告模块结束-->

                <div class="col-xs-1 col-md-2"></div>
            </div><!--/row-->



        </div>

        <!--公告模块结束-->
    </div> <!-- /container -->

</div>

<!-- Bootstrap core JavaScript
================================================== -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${rc.contextPath}/js/jquery-1.11.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${rc.contextPath}/js/bootstrap.min.js"></script>

<script src="${rc.contextPath}/js/alert.js"></script>
<script src="${rc.contextPath}/js/logAndreg.js"></script>

<script type="text/javascript">
    $(function(){
        //分页提交脚本
        $(".pagination ul li a").click(function(){
            if($(this).attr("name")=="last"){
                $("#currentPage").val(parseInt($("#currentPage").val())-1);
            }else if($(this).attr("name")=="next"){
                $("#currentPage").val(parseInt($("#currentPage").val())+1);
            }else{
                $("#currentPage").val($(this).html());
            }
            $("#form").submit();
        });
    });
</script>
</body>
</html>
