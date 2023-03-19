CREATE TABLE "theatre".employee(
                                   "id" serial,
                                   "age" integer NOT NULL CHECK ( "age" > 0 ),
                                   "full_name" integer NOT NULL,
                                   "standing" interval NOT NULL,
                                   "birth_year" integer NOT NULL CHECK ( "birth_year" > 0 ),
                                   "children" bool NOT NULL DEFAULT 'false',
                                   "children_number" integer NOT NULL DEFAULT '0',
                                   "payment" money NOT NULL,
                                   "sex" varchar(1) NOT NULL,
                                   "category" integer,
                                   CONSTRAINT "employee_pk" PRIMARY KEY ("id")
);



CREATE TABLE "theatre".actor(
                                "id" serial,
                                "employee" integer NOT NULL UNIQUE,
                                "honored_artist" bool NOT NULL DEFAULT 'false',
                                "national_artist" bool NOT NULL DEFAULT 'false',
                                "student" bool NOT NULL DEFAULT 'false',
                                CONSTRAINT "actor_pk" PRIMARY KEY ("id")
);



CREATE TABLE "theatre".musician(
                                   "id" serial,
                                   "employee" integer NOT NULL UNIQUE,
                                   CONSTRAINT "musician_pk" PRIMARY KEY ("id")
);



CREATE TABLE "theatre".producer(
                                   "id" serial,
                                   "employee" integer NOT NULL UNIQUE,
                                   CONSTRAINT "producer_pk" PRIMARY KEY ("id")
);



CREATE TABLE "theatre".servant(
                                  "id" serial,
                                  "employee" integer NOT NULL UNIQUE,
                                  CONSTRAINT "servant_pk" PRIMARY KEY ("id")
);



CREATE TABLE "theatre".role(
                               "id" serial,
                               "actor" integer NOT NULL,
                               "primary" bool NOT NULL DEFAULT 'false',
                               "backup" integer,
                               CONSTRAINT "role_pk" PRIMARY KEY ("id")
);



CREATE TABLE "theatre".play(
                               "id" serial,
                               "genre" integer NOT NULL,
                               "audience" integer NOT NULL,
                               "author" integer NOT NULL,
                               "director_producer" integer NOT NULL,
                               "art_producer" integer NOT NULL,
                               "conductor_producer" integer NOT NULL,
                               "premiere" DATE NOT NULL,
                               "name" TEXT NOT NULL,
                               "create_century" integer NOT NULL,
                               "places" integer NOT NULL CHECK ( "places" > 0),
                               CONSTRAINT "play_pk" PRIMARY KEY ("id")
);



CREATE TABLE "theatre".play_role(
                                    "play" serial,
                                    "role" integer NOT NULL,
                                    CONSTRAINT "play_role_pk" PRIMARY KEY ("play","role")
);


CREATE TABLE "theatre".play_actor(
                                     "play" integer NOT NULL,
                                     "actor" integer NOT NULL,
                                     CONSTRAINT "play_actor_pk" PRIMARY KEY ("play","actor")
);


CREATE TABLE "theatre".genre(
                                "id" serial,
                                "name" TEXT NOT NULL UNIQUE,
                                CONSTRAINT "genre_pk" PRIMARY KEY ("id")
);


CREATE TABLE "theatre".audience(
                                   "id" serial,
                                   "name" TEXT NOT NULL UNIQUE,
                                   CONSTRAINT "audience_pk" PRIMARY KEY ("id")
);


CREATE TABLE "theatre".contest(
                                  "id" serial,
                                  "name" TEXT NOT NULL UNIQUE,
                                  "date" DATE NOT NULL,
                                  CONSTRAINT "contest_pk" PRIMARY KEY ("id")
);


CREATE TABLE "theatre".actor_contest(
                                        "actor" integer NOT NULL,
                                        "contest" integer NOT NULL,
                                        "winner" bool NOT NULL DEFAULT 'false',
                                        CONSTRAINT "actor_contest_pk" PRIMARY KEY ("actor","contest")
);


CREATE TABLE "theatre".full_name(
                                    "id" serial,
                                    "name" TEXT NOT NULL,
                                    "surname" TEXT NOT NULL,
                                    "patronymic" TEXT,
                                    CONSTRAINT "full_name_pk" PRIMARY KEY ("id")
);


