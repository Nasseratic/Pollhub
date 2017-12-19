package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.Gson;
import crud.AnswerCrud;
import crud.PollCrud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Poll;

/**
 *
 * @author y
 */
@WebServlet(urlPatterns = {"/Statistics"})
public class Statistics extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String output = "";
            String p = request.getParameter("pollid");
            int pollid = Integer.parseInt(p);
            System.out.println("zizomody done--------------------------------");
            PollCrud po = new PollCrud();
            System.out.println("zizomody done-------------------------------3333333333333333333333333-");
            Poll poll;
            poll = po.selectPollWithEverything(pollid);
            ArrayList<ArrayList<Integer>> questions_Answers = new ArrayList<>();
            output += "<h1 class='h1'> Number of questions is ";
            output += poll.questions.size();
            output += "</h1>";
            for (int i = 0; i < poll.questions.size(); i++) {
                output += "<h3 class='h3'>";
                output += poll.questions.get(i).content;
                output += "</h3>";
                String answer = poll.questions.get(i).answer;
//                 out.println(answer);
                String answers[];
                answers = answer.split("/");
                AnswerCrud ans = new AnswerCrud();
                ArrayList<Integer> freq = new ArrayList<>();
                if (!"text".equals(poll.questions.get(i).type)) {
                    output += "<table class=\"table is-borderd \">\n"
                            + "        <thead>\n"
                            + "            <tr>\n"
                            + "                <th>Answer</th>\n"
                            + "                <th>Freq</th>\n"
                            + "            </tr>\n"
                            + "        </thead>\n";
                    output += "        <tbody>";
                    for (String answer1 : answers) {
//                     out.println(answer1);
                        if (!answer1.isEmpty()) {
                            freq.add(ans.selectByQuestionContent(answer1, poll.questions.get(i).questionid));
                            output += "<tr>";
                            output += "<td>";
                            output += answer1;
                            output += "</td>";
                            output += "<td>";
                            output += ans.selectByQuestionContent(answer1, poll.questions.get(i).questionid);
                            output += "</td>";
                            output += "</tr>";
                        }
                    }
                    output += "</tbody></table>";
                    freq.clear();

                } else {
                    output += ans.selectByQuestionId(poll.questions.get(i).questionid).size();

                }
            }

            request.setAttribute("html", output);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("poll-stat.jsp");
            requestDispatcher.forward(request, response);

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Statistics.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
