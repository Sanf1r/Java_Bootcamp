package school21.spring.service.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import school21.spring.service.repositories.*;
import school21.spring.service.models.*;

@Component("usersService")
public class UsersServiceImpl implements UsersService {
    private UsersRepository usersRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public String signUp(String email) {
        String password = UUID.randomUUID().toString();
        usersRepository.save(new User(usersRepository.findMax() + 1, email, password));
        return password;
    }

}
