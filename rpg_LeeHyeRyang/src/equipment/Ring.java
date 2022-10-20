package equipment;

public class Ring extends Equipment{

	private int critical;
	
	public Ring(String name, int price, int critical) {
		super(name, price);
		this.critical = critical;
	}
	
	public int getCritical() {
		return this.critical;
	}
}
