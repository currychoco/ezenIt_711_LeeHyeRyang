package user;

import java.sql.Timestamp;

public class UserDto {
	private int no;
	private String name;
	private String id;
	private String password;
	private String license;
	private String phone;
	private String address;
	private Timestamp regDate;
	
	public UserDto(int no, String name, String id, String password, String license, String phone, String address,
			Timestamp regDate) {
		super();
		this.no = no;
		this.name = name;
		this.id = id;
		this.password = password;
		this.license = license;
		this.phone = phone;
		this.address = address;
		this.regDate = regDate;
	}
	

	public UserDto(String name, String id, String password, String license, String phone, String address) {
		super();
		this.name = name;
		this.id = id;
		this.password = password;
		this.license = license;
		this.phone = phone;
		this.address = address;
	}



	public int getNo() {
		return no;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getLicense() {
		return license;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
