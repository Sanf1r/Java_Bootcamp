package edu.school21.chat.repositories;

import edu.school21.chat.models.Message;

// import java.util.List;
import java.util.Optional;

public interface MessagesRepository {
    public Optional<Message> findById(Long id);

    // void delete(Message course);

    // void save(Message course);

    // void update(Message course);

    // List<Message> findAll();

}
