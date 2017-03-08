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
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.common.AccountObj;
import sample.tbl_account.tbl_accountSessionBeanRemote;

/**
 *
 * @author PhucTDSE61834
 */
public class LoginServlet extends HttpServlet {

    private final String loginPage = "login.jsp";
    private final String managerPage = "manager.jsp";
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
            String url = loginPage;
            String loginErr = "";
            String username = request.getParameter("txtUsername");
            String password = request.getParameter("txtPassword");

            Context context = new InitialContext();
            Object obj = context.lookup("AccountRemote");
            tbl_accountSessionBeanRemote poji = (tbl_accountSessionBeanRemote) obj;
            int role = poji.checkLogin(username, password);
            if (role >= 0) {
                if (role == 1) {
                    url = staffPage;
                    AccountObj accObj = new AccountObj(username, role);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("ACCOUNT", accObj);
                } else if (role == 3) {
                    url = managerPage;
                    AccountObj accObj = new AccountObj(username, role);
                    HttpSession session = request.getSession(true);
                    session.setAttribute("ACCOUNT", accObj);
                } else {
                    request.setAttribute("LoginErr", "Access Denied !");
                }
            } else {
                request.setAttribute("LoginErr", "Invalid username or password !");
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (NamingException ex) {
            log("LoginServlet_JNDI: " + ex.getMessage());
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
