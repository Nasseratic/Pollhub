/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Crud.AnswerCrud;
import Crud.PollCrud;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Poll;
import model.Question;
import model.User;

/**
 *
 * @author y
 */
@WebServlet(urlPatterns = {"/PollController"})
public class PollController extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String op = request.getParameter("op");
            HttpSession session = request.getSession();
            int userId = (int) session.getAttribute("session_userid");
            if (userId < 0) {
                response.sendRedirect("user-login.jsp");
            } else {

                //Add poll
                PollCrud pollCrud = new PollCrud();
                int pollId = request.getParameter("pollId");
                switch (op) {
                    case "add":
                        Poll poll = new Gson().fromJson(request.getParameter("polls"), Poll.class);

                        pollCrud.add(poll.title, userId, poll.aissuspended, poll.uissuspended, poll.close, poll.questions);
                        ///response.sendRedirect("user-login.jsp"); need to know where

                        break;
                    case "getAllForUser":

                        String polls;
                        polls = new Gson.toJson(pollCrud.selectByUserId(userId));
                        request.setAttribute("polls", polls);
                        //response.sendRedirect("user-login.jsp"); need to know where

                        break;
                    case "getAllForSystem":
                        polls = new Gson.toJson(pollCrud.selectall());
                        request.setAttribute("polls", polls);
                        //response.sendRedirect("user-login.jsp"); need to know where
                        break;
                    case "answerPoll":
                        Question question = new Gson().fromJson(request.getParameter("questions"), Question.class);
                        AnswerCrud answerCrud = new AnswerCrud();
                        answerCrud.addAnswers(question.answers);
                        //response.sendRedirect("user-login.jsp"); need to know where

                        break;
                    case "delAll":
                        break;
                    case "getAllWithEverything":
                        List<Poll> pollss = pollCrud.selectall();
                        //Need to know about that

                        break;
                    case "suspend":
                        
                        pollCrud.suspend(session.getAttribute("session_IsAdmin"), pollId);
                        request.setAttribute("suspended","done");
                        //response.sendRedirect("user-login.jsp"); need to know where
                        break;
                    case "unsuspend":
                     
                        pollCrud.suspend(session.getAttribute("session_IsAdmin"), pollId);
                        request.setAttribute("suspended","done");
                        //response.sendRedirect("user-login.jsp"); need to know where
                        break;
                    case "close":
                        pollCrud.close(pollId);
                        request.setAttribute("close","done");
                        //response.sendRedirect("user-login.jsp"); need to know where
                        break;
                    case "open":
                        pollCrud.open(pollId);
                        request.setAttribute("open","done");
                        //response.sendRedirect("user-login.jsp"); need to know where
                        break;
                    default:
                        break;
                }
            }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
