package equipment;

import java.util.ArrayList;

import armor.DiamondArmor;
import armor.FabricArmor;
import armor.IronArmor;
import ring.DiamondRing;
import ring.GrassRing;
import ring.SilverRing;
import weapon.DiamondSword;
import weapon.IronSword;
import weapon.WoodSword;

public class GetEquipmentList {
	
	private ArrayList<Weapon> weaponList;
	private ArrayList<Armor> armorList;
	private ArrayList<Ring> ringList;
	
	private static GetEquipmentList instance = new GetEquipmentList();
	public static GetEquipmentList getInstance() {
		return instance;
	}
	
	private GetEquipmentList() {
		weaponList = new ArrayList<>();
		weaponList.add(new WoodSword());
		weaponList.add(new IronSword());
		weaponList.add(new DiamondSword());
		
		armorList = new ArrayList<>();
		armorList.add(new FabricArmor());
		armorList.add(new IronArmor());
		armorList.add(new DiamondArmor());
		
		ringList = new ArrayList<>();
		ringList.add(new GrassRing());
		ringList.add(new SilverRing());
		ringList.add(new DiamondRing());
	}
	
	public ArrayList<Weapon> getWeaponList(){
		return weaponList;
	}
	
	public ArrayList<Armor> getArmorList(){
		return armorList;
	}

	public ArrayList<Ring> getRingList(){
		return ringList;
	}
	
}
