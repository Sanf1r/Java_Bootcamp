package school21.spring.service.repositories;

import java.rmi.server.ObjID;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import school21.spring.service.models.User;

@Component("JdbcTemplate")
@Primary
public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private JdbcTemplate jdbctemplate;

    @Autowired
    public UsersRepositoryJdbcTemplateImpl(DataSource ds) {
        jdbctemplate = new JdbcTemplate(ds);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional
                .ofNullable(jdbctemplate.query("SELECT * FROM service.users WHERE email=?",
                        new Object[] { email },
                        new BeanPropertyRowMapper<>(User.class)).stream().findAny()
                        .orElse(null));
    }

    @Override
    public void delete(Long id) {
        jdbctemplate.update("DELETE FROM service.users WHERE identifier=?", id);
    }

    @Override
    public List<User> findAll() {
        return jdbctemplate.query("SELECT * FROM service.users",
                new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional
                .ofNullable(jdbctemplate.query("SELECT * FROM service.users WHERE identifier=?",
                        new Object[] { id },
                        new BeanPropertyRowMapper<>(User.class)).stream().findAny()
                        .orElse(null));
    }

    @Override
    public void save(User entity) {
        jdbctemplate.update("INSERT INTO service.users VALUES(?, ?, ?)",
                entity.getIdentifier(), entity.getEmail(), entity.getPassword());
    }

    @Override
    public void update(User entity) {
        jdbctemplate.update("UPDATE service.users SET email=?, password=? WHERE identifier=?",
                entity.getEmail(), entity.getPassword(), entity.getIdentifier());
    }

    @Override
    public Long findMax() {
        Long result = jdbctemplate.queryForObject("SELECT MAX(identifier) FROM service.users", Long.class);
        return (result != null) ? result : 0L;
    }

}
