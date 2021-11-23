/*   导航栏部分JS   */

/*
通过点击游戏类别
向GameType_Transfer类发送Ajax请求
如果返回1则跳转到FindResult页面
*/
$(function(){
	$("#action").click(function(){
		$.ajax({
			url:"GameType_Transfer",
			type:"post",
			data:{
				"Gtype":"action",
			},
			datatype:"json",
			error:function(error){
				alert(error+"请求失败");
			},
			success:function(result){
				window.location.href = "FindResult.html?result="+result;
				console.log(result)
			}
		})
	})
    $("#role").click(function(){
		$.ajax({
			url:"GameType_Transfer",
			type:"post",
			data:{
				"Gtype":"role",
			},
			datatype:"json",
			error:function(error){
				alert(error+"请求失败");
			},
			success:function(result){
				window.location.href = "FindResult.html?result="+result;
				console.log(result)
			}
		})
	})
    $("#strategy").click(function(){
		$.ajax({
			url:"GameType_Transfer",
			type:"post",
			data:{
				"Gtype":"strategy",
			},
			datatype:"json",
			error:function(error){
				alert(error+"请求失败");
			},
			success:function(result){
				window.location.href = "FindResult.html?result="+result;
				console.log(result)
			}
		})
	})
    $("#relax").click(function(){
		$.ajax({
			url:"GameType_Transfer",
			type:"post",
			data:{
				"Gtype":"relax",
			},
			datatype:"json",
			error:function(error){
				alert(error+"请求失败");
			},
			success:function(result){
				window.location.href = "FindResult.html?result="+result;
				console.log(result)
			}
		})
	})
    $("#sim").click(function(){
		$.ajax({
			url:"GameType_Transfer",
			type:"post",
			data:{
				"Gtype":"sim",
			},
			datatype:"json",
			error:function(error){
				alert(error+"请求失败");
			},
			success:function(result){
				window.location.href = "FindResult.html?result="+result;
				console.log(result)
			}
		})
	})
    $("#sport").click(function(){
		$.ajax({
			url:"GameType_Transfer",
			type:"post",
			data:{
				"Gtype":"sport",
			},
			datatype:"json",
			error:function(error){
				alert(error+"请求失败");
			},
			success:function(result){
				window.location.href = "FindResult.html?result="+result;
				console.log(result)
			}
		})
	})
})

/*
通过搜索游戏名
向GameName_Transfer类发送Ajax请求
如果返回1则跳转到FindResult页面
*/
$(function(){
    $("#search-but").click(function(){
        if($("#search").val() == ""){
            $("#search").attr({placeholder:"输入不可为空",class:"error"});
            window.location.href="../index.html";
        }
        else{
            $.ajax({
			url:"GameName_Query",
			type:"post",
			data:{
				"Gname":$("#search").val(),
			},
			datatype:"json",
			error:function(error){
				alert(error+"请求失败");
			},
			success:function(result){
				var obj = JSON.parse(result);
                if(obj.flag==1){
                    window.location.href='../FindResult.html'
                }
                else{
                    alert("请求失败")
                }
			}
		    })
        }
	})
})

/*
一进入就向Main类发送Ajax请求
返回用户图像和用户名
*/
function showpageUser(){
	$.ajax({
		url:"Main",
		type:"post",
		datatype:"json",
		error:function(error){
			alert(error+"请求失败");
		},
		success:function(result){
			var obj = JSON.parse(result);
            $("#usericon").attr({"src":obj.Uimgurl});
		}
	})
}






/*
个人中心页面逻辑
进入用户页面首先默认个人中心
一进入就向UserMsg类发送Ajax请求
将返回的用户名和用户图像路径设置给个人中心的用户图像和用户名和右上角的用户图像*/
$(function(){
    $.ajax({
        url:"UserMsg",
        type:"post",
        datatype:"json",
        error:function(error){
            alert(error+"请求失败");
        },
        success:function(result){
			var obj=JSON.parse(result);
            var Uimgurl = obj.Uimgurl;
            $("#UserImg").attr({src:obj.Uimgurl});
			$("#Uname").attr({placeholder:obj.Uname});
        }
    })
})



/*
点击修改密码的确认修改
向Password_Change类发送含有旧密码和新密码和确认密码的Ajax请求
如果返回-1
提示新密码和确认密码不一致
如果返回0则提示密码不正确
如果返回1则提示修改成功并恢复修改密码按钮和消除出现的三个输入框和一个确认修改按钮
*/

/*此部分为修改密码表单js*/
$(function(){
	$("#submit-button-reg").click(function(){
		if($("#reg-name").val() == ""){
            alert("请输入旧密码")
        }
        else if($("#reg-password").val() == ""){
            alert("请输入新密码")
        }
        else if($("#reg-password-check").val() == ""){
            alert("请再次输入密码")
        }
        else if($("#reg-password").val()!=$("#reg-password-check").val()){
            alert("请输入相同密码")
        }
		else{
			$.ajax({
				url:"Password_change",
				type:"post",
				data:{
                    "Upassword":$("#reg-name").val(),
					"Upassword":$("#reg-password").val(),
                    "Upassword":$("#reg-password-check").val()
				},
				datatype:"json",
				error:function(error){
					alert(error+"请求失败");
				},
				success:function(result){
					var flag = JSON.parse(result).flag;
					if(flag == -1){
						alert("新密码和确认密码不一致")
						$("#reg-password-check").attr({placeholder:"请重新输入确认密码",class:"error"});
					}
                    if(flag == 0){
						alert("旧密码不正确")
                        $("#reg-name").attr({placeholder:"请输入正确旧密码",class:"error"});
						
					}
					else if(flag == 1){
                        alert("修改成功")
                        window.location.href = "PersonalCenter.html";
					}
				}
			})
		}
	})
})

/*
点击保存
向Save类发送含有新用户图像和新用户名的的Ajax请求
如果返回1则提示保存成功
*/
function save() {
    $.ajax({
        url: "Save",
        type: "post",
        data: {
            "Uimage": $("#UserImg").attr("src"),
            "Uname": $("#Uname").val()
        },
        datatype: "json",
        error: function (error) {
            alert(error, "请求失败");
        },
        success: function (result) {
            var obj = JSON.parse(result);
            var flag = obj[0].flag;
            if (flag == 1) {
				$("#savebut").attr({"data-target":"#saveMsgForm"});
				$('#savebut').click();
            }else{
				$("#savebut").attr({"data-target":"#"});
			}
        }
    })
}
/*
在设置点击退出用户
向Logout类发送Ajax请求
接收到1跳转到SignINAndRegister界面
 */
function Signout() {
    $.ajax({
        url: "Logout",
        type: "post",
        error: function (error) {
            alert(error, "请求失败");
        },
        success: function (result) {
			window.location.href = "Main.html";
        }
    })
}















