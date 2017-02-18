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
public class plnr extends HttpServlet {

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
        int wt=Integer.parseInt(request.getParameter("wt"));
        String u1=request.getParameter("user");
   
        String user=request.getParameter("user");
        String act=request.getParameter("act");
       
             Connection conn = null;
		String url = "jdbc:mysql://localhost/";
		String dbName = "ajp";
              
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "password";
                String sql="";
                ResultSet rs;
             int j=0;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet plnr</title>");            
            out.println("</head>");
            out.println("<body background=\"C:\\Users\\Admin\\Pictures\\1.png\">");
             out.println("<form action=\"exer_plan\" method=\"get\"><center>");
            
             out.println("Now enter the quantity of the foods you selected.<br /><br />");
              try {
 
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
                        Statement st = conn.createStatement();
                        sql = ("select * from common_foods;");
                         rs = st.executeQuery(sql);
                         String a="",b="";int d=0;
                             String[] results = request.getParameterValues("foodc");
                             for (int i = 0; i < results.length; i++) 
                             {  rs.absolute(Integer.parseInt(results[i])+1);
                             d=Integer.parseInt(results[i])+1;
                             if ((d==8)||(d==9)||(d==10)||(d==12)||(d==14)||(d==15)||(d==17)||(d==18)||(d==28)||(d==29)||(d==30))
                                 a="cup";
                                 b=b.concat(rs.getString("food"));
                                 b=b.concat(":");
                                out.println(rs.getString("food")+"-<input type=\"text\" name=\""+rs.getString("food")+"\"> "+a+" <br /><br />");
                                
                             }
                             out.println("<input type=\"hidden\" name=\"fds\" value=\""+b+"\" />");
                             out.println("<input type=\"hidden\" name=\"user\" value=\""+u1+"\" />");
                             out.println("<input type=\"hidden\" name=\"wts\" value=\""+wt+"\" />");
                           conn.close();
             }
             
             catch(Exception e)
             {
             }
               out.println("<input type=\"hidden\" name=\"act\" value=\""+act+"\" />");
              out.println("<br /><br /><input type=\"submit\" name=\"Submit\" value=\"Plan Exercise\"><br /><br />");
            out.println("</center></form></body>");
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