CREATE TABLE "theatre".author(
                                 "id" serial,
                                 "full_name" integer NOT NULL,
                                 "century" integer NOT NULL,
                                 "country" TEXT NOT NULL,
                                 CONSTRAINT "author_pk" PRIMARY KEY ("id")
);


CREATE TABLE "theatre".repertoire(
                                     "id" serial,
                                     "play" integer NOT NULL,
                                     "date" DATE NOT NULL,
                                     "time" TIME NOT NULL,
                                     CONSTRAINT "repertoire_pk" PRIMARY KEY ("id")
);


CREATE TABLE "theatre".tour(
                               "id" serial,
                               "start" DATE NOT NULL,
                               "end" DATE NOT NULL,
                               "play" integer NOT NULL,
                               CONSTRAINT "tour_pk" PRIMARY KEY ("id")
);


CREATE TABLE "theatre".ticket(
                                 "id" serial,
                                 "cost" money NOT NULL,
                                 "place" integer NOT NULL,
                                 "row" integer NOT NULL,
                                 "play" integer NOT NULL,
                                 "buy_date" DATE NOT NULL,
                                 CONSTRAINT "ticket_pk" PRIMARY KEY ("id")
);


CREATE TABLE "theatre".play_musician(
                                        "play" integer NOT NULL,
                                        "musician" integer NOT NULL,
                                        CONSTRAINT "play_musician_pk" PRIMARY KEY ("play","musician")
);


CREATE TABLE "theatre".attribute(
                                  "id" serial,
                                  "attribute" TEXT NOT NULL UNIQUE,
                                  CONSTRAINT "attribute_pk" PRIMARY KEY ("id")
);


CREATE TABLE "theatre".attribute_value (
                                        "id" serial NOT NULL,
                                        "value" TEXT NOT NULL,
                                        "attribute" integer NOT NULL,
                                        CONSTRAINT "attribute_value_pk" PRIMARY KEY ("id")
);



CREATE TABLE "theatre".employee_attribute (
                                           "employee" integer NOT NULL,
                                           "attribute" integer NOT NULL,
                                           CONSTRAINT "employee_attribute_pk" PRIMARY KEY ("employee","attribute")
);


CREATE TABLE "theatre".role_attribute (
                                       "role" integer NOT NULL,
                                       "attribute" integer NOT NULL,
                                       CONSTRAINT "role_attribute_pk" PRIMARY KEY ("role","attribute")
);



CREATE TABLE "theatre".category (
                                   "id" serial NOT NULL,
                                   "name" TEXT NOT NULL,
                                   CONSTRAINT "category_pk" PRIMARY KEY ("id")
);


ALTER TABLE "theatre".employee ADD CONSTRAINT "employee_fk0" FOREIGN KEY ("full_name") REFERENCES "theatre".full_name("id") ON DELETE CASCADE ;
ALTER TABLE "theatre".employee ADD CONSTRAINT "employee_fk1" FOREIGN KEY ("category") REFERENCES "theatre".category("id");

ALTER TABLE "theatre".actor ADD CONSTRAINT "actor_fk0" FOREIGN KEY ("employee") REFERENCES "theatre".employee("id") ON DELETE CASCADE;


ALTER TABLE "theatre".musician ADD CONSTRAINT "musician_fk0" FOREIGN KEY ("employee") REFERENCES "theatre".employee("id") ON DELETE CASCADE;

ALTER TABLE "theatre".producer ADD CONSTRAINT "producer_fk0" FOREIGN KEY ("employee") REFERENCES "theatre".employee("id") ON DELETE CASCADE;

ALTER TABLE "theatre".servant ADD CONSTRAINT "servant_fk0" FOREIGN KEY ("employee") REFERENCES "theatre".employee("id") ON DELETE CASCADE;

ALTER TABLE "theatre".role ADD CONSTRAINT "role_fk0" FOREIGN KEY ("actor") REFERENCES "theatre".actor("id") ON DELETE CASCADE;
ALTER TABLE "theatre".role ADD CONSTRAINT "role_fk1" FOREIGN KEY ("backup") REFERENCES "theatre".actor("id") ON DELETE CASCADE;

