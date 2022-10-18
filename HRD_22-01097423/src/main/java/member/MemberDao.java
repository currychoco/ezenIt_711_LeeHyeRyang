package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DBManager;

public class MemberDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private String url;
	private String user;
	private String password;

	private MemberDao() {
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "c##lhr";
		password = "1234";
	}

	private static MemberDao instance = new MemberDao();

	public static MemberDao getInstance() {
		return instance;
	}

	// 마지막 번호
	public int getLastCustNo() {
		int lastNo = -1;
		try {
			conn = DBManager.getConnection(url, user, password);
			String sql = "SELECT MAX(custno) FROM member_tbl_02";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				lastNo = rs.getInt(1) + 1;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastNo;
	}

	// Create
	public void join(int custno, String custname, String phone, String address, String joindate, String grade,
			String city) {
		try {
			conn = DBManager.getConnection(url, user, password);
			String sql = "INSERT INTO member_tbl_02 VALUES(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custno);
			pstmt.setString(2, custname);
			pstmt.setString(3, phone);
			pstmt.setString(4, address);
			pstmt.setString(5, joindate);
			pstmt.setString(6, grade);
			pstmt.setString(7, city);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		}
	}

	// list
	public ArrayList<MemberDto> getMemberAll() {
		ArrayList<MemberDto> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection(url, user, password);
			String sql = "SELECT * FROM member_tbl_02 ORDER BY custno";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int custno = rs.getInt(1);
				String custname = rs.getString(2);
				String phone = rs.getString(3);
				String address = rs.getString(4);
				String joindate = rs.getString(5);
				String grade = rs.getString(6);
				String city = rs.getString(7);

				MemberDto member = new MemberDto(custno, custname, phone, address, joindate, grade, city);
				list.add(member);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		}
		return list;
	}

	// 한 개의 정보
	public MemberDto getMember(int custno) {
		MemberDto member = null;
		try {
			conn = DBManager.getConnection(url, user, password);
			String sql = "SELECT * FROM member_tbl_02 WHERE custno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, custno);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String custname = rs.getString(2);
				String phone = rs.getString(3);
				String address = rs.getString(4);
				String joindate = rs.getString(5);
				String grade = rs.getString(6);
				String city = rs.getString(7);

				member = new MemberDto(custno, custname, phone, address, joindate, grade, city);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		}
		return member;
	}

	// Update
	public void update(int custno, String custname, String phone, String address, String joindate, String grade,
			String city) {
		try {
			conn = DBManager.getConnection(url, user, password);
			String sql = "UPDATE member_tbl_02 SET custname=?,phone=?,address=?,joindate=?,grade=?,city=? WHERE custno=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, custname);
			pstmt.setString(2, phone);
			pstmt.setString(3, address);
			pstmt.setString(4, joindate);
			pstmt.setString(5, grade);
			pstmt.setString(6, city);
			pstmt.setInt(7, custno);
			rs = pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		}

	}
}
