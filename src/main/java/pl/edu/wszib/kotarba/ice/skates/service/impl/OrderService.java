package pl.edu.wszib.kotarba.ice.skates.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.kotarba.ice.skates.database.IOrderDAO;
import pl.edu.wszib.kotarba.ice.skates.database.ISkatesDAO;
import pl.edu.wszib.kotarba.ice.skates.model.Order;
import pl.edu.wszib.kotarba.ice.skates.model.OrderPosition;
import pl.edu.wszib.kotarba.ice.skates.model.Skates;
import pl.edu.wszib.kotarba.ice.skates.service.IOrderService;
import pl.edu.wszib.kotarba.ice.skates.session.SessionObject;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    ISkatesDAO skatesDAO;

    @Override
    public void confirmOrder() {
        Order order = new Order(this.sessionObject.getUser(), new HashSet<>(this.sessionObject.getCart().getOrderPositions()));
        this.orderDAO.addOrder(order);
        for (OrderPosition orderPosition : order.getOrderPositions()) {
            Optional<Skates> skatesBox = this.skatesDAO.getSkatesById(orderPosition.getSkates().getId());
            if(skatesBox.isPresent()) {
                skatesBox.get().setQuantity(skatesBox.get().getQuantity() - orderPosition.getQuantity());
                this.skatesDAO.updateSkates(skatesBox.get());
            }
        }
        this.sessionObject.getCart().clearOrderPositions();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionObject.getUser().getId());
    }
}
