<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>博主首页</title>

    <link rel="shortcut icon" href="${rc.contextPath}/images/favicon.ico" type="image/x-icon" />
    <link href="${rc.contextPath}/css/user-page.css" rel="stylesheet">
    <link href="${rc.contextPath}/css/comments.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="${rc.contextPath}/css/bootstrap.min.css" rel="stylesheet">

    <link href="${rc.contextPath}/css/index_add.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${rc.contextPath}/css/navbar.css" rel="stylesheet">

    <!-- 字体图标 -->
    <link href="${rc.contextPath}/css/font-awesome.min.css" rel="stylesheet">

    <script src="${rc.contextPath}/ckeditor/ckeditor.js"></script>
    <script src="${rc.contextPath}/js/jquery-1.11.1.min.js"></script>
    <script>
        function resetContent() {
            CKEDITOR.instances.content.setData('');
        }
    </script>
</head>

<body>
<div class="body_type">
<#include "../layout/front_nav.ftl">
    <div class="container" style="margin-top: 25px">


        <!--顶部菜单兰结束-->
    </div>


    <div class="container1">
        <!--实现类似qq空间的排版效果-->
        <!--中部布局-->
        <li class="bowen_item1 " >
            <form action="" method="post">
                <div class="bowen_title">
                <#--<span>标题:</span>-->
                    <input type="text" name="title" id="arTit" class="form-control" placeholder="标题">
                </div>
                <br>
                <div class="bowen_type">
                <#--<span>类别:</span>-->
                    <select class="form-control" id="category" name="category">
                        <option selected="selected"><--选择 类别--></option>
                    <#if artCates?exists>
                        <#list artCates as cate>
                            <option value="${cate.id}">${cate.name}</option>
                        </#list>
                    </#if>
                    </select>
                </div>

                <br>
                <div class="bowen_type">
                    <textarea onmousedown="s(event,this)" type="text" name="content" id="content" class="form-control" placeholder="你想发表什么..." rows="10"></textarea>
                    <script type="text/javascript">
                        CKEDITOR.replace('content');
                    </script>
                </div>
                <br>
                <div class="tag bowen_type">
                <#if tags?exists>
                    <#list tags as tag>
                    <#--<span class="badge"><a href="javascript:;" name="${tag.id}">${tag.name}</a></span>-->
                        <a href="javascript:;" name="${tag.id}" class="label label-default" style="font-size: small">${tag.name}</a>
                    </#list>
                </#if>
                </div>

                <button id="btnSb" type="submit" class="btn btn-default"><img src="${rc.contextPath}/images/write.png">发表</button>
                <button type="reset" onclick="resetContent()" class="btn btn-default"><img src="${rc.contextPath}/images/rewrite.png">重置</button>
            </form>
        </li>
        <ol class="bowen_list">


        <#if articles?exists>
            <#list articles as article>
                <!--一篇博文开始-->
            <li class="bowen_item">

                <div class="bowen_meta">
                    <div class="bowen_time bg6">
                        <span class="day">${article.createTime?string("yyyy")}</span>
                        <span class="month">${article.createTime?string("MM")}月</span>
                    </div>
                </div>
