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
public class UpdateReportServlet extends HttpServlet {

    private final String processServlet = "ProcessServlet";

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
            String[] idArray = request.getParameterValues("id");
            String txtDate = request.getParameter("txtDate");
            String url = processServlet + "?" + "txtDate=" + txtDate + "&myAction=Filter";

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
                String username = ac.getUsername();
                if (idArray != null) {
                    Context context = new InitialContext();
                    Object obj = context.lookup("MaintenanceRemote");
                    tbl_maintenanceSessionBeanRemote poji = (tbl_maintenanceSessionBeanRemote) obj;
                    for (String idStr : idArray) {
                        int id = Integer.parseInt(idStr);
                        String mend = request.getParameter("txtMend" + id).trim();
                        String cost = request.getParameter("txtCost" + id).trim();
                        boolean validCost = validCost(cost);
                        if (validCost && mend.length() <= 250) {
                            boolean success = poji.updateReport(id, mend, Double.parseDouble(cost), username);
                            if (!success) {
                                url += "&errUpdate=Some report hadn't been update !";
                            }
                        }
                    }
                }
            }
            response.sendRedirect(url);
        } catch (NamingException ex) {
            log("UpdateReportServlet_JNDI: " + ex.getMessage());
        } finally {
            out.close();
        }
    }

    private boolean validCost(String cost) {
        try {
            double c = Double.parseDouble(cost);
            if (c < 0 || c > 999999999.99) {
                return false;
            }
        } catch (NumberFormatException e) {
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
