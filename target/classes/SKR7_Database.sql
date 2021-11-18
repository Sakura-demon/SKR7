/*
 负责人：徐亦菲
 */

create database SKR7 default character set utf8;
use SKR7;

/*
数据库
Game（游戏表）
Gid（游戏id int） Gname（游戏名 String） Gtype（类别   String） Gimgurl（游戏图片路径   String）
*/
create table Game(
                     Gid int auto_increment not null primary key,
                     Gname varchar(20) unique,
                     Gtype varchar(20) not null,
                     Gimgurl varchar(100)
)ENGINE = INNODB COLLATE =utf8_bin;


/*
User（用户表）
Uid（用户id int） Uname（用户名 String） Upwd（密码    String） Uimgurl（用户图片路径    String）
*/
create table User(
                     Uid int auto_increment not null primary key,
                     Uname varchar(20) unique ,
                     Upwd varchar(20) not null,
                     Uimgurl varchar(100)
)ENGINE = INNODB COLLATE =utf8_bin;

/*
Video（视频表）
Vid（视频id    int） Gid（游戏id int） Vname（视频标题 String） VUid（作者id    int） Vurl（视频路径 String） Uid（访问的用户  int） time（String 访问时间）
*/
create table Video(
                      Vid int auto_increment not null primary key,
                      Gid int ,
                      Vname varchar(20) unique,
                      VUid int,
                      Vurl varchar(100),
                      Ucount int,
                      time date,
                      constraint foreign key(Gid) references Game(Gid) on update cascade,
                      constraint foreign key(VUid) references User(Uid) on update cascade
)ENGINE = INNODB COLLATE =utf8_bin;


/*
Painting（画作表）
Pid（画作id    int） Gid（游戏id int） Pname（画作名  String） Pid（作者id    int） Purl（画作路径 String） Uid（访问的用户  int） time（String 访问时间）
*/
create table Painting(
                         Pid int auto_increment not null primary key,
                         Gid int ,
                         Pname varchar(20) unique,
                         PUid int,
                         Purl varchar(100),
                         Ucount int,
                         time date,
                         constraint foreign key(Gid) references Game(Gid) on update cascade,
                         constraint foreign key(PUid) references User(Uid) on update cascade
)ENGINE = INNODB COLLATE =utf8_bin;


/*
GSetting(游戏设定集)
GSid（游戏设定集id    int） Gid（游戏id int） GSname（游戏设定集名  String） GSurl（游戏设定集路径 String） GSimgurl（游戏设定集图像路径 String） Oname（开发商名字 String） Uid（访问的用户  int） time（String 访问时间）
*/
create table GSetting(
                         GSid int auto_increment not null primary key,
                         Gid int ,
                         GSname varchar(20) unique,
                         GSurl varchar(100),
                         GSimgurl varchar(100),
                         Oname varchar(20),
                         Ucount int,
                         time date,
                         constraint foreign key(Gid) references Game(Gid) on update cascade
)ENGINE = INNODB COLLATE =utf8_bin;


/*
GMusic（游戏原声集）
GMid（游戏原声集id    int） Gid（游戏id int） GMname（游戏设定集名  String） GMurl（游戏原声集路径 String） GMimgurl（游戏原声集图像路径 String） Oname（开发商名字 String） Uid（访问的用户  int） time（String 访问时间）
*/
create table GMusic(
                       GMid int auto_increment not null primary key,
                       Gid int ,
                       GMname varchar(20) unique,
                       GMurl varchar(100),
                       GMimgurl varchar(100),
                       Oname varchar(20),
                       Ucount int,
                       time date,
                       constraint foreign key(Gid) references Game(Gid) on update cascade
)ENGINE = INNODB COLLATE =utf8_bin;

/*
GAccessories（游戏周边）
GAid（游戏周边id    int） Gid（游戏id int） GAname（游戏周边名  String） GAimgurl（游戏周边图像 String） Sname（商家名字 String）    Uid（访问的用户  int） time（String 访问时间）
*/
create table GAccessories(
                             GAid int auto_increment not null primary key,
                             Gid int ,
                             GAname varchar(20) unique,
                             GAimgurl varchar(100),
                             Sname varchar(20),
                             Ucount int,
                             time date,
                             constraint foreign key(Gid) references Game(Gid) on update cascade
)ENGINE = INNODB COLLATE =utf8_bin;

