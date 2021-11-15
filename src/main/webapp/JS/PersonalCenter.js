// JavaScript Document
/*
个人中心页面逻辑
进入用户页面首先默认个人中心
一进入就向UserMsg类发送Ajax请求
将返回的用户名和用户图像路径设置给个人中心的用户图像和用户名和右上角的用户图像

点击修改密码的确认修改
向Password_Change类发送含有旧密码和新密码和确认密码的Ajax请求
如果返回-1
提示新密码和确认密码不一致
如果返回0则提示密码不正确
如果返回1则提示修改成功并恢复修改密码按钮和消除出现的三个输入框和一个确认修改按钮

点击保存
向Save类发送含有新用户图像和新用户名的的Ajax请求
如果返回1则提示保存成功

在设置点击退出用户
向Logout类发送Ajax请求
接收到1跳转到SignINAndRegister界面
 */
$(function() {
    $.ajax({
        url: "UserMsg",
        type: "post",
        datatype: "json",
        error: function (error) {
            alert(error, "请求失败");
        },
        success:function(result){
            var obj = JSON.parse(result);
            var box = document.getElementById("usericon");
            if(result.flag!=1){
                $("#usericon").attr({"src":Img/user/未登录头像.jpg});
                $("#history").attr({"href":"#","data-toggle":"modal","data-target":"#membershipForm"});

            }else{
                $("#usericon").attr({"src":result.id});
                $("#history").attr({"href":"#history","data-toggle":"#","data-target":"#"});

            }
        }
    })
})
    $(function(){
    $(".main_left li").on("click",function(){
        var address =$(this).attr("data-src");
        $("iframe").attr("src",address);
    });
});
$(function(){
    $.ajax({
        url:"UserMsg",
        type:"post",
        data:{},
        datatype:"json",
        error:function(error){
            alert(error+"请求失败");
        },
        success:function(result){
            var Uimgurl = JSON.parse(result).Uimgurl;
            $("#UserImg").attr({src:Uimgurl});
        }
    })
    $.ajax({
        url:"UserMsg",
        type:"post",
        data:{},
        datatype:"json",
        error:function(error){
            alert(error+"请求失败");
        },
        success:function(result){
            var obj = JSON.parse(result);
            $("#userImg").attr({"src":obj.Uimgurl});
            var Uname = document.getElementById("Uname");
            Uname.innerHTML += obj.Uname;
            var Uaccount = document.getElementById("Uaccount");
            Uaccount.innerHTML += obj.Uaccount
        }
    })
})
function getJSON(){
    $.ajax({
        url:'action.php?act=getUserName',
            dataType:'json',
            async:false,
            type:'get',
            data:{},
    });

}

function Upassword(){
$("input[name='uid']").val(uid);
$("#tanchukuang").show;
};
<!--关闭按钮的关闭事件-->
$("#close").click(function(){
    $("#tanchukuang").hide();
})
$("#gb").click(function(){
    $("#tanchukuang").hide();
});
<!--密码输入小于或者大于为6位时，禁止用户提交-->
$(function(){
    $("#StorePassword").on('input',function(){
        if(this.value.length===6){
            $(".queding").prop('disabled',false);
        }else{
            $(".queding").prop('disabled',true);
        }
        $("#newStorePassword").on('input',function(){
            if(this.value.length===6){
                $(".queding").prop('disabled',false);
            }else{
                $(".queding").prop('disabled',true);
            }
        });
    })
});
<!--两次密码输入进行比较-->
$(function(){
    $(".queding").click(function(){
        var p1=$("#StorePassword").val();
        var p1=$("#newStorePassword").val();
        if(!p1){
            alert("密码不能为空!");
        }else
            if (p1!=p2){
            windows.alert("请确认两次输入的密码是否一致")
            return;
        }else{
            var data={
                "uid":$("input[name='uiid']")
            }
            $.post('userStore/updatePassword.smk',data,function(data){
            })
            alert("密码修改成功！");
        }
    })
});
function Save() {
    $.ajax({
        url: "Save",
        type: "post",
        data: {
            "AImg": $("#img").val(),
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
                alert("保存成功");
            }
        }
    })
}
function Signout() {
    $.ajax({
        url: "Logout",
        type: "post",
        error: function (error) {
            alert(error, "请求失败");
        },
        success: function (result) {
            var obj = JSON.parse(result);
            var flag = obj.flag;
            if (flag == 1) {
                window.location.href = "SignINAndRegister.html";
            }
        }
    })
}

