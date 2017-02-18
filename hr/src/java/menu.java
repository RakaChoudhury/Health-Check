/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class menu extends HttpServlet {

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
        String pass=request.getParameter("menu1");    
       String user=request.getParameter("user");
            
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>menu</title>");            
            out.println("</head>");
            out.println("<body background=\"C:\\Users\\Admin\\Pictures\\1.png\"><center>");
            
             if("1".equals(pass))
        {
            out.println("<h1 align=\"center\">Calorie Calculator</H1>");
            out.println("<form action=\"disp\" method=\"get\"><center>");
            out.println("<br /><br />Name: <input type=\"text\" name=\"name\">  <br><br />");
             out.println(" Gender: <input type=\"radio\" name=\"sex\" value=\"male\">Male" +
"<input type=\"radio\" name=\"sex\" value=\"female\">Female<br/><br />");
            
            out.println("Weight: <input type=\"text\" name=\"weight\"> Kilograms <br><br />");
            out.println("Height: <input type=\"text\" name=\"height\"> Centimeters <br><br />");
            out.println("Activity: <select name=\"activity\"><option>Basal Metabolic Rate</option><option>Sedentry - Little or No Exercise</option><option>Lightly Active-Exercise/Sports-1 to 3 times a week</option><option>Moderately Active-Exercise/Sports-3 to 5 times a week</option><option>Very Active-Exercise/Sports-6 to 7 times a week</option><option>"
                    + "Extra Active-very hard Exercise or Physical Job</option></select><br />");
           
            out.println("<br /><input type=\"submit\" name=\"Submit\" value=\"Calculate\"><br /><br /></center></form>");
            
        }
               if("2".equals(pass))
        {
            out.println("<h1 align=\"center\">Exercise Planner</H1>");
             out.println("<form action=\"xcise\" method=\"get\"><center>");
              out.println(" <br /><br />Select action: <input type=\"radio\" name=\"act\" value=\"maintain\">Maintain" +
"<input type=\"radio\" name=\"act\" value=\"lose\">Lose<input type=\"radio\" name=\"act\" value=\"gain\">Gain<br/><br />");
               out.println("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
         out.println("<br /><br /><input type=\"submit\" name=\"Submit\" value=\"Plan\"><br /><br /></center></form>");
        }
      
            out.println("</center></body>");
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
