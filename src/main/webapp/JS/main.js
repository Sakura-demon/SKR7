/*
一进入就向Main类发送Ajax请求
将返回图片路径和名字和Gid设置给对应栏内和用户图像
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
            if(result.flag!=1){
                $("#usericon").attr({"src":Img/user/未登录头像.jpg});
                $("#history").attr({"href":"#","data-toggle":"modal","data-target":"#membershipForm"});
                
            }else{
                $("#usericon").attr({"src":result.Gid});
                $("#history").attr({"href":"#history","data-toggle":"#","data-target":"#"});
                
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
				console.log(result)
                if(result.flag==1){
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
点击游戏向Game_Transfer类发送含有Gid的Ajax请求
如果返回1跳转到Game页面


写在findresult.js里面了
*/

/*
向后端要人气游戏前五名的数据
*/
function showHotGame(){
    /*要获取返回数据的个数以及游戏各种数据
      向后台获取数据
    */
    $.ajax({
        url:"Main",
        type:"post",
        data:{
            "type":"hotgame",
        },
        datatype:"json",
        error:function(error){
            alert(error+"请求失败");
        },
        success:function(data){
            $.each(data,function(idx,obj){
                var div_result = document.createElement("div");
                div_result.setAttribute("class","col-lg-4 col-md-6 col-12");
                div_result.setAttribute("data-aos","fade-up");
                div_result.setAttribute("data-aos-delay","400")
                li.innerHTML = 
                '<div class="class-thumb">'+
                '<img src='+obj.Gid+'class="img-fluid" alt="Class" onclick="showGamePage('+obj.Gid+')">'+//此处显示后台传来结果的图片路径,点击后向Game_Transfer传AJAX
                '<div class="class-info">'+
                    '<h3 class="mb-1">'+obj.name+'</h3>'+//此处显示后台传来结果的游戏名
                '</div>'+
                '</div>';
                document.getElementById("hot-game").appendChild(li);
            });
        }
    })
}
/*
同人视频作品数据
*/
function showFanWorkVideo(){
    /*要获取返回数据的个数以及游戏各种数据
      向后台获取数据
    */
    $.ajax({
        url:"Main",
        type:"post",
        data:{
            "type":"fanvideo",
        },
        datatype:"json",
        error:function(error){
            alert(error+"请求失败");
        },
        success:function(data){
            $.each(data,function(idx,obj){
                var div_result = document.createElement("div");
                div_result.setAttribute("class","col-lg-4 col-md-6 col-12");
                div_result.setAttribute("data-aos","fade-up");
                div_result.setAttribute("data-aos-delay","400")
                li.innerHTML = 
                '<div class="class-thumb">'+
                '<img src='+obj.Gid+'class="img-fluid" alt="Class" onclick="showGamePage('+obj.Gid+')">'+//此处显示后台传来结果的图片路径,点击后向Game_Transfer传AJAX
                '<div class="class-info">'+
                    '<h3 class="mb-1">'+obj.name+'</h3>'+//此处显示后台传来结果的游戏名
                '</div>'+
                '</div>';
                document.getElementById("fanvideo").appendChild(li);
            });
        }
    })
}
/*
同人绘画作品数据
*/
function showFanWorkPicture(){
    $.ajax({
        url:"Main",
        type:"post",
        data:{
            "type":"fanpicture",
        },
        datatype:"json",
        error:function(error){
            alert(error+"请求失败");
        },
        success:function(data){
            $.each(data,function(idx,obj){
                var div_result = document.createElement("div");
                div_result.setAttribute("class","col-lg-4 col-md-6 col-12");
                div_result.setAttribute("data-aos","fade-up");
                div_result.setAttribute("data-aos-delay","400")
                li.innerHTML = 
                '<div class="class-thumb">'+
                '<img src='+obj.Gid+'class="img-fluid" alt="Class" onclick="showGamePage('+obj.Gid+')">'+//此处显示后台传来结果的图片路径,点击后向Game_Transfer传AJAX
                '<div class="class-info">'+
                    '<h3 class="mb-1">'+obj.name+'</h3>'+//此处显示后台传来结果的游戏名
                '</div>'+
                '</div>';
                document.getElementById("fanpicture").appendChild(li);
            });
        }
    })
}
/*
游戏设定集
*/
function showGameSetting(){
    $.ajax({
        url:"Main",
        type:"post",
        data:{
            "type":"gameset",
        },
        datatype:"json",
        error:function(error){
            alert(error+"请求失败");
        },
        success:function(data){
            $.each(data,function(idx,obj){
                var div_result = document.createElement("div");
                div_result.setAttribute("class","col-lg-4 col-md-6 col-12");
                div_result.setAttribute("data-aos","fade-up");
                div_result.setAttribute("data-aos-delay","400")
                li.innerHTML = 
                '<div class="class-thumb">'+
                '<img src='+obj.Gid+'class="img-fluid" alt="Class" onclick="showGamePage('+obj.Gid+')">'+//此处显示后台传来结果的图片路径,点击后向Game_Transfer传AJAX
                '<div class="class-info">'+
                    '<h3 class="mb-1">'+obj.name+'</h3>'+//此处显示后台传来结果的游戏名
                '</div>'+
                '</div>';
                document.getElementById("game-set").appendChild(li);
            });
        }
    })
}
/*
游戏原声集
*/
function showGameMusic(){
    $.ajax({
        url:"Main",
        type:"post",
        data:{
            "type":"gamemusic",
        },
        datatype:"json",
        error:function(error){
            alert(error+"请求失败");
        },
        success:function(data){
            $.each(data,function(idx,obj){
                var div_result = document.createElement("div");
                div_result.setAttribute("class","col-lg-4 col-md-6 col-12");
                div_result.setAttribute("data-aos","fade-up");
                div_result.setAttribute("data-aos-delay","400")
                li.innerHTML = 
                '<div class="class-thumb">'+
                '<img src='+obj.Gid+'class="img-fluid" alt="Class" onclick="showGamePage('+obj.Gid+')">'+//此处显示后台传来结果的图片路径,点击后向Game_Transfer传AJAX
                '<div class="class-info">'+
                    '<h3 class="mb-1">'+obj.name+'</h3>'+//此处显示后台传来结果的游戏名
                '</div>'+
                '</div>';
                document.getElementById("game-music").appendChild(li);
            });
        }
    })
}
/*
游戏周边
*/
function showGameProduct(){
    $.ajax({
        url:"Main",
        type:"post",
        data:{
            "type":"gameproduct",
        },
        datatype:"json",
        error:function(error){
            alert(error+"请求失败");
        },
        success:function(data){
            $.each(data,function(idx,obj){
                var div_result = document.createElement("div");
                div_result.setAttribute("class","col-lg-4 col-md-6 col-12");
                div_result.setAttribute("data-aos","fade-up");
                div_result.setAttribute("data-aos-delay","400")
                li.innerHTML = 
                '<div class="class-thumb">'+
                '<img src='+obj.Gid+'class="img-fluid" alt="Class" onclick="showGamePage('+obj.Gid+')">'+//此处显示后台传来结果的图片路径,点击后向Game_Transfer传AJAX
                '<div class="class-info">'+
                    '<h3 class="mb-1">'+obj.name+'</h3>'+//此处显示后台传来结果的游戏名
                '</div>'+
                '</div>';
                document.getElementById("game-product").appendChild(li);
            });
        }
    })
}
