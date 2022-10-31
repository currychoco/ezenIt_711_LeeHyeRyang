package rent;

import java.sql.Timestamp;

public class RentDto {
	private int no; // 식별자
	private int userno;
	private int carno;
	private Timestamp resDate; // 예약일 // db에서 기본생성으로 오늘날짜
	private Timestamp sDate; // 시작일 'yyyy-mm-dd hh:mm:ss' 식..?
	private Timestamp eDate; // 종료일
	private int price; // 대여비
	private int extra; // 종료일 시간보다 늦게 반납버튼을 눌렀을 경우 기본으로 0으로 생성
	private boolean carReturn;
	


	public RentDto(int no, int userno, int carno, Timestamp resDate, Timestamp sDate, Timestamp eDate, int price,
			int extra, boolean carReturn) {
		super();
		this.no = no;
		this.userno = userno;
		this.carno = carno;
		this.resDate = resDate;
		this.sDate = sDate;
		this.eDate = eDate;
		this.price = price;
		this.extra = extra;
		this.carReturn = carReturn;
	}

	public boolean isCarReturn() {
		return carReturn;
	}

	public RentDto(int userno, int carno, Timestamp sDate, Timestamp eDate, int price) {
		super();
		this.userno = userno;
		this.carno = carno;
		this.sDate = sDate;
		this.eDate = eDate;
		this.price = price;
	}

	public int getNo() {
		return no;
	}

	public int getUserno() {
		return userno;
	}

	public int getCarno() {
		return carno;
	}

	public Timestamp getResDate() {
		return resDate;
	}

	public Timestamp getsDate() {
		return sDate;
	}

	public Timestamp geteDate() {
		return eDate;
	}

	public int getPrice() {
		return price;
	}

	public int getExtra() {
		return extra;
	}

	public void setsDate(Timestamp sDate) {
		this.sDate = sDate;
	}

	public void seteDate(Timestamp eDate) {
		this.eDate = eDate;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setExtra(int extra) {
		this.extra = extra;
	}

	public void setCarReturn(boolean carReturn) {
		this.carReturn = carReturn;
	}
	
	public boolean getCarReturn() {
		return this.carReturn;
	}
}
