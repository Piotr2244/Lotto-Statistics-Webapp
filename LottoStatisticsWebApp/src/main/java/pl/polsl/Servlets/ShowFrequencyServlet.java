/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import pl.polsl.model.LottoBase;

/**
 * Servlet shows information about numbers frequency and percentage
 *
 * @author Piotr Benio
 * @version 4.0
 */
@WebServlet("/showFrequency")
public class ShowFrequencyServlet extends HttpServlet {

    /**
     * Method gets database from session and then prints information about all
     * numbers, their frequency and percentage. Informations about every number
     * are shown in distinct row.
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

        HashMap<Integer, Integer> frequency = model.getFrequency();
        HashMap<Integer, Integer> percents = model.getPercents();

        PrintWriter out = response.getWriter();

        out.println("<html><body> <h1>Lotto Numbers: </h1><br>");
        String output;
        for (Integer i : frequency.keySet()) {
            output = ("Number: " + i + " Frequency: " + frequency.get(i) + " percent: " + percents.get(i) + "%");
            out.print("<p>" + output + "</p></body></html>");
        }
        session.setAttribute("database", model);
    }
}
