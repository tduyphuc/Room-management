/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import sample.tbl_room.tbl_roomSessionBeanRemote;

/**
 *
 * @author PhucTDSE61834
 */
public class SearchRoomServlet extends HttpServlet {

    private final String managerPage = "manager.jsp";

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
            String value = request.getParameter("txtSearchValue");
            String url = managerPage;

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
            } else if (value == null) {
                url = "ErrorPage/401.html";
            } else if (role != 3) {
                url = "ErrorPage/403.html";
            } else {
                value = value.trim();
                if (!value.equals("")) {
                    Context context = new InitialContext();
                    Object obj = context.lookup("RoomRemote");
                    tbl_roomSessionBeanRemote poji = (tbl_roomSessionBeanRemote) obj;
                    List result = poji.searchRoom(value);
                    request.setAttribute("SEARCHROOM", result);
                } else {
                    request.setAttribute("emptyValueErr", "Please fill in search bar");
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        } catch (NamingException ex) {
            log("SearchRoomServlet_JNDI: " + ex.getMessage());
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
