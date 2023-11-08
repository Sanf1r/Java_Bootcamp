package edu.school21.repositories;

import edu.school21.models.*;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public interface ProductsRepository {
    public List<Product> findAll();

    public Optional<Product> findById(Long id);

    public void update(Product product) throws SQLException;

    public void save(Product product) throws SQLException;

    public void delete(Long id);

}
