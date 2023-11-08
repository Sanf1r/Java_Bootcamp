DROP SCHEMA IF EXISTS service CASCADE;

CREATE SCHEMA service;

CREATE TABLE IF NOT EXISTS service.users (
    identifier integer PRIMARY KEY,
	email varchar(100)
);

INSERT INTO service.users (identifier, email)
VALUES (1, 'power@mail.ru'),
	   (2, 'pizza@gmail.com'),
	   (3, 'arm777@vk.ru'),
	   (4, 'zapzap@yandex.ru'),
	   (5, 'boom@x.com');
	   
select * from service.users;