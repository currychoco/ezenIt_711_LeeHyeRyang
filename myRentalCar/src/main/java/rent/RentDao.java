package rent;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import car.CarDao;
import car.CarDto;
import util.DBManager;

public class RentDao {
	private String url;
	private String user;
	private String password;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private RentDao() {
		this.url = "jdbc:mysql://localhost:3306/mvc2_rent";
		this.user = "root";
		this.password = "root";
		
		this.conn = null;
		this.pstmt = null;
		this.rs = null;
	}
	
	private static RentDao instance = new RentDao();
	public static RentDao getInstance() {
		return instance;
	}
	
	//create
	public void setRentCar(RentDto rent) {
		int no = generateSequence();
		String sql = "insert into rent(\r\n"
				+ "	`no`\r\n"
				+ "    ,userno\r\n"
				+ "    ,carno\r\n"
				+ "    ,sDate\r\n"
				+ "    ,eDate\r\n"
				+ "    ,price\r\n"
				+ ") values(\r\n"
				+ "	?\r\n"
				+ "    ,?\r\n"
				+ "    ,?\r\n"
				+ "    ,?\r\n"
				+ "    ,?\r\n"
				+ "    ,?\r\n"
				+ ");";
		try {
			conn = DBManager.getConnection(url, user, password);
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setInt(2, rent.getUserno());
			pstmt.setInt(3, rent.getCarno());
			pstmt.setTimestamp(4, rent.getsDate());
			pstmt.setTimestamp(5, rent.geteDate());
			pstmt.setInt(6, rent.getPrice());
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
	
	private int generateSequence() {
		int no = 0;
		String sql = "select MAX(`no`) from rent";
		
		try {
			conn=DBManager.getConnection(url, user, password);
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt(1);
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
		return no + 1;
	}
	
	public List<RentDto> getRentAllById(String id){
		List<RentDto> list = new ArrayList<>();
		String sql = "select * from rent where userno = ?";
		int userno = getUserNoById(id);
		
		try {
			conn = DBManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userno);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				int no = rs.getInt(1);
				int carno = rs.getInt(3);
				Timestamp resDate = rs.getTimestamp(4);
				Timestamp sDate = rs.getTimestamp(5);
				Timestamp eDate = rs.getTimestamp(6);
				int price = rs.getInt(7);
				int extra = rs.getInt(8);
				boolean carReturn = rs.getBoolean(9);
				
				list.add(new RentDto(no, userno, carno, resDate, sDate, eDate, price, extra, carReturn));
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
	
	private int getUserNoById(String id) {

		String sql = "select `no` from `user` where id = ?";
		int no = -1;
		
		try {
			conn = DBManager.getConnection(url, this.user, password);
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				no = rs.getInt(1);
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

		return no;
	}
	
	public RentDto getRentByNo(int no) {
		RentDto rent= null;
		String sql = "select * from rent where `no` = ?";
		
		try {
			conn=DBManager.getConnection(url, user, password);
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				int userno = rs.getInt(2);
				int carno = rs.getInt(3);
				Timestamp resDate = rs.getTimestamp(4);
				Timestamp sDate = rs.getTimestamp(5);
				Timestamp eDate = rs.getTimestamp(6);
				int price = rs.getInt(7);
				int extra = rs.getInt(8);
				boolean	carReturn = rs.getBoolean(9);
				
				rent = new RentDto(no, userno, carno, resDate, sDate, eDate, price, extra, carReturn);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return rent;
	}
	
	public void returnCar(RentDto rent) {
		String sql ="update rent set `return`=true where no=?";
		
		try {
			conn=DBManager.getConnection(url, user, password);
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rent.getNo());
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void returnCar(RentDto rent, int min) {
		String sql = "update rent set `return`=true, extra=? where no=?";
		int extra = min*getCarExtra(rent.getCarno());
		
		try {
			conn=DBManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, extra);
			pstmt.setInt(2, rent.getNo());
			pstmt.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private int getCarExtra(int carno) {
		CarDao dao = CarDao.getInstance();
		CarDto car = dao.getCarByNo(carno);
		int extra = (int)(car.getPrice() * 0.03);
		return extra;
	}
	
	public void rentCancel(int no) {
		String sql = "delete from rent where `no` = ?";
		
		try {
			conn=DBManager.getConnection(url, user, password);
			pstmt =conn.prepareStatement(sql);
			pstmt.setInt(1, no);
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
}
