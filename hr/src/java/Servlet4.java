/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;
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
public class Servlet4 extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Servlet4</title>");            
            out.println("</head>");
            out.println("<body background=\"C:\\Users\\Admin\\Pictures\\1.png\"><center>");
            String uname=request.getParameter("name");
            String p1=request.getParameter("pass1");
            String p2=request.getParameter("pass2");
          
            int status=1;
            if(!p1.equals(p2))
            {   
                out.println("Passwords don't match.Please enter again.");
                status=0;
            }
            else if(p1==""||p2==""||uname=="")
            {
                out.println("<br/><br/>All fields are compulsory.Please don't leave any field blank.");
                status=0;
            }
            if(status==0)
            {
                  out.println("<form action=\"Register\" method=\"get\">");
                out.println(" <br/><br/><input type=\"submit\" name=\"btnok\" value=\"OK\"> <br><form>");
            }
            
            if(status!=0)
            { 
            out.println("<form action=\"back\" method=\"get\">");
            out.println(" <br/><br/><input type=\"submit\" name=\"btnsubmit\" value=\"Return to Home\"> <br>");
             Connection conn = null;
		String url = "jdbc:mysql://localhost/";
		String dbName = "ajp";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "password";
                String str1;
                String str2;
                Statement stmt= null;
                String addServerURL = "rmi://localhost/AddServer";
                String a=request.getParameter("name");
                String b=request.getParameter("email");
                String c=request.getParameter("pass1");
                String d=request.getParameter("pass2");
                try
                {
                    Class.forName(driver).newInstance();
                      conn = DriverManager.getConnection(url+dbName,userName,password);
                      stmt = conn.createStatement();
                   /*    out.println("1");
                      String sq1="insert into user_list values(*3,'x','x');";
                       out.println("2");
                      int z=stmt.executeUpdate(sq1);
                       out.println("3");*/
                        String sql = "Select * from user_list;";
                    ResultSet rs1= stmt.executeQuery(sql);
                    while(!rs1.isLast()){ rs1.next(); }
                    int i=rs1.getInt("sno");
                   // out.println("last sno : "  + i);
                       
                          
                            int result;
                             
                           AddServerIntf addServerIntf =(AddServerIntf)Naming.lookup(addServerURL);
                           
                            result = addServerIntf.concat(i);
                            out.println("<br/><br/>Automatically Generated Unique-Id is:"+result);
                             
          
                         sql = "insert into user_list values("+result+",'"+uname+"','"+p1+"',null);";
                         // out.println(sql);
                      int rs = stmt.executeUpdate(sql);
                     
                        out.println("<br/>Successfully Registered!!!");
			conn.close();
                }
                catch(Exception e){}
            
            }
            out.println("</form></center></body>");
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
