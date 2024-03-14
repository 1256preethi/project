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

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.fastinfoset.sax.Properties;


@WebServlet("/ViewRequest")
public class ViewRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Map map = new HashMap<>();
		List list = new ArrayList<>();
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/cloud","root","root");  
		PreparedStatement ps=con.prepareStatement("Select * from requestdetails where Status='Inprogress'");
		ResultSet rs=ps.executeQuery();  
		while(rs.next())
		{
			map = new HashMap<>();
			
			map.put("Emp_Id", rs.getString("Emp_Id"));
			map.put("Emp_Name", rs.getString("Emp_Name"));
			map.put("Client_Id", rs.getString("Client_Id"));
			map.put("ClientName", rs.getString("ClientName"));
			map.put("CompanyName", rs.getString("CompanyName"));
			map.put("Email_Id", rs.getString("Email_Id"));
			list.add(map);
		}		
		HttpSession http = request.getSession();
		http.setAttribute("req_list", list);
		response.sendRedirect("ViewReq.jsp");
		}
		catch (Exception e2) 
		{
			System.out.println(e2);
			e2.printStackTrace();
		}           
		out.close();
		
	}

}
