package dataAccess;

import models.products.Electronic;
import models.products.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseAccess {


    private Connection connection;

    public DataBaseAccess() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/online_shop", "mona_razbani", "Pendi_Pengi142131");
    }

    public Connection getConnection() {
        return connection;
    }

    public int getId(String tableName, String columnName, String value) throws SQLException {
        int id = -1;
        if (connection != null) {
            String sql = String.format("SELECT `id` FROM `%s`  WHERE `%s` = ?;", tableName, columnName);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, value);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                id = resultSet.getInt("id");
        }
        return id;
    }

    public int getId(String tableName, String columnName, int value) throws SQLException {
        int id = -1;
        if (connection != null) {
            String sql = String.format("SELECT `id` FROM `%s`  WHERE `%s` = %d;", tableName, columnName, value);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
        }
        return id;
    }

    public boolean isObjectFound(String tableName, String columnName, String value) throws SQLException {
        if (connection != null) {
            String sql = String.format("SELECT * FROM '%s' WHERE '%s' = ?;", tableName, columnName);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, value);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        }
        return false;
    }


}
