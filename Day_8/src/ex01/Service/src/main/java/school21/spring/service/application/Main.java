package school21.spring.service.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import school21.spring.service.models.*;

import school21.spring.service.repositories.UsersRepository;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        UsersRepository usersRepository = context.getBean("usersRepositoryJdbc", UsersRepository.class);
        System.out.println("-------------------FIND ALL-------------------------");
        System.out.println(usersRepository.findAll());
        UsersRepository usersRepository2 = context.getBean("usersRepositoryJdbcTemplate",
                UsersRepository.class);
        System.out.println("--------------------------------------------");
        System.out.println(usersRepository2.findAll());

        System.out.println("-------------------FIND BY EMAIL-------------------------");

        System.out.println(usersRepository.findByEmail("zapzap@yandex.ru").get());
        System.out.println("--------------------------------------------");
        System.out.println(usersRepository2.findByEmail("zapzap@yandex.ru").get());

        System.out.println("--------------------FIND BY ID------------------------");

        System.out.println(usersRepository.findById(3L).get());
        System.out.println("--------------------------------------------");
        System.out.println(usersRepository2.findById(3L).get());

        System.out.println("-------------------SAVE-------------------------");

        usersRepository.save(new User(6L, "hehe@hoho1.ru"));
        usersRepository2.save(new User(7L, "hehe@hoho2.ru"));

        System.out.println(usersRepository.findAll());
        System.out.println("--------------------------------------------");
        System.out.println(usersRepository2.findAll());

        System.out.println("---------------------UPDATE-----------------------");

        usersRepository.update(new User(6L, "hehe@hoJDBCo.ru"));
        usersRepository2.update(new User(7L, "hehe@hoSPRINGo.ru"));

        System.out.println(usersRepository.findAll());
        System.out.println("--------------------------------------------");
        System.out.println(usersRepository2.findAll());

        System.out.println("---------------------DELETE-----------------------");

        usersRepository.delete(1L);
        usersRepository.delete(2L);

        usersRepository2.delete(3L);
        usersRepository2.delete(4L);

        System.out.println(usersRepository.findAll());
        System.out.println("--------------------------------------------");
        System.out.println(usersRepository2.findAll());

        ((ClassPathXmlApplicationContext) context).close();
    }
}
