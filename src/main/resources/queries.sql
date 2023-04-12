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
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, av.value as sex
      from theatre.full_name as fn, theatre.employee as e, theatre.attribute as a, theatre.employee_attribute as ea, theatre.attribute_value as av where e.full_name = fn.id and a.attribute = 'пол' and e.id = ea.employee and ea.attribute = a.id and av.id = ea.value) as e
where e.sex = 'Ж';

--список работников по году рождения
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.birth_year
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e
where e.birth_year = 2002;

--список работников по возрасту
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, av.value as age
      from theatre.full_name as fn, theatre.employee as e, theatre.attribute as a, theatre.employee_attribute as ea, theatre.attribute_value as av where e.full_name = fn.id and a.attribute = 'возраст' and e.id = ea.employee and ea.attribute = a.id and av.id = ea.value) as e
where e.age = '26';

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
from theatre.play as p, theatre.genre as g
where p.genre = g.id and g.name = 'музыкальная комедия';

--список когда либо поставленных
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
where p.author = a.id and a.full_name = f.id and p.premiere < current_date
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
where p.author = a.id and a.full_name = f.id and p.genre = g.id and g.name = 'музыкальная комедия' and p.premiere < current_date
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

--6

--список актеров подходящих под роль
select fn.surname, fn.name, coalesce(fn.patronymic, '') as patronymic
from theatre.full_name as fn, theatre.actor as a, theatre.employee as e, theatre.employee_attribute as ea, (select ra.attribute, ra.value
                                                                                   from theatre.role_attribute as ra
                                                                                   where ra.role = 2) as f
where e.id = a.employee and f.attribute = ea.attribute and f.value = ea.value and fn.id = e.full_name
group by fn.surname, fn.name, fn.patronymic;

--7

--колличество актеров +
select count(*)
from theatre.actor;


--список актеров +
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.standing, e.id
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
     theatre.actor as a
where a.employee = e.id;

--есть звания +
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.standing, e.id
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
     theatre.actor as a
where a.employee = e.id and (a.honored_artist = true or a.national_artist = true);

--получившие звания за нек период +
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.standing, e.id
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
     theatre.actor as a, theatre.actor_contest as ac, theatre.contest as c
where a.employee = e.id and ac.actor = a.id and (a.honored_artist = true or a.national_artist = true) and ac.contest = c.id and ac.winner = true and c.date between '01.01.2023' and '01.01.2025';

--получившие звания на указ конкурсе +
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.standing, e.id
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
     theatre.actor as a, theatre.actor_contest as ac, theatre.contest as c
where a.employee = e.id and ac.actor = a.id and (a.honored_artist = true or a.national_artist = true) and ac.contest = c.id and ac.winner = true and c.name = 'Оскар';

--по полу +
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, av.value as sex, e.id
      from theatre.full_name as fn, theatre.employee as e, theatre.attribute as a, theatre.employee_attribute as ea, theatre.attribute_value as av
      where e.full_name = fn.id and a.attribute = 'пол' and e.id = ea.employee and ea.attribute = a.id and av.id = ea.value) as e,
    theatre.actor as a
where e.sex = 'М' and a.employee  = e.id ;

--список по возрасту +
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, av.value as age, e.id
      from theatre.full_name as fn, theatre.employee as e, theatre.attribute as a, theatre.employee_attribute as ea, theatre.attribute_value as av
      where e.full_name = fn.id and a.attribute = 'возраст' and e.id = ea.employee and ea.attribute = a.id and av.id = ea.value) as e,
    theatre.actor as a
where e.age = '21' and e.id = a.employee;

--8

--список актеров и постановщика в туре за указанный период
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.id
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
     theatre.actor as a, theatre.play as p, theatre.play_actor as pa, theatre.producer as pro, theatre.tour as t
where ((a.employee = e.id and a.id = pa.actor and pa.play = p.id) or (pro.employee = e.id and (pro.id = p.art_producer or pro.id = p.conductor_producer or pro.id = p.director_producer))) and t.play = p.id and t.start > '10.02.2021' and t."end" < '01.01.2024'
group by e.name, e.surname, e.patronymic;

--список актеров и постановщика в туре опред спектакля и уехавших в точное время
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.id
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
     theatre.actor as a, theatre.play as p, theatre.play_actor as pa, theatre.producer as pro, theatre.tour as t
where ((a.employee = e.id and a.id = pa.actor and pa.play = p.id) or (pro.employee = e.id and (pro.id in (p.director_producer,p.art_producer, p.conductor_producer)))) and t.play = p.id and t.start = '01.14.2022' and p.id = 1
group by e.name, e.surname, e.patronymic;

--9

--список актеров спектакля
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.id
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
    theatre.actor as a, theatre.play as p, theatre.play_actor as pa
where p.id = 1 and e.id = a.employee and pa.play = p.id and pa.actor = a.id;

--список дублеров спектакля
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.id
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
     theatre.actor as a, theatre.play as p, theatre.play_role as pr, theatre.role as r
where p.id = 1 and e.id = a.employee and pr.play = p.id and pr.role = r.id and r.backup = a.id;

