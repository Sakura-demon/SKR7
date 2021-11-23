/*   导航栏部分JS   */

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
				console.log(result)
                window.location.href='FindResult.html'
			}
		    })
        }
	})
})
/*
个人中心部分写在下面
*/


/*     页面数据显示JS     */

/*
一进入就向Main类发送Ajax请求
返回给前端每个表内和该用户最近访问的条目类似的访问量最高的前五个数据的图片和名字
和用户图像和Gid和用户图像和用户名
*/
$(
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
				$("#userlink").attr({"href":"PersonalCentertest.html"})
                 //$("#history").attr({"href":"#history","data-toggle":"#","data-target":"#"}); 
            }
            //给各栏目传数据
            for(let i=1;i<=5;i++){
                for (let j = 0; j < 4; j++) {
                    switch (i) {
                        case 1:showVideo(obj[i][j].Vurl,obj[i][j].Vname,obj[i][j].Gid);break;
                        case 2:showPicture(obj[i][j].Purl,obj[i][j].Pname,obj[i][j].Gid);break;
                        case 3:showSetting(obj[i][j].GSimgurl,obj[i][j].GSname,obj[i][j].Gid);break;
                        case 4:showMusic(obj[i][j].GMimgurl,obj[i][j].GMname,obj[i][j].Gid);break;
                        case 5:showProduct(obj[i][j].GAimgurl,obj[i][j].GAname,obj[i][j].Gid);break;
                    }
                }
            }
		}
	})
}
)

