package equipment;

public class Armor extends Equipment{

	private int defense;
	
	public Armor(String name, int price, int defense) {
		super(name, price);
		this.defense = defense;
	}

	public int getDefense() {
		return defense;
	}
	
}
