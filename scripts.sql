//Получить всех студентов, возраст которых находится между 30 и 43
select *
from student
where 30 < age
  and age < 43;
//Получить всех студентов, но отобразить только список их имен
select name
from student;
//Получить всех студентов, у которых в имени присутствует буква «A»
select *
from student
where name like '%А%';
//Получить всех студентов, у которых возраст меньше идентификатора
select *
from student
where age < student.id;
// Получить всех студентов упорядоченных по возрасту
select *
from student
order by age;