INSERT INTO Chat.Users (Login, Password)
VALUES ('Virgil', 'power'),
	   ('Dante', 'pizza'),
	   ('Nero', 'arm777'),
	   ('Trish', 'zapzap'),
	   ('Lady', 'boom');

INSERT INTO Chat.Chatroom (Name, Owner)
VALUES ('Jackpot', 2),
       ('Motivational Training', 1),
       ('Did you see my arm?', 3),
       ('Need more ammo', 5),
       ('Pizza night', 2);

INSERT INTO Chat.MessageObj (Author, Chatroom, String, LocalDateTime)
VALUES (1, 2, 'I WANT MORE POWER!', '1999-01-08 04:05:06'),
       (3, 2, 'DAD, STOP(', DEFAULT),
       (2, 5, 'Hi all, pizza night?', DEFAULT),
       (4, 5, 'Im in!', DEFAULT),
       (4, 5, 'GO!', DEFAULT);
