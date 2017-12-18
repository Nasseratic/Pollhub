package crud;

import model.connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;
import model.User;

/**
 * @author Abdullah
 */
public class UsersMessagesCrud {

    private final connection conn = new connection();

    public void add(int userId, int messageId) {
        try (Connection c = conn.connect(); PreparedStatement add = c.prepareStatement("INSERT INTO massage_users(massageid, userid) VALUES(?,?)")) {
            add.setInt(1, messageId);
            add.setInt(2, userId);
            add.executeUpdate();
            add.close();
            c.close();
            System.out.println("Insert is done successfully tp massage_users table");
        } catch (SQLException ex) {
            Logger.getLogger(MessageCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addToAllUsers(List<Integer> users, int messageId) {
        users.forEach((Integer userId) -> {
            add(userId, messageId);
        });
    }

    public void update(int userId, int messageId) {
        try (Connection c = conn.connect(); PreparedStatement update = c.prepareStatement("UPDATE massage_users SET ischecked=? WHERE massageid=?, userid=?")) {
            update.setBoolean(1, true);
            update.setInt(2, messageId);
            update.setInt(3, userId);
            update.executeUpdate();
            update.close();
            c.commit();
            c.close();
            System.out.println("Insert is done successfully tp massage_users table");
        } catch (SQLException ex) {
            Logger.getLogger(MessageCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int userId, int messageId) throws SQLException {
        try (Connection c = conn.connect()) {
            String deleteSQL = "DELETE FROM massage_users WHERE massageid=?, userid=?";
            try (PreparedStatement delete = c.prepareStatement(deleteSQL)) {
                delete.setInt(1, messageId);
                delete.setInt(2, userId);
                delete.executeUpdate();
                System.out.println("delete is done successfully");
                delete.close();
                c.commit();
            }
            c.close();
        }
    }
		
    public List<Message> selectAllMessages(int userId, boolean isChecked) throws SQLException {
        ResultSet resultSet;
        List<Message> messages = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "select massageid from massage_users where userid=? and ischecked=?";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                select.setInt(1, userId);
                select.setBoolean(2, isChecked);
                resultSet = select.executeQuery();
                MessageCrud msg = new MessageCrud();
                while (resultSet.next()) {
                    Message message = msg.selectById(resultSet.getInt("massageid")).get(0);
                    messages.add(message);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();
                return messages;
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
