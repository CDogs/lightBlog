
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
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html" color="#white">常用操作</a><span class="crumb-step">&gt;</span><span class="crumb-name">博文统计</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="#" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="70">关键字:</th>
                            <td><input class="common-text" placeholder="关键字" name="keywords" value="" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">


                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>标题</th>
                            <th>作者</th>
                            <th>发布时间</th>
                            <th>操作</th>
                        </tr>
<#if articles?exists>
    <#list articles as article>
                        <tr>
                            <td><a target="_blank" href="${rc.contextPath}/article/read.htm?id=${article.id}">
                            ${article.title}
                            </a></td>
                            <td>${article.authorName}</td>
                            <td>${article.createTime?string("yyyy-MM-dd HH:mm")}</td> <!--订单时间-->
                            <td><button data-id="${article.id}" data-action="delete" type="button" class="btn-adele btn btn-default"><img src="${rc.contextPath}/images/del.png">删除</button></td>
                        </tr>
    </#list>
    <#else>
        <tr class="error">没有发现相关的文章</tr>
</#if>
                    </table>
                    <div class="list-page">
                         <span text-align="center">
                        <#if page?exists>
                            <form id="form" action="${rc.contextPath}/admin/admin_index.htm" method="post">
                                <input type="hidden" name="currentPage" id="currentPage" value="${page.currentPage}"></input>
                                <input type="hidden" name="pageSize" id="pageSize" value="5"></input>
                            </form>
                        </#if>
                            <!-- 分页 -->
                        <#include "../layout/pagination.ftl">
                        <#--                        <p style="text-align: center;text-indent: 0em"><a class="btn btn-default" href="#" role="button"> &laquo;上一页</a>&nbsp;&nbsp;&nbsp;<a class="btn btn-default" href="#" role="button">下一页 &raquo;</a></p>-->
                    </span>
                </div>
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
</html>