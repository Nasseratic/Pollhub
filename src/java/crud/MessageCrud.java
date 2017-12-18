package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;
import model.connection;

public class MessageCrud {

    connection conn = new connection();

    public void add(String content) {
        try (Connection c = conn.connect(); PreparedStatement add = c.prepareStatement("INSERT INTO massage(content) VALUES(?)")) {
            add.setString(1, content);
            add.executeUpdate();
            add.close();
            c.close();
            System.out.println("++++++++++++++++++++++++++++++++++Insert is done successfully");
        } catch (SQLException ex) {
            Logger.getLogger(MessageCrud.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("555555555555555555555555555555555555Insert is done successfully");
        }
    }

    public void update(int id, String content) throws SQLException {
        try (Connection c = conn.connect(); PreparedStatement update = c.prepareStatement("UPDATE massage SET content = ? WHERE  massageid= ?")) {
            update.setString(1, content);
            update.setInt(2, id);
            update.executeUpdate();
            update.close();
            c.commit();
            c.close();
        }
        System.out.println("update is done successfully");
    }

    public void delete(int id) throws SQLException {
        try (Connection c = conn.connect()) {
            String deleteSQL = "DELETE FROM massage WHERE massageid = ?";
            try (PreparedStatement delete = c.prepareStatement(deleteSQL)) {
                delete.setInt(1, id);
                delete.executeUpdate();
                System.out.println("delete is done successfully");
                delete.close();
                c.commit();
            }
            c.close();
        }
    }

    public List<Message> selectall() throws SQLException {
        ResultSet resultSet;
        List<Message> messages = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "select * from massage";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Message message = new Message();
                    message.content = resultSet.getString("content");
                    //message.ischecked = resultSet.getBoolean("ischecked");
                    messages.add(message);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();
                return messages;
            }
        }
    }

    public List<Message> selectById(int id) throws SQLException {
        ResultSet resultSet;
        List<Message> messages = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "SELECT * FROM massage WHERE massageid= ? ";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                select.setInt(1, id);
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Message message = new Message();
                    message.content = resultSet.getString("content");
                    //message.ischecked = resultSet.getBoolean("ischecked");
                    messages.add(message);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();
                return messages;
            }
        }
    }
    
    public Message selectLastMessage() throws SQLException {
        ResultSet resultSet;
        Message message = new Message();
        try (Connection c = conn.connect()) {
            String selectSQL = "SELECT * FROM massage ORDER BY massageid DESC LIMIT 1";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                resultSet = select.executeQuery();
                if (resultSet.next()) {
                    message.massageid = resultSet.getInt("massageid");
                    message.content = resultSet.getString("content");
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();
                return message;
            }
        }
    }
    

}
