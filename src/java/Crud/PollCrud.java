/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Poll;
import model.connection;

/**
 *
 * @author y
 */
public class PollCrud {
    
    connection conn = new connection();

    public void add(String title, int user, boolean aissuspended, boolean uissuspended, boolean close) throws SQLException {

        try (Connection c = conn.connect(); PreparedStatement add = c.prepareStatement("INSERT INTO poll (title, user, aissuspended, uissuspended, close) VALUES (?, ?, ?, ?, ?)")) {
            add.setString(1, title);
            add.setInt(2, user);
            add.setBoolean(3, aissuspended);
            add.setBoolean(4, uissuspended);
            add.setBoolean(5, close);
            
            add.executeUpdate();
            add.close();
            c.close();
        }

        System.out.println("+++++++++++++++++++++++++++++++Insert is done successfully");

    }

    public void update(String title, int pollid, int user, boolean aissuspended, boolean uissuspended, boolean close) throws SQLException {
        try (Connection c = conn.connect(); PreparedStatement update = c.prepareStatement("UPDATE user SET title = ?, user = ?, aissuspended = ?, uissuspended = ?, close = ? WHERE pollid = ?")) {
            update.setString(1, title);
            update.setInt(2, user);
            update.setBoolean(3, aissuspended);
            update.setBoolean(4, uissuspended);
            update.setBoolean(6, close);
            update.setInt(7, pollid);
            
            update.executeUpdate();
            update.close();
            c.close();
        }

        System.out.println("update is done successfully");

    }

    public void delete(int id) throws SQLException {
        try (Connection c = conn.connect()) {
            String deleteSQL = "DELETE FROM poll WHERE pollid = ?";
            try (PreparedStatement delete = c.prepareStatement(deleteSQL)) {
                delete.setInt(1, id);
                delete.executeUpdate();
                System.out.println("delete is done successfully");
                delete.close();
            }

            c.close();
        }

    }

    public List<Poll> selectall() throws SQLException {
        ResultSet resultSet;
        List<Poll> polls = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "select * from poll";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Poll poll = new Poll();
                    poll.title = resultSet.getString("title");
                    poll.pollid = resultSet.getInt("pollid");
                    poll.user = resultSet.getInt("user");
                    poll.aissuspended = resultSet.getBoolean("aissuspended");
                    poll.uissuspended = resultSet.getBoolean("uissuspended");
                    poll.close = resultSet.getBoolean("close");
                                        
                    polls.add(poll);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();

                return polls;

            }
        }

    }

    public List<Poll> selectById(int id) throws SQLException {
        ResultSet resultSet;

        List<Poll> polls = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "SELECT * FROM poll WHERE pollid = ? ";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                select.setInt(1, id);
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Poll poll = new Poll();
                    poll.title = resultSet.getString("title");
                    poll.pollid = resultSet.getInt("pollid");
                    poll.user = resultSet.getInt("user");
                    poll.aissuspended = resultSet.getBoolean("aissuspended");
                    poll.uissuspended = resultSet.getBoolean("uissuspended");
                    poll.close = resultSet.getBoolean("close");
                    
                    polls.add(poll);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();
                return polls;

            }
        }

    }
    
}
