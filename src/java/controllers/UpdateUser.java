/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import crud.UserCrud;
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
import model.User;

/**
 *
 * @author Zizo
 */
@WebServlet(name = "UpdateUser", urlPatterns = {"/UpdateUser"})
public class UpdateUser extends HttpServlet {

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
            String op = request.getParameter("op");
            String username = request.getParameter("username");
            UserCrud u = new UserCrud();
            ArrayList<User> users = new ArrayList<>();
            users = u.selectById(username);
            if (op.equals("reset_password")) {
                String password = request.getParameter("password");
                u.update(users.get(0).userId, users.get(0).username, password, users.get(0).email, users.get(0).isAdmin, users.get(0).isSuspended);
                out.println("The new password is " + password);
            } else if (op.equals("admin")) {
                String isAdmin = request.getParameter("isAdmin");
                if (isAdmin.equals("true")) {
                    u.update(users.get(0).userId, users.get(0).username, users.get(0).password, users.get(0).email, true, users.get(0).isSuspended);
                    out.println(username + " is be Admin ");
                } else {
                    u.update(users.get(0).userId, users.get(0).username, users.get(0).password, users.get(0).email, false, users.get(0).isSuspended);
                    out.println(username + " is be not Admin ");
                }

            }
            else if (op.equals("suspended")) {
                String isSuspended = request.getParameter("isSuspended");
                if (isSuspended.equals("true")) {
                    u.update(users.get(0).userId, users.get(0).username, users.get(0).password, users.get(0).email, users.get(0).isAdmin, true);
                    out.println(username + " is suspended ");
                } else {
                    u.update(users.get(0).userId, users.get(0).username, users.get(0).password, users.get(0).email, users.get(0).isAdmin, false);
                    out.println(username + " is  unsuspended ");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateUser.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(UpdateUser.class.getName()).log(Level.SEVERE, null, ex);
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
