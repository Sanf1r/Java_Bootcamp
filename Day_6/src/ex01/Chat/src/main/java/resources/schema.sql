DROP SCHEMA IF EXISTS Chat CASCADE;

CREATE SCHEMA Chat;

CREATE TABLE IF NOT EXISTS Chat.Users (
    ID serial PRIMARY KEY,
	Login text,
	Password text
);

CREATE TABLE IF NOT EXISTS Chat.Chatroom (
    ID serial PRIMARY KEY,
	Name text,
	Owner integer,
    CONSTRAINT fk_owner_Chatroom FOREIGN KEY (Owner) REFERENCES Chat.Users(ID)
);

CREATE TABLE IF NOT EXISTS Chat.MessageObj (
    ID serial PRIMARY KEY,
	Author integer,
	Chatroom integer,
    String text,
    LocalDateTime timestamp default CURRENT_TIMESTAMP,
    CONSTRAINT fk_Author_Message FOREIGN KEY (Author) REFERENCES Chat.Users(ID),
    CONSTRAINT fk_Chatroom_Message FOREIGN KEY (Chatroom) REFERENCES Chat.Chatroom(ID)
);