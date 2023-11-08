package edu.school21.repositories;

import edu.school21.models.*;

public interface UsersRepository {
    User findByLogin(String login);

    void update(User user);
}
