<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"></base>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
	<meta name="description" content="">
	<meta name="author" content="ThemeBucket">
	<title>404 Page</title>
	<link href="stylesheet/style.css" rel="stylesheet">
</head>
<body class="error-page">
<section>
	<div class="container ">
		<section class="error-wrapper text-center">
			<h1><img alt="" src="images/404-error.png"></h1>
			<h2>(⊙o⊙)，页面找不到</h2>
			<h3>We Couldn’t Find This Page</h3>
			<a class="back-btn" href="article/index.htm"> 回到主页</a>
		</section>

	</div>
</section>
</body>
</html>
