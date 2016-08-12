/**
 * 该JS临时用来写入一些JQuery的Ajax作为模板。用来进行前后台的数据交换。
 * 所用的类选择器一律使用id选择器。
 * Created by Tony on 2016/1/1.
 */

/**
 * 添加用户的Json格式：
 * role: "(MUST)the User role(T/S)"
 * id: "(MUST)the User id(Max length: 15)",
 * name: "(MUST)the User name(Max length: 15)",
 * password: "(MUST)the User password(Max length: 10)",
 * sex: "the User sex(F/M)",
 * school: "(MUST)the User school(Max Chinese length: 15)",
 * classNo: "(MUST)(Student Only)(Max Chinese length: 10)"
 */

function addUser() {
    // 获取数据
    var role = $("input[name=role]").val();
    var id = $("#id").val();
    var name = $("#name").val();
    var password = $("#password").val();
    var sex = $("input[name=sex]").val();
    var school = $("#school").val();
    var classNo = $("#classNo").val();

    //// 数据检测，将非法数据撤回
    //if ("T" != role && "S" != role) {
    //    console.log("role = " + role + "：非法！");
    //}
    //if (id.length > 15) {
    //    console.log("id.length = " + id.length + "：非法！");
    //}
    //if (name.length > 15) {
    //    console.log("name.length = " + name.length + "：非法！");
    //}
    //if (password.length > 10) {
    //    console.log("password.length = " + password.length + "：非法！");
    //}
    //if ("F" != sex && "M" != sex) {
    //    console.log("sex = " + sex + "：非法！");
    //}
    //if (school.length > 30) {
    //    console.log("school.length = " + school.length + "：非法！");
    //}
    //if (classNo.length > 30) {
    //    console.log("classNo.length = " + classNo.length + "：非法！");
    //}

    // 发送Ajax给服务器
    var info = {
        "role" : role,
        "id" : id,
        "name" : name,
        "password" : password,
        "sex" : sex,
        "school" : school,
        "classNo" : classNo
    };
    info = JSON.stringify(info);
    $.ajax({
        url: "/admin_addUserServlet",
        dataType: "text",
        data: info,
        type: "post"
    });
}

/**
 * 教师页->用户列表->“删除”的JSon格式：
 * id: 用户的id
 * role: 用户的身份(T/S)
 */
function deleteUser() {
    // 获取数据
    var role = $("input[name=role]").val();
    var id = $("#id").val();
    var name = $("#name").val();
    var password = $("#password").val();
    var sex = $("input[name=sex]").val();
    var school = $("#school").val();
    var classNo = $("#classNo").val();

    // 数据检测，将非法数据撤回
    if ("T" != role && "S" != role) {
        console.log("role = " + role + "：非法！");
    }
    if (id.length > 15) {
        console.log("id.length = " + id.length + "：非法！");
    }
    if (name.length > 15) {
        console.log("name.length = " + name.length + "：非法！");
    }
    if (password.length > 10) {
        console.log("password.length = " + password.length + "：非法！");
    }
    if ("F" != sex && "M" != sex) {
        console.log("sex = " + sex + "：非法！");
    }
    if (school.length > 30) {
        console.log("school.length = " + school.length + "：非法！");
    }
    if (classNo.length > 30) {
        console.log("classNo.length = " + classNo.length + "：非法！");
    }

    // 发送Ajax给服务器
    var info = {
        "role" : role,
        "id" : id,
        "name" : name,
        "password" : password,
        "sex" : sex,
        "school" : school,
        "classNo" : classNo
    };
    info = JSON.stringify(info);
    $.ajax({
        url: "/admin_addUserServlet",
        dataType: "json",
        data: info,
        type: "post"
    });
}
