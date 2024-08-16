package et.by.dao;

import et.by.models.user.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends ConnectionInit {
    final private String create = "INSERT INTO User (UserID, Username, Password) VALUES (?, ?, ?)";
    final private String read = "SELECT * FROM User WHERE UserID = ?";
    final private String update = "UPDATE User SET Username = ?, Password = ? WHERE ID = ?";
    final private String readByName = "SELECT * FROM User WHERE TRIM(Username) = ?";
    final private String count = "SELECT COUNT(*) AS quantity FROM User;";
    final private String delete = "DELETE FROM User WHERE ID = ?";
    final private String readAll = "SELECT * FROM User";
    Connection connection;

    public UserDao() throws DaoException {
        super();
    }


    public boolean create(User user) throws DaoException {
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(create);
            statement.setInt(1, user.getUserID());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getUserPassword());
            int rowsInserted = statement.executeUpdate();
            statement.close();
            return rowsInserted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error creating user", e);
        }
    }


    public User read(Integer id) throws DaoException {
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(read);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = createFromResultSet(resultSet);
            }
            resultSet.close();
            statement.close();
            if (user == null) {
                throw new DaoException("User not found with ID");
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Error reading user", e);
        }
    }


    public User readByName(String nickname) throws DaoException {
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(readByName);
            statement.setString(1, nickname);
            ResultSet resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = createFromResultSet(resultSet);
            }
            resultSet.close();
            statement.close();
            if (user == null) {
                return null;
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Error reading user", e);
        }
    }


    public User update(Integer id, User user) throws DaoException {
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(update);
            statement.setInt(1, user.getUserID());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getUserPassword());
            int rowsUpdated = statement.executeUpdate();
            statement.close();
            if (rowsUpdated == 0) {
                throw new DaoException("User not found with ID " + id);
            }
            return user;
        } catch (SQLException e) {
            throw new DaoException("Error updating user", e);
        }
    }


    public boolean delete(Integer id) throws DaoException {
        try {
            connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(delete);
            statement.setInt(1, id);
            int rowsDeleted = statement.executeUpdate();
            statement.close();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            throw new DaoException("Error deleting user", e);
        }
    }


    public List<User> readAll() throws DaoException {
        try {
            connection = connectionPool.getConnection();
            List<User> users = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(readAll);
            while (resultSet.next()) {
                User user = createFromResultSet(resultSet);
                users.add(user);
            }
            resultSet.close();
            statement.close();
            return users;
        } catch (SQLException e) {
            throw new DaoException("Error reading all users", e);
        }
    }

    public int returnCount() throws DaoException {
        try {
            connection = connectionPool.getConnection();
            String query = count;
            int quantity = -1;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                String temp = resultSet.getString("quantity");
                quantity = Integer.parseInt(temp);

            }
            statement.close();
            connection.close();
            return quantity;
        } catch (SQLException e) {
            throw new DaoException("Error returning count", e);
        }
    }


    private User createFromResultSet(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("UserID");
        String nickname = resultSet.getString("Username");
        String password = resultSet.getString("Password");
        return new User(id, nickname, password);
    }
}
