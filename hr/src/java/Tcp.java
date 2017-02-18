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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author lenovo
 */
public class Tcp {
      public static void main(String[] args) throws IOException {
        // TODO code application logic here
        try{
                
            
        ServerSocket listener = new ServerSocket(9019);
    Socket s = listener.accept();
                BufferedReader input =
            new BufferedReader(new InputStreamReader(s.getInputStream()));
        String username = input.readLine();
          String pass = input.readLine();
        
        Connection conn = null;
		String url = "jdbc:mysql://localhost/";
		String dbName = "ajp";
        
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root"; 
		String password = "password";
		try {
 
			Class.forName(driver).newInstance();
			conn = DriverManager.getConnection(url+dbName,userName,password);
             
			
     Statement st = conn.createStatement();
String sql = ("SELECT * FROM user_list;");

ResultSet rs = st.executeQuery(sql);
int j=0;
 
while(rs.next())
{ 
 String name = rs.getString("uname"); 
 String pass2 = rs.getString("password");

 if(name.compareTo(username)== 0 && pass2.compareTo(pass) == 0)
 { j=1;
     PrintWriter in =new PrintWriter(s.getOutputStream(), true);
                    in.println("success");
       conn.close();
     exit(1);
 }
 

}
if(j==0)
{
      PrintWriter in =
                        new PrintWriter(s.getOutputStream(), true);
                    in.println("fail");
       conn.close();
     exit(1);
 
}


conn.close();
	  
                        		} catch (Exception e)
                                            
                {
		
		}
        
        }finally {
           
        }
        
              } 

}