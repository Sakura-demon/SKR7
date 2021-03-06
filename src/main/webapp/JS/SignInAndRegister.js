

/*登陆部分 */
$(function(){
	$("#submit-button").click(function(){
			if($("#name").val() == ""){
                alert("请输入用户名")
			}
            else if($("#password").val() == ""){
                alert("请输入密码")
            }
			else{
				$.ajax({
					url:"SignIn",
					type:"post",
					data:{
						"Uname":$("#name").val(),
						"Upassword":$("#password").val()
					},
					datatype:"json",
					error:function(error){
						alert(error+"请求失败");
					},
					success:function(result){
						var flag = JSON.parse(result)[0].flag;
						if(flag == 1){
							window.location.href = "Main.html";
						}
						else if(flag == -1){
							$("#name").val("");
							$("#password").val("");
							$("#name").attr({placeholder:"请输入12位的用户名",class:"error"});
						}
						else{
							$("#name").val("");
							$("#password").val("");
							$("#password").attr({placeholder:"用户名或密码错误",class:"error"});
						}
					}
				})
			}
	})
})

/*此部分为注册表单js*/
$(function(){
	$("#submit-button-reg").click(function(){
		if($("#reg-name").val() == ""){
            alert("请输入用户名")
        }
        else if($("#reg-password").val() == ""){
            alert("请输入密码")
        }
        else if($("#reg-password-check").val() == ""){
            alert("请再次输入密码")
        }
        else if($("#reg-password").val()!=$("#reg-password-check").val()){
            alert("请输入相同密码")
        }
		else{
			$.ajax({
				url:"Register",
				type:"post",
				data:{
					"Uname":$("#reg-name").val(),
					"Upassword":$("#reg-password").val()
				},
				datatype:"json",
				error:function(error){
					alert(error+"请求失败");
				},
				success:function(result){
					var flag = JSON.parse(result)[0].flag;
					if(flag == 1){
						alert("注册成功")
						window.location.href = "Main.html";
					}
					else if(flag == -1){
						$("#reg-name").val("");
						$("#reg-password").val("");
						$("#reg-name").attr({placeholder:"请输入12位的用户名",class:"error"});
					}
					else{
						$("#reg-name").val("");
						$("#reg-password").val("");
						$("#reg-password").attr({placeholder:"该用户名已存在",class:"error"});
					}
				}
			})
		}
	})
})
