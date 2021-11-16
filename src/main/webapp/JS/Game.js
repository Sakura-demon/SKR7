// JavaScript Document
/*
一进入就向Game_Query类发送Ajax请求
将返回的图片路径和名字和不同的id设置给相应的栏目
 */
$(function() {
    $.ajax({
        url: "Game_Query",
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