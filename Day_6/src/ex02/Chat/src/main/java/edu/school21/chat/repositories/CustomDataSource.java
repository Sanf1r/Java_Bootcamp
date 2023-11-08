package edu.school21.chat.repositories;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.*;

public class CustomDataSource {
    private HikariDataSource ds;

    public CustomDataSource() {
        ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        ds.setUsername("morfinov");
        ds.setPassword("12345");
    }

    public HikariDataSource getDs() {
        return ds;
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

}
