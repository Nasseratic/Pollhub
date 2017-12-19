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
import model.Question;
import model.connection;

/**
 *
 * @author y
 */
public class QuestionCrud {

    connection conn = new connection();

    public void addQuestions(ArrayList<Question> questions) throws SQLException {
        try (Connection c = conn.connect(); PreparedStatement add = c.prepareStatement("INSERT INTO question (content, type, poll, answer)VALUES( ?, ?, ?, ?)")) {
            for (int i = 0; i < questions.size(); i++) {

                add.setString(1, questions.get(i).content);
                add.setString(2, questions.get(i).type);
                add.setInt(3, questions.get(i).poll);
                add.setString(4, questions.get(i).answer);

                add.executeUpdate();

            }
        }

        System.out.println("Insert is done successfully");

    }

    public void updateQuestions(ArrayList<Question> questions) throws SQLException {
        try (Connection c = conn.connect(); PreparedStatement update = c.prepareStatement("UPDATE question SET content = ?, type = ?, poll = ?, answer = ? WHERE questionid = ?")) {

            for (int i = 0; i < questions.size(); i++) {
                update.setString(1, questions.get(i).content);
                update.setString(2, questions.get(i).type);
                update.setInt(3, questions.get(i).poll);
                update.setString(4, questions.get(i).answer);
                update.setInt(5, questions.get(i).questionid);

                update.executeUpdate();

            }

            update.close();
            c.close();
        }

        System.out.println("update is done successfully");

    }

    public void delete(ArrayList<Integer> questionsIds) throws SQLException {
        try (Connection c = conn.connect()) {
            String deleteSQL = "DELETE FROM question WHERE questionid = ?";
            try (PreparedStatement delete = c.prepareStatement(deleteSQL)) {
                for (int i = 0; i < questionsIds.size(); i++) {

                    delete.setInt(1, questionsIds.get(i));
                    delete.executeUpdate();
                }

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

    public List<Question> selectByPollId(int id) throws SQLException {
        ResultSet resultSet;

        List<Question> questions = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "SELECT * FROM question WHERE poll= ? ";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                select.setInt(1, id);
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Question question = new Question();
                    question.questionid = resultSet.getInt("questionid");
                    question.content = resultSet.getString("content");
                    question.type = resultSet.getString("type");
                    question.poll = resultSet.getInt("poll");
                    question.answer = resultSet.getString("answer");

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
