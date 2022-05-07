select *
from student
where 30 < age
  and age < 43;
select name
from student;
select *
from student
where name like '%А%';
select *
from student
where age < student.id;
SELECT *
from student
order by age;