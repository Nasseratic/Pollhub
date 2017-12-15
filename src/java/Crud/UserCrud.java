/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.User;
import model.connection;

/**
 *
 * @author Zizo
 */
public class UserCrud {

    connection conn = new connection();

    public void add(String username, String password, String email, boolean isAdmin, boolean issusbended) throws SQLException {

        try (Connection c = conn.connect(); PreparedStatement add = c.prepareStatement("INSERT INTO user (username ,password,email,isAdmin  ,issuspended)VALUES( ?, ?, ?, ?,? )")) {
            add.setString(1, username);
            add.setString(2, password);
            add.setString(3, email);
            add.setBoolean(4, isAdmin);
            add.setBoolean(5, issusbended);

            add.executeUpdate();
            add.close();
            c.close();
        }

        System.out.println("+++++++++++++++++++++++++++++++Insert is done successfully");

    }

    public void update(int id, String username, String password, String email, boolean isAdmin, boolean issusbended) throws SQLException {
        try (Connection c = conn.connect(); PreparedStatement update = c.prepareStatement("UPDATE user SET username = ?, password = ?, email = ?, isAdmin = ?, issuspended=? WHERE userId = ?")) {
            update.setString(1, username);
            update.setString(2, password);
            update.setString(3, email);
            update.setBoolean(4, isAdmin);
            update.setBoolean(5, issusbended);
            update.setInt(6, id);
            update.executeUpdate();
            update.close();
            c.close();
        }

        System.out.println("update is done successfully");

    }

    public void delete(String username) throws SQLException {
        try (Connection c = conn.connect()) {
            String deleteSQL = "DELETE FROM user WHERE username = ?";
            try (PreparedStatement delete = c.prepareStatement(deleteSQL)) {
                delete.setString(1, username);
                delete.executeUpdate();
                System.out.println("delete is done successfully");
                delete.close();
            }

            c.close();
        }

    }

    public List<User> selectall() throws SQLException {
        ResultSet resultSet;
        List<User> users = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "select * from user";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.username = resultSet.getString("username");
                    user.email = resultSet.getString("email");
                    user.password = resultSet.getString("password");
                    user.isAdmin = resultSet.getBoolean("isadmin");
                    user.isSuspended = resultSet.getBoolean("issuspended");
                    users.add(user);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();

                return users;

            }
        }

    }

    public List<User> selectById(String username) throws SQLException {
        ResultSet resultSet;

        List<User> users = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "SELECT * FROM user WHERE username= ? ";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                select.setString(1, username);
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.username = resultSet.getString("username");
                    user.email = resultSet.getString("email");
                    user.password = resultSet.getString("password");
                    user.isAdmin = resultSet.getBoolean("isadmin");
                    user.isSuspended = resultSet.getBoolean("issuspended");
                    users.add(user);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();
                return users;

            }
        }

    }
}
