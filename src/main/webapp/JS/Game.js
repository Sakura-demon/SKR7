/*此为导航栏部分*/
/*
一进入就向Main类发送Ajax请求
确认用户是否登录
*/
function showpageUser(){
	$.ajax({
		url:"Main",
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
            if(obj.Login!=1){
                $("#usericon").attr({"src":"Img/user/未登录头像.jpg","data-aos":"fade-up","data-aos-delay":"600","data-toggle":"modal","data-target":"#membershipForm"});
                //$("#history").attr({"href":"#","data-toggle":"modal","data-target":"#membershipForm"});
                
            }else{
                $("#usericon").attr({"src":obj.Gimgurl});
				$("#userlink").attr({"href":"PersonalCenter.html"})
                //$("#history").attr({"href":"#history","data-toggle":"#","data-target":"#"});
            }
		}
	})
}

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
			url:"GameName_Transfer",
			type:"post",
			data:{
				"Gname":$("#search").val(),
			},
			datatype:"json",
			error:function(error){
				alert(error+"请求失败");
			},
			success:function(result){
				window.location.href='FindResult.html'
			}
		    })
        }
	})
})




/*
一进入就向Game_Query类发送Ajax请求
将返回的图片路径和名字和不同的id设置给相应的栏目
 */
$(
function showGame(){
	$.ajax({
		url:"Game_Query",
		type:"post",
		data:{},
		datatype:"json",
		error:function(error){
			alert(error+"请求失败");
		},
		success:function(result){
			var obj = JSON.parse(result);
			//给封面传数据
			$("#cover").attr({"src":obj[0].Gimgurl});
            //给各栏目传数据
			for(let i=1;i<6;i++){
				for (let j = 0; j < 1; j++) {
					switch (i) {
						case 1:showVideo(obj[i][j].Vurl,obj[i][j].Vname,obj[i][j].Vid);break;
						case 2:showPicture(obj[i][j].Purl,obj[i][j].Pname,obj[i][j].Pid);break;
						case 3:showSetting(obj[i][j].GSimgurl,obj[i][j].GSname,obj[i][j].GSid);break;
						case 4:showMusic(obj[i][j].GMimgurl,obj[i][j].GMname,obj[i][j].GMid);break;
						case 5:showProduct(obj[i][j].GAimgurl,obj[i][j].GAname,obj[i][j].GAid);break;
					}
				}
			}
		}
	})
}
)

/*跳转页面*/
function showGamePage(Gid){
    $.ajax({
		url:"Game_Transfer",//向后端发送含有Gid的AJAX请求
		type:"post",
		data:{
			"Gid":Gid
		},
		datatype:"json",
		error:function(error){
			alert(error+"请求失败");
		},
		success:function(result){
            window.location.href="Game.html"
		}
	})
}

/*获取后端数据创建前端页面*/
function createDiv(Gimgurl,Gname,Gid){
    var div_result = document.createElement("div");
    div_result.setAttribute("class","ml-lg-auto col-lg-3 col-md-6 col-12");
    div_result.setAttribute("data-aos","fade-up");
    div_result.setAttribute("data-aos-delay","700")
    div_result.innerHTML = 
    '<div class="team-thumb">'+
    	'<img style="height: 200px;width: 330px" src='+Gimgurl+' class="img-fluid" alt="Trainer" id='+Gid+' onclick="showGamePage('+Gid+')">'+
    	'<div class="team-info d-flex flex-column">'+
        	'<h3 class="mb-1">'+Gname+'</h3>'+//此处显示后台传来结果的游戏名
    	'</div>'+
    '</div>';
    return div_result
}
/*同人视频作品数据*/
function showVideo(Gimgurl,Gname,Gid){
    div_result=createDiv(Gimgurl,Gname,Gid)
    document.getElementById("fan-video").appendChild(div_result);
}
/*同人绘画作品数据*/
function showPicture(Gimgurl,Gname,Gid){
    div_result=createDiv(Gimgurl,Gname,Gid)
    document.getElementById("fan-picture").appendChild(div_result);
}
/*游戏设定集*/
function showSetting(Gimgurl,Gname,Gid){
    div_result=createDiv(Gimgurl,Gname,Gid)
    document.getElementById("game-set").appendChild(div_result);
}
/*游戏原声集*/
function showMusic(Gimgurl,Gname,Gid){
    div_result=createDiv(Gimgurl,Gname,Gid)
    document.getElementById("game-music").appendChild(div_result);
}
/*游戏周边*/
function showProduct(Gimgurl,Gname,Gid){
    div_result=createDiv(Gimgurl,Gname,Gid)
    document.getElementById("game-product").appendChild(div_result);
}

