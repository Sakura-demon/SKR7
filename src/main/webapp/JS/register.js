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
				url:"User_Signup",
				type:"post",
				data:{
					"Upname":$("#reg-name").val(),
					"Uppassword":$("#reg-password").val()
				},
				datatype:"json",
				error:function(error){
					alert(error+"请求失败");
				},
				success:function(result){
					var flag = JSON.parse(result).flag;
					if(flag == 1){
						alert("注册成功")
						window.location.href = "index.html";
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