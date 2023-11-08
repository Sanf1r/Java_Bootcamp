package edu.school21.repositories;

import edu.school21.models.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.*;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
    private DataSource ds;

    public ProductsRepositoryJdbcImpl(DataSource ds) {
        this.ds = ds;
    }

    public List<Product> findAll() {
        ArrayList<Product> result = new ArrayList<Product>();
        String query = "SELECT * FROM Products.Inventory";
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            ResultSet rs = sm.executeQuery(query);
            while (rs.next()) {
                Long sqlId = rs.getLong(1);
                String sqlName = rs.getString(2);
                Double sqlPrice = rs.getDouble(3);

                result.add(new Product(sqlId, sqlName, sqlPrice));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public Optional<Product> findById(Long id) {
        Optional<Product> result = Optional.empty();
        String query = "SELECT * FROM Products.Inventory WHERE id = " + id;
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            ResultSet rs = sm.executeQuery(query);
            if (rs.next()) {
                Long sqlId = rs.getLong(1);
                String sqlName = rs.getString(2);
                Double sqlPrice = rs.getDouble(3);

                result = Optional.of(new Product(sqlId, sqlName, sqlPrice));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public void update(Product product) throws SQLException {
        Long id = product.getId();
        String newName = product.getName();
        Double newPrice = product.getPrice();
        String query = "UPDATE Products.Inventory SET name = '" + newName +
                "', price = " + newPrice + " WHERE id = " + id;
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            sm.executeQuery(query);
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    public void save(Product product) throws SQLException {
        Long id = product.getId();
        String newName = product.getName();
        Double newPrice = product.getPrice();
        String query = "INSERT INTO Products.Inventory VALUES (" + id +
                ", '" + newName + "', " + newPrice + ")";
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            sm.executeQuery(query);
        } catch (SQLException ex) {
            throw new SQLException(ex.getMessage());
        }
    }

    public void delete(Long id) {
        String query = "DELETE FROM Products.Inventory WHERE id = " + id;
        try (Connection in = ds.getConnection();
                Statement sm = in.createStatement();) {
            sm.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
