package school21.spring.service.application;

import org.springframework.context.*;
import org.springframework.context.annotation.*;

import school21.spring.service.config.ApplicationConfig;
import school21.spring.service.models.*;

import school21.spring.service.repositories.UsersRepository;
import school21.spring.service.services.UsersService;

public class Main {
        public static void main(String[] args) {
                AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
                context.register(ApplicationConfig.class);
                context.refresh();
                UsersRepository usersRepositoryJdbc = context.getBean("Jdbc",
                                UsersRepository.class);

                UsersRepository usersRepositoryJdbcTemplate = context.getBean("JdbcTemplate",
                                UsersRepository.class);

                UsersService usersService = context.getBean("usersService",
                                UsersService.class);

                System.out.println("-------------------FIND ALL-------------------------");
                System.out.println(usersRepositoryJdbc.findAll());
                System.out.println("--------------------------------------------");
                System.out.println(usersRepositoryJdbcTemplate.findAll());

                System.out.println("-------------------FIND BY EMAIL-------------------------");

                if (usersRepositoryJdbc.findByEmail("zapzap@yandex.ru").isPresent()) {
                        System.out.println(usersRepositoryJdbc.findByEmail("zapzap@yandex.ru").get());
                }
                System.out.println("--------------------------------------------");
                if (usersRepositoryJdbcTemplate.findByEmail("zapzap@yandex.ru").isPresent()) {
                        System.out.println(usersRepositoryJdbcTemplate.findByEmail("zapzap@yandex.ru").get());
                }
                System.out.println("--------------------FIND BY ID------------------------");
                if (usersRepositoryJdbc.findById(3L).isPresent())
                        System.out.println(usersRepositoryJdbc.findById(3L).get());
                System.out.println("--------------------------------------------");
                if (usersRepositoryJdbcTemplate.findById(3L).isPresent())
                        System.out.println(usersRepositoryJdbcTemplate.findById(3L).get());

                System.out.println("-------------------SAVE-------------------------");

                usersRepositoryJdbc.save(new User(6L, "hehe@hoho1.ru", "pass"));
                usersRepositoryJdbcTemplate.save(new User(7L, "hehe@hoho2.ru", "pass"));

                System.out.println(usersRepositoryJdbc.findAll());
                System.out.println("--------------------------------------------");
                System.out.println(usersRepositoryJdbcTemplate.findAll());

                System.out.println("---------------------UPDATE-----------------------");

                usersRepositoryJdbc.update(new User(6L, "hehe@hoJDBCo.ru", "pass"));
                usersRepositoryJdbcTemplate.update(new User(7L, "hehe@hoSPRINGo.ru", "pass"));

                System.out.println(usersRepositoryJdbc.findAll());
                System.out.println("--------------------------------------------");
                System.out.println(usersRepositoryJdbcTemplate.findAll());

                System.out.println("---------------------DELETE-----------------------");

                usersRepositoryJdbc.delete(1L);
                usersRepositoryJdbc.delete(2L);

                usersRepositoryJdbcTemplate.delete(3L);
                usersRepositoryJdbcTemplate.delete(4L);

                System.out.println(usersRepositoryJdbc.findAll());
                System.out.println("--------------------------------------------");

                System.out.println("---------------------user service-----------------------");

                usersService.signUp("student@21-school.ru");

                System.out.println(usersRepositoryJdbc.findAll());
                System.out.println("--------------------------------------------");

                System.out.println(usersRepositoryJdbcTemplate.findAll());
                context.close();
        }
}
