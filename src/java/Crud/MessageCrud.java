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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Message;
import model.connection;

/**
 *
 * @author y
 */
public class MessageCrud {
     connection conn = new connection();

    public void add( String content, boolean ischecked)  {

        try (Connection c = conn.connect(); PreparedStatement add = c.prepareStatement("INSERT INTO massage (content ,ischecked)VALUES(?,?)")) {
            add.setString(1, content);
       
            add.setBoolean(2, ischecked);

          add.executeUpdate();
            add.close();
            c.close();
             System.out.println("Insert is done successfully");
          
        } catch (SQLException ex) {
             Logger.getLogger(MessageCrud.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("Insert is done successfully");
               
         }

       

    }

    public void update(int id,String content, boolean ischecked ) throws SQLException {
        try (Connection c = conn.connect(); PreparedStatement update = c.prepareStatement("UPDATE Massage SET content = ?,  ischecked=? WHERE  massageid= ?")) {
           update.setString(1, content);
            update.setBoolean(2, ischecked);
            update.setInt(3, id);
            update.executeUpdate();
            update.close();
            c.close();
        }

        System.out.println("update is done successfully");

    }

    public void delete(int id) throws SQLException {
        try (Connection c = conn.connect()) {
            String deleteSQL = "DELETE FROM Massage WHERE massageid = ?";
            try (PreparedStatement delete = c.prepareStatement(deleteSQL)) {
                delete.setInt(1, id);
                delete.executeUpdate();
                System.out.println("delete is done successfully");
                delete.close();
            }

            c.close();
        }

    }

    public List<Message> selectall() throws SQLException {
        ResultSet resultSet;
        List<Message> messages = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "select * from Massage";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Message message = new Message();
                    message.content = resultSet.getString("content");
                   
                    message.ischecked = resultSet.getBoolean("ischecked");
                    messages.add(message);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();

                return messages;

            }
        }

    }

    public Message selectById(int id) throws SQLException {                        
        
        ResultSet resultSet;
        Message message = null;
        
        try (Connection c = conn.connect()) {
            String selectSQL = "SELECT * FROM Massage WHERE massegeid= ? ";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                select.setInt(1, id);
                resultSet = select.executeQuery();
                if (resultSet.next()) {
                    message = new Message();
                    message.content = resultSet.getString("content");                   
                    message.ischecked = resultSet.getBoolean("ischecked");
                    
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();
                return  message;

            }
        }

    }
    
    
    public List<Message> getCheckedMessages() throws SQLException{
        
        ResultSet resultSet;
        List<Message> messages = new ArrayList<>();
        
        try (Connection c = conn.connect()) {
            String selectSQL = "SELECT * FROM Massage WHERE ischecked = true";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {                
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Message message = new Message();                    
                    message.content = resultSet.getString("content");                   
                    
                    messages.add(message);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();
                return  messages;

            }
        }
        
    }
    
    
    public int getNumOfMessages(){
                
        List<Message> messages = null;
        
        try {
            messages = new MessageCrud().selectall();
        }
        catch (SQLException ex) {
            System.out.println("can't select the messages from the database !! \n");
            Logger.getLogger(ReportCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        int numOfMessages = 0;
        
        if(messages != null){
            numOfMessages = messages.size();
        }        
        
        return numOfMessages;
    }
    
    
}
