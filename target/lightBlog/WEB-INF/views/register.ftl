<html xmlns="http://www.w3.org/1999/xhtml"><head>
    <title>ע��</title>
<#include "layout/head.ftl">
    <script language="javascript" charset="utf-8" type="text/javascript" src="${rc.contextPath}/javascripts/front-main.js"></script>
</head>
<body>

<div class="warpper">
    <!--header---->
<#include "layout/front_nav.ftl">
    <!-- ҳ������ -->
    <div class="main">
        <div class="form-wrapper">
            <fieldset>
                <legend>��¼</legend>
                <form id="form1" class="form-horizontal" method="post" action="${rc.contextPath}/user/register.htm">
                    <div class="control-group">
                        <label class="control-label" for="inputName">UserName:</label>
                        <div class="controls">
                            <input type="text" id="inputName" value="" name="name">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputPassword">Password:</label>
                        <div class="controls">
                            <input type="password" id="inputPassword" value="" name="password" >
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <button type="submit" class="btn btmar"> �� ¼ </button>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="controls">
                            <label style="color:#eba3a9;" id="message">
                            <#if message?exists>
                                <#if (message=="INVALID_USER")>
                                    ��Ч���û���¼��Ϣ����������˻���������Ϣ��
                                <#else>
                                    ϵͳ�ڲ�����
                                </#if>
                            </#if>
                            </label>
                        </div>
                    </div>
                </form>
            </fieldset>
        </div>
    </div>
    <!-- �ײ� -->
<#include "layout/footer.ftl">
</div>
</body>
</html>