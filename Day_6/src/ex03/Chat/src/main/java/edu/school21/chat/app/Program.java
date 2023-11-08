package edu.school21.chat.app;

import edu.school21.chat.models.*;
import edu.school21.chat.repositories.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Program {

    public static void main(String[] args) {
        final CustomDataSource dataSource = new CustomDataSource();
        final MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("BEFORE UPDATE");
        for (Long i = 1L; i <= 5; ++i) {
            Optional<Message> messageOptional = messagesRepository.findById(i);
            if (messageOptional.isPresent()) {
                LocalDateTime dateCheck = messageOptional.get().getDateTime();
                String toPrint = (dateCheck == null) ? "null" : dateCheck.format(formatter);
                System.out.println("Id - " + i + " " +
                        messageOptional.get().getText() + " " + toPrint);
            }
        }
        System.out.println("AFTER UPDATE");
        Optional<Message> messageOptional = messagesRepository.findById(1L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("Bye");
            message.setDateTime(null);
            messagesRepository.update(message);
        }
        messageOptional = messagesRepository.findById(2L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText(null);
            message.setDateTime(LocalDateTime.now());
            messagesRepository.update(message);
        }
        messageOptional = messagesRepository.findById(3L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("Im number three!!!");
            message.setDateTime(LocalDateTime.now());
            messagesRepository.update(message);
        }

        for (Long i = 1L; i <= 5; ++i) {
            messageOptional = messagesRepository.findById(i);
            if (messageOptional.isPresent()) {
                LocalDateTime dateCheck = messageOptional.get().getDateTime();
                String toPrint = (dateCheck == null) ? "null" : dateCheck.format(formatter);
                System.out.println("Id - " + i + " " +
                        messageOptional.get().getText() + " " + toPrint);
            }
        }
    }
}
