/*
 负责人：徐亦非
 */
create database SKR7 default character set utf8;
use SKR7;
/*
数据库
Game（游戏表）
Gid（游戏id int） Gname（游戏名 String） Gtype（类别   String） Gimgurl（游戏图片路径   String）
User（用户表）
Uid（用户id int） Uname（用户名 String） Upwd（密码    String） Uimgurl（用户图片    String）
Video（视频表）
Vid（视频id    int） Gid（游戏id int） Vname（视频标题 String） Vid（作者id    int） Vurl（视频路径 String） Uid（访问的用户  int） time（String 访问时间）
Painting（画作表）
Pid（画作id    int） Gid（游戏id int） Pname（画作名  String） Pid（作者id    int） Purl（画作路径 String） Uid（访问的用户  int） time（String 访问时间）
GSetting(游戏设定集)
GSid（游戏设定集id    int） Gid（游戏id int） GSname（游戏设定集名  String） GSurl（游戏设定集路径 String） GSimgurl（游戏设定集图像路径 String） Oname（开发商名字 String） Uid（访问的用户  int） time（String 访问时间）
GMusic（游戏原声集）
GMid（游戏原声集id    int） Gid（游戏id int） GMname（游戏设定集名  String） GMurl（游戏原声集路径 String） GMimgurl（游戏原声集图像路径 String） Oname（开发商名字 String） Uid（访问的用户  int） time（String 访问时间）
GAccessories（游戏周边）
GAid（游戏周边id    int） Gid（游戏id int） GAname（游戏周边名  String） GAimgurl（游戏周边图像 String） Sname（商家名字 String）    Uid（访问的用户  int） time（String 访问时间）
 */
/*
SignIn存储过程
接收用户名和密码
判断是否存在该账号
如果存在再判断密码是否正确
如果正确返回1和Uid
如果不存在账号或密码错误返回0

Register存储过程
接收用户名和密码
自动生成Uid并保存用户名和密码
返回1和Uid

GameType_Query存储过程
接收游戏类别
搜索游戏表
返回符合该游戏类别的游戏名和游戏图片路径和Gid

GameName_Query存储过程
接收游戏名（可能不全，例如：怪物猎人：世界，这里接收到可能就只有怪猎或怪物猎人）
搜索游戏表
返回含有该游戏名的游戏名和游戏图片路径和Gid

Password_Change存储过程
接收Uid和旧密码和新密码
先判断旧密码是否正确
不正确返回0
正确将旧密码替换为新密码并返回1

Save存储过程
接收Uid和用户图像路径和新用户名
将用户图像路径和新用户名设置给Uid对应的用户图像路径和用户名
返回1

History_Query存储过程
接收Uid和flag
如果flag = 1返回Video（视频表）表中等于该Uid的图片路径和名字和Vid和访问时间，按照访问时间从最近到之前的顺序往下排列，下同
如果flag = 2返回Painting（画作表）表中等于该Uid的图片路径和名字和Pid和访问时间
如果flag = 3返回GSetting(游戏设定集)表中等于该Uid的图片路径和名字和GSid和访问时间
如果flag = 4返回GMusic（游戏原声集）表中等于该Uid的图片路径和名字和GMid和访问时间
如果flag = 5返回GAccessories（游戏周边）表中等于该Uid的图片路径和名字和GAid和访问时间

History_Delete存储过程
接收Uid和Vid/Pid/GSid/GMid/GAid和flag
如果flag = 1删除V表中的相应的数据
如果flag = 2删除P表中的相应的数据
如果flag = 3删除GS表中的相应的数据
如果flag = 4删除GM表中的相应的数据
如果flag = 5删除GA表中的相应的数据
返回1

Game_Query存储过程
接收Gid
搜索五个表内Gid = 传入值Gid的数据
返回这些数据的图片路径和名字和id，分五个数组返回

Main_Y存储过程Main_Y(in Uid,in flag)
接收Uid和flag
如果flag = 1，搜索Video（视频表）表中等于该Uid的Gid和time，然后按照time从最近开始往下排序，找到前五个Gid，然后在再Video（视频表）表中找到这五个Gid访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 2，搜索Painting（画作表）表中等于该Uid的Gid和time，然后按照time从最近开始往下排序，找到前五个Gid，然后在再Video（视频表）表中找到这五个Gid访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 3，搜索GSetting(游戏设定集)表中等于该Uid的Gid和time，然后按照time从最近开始往下排序，找到前五个Gid，然后在再Video（视频表）表中找到这五个Gid访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 4，搜索GMusic（游戏原声集）表中等于该Uid的Gid和time，然后按照time从最近开始往下排序，找到前五个Gid，然后在再Video（视频表）表中找到这五个Gid访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 5，搜索GAccessories（游戏周边）表中等于该Uid的Gid和time，然后按照time从最近开始往下排序，找到前五个Gid，然后在再Video（视频表）表中找到这五个Gid访问量最高的前五个数据的图片路径和名字和Gid

Main_N存储过程
接收flag
如果flag = 1，返回Video（视频表）访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 2，返回Painting（画作表）访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 3，返回GSetting(游戏设定集)访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 4，返回GMusic（游戏原声集）访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 5，返回GAccessories（游戏周边）访问量最高的前五个数据的图片路径和名字和Gid

UserMsg存储过程
接收Uid
返回用户名和用户图像路径
*/