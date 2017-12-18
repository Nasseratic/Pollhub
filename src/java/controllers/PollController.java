package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import crud.AnswerCrud;
import crud.PollCrud;
import java.io.IOException;
import java.io.PrintWriter;
import com.google.gson.Gson;
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
import javax.servlet.http.HttpSession;
import model.Answer;
import model.Poll;
import model.Question;

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
            String polls = "";
            Poll poll = null;
            int userId = 2;
            if (userId < 0) {
                response.sendRedirect("user-login.jsp");
            } else {
                //Add poll
                crud.PollCrud pollCrud = new crud.PollCrud();
                Gson json = new Gson();
                switch (op) {
                    case "add":
                        poll = json.fromJson(request.getParameter("json"), Poll.class);

                         {
                            try {
                                ArrayList<Question> questions = new ArrayList<>() ;
                                for (int i = 0; i < poll.questions.size(); i++) {
                                   questions.add( poll.questions.get(i));
                                    
                                }
                                System.out.println(questions.get(0).content+"looooooooooool");
                                pollCrud.add(poll.title, userId, poll.aissuspended, poll.uissuspended, poll.close, questions);
                                ///response.sendRedirect("user-login.jsp"); need to know where
                            } catch (SQLException ex) {
                                Logger.getLogger(PollController.class.getName()).log(Level.SEVERE, null, ex);
                                //response.sendRedirect("user-login.jsp"); need to know where
                            }
                        }

                        break;

                    case "getAllForSystem": {
                        try {
                            polls = json.toJson(pollCrud.selectall());
                            request.setAttribute("polls", polls);
                            RequestDispatcher disp = request.getRequestDispatcher("home.jsp");
                            disp.forward(request, response);
                            //request.getRequestDispatcher("/PollController").forward(request, response);
                        } catch (SQLException ex) {
                            Logger.getLogger(PollController.class.getName()).log(Level.SEVERE, null, ex);
                            //response.sendRedirect("user-login.jsp"); need to know where
                        }
                    }
                    request.setAttribute("polls", polls);
                    //response.sendRedirect("user-login.jsp"); need to know where
                    break;
                    case "answerPoll":
                        Question question = json.fromJson(request.getParameter("questions"), Question.class);
                        AnswerCrud answerCrud = new AnswerCrud();
                         {
                            try {
                                ArrayList<Answer> answers = new ArrayList<>() ;
                                for (int i = 0; i < poll.questions.size(); i++) {
                                   answers.add( question.answers.get(i));
                                    
                                }
                                answerCrud.addAnswers(answers);
                                //response.sendRedirect("user-login.jsp"); need to know where
                            } catch (SQLException ex) {
                                Logger.getLogger(PollController.class.getName()).log(Level.SEVERE, null, ex);
                                //response.sendRedirect("user-login.jsp"); need to know where
                            }
                        }

                        break;
                    case "getPollWithEverything": {
                        try {
                            int pollId = Integer.parseInt(request.getParameter("pollId"));

                            poll = pollCrud.selectPollWithEverything(pollId);
                            polls = json.toJson(poll);
                            request.setAttribute("poll", polls);
                            //request.getRequestDispatcher("/ConfirmationServlet").forward(request, response);
                            //Need to know about that
                        } catch (SQLException ex) {
                            Logger.getLogger(PollController.class.getName()).log(Level.SEVERE, null, ex);
                            //response.sendRedirect("user-login.jsp"); need to know where
                        }
                    }

                    break;
                    case "suspend": {
                        try {
                                            int pollId = Integer.parseInt(request.getParameter("pollId"));

                            pollCrud.suspend((boolean) session.getAttribute("session_IsAdmin"), pollId);
                            request.setAttribute("suspended", "done");
                            out.println("true");
                            //response.sendRedirect("user-login.jsp"); need to know where
                        } catch (SQLException ex) {
                            Logger.getLogger(PollController.class.getName()).log(Level.SEVERE, null, ex);
                            //response.sendRedirect("user-login.jsp"); need to know where
                        }
                    }

                    break;
                    case "unsuspend": {
                        try {
                                            int pollId = Integer.parseInt(request.getParameter("pollId"));

                            pollCrud.unSuspend((boolean) session.getAttribute("session_IsAdmin"), pollId);
                            request.setAttribute("suspended", "done");
                            out.println("true");
                            //response.sendRedirect("user-login.jsp"); need to know where
                        } catch (SQLException ex) {
                            Logger.getLogger(PollController.class.getName()).log(Level.SEVERE, null, ex);
                            //response.sendRedirect("user-login.jsp"); need to know where
                        }
                    }

                    break;
                    case "close": {
                        try {
                                            int pollId = Integer.parseInt(request.getParameter("pollId"));

                            pollCrud.close(pollId);
                            out.println("true");
                            //response.sendRedirect("user-login.jsp"); need to know where
                        } catch (SQLException ex) {
                            Logger.getLogger(PollController.class.getName()).log(Level.SEVERE, null, ex);
                            //response.sendRedirect("user-login.jsp"); need to know where
                        }
                    }

                    break;
                    case "open": {
                        try {
                                            int pollId = Integer.parseInt(request.getParameter("pollId"));

                            pollCrud.open(pollId);
                            request.setAttribute("open", "done");
                            out.println("true");
                            //response.sendRedirect("user-login.jsp"); need to know where
                        } catch (SQLException ex) {
                            Logger.getLogger(PollController.class.getName()).log(Level.SEVERE, null, ex);
                            //response.sendRedirect("user-login.jsp"); need to know where
                        }
                    }

                    break;
                    default:
                        response.sendRedirect("user-login.jsp");
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
