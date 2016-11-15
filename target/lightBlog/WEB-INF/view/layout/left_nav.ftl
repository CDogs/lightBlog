<div class="col-xs-12 col-sm-3 col-sm-offset-1 " id="sidebar">

    <div class="panel panel-default">
        <h3 class="text-center text-primary ">全部文章(<#if countOfAllArticles?exists>${countOfAllArticles})</#if></h3>
        <hr>
        <div class="panel-body">
            <#if categorys?exists>
                <#if categorys?size <= 0>
                    <p class="text-center text-primary ">还没有类别，敬请期待</p>
                <#else>
                    <#list categorys as cate>
                        <#if (cate.articleCount > 0)>
                            &nbsp;&nbsp;&nbsp;&nbsp;<a class="text-primary" href="${rc.contextPath}/article/index.htm?cat=${cate.id}"><span>${cate.name}(${cate.articleCount})</span></a>&nbsp;&nbsp;&nbsp;&nbsp;
                        </#if>
                        <#if ((cate_index+1) % 3) == 0>
                            <hr style="border: 0 solid">
                        </#if>
                    </#list>
                </#if>

            </#if>
        </div>
    </div>
    <div class="panel panel-warning">
        <h3 class="text-center text-warning"> 归档</h3>
        <hr>
        <div class="panel-body">


            <#if archives?exists>
                <#if archives?size <= 0>
                    <p class="text-center text-warning">文章还没有归档哦</p>
                <#else>
                    <#list archives as article>
                        <p class="text-center "><a class="text-warning" href="${rc.contextPath}/article/search-archives.htm?time=${article.createTime?datetime}">${article.createTime?string("yyyy年MM月")}(${article.count})</a></p>
                    </#list>
                </#if>

            </#if>
        </div>
    </div>
    <div class="panel panel-success">
        <h3 class="text-center text-error">热门文章</h3>
        <hr>
        <div class="panel-body">


            <#if hotArticles?exists>
                <#if hotArticles?size <= 0>
                    <p class="text-center text-error">等你来发布（0.0）</p>
                <#else>
                    <#list hotArticles as article>
                        <p class=""><strong class="text-primary"> ${article_index + 1}.</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a class="text-error" title="${article.title}" target="_blank" href="${rc.contextPath}/article/read.htm?id=${article.id}">${article.title}</a></p>
                    </#list>
                </#if>


            </#if>
        </div>
    </div>
    <div class="panel panel-info">
        <h3 class="text-center text-info">标签列表</h3>
        <hr>
        <div class="panel-body">

            <#if tags?exists>
                <#if tags?size <= 0>
                    <p class="text-center text-info">>日后必有标签</p>
                <#else>
                <#list tags as tag>
                    <#if (tag.count > 0)>
                        &nbsp;&nbsp;<a class="text-primary" href="${rc.contextPath}/article/list.htm?tagId=${tag.id}"><span>${tag.name}(${tag.count})</span></a>&nbsp;&nbsp;&nbsp;
                    </#if>
                    <#if ((tag_index+1) % 3) == 0>
                        <hr style="border: 0 solid">
                    </#if>
                </#list>
                </#if>
        </#if>
        </div>
    </div>
    <div class="panel panel-danger">
        <h3 class="text-center text-danger">友情链接</h3>
        <hr>
        <div class="panel-body">
            <#if links?exists>


                <#if links?size <= 0 >
                    <p class="text-center text-danger">欢迎你的访问，我们尽快提供-_-</p>
                <#else>
                    <#list links as link>
                        <p><a class ="" target="_blank" href="${link.link}">${link.name}</a></p>
                    </#list>
                </#if>
            </#if>

        </div>
    </div>


    </div><!--/.sidebar-offcanvas-->