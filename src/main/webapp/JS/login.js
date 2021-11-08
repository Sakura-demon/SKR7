// JavaScript Document
/*

	点击用户，刷新出现
		input（type=text 默认灰色字体账号）
		input（type=password 默认灰色字体密码）
		submit
			点击使用ajax向User_Signin后台Servlet传输用户名和密码
			请求成功后判断后台返回信息
			如果为1表示验证成功，跳转到主页
			如果为-1表示输入的用户名不符合规范，提示输入的用户名不符合规范
			如果都不是表示输入的用户名或密码错误，提示输入的用户名或密码错误
		button（点击返回选择管理员登录或用户登录)
		a“注册”，点击刷新出现
			h1“Sign up”
			input（type=text 默认灰色字体账号）
			p（提示输入规范的用户名）
			input（type=password 默认灰色字体密码）
			submit
				点击使用ajax向User_Signup后台Servlet传输用户名和密码
				请求成功后判断后台返回信息
				如果为1表示注册成功
					h1（提示注册成功）
					p（提示点击下面按钮进入主页）
					button（点击跳转主页）
				如果为-1表示输入的用户名不符合规范，提示输入的用户名不符合规范
				如果都不是表示输入的用户名已存在，提示输入的用户名已存在
*/

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
					url:"User_Signin",
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