<#--                <div class="bowen_content bor3 bg">
                    &lt;#&ndash;<b class="bowen_quote_before c_tx3"></b>&ndash;&gt;
                    <div class="bowen_detail">
                        <div class="bowen_zhuan bbor3">
                            <a target="_blank" href="${rc.contextPath}/article/read.htm?id=${article.id}">
                            ${article.title}
                            </a>
                        </div>
                        <p>
                        ${article.content?html}
                        </p>

                        <#if article.tags?exists && (article.tags?size>0)>
                            <span class="btn_float_left">
                                <#list article.tags as tag>
                                    <span class="btn btn-default"><img src="${rc.contextPath}/images/type.png">${tag.name}</span>
                                </#list>
                            </span>
                        </#if>


                        <span class="btn_float_right">
                            <button data-id="${article.id}" data-action="delete" type="button" class="btn-adele btn alert-danger"><img src="${rc.contextPath}/images/del.png">删除</button>
                            <span class="btn alert-info">浏览&nbsp&nbsp<span class="badge pull-right">${article.readCount}</span></span>
                            <a data-toggle="modal" class="btn alert-warning" href="#comments" role="button"><img src="${rc.contextPath}/images/comm.png">查看评论</a>
                        </span>
                    </div>
                </div>-->

            <div class="panel panel-info bowen_content">
                <div class="panel-heading">
                    <span class="panel-title"><a target="_blank" href="${rc.contextPath}/article/read.htm?id=${article.id}">${article.title}</a></span>
                    <div class="pull-right">
                        ${article.createTime?string("dd 日 HH 点")}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <span class="badge" title="浏览量">${article.readCount}</span>
                    </div>
                </div>
                <div class="panel-body">
                    <#--${article.content?html}-->
                    ${article.content}


                </div>
                <div >
                    <#if article.tags?exists && (article.tags?size>0)>
                        <span class="btn_float_left">
                            <#list article.tags as tag>
                                <span class="label label-success" style="font-size: small">${tag.name}</span>
                            </#list>
                        </span>
                    </#if>

                    <span class="btn_float_right">

                            <a data-toggle="modal" href="#comments" role="button" title="查看评论"><i class="fa fa-commenting-o fa-lg"></i></a>
                            &nbsp;&nbsp;&nbsp;&nbsp;
                            <a data-id="${article.id}" data-action="delete" title="删除"><i class="fa fa-trash-o fa-lg"></i></a>
                    </span>
                </div>
            </div>
            </#list>
        <#else>
            <p class="error">你还没有发表过文章，赶紧去发表吧</p>
        </#if>

        </li>
            <!--一篇博文结束-->

        </ol>

        <!--分页 -->
        <div class="text-center">
            <span>
                <#if page?exists>
                    <form id="form" action="${rc.contextPath}/user/ucenter.htm" method="post">
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


    <!--评论弹窗模块-->
    <div class="modal fade" id="comments" tabindex="-1" role="dialog" aria-labelledby="commentsModal" aria-hidden="true">
        <div class="modal-dialog" style="width: 450px;background-color: #efefef" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="commentsModal">评论/回复</h4>
                </div>
                <div class="modal-body">
                    <form class="form-signin" action="" method="post">
                        <div class="userKey">
                            <!--<span style="margin-bottom: 10px;font-size: 20px">评论</span>-->
                            <label for="inputComments" class="sr-only">评论</label>
                            <textarea style="height: 150px;line-height: 2.0" type="text" autocomplete="off" name="password" id="inputComments" class="form-control password"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary" data-dismiss="modal">关闭</button>
                    <button id="login-btn" type="submit" class="btn btn-primary">发表</button>
                </div>
                <div id="comment_list">
                    <div class="mod_comments">
                        <div class="comments_list">
                            <div class="comments_list_more"><a href="#">查看全部<span>26</span>条评论</a>
                            </div>
                            <ul>
                                <!--<ul data-uin="1178850769" data-tid="d1d54346bf6d0a5745f00200" data-source="1">-->
                                <li class="comments_item bor3">
                                    <div class="comments_item_bd">
                                        <div>
                                            <div class="ui_avatar">
                                                <a target="_blank" title="博主昵称" data-uin="807599680"><img src="${rc.contextPath}/images/icon5.png"></a></div>
                                            <div class="comments_content">
                                                <a class="nickname" target="_self">博主昵称</a> : <span>评论内容</span>
                                                <div class="comments_op">
                                                    <span class="c_tx3">2016-4-11</span>
                                                    <a href="#" class="c_tx">回复</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li class="comments_item bor3">
                                    <div class="comments_item_bd">
                                        <div>
                                            <div class="ui_avatar">
                                                <a target="_blank" title="博主昵称" data-uin="807599680"><img src="${rc.contextPath}/images/icon5.png"></a></div>
                                            <div class="comments_content">
                                                <a class="nickname" target="_self">博主昵称</a> : <span>评论内容</span>
                                                <div class="comments_op">
                                                    <span class="c_tx3">2016-4-11</span>
                                                    <a href="#" class="c_tx">回复</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div><!-- list end --><!-- poster -->
                    </div><!-- qz_comments end-->
                </div>
            </div>
        </div>
    </div>
    <!--评论弹窗模块结束-->
</div>

</body>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="${rc.contextPath}/js/bootstrap.min.js"></script>
<script src="${rc.contextPath}/js/alert.js"></script>
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