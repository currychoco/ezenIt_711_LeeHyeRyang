package car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class CarDao {
	private String url;
	private String user;
	private String password;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private CarDao() {
		this.url = "jdbc:mysql://localhost:3306/mvc2_rent";
		this.user = "root";
		this.password = "root";
		
		this.conn = null;
		this.pstmt = null;
		this.rs = null;
	}
	
	private static CarDao instance = new CarDao();
	public static CarDao getInstance() {
		return instance;
	}
	
	//Read
	public List<CarDto> getCarAll(){
		List<CarDto> list = new ArrayList<>();
		String sql = "select c.`no`, c.car_no, ct.name, c.price, c.img, c.DOM, c.fuel\r\n"
				+ "from car c\r\n"
				+ "join car_type ct\r\n"
				+ "on c.`type` = ct.`no`\r\n"
				+ "order by c.`no`";
		try {
			conn=DBManager.getConnection(url, user, password);
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int no = rs.getInt(1);
				String car_no = rs.getString(2);
				String name = rs.getString(3);
				int price = rs.getInt(4);
				String img = rs.getString(5);
				int DOM = rs.getInt(6);
				String fuel = rs.getString(7);
				
				list.add(new CarDto(no, car_no, name, price, img, DOM, fuel));
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
		return list;
	}
	
	public CarDto getCarByNo(int no) {
		CarDto car = null;
		String sql = "select\r\n"
				+ "	c.`no`\r\n"
				+ "    ,c.car_no\r\n"
				+ "    ,ct.name\r\n"
				+ "    ,c.price\r\n"
				+ "    ,c.img\r\n"
				+ "    ,c.DOM\r\n"
				+ "    ,c.fuel\r\n"
				+ "from car c \r\n"
				+ "join car_type ct\r\n"
				+ "on c.type = ct.`no`\r\n"
				+ "where c.`no` = ?";
		
		try {
			conn=DBManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				String car_no = rs.getString(2);
				String name=rs.getString(3);
				int price = rs.getInt(4);
				String img = rs.getString(5);
				int DOM = rs.getInt(6);
				String fuel = rs.getString(7);
				
				car = new CarDto(no, car_no, name, price, img, DOM, fuel);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return car;
	}
}
