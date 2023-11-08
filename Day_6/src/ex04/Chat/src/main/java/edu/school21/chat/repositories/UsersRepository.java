package edu.school21.chat.repositories;

import java.util.List;

import edu.school21.chat.models.*;

public interface UsersRepository {
    public List<User> findAll(int page, int size);
}
