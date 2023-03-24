/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package pl.polsl.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.model.LottoBase;
import pl.polsl.model.SingleNumber;

/**
 * This servlet represents simple admin panel.
 *
 * @author Piotr Benio
 * @version 4.0
 */
@WebServlet("/adminLogin")
public class AdminPanelServlet extends HttpServlet {

    /**
     * After typing correct password, user can check how many new sets have been
     * added since the last visit in this panel. Cookies stores data about
     * database sizes and provides to calculate new sets since last visit. Also
     * informations about all sets and all numbers amount are showed in this
     * panel.
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

        try {
            if (!session.getAttribute("password").equals(request.getParameter("password"))) {
                out.println("<p>Incorrect password</p></body></html>");
            } else {
                out.println("<html><body> <h1>Admin Panel </h1><br>");
                LottoBase model = new LottoBase();
                if ((LottoBase) session.getAttribute("database") != null) {
                    model = (LottoBase) session.getAttribute("database");
                }
                ArrayList<SingleNumber> numbers = model.getAllDatabaseElements();
                Cookie[] cookies = request.getCookies();
                int allSetsAmount = 0;
                int lastValue = 0;
                if (cookies != null) {
                    allSetsAmount = ((numbers.size()) / 6);

                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("newSetsAmount")) {

                            lastValue = Integer.parseInt(cookie.getValue());
                        }
                    }
                    if (lastValue < 0) {
                        out.println("New sets since your last visit as admin: 0");
                    } else {
                        out.println("<p>" + "New sets since Your last visit as admin: " + (allSetsAmount - lastValue) + "</p></body></html>");
                    }
                    out.println("<p>" + "All sets in database:" + numbers.size() / 6 + "</p></body></html>");
                    out.println("<p>" + "All Numbers amount in database: " + numbers.size() + "</p></body></html>");
                    Cookie cookie = new Cookie("newSetsAmount", String.valueOf(allSetsAmount));
                    response.addCookie(cookie);
                }
            }
        } catch (NullPointerException e) {
            out.println("Create password firstly!");
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

        try {
            if (!session.getAttribute("password").equals(request.getParameter("password"))) {
                out.println("<p>Incorrect password</p></body></html>");
            } else {
                out.println("<html><body> <h1>Admin Panel </h1><br>");
                LottoBase model = new LottoBase();
                if ((LottoBase) session.getAttribute("database") != null) {
                    model = (LottoBase) session.getAttribute("database");
                }
                ArrayList<SingleNumber> numbers = model.getAllDatabaseElements();
                Cookie[] cookies = request.getCookies();
                int allSetsAmount = 0;
                int lastValue = 0;
                if (cookies != null) {
                    allSetsAmount = ((numbers.size()) / 6);

                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("newSetsAmount")) {

                            lastValue = Integer.parseInt(cookie.getValue());
                        }
                    }
                    if (lastValue < 0) {
                        out.println("New sets since your last visit as admin: 0");
                    } else {
                        out.println("New sets since your last visit as admin: " + (allSetsAmount - lastValue));
                    }
                    Cookie cookie = new Cookie("newSetsAmount", String.valueOf(allSetsAmount));
                    response.addCookie(cookie);
                }
            }
        } catch (NullPointerException e) {
            out.println("Firstly create  password!");
        }
    }
}
