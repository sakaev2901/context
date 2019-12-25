package repositories;

import config.ConnectionConfig;
import models.Product;
import ru.itis.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository, Component {

    private final static ConnectionConfig CONNECTION_CONFIG = ConnectionConfig.getInstance();
    private final static String FIND_ALL = "SELECT * FROM web_product";
    private final static String SAVE = "INSERT INTO web_product(name, price) values (? , ?)";

    @Override
    public void save(Product model) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(SAVE);
            statement.setString(1, model.getName());
            statement.setInt(2, model.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(statement);
            CONNECTION_CONFIG.close(connection);
        }
    }

    @Override
    public Optional<Product> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        List<Product> productList = new LinkedList<>();
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(FIND_ALL);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                productList.add(new Product().builder()
                                    .id(set.getLong("id"))
                                    .name(set.getString("name"))
                                    .price(set.getInt("price")).build());
            }
        return productList;
        } catch (SQLException e) {
             throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(statement);
            CONNECTION_CONFIG.close(connection);
        }
    }

    @Override
    public String getComponentName() {
        return "productRepositoryImpl";
    }
}
