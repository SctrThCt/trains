DELETE FROM waybill;
DELETE FROM track;
DELETE FROM station;
DELETE FROM wagon_passport;
DELETE FROM cargo;
DELETE FROM users;
DELETE FROM user_roles;
ALTER SEQUENCE cargo_id_seq RESTART WITH 1;
ALTER SEQUENCE station_id_seq RESTART WITH 1;
ALTER SEQUENCE track_id_seq RESTART WITH 1;
ALTER SEQUENCE wagon_passport_id_seq RESTART WITH 1;
ALTER SEQUENCE waybill_id_seq RESTART WITH 1;
ALTER SEQUENCE users_id_seq RESTART WITH 1;

INSERT INTO users (name,password)
VALUES ('test', '{noop}test'),
       ('user', '{noop}user');

INSERT INTO user_roles (user_id, role)
VALUES (1,'ADMIN');

INSERT INTO cargo(name,code)
VALUES ('ПШЕНИЦА', '011005'),
       ('ЛЕСОМАТЕРИАЛЫ КРЕПЕЖНЫЕ', '082000'),
       ('АВТОМОБИЛИ И ИХ ЧАСТИ', '380008');

INSERT INTO station(name)
VALUES ('Москва'),
       ('Екатеринбург');

INSERT INTO wagon_passport(number,type,wagon_weight,carrying_weight)
VALUES (1, 'COVERED', 23000, 68000),
       (2, 'REFRIGERATOR', 48000, 36000),
       (3, 'PLATFORM',21400, 71000),
       (4, 'PLATFORM',21400, 71000);
INSERT INTO track(station_id,track_number)
VALUES (1,0),
       (1,1),
       (1,2),
       (1,3),
       (2,0),
       (2,1);
INSERT INTO waybill(wagon_passport_id,track_id,cargo_id,order_number,cargo_weight,full_wagon_weight)
VALUES (1,1,1,1,50000,23000+50000),
       (3,1,2,2,10000,21400+10000);

