<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>博客首页</title>
    <link rel="shortcut icon" href="${rc.contextPath}/images/favicon.ico" type="image/x-icon" />
    <!-- Bootstrap core CSS -->

    <link href="${rc.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <link href="${rc.contextPath}/css/index_add.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${rc.contextPath}/css/navbar.css" rel="stylesheet">

    <link href="${rc.contextPath}/css/font-awesome.min.css" rel="stylesheet">



</head>

<body>
<div class="body_type">
    <#include "layout/front_nav.ftl">
    <div class="container" style="margin-top: 25px">

        <!--博客模块实现-->

        <div class="labelContainer">
            <div class="row row-offcanvas row-offcanvas-right">
            <#include "layout/left_nav.ftl">

                <!--要闻模块开始-->

                <div class="col-xs-12 col-sm-9">
    <#if articles?exists>
        <#list articles as article>
            <div class="panel panel-info jumbotron" style="border: 0">
                <div class="panel-heading">
                    <span class="panel-title"><a target="_blank" href="${rc.contextPath}/article/read.htm?id=${article.id}">${article.title}</a></span>
                    <div class="pull-right">
                        ${article.createTime?string("yyyy-MM-dd HH点 mm分")}&nbsp;&nbsp;发表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="badge" title="浏览量">${article.readCount}</span>
                    </div>
                </div>
                <div class="panel-body">
                ${article.content}&nbsp;&nbsp;&nbsp;&nbsp;<a style="font-size: medium" href="${rc.contextPath}/article/read.htm?id=${article.id}" role="button" title="查看详情"><i class="fa fa-newspaper-o fa-lg"></i></a>
                </div>
                <div>
                    <#if article.tags?exists && (article.tags?size>0)>
                        <span class="btn_float_left">
                            <#list article.tags as tag>
                            <a href="${rc.contextPath}/article/list.htm?tagId=${tag.id}"><span class="label label-success" style="font-size: small">${tag.name}</span></a>
                            </#list>
                        </span>
                    </#if>

                    <span class="btn_float_right">
                            <a data-toggle="modal" href="#comments" role="button" title="查看评论"><i class="fa fa-comments-o fa-lg"></i></a>
                            &nbsp;&nbsp;&nbsp;&nbsp;

                    </span>
                </div>
            </div>
        </#list>
       <#-- <div class="jumbotron">
                        <h4>
                            <a target="_blank" href="${rc.contextPath}/article/read.htm?id=${article.id}">
                        ${article.title}
                            </a>
                        </h4>
                        <h5>${article.createTime?string("yyyy-MM-dd HH:mm")}</h5>

                        <blockquote>
                            <p style="text-indent: 2em">  ${article.content?html} </p>
                        </blockquote>
                        <#if article.tags?exists && (article.tags?size>0)>
                        <span class="btn_float_left">
                            <#list article.tags as tag>
                            <a href="${rc.contextPath}/article/list.htm?tagId=${tag.id}"><span class="btn btn-default"><img src="${rc.contextPath}/images/type.png">${tag.name}</span></a>
                            </#list>
                        </span>
                        </#if>
                        <p class="btn_float_right">
                            <span class="btn btn-default"><img src="${rc.contextPath}/images/write.png">评论</span>
                            <span class="btn btn-default">浏览  <span class="badge">${article.readCount}</span></span>
                            <a class="btn btn-default" href="${rc.contextPath}/article/read.htm?id=${article.id}" role="button">查看详情 &raquo;</a>
                        </p>
                    </div>-->

                <#else>
                    <p class="error">没有发现相关的文章</p>
                </#if>
        <!--分页 -->
        <div class="text-center">
            <span >
                <#if page?exists>
                    <form id="form" action="${rc.contextPath}/article/index.htm" method="post">
                        <input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}"></input>
                        <input type="hidden" name="pageSize" id="pageSize" value="5"></input>
                    </form>
                </#if>
                    <!-- 分页 -->
                <#include "layout/pagination.ftl">
<#--                        <p style="text-align: center;text-indent: 0em"><a class="btn btn-default" href="#" role="button"> &laquo;上一页</a>&nbsp;&nbsp;&nbsp;<a class="btn btn-default" href="#" role="button">下一页 &raquo;</a></p>-->
            </span>
                    <!--要闻模块结束-->
        </div>
                </div><!--/.col-xs-12.col-sm-9-->
            </div><!--/row-->

        </div>
        <!--博客模块结束-->

    <#include "layout/footer.ftl">
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
