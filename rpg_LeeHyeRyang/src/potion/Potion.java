package potion;

public class Potion {
	private String name;
	private int hp;
	private int price;

	public Potion(String name, int hp, int price) {
		this.name = name;
		this.hp = hp;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getHp() {
		return hp;
	}
	
	public int getPrice() {
		return price;
	}
}
