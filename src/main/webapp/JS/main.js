/*此部分实现用户未登录时候用户头像变成登录按钮功能*/
function showpageUser(){
	$.ajax({
		url:"Main_Query",//向后端请求cookie
		type:"post",
		data:{
			info:"if user login?"
		},
		datatype:"json",
		error:function(error){
			alert(error+"请求失败");
		},
		success:function(result){
			var obj = JSON.parse(result);
			var box = document.getElementById("usericon");
            if(result!=1){
                $("#usericon").attr({"src":Img/user/未登录头像.jpg});
                $("#history").attr({"href":"#","data-toggle":"modal","data-target":"#membershipForm"});
                
            }else{
                $("#usericon").attr({"src":Img/user/用户头像.jpg});
                $("#history").attr({"href":"#history","data-toggle":"#","data-target":"#"});
                
            }
		}
	})
}