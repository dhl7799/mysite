-- user
desc user;
select * from user;

-- join
insert into user values (null, '안대혁', 'kickscar@gmail.com', password('1234'), 'male', now());

-- login
select no, name from user where email = 'kickscar@gmail.com' and password = password('1234');
select * from board where title LIKE '%%' order by g_no desc, o_no asc, depth asc;
select * from board;
desc user;
delete from board;
select* from user;
desc guestbook;
select* from guestbook;
desc board;
select* from board where title LIKE '%%';
set sql_safe_updates=0;
select * from user;
select * from board join user where board.user_no = user.no;
select * from board;

desc user;
alter table user add column role enum("ADMIN","USER") default "USER" after gender;
insert into user values (null, '관리자', 'admin@mysite.com', password('1234'), 'male',"ADMIN",now());












