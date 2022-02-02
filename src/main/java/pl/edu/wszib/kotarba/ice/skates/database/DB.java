package pl.edu.wszib.kotarba.ice.skates.database;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.edu.wszib.kotarba.ice.skates.model.Order;
import pl.edu.wszib.kotarba.ice.skates.model.Skates;
import pl.edu.wszib.kotarba.ice.skates.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DB {
    private List<Skates> skates = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public DB() {
        this.skates.add(new Skates(1, "Tempish", "Montreal", 40, 11, 5));
        this.skates.add(new Skates(2, "Tempish", "Montreal", 41, 12, 5));
        this.skates.add(new Skates(3, "Bauer", "NS", 42, 13, 5));
        this.skates.add(new Skates(4, "Bauer", "Nexus", 40, 14, 5));
        this.skates.add(new Skates(5, "K2", "Raider", 41, 15, 5));
        this.skates.add(new Skates(6, "K2", "Marlee", 42, 10, 5));

        this.users.add(new User(1, "Piotr", "Kotarba", "piotr", DigestUtils.md5Hex("piotr")));
        this.users.add(new User(2, "Ania", "Kowalska", "ania", DigestUtils.md5Hex("ania")));
        this.users.add(new User(3, "Mateo","Admin", "mateo", DigestUtils.md5Hex("mateo")));
    }

    public List<Skates> getSkates() {
        return skates;
    }

    public Optional<User> getUserByLogin(String login) {
        for(User user : this.users) {
            if(user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public Optional<Skates> getSkatesById(int skatesId) {
        for(Skates skates : this.skates) {
            if(skates.getId() == skatesId) {
                return Optional.of(skates);
            }
        }

        return Optional.empty();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrdersByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        for(Order order : this.orders) {
            if(order.getUser().getId() == userId) {
                result.add(order);
            }
        }

        return result;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

}
