package Main;

import java.util.ArrayList;
import java.util.Scanner;

import equipment.Equipment;
import equipment.GetEquipmentList;
import equipment.Weapon;
import potion.GetPotionList;
import shop.Shop;

public class Run {

	private int menu;
	private int sel;
	private int choice;
	private Scanner sc;
	private Shop shop;
	private GetEquipmentList getEquipmentList;
	private GetPotionList getPotionList;

	public Run() {
		sc = new Scanner(System.in);
		menu = -1;
		sel = -1;
		choice = -1;
		shop = new Shop();
		getEquipmentList = GetEquipmentList.getInstance();
		getPotionList = GetPotionList.getInstance();
	}

	// 정수 확인 메소드
	public int checkInteger() {
		int num = -1;
		while (num == -1) {
			System.out.print("번호 입력 : ");
			try {
				num = Integer.parseInt(sc.next());
			} catch (Exception e) {
				System.out.println("정수를 입력해 주십시오");
				num = -1;
			}
		}
		return num;
	}

	public void printMainMenu() {
		System.out.println("-------Menu-------");
		System.out.println("[1] 길드원 보기");
		System.out.println("[2] 파티원 보기");
		System.out.println("[3] 상점 보기");
		System.out.println("[4] 전투하기");
		System.out.println("[5] 저장하기");
		System.out.println("[6] 불러오기");
		System.out.println("[0] 게임 끝내기");
	}

	public void run() {
		while (true) {

			// 메뉴 확인
			this.printMainMenu();
			menu = checkInteger();

			// 길드원 보기
			if (menu == 1) {

			}
			// 파티원 보기
			else if (menu == 2) {

			}
			// 상점 보기
			else if (menu == 3) {
				while (true) {
					shop.printMenu();
					sel = checkInteger();

					// 장비 구매
					if (sel == 1 || sel == 2 || sel == 3) {

						if (sel == 1) {
							// 무기 메뉴 출력
							shop.printWeaponMenu();
						} else if (sel == 2) {
							// 방어구 메뉴 출력
							shop.printArmorMenu();
						} else if (sel == 3) {
							// 반지 메뉴 출력
							shop.printRingMenu();
						}
						choice = checkInteger();
						int idx = choice - 1;
						Equipment equipment = shop.getEquipment(sel, idx);
						if (equipment != null) {
							shop.buyEquipment(equipment);
						} else {
							System.out.println("해당 번호의 장비가 없습니다.");
						}
					}
					
					// 물약 구매
					else if (sel == 4) {
						// 물약 메뉴 출력
						shop.printPotionMenu();
					}
					// 물건 판매
					else if (sel == 5) {

					}
					// 뒤로 가기
					else if (sel == 0) {
						break;
					}
				}

			}
			// 전투하기
			else if (menu == 4) {

			}
			// 저장하기
			else if (menu == 5) {

			}
			// 불러오기
			else if (menu == 6) {

			}
			// 종료하기
			else if (menu == 0) {
				System.out.println("게임을 종료합니다.");
				break;
			}
		}

	}
}
