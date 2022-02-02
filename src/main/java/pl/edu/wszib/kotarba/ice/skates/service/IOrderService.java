package pl.edu.wszib.kotarba.ice.skates.service;

import pl.edu.wszib.kotarba.ice.skates.model.Order;

import java.util.List;

public interface IOrderService {
    void confirmOrder();
    List<Order> getOrdersForCurrentUser();
}
