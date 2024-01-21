use mysql;

create table user_authenticate (
id int not null auto_increment  primary key,
user_name varchar(255),
password varchar(255),
token varchar(255)
);

insert into user_authenticate (user_name, password, token) values ('+84586099640', '123456', '12313dsdasdsd');

create table article_selly (
id int not null auto_increment  primary key,
product_id varchar(255),
product_name varchar(255),
article_type varchar(255),
article_price bigint,
description text
);

create table product_photo_url(
id int not null auto_increment  primary key,
product_id varchar(255),
product_name varchar(255),
photo_url varchar(255),
shopee_photo_id varchar(255)
);