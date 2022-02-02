package pl.edu.wszib.kotarba.ice.skates.service;

import pl.edu.wszib.kotarba.ice.skates.model.view.RegisterUser;

public interface IAuthenticationService {
    void authenticate(String login, String password);
    void register(RegisterUser registerUser);
}
