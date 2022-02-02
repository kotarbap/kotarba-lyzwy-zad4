package pl.edu.wszib.kotarba.ice.skates.database.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.wszib.kotarba.ice.skates.database.ISkatesDAO;
import pl.edu.wszib.kotarba.ice.skates.model.Skates;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SkatesDAOImpl implements ISkatesDAO {

    @Autowired
    Connection connection;

    @Override
    public List<Skates> getSkates() {
        List<Skates> skates1 = new ArrayList<>();
        try {
            String sql = "SELECT * FROM tskates";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Skates skates = new Skates();
                skates.setId(rs.getInt("id"));
                skates.setBrand(rs.getString("brand"));
                skates.setModel(rs.getString("model"));
                skates.setPrice(rs.getDouble("price"));
                skates.setQuantity(rs.getInt("quantity"));
                skates.setSize(rs.getInt("size"));

                skates1.add(skates);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return skates1;
    }

    @Override
    public Optional<Skates> getSkatesById(int skatesId) {
        try {
            String sql = "SELECT * FROM tskates WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, skatesId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Skates skates = new Skates();
                skates.setId(resultSet.getInt("id"));
                skates.setBrand(resultSet.getString("brand"));
                skates.setModel(resultSet.getString("model"));
                skates.setPrice(resultSet.getDouble("price"));
                skates.setQuantity(resultSet.getInt("quantity"));
                skates.setSize(resultSet.getInt("size"));

                return Optional.of(skates);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void updateSkates(Skates skates) {
        try {
            String sql = "UPDATE tskates SET title = ?, author = ?, price = ?, isbn  = ?, quantity = ? WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, skates.getBrand());
            preparedStatement.setString(2, skates.getModel());
            preparedStatement.setDouble(3, skates.getPrice());
            preparedStatement.setInt(4, skates.getSize());
            preparedStatement.setInt(5, skates.getQuantity());
            preparedStatement.setInt(6, skates.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
