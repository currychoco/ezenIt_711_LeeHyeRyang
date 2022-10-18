package member;

public class MemberDto {

	private int custno;
	private String custname;
	private String phone;
	private String address;
	private String joindate;
	private String grade;
	private String city;

	public MemberDto(int custno, String custname, String phone, String address, String joindate, String grade,
			String city) {
		super();
		this.custno = custno;
		this.custname = custname;
		this.phone = phone;
		this.address = address;
		this.joindate = joindate;
		this.grade = grade;
		this.city = city;
	}

	public int getCustno() {
		return custno;
	}

	public String getCustname() {
		return custname;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getJoindate() {
		String[] date=joindate.toString().split(" ");
		return date[0].toString();
	}

	public String getGrade() {
		return grade;
	}
	
	public String toStringGrade() {
		if(grade.equals("A")) {
			return "VIP";
		}else if(grade.equals("B")) {
			return "일반";
		}
		return "직원";
	}

	public String getCity() {
		return city;
	}

	public void setCustname(String custname) {
		this.custname = custname;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
