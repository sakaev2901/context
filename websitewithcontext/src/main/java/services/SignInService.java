package services;

import models.User;

import java.util.Optional;

public interface SignInService {
    User signIn(String login, String password);
}
