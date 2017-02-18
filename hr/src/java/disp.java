/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.exit;
import java.net.ServerSocket;
import java.net.Socket;
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
public class disp extends HttpServlet {

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
   
        String name=request.getParameter("name"); 
        String gender=request.getParameter("sex");    
        String weight=request.getParameter("weight"); 
        String height=request.getParameter("height"); 
        String activity=request.getParameter("activity");
        
        float w = Float.parseFloat(weight);
        float h = Float.parseFloat(height);
        double lbm=0,bmr=0;
        if (gender.equalsIgnoreCase("male"))
           lbm=(0.32810*w)+(0.33929*h)-29.5336;
        else if (gender.equalsIgnoreCase("female"))
            lbm=(0.29569*w)+(0.41813*h)-43.2933;
        bmr=500+(22*lbm);
        double maintain=0;
        if(!activity.equalsIgnoreCase("Basal Metabolic Rate"))
        { 
        if (activity.equalsIgnoreCase("Sedentry - Little or No Exercise"))
             maintain=bmr*1.1997;
        else if (activity.equalsIgnoreCase("Lightly Active-Exercise/Sports-1 to 3 times a week"))
            maintain=bmr*1.3750;
        else if (activity.equalsIgnoreCase("Moderately Active-Exercise/Sports-3 to 5 times a week"))
            maintain=bmr*1.5497;
        else if (activity.equalsIgnoreCase("Very Active-Exercise/Sports-6 to 7 times a week"))
            maintain=bmr*1.7251;
        else if (activity.equalsIgnoreCase("Extra Active-very hard Exercise or Physical Job"))
            maintain=bmr*1.8998;
        }
        else maintain = -1; 
        
         Connection conn = null;
		String url = "jdbc:mysql://localhost/";
		String dbName = "ajp";
        
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "password";
                String sql="";
		try {
 
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
                        Statement st = conn.createStatement();
                        sql = ("UPDATE user_list SET maintain="+maintain+" where uname like '"+name+"';");
                        
                        int rs = st.executeUpdate(sql);
                        int j=0;
                        conn.close();
                }
                catch(Exception e)
                { 
                }
                
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet disp</title>");            
            out.println("</head>");
            out.println("<body background=\"C:\\Users\\Admin\\Pictures\\1.png\">");
            out.println("<center><h3>Your BMR is "+bmr+"</h3></ center>");
           if (maintain!=-1)
           {
               out.println("<center><h3>You need to have "+maintain+" cal/day to maintain your weight<br />");
               out.println("You need to have "+(maintain+1000)+" cal/day to gain 1kg/week <br />");
               out.println("You need to have "+(maintain-1000)+" cal/day to lose 1kg/week<br /></h3></center>");
               
           }
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
