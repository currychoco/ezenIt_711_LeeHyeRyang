package equipment;

public class Equipment {
	private String name;
	private int price;
	private int damage;
	private int defense;
	private int critical;

	public Equipment(String name, int price, int damage, int defense, int critical) {
		this.name = name;
		this.price = price;
		this.damage = damage;
		this.defense = defense;
		this.critical = critical;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getDamage() {
		return damage;
	}

	public int getDefense() {
		return defense;
	}

	public int getCritical() {
		return critical;
	}
}
