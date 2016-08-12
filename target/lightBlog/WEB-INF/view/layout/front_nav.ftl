<!-- 顶部菜单栏 navbar -->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <img src="${rc.contextPath}/images/favicon.ico" style="height: 50px;width:50px">
            <img src="${rc.contextPath}/images/favicon.ico" style="height: 50px;width:50px">
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="${rc.contextPath}">首页</a></li>
                <li><a href="${rc.contextPath}/notice/notices.htm">公告</a></li>

            </ul>
<#if user?exists>
    <ul class="nav navbar-nav navbar-right">
        <li><a href="${rc.contextPath}/user/ucenter.htm">个人主页</a></li>
        <li><a href="${rc.contextPath}/user/init-info.htm">${UserName}</a></li>
        <li>
            <a data-toggle="modal" href="${rc.contextPath}/user/logout.htm" class="btn log-reg" onclick="return confirm('如果选择确定，你将放弃整个世界？')">退出</a>
        </li>
    </ul>
</div><!--/.nav-collapse -->
<#elseif admin?exists>

    <ul class="nav navbar-nav navbar-right">
        <li><a href="${rc.contextPath}/admin/admin_index.htm">管理主页</a></li>
        <li class="active"><a href="userpage.html">${AdminName}</a></li>
        <li>
            <a data-toggle="modal" href="${rc.contextPath}/admin/logout.htm" class="btn log-reg">退出</a>
        </li>
    </ul>
</div><!--/.nav-collapse -->
    <#else>
        <div class="navbar-header pull-right"><img  data-toggle="modal" href="index.ftl#log-reg" title="登录" style="height: 50px;width: 50px" src="${rc.contextPath}/images/user.ico"></div>
<#--        <ul class="nav navbar-nav navbar-right">
            <li>
                <a data-toggle="modal" href="index.ftl#log-reg" class="btn log-reg" title="登录"><img  style="height: 50px;width: 50px" src="${rc.contextPath}/images/user.png"></a>
            </li>
        </ul>-->

</#if>




    </div><!--/.container-fluid -->
</nav>

<!--登录弹窗模块-->
<div class="modal fade" id="log-reg" tabindex="-1" role="dialog" aria-labelledby="log-regModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="log-regModal">登录</h4>
            </div>
            <div class="modal-body">
                <form class="form-signin" action="" method="post" id="login">
                    <span class="error_msg"></span>
                    <div class="userId">
                        <label for="inputName" class="sr-only">帐号</label>

                        <input type="text" autocomplete="off" name="name" id="inputName" class="form-control username"  placeholder="帐号">
                        <span class="glyphicon glyphicon-user userIcon"></span>
                    </div>
                    <div class="userKey">
                        <label for="inputPassword" class="sr-only">密码</label>
                        <input type="password" style="display:none">
                        <input type="password" autocomplete="off" name="password" id="inputPassword" class="form-control password" placeholder="密码">
                        <span class="glyphicon glyphicon-lock userIcon"></span>
                    </div>
                    <div class="rad">
                        <label class="radio-inline">
                            <input type="radio" name="role" id="inlineRadio1" value="User" checked="">用户</label>
                        <label class="radio-inline">
                            <input type="radio" name="role" id="inlineRadio2" value="Admin">管理员</label>
                    </div>

                    <div class="controls">
                        <label style="color:#eba3a9;" id="loginMessage">

                        </label>
                    </div>
                    <div class="modal-footer">
                        <a class="register" data-toggle="modal" href="#register" data-dismiss="modal" style="font-family: 华文行楷;margin-right: 10px;font-size: 18px;margin-top: 4px">注册...</a>
                        <button id="login-btn" type="submit" class="btn btn-primary" onclick="checkid()">登录</button>
                    </div>

                </form>
            </div>

        </div>
    </div>
</div>
<!--登录弹窗模块结束-->

<!--注册弹窗模块-->
<div class="modal fade" id="register" tabindex="-2" role="dialog" aria-labelledby="registerModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="registerModal">注册</h4>
            </div>
            <div class="modal-body">
                <form class="form-signin" action="${rc.contextPath}/user/register.htm" method="post" id="registerForm">
                    <span class="error_msg"></span>
                    <div class="userId">
                        <label for="registerName" class="sr-only">帐号</label>

                        <input type="text" autocomplete="off" name="name" id="registerName" class="form-control username"  placeholder="帐号">
                        <span class="glyphicon glyphicon-user userIcon"></span>
                    </div>
                    <div class="userKey">
                        <label for="registerPassword" class="sr-only">密码</label>
                        <input type="password" style="display:none">
                        <input type="password" autocomplete="off" name="password" id="registerPassword" class="form-control password" placeholder="密码">
                        <span class="glyphicon glyphicon-lock userIcon"></span>
                    </div>
                    <div class="userKey">
                        <label for="re-input" class="sr-only">重新输入密码</label>
                        <input type="password" style="display:none">
                        <input type="password" autocomplete="off" name="re-password" id="re-input" class="form-control password" placeholder="重新输入密码">
                        <span class="glyphicon glyphicon-lock userIcon"></span>
                    </div>
                    <div class="controls">
                        <label style="color:#eba3a9;" id="registerMessage">

                        </label>
                    </div>
                    <div class="modal-footer">
                        <button   id="register-btn" type="submit" class="btn btn-primary" onclick="checkid1()">注册</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<!--注册弹窗模块结束-->




