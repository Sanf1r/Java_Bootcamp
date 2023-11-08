package edu.school21.repositories;

import edu.school21.models.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import javax.sql.*;
import javax.xml.crypto.Data;

public class ProductsRepositoryJdbcImplTest {
    private ProductsRepositoryJdbcImpl rep;
    private DataSource db;

    final List<Product> EXPECTED_FIND_ALL_PRODUCTS = new ArrayList<Product>(Arrays.asList(
            new Product(1L, "Браслет 21 Sber", 20.0),
            new Product(2L, "Фигурки трайба", 121.0),
            new Product(3L, "Футболка \"BORN TO CODE\"", 300.0),
            new Product(4L, "Футболка \"School 21\"", 300.0),
            new Product(5L, "Носки \"FAILED SUCCESSFULLY\"", 80.0)));

    final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(1L, "Браслет 21 Sber", 20.0);

    final Product EXPECTED_UPDATED_PRODUCT = new Product(1L, "Браслет 21 Sber", 42.0);

    final Product EXPECTED_SAVED_PRODUCT = new Product(6L, "100% за DevOps Экзамен", 500.0);

    final List<Product> EXPECTED_FIND_ALL_AFTER_DELETE_PRODUCT = new ArrayList<Product>(Arrays.asList(
            new Product(1L, "Браслет 21 Sber", 20.0),
            new Product(3L, "Футболка \"BORN TO CODE\"", 300.0),
            new Product(4L, "Футболка \"School 21\"", 300.0),
            new Product(5L, "Носки \"FAILED SUCCESSFULLY\"", 80.0)));

    @BeforeEach
    void init() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        db = builder.setType(EmbeddedDatabaseType.HSQL)
                .addScript("schema.sql")
                .addScript("data.sql")
                .build();
        rep = new ProductsRepositoryJdbcImpl(db);
    }

    @Test
    void updateTest() throws SQLException {
        rep.update(EXPECTED_UPDATED_PRODUCT);
        assertEquals(EXPECTED_UPDATED_PRODUCT, rep.findById(1L).get());
    }

    @Test
    void updateTestThrow() throws SQLException {

        assertThrows(SQLException.class, () -> rep.update(new Product(1L, "Браслет 21 Sber", null)));
    }

    @Test
    void findAllTest() throws SQLException {
        assertEquals(EXPECTED_FIND_ALL_PRODUCTS, rep.findAll());
    }

    @Test
    void findByIdTest() throws SQLException {
        assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, rep.findById(1L).get());
    }

    @Test
    void saveTest() throws SQLException {
        rep.save(EXPECTED_SAVED_PRODUCT);
        assertEquals(EXPECTED_SAVED_PRODUCT, rep.findById(6L).get());
    }

    @Test
    void saveTestThrow() throws SQLException {
        assertThrows(SQLException.class, () -> rep.save(new Product(7L, "NO!", null)));
    }

    @Test
    void deleteTest() throws SQLException {
        rep.delete(2L);
        assertEquals(EXPECTED_FIND_ALL_AFTER_DELETE_PRODUCT, rep.findAll());
    }

}
