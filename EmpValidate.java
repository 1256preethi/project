package pbcloud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmpValidate 
{
	public static boolean checkUser2(String Uname,String Upass) 
    {
        boolean st =false;
        try {

            //loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");

            //creating connection with the database
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cloud","root","root");
            PreparedStatement ps = (PreparedStatement) con.prepareStatement("select * from regist where Emp_Name=? and EmpPassword=? ");
            ps.setString(1, Uname);
            ps.setString(2, Upass);
            ResultSet rs =(ResultSet) ps.executeQuery();
            st = rs.next();

        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
        return st;
	}


}
