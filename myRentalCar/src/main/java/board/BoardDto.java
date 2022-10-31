package board;

import java.sql.Timestamp;

public class BoardDto {
	public BoardDto(String title, String content, String user) {
		super();
		this.title = title;
		this.content = content;
		this.user = user;
	}
	
	public BoardDto(int no, String title, String content) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
	}

	private int no;
	private String title;
	private String content;
	private String user;
	private Timestamp regDate;
	private Timestamp modDate;
	private int viewCnt;
	
	public int getNo() {
		return no;
	}

//	public void setNo(int no) {
//		this.no = no;
//	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUser() {
		return user;
	}

//	public void setUser(String user) {
//		this.user = user;
//	}

	public Timestamp getRegDate() {
		return regDate;
	}

//	public void setRegDate(Timestamp regDate) {
//		this.regDate = regDate;
//	}

	public Timestamp getModDate() {
		return modDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}

	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}

	public BoardDto(int no, String title, String content, String user,Timestamp regDate,
			Timestamp modDate, int viewCnt) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.user = user;
		this.regDate = regDate;
		this.modDate = modDate;
		this.viewCnt = viewCnt;
	}
	
	// 
}
