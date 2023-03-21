--1

--список всех работников
select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic
from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id;

--колличество работников
select count(*) from theatre.employee;

--список актеров
select e.name, e.surname, e.patronymic
from
(select fn.name, fn.surname, coalesce(fn.patronymic, '') as patronymic, e.id from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
theatre.actor as a
where a.employee = e.id;

--кол-во актеров
select count(*) from theatre.actor;

--кол-во музыкантов
select count(*) from theatre.musician;

--список музыкантов
select e.name, e.surname, e.patronymic
from
    (select fn.name, fn.surname, coalesce(fn.patronymic, '') as patronymic, e.id from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
    theatre.musician as m
where m.employee = e.id;

--кол-во работников с определенным стажем
select count(*)
from theatre.employee as e
where e.standing = '4-2';

--список работников с определенным стажем
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.standing
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e
where e.standing = '4-2';

--кол-во актеров c определенным стажем
select count(*)
from theatre.actor as a, theatre.employee as e
where a.employee = e.id and e.standing = '4-2';

--список актеров с определенным стажем
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.standing, e.id
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
theatre.actor as a
where a.employee = e.id and e.standing = '4-2';

--кол-во музыкантов c определенным стажем
select count(*)
from theatre.musician as a, theatre.employee as e
where a.employee = e.id and e.standing = '4-2';

--список музыкантов с определенным стажем
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.standing, e.id
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
     theatre.musician as m
where m.employee = e.id and e.standing = '4-2';

--список работников по половому признаку
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.sex
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e
where e.sex = 'М';

--список работников по году рождения
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.birth_year
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e
where e.birth_year = 2002;

--список работников по возрасту
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.age
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e
where e.age = 21;

--список работников по наличию детей
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.children
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e
where e.children = true;

--список работников по кол-ву детей
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.children_number
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e
where e.children_number = 0;

--список работников по размеру зп
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.payment
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e
where e.payment = '$10000';


--2

--число спектаклей в репе
select count(*) from theatre.repertoire;

--список спектаклей в репе
select  p.name
from theatre.repertoire as r, theatre.play as p
where r.play = p.id;

-- сыгранные спектакли
select  p.name
from theatre.repertoire as r, theatre.play as p
where r.play = p.id and r.date < current_date;

--указаннго жанра
select  p.name
from theatre.repertoire as r, theatre.play as p, theatre.genre as g
where r.play = p.id and p.genre = g.id and g.name = 'музыкальная комедия';

--все спектакли театра
select p.name from theatre.play as p;

--все спектакли в репе за указанный период
select p.name
from theatre.repertoire as r, theatre.play as p
where r.play = p.id and r.date between '01.01.2023' and '01.01.2025';

--3

--все спектакли указаннго жанра
select  p.name
from theatre.repertoire as r, theatre.play as p, theatre.genre as g
where r.play = p.id and p.genre = g.id and g.name = 'музыкальная комедия';

--когда либо сыгранные cпектакли
select p.name
from theatre.play as p
where p.premiere < current_date;

--кол-во когда либо поставленных
select count(*)
from theatre.play as p
where p.premiere < current_date;

--все спектакли за указанный период
select p.name
from theatre.play as p
where p.premiere between '01.01.2023' and '01.01.2025';

--4

--список авторов поставленных спектаклей
select f.name, f.surname, coalesce(f.patronymic, '') as patronymic
from theatre.author as a, theatre.play as p, theatre.full_name as f
where p.author = a.id and a.full_name = f.id
group by f.name, f.surname, patronymic;

--список авторв живших в указанном веке
select f.name, f.surname, coalesce(f.patronymic, '') as patronymic
from theatre.author as a, theatre.full_name as f
where a.century = 18 and f.id = a.full_name;

--список авторв указанной страны
select f.name, f.surname, coalesce(f.patronymic, '') as patronymic
from theatre.author as a, theatre.full_name as f
where a.country = 'Россия' and f.id = a.full_name;

--список авторов когда либо поставленных спектаклей указанного жанра
select f.name, f.surname, coalesce(f.patronymic, '') as patronymic
from theatre.author as a, theatre.play as p, theatre.full_name as f, theatre.genre as g
where p.author = a.id and a.full_name = f.id and p.genre = g.id and g.name = 'музыкальная комедия'
group by f.name, f.surname, patronymic;

--список авторов поставленных спектаклей за указанный период
select f.name, f.surname, coalesce(f.patronymic, '') as patronymic
from theatre.author as a, theatre.play as p, theatre.full_name as f
where p.author = a.id and a.full_name = f.id and p.premiere between '01.01.2023' and '01.01.2025'
group by f.name, f.surname, patronymic;

--5

--перечень спектаклей указанного жанра
select  p.name
from  theatre.play as p, theatre.genre as g
where p.genre = g.id and g.name = 'музыкальная комедия';

--некоторого автора
select  p.name
from  theatre.play as p, theatre.author as a, theatre.full_name as f
where  p.author = a.id and a.full_name = f.id
  and f.name = 'Константин' and f.surname = 'Коровин' and f.patronymic = 'Иванович';

--авторов некоторой страны
select  p.name
from  theatre.play as p, theatre.author as a
where  p.author = a.id and a.country = 'Россия';

--написанных в опред веке
select  p.name
from  theatre.play as p
where p.create_century = 18;

--впервые поставленых в указаный период
select  p.name
from  theatre.play as p
where p.premiere between '01.01.2023' and '01.01.2025';

--поставленные---у них была уже премьера