//первый JOIN-запрос

SELECT student.name, student.age , faculty.name, faculty.color FROM  student
INNER JOIN faculty ON  faculty_id = faculty_id;

//второй JOIN-запрос

SELECT student.name from student
 inner join avatar a on student.id = a.student_id