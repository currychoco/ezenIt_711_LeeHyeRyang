package notice;

import java.sql.Timestamp;

public class NoticeDto {
	private int no;
	private String title;
	private String content;
	private String writer;
	private Timestamp regDate;
	private int viewCnt;
	
	public NoticeDto(int no, String title, String content, String writer, Timestamp regDate, int viewCnt) {
		super();
		this.no = no;
		this.title = title;
		this.content = content;
		this.writer = writer;
		this.regDate = regDate;
		this.viewCnt = viewCnt;
	}

	public int getNo() {
		return no;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public String getWriter() {
		return writer;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public int getViewCnt() {
		return viewCnt;
	}
	
}
