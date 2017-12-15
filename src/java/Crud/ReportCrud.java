/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crud;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Report;
import model.connection;

/**
 *
 * @author y
 */
public class ReportCrud {
    connection conn = new connection();

    public void add( String content,int poll, boolean ischecked) throws SQLException {

        try (Connection c = conn.connect(); PreparedStatement add = c.prepareStatement("INSERT INTO report (content ,poll,ischecked)VALUES(?,?,?)")) {
            add.setString(1, content);
            add.setInt(2, poll);
            add.setBoolean(3, ischecked);

            add.executeUpdate();
            add.close();
            c.close();
            System.out.println("555555555555555555555555555555555555Insert is done successfully");
        }

        System.out.println("++++++++++++++++++++++++++++++++++Insert is done successfully");

    }

    public void update(int id, String content, boolean ischecked ) throws SQLException {
        try (Connection c = conn.connect(); PreparedStatement update = c.prepareStatement("UPDATE report SET content = ?,  ischecked=? WHERE  massageid= ?")) {
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
            String deleteSQL = "DELETE FROM report WHERE reportid = ?";
            try (PreparedStatement delete = c.prepareStatement(deleteSQL)) {
                delete.setInt(1, id);
                delete.executeUpdate();
                System.out.println("delete is done successfully");
                delete.close();
            }

            c.close();
        }

    }

    public List<Report> selectall() throws SQLException {
        ResultSet resultSet;
        List<Report> reports = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "select * from Massage";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Report report = new Report();
                    report.content = resultSet.getString("content");
                   
                    report.ischecked = resultSet.getBoolean("ischecked");
                    reports.add(report);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();

                return reports;

            }
        }

    }

    public List<Report> selectById(int id) throws SQLException {
        ResultSet resultSet;

        List<Report> messages = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "SELECT * FROM Massage WHERE massegeid= ? ";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                select.setInt(1, id);
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                   Report message = new Report();
                    message.content = resultSet.getString("content");
                   
                    message.ischecked = resultSet.getBoolean("ischecked");
                    messages.add(message);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();
                return  messages;

            }
        }

    }
}
