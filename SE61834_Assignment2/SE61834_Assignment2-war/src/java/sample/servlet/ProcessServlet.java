/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.common.AccountObj;

/**
 *
 * @author PhucTDSE61834
 */
public class ProcessServlet extends HttpServlet {

    private final String loginPage = "login.jsp";
    private final String loginServlet = "LoginServlet";
    private final String searchRoomServlet = "SearchRoomServlet";
    private final String showAllServlet = "ShowAllServlet";
    private final String loadCateServlet = "LoadCateServlet";
    private final String changeCateServlet = "UpdateCateServlet";
    private final String requestPage = "request.jsp";
    private final String createReportServlet = "CreateReportServlet";
    private final String dateFilterServlet = "DateFilterServlet";
    private final String updateReportServlet = "UpdateReportServlet";
    private final String logoutServlet = "LogoutServlet";

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
        PrintWriter out = response.getWriter();
        try {
           String myAction = request.getParameter("myAction");
            String url = loginPage;
            HttpSession session = request.getSession(false);
            AccountObj acc = null;
            if (session != null) {
                acc = (AccountObj) session.getAttribute("ACCOUNT");
            }
            if (myAction == null) {
                //
            } else if (acc == null && !myAction.equals("Login")) {
                request.setAttribute("LoginErr", "You are not login");
            } else {
                if (myAction.equals("Login")) {
                    url = loginServlet;
                } else if (myAction.equals("Search")) {
                    url = searchRoomServlet;
                } else if (myAction.equals("Show All")) {
                    url = showAllServlet;
                } else if (myAction.equals("Change")) {
                    url = loadCateServlet;
                } else if (myAction.equals("Request")) {
                    url = requestPage;
                } else if (myAction.equals("Save")) {
                    url = changeCateServlet;
                } else if (myAction.equals("Request")) {
                    url = requestPage;
                } else if (myAction.equals("Report")) {
                    url = createReportServlet;
                } else if (myAction.equals("Filter")) {
                    url = dateFilterServlet;
                } else if (myAction.equals("Finish")) {
                    url = updateReportServlet;
                } else if (myAction.equals("Logout")) {
                    url = logoutServlet;
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } finally {
            out.close();
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