/*
SignIn存储过程
接收用户名和密码
判断是否存在该账号
如果存在再判断密码是否正确
如果正确返回1和Uid
如果不存在账号或密码错误返回0
*/
DELIMITER \\
create procedure SignIn(in Uname char(12),in Upwd varchar(20),out flag int)
begin
    if exists(select * from User where User.Uname = Uname and User.Upwd = Upwd)
    then set flag = 1;
    select Uid from User where User.Uname = Uname and User.Upwd = Upwd;
    else
        set flag = 0;
    end if;
end
\\

/*
Register存储过程
接收用户名和密码
自动生成Uid并保存用户名和密码
返回1和Uid
已经有账户，返回0
*/
DELIMITER \\
create procedure Register(in Uname char(12),in Upwd varchar(20),in Uimgurl varchar(100),out flag int)
begin
    if exists(select * from User where User.Uname = Uname and User.Upwd = Upwd)
    then set flag = 0;
    else
        insert into User(Uname,Upwd,Uimgurl) values(Uname,Upwd,Uimgurl);
        set flag = 1;
        select Uid from User where User.Uname = Uname and User.Upwd = Upwd;
    end if;
end
\\

/*
GameType_Query存储过程
接收游戏类别
搜索游戏表
返回符合该游戏类别的游戏名和游戏图片路径和Gid
*/
DELIMITER \\
create procedure GameType_Query(in Gtype varchar(20))
begin
    select Gname,Gimgurl,Gid from Game where Game.Gtype = Gtype;
end
\\


/*
GameName_Query存储过程
接收游戏名（可能不全，例如：怪物猎人：世界，这里接收到可能就只有怪猎或怪物猎人）
搜索游戏表
返回含有该游戏名的游戏名和游戏图片路径和Gid
*/
DELIMITER \\
create procedure GameName_Query(in Gname varchar(20))
begin
    select Gname,Gimgurl,Gid from Game where Game.Gname = Gname;
end
\\


/*
Password_Change存储过程，修改密码
接收Uid和旧密码和新密码
先判断旧密码是否正确
不正确返回0
正确将旧密码替换为新密码并返回1
*/
DELIMITER \\
create procedure Password_Change(in Uid int,in OUpwd varchar(20),in NUpwd varchar(20),out flag int)
begin
    if exists(select * from User where User.Uid = Uid and User.Upwd = OUpwd)
    then
        set flag = 1;
        update User set User.Upwd = NUpwd where User.Uid = Uid;
    else
        set flag = 0;
    end if;
end
\\

/*
Save存储过程
接收Uid和用户图像路径和新用户名
将用户图像路径和新用户名设置给Uid对应的用户图像路径和用户名
返回1
*/
DELIMITER \\
create procedure Save(in Uid int,in Uimgurl varchar(100),in NUname varchar(20),out flag int)
begin
    update User set User.Uimgurl = Uimgurl,User.Uname = NUname where User.Uid = Uid;
    set flag = 1;
end
\\

/*
History_Query存储过程
接收Uid和flag
如果flag = 1返回Video（视频表）表中等于该Uid的图片路径和名字和Vid和访问时间，按照访问时间从最近到之前的顺序往下排列，下同
如果flag = 2返回Painting（画作表）表中等于该Uid的图片路径和名字和Pid和访问时间
如果flag = 3返回GSetting(游戏设定集)表中等于该Uid的图片路径和名字和GSid和访问时间
如果flag = 4返回GMusic（游戏原声集）表中等于该Uid的图片路径和名字和GMid和访问时间
如果flag = 5返回GAccessories（游戏周边）表中等于该Uid的图片路径和名字和GAid和访问时间
DELIMITER \\
create procedure History_Query()
begin

end \\
*/

