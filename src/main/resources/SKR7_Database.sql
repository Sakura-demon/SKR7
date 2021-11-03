/*
 负责人：徐亦非
 */
create database SKR7 default character set utf8;
use SKR7;
/*
数据库
Game（游戏表）
Gid（游戏id int） Gname（游戏名 String） Gtype（类别   String） Gimg（游戏图片   类型未知）
User（用户表）
Uid（用户id int） Uname（用户名 String） Upwd（密码    String） Uimg（用户图片    类型未知）
Video（视频表）
Vid（视频id    int） Gid（游戏id int） Vname（视频标题 String） Vid（作者id    int） Vimg（视频图像 未知类型） Uid（访问的用户  int） time（String 访问时间）
Painting（画作表）
Pid（画作id    int） Gid（游戏id int） Pname（画作名  String） Pid（作者id    int） Pimg（画作图像 未知类型） Uid（访问的用户  int） time（String 访问时间）
GSetting(游戏设定集)
GSid（游戏设定集id    int） Gid（游戏id int） GSname（游戏设定集名  String） GSimg（游戏设定集图像 未知类型） Oname（开发商名字 String） Uid（访问的用户  int） time（String 访问时间）
GMusic（游戏原声集）
GMid（游戏原声集id    int） Gid（游戏id int） GMname（游戏设定集名  String） GMimg（游戏原声集图像 未知类型） Oname（开发商名字 String） Uid（访问的用户  int） time（String 访问时间）
GAccessories（游戏周边）
GAid（游戏周边id    int） Gid（游戏id int） GAname（游戏周边名  String） GAimg（游戏周边图像 未知类型） Sname（商家名字 String）    Uid（访问的用户  int） time（String 访问时间）
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
返回符合该游戏类别的游戏名和游戏图片和Gid

GameName_Query存储过程
接收游戏名（可能不全，例如：怪物猎人：世界，这里接收到可能就只有怪猎或怪物猎人）
搜索游戏表
返回含有该游戏名的游戏名和游戏图片和Gid

Password_Change存储过程
接收Uid和旧密码和新密码
先判断旧密码是否正确
不正确返回0
正确将旧密码替换为新密码并返回1

Save存储过程
接收Uid和用户图像和新用户名（如何接收用户图像请研究一下）
将用户图像和新用户名设置给Uid对应的用户图像和用户名
返回1

History_Query存储过程
接收Uid
搜索Video（视频表）Painting（画作表）GSetting(游戏设定集)GMusic（游戏原声集）GAccessories（游戏周边）五个表
找出该Uid访问的数据并返回对应的图片和名字和Vid、Pid、GSid、GMid和GAid和访问时间，按照最近的时间往下排序

History_Delete存储过程
接收Uid和Vid/Pid/GSid/GMid/GAid和flag
如果flag = 1删除V表中的
如果flag = 2删除P表中的
如果flag = 3删除GS表中的
如果flag = 4删除GM表中的
如果flag = 5删除GA表中的
删除表内相应的数据
返回1

Game_Query存储过程
接收Gid
搜索五个表内Gid = 传入值Gid的数据
返回这些数据的图片和名字和id，分五个数组返回

Main_Y存储过程
接收Uid
搜索五个表
返回每个表内和该用户最近访问的条目类似的访问量最高的前五个数据的图片和名字和Gid
（通俗点就是将五个表组合成只有Uid和Gid和time的表，再在里面搜索该Uid的Gid和time，然后按照time从最近开始往下排序，找到前五个Gid，然后去每个表中找到这个五个Gid访问量最高的数据，返回）

Main_N存储过程
搜索五个表
返回每个表内访问量最高的前五个数据的图片和名字和Gid

UserMsg存储过程
接收Uid
返回用户名和用户图像
*/