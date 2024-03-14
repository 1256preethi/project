package pbcloud;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
      String message=null;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html;charset=UTF-8");
		HttpSession http=request.getSession();
	     PrintWriter out = response.getWriter();
	     
	     String Uname = request.getParameter("AdminName");
	     String Upass = request.getParameter("AdminPassword");
	     
	     if(Uname.equals("admin") && Upass.equals("admin"))
	     {	    	 	
	    	 //message="Successfully Logined";
	    	 //http.setAttribute("msg", message);
		     RequestDispatcher rs = request.getRequestDispatcher("AdminHome.html");
		     rs.include(request, response);
		     //response.sendRedirect("LoginMessage.jsp");      
	     }
	     else
	     {
	    	out.println("<script>alert='Username or Password incorrect'</script>");
	        RequestDispatcher rs = request.getRequestDispatcher("AdminLogin.html");
	        rs.include(request, response);
	     }
	}
}
