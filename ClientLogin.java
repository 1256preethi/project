package pbcloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ClientLogin")
public class ClientLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	     PrintWriter out = response.getWriter();
	     HttpSession ses = request.getSession();
	    //String message=null; 
	     String Uname = request.getParameter("ClientName");
	     String Upass = request.getParameter("Client_Id");
	     try{
	     //loading drivers for mysql
         Class.forName("com.mysql.jdbc.Driver");
         //creating connection with the database
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloud","root","root");
         PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from ClientDetails where ClientName=? and Client_Id=?");
         ps.setString(1, Uname);
         ps.setString(2, Upass);
         ResultSet rs =(ResultSet) ps.executeQuery();
         if(rs.next())
         {
        	// message="Successfully Login";
        	 ses.setAttribute("clientname",rs.getString("ClientName"));
        	 ses.setAttribute("clientId", rs.getString("Client_Id"));
        	 //ses.setAttribute("msg", message);
        	// response.sendRedirect("ClientLoginMessage.jsp");
        	 RequestDispatcher rs1 = request.getRequestDispatcher("ClientHome.jsp");
        	 rs1.include(request, response);
         }
         else
         {
        	 out.println("Username or Password incorrect");
        	 ses.setAttribute("clientId", "");
        	 RequestDispatcher rs2 = request.getRequestDispatcher("ClientLog.jsp");
        	 rs2.include(request, response);
         }
	     }
	     catch(Exception e)
	     {
	    	 e.printStackTrace();
	     }
	}


}
