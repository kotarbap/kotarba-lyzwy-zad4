package pl.edu.wszib.kotarba.ice.skates.database;

import pl.edu.wszib.kotarba.ice.skates.model.User;

import java.util.Optional;

public interface IUserDAO {
    Optional<User> getUserByLogin(String login);
    void addUser(User user);
    Optional<User> getUserById(int id);
}
