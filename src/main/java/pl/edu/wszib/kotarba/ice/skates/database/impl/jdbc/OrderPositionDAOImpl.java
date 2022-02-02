package pl.edu.wszib.kotarba.ice.skates.database.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.kotarba.ice.skates.database.IOrderPositionDAO;
import pl.edu.wszib.kotarba.ice.skates.database.ISkatesDAO;
import pl.edu.wszib.kotarba.ice.skates.model.OrderPosition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderPositionDAOImpl implements IOrderPositionDAO {

    @Autowired
    Connection connection;

    @Autowired
    ISkatesDAO skatesDAO;

    @Override
    public void addOrderPosition(OrderPosition orderPosition, int orderId) {
        try {
            String sql = "INSERT INTO torderposition VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setInt(2, orderId);
            preparedStatement.setInt(3, orderPosition.getSkates().getId());
            preparedStatement.setInt(4, orderPosition.getQuantity());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                orderPosition.setId(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<OrderPosition> getOrderPositionsByOrderId(int orderId) {
        List<OrderPosition> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torderposition WHERE order_id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                OrderPosition orderPosition = new OrderPosition();
                orderPosition.setId(rs.getInt("id"));
                orderPosition.setQuantity(rs.getInt("quantity"));
                orderPosition.setSkates(this.skatesDAO.getSkatesById(rs.getInt("skates_id")).get());

                result.add(orderPosition);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
