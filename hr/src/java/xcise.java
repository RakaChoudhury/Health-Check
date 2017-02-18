/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Admin
 */
public class xcise extends HttpServlet {

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
             String act=request.getParameter("act");
             
             String user=request.getParameter("user");
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet xcise</title>");            
            out.println("</head>");
            out.println("<body background=\"C:\\Users\\Admin\\Pictures\\1.png\">");
             Connection conn = null;
		String url = "jdbc:mysql://localhost/";
		String dbName = "ajp";
        
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "password";
                String sql="";
                ResultSet rs;
                 out.println("<form action=\"plnr\" method=\"get\"><center>");
             if(act.equalsIgnoreCase("lose"))
                out.println("Enter no. of kgs you want to lose: <input type=\"text\" name=\"wt\"> Kgs <br /><br />");
            else if(act.equalsIgnoreCase("gain"))
                out.println("Enter no. of kgs you want to gain: <input type=\"text\" name=\"wt\"> Kgs <br /><br />");
            else out.println("<input type=\"hidden\" name=\"wt\" value=\"0\" />");
            out.println("</center><h4>Now enter your diet from the below given list of common foods</h4>");
            user=user+":"+act;
            
             out.println("<input type=\"hidden\" name=\"user\" value=\""+user+"\" />");
             //out.println("<input type=\"hidden\" name=\"act\" value=\""+act+"\" />");
             
           try {
 
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
                        Statement st = conn.createStatement();
                        sql = ("select * from common_foods;");
                         rs = st.executeQuery(sql);
                          int j=0;
                          while (rs.next())
                         {   out.println("<input type=\"checkBox\" name=\"foodc\" value=\""+j+"\">"+rs.getString("food")+"<br><br/>");
                            j++;
                            }
                 
                        conn.close();
                }
                catch(Exception e)
                { 
                }
            //out.println("<input type=\"hidden\" name=\"act\" value=\""+act+"\" />");
            out.println("<br /><br /><input type=\"submit\" name=\"Submit\" value=\"Proceed\"><br /><br /></form>");
            out.println("</body>");
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
