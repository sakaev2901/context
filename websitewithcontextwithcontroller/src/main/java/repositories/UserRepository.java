package repositories;

import models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByLoginAndPassword(String login, String password);
}
