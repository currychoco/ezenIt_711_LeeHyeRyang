package user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import util.DBManager;

public class UserDao {
	private String url;
	private String user;
	private String password;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private UserDao() {
		this.url = "jdbc:mysql://localhost:3306/mvc2_rent";
		this.user = "root";
		this.password = "root";
		
		this.conn = null;
		this.pstmt = null;
		this.rs = null;
	}
	
	private static UserDao instance = new UserDao();
	public static UserDao getInstance() {
		return instance;
	}
	
	//Create
	public void join(UserDto user) {
		int no = generateSequence() + 1;
		String sql = "insert into `user`(`no`,id,`password`,`name`,phone,address,license) values(?,?,?,?,?,?,?)";
		try {
			conn=DBManager.getConnection(this.url, this.user, this.password);
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, user.getId());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getName());
			pstmt.setString(5, user.getPhone());
			pstmt.setString(6, user.getAddress());
			pstmt.setString(7, user.getLicense());
			pstmt.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	//no
	private int generateSequence() {
		int no = 0;
		String sql = "select MAX(`no`) from `user`;";
		
		try {
			conn=DBManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return no;
	}
	
	public boolean login(String id, String password) {
		boolean login = false;
		String sql = "select `password` from `user` where id=?;";
		
		try {
			conn=DBManager.getConnection(url, user, this.password);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String dPw = rs.getString(1);
				if(password.equals(dPw)) {
					login=true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return login;
	}
	
	public UserDto getUserById(String id) {
		UserDto user = null;
		String sql = "select * from user where id = ?;";
		
		try {
			conn=DBManager.getConnection(url, this.user, password);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt(1);
				String password= rs.getString(3);
				String name= rs.getString(4);
				String phone = rs.getString(5);
				String address = rs.getString(6);
				String license = rs.getString(7);
				Timestamp regDate = rs.getTimestamp(8);
				
				user=new UserDto(no, name, id, password, license, phone, address, regDate);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return user;
	}
}
