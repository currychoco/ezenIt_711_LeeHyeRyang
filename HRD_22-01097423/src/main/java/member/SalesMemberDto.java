package member;

public class SalesMemberDto {
	private int custno;
	private String custname;
	private String grade;
	private int price;
	
	public SalesMemberDto(int custno, String custname, String grade, int price) {
		super();
		this.custno = custno;
		this.custname = custname;
		this.grade = grade;
		this.price = price;
	}

	public int getCustno() {
		return custno;
	}

	public String getCustname() {
		return custname;
	}

	public String getGrade() {
		return grade;
	}

	public int getPrice() {
		return price;
	}
	
	
}
