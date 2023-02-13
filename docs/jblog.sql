
desc blog;
select * from category;
set sql_safe_updates=0;
delete from user;
delete from user where no = 2;
select * from category;
insert into category values(null,'dooly','기본 카테고리',now());
insert into blog values ('dhl7799','이동현의 블로그','/assets/images/spring-logo.jpg');
select no from category order by no asc limit 1;
select * from post;
select * from category;
select c.no, c.blog_id, c.name, c.description, c.reg_date, {select * from post where category_no = c.no} as postCount from category c join post p where c.blog_id = 'dhl7799' group by c.no;
