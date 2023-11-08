DROP SCHEMA IF EXISTS Chat CASCADE;

CREATE SCHEMA Chat;

CREATE TABLE IF NOT EXISTS Chat.Users (
    ID serial PRIMARY KEY NOT NULL,
	Login text NOT NULL,
	Password text NOT NULL
);

CREATE TABLE IF NOT EXISTS Chat.Chatroom (
    ID serial PRIMARY KEY NOT NULL,
	Name text NOT NULL,
	Owner integer NOT NULL,
    CONSTRAINT fk_owner_Chatroom FOREIGN KEY (Owner) REFERENCES Chat.Users(ID)
);

CREATE TABLE IF NOT EXISTS Chat.MessageObj (
    ID serial PRIMARY KEY NOT NULL,
	Author integer NOT NULL,
	Chatroom integer NOT NULL,
    String text NOT NULL,
    LocalDateTime timestamp default CURRENT_TIMESTAMP,
    CONSTRAINT fk_Author_Message FOREIGN KEY (Author) REFERENCES Chat.Users(ID),
    CONSTRAINT fk_Chatroom_Message FOREIGN KEY (Chatroom) REFERENCES Chat.Chatroom(ID)
);