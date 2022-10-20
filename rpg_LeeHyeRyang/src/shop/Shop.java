package shop;

import java.util.ArrayList;

import equipment.Armor;
import equipment.Equipment;
import equipment.GetEquipmentList;
import equipment.Ring;
import equipment.Weapon;
import potion.Elixir;
import potion.GetPotionList;
import potion.Potion;
import potion.RedPotion;
import potion.WhitePotion;
import unit.Hero;

public class Shop {
	private GetEquipmentList getEquipmentList;
	private GetPotionList getPotionList;

	public Shop() {
		getEquipmentList = GetEquipmentList.getInstance();
		getPotionList = GetPotionList.getInstance();
	}

	// 상점 메뉴 출력
	public void printMenu() {
		System.out.println("-------ShopMenu-------");
		System.out.println("(1)무기 구매");
		System.out.println("(2)방어구 구매");
		System.out.println("(3)반지 구매");
		System.out.println("(4)물약 구매");
		System.out.println("(5)물건 판매");
		System.out.println("(0)뒤로가기");
	}
	
	// 현재 가지고 있는 금액 보여주기
	public void printGold() {
		System.out.printf("보유 GOLD : %d\n", Hero.getGold());
	}

	// 무기 메뉴 출력
	public void printWeaponMenu() {
		System.out.println("-------WeaponMenu-------");
		this.printGold();
		ArrayList<Weapon> weaponList = getEquipmentList.getWeaponList();
		for (int i = 0; i < weaponList.size(); i++) {
			System.out.printf("%d. [%s] 공격력 : %d 가격 : %d\n", i + 1, weaponList.get(i).getName(),
					weaponList.get(i).getDamage(), weaponList.get(i).getPrice());
		}
		System.out.println("0. 뒤로가기");
	}

	// 방어구 메뉴 출력
	public void printArmorMenu() {
		System.out.println("-------ArmorMenu-------");
		this.printGold();
		ArrayList<Armor> armorList = getEquipmentList.getArmorList();
		for (int i = 0; i < armorList.size(); i++) {
			System.out.printf("%d. [%s] 방어력 : %d 가격: %d\n", (i + 1), armorList.get(i).getName(),
					armorList.get(i).getDefense(), armorList.get(i).getPrice());
		}
		System.out.println("0. 뒤로가기");
	}

	// 반지 메뉴 출력
	public void printRingMenu() {
		System.out.println("-------RingMenu-------");
		this.printGold();
		ArrayList<Ring> ringList = getEquipmentList.getRingList();
		for (int i = 0; i < ringList.size(); i++) {
			System.out.printf("%d. [%s] 크리티컬 확률 : %d 가격 : %d\n", (i + 1), ringList.get(i).getName(), ringList.get(i).getCritical(), ringList.get(i).getPrice());
		}
		System.out.println("0. 뒤로가기");
	}

	// 물약 메뉴 출력
	public void printPotionMenu() {
		System.out.println("-------ShopMenu-------");
		this.printGold();
		ArrayList<Potion> potionList = getPotionList.getPotionList();
		for (int i = 0; i < potionList.size(); i++) {
			System.out.printf("%d. [%s] 회복량 : %d 가격 : %d\n", (i + 1), potionList.get(i).getName(), potionList.get(i).getHp(), potionList.get(i).getPrice());
		}
	}

	// 가지고 있는 팔 장비 보여주기
	public void sellEquipmentMenu() {
		System.out.println("-------SELL-------");
		this.printGold();
		ArrayList<Equipment> list = Hero.getEquipments();
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%d. [%s]\n", (i + 1), list.get(i).getName());
		}
	}

	// 물약 구매할 때
	public void buyPotion(Potion potion) {
		System.out.println("-------PotionMenu-------");
		this.printGold();
		if (Hero.getGold() < potion.getPrice()) {
			System.out.println("금액이 모자랍니다.");
		} else {
			Hero.setGold(Hero.getGold() - potion.getPrice());
			if (potion instanceof RedPotion) {
				Hero.setRedPotion(Hero.getRedPotion() + 1);
			} else if (potion instanceof WhitePotion) {
				Hero.setWhitePotion(Hero.getWhitePotion() + 1);
			} else if (potion instanceof Elixir) {
				Hero.setElixir(Hero.getElixir() + 1);
			}
		}
	}

	// 장비 선택
	public Equipment getEquipment(int sel, int idx) {
		Equipment equipment;

		if (sel == 1) {
			if (idx < 0 || idx >= getEquipmentList.getWeaponList().size()) {
				equipment = null;
			} else {
				equipment = getEquipmentList.getWeaponList().get(idx);
			}
		} else if (sel == 2) {
			if (idx < 0 || idx >= getEquipmentList.getArmorList().size()) {
				equipment = null;
			} else {
				equipment = getEquipmentList.getArmorList().get(idx);
			}

		} else {
			if (idx < 0 || idx >= getEquipmentList.getRingList().size()) {
				equipment = null;
			} else {
				equipment = getEquipmentList.getRingList().get(idx);
			}

		}
		return equipment;
	}

	// 물약 선택
	public Potion getPotion(int idx) {
		Potion potion = getPotionList.getPotionList().get(idx);
		return potion;
	}

	// 장비를 구매했을 때
	public void buyEquipment(Equipment equipment) {
		if (Hero.getGold() < equipment.getPrice()) {
			System.out.println("금액이 모자랍니다.");
		} else {
			Hero.setGold(Hero.getGold() - equipment.getPrice());
			Hero.addEquipments(equipment);
		}
	}

	//팔 장비 선택
	public Equipment getSellEquipment(int idx) {
		Equipment equipment = Hero.getEquipments().get(idx);
		return equipment;
	}
	
	// 장비를 판매했을 때
	public void sellEquipment(Equipment equipment) {
		int price = (int) (equipment.getPrice() * 0.7);
		Hero.setGold(Hero.getGold() + price);
		Hero.sellEquipment(equipment);
	}
}
