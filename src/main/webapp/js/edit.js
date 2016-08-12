$(function () {
  $(".t_value").click(function () {
      var li = $(this);
      var txt = li.text();
      var input = $("<input style='height: 36px;text-align: center;margin: 2px;' type='text' value='"+txt+"'>");
          li.html(input);
      input.click(function () {
          return false;
      });
      //获取焦点
      input.trigger("focus");
      /*文本框失去焦点后提交内容，重新变为文本*/
      input.blur(function () {
          var new_txt = $(this).val();
          if (new_txt == "")
              new_txt = txt;
          li.html(new_txt);
          // var user_info = $.trim(li.prev().text());
          // //ajax异步更新数据库，加数据库date是解决缓存问题
      })
  })
})