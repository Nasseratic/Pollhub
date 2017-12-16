package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Crud.MessageCrud;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Message;
import model.Report;

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
            
            HttpSession session = request.getSession();
            String op = request.getParameter("op");
            MessageCrud messagecrud = new MessageCrud();
            
            Gson gson = new Gson();
            
            Message message = gson.fromJson(request.getParameter("message"), Message.class);
            
            switch(op){
                case("add"):                    

                    messagecrud.add(message.content, message.ischecked);
                    request.setAttribute("add", "done");
                    break;

                case("delete"):
                    
                    messagecrud.delete(message.massageid);
                    request.setAttribute("delete", "done");
                    break;

                case("update"):

                    messagecrud.update(message.massageid, message.content, message.ischecked);
                    request.setAttribute("update", "done");
                    break;

                case("selectAll"):

                    String allMessages = gson.toJson(messagecrud.selectall());
                    request.setAttribute("allMessages", allMessages);
                    break;

                case("selectById"):                    

                    String Message = gson.toJson(messagecrud.selectById(message.massageid));
                    request.setAttribute("Message", Message);
                    break;
                
                
                case("getCheckedMessages"):    
                    
                    String checkedMessages = gson.toJson(messagecrud.getCheckedMessages());
                    request.setAttribute("checkedMessages", checkedMessages);
                    break;
                    
                
                case("numOfMessages"):
                    
                    int numOfMessages = messagecrud.getNumOfMessages();
                    request.setAttribute("numOfMessages", numOfMessages);
                    break;
                    
                default:                    
                    break;

            }
    }   catch (SQLException ex) {
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