package pl.edu.wszib.kotarba.ice.skates.database;

import pl.edu.wszib.kotarba.ice.skates.model.OrderPosition;

import java.util.List;

public interface IOrderPositionDAO {
    void addOrderPosition(OrderPosition orderPosition, int orderId);
    List<OrderPosition> getOrderPositionsByOrderId(int orderId);
}
