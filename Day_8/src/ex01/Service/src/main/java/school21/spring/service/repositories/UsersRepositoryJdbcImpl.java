package school21.spring.service.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import java.sql.*;

import school21.spring.service.models.User;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private DataSource ds;

    public UsersRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<User> result = Optional.empty();
        String query = "SELECT * FROM service.users WHERE email = '" + email + "'";
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            ResultSet rs = sm.executeQuery(query);
            if (rs.next()) {
                Long sqlId = rs.getLong(1);
                String sqlEmail = rs.getString(2);

                result = Optional.of(new User(sqlId, sqlEmail));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public void delete(Long id) {
        String query = "DELETE FROM service.users WHERE identifier = " + id;
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            sm.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public List<User> findAll() {
        ArrayList<User> result = new ArrayList<User>();
        String query = "SELECT * FROM service.users";
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            ResultSet rs = sm.executeQuery(query);
            while (rs.next()) {
                Long sqlId = rs.getLong(1);
                String sqlEmail = rs.getString(2);

                result.add(new User(sqlId, sqlEmail));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public Optional<User> findById(Long id) {
        Optional<User> result = Optional.empty();
        String query = "SELECT * FROM service.users WHERE identifier = " + id;
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            ResultSet rs = sm.executeQuery(query);
            if (rs.next()) {
                Long sqlId = rs.getLong(1);
                String sqlEmail = rs.getString(2);

                result = Optional.of(new User(sqlId, sqlEmail));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public void save(User entity) {
        Long id = entity.getIdentifier();
        String newEmail = entity.getEmail();
        String query = "INSERT INTO service.users VALUES (" + id +
                ", '" + newEmail + "')";
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            sm.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        Long id = entity.getIdentifier();
        String newEmail = entity.getEmail();
        String query = "UPDATE service.users SET email = '" + newEmail +
                "' WHERE identifier = " + id;
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            sm.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

}
