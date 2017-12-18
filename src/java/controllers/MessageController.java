package controllers;

import crud.UsersMessagesCrud;
import crud.MessageCrud;
import crud.UserCrud;
import model.Message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mohamed
 */
@WebServlet(urlPatterns = {"/MessageController"})
public class MessageController extends HttpServlet {

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
            MessageCrud msg = new MessageCrud();
            out.print(op);
            if (op != null) {
                switch (op) {
                    case "add": {
                        String content = request.getParameter("content");
                        String users = request.getParameter("spesific");
                        msg.add(content);
                        out.print(content);
                        out.print(users);
                        Message lastMessage = msg.selectLastMessage();
                        int messageId = lastMessage.massageid;
                        if (!users.equals("null")) {
                            UsersMessagesCrud crudOfUsersMessages = new UsersMessagesCrud();
                            if (users.equals("allUsers")) {
                                UserCrud crudOfUser = new UserCrud();
                                List<Integer> usersId;
                                usersId = crudOfUser.selectAllUsersId();
                                crudOfUsersMessages.addToAllUsers(usersId, messageId);
                            } else {
                                int userId;
                                try {
                                    userId = Integer.parseInt(users);
                                    crudOfUsersMessages.add(userId, messageId);
                                } catch (NumberFormatException ex) {
                                    System.out.println("Number format exception.");
                                }
                            }
                        }
                        response.sendRedirect("message-success.jsp");
                        break;
                    }
                    case "delete": {
                        int messageid = Integer.parseInt(request.getParameter("messageid"));
                        msg.delete(messageid);
                        break;
                    }
                    case "update": {
                        int messageid = Integer.parseInt(request.getParameter("messageid"));
                        String content = request.getParameter("content");
                        boolean isChecked = Boolean.getBoolean(request.getParameter("isChecked"));
                        msg.update(messageid, content);
                        break;
                    }
                    case "selectAll":
                        msg.selectall();
                        break;
                    case "selectById": {
                        int messageid = Integer.parseInt(request.getParameter("messageid"));
                        msg.selectById(messageid);
                        break;
                    }
                    default:
                        break;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageController.class.getName()).log(Level.SEVERE, null, ex);
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
