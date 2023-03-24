/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
 * Servlet responsible for generating new random winning sets and printing them.
 *
 * @author Piotr Benio
 * @version 4.0
 */
@WebServlet("/showRandomData")
public class ShowRandomSetsServlet extends HttpServlet {

    /**
     * Method gets information from user about how many winning sets are to be
     * generted. Then, method request database from session (or creates one if
     * it still diesn't exist), generates new winning numbers and adds them to
     * main database. New generated sets are also shown to user. If any problems
     * occures, a proper exception will be cought and a message will be printed
     * on page to inform user what is wrong.
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
            int amount = Integer.parseInt(request.getParameter("amount"));
            LottoBase model = new LottoBase();
            if ((LottoBase) session.getAttribute("database") != null) {
                model = (LottoBase) session.getAttribute("database");
            }

            model.DoNewRandomWinningSetsProcedure(amount);
            ArrayList<SingleNumber> numbers = model.getAllDatabaseElements();

            int counter = 0; //to make sure only new numbers will be visible
            int border = numbers.size() - (amount * 6);
            out.println("<html><body> <h1>Numbers generated succesfully! </h1><br>");
            out.println("<html><body> <hr> </h1><br>");
            out.println("<html><body> <h2>New Lotto Numbers: </h1><br>");
            String numberString = " ";
            for (int i = 0; i < numbers.size(); i++) {
                for (int x = 0; x < 6; x++) {
                    numberString += Integer.toString(numbers.get(i).getNumber()) + " ";
                    i++;
                    counter++;
                }
                if (counter > border) {
                    out.print("<p>" + numberString + "</p></body></html>");
                }
                numberString = "";
                i--;
            }
            session.setAttribute("database", model);
        } catch (java.lang.NumberFormatException e) {
            out.println("<p>Value should be a number!</p></body></html>");
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
            int amount = Integer.parseInt(request.getParameter("amount"));
            LottoBase model = new LottoBase();
            if ((LottoBase) session.getAttribute("database") != null) {
                model = (LottoBase) session.getAttribute("database");
            }

            model.DoNewRandomWinningSetsProcedure(amount);
            ArrayList<SingleNumber> numbers = model.getAllDatabaseElements();

            int counter = 0; //to make sure only new numbers will be visible
            int border = numbers.size() - (amount * 6);
            out.println("<html><body> <h1>Numbers generated succesfully! </h1><br>");
            out.println("<html><body> <hr> </h1><br>");
            out.println("<html><body> <h2>New Lotto Numbers: </h1><br>");
            String numberString = " ";
            for (int i = 0; i < numbers.size(); i++) {
                for (int x = 0; x < 6; x++) {
                    numberString += Integer.toString(numbers.get(i).getNumber()) + " ";
                    i++;
                    counter++;
                }
                if (counter > border) {
                    out.print("<p>" + numberString + "</p></body></html>");
                }
                numberString = "";
                i--;
            }
            session.setAttribute("database", model);
        } catch (java.lang.NumberFormatException e) {
            out.println("<p>Value should be a number!</p></body></html>");
        }
    }
}
