insert into theatre.full_name(name, surname) values ('Иван', 'Шелдяков');
insert into theatre.full_name(name, surname, patronymic) values ('Сергей', 'Нетесов', 'Иванович');
insert into theatre.full_name(name, surname) values ('Мстислав', 'Добужинский');
insert into theatre.full_name(name, surname) values ('Константин', 'Коровин');
insert into theatre.full_name(name, surname) values ('Александр', 'Головин');
insert into theatre.full_name(name, surname, patronymic) values ('Матвей','Гаврилов', 'Вениаминович');
insert into theatre.full_name(name, surname, patronymic) values ('Иосиф', 'Юдин', 'Миронович');
insert into theatre.full_name(name, surname, patronymic) values ('Алексей', 'Голубев', 'Ярославович');
insert into theatre.full_name(name, surname, patronymic) values ( 'Дана', 'Назарова', 'Робертовна');
insert into theatre.full_name(name, surname, patronymic) values ('Владислава', 'Большакова', 'Пётровна');
insert into theatre.full_name(name, surname, patronymic) values ('Амина', 'Сергеева', 'Германовна');
insert into theatre.full_name(surname, name, patronymic) values ('Иванов', 'Иван', 'Иванович');

insert into theatre.category(name) values ('режисер-постановщик'),('художник-постановщик'),('дирижер-постановщик');

insert into theatre.employee(full_name, standing, birth_year, payment) VALUES (1,(INTERVAL '4-2' YEAR TO MONTH),2002,50000);

insert into theatre.employee(full_name, standing, birth_year, children, children_number, payment) VALUES
    (2,(INTERVAL '4-2' YEAR TO MONTH),1996, true, 1, 10000);

insert into theatre.employee(full_name, standing, birth_year, children, children_number, payment, category) VALUES
    (6,(INTERVAL '3-0' YEAR TO MONTH),1995, true, 2, 10000,1);
insert into theatre.employee(full_name, standing, birth_year, children, children_number, payment, category) VALUES
    (7,(INTERVAL '10-0' YEAR TO MONTH),1996, true, 3, 45000, 2);
insert into theatre.employee(full_name, standing, birth_year, payment, category) VALUES
    (8,(INTERVAL '4-2' YEAR TO MONTH),1983, 12000, 3);

insert into theatre.employee(full_name, standing, birth_year, children, children_number, payment,  category) VALUES
    (9,(INTERVAL '6-0' YEAR TO MONTH),1995, true, 2, 12000,1);
insert into theatre.employee( full_name, standing, birth_year, children, children_number, payment,  category) VALUES
    (10,(INTERVAL '10-0' YEAR TO MONTH),2000, true, 2, 45000, 2);
insert into theatre.employee(full_name, standing, birth_year, payment, category) VALUES
    (11,(INTERVAL '2-2' YEAR TO MONTH),1984, 13000, 3);

insert into theatre.producer(employee) values (3),(4),(5);
insert into theatre.producer(employee) values (6),(7),(8);

insert into theatre.actor(employee) values (1);

insert into theatre.musician(employee) values (2);

insert into theatre.genre(name) values ('музыкальная комедия'),('трагедия'),('оперетта');

insert into theatre.audience(name) values ('детский'), ('молодежный'),('для взрослых');

insert into theatre.author(full_name, century, country) values (3, 18, 'Россия');
insert into theatre.author(full_name, century, country) values (4, 19, 'Франция');
insert into theatre.author(full_name, century, country) values (5, 18, 'Россия');


insert into theatre.play(genre, audience, author, director_producer, art_producer, conductor_producer, premiere, name, create_century, places)
values (1,1,1,1,2,3,'1.01.2021','В поисках Гены',18,300);

insert into theatre.play(genre, audience, author, director_producer, art_producer, conductor_producer, premiere, name, create_century, places)
values (1,2,1,4,2,3,'11.20.2002','Кто?',18,250);

insert into theatre.play(genre, audience, author, director_producer, art_producer, conductor_producer, premiere, name, create_century, places)
values (3,3,2,4,5,6,'11.20.2024','Абоба',19,250);

insert into theatre.repertoire(play, date, time) values (1,'4.19.2023','12:00:00');
insert into theatre.repertoire(play, date, time) values (2,'3.19.2023','14:00:00');
insert into theatre.repertoire(play, date, time) values (3,'11.20.2024','20:00:00');

insert into theatre.attribute(attribute) values ('цвет волос'),('рост'),('голос');
insert into theatre.attribute(attribute) values ('пол'),('возраст');
insert into theatre.attribute(attribute) values ('описание роли');

insert into theatre.attribute_value(value, attribute) values ('черный',1), ('русый',1), ('красный',1), ('180',2),('200',2),('178',2),('бас',3),('сопрано',3);
insert into theatre.attribute_value(value, attribute) values ('М',4), ('Ж',4), ('26',5),('27',5),('23',5),('39',5),('40',5),('21',5);
insert into theatre.attribute_value(value, attribute) values ('Врач',6), ('Царь Агат',6);

insert into theatre.employee_attribute(employee, attribute,value) values (1,5,16),(1,4,9),(2,5,11),(2,4,9),(3,5,12),(3,4,9),(4,5,11),(4,4,9),(5,5,15),(5,4,9),(6,5,12),(6,4,10),(7,5,13),(7,4,10),(8,5,14),(8,4,10);

insert into theatre.employee_attribute(employee, attribute, value) values (1,1,1),(1,2,4),(1,3,7);

insert into theatre.employee(full_name, standing, birth_year, children, children_number, payment) VALUES
    (12,(INTERVAL '5-3' YEAR TO MONTH),2000, true, 2, 16000);

insert into theatre.actor(employee) values (9);
insert into theatre.employee_attribute(employee, attribute, value) values (9,5,15),(9,4,9),(9,1,2),(9,2,5),(9,3,8);

insert into theatre.role("primary") values (true);
insert into theatre.role("primary") values (false);

insert into theatre.role_attribute(role, attribute, value) values (1,6,17), (2,6,18), (2,4,9), (1,4,9),(1,1,1);

update theatre.actor set honored_artist = true where id = 1;

insert into theatre.contest(name, date) values ('Оскар', '03.13.2023'), ('Бафта','02.20.2022');

insert into theatre.actor_contest(actor, contest, winner) values (1,1,true), (1,2,false), (2,1,false);

insert into theatre.play_actor(play, actor) values (1,1);

insert into theatre.tour(start, "end", play) values ('01.14.2022','02.28.2022',1);

insert into theatre.play_role(play, role) values (1,1), (1,2);

insert into theatre.play_actor(play, actor) values (1, 2);

update theatre.role SET actor = 1, backup = 2 where id = 1