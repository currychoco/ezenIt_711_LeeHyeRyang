package unit;

import java.util.ArrayList;

import booty.Booty;
import equipment.Armor;
import equipment.Equipment;
import equipment.Ring;
import equipment.Weapon;

public class Hero extends Unit {
	private static int gold = 10000;
	private static int redPotion = 0;
	private static int whitePotion = 0;
	private static int elixir = 0;
	private static ArrayList<Equipment> equipments = new ArrayList<>();
	private static ArrayList<Hero> partyMembers = new ArrayList<>();

	private boolean party;
	private Weapon weapon;
	private Armor armor;
	private Ring ring;
	private boolean equipWeapon;
	private boolean equipRing;
	private boolean equipArmor;
	private int MAX_EXP = getLevel() * 50;

	protected Hero(String name, int hp, int damage, int defense, int critical) {
		super(1, name, hp, damage, defense, critical, 0);
		this.weapon = null;
		this.ring = null;
		this.armor = null;
	}

	
	
	public static ArrayList<Equipment> getEquipments() {
		return equipments;
	}
	
	public static ArrayList<Hero> getPartyMembers(){
		return partyMembers;
	}

	public static void sellEquipment(Equipment equipment) {
		Hero.equipments.remove(equipment);
	}
	public static void setGold(int gold) {
		Hero.gold = gold;
	}

	public static void setRedPotion(int redPotion) {
		Hero.redPotion = redPotion;
	}

	public static void setWhitePotion(int whitePotion) {
		Hero.whitePotion = whitePotion;
	}

	public static void setElixir(int elixir) {
		Hero.elixir = elixir;
	}

	public static void addEquipments(Equipment equipment) {
		Hero.equipments.add(equipment);
	}

	public static int getRedPotion() {
		return redPotion;
	}

	public static int getWhitePotion() {
		return whitePotion;
	}

	public static int getElixir() {
		return elixir;
	}

	public static int getGold() {
		return gold;
	}

	public boolean getParty() {
		return party;
	}
	
	public void setParty(boolean bool){
		this.party = bool;
	}

	public Equipment getWeapon() {
		return weapon;
	}

	public Equipment getArmor() {
		return armor;
	}

	public Equipment getRing() {
		return ring;
	}

	public boolean isEquipWeapon() {
		return equipWeapon;
	}

	public boolean isEquipRing() {
		return equipRing;
	}

	public boolean isEquipArmor() {
		return equipArmor;
	}

	public int getMAX_EXP() {
		return MAX_EXP;
	}

	// 몬스터를 처리했을 때
	public void killMoster(Booty booty) {
		this.setExp(getExp() + booty.getExp());
		gold += booty.getGold();

		if (getExp() >= MAX_EXP) {
			setExp(getExp() - MAX_EXP);
			setLevel(getLevel() + 1);
			setMaxHp(getMAX_HP() + 50);
		}
	}

	// 장비를 장착헀을 때	
	
	public void equipWeapon(Weapon weapon) {
		if (equipWeapon) {
			equipments.add(this.weapon);
			setDamage(this.getDamage() - this.weapon.getDamage());
			this.weapon = weapon;
			equipments.remove(this.weapon);
			setDamage(this.getDamage() + this.weapon.getDamage());
		} else {
			equipments.remove(weapon);
			setDamage(this.getDamage() + weapon.getDamage());
			this.weapon = weapon;
			equipWeapon = true;
		}
	}

	public void equipArmor(Armor armor) {
		if (equipArmor) {
			equipments.add(this.armor);
			this.setDefense(this.getDefense() - this.armor.getDefense());
			this.armor = armor;
			equipments.remove(this.armor);
			this.setDefense(this.getDefense() + this.armor.getDefense());
		} else {
			equipments.remove(armor);
			this.setDefense(this.getDefense() + armor.getDefense());
			this.armor = armor;
			equipArmor = true;
		}
	}

	public void equipRing(Ring ring) {
		if (equipRing) {
			equipments.add(this.ring);
			setCritical(this.getCritical() - this.ring.getCritical());
			this.ring = ring;
			equipments.remove(this.ring);
			setCritical(this.getCritical() + this.ring.getCritical());
		} else {
			equipments.remove(ring);
			setCritical(this.getCritical() + ring.getCritical());
			this.ring = ring;
			equipRing = true;
		}
	}

	// 장비를 해제했을 때
	public void releaseWeapon() {
		if (equipWeapon) {
			equipments.add(this.weapon);
			setDamage(this.getDamage() - this.weapon.getDamage());
			this.weapon = null;
			this.equipWeapon = false;
		} else {
			System.out.println("장착하고 있는 무기가 없습니다.");
		}
	}

	public void releaseArmor() {
		if (equipArmor) {
			equipments.add(this.armor);
			setDefense(this.getDefense() - this.armor.getDefense());
			this.armor = null;
			this.equipArmor = false;
		} else {
			System.out.println("장착하고 있는 갑옷이 없습니다.");
		}
	}

	public void releaseRing() {
		if (equipRing) {
			equipments.add(this.ring);
			setCritical(this.getCritical() - this.ring.getCritical());
			this.ring = null;
			this.equipRing = false;
		} else {
			System.out.println("장착하고 있는 반지가 없습니다.");
		}
	}

}
