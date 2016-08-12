
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

        <#--        <div class="result-title">
                    <div class="result-list">
                        <a id="batchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
                        <a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>
                    </div>
                </div>-->
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>

                            <th>作者</th>

                            <th>公告标题</th>
                            <th>发布时间</th>
                            <th>操作</th>
                        </tr>
                        <#if notices?exists>
                            <#list notices as notice>
                                <tr>
                                    <td>${notice.authorName}</td>

                                    <td>${notice.title}</td>
                                    <td>${notice.createTime?string("yyyy-MM-dd HH:mm")}</td>
                                    <td><input type="image" src="${rc.contextPath}/images/icn_edit.png" title="编辑">
                                        &nbsp;&nbsp;&nbsp;&nbsp;<a href=""><img  src="${rc.contextPath}/images/icn_trash.png" title="删除"></a></td>
                                </tr>
                            </#list>
                        <#else>
                        <tr align="center" clospan="4">还没有公告</tr>
                        </#if>
                    </table>
                    <div class="list-page">
                         <span text-align="center">
                        <#if page?exists>
                            <form id="form" action="${rc.contextPath}/notice/index.htm" method="post">
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