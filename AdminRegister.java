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

@WebServlet("/AdminRegister")
public class AdminRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		
		String a=request.getParameter("AdminName");
		String b=request.getParameter("AdminPassword");
		          
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/cloud","root","root");  
		PreparedStatement ps=con.prepareStatement("insert into AdminDetails values(?,?)");  
		  
	  
		ps.setString(1,a);    
		ps.setString(2,b); 
				          
		int i=ps.executeUpdate();  
		if(i>0)  
		out.print("You are successfully registered...");  
		  
		}catch (Exception e2) {System.out.println(e2);}  
		          
		out.close();
	}
}
