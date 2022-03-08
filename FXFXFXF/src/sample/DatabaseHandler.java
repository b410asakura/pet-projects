package sample;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection connection;
    public Connection getDatabaseConnection() {
        String driver = "com.mysql.jdbc.Driver";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void singUpUser(User user) {
        String sql = "INSERT INTO " + Const.USER_TABLE + " ("
                + Const.USERS_FIRST_NAME + ", "
                + Const.USERS_LAST_NAME + ", "
                + Const.USERS_USER_NAME + ", "
                + Const.USERS_PASSWORD + ", "
                + Const.USERS_ADDRESS + ")"
                + "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = getDatabaseConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getUserName());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getAddress());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getUserData(User user)  {
        ResultSet resultSet = null;
        String sql = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_USER_NAME + " = ? AND " + Const.USERS_PASSWORD + " = ?";

        try {
            PreparedStatement preparedStatement = getDatabaseConnection().prepareStatement(sql);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return resultSet;
    }
}
