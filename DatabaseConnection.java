package RestaurantManagement;

import java.sql.*;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.sql.Statement;

public class DatabaseConnection {
	public static final String user="root";
	public static final String password="zeng+77451zc";
	public static final String url="jdbc:mysql://localhost:3306/restaurant";  //192.168.199.133
	public Connection conn=null;
	//public Statement stat;
	
	public DatabaseConnection(){
	try
	{
		Class.forName("com.mysql.jdbc.Driver");
		this.conn=DriverManager.getConnection(url,user,password);  //创建数据库连接
		//Statement stat=conn.createStatement();
		
	}
	catch(ClassNotFoundException e)
	{
		e.printStackTrace();
	}
	catch(SQLException e)
	{
		e.printStackTrace();
	}
	
	
	
	}
	
	public Connection getConnection()
	{
		return this.conn;
	}
	
	//public Statement getStatement()
	//{
		//return this.stat;
	//}
	
	public void close()
	{
		if(this.conn!=null)
		{
			try
			{
				this.conn.close();
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

}
