package services;

import models.User;
import repositories.UserRepository;
import ru.itis.Component;

public class SignUpServiceImpl implements SignUpService, Component {

    private UserRepository userRepository;

    @Override
    public void signUp(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        userRepository.save(user);
    }

    @Override
    public String getComponentName() {
        return "signUpServiceImpl";
    }
}
