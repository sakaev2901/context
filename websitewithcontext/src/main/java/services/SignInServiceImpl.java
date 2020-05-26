package services;


import models.User;
import repositories.UserRepository;
import ru.itis.Component;

import java.util.ArrayList;
import java.util.Optional;

public class SignInServiceImpl implements SignInService, Component {

    private UserRepository userRepository;

    @Override
    public User signIn(String login, String password) {
        Optional<User> userOptional = userRepository.findByLoginAndPassword(login, password);
        User user = userOptional.orElseThrow(() -> new IllegalStateException("user not found"));
        return user;
    }

    @Override
    public String getComponentName() {
        return "signInServiceImpl";
    }
}