ALTER TABLE "theatre".play ADD CONSTRAINT "play_fk0" FOREIGN KEY ("genre") REFERENCES "theatre".genre("id") ON DELETE CASCADE;
ALTER TABLE "theatre".play ADD CONSTRAINT "play_fk1" FOREIGN KEY ("audience") REFERENCES "theatre".audience("id") ON DELETE CASCADE;
ALTER TABLE "theatre".play ADD CONSTRAINT "play_fk2" FOREIGN KEY ("author") REFERENCES "theatre".author("id") ON DELETE CASCADE;
ALTER TABLE "theatre".play ADD CONSTRAINT "play_fk3" FOREIGN KEY ("director_producer") REFERENCES "theatre".producer("id") ON DELETE CASCADE;
ALTER TABLE "theatre".play ADD CONSTRAINT "play_fk4" FOREIGN KEY ("art_producer") REFERENCES "theatre".producer("id") ON DELETE CASCADE;
ALTER TABLE "theatre".play ADD CONSTRAINT "play_fk5" FOREIGN KEY ("conductor_producer") REFERENCES "theatre".producer("id") ON DELETE CASCADE;

ALTER TABLE "theatre".play_role ADD CONSTRAINT "play_role_fk0" FOREIGN KEY ("play") REFERENCES "theatre".play("id") ON DELETE CASCADE;
ALTER TABLE "theatre".play_role ADD CONSTRAINT "play_role_fk1" FOREIGN KEY ("role") REFERENCES "theatre".role("id") ON DELETE CASCADE;

ALTER TABLE "theatre".actor_contest ADD CONSTRAINT "actor_contest_fk0" FOREIGN KEY ("actor") REFERENCES "theatre".actor("id") ON DELETE CASCADE;
ALTER TABLE "theatre".actor_contest ADD CONSTRAINT "actor_contest_fk1" FOREIGN KEY ("contest") REFERENCES "theatre".contest("id") ON DELETE CASCADE;

ALTER TABLE "theatre".author ADD CONSTRAINT "author_fk0" FOREIGN KEY ("full_name") REFERENCES "theatre".full_name("id") ON DELETE CASCADE;

ALTER TABLE "theatre".repertoire ADD CONSTRAINT "repertoire_fk0" FOREIGN KEY ("play") REFERENCES "theatre".play("id") ON DELETE CASCADE;

ALTER TABLE "theatre".tour ADD CONSTRAINT "tour_fk0" FOREIGN KEY ("play") REFERENCES "theatre".play("id") ON DELETE CASCADE;

ALTER TABLE "theatre".ticket ADD CONSTRAINT "ticket_fk0" FOREIGN KEY ("play") REFERENCES "theatre".play("id") ON DELETE CASCADE;

ALTER TABLE "theatre".play_musician ADD CONSTRAINT "play_musician_fk0" FOREIGN KEY ("play") REFERENCES "theatre".play("id") ON DELETE CASCADE;
ALTER TABLE "theatre".play_musician ADD CONSTRAINT "play_musician_fk1" FOREIGN KEY ("musician") REFERENCES "theatre".musician("id") ON DELETE CASCADE;


ALTER TABLE "theatre".attribute_value ADD CONSTRAINT "attribute_value_fk0" FOREIGN KEY ("attribute") REFERENCES "theatre".attribute("id");

ALTER TABLE "theatre".employee_attribute ADD CONSTRAINT "employee_attribute_fk0" FOREIGN KEY ("employee") REFERENCES "theatre".employee("id");
ALTER TABLE "theatre".employee_attribute ADD CONSTRAINT "employee_attribute_fk1" FOREIGN KEY ("attribute") REFERENCES "theatre".attribute("id");

ALTER TABLE "theatre".role_attribute ADD CONSTRAINT "role_attribute_fk0" FOREIGN KEY ("role") REFERENCES "theatre".role("id");
ALTER TABLE "theatre".role_attribute ADD CONSTRAINT "role_attribute_fk1" FOREIGN KEY ("attribute") REFERENCES "theatre".attribute("id");



























