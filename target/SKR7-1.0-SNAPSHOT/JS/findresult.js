// JavaScript Document
/*
点击游戏向Game_Transfer类发送含有Gid的Ajax请求
如果成功跳转到Game页面
 */
/*跳转页面
    点击游戏跳转
    点击游戏向Game_Transfer类发送含有Gid的Ajax请求
    如果返回1跳转到Game页面
*/
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

/*返回搜索框内容
GameTypeAndName_Query逻辑
一进入就向GameTypeAndName_Query类发送Ajax请求
将返回的游戏名和图片路径和Gid设置给FindResult页面
*/
/*获取后端数据创建前端页面*/
function createDiv(Gimgurl,Gname,Gid){
    var div_result = document.createElement("div");
    div_result.setAttribute("class","col-lg-4 col-md-6 col-12");
    div_result.setAttribute("data-aos","fade-up");
    div_result.setAttribute("data-aos-delay","400")
    div_result.innerHTML = 
    	'<div class="class-thumb" id='+Gid+'>'+
    		'<img style="height: 200px;width: 330px" src='+Gimgurl+' class="img-fluid" alt="Class" onclick="showGamePage('+Gid+')">'+
    		'<div class="class-info">'+
        		'<h3 class="mb-1">'+Gname+'</h3>'+//此处显示后台传来结果的游戏名
    		'</div>'+
    	'</div>';
    return div_result
}
/*搜索结果栏目数据*/
function showResult(Gimgurl,Gname,Gid){
    div_result=createDiv(Gimgurl,Gname,Gid)
    document.getElementById("result").appendChild(div_result);
}
/*
向后端请求数据
*/
$(
function getResult(){
	$.ajax({
		url:"GameTypeAndName_Query",
		type:"post",
		datatype:"json",
		error:function(error){
			alert(error+"请求失败");
		},
		success:function(result){
			var obj = JSON.parse(result);
            //给搜索栏目传数据
            for(let i=0;i<obj[0].len;i++){
            	showResult(obj[1][i].Gimgurl,obj[1][i].Gname,obj[1][i].Gid);
            }
		}
	})
}
)

/*此部分为导航栏部分*/

/*
一进入就向Main类发送Ajax请求
确认用户是否登录
*/
function showpageUser(){
	$.ajax({
		url:"Main",
		type:"post",
		data:{},
		datatype:"json",
		error:function(error){
			alert(error+"请求失败");
		},
		success:function(result){
			var obj = JSON.parse(result);
            if(obj[0].Login!=1){
                $("#usericon").attr({"src":"Img/user/未登录头像.jpg","data-aos":"fade-up","data-aos-delay":"600","data-toggle":"modal","data-target":"#membershipForm"});
                //$("#history").attr({"href":"#","data-toggle":"modal","data-target":"#membershipForm"});
            }else{
                $("#usericon").attr({"src":obj[6].Uimgurl});
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
            window.location.href="Main.html";
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
				window.location.href = 'FindResult.html'
			}
		    })
        }
	})
})

