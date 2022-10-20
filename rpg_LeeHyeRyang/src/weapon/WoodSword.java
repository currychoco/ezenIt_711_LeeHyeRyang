package weapon;

import equipment.Weapon;

public class WoodSword extends Weapon{
	
	public WoodSword() {
		super("나무검", 10, 2);
	}
	
	private WoodSword woodSword = new WoodSword();
	public WoodSword getWoodSowrd() {
		return woodSword;
	}

}