/*获取后端数据创建前端页面*/
function createDiv(Gimgurl,Gname,Gid){
    var div_result = document.createElement("div");
    div_result.setAttribute("class","ml-lg-auto col-lg-3 col-md-6 col-12");
    div_result.setAttribute("data-aos","fade-up");
    div_result.setAttribute("data-aos-delay","700")
    div_result.innerHTML =
        '<div class="team-thumb" id='+Gid+'>'+
            '<img style="height: 200px;width: 330px" src='+Gimgurl+' class="img-fluid" alt="Trainer" onclick="showGamePage('+Gid+')">'+
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


/*
    点击游戏跳转
    点击游戏向Game_Transfer类发送含有Gid的Ajax请求
    如果返回1跳转到Game页面
*/
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

/*此部分为页面静态数据，由于部分数据库未完善先使用该数据*/
var gamenamelist=["战地2042","宝可梦：晶灿钻石/明亮珍珠","天命奇御2","光环：无限","真三国无双8：帝国"]
var gamemakerlist=["DICE/EA","任天堂","甲山林娱乐","微软","KOEI"]
var gametimelist=["2021-11-19","2021-11-19","2021-11-23","2021-12-08","2021-12-23"]
var gameintrolist=["《战地2042》是一款由EA制作并发行的第一人称射击游戏，游戏会含有大规模动态破坏效果，游戏地图中几乎所有建筑都可以被摧毁，与任务目标相关的建筑除外。建筑的摧毁过程会因受损处的不同而有所变化。",
"《宝可梦：晶灿钻石/明亮珍珠》游戏中，玩家可以和《宝可梦钻石·珍珠》中登场的宝可梦们一起冒险。",
"《天命奇御2》除了场景承袭了前代的设计外，游戏内的建筑造型及造景皆全面新制，更加细致地刻画了游戏场景，地图采用无接缝地图，而战斗方面主角更可使用多种武器。",
 "《光环：无限》当所有的希望都破灭，人类的命运悬而未决时，士官长们已经准备好面对最无情的敌人了。传说中的《光环》系列回归，并且有着至今为止最庞大的士官长故事。",
 "《真三国无双8：帝国》游戏中采用各种手段来攻略的“攻城战”，使用破坏、潜入等来攻城。玩家可以体验到君主、将军等身处不同地位的人物的生存方式，为打造强国和各种武将进行交流"]


/*前端定死人气游戏前五名的数据*/
function showHotGame(Gimgurl,Gname){
    result_div=createDiv(Gimgurl,Gname)
    document.getElementById("hot-game").appendChild(result_div);
}

//滚动播放图片

   function my$(id) {
       return document.getElementById(id);
   }

   //获取各元素，方便操作
   var box=my$("box");
   var inner=box.children[0];
   var ulObj=inner.children[0];
   var list=ulObj.children;//滚动图片列表li
   var olObj=inner.children[1];//图片页标bar
   var arr=my$("arr");//左右焦点
   var imgWidth=inner.offsetWidth;
   var right=my$("right");//右箭头
   var pic=0;//存放图片


/*此部分为右侧游戏介绍部分，此部分功能还未完善，暂时先用静态数据*/
   var gameinfo=my$("game-info")
   var gamename=gameinfo.children[0]
   var gamemaker=gameinfo.children[1]
   var gametime=gameinfo.children[3]
   var gameintro=gameinfo.children[5]

   //根据li个数，创建小按钮
   for(var i=0;i<list.length;i++){
       var liObj=document.createElement("li");//小按钮li

       olObj.appendChild(liObj);
       liObj.innerText=(i+1);
       liObj.setAttribute("index",i);

       //为按钮注册mouseover事件
       liObj.onmouseover=function () {
           //先清除所有按钮的样式
           for (var j=0;j<olObj.children.length;j++){
               olObj.children[j].removeAttribute("class");
           }
           this.className="current";
           pic=this.getAttribute("index");
           animate(ulObj,-pic*imgWidth);
           infochange(pic);
       }

   }

   //设置ol中第一个li有背景颜色
   olObj.children[0].className = "current";
   //克隆一个ul中第一个li,加入到ul中的最后=====克隆
   ulObj.appendChild(ulObj.children[0].cloneNode(true));

   var timeId=setInterval(onmouseclickHandle,2000);
   //左右焦点实现点击切换图片功能
   box.onmouseover=function () {
       arr.style.display="block";
       clearInterval(timeId);
   };
   box.onmouseout=function () {
       arr.style.display="none";
       timeId=setInterval(onmouseclickHandle,2000);
   };

   right.onclick=onmouseclickHandle;
   function onmouseclickHandle() {
       //如果pic的值是5,恰巧是ul中li的个数-1的值,此时页面显示第六个图片,而用户会认为这是第一个图,
       //所以,如果用户再次点击按钮,用户应该看到第二个图片
       if (pic == list.length - 1) {
           //如何从第6个图,跳转到第一个图
           pic = 0;//先设置pic=0
           ulObj.style.left = 0 + "px";//把ul的位置还原成开始的默认位置
       }
       pic++;//立刻设置pic加1,那么此时用户就会看到第二个图片了
       animate(ulObj, -pic * imgWidth);//pic从0的值加1之后,pic的值是1,然后ul移动出去一个图片
       //如果pic==5说明,此时显示第6个图(内容是第一张图片),第一个小按钮有颜色,
       if (pic == list.length - 1) {
           //第五个按钮颜色干掉
           olObj.children[olObj.children.length - 1].className = "";
           //第一个按钮颜色设置上
           olObj.children[0].className = "current";
       } else {
           //干掉所有的小按钮的背景颜色
           for (var i = 0; i < olObj.children.length; i++) {
               olObj.children[i].removeAttribute("class");
           }
           olObj.children[pic].className = "current";
       }
       infochange(pic)
   }
   left.onclick=function () {
       if (pic==0){
           pic=list.length-1;
           ulObj.style.left=-pic*imgWidth+"px";
       }
       pic--;
       animate(ulObj,-pic*imgWidth);
       for (var i = 0; i < olObj.children.length; i++) {
           olObj.children[i].removeAttribute("class");
       }
       //当前的pic索引对应的按钮设置颜色
       olObj.children[pic].className = "current";
       infochange(pic);
   };
   
   //设置任意的一个元素,移动到指定的目标位置
   function animate(element, target) {
       clearInterval(element.timeId);
       //定时器的id值存储到对象的一个属性中
       element.timeId = setInterval(function () {
           //获取元素的当前的位置,数字类型
           var current = element.offsetLeft;
           //每次移动的距离
           var step = 90;
           step = current < target ? step : -step;
           //当前移动到位置
           current += step;
           if (Math.abs(current - target) > Math.abs(step)) {
               element.style.left = current + "px";
           } else {
               //清理定时器
               clearInterval(element.timeId);
               //直接到达目标
               element.style.left = target-40 + "px";
           }
       }, 40);
   }
    
   //改变游戏介绍页信息
   function infochange(pic){
       if(pic==5)pic=0
        my$("gamename").innerText=gamenamelist[pic]
        my$("gamemaker").innerText=gamemakerlist[pic]
        my$("gametime").innerText=gametimelist[pic]
        my$("gameintro").innerText=gameintrolist[pic]
   }
   my$("bar").onmouseover(infochange(pic))