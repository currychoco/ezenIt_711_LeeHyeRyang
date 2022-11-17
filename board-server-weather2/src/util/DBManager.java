package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBManager {
	public static Connection getConnection()  {
		Connection conn = null;	
		final String url = "jdbc:mysql://database-1.ccnr5xsbemm5.ap-northeast-2.rds.amazonaws.com:3306/MysqlDB";
		final String user = "admin";
		final String password = "tGTACmhj8b0ti";
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url,user,password);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	//select문 수행 후 close 메소드
	public static void close(Connection conn,Statement stmt, ResultSet rs){
		try{
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//DBL 수행 후 close 메소드
	public static void close(Connection conn,Statement stmt){
		try{
			stmt.close();
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