/*
History_Delete存储过程
接收Uid和Vid/Pid/GSid/GMid/GAid和flag
如果flag = 1删除V表中的相应的数据
如果flag = 2删除P表中的相应的数据
如果flag = 3删除GS表中的相应的数据
如果flag = 4删除GM表中的相应的数据
如果flag = 5删除GA表中的相应的数据
返回1
DELIMITER \\
create procedure History_Delete()
begin
    if
    then
    else

    end if;
end
\\
*/

/*
Game_Query存储过程
接收Gid和flag
如果flag = 1，返回V表中的图片路径和名字和id
如果flag = 2，返回P表中的图片路径和名字和id
如果flag = 3，返回GS表中的图片路径和名字和id
如果flag = 4，返回GM表中的图片路径和名字和id
如果flag = 5，返回GA表中的图片路径和名字和id
*/
DELIMITER \\
create procedure Game_Query(in Gid int,in flag int)
begin
    if flag = 0 then select Gimgurl from game where Game.Gid = Gid;end if;
    if flag = 1 then select Vurl,Vname,Vid from Video where Video.Gid = Gid; end if;
    if flag = 2 then select Purl,Pname,Pid  from Painting where Painting.Gid = Gid;end if;
    if flag = 3 then select GSimgurl,GSname,GSid  from GSetting where GSetting.Gid = Gid;end if;
    if flag = 4 then select GMimgurl,GMname,GMid  from GMusic where GMusic.Gid = Gid;end if;
    if flag = 5 then select GAimgurl,GAname,GAid from GAccessories where GAccessories.Gid = Gid;end if;
end
\\

/*
Main_Y存储过程Main_Y(in Uid,in flag)
接收Uid和flag
如果flag = 1，搜索Video（视频表）表中等于该Uid的Gid和time，然后按照time从最近开始往下排序，找到前五个Gid，然后在再Video（视频表）表中找到这五个Gid访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 2，搜索Painting（画作表）表中等于该Uid的Gid和time，然后按照time从最近开始往下排序，找到前五个Gid，然后在再Video（视频表）表中找到这五个Gid访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 3，搜索GSetting(游戏设定集)表中等于该Uid的Gid和time，然后按照time从最近开始往下排序，找到前五个Gid，然后在再Video（视频表）表中找到这五个Gid访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 4，搜索GMusic（游戏原声集）表中等于该Uid的Gid和time，然后按照time从最近开始往下排序，找到前五个Gid，然后在再Video（视频表）表中找到这五个Gid访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 5，搜索GAccessories（游戏周边）表中等于该Uid的Gid和time，然后按照time从最近开始往下排序，找到前五个Gid，然后在再Video（视频表）表中找到这五个Gid访问量最高的前五个数据的图片路径和名字和Gid
DELIMITER \\
create procedure Main_Y(in Uid int,in flag int)
begin
    if flag = 1 then select Vurl,Vname,Gid from Video where Video.Gid = (select Gid from (select Gid,time from Video where Video.Uid = Uid) as Gt order by time desc limit 5) order by Video.Ucount limit 5;end if;
    if flag = 2 then select Purl,Pname,Gid from Painting where Painting.Gid = (select Gid from (select Gid,time from Painting where Painting.Uid = Uid) as Gt order by time desc limit 5) order by Painting.Ucount limit 5;end if;
    if flag = 3 then select GSimgurl,GSname,Gid from GSetting where GSetting.Gid = (select Gid from (select Gid,time from GSetting where GSetting.Uid = Uid) as Gt order by time desc limit 5) order by GSetting.Ucount limit 5;end if;
    if flag = 4 then select GMimgurl,GMname,Gid from GMusic where GMusic.Gid = (select Gid from (select Gid,time from GMusic where GMusic.Uid = Uid) as Gt order by time desc limit 5) order by GMusic.Ucount limit 5;end if;
    if flag = 5 then select GAimgurl,GAname,Gid from GAccessories where GAccessories.Gid = (select Gid from (select Gid,time from GAccessories where GAccessories.Uid = Uid) as Gt order by time desc limit 5) order by GAccessories.Ucount limit 5;end if;
end
\\
*/

