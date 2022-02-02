package pl.edu.wszib.kotarba.ice.skates.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.kotarba.ice.skates.database.ISkatesDAO;
import pl.edu.wszib.kotarba.ice.skates.model.OrderPosition;
import pl.edu.wszib.kotarba.ice.skates.model.Skates;
import pl.edu.wszib.kotarba.ice.skates.service.ICartService;
import pl.edu.wszib.kotarba.ice.skates.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    ISkatesDAO skatesDAO;

    @Resource
    SessionObject sessionObject;

    public void addSkatesToCart(int skatesId) {
        Optional<Skates> skatesBox = this.skatesDAO.getSkatesById(skatesId);

        if(skatesBox.isEmpty()) {
            return;
        }

        Skates skates = skatesBox.get();
        if(skates.getQuantity() <= 0) {
            return;
        }

        for(OrderPosition orderPosition : this.sessionObject
                .getCart().getOrderPositions()) {
            if(orderPosition.getSkates().getId() == skatesId) {
                orderPosition.incrementQuantity();
                return;
            }
        }

        OrderPosition orderPosition = new OrderPosition(0, skates, 1);
        this.sessionObject.getCart().getOrderPositions().add(orderPosition);
    }
}
