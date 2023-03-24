/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pl.polsl.Servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pl.polsl.model.LottoBase;
import pl.polsl.model.LottoBaseException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;
import pl.polsl.model.SingleNumber;

/**
 * Servlet reading and printing data from database
 *
 * @author Piotr Benio
 * @version 4.0
 */
@WebServlet("/getFromFile")
public class GetDataFromDatabaseServlet extends HttpServlet {

    /**
     * This method request database from session (or creates one if it still
     * diesn't exist) and then tries to open file chosen by user. After that,
     * database elements are saved and presented to user in rows, six numbers
     * each just as winning sets. If any problems occures, a proper exception
     * will be cought and a message will be printed on page to inform user what
     * is wrong.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        HttpSession session = request.getSession(true);
        String fileName = request.getParameter("dataFile");
        PrintWriter out = response.getWriter();
        LottoBase model = new LottoBase();
        if ((LottoBase) session.getAttribute("database") != null) {
            model = (LottoBase) session.getAttribute("database");
        }
        ServletContext application = getServletConfig().getServletContext();
        String filePath = application.getRealPath(fileName);
        model.setFilename(filePath);
        try {
            model.getDataFromDatabase();
            ArrayList<SingleNumber> numbers = model.getAllDatabaseElements();

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
        } catch (LottoBaseException e) {
            out.println("<html><body><p>Inner base exception in model</p></body></html>");
        } catch (NullPointerException e) {
            out.println("<html><body><p>Database not found</p></body></html>");

        } catch (FileNotFoundException e) {
            out.println("<html><body><p>Database not found</p></body></html>");
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
            throws IOException, ServletException {

        HttpSession session = request.getSession(true);
        String fileName = request.getParameter("dataFile");
        PrintWriter out = response.getWriter();
        LottoBase model = new LottoBase();
        if ((LottoBase) session.getAttribute("database") != null) {
            model = (LottoBase) session.getAttribute("database");
        }
        ServletContext application = getServletConfig().getServletContext();
        String filePath = application.getRealPath(fileName);
        model.setFilename(filePath);
        try {
            model.getDataFromDatabase();
            ArrayList<SingleNumber> numbers = model.getAllDatabaseElements();

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
        } catch (LottoBaseException e) {
            out.println("<html><body><p>Inner base exception in model</p></body></html>");
        } catch (NullPointerException e) {
            out.println("<html><body><p>Database not found</p></body></html>");

        } catch (FileNotFoundException e) {
            out.println("<html><body><p>Database not found</p></body></html>");
        }
    }
}
