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
            //String p = request.getParameter("pollid");
           // int pollid = Integer.parseInt(p);
            System.out.println("zizomody done--------------------------------");
            PollCrud po = new PollCrud();
            System.out.println("zizomody done-------------------------------3333333333333333333333333-");
            Poll poll;
            poll = po.selectPollWithEverything(1);
            ArrayList<ArrayList<Integer>> questions_Answers = new ArrayList<>();
            for (int i = 0; i < poll.questions.size(); i++) {
                String answer = poll.questions.get(i).answer;
                String answers[];
                answers = answer.split("/");
                
                ArrayList<Integer> freq = new ArrayList<>();
                for (String answer1 : answers) {
                    AnswerCrud ans = new AnswerCrud();
                    freq.add(ans.selectByQuestionContent(answer1));
                }
                questions_Answers.add(freq);
                freq.clear();

            }
            
           String stat= new Gson().toJson(questions_Answers);
           out.println(stat);
                   System.out.println("controllers.Statistics.processRequest()777777777777777777777777777777777777  "+questions_Answers.size()+"    hhhhhhhhhhhhhhhhhhh" );

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
