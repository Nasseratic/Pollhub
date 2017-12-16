/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import crud.ReportCrud;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
            String op = request.getParameter("op");
            out.print("<h1>Report Controller</h1>");
            out.print("<h1>" + op + "</h1>");
            ReportCrud report = new ReportCrud();
            if ("add".equals(op)) {
                String content = request.getParameter("content");
                int poll = Integer.parseInt(request.getParameter("poll"));
                boolean isChecked = Boolean.getBoolean(request.getParameter("isChecked"));
                report.add(content, poll, isChecked);
            } else if ("delete".equals(op)) {
                int reportid = Integer.parseInt(request.getParameter("reportid"));
                report.delete(reportid);
            } else if ("update".equals(op)) {
                int reportid = Integer.parseInt(request.getParameter("reportid"));
                String content = request.getParameter("content");
                int poll = Integer.parseInt(request.getParameter("poll"));
                boolean isChecked = Boolean.getBoolean(request.getParameter("isChecked"));
                report.update(reportid, content, isChecked);
            } else if ("selectAll".equals(op)) {
                out.print("Select All");
                List<Report> list = report.selectall();
                request.setAttribute("listOfReports", list);
                RequestDispatcher RD = request.getRequestDispatcher("reports-list.jsp");
                RD.forward(request, response);
            } else if ("selectById".equals(op)) {
                int reportid = Integer.parseInt(request.getParameter("reportid"));
                List<Report> list = report.selectById(reportid);
                Report r = list != null ? list.get(0) : null;
                out.print("Ana");
                request.setAttribute("report", r);
                RequestDispatcher RD = request.getRequestDispatcher("report-view.jsp");
                RD.forward(request, response);
            }
        } catch (SQLException ex) {
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
