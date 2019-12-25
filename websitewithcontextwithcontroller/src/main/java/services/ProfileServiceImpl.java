package services;

import models.User;
import repositories.UserRepository;
import ru.itis.Component;

import java.util.Optional;

public class ProfileServiceImpl implements ProfileService, Component {

    private UserRepository userRepository;
    @Override
    public String getComponentName() {
        return "profileServiceImpl";
    }

    @Override
    public User getUser(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        User user = optionalUser.orElseThrow(() -> new IllegalStateException("no such user"));
        return user;
    }
}
