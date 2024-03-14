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


@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();
		
		HttpSession ses=request.getSession();
		String message=null;
		
		String a=request.getParameter("Cpny_Name");
		String b=request.getParameter("Emp_Id");		
		String c=request.getParameter("Emp_Name");
		String d=request.getParameter("Age");
		String e=request.getParameter("DOB");
		String f=request.getParameter("Gender");
		String g=request.getParameter("Ph_No");
		String h=request.getParameter("Email_Id");
		String j=request.getParameter("Address");  
		String k=request.getParameter("Edu_Details");  
		String l=request.getParameter("Exp_Details");  
		String m=request.getParameter("Accnt_Details");
		
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/cloud","root","root");  
			
			 //String path = Paths.get("").toAbsolutePath().toString(); 
			 FileWriter fw=new FileWriter("D:\\FinalProject\\workspace\\publiccloud\\src\\upload\\"+c+".txt");    
	         fw.write("Cpny_Name:"+a); 
	         fw.write("\n");
	         fw.write("Emp_Id");
	         fw.write("\n");
	         fw.write("Emp_Name: "+c); 
	         fw.write("\n");
	         fw.write("Age: "+d); 
	         fw.write("\n");
	         fw.write("DOB: "+e); 
	         fw.write("\n");
	         fw.write("Gender: "+f); 
	         fw.write("\n");
	         fw.write("Ph_No: "+g); 
	         fw.write("\n");
	         fw.write("Email_Id: "+h); 
	         fw.write("\n");
	         fw.write("Address: "+j); 
	         fw.write("\n");
	         fw.write("Edu_Details: "+k); 
	         fw.write("\n");
	         fw.write("Exp_Details: "+l); 
	         fw.write("\n");
	         fw.write("Accnt_Details: "+m);
	         fw.close();
	         
		PreparedStatement ps=con.prepareStatement("insert into regist(Cpny_Name,Emp_Id,Emp_Name,Age,DOB,Gender,Ph_No,Email_Id,Address,Edu_Details,Exp_Details,Accnt_Details,FilePath,keyGen) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");  
		  
		ps.setString(1,a);  
		ps.setInt(2,Integer.parseInt(b));  
		ps.setString(3,c); 
		ps.setInt(4,Integer.parseInt(d));
		ps.setString(5,e);  
		ps.setString(6,f);  
		ps.setString(7,g);
		ps.setString(8,h);
		ps.setString(9,j);
		ps.setString(10,k);
		ps.setString(11,l);
		ps.setString(12,m);
		ps.setString(13,"D:\\FinalProject\\workspace\\publiccloud\\src\\upload\\"+c+".txt");
		ps.setString(14,getAlphaNumericString(8));
		          
		int i=ps.executeUpdate();  
		
		if(i>0)	
		{
			 message="Register Successfully...";
			 ses.setAttribute("msg",message);
			 //response.sendRedirect("EmpRegisterMessage.jsp"); 
			RequestDispatcher rs1 = request.getRequestDispatcher("EmpRegisterMessage.jsp");
       	 	rs1.include(request, response);
		}  
		}
		catch (Exception e2) 
		{
			System.out.println(e2);
			e2.printStackTrace();
		}           
		out.close();
	}
	public  String getAlphaNumericString(int n) 
    {  
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz";  
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) 
        {  
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
        return sb.toString(); 
    } 
}

