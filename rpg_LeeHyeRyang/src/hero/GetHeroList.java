package hero;

import java.util.ArrayList;

import equipment.Armor;
import equipment.Equipment;
import equipment.Ring;
import equipment.Weapon;
import unit.Hero;

public class GetHeroList {
	
	private ArrayList<Hero> list;
	
	private static GetHeroList instance = new GetHeroList();
	public static GetHeroList getInstance() {
		return instance;
	}
	
	private GetHeroList() {
		list = new ArrayList<>();
		list.add(new HeroRed());
		list.add(new HeroYellow());
		list.add(new HeroGreen());
		list.add(new HeroBlue());
		list.add(new HeroPink());
		list.add(new HeroBlack());
	}
	
	public ArrayList<Hero> getHeroList(){
		return list;
	}
	
	//장비 장착
	public void getEquipEquipment(int idx, Equipment equipment) {
		if(equipment instanceof Weapon) {
			list.get(idx).equipWeapon((Weapon)equipment);
		}else if(equipment instanceof Armor) {
			list.get(idx).equipArmor((Armor)equipment);
		}else {
			list.get(idx).equipRing((Ring)equipment);
		}
	}
	
	//장비 해제
	public void releaseEquipment(int idx, int part) {
		
	}
}
