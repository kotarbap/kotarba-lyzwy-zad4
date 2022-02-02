package pl.edu.wszib.kotarba.ice.skates.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.kotarba.ice.skates.database.IUserDAO;
import pl.edu.wszib.kotarba.ice.skates.model.Order;
import pl.edu.wszib.kotarba.ice.skates.model.User;
import pl.edu.wszib.kotarba.ice.skates.model.view.RegisterUser;
import pl.edu.wszib.kotarba.ice.skates.service.IAuthenticationService;
import pl.edu.wszib.kotarba.ice.skates.service.IOrderService;
import pl.edu.wszib.kotarba.ice.skates.session.SessionObject;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RestController
public class UserRestController {

    @Autowired
    IAuthenticationService authenticateService;
    @Autowired
    IOrderService orderService;
    @Autowired
    IUserDAO UserDAOImpl;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/endpoint4", method = RequestMethod.GET)
    public User showUser() {
            return sessionObject.getUser();
    }

    @RequestMapping(value = "/endpoint5", method = RequestMethod.POST)
    public User registerUser(@RequestBody RegisterUser user) {
        this.authenticateService.register(user);
        return user;
    }

    @RequestMapping(value = "/endpoint6", method = RequestMethod.GET)
    public List<Order> getOrdersForCurrentUser() {
        return this.orderService.getOrdersForCurrentUser();
    }

    @RequestMapping(value = "/endpoint7", method = RequestMethod.POST)
    public Optional<User> getUserByLogin(@RequestHeader("login") String login) {
        return this.UserDAOImpl.getUserByLogin(login);
    }
}
