package edu.school21.services;

import edu.school21.repositories.UsersRepository;
import edu.school21.exceptions.AlreadyAuthenticatedException;
import edu.school21.models.*;
import javax.persistence.EntityNotFoundException;

public class UsersServiceImpl {
    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public boolean authenticate(String login, String password) {
        boolean code = false;
        try {
            User result = usersRepository.findByLogin(login);
            if (result.isAuth()) {
                throw new AlreadyAuthenticatedException("Already login");
            } else if (result.getPassword().equals(password)) {
                result.setAuth(true);
                usersRepository.update(result);
                code = true;
            }
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
        return code;
    }
}
