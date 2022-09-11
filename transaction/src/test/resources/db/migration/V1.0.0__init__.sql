create table todo(
    id int auto_increment,
    name varchar(200),
    title varchar(200),
    content varchar(1000),
    done tinyint
);

insert into todo(name,title,content,done) values('test','test','test',0);