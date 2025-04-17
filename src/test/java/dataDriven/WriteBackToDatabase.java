package dataDriven;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

public class WriteBackToDatabase {
	 @Test
	 public void FetchDataFromDataBase() throws SQLException
	 
	 {
		 //load/registerring with database
		 Driver dref=new com.mysql.cj.jdbc.Driver();
		 //register
		 DriverManager.registerDriver(dref);
		 //connecting to database 
		 Connection con= DriverManager.getConnection("jdbc:Mysql://localhost:3306/project","root","root");
		 //creating  a statement
		 Statement st=con.createStatement();
		 //use non select query and modify 
		 int R=st.executeUpdate(" insert into ADVSEL1 values(600,'lol','lol@gmail.com'),(700'bill','bill@gmail.com');");
		 System.out.println(R);
		 con.close();
	 }
	 
	 

}
