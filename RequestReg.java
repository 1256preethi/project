package pbcloud;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RequestReg")
public class RequestReg extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		
		HttpSession ses=request.getSession();
		
		String message=null;
		
		String a=request.getParameter("Emp_Id");
		String b=request.getParameter("Emp_Name");
		String c=request.getParameter("Client_Id");
		String d=request.getParameter("ClientName");
		String e=request.getParameter("CompanyName");
		String f=request.getParameter("Email_Id");
	
		          
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/cloud","root","root");  
		
		PreparedStatement ps=con.prepareStatement("insert into requestdetails(Emp_Id,Emp_Name,Client_Id,ClientName,CompanyName,Email_Id,Status) values(?,?,?,?,?,?,?)");  
		  
		ps.setString(1,a); 
		ps.setString(2,b); 
		ps.setString(3,c);
		ps.setString(4,d);  
		ps.setString(5,e);  
		ps.setString(6,f);
		ps.setString(7,"Inprogress");
		
		int i=ps.executeUpdate();  
		if(i>0) 
			
		message="Request send successfully..."; 
		ses.setAttribute("msg",message);
		RequestDispatcher rs=request.getRequestDispatcher("RequestMessage.jsp");
		rs.include(request,response);
		  
		}
		catch (Exception e2) 
		{
			//System.out.println(e2);
			e2.printStackTrace();
		}           
		out.close();
	}

}