--список постановщиков
select e.name, e.surname, e.patronymic
from (select fn.name as name, fn.surname as surname, coalesce(fn.patronymic, '') as patronymic, e.id
      from theatre.full_name as fn, theatre.employee as e where e.full_name = fn.id) as e,
     theatre.producer as pro, theatre.play as p
where p.id = 1 and e.id = pro.employee and (pro.id in (p.director_producer,p.art_producer, p.conductor_producer));

--список авторов
select f.name, f.surname, coalesce(f.patronymic, '') as patronymic
from theatre.author as a, theatre.play as p, theatre.full_name as f
where p.author = a.id and a.full_name = f.id and p.premiere < current_date
group by f.name, f.surname, patronymic;

--дата премьеры
select p.premiere
from theatre.play as p
where p.id = 1;

--10

--все роли актера
select av.value
from theatre.actor as a, theatre.role as r, theatre.role_attribute as ra, theatre.attribute_value as av, theatre.attribute as at
where a.id = 1 and r.actor = a.id and ra.role = r.id and ra.attribute = at.id and at.attribute = 'описание роли' and ra.value = av.id and av.attribute = at.id;

--кол-во ролей актера +
select count(*)
from theatre.actor as a, theatre.role as r
where a.id = 1 and r.actor = a.id;

--все роли актера за опред время
select av.value
from theatre.actor as a, theatre.role as r, theatre.role_attribute as ra, theatre.attribute_value as av, theatre.attribute as at, theatre.play_role as pr, theatre.play as p, theatre.repertoire as rep
where a.id = 1 and r.actor = a.id and ra.role = r.id and ra.attribute = at.id and at.attribute = 'описание роли' and ra.value = av.id and av.attribute = at.id and pr.role = r.id and pr.play = p.id and p.id = rep.play and rep.date between '01.01.2023' and '01.01.2025'; ;

--роли в спектаклях опред жанра
select av.value
from  theatre.role as r, theatre.role_attribute as ra, theatre.attribute_value as av, theatre.attribute as at, theatre.play_role as pr, theatre.play as p, theatre.genre as g
where ra.role = r.id and ra.attribute = at.id and at.attribute = 'описание роли' and ra.value = av.id and av.attribute = at.id and pr.role = r.id and pr.play = p.id and p.genre = g.id and g.name = 'музыкальная комедия'
group by av.value;

--роли в спектакле опред реж постановщика
select av.value
from  theatre.role as r, theatre.role_attribute as ra, theatre.attribute_value as av, theatre.attribute as at, theatre.play_role as pr, theatre.play as p, theatre.producer as pro
where  ra.role = r.id and ra.attribute = at.id and at.attribute = 'описание роли' and ra.value = av.id and av.attribute = at.id and pr.role = r.id and pr.play = p.id and p.director_producer = pro.id and pro.id = 1;

--роли в детских спектаклях
select av.value
from  theatre.role as r, theatre.role_attribute as ra, theatre.attribute_value as av, theatre.attribute as at, theatre.play_role as pr, theatre.play as p, theatre.audience as aud
where ra.role = r.id and ra.attribute = at.id and at.attribute = 'описание роли' and ra.value = av.id and av.attribute = at.id and pr.role = r.id and pr.play = p.id and p.audience = aud.id and aud.name = 'детский';

--11

--все проданные билеты
select count(*)
from theatre.ticket;

--колво на конкретный спектакль
select count(*)
from theatre.ticket as t, theatre.play as p
where t.play = p.id and p.id = 1;

--кол-во билетов на премьеры
select count(*)
from theatre.ticket as t, theatre.play as p, theatre.repertoire as rep
where t.play = p.id and p.id = rep.play and p.premiere = rep.date;

--кол-во проданных билетов за период
select count(*)
from theatre.ticket as t
where t.buy_date between '01.01.2021' and '02.01.2023';

--12

--сумма денег на указанный спектакль
select sum(t.cost)
from theatre.ticket as t, theatre.play as p
where t.play = p.id and p.id = 1;

--сумма за определеное время
select sum(t.cost)
from theatre.ticket as t
where t.buy_date between '01.01.2021' and '02.01.2023';

--13

--кол-во свободных мест на все спектакли

select sum(p.places) - t.count as count
from theatre.play as p, theatre.repertoire as rep, (select count(*) as count
                                                    from theatre.ticket as t, theatre.play as p
                                                    where t.play = p.id) as t
where p.id = rep.play
group by count;

--кол-во свободных мест на конкрет спек
select p.places - t.count as count
from theatre.play as p, theatre.repertoire as rep, (select count(*) as count
                                                    from theatre.ticket as t, theatre.play as p
                                                    where t.play = p.id and p.id = 1) as t
where p.id = rep.play and p.id = 1;


--кол-во свободных мест на премьеры
select sum(p.places) - t.count as count
from theatre.play as p, theatre.repertoire as rep, (select count(*) as count
                                                    from theatre.ticket as t, theatre.play as p, theatre.repertoire as rep
                                                    where t.play = p.id and rep.play = p.id and rep.date = p.premiere) as t
where p.id = rep.play and rep.date = p.premiere
group by count;

