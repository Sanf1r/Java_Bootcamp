package school21.spring.service.services;

import school21.spring.service.config.*;
import javax.sql.DataSource;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

public class UsersServiceImplTest {
        private static DataSource ds;
        private static UsersService usersServiceJdbcTemplate;

        @BeforeAll
        static void init() {
                AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
                context.register(TestApplicationConfig.class);
                context.refresh();
                ds = context.getBean("dataSource", DataSource.class);

                usersServiceJdbcTemplate = context.getBean("jdbcTemplateTest",
                                UsersService.class);
        }

        @BeforeEach
        void clean() {
                try (Connection in = ds.getConnection();
                                Statement sm = in.createStatement();) {
                        sm.executeUpdate("DROP SCHEMA IF EXISTS service CASCADE");
                        sm.executeUpdate("CREATE SCHEMA service");
                        sm.executeUpdate("CREATE TABLE IF NOT EXISTS service.users " +
                                        "(identifier integer PRIMARY KEY, email varchar(100)," +
                                        "password varchar(100))");
                } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                }
        }

        @ParameterizedTest
        @ValueSource(strings = { "student1@21-school.ru", "student2@21-school.ru",
                        "student3@21-school.ru" })
        public void issignUpForTemplate(String email) {
                assertNotNull(usersServiceJdbcTemplate.signUp(email));
        }

}
