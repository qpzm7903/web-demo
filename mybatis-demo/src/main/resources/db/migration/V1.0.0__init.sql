create table student(
    id int primary key ,
    name varchar(200),
    age int ,
    address varchar(200)
);

create table class(
    id int primary key ,
    address varchar(200),
    information varchar(200)
);

create table teacher(
    id int primary key ,
    title varchar(200)
);

insert into student(id, name, age, address) values ( 1,'test',99,'test');
insert into student(id, name, age, address) values ( 2,'test2',99,'testw');

