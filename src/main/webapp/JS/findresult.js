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
})

function showpageResult(){
	var Choice = document.getElementById("Choice");
    /*要获取返回数据的个数以及游戏各种数据
      向后台获取数据
    */
    $.ajax({
        url:"GameName_Query",
        type:"post",
        dataType:"json",
        error:function(XMLHttpRequest, textStatus, errorThrown){
            alert(XMLHttpRequest);
            alert(textStatus);
            alert(errorThrown);
        },
        success:function(data){
            $.each(data,function(idx,obj){
                var li = document.createElement("li");
                li.innerHTML = "<a>" + obj.name + "</a>";
                document.getElementById("ul1").appendChild(li);
            });
        }
    });


		Choice.innerHTML = 
			'<div class="col-lg-4 col-md-6 col-12" data-aos="fade-up" data-aos-delay="400">'+
            '<div class="class-thumb">'+
                '<img src="../Img/class/yoga-class.jpg" class="img-fluid" alt="Class">'+
                '<div class="class-info">'+
                    '<h3 class="mb-1">Yoga</h3>'+
                    '<span><strong>Trained by</strong> - Bella</span>'+
                    '<p class="mt-3">Lorem ipsum dolor sit amet, consectetur adipiscing</p>'+
                '</div>'+
            '</div>'+
            '</div>';
		Choice.style.display = "block";

}