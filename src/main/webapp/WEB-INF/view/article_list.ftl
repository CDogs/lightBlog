<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>博文列表</title>
    <link rel="shortcut icon" href="${rc.contextPath}/images/favicon.ico" type="image/x-icon" />
    <!-- Bootstrap core CSS -->
    <link href="${rc.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/list-style.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/index_add.css" rel="stylesheet">

    <!-- 字体图标 -->
    <link href="${rc.contextPath}/css/font-awesome.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${rc.contextPath}/css/navbar.css" rel="stylesheet">

</head>

<body>
<div class="body_type">
<#include "layout/front_nav.ftl">
    <div class="container" style="margin-top: 25px">
        <!-- 菜单栏 navbar -->


        <!--公告模块实现-->

        <div class="labelContainer">
            <div class="row row-offcanvas row-offcanvas-right">

                <!-- 侧边栏模块 -->
                <#include "layout/left_nav.ftl">


                <!--jumbotron-->
                <div class="col-xs-12 col-sm-8">
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
                            ${article.content}                                    &nbsp;&nbsp;&nbsp;&nbsp;
                                <a style="font-size: medium" href="${rc.contextPath}/article/read.htm?id=${article.id}" role="button" title="查看详情"><i class="fa fa-newspaper-o fa-lg"></i></a>
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

                                </span>
                            </div>
                        </div>
                    </#list>
<#--                    <div class="jumbotron">
                        <h3 style="margin-top: 32px;text-indent: 1em;"><#if label?exists>${label}<#else>列表：</#if></h3>
                        <div class="article-list">

                           <#if articles?exists>
                               <table class="table table-hover">
                                   <thead><tr><td>标题</td><td>编写时间</td><td>访问次数</td></tr></thead>
                                   <tbody>
                                       <#list articles as article>
                                       <tr>
                                           <td width="500px"><a href="${rc.contextPath}/article/read.htm?id=${article.id}">${article.title}</a></td>
                                           <td>${article.createTime?string("yyyy-MM-dd HH:mm")}</td>
                                           <td class="align-cen">${article.readCount}</td>
                                       </tr>
                                       </#list>
                                   </tbody>
                               </table>-->
                           <#else>
                               <p class="error">没有发现相关的文章</p>
                           </#if>
                    </div>

                        <div class=" text-center">
                        <#if page?exists>
                            <form id="form" action="${rc.contextPath}${action}" method="post">
                                <input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}"></input>
                                <#if tagId?exists>
                                    <input type="hidden" name="tagId" id="tagId" value="${tagId}"></input>
                                </#if>
                                <#if catId?exists>
                                    <input type="hidden" name="cat" id="catId" value="${catId}"></input>
                                </#if>
                                <input type="hidden" name="pageSize" id="pageSize" value="15"></input>
                            </form>
                        </#if>
                            <!-- 分页 -->


                        <#include "layout/pagination.ftl">
                        </div>
                </div>
<#--
                        <p style="text-align: center"><a class="btn btn-default" href="#" role="button"> &laquo;上一页</a>&nbsp;&nbsp;&nbsp;<a class="btn btn-default" href="#" role="button">下一页 &raquo;</a></p>
-->

                    </div>
                </div>
            </div><!--/jumbotron-->


        </div>

        <!--公告模块结束-->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="${rc.contextPath}/js/jquery-1.11.1.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${rc.contextPath}/js/bootstrap.min.js"></script>
<script src="${rc.contextPath}/js/alert.js"></script>
<script type="text/javascript">
    $(function(){
        //分页URL初始化
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
