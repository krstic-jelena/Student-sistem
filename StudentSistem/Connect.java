package StudentSistem;


import java.sql.*;
import javax.swing.*;
public class Connect {
	Connection con=null;
	public static Connection java_db(){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Sistem","root","root");
			return con;
		}catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;

		}
	}

}


