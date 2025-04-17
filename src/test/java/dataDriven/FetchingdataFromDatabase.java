package dataDriven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class FetchingdataFromDatabase {
	 @Test
	 public void FetchDataFromDataBase() throws SQLException
	 
	 {
		 String Actres="monkey";
		 //load/registerring with database
		 Driver dref = new Driver();
		 //register
		 DriverManager.registerDriver(dref);
		 //connecting to database 
		 Connection con= DriverManager.getConnection("jdbc:Mysql://localhost:3306/project","root","root");
		 //creating  a statement
		 Statement st=con.createStatement();
		 //use non select query and modify 
	ResultSet result=st.executeQuery("select* from ADVSEL1;");
	
	
	while(result.next()) {
		String expres=result.getString(2);
		if(expres.equals(Actres)) {
			System.out.println(expres);
			
		}
		else {
			
			System.out.println("NO DATA FOUND");
		}
	}
		 
		 
			 
		 con.close();
	 }
	 
	
}


