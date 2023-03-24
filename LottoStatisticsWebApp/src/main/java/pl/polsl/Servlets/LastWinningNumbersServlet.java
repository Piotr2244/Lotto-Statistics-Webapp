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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.model.LottoBase;
import pl.polsl.model.SingleNumber;

/**
 * Servlet printing last winning numbers
 *
 * @author Piotr Benio
 * @version 4.0
 */
@WebServlet("/showLastWinningNumbers")
public class LastWinningNumbersServlet extends HttpServlet {

    /**
     * Method gets the database from session and prints last winning numbers
     * set. If there are not any sets yet, nothing except title would be
     * printed.
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
        LottoBase model = new LottoBase();
        if ((LottoBase) session.getAttribute("database") != null) {
            model = (LottoBase) session.getAttribute("database");
        }

        try {

            out.println("<html><body> <h1>Last winning numbers: </h1><br>");
            String output = " ";
            ArrayList<SingleNumber> winningNumbers = model.getWinningNumbers();

            for (int i = 0; i < 6; i++) {
                output += ((winningNumbers.get(i)).getNumber()) + " ";
            }
            out.print("<p>" + output + "</p></body></html>");

            session.setAttribute("database", model);
        } catch (IndexOutOfBoundsException e) {
            out.println(" <h1>Not any generated sets yet!</h1>");
        }
    }

}
