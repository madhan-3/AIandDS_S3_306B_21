import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class login_servlet extends HttpServlet
{
  public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
    System.out.println("login service called...");
    PrintWriter out = res.getWriter();
    res.setContentType("text/html");
    String mail = req.getParameter("mail");
    String pwd = req.getParameter("pword");
    try{
      Class.forName("oracle.jdbc.driver.OracleDriver");
      Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","jonnadakumar0");
      System.out.println("HTML DETAILS : "+mail+" "+pwd);
      String vsql = "select * from user_log where mail=? and pword=?";

      PreparedStatement pstmt = con.prepareStatement(vsql);
      pstmt.setString(1,mail);
      pstmt.setString(2,pwd);
      ResultSet rs = pstmt.executeQuery();
        
      if( rs.next() ){
        System.out.println("Entered User details are existed in the database");
        RequestDispatcher rd = req.getRequestDispatcher("college.html");
        rd.forward(req, res);
      }
      out.println("</body>");
      out.println("<html>");
      con.close();
    }catch(Exception e){
      res.sendError(500,"Our application is failed due to:" + e);
    }
  }
}