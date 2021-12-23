create database hibernateTest;
use hibernateTest;
create table user(
         	 `id` INT  NOT NULL AUTO_INCREMENT,
            `name` VARCHAR(10) NOT NULL,
            `password` VARCHAR(20) NOT NULL,
			 PRIMARY KEY (`id`) USING BTREE
         );  
         
create table backup_user(
         	 `id` INT  NOT NULL AUTO_INCREMENT,
            `name` VARCHAR(10) NOT NULL,
            `password` VARCHAR(20) NOT NULL,
			 PRIMARY KEY (`id`) USING BTREE
         );
         
insert into user(name,password) value("alan","123456");
insert into user(name,password) value("terry","a12345");
insert into user(name,password) value("tom","123321123");       
insert into user(name,password) value("jacky","878787");
insert into user(name,password) value("karen","tva231");
   
/* 個人測試用
select * from hibernatetest.user;
select * from hibernatetest.backup_user;
insert into backup_user(name,password) select name,password from user;

select name from user u where not exists (select name from backup_user b where b.id = u.id); 
insert into backup_user(name,password) select name,password from user Where Not Exists(select * From backup_user Where user.id = backup_user.id);

alter table user auto_increment=1;
alter table backup_user auto_increment=1;
*/