/*
Main_N存储过程
接收flag
如果flag = 1，返回Video（视频表）访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 2，返回Painting（画作表）访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 3，返回GSetting(游戏设定集)访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 4，返回GMusic（游戏原声集）访问量最高的前五个数据的图片路径和名字和Gid
如果flag = 5，返回GAccessories（游戏周边）访问量最高的前五个数据的图片路径和名字和Gid
*/
DELIMITER \\
create procedure Main_N(in flag int)
begin
    if flag = 1 then select Vurl,Vname,Gid from Video order by Video.Ucount limit 4;end if;
    if flag = 2 then select Purl,Pname,Gid from Painting order by Painting.Ucount limit 4;end if;
    if flag = 3 then select GSimgurl,GSname,Gid from GSetting order by GSetting.Ucount limit 4;end if;
    if flag = 4 then select GMimgurl,GMname,Gid from GMusic order by GMusic.Ucount limit 4;end if;
    if flag = 5 then select GAimgurl,GAname,Gid from GAccessories order by GAccessories.Ucount limit 4;end if;
end
\\


/*
UserMsg存储过程
接收Uid
返回用户名和用户图像路径
*/
DELIMITER \\
create procedure UserMsg(in Uid int)
begin
    select Uname,Uimgurl from User where User.Uid = Uid;
end
\\
insert into Game(Gname, Gtype, Gimgurl) VALUES ('英雄联盟','strategy','Img/英雄联盟.png');
insert into Game(Gname, Gtype, Gimgurl) VALUES ('绝地求生','action','Img/绝地求生.png');
insert into Game(Gname, Gtype, Gimgurl) VALUES ('只狼','role','Img/只狼.jpg');
insert into Game(Gname, Gtype, Gimgurl) VALUES ('黑暗之魂','role','Img/黑暗之魂.jpg');
insert into Game(Gname, Gtype, Gimgurl) VALUES ('艾尔登法环','role','Img/艾尔登法环.jpg');
insert into Game(Gname, Gtype, Gimgurl) VALUES ('怪物猎人世界','role','Img/怪物猎人世界.jpg');

insert into User(Uname, Upwd, Uimgurl) VALUES ('1','1','Img/user/用户头像.jpg');
insert into User(Uname, Upwd, Uimgurl) VALUES ('2','1','Img/user/用户头像.jpg');
insert into User(Uname, Upwd, Uimgurl) VALUES ('3','1','Img/user/用户头像.jpg');
insert into User(Uname, Upwd, Uimgurl) VALUES ('4','1','Img/user/用户头像.jpg');
insert into User(Uname, Upwd, Uimgurl) VALUES ('5','1','Img/user/用户头像.jpg');
insert into User(Uname, Upwd, Uimgurl) VALUES ('6','1','Img/user/用户头像.jpg');

insert into Video(Gid,Vname,Vurl,Ucount,time) VALUES (1,'a','Img/英雄联盟.png',1,'2021-11-18');
insert into Video(Gid,Vname,Vurl,Ucount,time) VALUES (2,'b','Img/绝地求生.png',2,'2021-11-18');
insert into Video(Gid,Vname,Vurl,Ucount,time) VALUES (3,'c','Img/只狼.jpg',3,'2021-11-18');
insert into Video(Gid,Vname,Vurl,Ucount,time) VALUES (4,'d','Img/黑暗之魂.jpg',4,'2021-11-18');
insert into Video(Gid,Vname,Vurl,Ucount,time) VALUES (5,'e','Img/艾尔登法环.jpg',5,'2021-11-18');
insert into Video(Gid,Vname,Vurl,Ucount,time) VALUES (6,'f','Img/怪物猎人世界.jpg',6,'2021-11-18');

