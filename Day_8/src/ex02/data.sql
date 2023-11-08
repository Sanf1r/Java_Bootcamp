DROP SCHEMA IF EXISTS service CASCADE;

CREATE SCHEMA service;

CREATE TABLE IF NOT EXISTS service.users (
    identifier integer PRIMARY KEY,
	email varchar(100),
	password varchar(100)
);

INSERT INTO service.users (identifier, email, password)
VALUES (1, 'power@mail.ru','test@test'),
	   (2, 'pizza@gmail.com','test@test'),
	   (3, 'arm777@vk.ru','test@test'),
	   (4, 'zapzap@yandex.ru','test@test'),
	   (5, 'boom@x.com','test@test');
	   
select * from service.users;