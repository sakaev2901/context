package repositories;


import config.ConnectionConfig;
import models.Role;
import models.User;
import ru.itis.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository, Component {

    private final static ConnectionConfig CONNECTION_CONFIG = ConnectionConfig.getInstance();
    private final static String SAVE = "INSERT INTO web_user(login, password, role) VALUES (?, ?, ?)";
    private final static String FIND_BY_LOGIN_AND_PASSWORD = "SELECT * FROM web_user WHERE \"login\" = ? AND \"password\" = ? ";
    private static final String FIND_BY_ID = "SELECT * FROM web_user WHERE \"id\" = ?";

    @Override
    public void save(User model) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(SAVE);
            statement.setString(1, model.getLogin());
            statement.setString(2, model.getPassword());
            statement.setString(3, model.getRole().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(statement);
            CONNECTION_CONFIG.close(connection);
        }
    }

    @Override
    public Optional<User> findById(Long aLong) {
        Connection connection = null;
        PreparedStatement statement = null;
        User user = null;
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(FIND_BY_ID);
            statement.setLong(1, aLong);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                user = new User();
                user.setLogin(set.getString("login"));
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(statement);
            CONNECTION_CONFIG.close(connection);
        }


    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public String getComponentName() {
        return "userRepositoryImpl";
    }

    @Override
    public Optional<User> findByLoginAndPassword(String login, String password) {
        Connection connection = null;
        PreparedStatement statement = null;
        User user = null;
        try {
            connection = CONNECTION_CONFIG.getConnection();
            statement = connection.prepareStatement(FIND_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();
            if (set.next()) {
                user = new User();
                user.setId(set.getLong("id"));
                user.setLogin(set.getString("login"));
                user.setRole(Role.valueOf(set.getString("role")));
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        } finally {
            CONNECTION_CONFIG.close(statement);
            CONNECTION_CONFIG.close(connection);
        }
    }
}
