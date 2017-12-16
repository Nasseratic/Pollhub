package controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.google.gson.Gson;
import Crud.ReportCrud;
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
import model.Report;

/**
 *
 * @author y
 */
@WebServlet(urlPatterns = {"/ReportController"})
public class ReportController extends HttpServlet {

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
            ReportCrud reportcrud = new ReportCrud();
            
            Gson gson = new Gson();
            
            Report report = gson.fromJson(request.getParameter("report"), Report.class);
            
            switch(op){
                
                case("add"):

                    reportcrud.add(report.content, report.poll, report.ischecked);
                    request.setAttribute("add", "done");
                    break;

                case("delete"):                    

                    reportcrud.delete(report.reportid);
                    request.setAttribute("add", "done");
                    break;

                case("update"):
                    
                    reportcrud.update(report.reportid, report.content, report.ischecked);
                    request.setAttribute("add", "done");    
                    break;
                    
                case("selectAll"):

                    String Reports = gson.toJson(reportcrud.selectall());                                        
                    request.setAttribute("allReports", Reports);
                    break;


                case("selectByPollId"):
                    
                    String Report = gson.toJson(reportcrud.selectByPollId(report.poll));             
                    request.setAttribute("report", Report);
                    break;
                
                case("numOfReports"):
                    
                    int numOfReports = reportcrud.getNumOfReports();
                    request.setAttribute("numOfReports", numOfReports);
                    
                    
                default:
                    break;
                                        
            }
            
        }catch (SQLException ex) {
            Logger.getLogger(ReportController.class.getName()).log(Level.SEVERE, null, ex);
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