insert into Painting(Gid,Pname,Purl,Ucount,time) VALUES (1,'q','Img/英雄联盟.png',1,'2021-11-18');
insert into Painting(Gid,Pname,Purl,Ucount,time) VALUES (2,'w','Img/绝地求生.png',2,'2021-11-18');
insert into Painting(Gid,Pname,Purl,Ucount,time) VALUES (3,'e','Img/只狼.jpg',3,'2021-11-18');
insert into Painting(Gid,Pname,Purl,Ucount,time) VALUES (4,'r','Img/黑暗之魂.jpg',4,'2021-11-18');
insert into Painting(Gid,Pname,Purl,Ucount,time) VALUES (5,'t','Img/艾尔登法环.jpg',5,'2021-11-18');
insert into Painting(Gid,Pname,Purl,Ucount,time) VALUES (6,'s','Img/怪物猎人世界.jpg',6,'2021-11-18');

insert into GSetting(Gid,GSname,GSurl,GSimgurl,Oname,Ucount,time) VALUES (1,'aa','bb','Img/英雄联盟.png','dd',1,'2021-11-18');
insert into GSetting(Gid,GSname,GSurl,GSimgurl,Oname,Ucount,time) VALUES (2,'bb','bb','Img/绝地求生.png','dd',2,'2021-11-18');
insert into GSetting(Gid,GSname,GSurl,GSimgurl,Oname,Ucount,time) VALUES (3,'cc','bb','Img/只狼.jpg','dd',3,'2021-11-18');
insert into GSetting(Gid,GSname,GSurl,GSimgurl,Oname,Ucount,time) VALUES (4,'dd','bb','Img/黑暗之魂.jpg','dd',4,'2021-11-18');
insert into GSetting(Gid,GSname,GSurl,GSimgurl,Oname,Ucount,time) VALUES (5,'ee','bb','Img/艾尔登法环.jpg','dd',5,'2021-11-18');
insert into GSetting(Gid,GSname,GSurl,GSimgurl,Oname,Ucount,time) VALUES (6,'ff','bb','Img/怪物猎人世界.jpg','dd',6,'2021-11-18');

insert into GMusic(Gid,GMname,GMurl,GMimgurl,Oname,Ucount,time) VALUES (1,'aaa','bb','Img/英雄联盟.png','dd',1,'2021-11-18');
insert into GMusic(Gid,GMname,GMurl,GMimgurl,Oname,Ucount,time) VALUES (2,'bbb','bb','Img/绝地求生.png','dd',2,'2021-11-18');
insert into GMusic(Gid,GMname,GMurl,GMimgurl,Oname,Ucount,time) VALUES (3,'ccc','bb','Img/只狼.jpg','dd',3,'2021-11-18');
insert into GMusic(Gid,GMname,GMurl,GMimgurl,Oname,Ucount,time) VALUES (4,'ddd','bb','Img/黑暗之魂.jpg','dd',4,'2021-11-18');
insert into GMusic(Gid,GMname,GMurl,GMimgurl,Oname,Ucount,time) VALUES (5,'eee','bb','Img/艾尔登法环.jpg','dd',5,'2021-11-18');
insert into GMusic(Gid,GMname,GMurl,GMimgurl,Oname,Ucount,time) VALUES (6,'fff','bb','Img/怪物猎人世界.jpg','dd',6,'2021-11-18');

insert into GAccessories(Gid,GAname,GAimgurl,Sname,Ucount,time) VALUES (1,'a1','Img/英雄联盟.png','cc',1,'2021-11-18');
insert into GAccessories(Gid,GAname,GAimgurl,Sname,Ucount,time) VALUES (2,'b1','Img/绝地求生.png','cc',2,'2021-11-18');
insert into GAccessories(Gid,GAname,GAimgurl,Sname,Ucount,time) VALUES (3,'c1','Img/只狼.jpg','cc',3,'2021-11-18');
insert into GAccessories(Gid,GAname,GAimgurl,Sname,Ucount,time) VALUES (4,'d1','Img/黑暗之魂.jpg','cc',4,'2021-11-18');
insert into GAccessories(Gid,GAname,GAimgurl,Sname,Ucount,time) VALUES (5,'e1','Img/艾尔登法环.jpg','cc',5,'2021-11-18');
insert into GAccessories(Gid,GAname,GAimgurl,Sname,Ucount,time) VALUES (6,'f1','Img/怪物猎人世界.jpg','cc',6,'2021-11-18');