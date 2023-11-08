package school21.spring.service.repositories;

import java.rmi.server.ObjID;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import org.springframework.jdbc.core.*;

import school21.spring.service.models.User;

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {
    private DataSource ds;
    private JdbcTemplate jdbctemplate;

    public UsersRepositoryJdbcTemplateImpl(DataSource ds) {
        this.ds = ds;
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
        jdbctemplate.update("INSERT INTO service.users VALUES(?, ?)",
                entity.getIdentifier(), entity.getEmail());
    }

    @Override
    public void update(User entity) {
        jdbctemplate.update("UPDATE service.users SET identifier=?, email=? WHERE identifier=?",
                entity.getIdentifier(), entity.getEmail(), entity.getIdentifier());
    }

}
