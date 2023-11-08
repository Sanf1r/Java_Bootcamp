package edu.school21.chat.app;

import edu.school21.chat.models.*;
import edu.school21.chat.repositories.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {
        final CustomDataSource dataSource = new CustomDataSource();
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        System.out.println("---WRONG USER AND CHAT ID---");
        try {
            User creator = new User(7L, "user", "user", new ArrayList<Chatroom>(), new ArrayList<Chatroom>());
            User author = creator;
            Chatroom room = new Chatroom(8L, "room", creator, new ArrayList<Message>());
            Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
            messagesRepository.save(message);
            System.out.println(message.getId());
        } catch (NotSavedSubEntityException e) {
            System.out.println(e);
        }

        System.out.println("---WRONG CHAT ID---");
        try {
            User creator = new User(1L, "Vergil", "power", new ArrayList<Chatroom>(), new ArrayList<Chatroom>());
            User author = creator;
            Chatroom room = new Chatroom(8L, "Motivational Training", creator, new ArrayList<Message>());
            Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
            messagesRepository.save(message);
            System.out.println(message.getId());
        } catch (NotSavedSubEntityException e) {
            System.out.println(e);
        }

        System.out.println("---WRONG USER ID---");
        try {
            User creator = new User(null, "Vergil", "power", new ArrayList<Chatroom>(), new ArrayList<Chatroom>());
            User author = creator;
            Chatroom room = new Chatroom(5L, "Motivational Training", creator, new ArrayList<Message>());
            Message message = new Message(null, author, room, "Hello!", LocalDateTime.now());
            messagesRepository.save(message);
            System.out.println(message.getId());
        } catch (NotSavedSubEntityException e) {
            System.out.println(e);
        }

        System.out.println("---ALL IS CLEAR---");
        try {
            User creator = new User(1L, "Vergil", "power", new ArrayList<Chatroom>(), new ArrayList<Chatroom>());
            User author = creator;
            Chatroom room = new Chatroom(5L, "Motivational Training", creator, new ArrayList<Message>());
            Message message = new Message(null, author, room, "YEEESSS!", LocalDateTime.now());
            messagesRepository.save(message);
            System.out.println(message.getId());
        } catch (NotSavedSubEntityException e) {
            System.out.println(e);
        }

    }

}