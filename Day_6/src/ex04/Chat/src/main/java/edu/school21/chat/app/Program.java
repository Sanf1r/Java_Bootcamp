package edu.school21.chat.app;

import edu.school21.chat.models.*;
import edu.school21.chat.repositories.*;

import java.util.List;

public class Program {

    public static void main(String[] args) {
        final CustomDataSource dataSource = new CustomDataSource();
        final UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);

        System.out.println("THERE ERROR");
        List<User> result = usersRepository.findAll(-1, 1);
        for (User data : result) {
            System.out.println(data);
        }

        System.out.println("PAGE 0 SIZE 3");
        result = usersRepository.findAll(0, 3);
        for (User data : result) {
            System.out.println(data);
        }

        System.out.println("PAGE 4 SIZE 1");
        result = usersRepository.findAll(4, 1);
        for (User data : result) {
            System.out.println(data);
        }

    }
}
