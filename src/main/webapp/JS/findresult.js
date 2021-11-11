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
                window.href.location="../Game.html"
            }else{
                alert('跳转失败')
            }
		}
	})
}

function showpageResult(){
	var Choice = document.getElementById("result");
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