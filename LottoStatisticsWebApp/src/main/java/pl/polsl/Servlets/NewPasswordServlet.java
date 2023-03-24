/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * servlet saves new admin password and informs if this procedure ended with
 * success.
 *
 * @author Piotr Benio
 * @version 4.0
 */
@WebServlet("/newPassword")
public class NewPasswordServlet extends HttpServlet {

    /**
     * Method gets new password from user and saves it in session. If any
     * problem occures, error message will be shown.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        String newPassword;
        try {
            newPassword = request.getParameter("newPassword");
            session.setAttribute("password", newPassword);
            out.println("<html><body><p>Password changed succesfully!</p></body></html>");
        } catch (Exception e) {
            out.println("<html><body><p>Failed to create new password</p></body></html>");
        }

    }

    /**
     * doPost version of previous doGet method, does exacly the same
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        PrintWriter out = response.getWriter();
        String newPassword;
        try {
            newPassword = request.getParameter("newPassword");
            session.setAttribute("password", newPassword);
            out.println("<html><body><p>Password changed succesfully!</p></body></html>");
        } catch (Exception e) {
            out.println("<html><body><p>Failed to create new password</p></body></html>");
        }

    }

}
