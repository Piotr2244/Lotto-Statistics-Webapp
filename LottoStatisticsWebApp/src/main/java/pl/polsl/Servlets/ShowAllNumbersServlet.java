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
 * Servlet to show all winning sets in database.
 *
 * @author Piotr Benio
 * @version 4.0
 */
@WebServlet("/showAllNumbers")
public class ShowAllNumbersServlet extends HttpServlet {

    /**
     * Method gets database from session and then prints it in rows, six numbers
     * each to show them as winning sets
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

        LottoBase model = new LottoBase();
        if ((LottoBase) session.getAttribute("database") != null) {
            model = (LottoBase) session.getAttribute("database");
        }

        ArrayList<SingleNumber> numbers = model.getAllDatabaseElements();
        PrintWriter out = response.getWriter();

        out.println("<html><body> <h1>Lotto Numbers: </h1><br>");
        String numberString = " ";
        for (int i = 0; i < numbers.size(); i++) {
            for (int x = 0; x < 6; x++) {
                numberString += Integer.toString(numbers.get(i).getNumber()) + " ";
                i++;
            }
            out.print("<p>" + numberString + "</p></body></html>");
            numberString = "";
            i--;
        }
        session.setAttribute("database", model);
    }
}
