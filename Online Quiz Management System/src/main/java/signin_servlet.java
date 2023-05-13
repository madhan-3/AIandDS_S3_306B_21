import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class signin_servlet extends HttpServlet
{
  public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
    System.out.println("register service called...");
    res.setContentType("text/html");
    String uname = req.getParameter("mail");
    String pword = req.getParameter("pword");
    System.out.println("Mail:"+uname+"            Password"+pword);
    try {
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","jonnadakumar0");
      System.out.println("DB Connected");
      String query = "INSERT INTO user_log (mail, pword) VALUES (?, ?)";
      PreparedStatement stmt = con.prepareStatement(query);
      stmt.setString(1, uname);
      stmt.setString(2, pword);
      stmt.executeUpdate();
      con.close();
      res.sendRedirect("user.html");
    }catch(Exception e)
    {
    	System.out.println("DB Connection problem");}
    }
    
  }
