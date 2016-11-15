<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>博文</title>
    <link rel="shortcut icon" href="${rc.contextPath}/images/favicon.ico" type="image/x-icon" />
    <!-- Bootstrap core CSS -->
    <link href="${rc.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <link href="${rc.contextPath}/css/list-style.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/index_add.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${rc.contextPath}/css/navbar.css" rel="stylesheet">

    <link href="${rc.contextPath}/css/font-awesome.min.css" rel="stylesheet">

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="${rc.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script src="${rc.contextPath}/javascripts/common.js"></script>
    <script src="${rc.contextPath}/highlight/highlight.pack.js"></script>
    <script src="${rc.contextPath}/javascripts/filterHtml.js"></script>
    <script src="${rc.contextPath}/javascripts/front-main.js"></script>

</head>

<body>
<div class="body_type">
    <!-- Static navbar -->
<#include "layout/front_nav.ftl">
    <div class="container" style="margin-top: 25px">

        <!--顶部菜单兰结束-->

        <!--文章模块实现-->

        <div class="labelContainer">
            <div class="row row-offcanvas row-offcanvas-right">

                <#include "layout/left_nav.ftl">

                <!--jumbotron-->
                <div class="col-xs-12 col-sm-8">
<#if article?exists>
<#--                    <div class="jumbotron">
                        <h4>${article.title}</h4><h5>${article.createTime?string("yyyy-MM-dd HH:mm")} 发表</h5>
                        <blockquote>
                            <p style="text-indent: 2em">  ${article.content} </p>
                            <h5 class="text-right">${article.authorName}</h5>
                        </blockquote>
                        <span class="btn_float_left">
                            <#if article.tags?exists && (article.tags?size>0)>
                                <span>
                                    <#list article.tags as tag>
                                        <a href="${rc.contextPath}/article/list.htm?tagId=${tag.id}"><span class="btn btn-default"><img src="${rc.contextPath}/images/type.png">${tag.name}</span></a>
                                    </#list>
                                </span>
                            </#if>
                        </span>-->
    <div class="panel panel-info jumbotron" style="border: 0;border-left: 1px solid skyblue ">
        <div class="panel-body">
            <P class="text-center text-primary" style="font-size: xx-large">${article.title}</P>
            <div >
            <#if article.tags?exists && (article.tags?size>0)>
                <span class="pull-left">
                    <#list article.tags as tag>
                        <a href="${rc.contextPath}/article/list.htm?tagId=${tag.id}"><span class="label label-success" style="font-size: small">${tag.name}</span></a>
                    </#list>
                </span>
            </#if>

                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                <span class="pull-right badge" title="浏览量">${article.readCount}</span>
                <span class="pull-right text-right text-danger">${article.createTime?string("yyyy-MM-dd HH点 mm分")}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>

            </div>
            <br>
            ${article.content}

        </div>

            <span class="btn_float_right">

                <a data-toggle="modal" href="#comments" role="button" title="查看评论"><i class="fa fa-comments-o fa-lg"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <b class="text-info">作者：${article.authorName}</b>
            </span>
    </div>
<div class="panel panel-warning">
    <div class="panel-body">
        <!-- 留言板 -->
        <div >
            <form id="formCoom" >
<#--                <fieldset>
                    <legend>留言板</legend>-->
                    <div >
                        <textarea style="width:100%;border: none;overflow:hidden; resize:none;outline:none" rows="4" class="" id="inputContent" name="content" placeholder="评论："></textarea>
                        <input type="hidden" id="feedbackUser" name="feedback" value=""/>
                        <input type="hidden" id="fatherId" name="fatherComm" value=""/>
                        <input type="hidden" id="articleId" name="articleId" value="${article.id}"/>
                        <input type="hidden" id="inputBlogUrl" name="blogUrl" value=""/>
                        <input type="hidden" id="inputName" name="userName" value="<#if UserName?exists>${UserName}</#if>"/>
                        <button type="button" id="btnComm" class="btn pull-right btn-warning">发&nbsp;&nbsp;表</button>

                    </div>
                <#--</fieldset>-->
            </form>
        </div>
        <br>
        <hr>
        <!-- 显示留言 -->
        <div class="show-comm">
<#--            <div id="comment_list">
                <div class="mod_comments">
                    <div class="comments_list">
                    &lt;#&ndash;                    <div class="comments_list_more"><a href="#">查看全部<span>26</span>条评论</a>
                                        </div>&ndash;&gt;
                        <ul>
                            <!--<ul data-uin="1178850769" data-tid="d1d54346bf6d0a5745f00200" data-source="1">&ndash;&gt;

                        </ul>
                    </div><!-- list end &ndash;&gt;<!-- poster &ndash;&gt;
                </div><!-- qz_comments end&ndash;&gt;
            </div>-->
        </div>
        <!-- 留言版 -->

    </div>


    </div>


<#else>
    <p class="error">您查看的文章已删除或者不存在。</p>
</#if>




            </div><!--/jumbotron-->


            </div>

        </div>

        <!--文章模块结束-->

    </div> <!-- /container -->
</div>


<!-- Bootstrap core JavaScript
================================================== -->

<script type="text/javascript">
    var currentPage = 	1,
            pageSize = 8;
    var comment = new Comment();
    //load comment
    comment.loadArtiComment(${article.id}, currentPage, pageSize);
    //the page auto scroll to comment content
    var flag = "<#if flag?exists>${flag}</#if>";
    if (flag == "cmt"){
        window.scrollTo(0,$(".show-comm").offset().top);
    }
    //highlight
    hljs.configure({tabReplace: '    '}); // 4 spaces
    hljs.initHighlightingOnLoad();
</script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${rc.contextPath}/js/bootstrap.min.js"></script>
<script src="${rc.contextPath}/js/alert.js"></script>
<script src="${rc.contextPath}/js/logAndreg.js"></script>


</body>

</html>
