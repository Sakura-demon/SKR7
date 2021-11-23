$(function(){
    $.ajax({
        url:"Template",
        type:"post",
        data:{
            "flag":"后台接收到信息"
        },
        datatype:"json",
        error:function(error){
            alert(error+"请求失败");
        },
        success:function(result){
            var obj = JSON.parse(result);
            var imgUrl = obj[0].imgUrl;
            var Gname = obj[0].Gname;
            var Gid = obj[0].Gid;
            console.log(imgUrl,Gname);
            $("#a").attr("href","javascript:message("+Gid+")");
            $("#img").attr("src",imgUrl);
            $("#p").html(Gname);
            $("#data").html("返回的图片路径："+imgUrl+"\n返回的名字:"+Gname);
        }
    })
})
function message(Gid){
    $.ajax({
        url:"Game_Transfer",
        type:"post",
        data:{
            Gid:Gid
        },
        datatype:"json",
        error:function(error){
            alert(error+"请求失败");
        },
        success:function(){
            window.location.href = "Game.html";
        }
    })
}