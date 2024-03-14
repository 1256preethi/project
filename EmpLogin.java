package pbcloud;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/EmpLogin")
public class EmpLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	     PrintWriter out = response.getWriter();
	     HttpSession ses = request.getSession();
	     String message=null;
	     String Uname = request.getParameter("Emp_Name");
	     String Upass = request.getParameter("Emp_Id");
	     try{
	     //loading drivers for mysql
        Class.forName("com.mysql.jdbc.Driver");
        //creating connection with the database
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloud","root","root");
        PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from regist where Emp_Name=? and Emp_Id=?");
        ps.setString(1, Uname);
        ps.setString(2, Upass);
        ResultSet rs =(ResultSet) ps.executeQuery();
        if(rs.next())
        {
       	 //message="Successfully Login";
       	 ses.setAttribute("empId", rs.getString("Emp_Id"));
       	 //ses.setAttribute("msg",message);
       	 //response.sendRedirect("EmpLoginMessage.jsp");
       	RequestDispatcher rs1 = request.getRequestDispatcher("EmpHome.jsp");
       	rs1.forward(request, response);
        }
        else
        {
       	 ses.setAttribute("empId", "");
       	 RequestDispatcher rs2 = request.getRequestDispatcher("EmpLog.jsp");
       	 out.println("Username or Password incorrect");
       	 rs2.include(request, response);
        }
	     }
	     catch(Exception e)
	     {
	    	 e.printStackTrace();
	     }
	}
}
