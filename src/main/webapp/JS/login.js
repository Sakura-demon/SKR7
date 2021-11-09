

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
					url:"Signin",
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
						var flag = JSON.parse(result).flag;
						if(flag == 1){
							window.location.href = "index.html";
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
