package equipment;

public class Weapon extends Equipment{

	private int damage;
	
	public Weapon(String name, int price, int damage) {
		super(name, price);
		this.damage = damage;
	}

	public int getDamage() {
		return damage;
	}
	
}
