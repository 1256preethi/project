package pbcloud;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/EditEmp")
public class EditEmp extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Map<Object, Object> map = new HashMap<>();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/cloud","root","root");  
		PreparedStatement ps=con.prepareStatement("Select * from regist where Emp_Id="+request.getParameter("empId"));
		ResultSet rs=ps.executeQuery();  
		if(rs.next()){
		
			map.put("Emp_Id", rs.getString("Emp_Id"));
			map.put("Emp_Name", rs.getString("Emp_Name"));
			map.put("Age", rs.getString("Age"));
			map.put("DOB", rs.getString("DOB"));
			map.put("Gender", rs.getString("Gender"));
			map.put("Ph_No", rs.getString("Ph_No"));
			map.put("Email_Id", rs.getString("Email_Id"));
			map.put("Address", rs.getString("Address"));
			map.put("Edu_Details", rs.getString("Edu_Details"));
			map.put("Exp_Details", rs.getString("Exp_Details"));
			map.put("Accnt_Details", rs.getString("Accnt_Details"));
			map.put("Cpny_Name", rs.getString("Cpny_Name"));
		}
		
		HttpSession http = request.getSession();
		http.setAttribute("emp_list", map);
		response.sendRedirect("editRes.jsp");
		}
		catch (Exception e2) 
		{
			System.out.println(e2);
		}           
		out.close();
	}

}
