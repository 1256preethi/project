package pbcloud;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String path = null;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{  
		
		response.setContentType("text/html;charset=UTF-8");
	     PrintWriter out = response.getWriter();
	     
	     String empid = request.getParameter("Emp_Id");
	     String key = request.getParameter("keyGen");
	   
	     
	     String clientid = request.getParameter("Client_Id");
	     
	     if(checkUser3(clientid,empid))
	     {
	    	 
	    	 String filename = empid+".txt";   
	         response.setContentType("APPLICATION/OCTET-STREAM");   
	         response.setHeader("Content-Disposition","attachment; filename=\"" + filename + "\"");       
	         
	         FileInputStream fileInputStream = new FileInputStream(path);  
	                     
	         int i;   
	         while ((i=fileInputStream.read()) != -1) 
	         {  
	         out.write(i);   
	         }   
	         fileInputStream.close();  
	           	 
	     }
	     else
	     {
	        out.println("Invalid User");
	     } 
	     out.close();
		}
	
	public boolean checkUser3(String clientid,String empid) 
    {
       boolean st =false;
        try {

            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloud","root","root");
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("Select r.FilePath from regist r join requestdetails re on r.Emp_Id=re.Emp_Id where re.Client_Id="+clientid+" and r.Emp_Id="+empid+"");
            ResultSet rs =(ResultSet) ps.executeQuery();
            if(rs.next())
            {
            	path = rs.getString(1);
            	return true;
            }
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
        return false;
	}
}
