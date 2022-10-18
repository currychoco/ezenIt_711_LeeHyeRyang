package money;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import member.MemberDao;
import member.MemberDto;
import member.SalesMemberDto;
import util.DBManager;

public class MoneyDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private String url;
	private String user;
	private String password;

	private MoneyDao() {
		url = "jdbc:oracle:thin:@localhost:1521:xe";
		user = "c##lhr";
		password = "1234";
	}

	private static MoneyDao instance = new MoneyDao();

	public static MoneyDao getInstance() {
		return instance;
	}

	// sales
	public ArrayList<SalesMemberDto> getSales() {
		ArrayList<SalesMemberDto> list = new ArrayList<>();
		try {
			conn = DBManager.getConnection(url, user, password);
			String sql = "SELECT custno, SUM(price) AS price FROM money_tbl_02 GROUP BY custno ORDER BY price DESC";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int custno = rs.getInt(1);
				int price = rs.getInt(2);
				MemberDao memberDao = MemberDao.getInstance();
				MemberDto member = memberDao.getMember(custno);
				String custname = member.getCustname();
				String grade = member.toStringGrade();

				SalesMemberDto sales = new SalesMemberDto(custno, custname, grade, price);
				list.add(sales);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBManager.closeConnection(conn, pstmt, rs);
		}
		return list;
	}
}
