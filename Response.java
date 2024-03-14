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

@WebServlet("/sendresponse")
public class Response extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String keyGen =null;
		String Emp_Id =null;

		try
		{  
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/cloud","root","root");  
		PreparedStatement ps=con.prepareStatement("Select * from regist where Emp_Id="+request.getParameter("empId"));
		ResultSet rs=ps.executeQuery();  
		if(rs.next())
		{
			keyGen=rs.getString("keyGen");
		}
		
		String host="", user="", pass="";

		host ="smtp.gmail.com"; //"smtp.gmail.com";

		user ="boobalan.shiash@gmail.com"; //"YourEmailId@gmail.com" // email id to send the emails

		pass ="Balan@98"; //Your gmail password

		String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

		
		String to =request.getParameter("Email_id");// out going email id

		String from ="boobalan.shiash@gmail.com"; //Email id of the recipient

		String subject1="Mail Send Succesfully!!!!!";
		
		Emp_Id=request.getParameter("empId");
		
		String msg1="Dear Vendor, "+keyGen+" \n this key is the secret key of "+Emp_Id+"";

		boolean sessionDebug = true;

		java.util.Properties props = System.getProperties();
		props.put("mail.host", host);
		props.put("mail.transport.protocol.", "smtp");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.fallback", "false");
		props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
		Session mailSession = Session.getDefaultInstance(props, null);
		mailSession.setDebug(sessionDebug);
		Message msg = new MimeMessage(mailSession);
		msg.setFrom(new InternetAddress(from));
		InternetAddress[] address = {new InternetAddress(to)};
		msg.setRecipients(Message.RecipientType.TO, address);
		msg.setSubject(subject1);
		msg.setText(msg1); // use setText if you want to send text
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(host, user, pass);
		try {
		transport.sendMessage(msg, msg.getAllRecipients());

		}
		catch (Exception err) {

		out.println("message not successfully sended"); // assume itâs a fail
		}
		transport.close();
		PreparedStatement ps1=con.prepareStatement("update requestdetails set Status='Approved' where Emp_Id="+request.getParameter("empId"));
		ps1.executeUpdate();  
		HttpSession http = request.getSession();
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
