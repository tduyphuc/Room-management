/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
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
public class DateFilterServlet extends HttpServlet {

    private final String staffPage = "staff.jsp";

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
            String txtDate = request.getParameter("txtDate");
            String url = staffPage;
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
            } else if (txtDate == null) {
                url = "ErrorPage/401.html";
            } else if (role != 1) {
                url = "ErrorPage/403.html";
            } else {
                txtDate = txtDate.trim();
                if (checkDateFormat(txtDate)) {
                    Context context = new InitialContext();
                    Object obj = context.lookup("MaintenanceRemote");
                    tbl_maintenanceSessionBeanRemote poji = (tbl_maintenanceSessionBeanRemote) obj;
                    List list = poji.filterDate(txtDate);
                    request.setAttribute("MAINTENANCE", list);

                } else {
                    if (txtDate.equals("")) {
                        request.setAttribute("errFilter", "Please fill in the filter bar");
                    } else {
                        request.setAttribute("errFilter", "Wrong date format!");
                    }
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (NamingException ex) {
            log("DateFilterServlet_JNDI: " + ex.getMessage());
        } finally {
            out.close();
        }
    }

    private boolean checkDateFormat(String s) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        String rex = "[0-9]{1,2}/[0-9]{1,2}/[0-9]{4}";
        if (!s.matches(rex)) {
            return false;
        }
        try {
            sdf.parse(s);
        } catch (ParseException ex) {
            return false;
        }
        return true;
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
