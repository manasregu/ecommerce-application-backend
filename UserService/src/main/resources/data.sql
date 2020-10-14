DROP table Customer if exists;

CREATE TABLE Customer (
 id int(100) NOT NULL AUTO_INCREMENT,
 name VARCHAR(100) ,
 password VARCHAR(500),
 email VARCHAR(500),
 PRIMARY KEY(id)
);

insert into Customer (name, password, email) values ('Manas','one','manasregu@gmail.com');
insert into Customer (name, password, email) values ('lakshmi','two','lakshmiragu252000@gmail.com');
insert into Customer (name, password, email) values ('Visa','three','17coae022@ldc.edu.in');
insert into Customer (name, password, email) values ('Regu','five','manasadeviregupathynagarajan@gmail.com');

