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
public class exer_plan extends HttpServlet {

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
        String b=request.getParameter("fds");
       
        String[] d=request.getParameter("user").split(":");
         String act=d[1];
        int wts=Integer.parseInt(request.getParameter("wts"));
        String[] c=b.split(":");
         Connection conn = null;
		String url = "jdbc:mysql://localhost/";
		String dbName = "ajp";
              String user=d[0];
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "password";
                String sql="";
                ResultSet rs,rs1;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet exer_plan</title>");            
            out.println("</head>");
            out.println("<body background=\"C:\\Users\\Admin\\Pictures\\1.png\"><center>");
            int val,val2,cal=0;
             try {
 
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
                        Statement st = conn.createStatement();
                        for(int z=0;z<c.length;z++)
                        {   sql = ("select * from common_foods where food like '"+c[z]+"';");
                            rs = st.executeQuery(sql);
                            val=Integer.parseInt(request.getParameter(c[z]));
                            rs.first();
                            val2=rs.getInt("calories");
                            cal+=val*val2;
                            //out.println("cal="+cal);  
                        }
                        out.println("You have consumed "+cal+" calories");
                        sql = ("select * from user_list where uname like '"+user+"';");
                        
                            rs1 = st.executeQuery(sql);
                            rs1.first();
                            val=rs1.getInt("maintain");
                           
                          
                            if (act.equalsIgnoreCase("lose"))
                            val=val-1000*wts;
                            else if (act.equalsIgnoreCase("gain"))
                            val=val+1000*wts;
                           
                           
                            val2=val-cal;
                            
                        
                        
                        
                        if(val2>0)
                            out.println("<br /><br />You need to eat more to "+act+" your weight!! Forget exercising");
                        else
                        {   val2=-1*val2;
                            out.println("<br /><br />You need to eat "+val2+" calories/day less to "+act+" your intended weight!!  Exercise and burn extra calories");
                        }
                        
                        out.println("<h3>You may chose from the following exercises</h3>");
                        out.println("<h4>The table gives a few common exercises with their corresponding hourly calorie burning rates. Choose the value that is closest to your weight!!</h4>");
                        sql = ("select * from exercises;");
                        out.println("<table border=\"1\">");
                        out.println("<thead><td>Activity</td><td>62.5 Kg person</td><td>77.5 Kg</td><td>92.5Kg</td></thead>");
                            rs1 = st.executeQuery(sql);
                            rs1.first();
                            while(rs1.next())
                            {
                                out.println("<tr><td>"+rs1.getString("activity")+"</td><td>"+rs1.getString("type1")+"<td>"+rs1.getString("type2")+"</td><td>"+rs1.getString("type3")+"</td></tr>");
                            }
                            out.println("</table>");
                      //  out.println("<input type=\"hidden\" name=\"fds\" value=\""+b+"\" />");
                        conn.close();
             }
             
             catch(Exception e)
             {
             }
             // out.println("<br /><br /><input type=\"submit\" name=\"Submit\" value=\"Plan Exercise\"><br /><br />");
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
