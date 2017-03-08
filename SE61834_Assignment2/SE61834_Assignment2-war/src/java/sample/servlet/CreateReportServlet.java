/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.common.AccountObj;
import sample.tbl_maintenance.tbl_maintenanceSessionBeanRemote;

/**
 *
 * @author PhucTDSE61834
 */
public class CreateReportServlet extends HttpServlet {

    private final String processServlet = "ProcessServlet";
    private final String requestPage = "request.jsp";

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

            String roomID = request.getParameter("roomID");
            String show = request.getParameter("show");
            String value = request.getParameter("txtSearchValue");
            String txtSearchValue = "txtSearchValue=" + (value != null ? value : "");
            String txtReason = request.getParameter("txtReason");
            String url = requestPage + "?" + "&roomID=" + roomID + (show != null ? "&show=SHOW" : "") + "&" + txtSearchValue + "&txtReason=" + txtReason;
            HttpSession session = request.getSession(false);
            int role = -1;
            AccountObj ac = null;
            if (session != null) {
                ac = (AccountObj) session.getAttribute("ACCOUNT");
                if (ac != null)
                    role = ac.getRole();
            }
            if (session == null) {
                url = "ProcessServlet";
            } else if (ac == null) {
                url = "ProcessServlet";
            } else if (roomID == null || value == null || txtReason == null) {
                url = "ErrorPage/401.html";
            } else if (role != 3) {
                url = "ErrorPage/403.html";
            } else {
                txtReason = txtReason.trim();
                if (txtReason.equals("")) {
                    url += "&errCreate=Please fill in reason";
                } else if (txtReason.length() > 250) {
                    url += "&errCreate=Limit is 250 chars.";
                } else {
                    // create report
                    String requestUser = ac.getUsername();
                    // call remote
                    Context context = new InitialContext();
                    Object obj = context.lookup("MaintenanceRemote");
                    tbl_maintenanceSessionBeanRemote poji = (tbl_maintenanceSessionBeanRemote) obj;
                    poji.createReport(requestUser, roomID, txtReason);
                    // set url
                    String action = show != null ? "myAction=Show All" : "myAction=Search";
                    url = processServlet + "?" + action + "&" + txtSearchValue;
                }
            }

            response.sendRedirect(url);
        } catch (NamingException ex) {
            log("CreateReportServlet_JNDI: " + ex.getMessage());
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
