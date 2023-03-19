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