package pbcloud;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/insource")
public class insource extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		try
		{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cloud","root","root");  
			PreparedStatement ps=con.prepareStatement("Select * from regist where Emp_Id="+request.getParameter("empId"));
			ResultSet rs=ps.executeQuery();  
			if(rs.next())
			{
				PreparedStatement ps1=con.prepareStatement("insert into insourcereg(Emp_Id,Emp_Name,Cpny_Name) values(?,?,?)");
				ps1.setString(1,rs.getString("Emp_Id")); 
				ps1.setString(2,rs.getString("Emp_Name")); 
				ps1.setString(3,rs.getString("Cpny_Name")); 
				ps1.executeUpdate(); 
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
