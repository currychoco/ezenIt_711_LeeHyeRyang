package unit;

import booty.Booty;

public class Monster extends Unit {

	private int gold;
	
	public Monster(int level, String name, int hp, int damage, int defense, int critical, int exp, int gold) {
		super(level, name, hp, damage, defense, critical, exp);
		this.gold = gold;
	}
	
	//전투에서 죽었을 때
	public Booty deadMoster() {
		Booty booty = new Booty(this.getExp(), this.gold);
		return booty;
	}
}
