drop table Cart if exists;

CREATE TABLE Cart (
cid int(11) NOT NULL AUTO_INCREMENT,
uid int(11) NOT NULL,
pid int(11) NOT NULL,
name VARCHAR(50) NOT NULL,
price float(50) NOT NULL,
PRIMARY KEY(cid)
);

insert into Cart (uid,pid,name,price) values (1,123,'Pant',1000);
insert into Cart (uid,pid,name,price) values (1,456,'Sock',2000);
insert into Cart (uid,pid,name,price) values (2,567,'Tie',1000);
insert into Cart (uid,pid,name,price) values (2,899,'Cap',2000);
insert into Cart (uid,pid,name,price) values (3,111,'Shirt',1000);
insert into Cart (uid,pid,name,price) values (3,899,'Cap',2000);




