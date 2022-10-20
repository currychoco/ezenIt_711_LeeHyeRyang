package unit;

import booty.Booty;
import equipment.Armor;
import equipment.Equipment;
import equipment.Ring;
import equipment.Weapon;

public class Player extends Unit {
	public static int GOLD = 0;

	private boolean party;
	private Weapon weapon;
	private Armor armor;
	private Ring ring;
	private boolean equipWeapon;
	private boolean equipRing;
	private boolean equipArmor;
	private int MAX_EXP = getLevel() * 10;
	private boolean inParty;

	public Player(String name, int hp, int damage, int defense, int critical, int exp) {
		super(1, name, hp, damage, defense, critical, exp);
		this.weapon = null;
		this.ring = null;
		this.armor = null;
	}

	public static int getGOLD() {
		return GOLD;
	}

	public boolean isParty() {
		return party;
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

	public boolean getInParty() {
		return inParty;
	}
	// 몬스터를 처리했을 때
	public void killMoster(Booty booty) {
		this.setExp(getExp() + booty.getExp());
		GOLD += booty.getGold();
		
		if(getExp() >= MAX_EXP) {
			setExp(getExp() - MAX_EXP);
			setLevel(getLevel() + 1);
		}
	}

	// 장비를 장착헀을 때
	public void equipWeapon(Weapon weapon) {
		if (equipWeapon) {
			setDamage(this.getDamage() - this.weapon.getDamage());
			this.weapon = weapon;
			setDamage(this.getDamage() + this.weapon.getDamage());
		} else {
			setDamage(this.getDamage() + weapon.getDamage());
			this.weapon = weapon;
			equipWeapon = true;
		}
	}

	public void equipArmor(Armor armor) {
		if (equipArmor) {
			this.setDefense(this.getDefense() - this.armor.getDefense());
			this.armor = armor;
			this.setDefense(this.getDefense() + this.armor.getDefense());
		} else {
			this.setDefense(this.getDefense() + armor.getDefense());
			this.armor = armor;
			equipArmor = true;
		}
	}

	public void equipRing(Ring ring) {
		if (equipRing) {
			setCritical(this.getCritical() - this.ring.getCritical());
			this.ring = ring;
			setCritical(this.getCritical() + this.ring.getCritical());
		} else {
			setCritical(this.getCritical() + ring.getCritical());
			this.ring = ring;
			equipRing = true;
		}
	}

	// 장비를 해제했을 때
	public void releaseWeapon() {
		if(equipWeapon) {
			setDamage(this.getDamage() - this.weapon.getDamage());
			this.weapon = null;
			this.equipWeapon = false;
		}else {
			System.out.println("장착하고 있는 무기가 없습니다.");
		}
	}
	
	public void releaseArmor() {
		if(equipArmor) {
			setDefense(this.getDefense() - this.armor.getDefense());
			this.armor = null;
			this.equipArmor = false;
		}else {
			System.out.println("장착하고 있는 갑옷이 없습니다.");
		}
	}
	
	public void releaseRing() {
		if(equipRing) {
			setCritical(this.getCritical() - this.ring.getCritical());
			this.ring = null;
			this.equipRing = false;
		}else {
			System.out.println("장착하고 있는 반지가 없습니다.");
		}
	}

}
