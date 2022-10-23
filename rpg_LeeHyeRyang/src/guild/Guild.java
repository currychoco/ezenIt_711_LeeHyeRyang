package guild;

import java.util.ArrayList;

import equipment.Armor;
import equipment.Equipment;
import equipment.Weapon;
import hero.GetHeroList;
import unit.Hero;

public class Guild {

	private GetHeroList getHeroList;
	private ArrayList<Hero> list;

	public Guild() {
		getHeroList = GetHeroList.getInstance();
		list = getHeroList.getHeroList();
	}

	// 길드 메뉴 출력
	public void printGuildMenu() {
		System.out.println("(1) 길드원 확인");
		System.out.println("(2) 장비 장착");
		System.out.println("(3) 장비 해제");
		System.out.println("(0) 뒤로 가기");
	}

	// 길드원 출력
	public void printGuildMember() {
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%d. [Lv.%d : %s] hp : %d 공격력 : %d 방어력 : %d 크리티컬확률 : %d 경험치 : %d / %d\n", (i + 1),
					list.get(i).getLevel(),list.get(i).getName(), list.get(i).getMAX_HP(), list.get(i).getDamage(), list.get(i).getDefense(),
					list.get(i).getCritical(), list.get(i).getExp(), list.get(i).getMAX_EXP());
			System.out.printf("무기 : %s 방어구 : %s 반지 : %s 파티가입 : %s\n",
					list.get(i).isEquipWeapon() ? list.get(i).getWeapon().getName() : "-",
					list.get(i).isEquipArmor() ? list.get(i).getArmor().getName() : "-",
					list.get(i).isEquipRing() ? list.get(i).getRing().getName() : "-", list.get(i).getParty());
			System.out.println("---------------------");
		}

	}

	// 장비 장착할 길드원 출력
	public void printGetEquipmentMember() {
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%d. [%s]\n", (i + 1), list.get(i).getName());
		}
		System.out.println("0. 뒤로가기");
	}

	// 인벤토리에 있는 장착할 수 있는 아이템 출력
	public void printEquipments() {
		Equipment equipment;
		for (int i = 0; i < Hero.getEquipments().size(); i++) {
			equipment = Hero.getEquipments().get(i);
			System.out.printf("%d. [%s] ", (i + 1), equipment.getName());
			if (Hero.getEquipments().get(i) instanceof Weapon) {
				System.out.printf("공격력 : %d\n", equipment.getDamage());
			} else if (Hero.getEquipments().get(i) instanceof Armor) {
				System.out.printf("방어력 : %d\n", equipment.getDefense());
			} else {
				System.out.printf("크리티컬 : %d\n", equipment.getCritical());
			}
		}
	}

	// 장착할 장비 선택
	public Equipment getEquipEquipment(int idx) {
		Equipment equipment;
		if (idx < 0 || idx >= Hero.getEquipments().size()) {
			equipment = null;
		} else {
			equipment = Hero.getEquipments().get(idx);
		}
		return equipment;
	}

	// 장비 해제할 길드원 출력
	public void printMemberToReleaseEquipment() {
		for (int i = 0; i < list.size(); i++) {
			System.out.printf("%d. [Lv.%d : %s] : ", (i + 1),list.get(i).getLevel(), list.get(i).getName());
			System.out.printf("무기 : %s 방어구 : %s 반지 : %s\n",
					list.get(i).isEquipWeapon() ? list.get(i).getWeapon().getName() : "-",
					list.get(i).isEquipArmor() ? list.get(i).getArmor().getName() : "-",
					list.get(i).isEquipRing() ? list.get(i).getRing().getName() : "-");
			System.out.println("---------------------");
		}
	}
	
	// 해제할 부위 출력
	public void printPart() {
		System.out.println("1. 무기");
		System.out.println("2. 방어구");
		System.out.println("3. 무기");
	}
	
	// 장비 해제
	public void releaseEquipment(int idx, int part) {
		if(idx < 0 || idx >= list.size() || part < 1 || part > 3) {
			System.out.println("유효하지 않은 범위입니다.");
			return;
		}
		if(part == 1) {
			list.get(idx).releaseWeapon();
		}else if(part == 2) {
			list.get(idx).releaseArmor();;
		}else {
			list.get(idx).releaseRing();;
		}
	}
}
