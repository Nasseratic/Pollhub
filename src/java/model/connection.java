/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Zizo
 */
public class connection {

    String url = "jdbc:mysql://localhost:3306/pollhub";
    final String user = "root";
    final String password = "";

    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("model.connection.connect()+++++++++++++++++++++++++++++++++++++++++");
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            Logger.getLogger(connection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("models.connection.notconnect()");
        }
        return null;
    }

    public java.sql.Statement createstatement(Connection con) throws SQLException {
        return con.createStatement();
    }

}
