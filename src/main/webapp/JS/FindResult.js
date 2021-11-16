// JavaScript Document
/*
GameTypeAndName_Query逻辑
一进入就向GameTypeAndName_Query类发送Ajax请求
将返回的游戏名和图片路径和Gid设置给FindResult页面

点击游戏向Game_Transfer类发送含有Gid的Ajax请求
如果成功跳转到Game页面
 */



/*此部分编写返回搜索内容部分*/
$(function(){
	$("#Admin").click(function(){
		var Choice = document.getElementById("Choice");
		Choice.innerHTML = 
			'<input type="password" placeholder="口令" class="text" id="password">'+
			'<br>'+
			'<br>'+
			'<br>'+
			'<input type="submit" id="submit">'+
			'<br>'+
			'<br>'+
			'<button id="back">返回</button>';
		Choice.style.display = "block";
		$("#submit").click(function(){
			if($("#password").val() == ""){
				$("#password").attr({placeholder:"请输入12位口令",class:"error"});
			}
			else{
				$.ajax({
					url:"Admin_Signin",
					type:"post",
					data:{"Apassword":$("#password").val()},
					datatype:"json",
					error:function(error){
						alert(error,"请求失败");
					},
					success:function(result){
						var flag = JSON.parse(result).flag;
						if(flag == 1){
							window.location.href = "Main.html";
						}
						else if(flag == -1){
							$("#password").val("");
							$("#password").attr({placeholder:"请输入12位口令",class:"error"});
						}
						else{
							$("#password").val("");
							$("#password").attr({placeholder:"口令错误",class:"error"});
						}
					}
				})
			}
		})
	})

    $
})

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
			var obj = JSON.parse(result);
			var box = document.getElementById("usericon");
            if(result.flag==1){
                window.location.href="../Game.html"
            }else{
                alert('跳转失败')
            }
		}
	})
}

function showpageResult(){
    /*要获取返回数据的个数以及游戏各种数据
      向后台获取数据
    */
    $.ajax({
        url:"GameTypeAndName_Query",
        type:"post",
        dataType:"json",
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(XMLHttpRequest);
            alert(textStatus);
            alert(errorThrown);
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
                document.getElementById("result").appendChild(li);
            });
        }
    });

		result.style.display = "block";

}


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

