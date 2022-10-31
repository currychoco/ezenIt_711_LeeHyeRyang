package notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import util.DBManager;

public class NoticeDao {
	private String url;
	private String user;
	private String password;

	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;

	private NoticeDao() {
		this.url = "jdbc:mysql://localhost:3306/mvc2_rent";
		this.user = "root";
		this.password = "root";

		this.conn = null;
		this.pstmt = null;
		this.rs = null;
	}

	private static NoticeDao instance = new NoticeDao();

	public static NoticeDao getInstance() {
		return instance;
	}

	// 공지사항
	public List<NoticeDto> getNoticeAll() {
		List<NoticeDto> list = new ArrayList<>();
		String sql = "select n.`no`, n.title, n.content, m.name, n.regDate, n.viewCnt\r\n" + "from notice n\r\n"
				+ "join manager m\r\n" + "on n.writer = m.`no`\r\n" + "order by n.`no` desc";
		try {
			conn = DBManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				int no = rs.getInt(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				String writer = rs.getString(4);
				Timestamp regDate = rs.getTimestamp(5);
				int veiwCnt = rs.getInt(6);

				list.add(new NoticeDto(no, title, content, writer, regDate, veiwCnt));
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

	public NoticeDto getNotice(int no) {
		NoticeDto notice = null;
		String sql = "select n.`no`, n.title, n.content, m.name, n.regDate, n.viewCnt\r\n" + "from notice n\r\n"
				+ "join manager m\r\n" + "on n.writer = m.`no`\r\n" + "where n.`no` = ?;";
		try {
			conn = DBManager.getConnection(url, user, password);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String title = rs.getString(2);
				String content = rs.getString(3);
				String writer = rs.getString(4);
				Timestamp regDate = rs.getTimestamp(5);
				int viewCnt = rs.getInt(6);

				notice = new NoticeDto(no, title, content, writer, regDate, viewCnt);
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
		return notice;
	}
	
	// viewCnt
		public void viewCnt(int no) {
			NoticeDto notice = getNotice(no);
			int viewCnt = notice.getViewCnt() + 1;
			
			String sql = "UPDATE notice SET viewCnt = ? WHERE no = ?";
			try {
				conn=DBManager.getConnection(url, user, password);
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, viewCnt);
				pstmt.setInt(2, no);
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
