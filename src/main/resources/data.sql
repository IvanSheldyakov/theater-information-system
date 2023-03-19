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

insert into theatre.employee(age, full_name, standing, birth_year, payment, sex) VALUES
                                                                                     (21,1,(INTERVAL '4-2' YEAR TO MONTH),2002,50000,'М');

insert into theatre.employee(age, full_name, standing, birth_year, children, children_number, payment, sex) VALUES
    (26,2,(INTERVAL '4-2' YEAR TO MONTH),1996, true, 1, 10000,'М');

insert into theatre.employee(age, full_name, standing, birth_year, children, children_number, payment, sex, category) VALUES
    (27,6,(INTERVAL '3-0' YEAR TO MONTH),1995, true, 2, 10000,'М',1);
insert into theatre.employee(age, full_name, standing, birth_year, children, children_number, payment, sex, category) VALUES
    (26,7,(INTERVAL '10-0' YEAR TO MONTH),1996, true, 3, 45000,'М', 2);
insert into theatre.employee(age, full_name, standing, birth_year, payment, sex, category) VALUES
    (40,8,(INTERVAL '4-2' YEAR TO MONTH),1983, 12000,'М', 3);

insert into theatre.employee(age, full_name, standing, birth_year, children, children_number, payment, sex, category) VALUES
    (27,9,(INTERVAL '6-0' YEAR TO MONTH),1995, true, 2, 12000,'Ж',1);
insert into theatre.employee(age, full_name, standing, birth_year, children, children_number, payment, sex, category) VALUES
    (23,10,(INTERVAL '10-0' YEAR TO MONTH),2000, true, 2, 45000,'Ж', 2);
insert into theatre.employee(age, full_name, standing, birth_year, payment, sex, category) VALUES
    (39,11,(INTERVAL '2-2' YEAR TO MONTH),1984, 13000,'Ж', 3);

insert into theatre.producer(employee) values (3),(4),(5);
insert into theatre.producer(employee) values (6),(7),(8);

insert into theatre.actor(employee) values (1);

insert into theatre.musician(employee) values (2);

insert into theatre.genre(name) values ('музыкальная комедия'),('трагедия'),('оперетта');

insert into theatre.audience(name) values ('детский'), ('молодежный'),('для взрослых');

insert into theatre.author(full_name, century, country) values (3, 18, 'Россия');
insert into theatre.author(full_name, century, country) values (4, 19, 'Франция');
insert into theatre.author(full_name, century, country) values (5, 18, 'Россия');

insert into theatre.category(name) values ('режисер-постановщик'),('художник-постановщик'),('дирижер-постановщик');

insert into theatre.play(genre, audience, author, director_producer, art_producer, conductor_producer, premiere, name, create_century, places)
values (1,1,1,1,2,3,'1.01.2021','В поисках Гены',18,300); --век косяк был

insert into theatre.play(genre, audience, author, director_producer, art_producer, conductor_producer, premiere, name, create_century, places)
values (1,2,1,4,2,3,'11.20.2002','Кто?',18,250);

insert into theatre.play(genre, audience, author, director_producer, art_producer, conductor_producer, premiere, name, create_century, places)
values (3,3,2,4,5,6,'11.20.2024','Абоба',19,250);

insert into theatre.repertoire(play, date, time) values (1,'4.19.2023','12:00:00');
insert into theatre.repertoire(play, date, time) values (2,'3.19.2023','14:00:00');
insert into theatre.repertoire(play, date, time) values (3,'11.20.2024','20:00:00');
