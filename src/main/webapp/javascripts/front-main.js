// JavaScript Document
//main function
$(document).ready(function() {
	var comment = new Comment();
	//设置参数
	$("#btnComm").click(function() {
		comment.addArtiComment();
	});

	//login
	$("#form1").submit(function(event) {
		
		var name = $("#inputName").val().trim();
		var password = $("#inputPassword").val().trim();
		if (name == "" || name == null) {
			$("#message").html("账号不可为空！");
			return false;
		} else if (password == "" || password == null) {
			$("#message").html("密码不可为空！");		
			return false;
		}	
		return true;
	});

});


//评论对象
function Comment() {
	
	//参数检测
	this.checkParam = function() {
		var url = $.trim(""+$("#inputBlogUrl").val());
		var con = $.trim(""+$("#inputContent").val());
		var email = $.trim(""+$("#inputEmail").val());

/*		if (url != "") {
			if (!checkeUrl(url)) {
			 	alert("非法的博客地址！");
			 	return false;
			}
		}
		if (email != "") {
			if (!checkEmail(email)) {
				alert("无效的邮箱地址！");
			 	return false;
			}
		}*/

		if (con == "") {
			alert("评论内容不可为空！");
			return  false;
		} 
		if (con != "") {
			var conHtm = $filterHtml(con, {
					badAttr             : [ /^on/ ] //支持正则 ( .match() )
					, whiteHrefScheme   : ['http', 'https', 'tel'] 
					, whiteSrcScheme    : ['http', 'https', 'tel'] //<form action>依赖  href="&#106;avascrip&#116;&#58;alert(1);" 
					, badStyleName      : [ 'behavior' ]  //position , left , behavior(IE下加载js文件)
					, badStyleVal       : [ 'expression' ] // 只要发现有问题的 值，则把整个 名 清空
					, badTag            : [ 'script', 'link', 'video', 'object' ] //支持 jQueyr 选择器 .className, 'form',
					   // 一定要 禁止 <link> <script>, 因为他们很危险，而且 .html() 不支持 这两个标签
					, isClearCssImport	: true //是否清理 @import "CssStyle.css";
				});
			//过滤内容
			$("#inputContent").val(conHtm);
			if (conHtm == "") {
				return false;
			}
		}
		return true;
	};

	//添加新评论
	this.addArtiComment = function() {
		var comment = new Comment();
		if (comment.checkParam()) {
			$.post(contextPath+"/comment/add.htm",$("#formCoom").serialize(), function(data) {
				if (data == "SUCCESS") {
					$("#inputContent").val("");
					//alert("评论成功~！");
					var comment = new Comment();
					comment.loadArtiComment($("#articleId").val(),currentPage, pageSize);
				} else if (data == "INVALIDE_URL") {
					alert("无效的博客地址"); 
				} else if (data == "FAIL") {
					alert("评论失败~！"); 
				}
			});		
		}
	};

	//加载文章评论
	this.loadArtiComment = function(id, current, size) {

		console.log(id);
		var _param = {
			articleId: id,
			page: {
				currentPage:current,
				pageSize:size
			}
		};
		$.ajax({
			url : contextPath+"/comment/load.htm",
			data : JSON.stringify(_param),
			//data: _param,
			type : "post",
			dataType : "json",
			contentType:"application/json",
			success : function(data) {
				console.log(data);
				//alert(data.list[0].comment.content);
				if (data != null) {
					var objects = data.list;
					if(objects.length == 0){
						$(".show-comm").append('<h3>还没有评论哦，快来抢沙发吧</h3>');
						return;
					}
					$(".show-comm").empty();
					$.each(objects,function(index,object){
						var content = object.comment.content;
						var author = object.comment.userName;
						var createTime = (new Date(object.comment.createTime.time)).toLocaleString();
						//alert(object.comment.content);
						$(".show-comm").append('<div class="media ">	<a href="#" class="pull-left"><img src='+contextPath+'/images/favicon.ico class="media-object img-circle" style="height:45px;width:45px"/></a>	<div class="media-body"><h6 class="media-heading text-warning">'+author+'</h6> ' + content +'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span ><a href="" class="text-primary">回复</a></span><span class="pull-right text-primary">'+createTime+'</span></div></div>');
						//$(".show-comm").append('<li class="comments_item bor3"><div class="comments_item_bd"><div><div class="ui_avatar"><a target="_blank" title='+author+' data-uin="807599680"><img src='+contextPath+'/images/icon5.png></a></div><div class="comments_content">:<span>'+content+'</span><div class="comments_op"><span class="c_tx3">'+createTime+'</span><a href="#" class="c_tx">回复</a></div></div></div></div></li>');
					});

					//$(".show-comm").html(data);
				}
			},
			error : function(){
				alert("异常");
			}
		});


/*		$.post(contextPath+"/comment/load.htm",
			{
			articleId: id,
			currentPage: current,
			pageSize:size
			},
			function(data) {
				console.log(data);
				alert(data.list.comment);
				if (data != null) {
					$(".show-comm").html(data);
				}
		});*/
	};
}