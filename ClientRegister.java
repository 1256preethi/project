package pbcloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ClientRegister
 */
@WebServlet("/ClientRegister")
public class ClientRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		
		HttpSession ses=request.getSession();
		String message=null;
		
		String a=request.getParameter("Client_Id");
		String b=request.getParameter("ClientName");  
		String c=request.getParameter("CompanyName"); 
		String d=request.getParameter("Email_Id");
		String e=request.getParameter("Contact_No");
		
		          
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/cloud","root","root");  
		  
		PreparedStatement ps=con.prepareStatement("insert into clientdetails(Client_Id,ClientName,CompanyName,Email_Id,Contact_No) values(?,?,?,?,?)");  
		
		ps.setString(1,a);
		ps.setString(2,b); 
		ps.setString(3,c);   
		ps.setString(4,d);
		ps.setString(5,e);
		          
		int i=ps.executeUpdate();  
		if(i>0)  
		{
		 message="Successfully Registered...";
		 ses.setAttribute("msg",message);
		 response.sendRedirect("ClientRegisterMessage.jsp");
		}     
		          
		}catch (Exception e2) {System.out.println(e2);}  
		          
		out.close();
	}

}
