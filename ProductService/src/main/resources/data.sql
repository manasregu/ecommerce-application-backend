drop table product if exists;

CREATE TABLE product (
pid int(11) NOT NULL,
name VARCHAR(100) NOT NULL,
brand VARCHAR(100) NOT NULL,
size VARCHAR(100) NOT NULL,
seller VARCHAR(100) NOT NULL,
price float(11) NOT NULL,
PRIMARY KEY(pid)
);

insert into product (pid, name, brand, size, seller, price) values (123,'Pant', 'Raymond', 'L', 'Jack', 1000);
insert into product (pid, name, brand, size, seller, price) values (456,'Sock', 'Nike', 'FS', 'Chandru', 2000);
insert into product (pid, name, brand, size, seller, price) values (567,'Tie', 'Raymond', 'L', 'Murali', 1000);
insert into product (pid, name, brand, size, seller, price) values (899,'Cap', 'Adidas', 'FS', 'Bala', 500);
insert into product (pid, name, brand, size, seller, price) values (111,'Shirt', 'Tommy', 'M', 'Micheal', 1500);