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
import model.Answer;
import model.connection;

/**
 *
 * @author y
 */
public class AnswerCrud {

    connection conn = new connection();

    public void addAnswers(ArrayList<Answer> answers) throws SQLException {

        try (Connection c = conn.connect(); PreparedStatement add = c.prepareStatement("INSERT INTO answer (question, content, user, isrevailed) VALUES ( ?, ?, ?, ?)")) {
            for (int i = 0; i < answers.size(); i++) {

                add.setInt(1, answers.get(i).question);
                add.setString(2, answers.get(i).content);
                add.setInt(3, answers.get(i).user);
                add.setBoolean(4, answers.get(i).isrevailed);

                add.executeUpdate();
            }

            add.close();
            c.close();
        }

        System.out.println("Insert is done successfully");

    }

    public void updateAnswers(ArrayList<Answer> answers) throws SQLException {
        try (Connection c = conn.connect(); PreparedStatement update = c.prepareStatement("UPDATE answer SET question = ?, content = ?, user = ?, isrevailed = ? WHERE answerid = ?")) {

            for (int i = 0; i < answers.size(); i++) {
                update.setInt(1, answers.get(i).question);
                update.setString(2, answers.get(i).content);
                update.setInt(3, answers.get(i).user);
                update.setBoolean(4, answers.get(i).isrevailed);
                update.setInt(5, answers.get(i).answerid);

                update.executeUpdate();

            }

            update.close();
            c.close();
        }

        System.out.println("update is done successfully");

    }

    public void delete(int id) throws SQLException {
        try (Connection c = conn.connect()) {
            String deleteSQL = "DELETE FROM answer WHERE answerid = ?";
            try (PreparedStatement delete = c.prepareStatement(deleteSQL)) {
                delete.setInt(1, id);
                delete.executeUpdate();
                System.out.println("delete is done successfully");
                delete.close();
            }

            c.close();
        }

    }

    public List<Answer> selectall() throws SQLException {
        ResultSet resultSet;
        List<Answer> answers = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "select * from answer";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Answer answer = new Answer();
                    answer.answerid = resultSet.getInt("answerid");
                    answer.question = resultSet.getInt("question");
                    answer.content = resultSet.getString("content");
                    answer.user = resultSet.getInt("user");
                    answer.isrevailed = resultSet.getBoolean("isrevailed");

                    answers.add(answer);
                }

                System.out.println("Selection is done successfully");
                select.close();
                c.close();

                return answers;

            }
        }

    }

    public List<Answer> selectByQuestionId(int id) throws SQLException {
        ResultSet resultSet;

        List<Answer> answers = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "SELECT * FROM answer WHERE question= ? ";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                select.setInt(1, id);
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Answer answer = new Answer();
                    answer.answerid = resultSet.getInt("answerid");
                    answer.question = resultSet.getInt("question");
                    answer.content = resultSet.getString("content");
                    answer.user = resultSet.getInt("user");
                    answer.isrevailed = resultSet.getBoolean("isrevailed");

                    answers.add(answer);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();
                return answers;

            }
        }

    }

    public int selectByQuestionContent(String content ) throws SQLException {
        ResultSet resultSet;

        List<Answer> answers = new ArrayList<>();
        try (Connection c = conn.connect()) {
            String selectSQL = "SELECT * FROM answer WHERE content= ? ";
            try (PreparedStatement select = c.prepareStatement(selectSQL)) {
                select.setString(1, content);
                resultSet = select.executeQuery();
                while (resultSet.next()) {
                    Answer answer = new Answer();
                    answer.answerid = resultSet.getInt("answerid");
                    answer.question = resultSet.getInt("question");
                    answer.content = resultSet.getString("content");
                    answer.user = resultSet.getInt("user");
                    answer.isrevailed = resultSet.getBoolean("isrevailed");

                    answers.add(answer);
                }
                System.out.println("Selection is done successfully");
                select.close();
                c.close();
                return answers.size();

            }
        }

}
}
