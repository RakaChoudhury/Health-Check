/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");            
            out.println("</head>");
            out.println("<body background=\"C:\\Users\\Admin\\Pictures\\1.png\"><center>");
           // out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
            String user=request.getParameter("user");
            String pass=request.getParameter("pass");
            Socket socket = new Socket("localhost",9019);
            
                   
                    PrintWriter in =
                        new PrintWriter(socket.getOutputStream(), true);
         in.println(user);
         in.println(pass);
        
         BufferedReader input =
            new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String answer = input.readLine();
     
       
        if(answer.equals("success"))
        {
           // out.println("<h1>hurrae</h1>");
            out.println("<br/><br/><h1>Welcome "+user+"</h1>");
                           out.println("<br/><br/><form action=\"menu\" method=\"get\">");
                          out.println("<input type=\"radio\" name=\"menu1\" value=\"1\">Calorie Calculator<br/>");
                           out.println("<br/><input type=\"radio\" name=\"menu1\" value=\"2\">Exercise Planner<br/>");
                           out.println("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
                           out.println("<br/><input type=\"submit\" value=\"Select\">");
                          out.println("</form>");
        }
        else
            out.println("<h1>invalid</h1>");
            out.println("<center></body>");
            out.println("</html>");
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
