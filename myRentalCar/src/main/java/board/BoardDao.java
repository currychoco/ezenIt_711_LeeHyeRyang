package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import util.DBManager;

public class BoardDao {
	private String url;
	private String user;
	private String password;
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	// 단일한 인스턴스 제공
	// Singletone으로 구현
	private BoardDao() {
		this.url = "jdbc:mysql://localhost:3306/mvc2_rent";
		this.user = "root";
		this.password = "root";
		
		this.conn = null;
		this.pstmt = null;
		this.rs = null;
	}
	
	private static BoardDao instance = new BoardDao();
	
	public static BoardDao getInstance() {
		return instance;
	}
	
	// CRUD
	// Create
	public void createBoard(BoardDto board) {
		String sql = "insert into board values(?,?,?,?,?,?,?)";
		int no = noGenerator(); // 마지막 no + 1
		try {
			this.conn = DBManager.getConnection(this.url, this.user, this.password);
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no); 
			this.pstmt.setString(2, board.getTitle());
			this.pstmt.setString(3, board.getContent());
			this.pstmt.setString(4, board.getUser());
			
			Timestamp now = new Timestamp(System.currentTimeMillis());
			this.pstmt.setTimestamp(5, now);
			this.pstmt.setTimestamp(6, now);
			this.pstmt.setInt(7, 0);
			
			this.pstmt.execute();
			System.out.println("연동 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("연동 실패");
		}finally {
			try {
				this.pstmt.close();
				this.conn.close();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	private int noGenerator() {
		String sql = "SELECT MAX(`no`) FROM board";
		int no = 0;
		try {
			this.conn = DBManager.getConnection(this.url, this.user, this.password);
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			
			if(this.rs.next()) {
				no = this.rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println(no);
		return ++no;
	}
	
	// Read
	public ArrayList<BoardDto> getBoardAll(){
		ArrayList<BoardDto> list = new ArrayList<BoardDto>();
		String sql = "SELECT * FROM board ORDER BY `no` DESC";
		
		try {
			this.conn = DBManager.getConnection(this.url, this.user, this.password);
			this.pstmt = this.conn.prepareStatement(sql);
			this.rs = this.pstmt.executeQuery();
			
			while(this.rs.next()) {
				int no = this.rs.getInt(1);
				String title = this.rs.getString(2);
				String content = this.rs.getString(3);
				String user = this.rs.getString(4);
				Timestamp regDate = this.rs.getTimestamp(5);
				Timestamp modDate = this.rs.getTimestamp(6);
				int viewCnt = this.rs.getInt(7);
				
				BoardDto board = new BoardDto(no,title,content,user,regDate,modDate,viewCnt);
				list.add(board);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
	
	// 2.one
	public BoardDto getBoardByNo(int no) {
		BoardDto board = null;
		String sql = "SELECT * FROM board WHERE `no` = ?";
		
		try {
			this.conn = DBManager.getConnection(this.url, this.user, this.password);
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);
			this.rs = this.pstmt.executeQuery();
			
			if(rs.next()) {
				String title = this.rs.getString(2);
				String content = this.rs.getString(3);
				String user = this.rs.getString(4);
				Timestamp regDate = this.rs.getTimestamp(5);
				Timestamp modDate = this.rs.getTimestamp(6);
				int viewCnt = this.rs.getInt(7);
				
				board = new BoardDto(no, title, content, user, regDate, modDate, viewCnt);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				this.rs.close();
				this.pstmt.close();
				this.conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		}
		
		return board;
	}
	// Update
	public void updateBoard(BoardDto board) {
		String sql = "update board set title = ?, content = ?, modDate = ? where `no` = ?";
		
		int no = board.getNo();
		String title = board.getTitle();
		String content = board.getContent();
		Timestamp now = new Timestamp(System.currentTimeMillis());;
		
		try {
			this.conn = DBManager.getConnection(this.url, this.user, this.password);
			this.pstmt=this.conn.prepareStatement(sql);
			this.pstmt.setString(1, title);
			this.pstmt.setString(2, content);
			this.pstmt.setTimestamp(3, now);
			this.pstmt.setInt(4, no);
			this.pstmt.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	// Delete
	public void deleteBoard(int no) {
		String sql = "DELETE FROM board WHERE no = ?";
		
		try {
			this.conn = DBManager.getConnection(this.url, this.user, this.password);
			this.pstmt = this.conn.prepareStatement(sql);
			this.pstmt.setInt(1, no);
			this.pstmt.execute();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				this.pstmt.close();
				this.conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}	
		}
	}
	
	// viewCnt
	public void viewCnt(int no) {
		BoardDto board = getBoardByNo(no);
		int viewCnt = board.getViewCnt() + 1;
		
		String sql = "UPDATE board SET viewCnt = ? WHERE no = ?";
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