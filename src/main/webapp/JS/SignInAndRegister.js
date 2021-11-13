// JavaScript Document
/*
Signin的逻辑
向SignIn类发送含有用户名和密码的Ajax请求
如果返回0提示账号不存在或密码错误
如果返回1就跳转到首页
Register逻辑
向Register类发送含有用户名和密码的Ajax请求
如果返回1就跳转到首页
 */
$(function(){
$.ajax({
    url:"Signin",
    type:"post",
    data:{
    "Apassword":$("#password").val()
},
    datatype:"json",
    error:function(error){
        alert(error,"请求失败");
        },
success:function(result){
        var obj = JSON.parse(result);
        var flag = obj.flag;
        if(flag==1){
            window.location.href = "Main.html";
        }
        else{
            alert("密码错误");
        }
}
})
    $.ajax({
        url:"Register",
        type:"post",
        data:{
            "Uid":$("#uid").val()
        },
        datatype:"json",
        error:function(error){
            alert(error,"请求失败");
        },
        success:function(result){
            var obj = JSON.parse(result);
            var flag = obj.flag;
            if(flag==1){
                window.location.href = "Main.html";
            }
            else{
                alert("账号不存在");
            }
        }
    })
 })
function Signout() {
    $.ajax({
        url: "Signin",
        type: "post",
        data: {
            "Apassword": $("#password").val(),
            "Auid": $("#uid").val()
        },
        datatype: "json",
        error: function (error) {
            alert(error, "请求失败");
        },
        success: function (result) {
            var obj = JSON.parse(result);
            var flag = obj.flag;
            if (flag == 1) {
                window.location.href = "Main.html";
            }
        }
    })
}

