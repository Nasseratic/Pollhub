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
import model.Question;
import model.connection;

/**
 *
 * @author y
 */
public class QuestionCrud {
    
    connection conn = new connection();

    public void add(String content, String type, int poll, String answer) throws SQLException {

        try (Connection c = conn.connect(); PreparedStatement add = c.prepareStatement("INSERT INTO user (content, type, poll, answer)VALUES( ?, ?, ?, ?)")) {
            add.setString(1, content);
            add.setString(2, type);
            add.setInt(3, poll);
            add.setString(4, answer);
            
            add.executeUpdate();
            add.close();
            c.close();
        }

        System.out.println("+++++++++++++++++++++++++++++++Insert is done successfully");

    }

    public void update(int id, String content, String type, int poll, String answer) throws SQLException {
        try (Connection c = conn.connect(); PreparedStatement update = c.prepareStatement("UPDATE user SET content = ?, type = ?, poll = ?, answer = ? WHERE questionid = ?")) {
            update.setString(1, content);
            update.setString(2, type);
            update.setInt(3, poll);
            update.setString(4, answer);
            update.setInt(6, id);
            
            update.executeUpdate();
            update.close();
            c.close();
        }

        System.out.println("update is done successfully");

    }

    public void delete(int id) throws SQLException {
        try (Connection c = conn.connect()) {
            String deleteSQL = "DELETE FROM question WHERE questionid = ?";
            try (PreparedStatement delete = c.prepareStatement(deleteSQL)) {
                delete.setInt(1, id);
                delete.executeUpdate();
                System.out.println("delete is done successfully");
                delete.close();
            }

            c.close();
        }

    }

    public List<Question> selectall() throws SQLException {
        ResultSet resultSet;
        List<Question> questions = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "select * from question";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Question question = new Question();
                    question.questionid = resultSet.getInt("questionid");
                    question.content = resultSet.getString("content");
                    question.type = resultSet.getString("type");
                    question.poll = resultSet.getInt("poll");
                    
                    questions.add(question);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();

                return questions;

            }
        }

    }

    public List<Question> selectById(int id) throws SQLException {
        ResultSet resultSet;

        List<Question> questions = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "SELECT * FROM question WHERE questionid= ? ";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                select.setInt(1, id);
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Question question = new Question();
                    question.questionid = resultSet.getInt("questionid");
                    question.content = resultSet.getString("content");
                    question.type = resultSet.getString("type");
                    question.poll = resultSet.getInt("poll");
                    
                    questions.add(question);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();
                return questions;

            }
        }

    }
    
}
