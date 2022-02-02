package pl.edu.wszib.kotarba.ice.skates.database;

import pl.edu.wszib.kotarba.ice.skates.model.Order;

import java.util.List;

public interface IOrderDAO {
    void addOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
}
