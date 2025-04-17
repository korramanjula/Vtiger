package dataDriven;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class FetchDataFromMysqlDatabase {
	@Test
	public void FetchDataFromMysqlDatabase() throws SQLException {
	//load/registering with database
	Driver drf=new Driver();
	DriverManager.registerDriver(drf);
	//connecting to database
	Connection con =DriverManager.getConnection("jdbc:Mysql://localhost:3306/project","root","root");
	//creating a statement
	Statement st=con.createStatement();
	//fetching the data by using select query
	ResultSet R=st.executeQuery("select* from ADVSEL1");
	while(R.next()) {
		System.out.println(R.getString(1)+"\t"+R.getString(2)+"\t"+R.getString(3));
	}
	con.close();
		
	}

}
