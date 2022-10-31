package car;

public class CarDto {
	private int no;
	private String car_no;
	private String name;
	private int price;
	private String img;
	private int DOM;
	private String fuel;
	
	public CarDto(int no, String car_no, String name, int price, String img, int dOM, String fuel) {
		super();
		this.no = no;
		this.car_no = car_no;
		this.name = name;
		this.price = price;
		this.img = img;
		DOM = dOM;
		this.fuel = fuel;
	}

	public int getNo() {
		return no;
	}

	public String getCar_no() {
		return car_no;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public String getImg() {
		return img;
	}

	public int getDOM() {
		return DOM;
	}

	public String getFuel() {
		return fuel;
	}
	
	
}
