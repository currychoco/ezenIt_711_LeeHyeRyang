package Main;

import java.util.Scanner;

import dungeon.BossDungeon;
import dungeon.Dungeon1;
import dungeon.Dungeon2;
import dungeon.Dungeon3;
import dungeon.PrintStage;
import equipment.Equipment;
import guild.Guild;
import hero.GetHeroList;
import party.Party;
import potion.Potion;
import shop.Shop;
import unit.Hero;

public class Run {

	private int menu;
	private int sel;
	private int choice;
	private int idx;
	private Scanner sc;
	private Shop shop;
	private Guild guild;
	private GetHeroList getHeroList;
	private Party party;
	private PrintStage stage;
	private Dungeon1 stage1;
	private Dungeon2 stage2;
	private Dungeon3 stage3;
	private BossDungeon bossStage;
	

	public Run() {
		sc = new Scanner(System.in);
		menu = -1;
		sel = -1;
		choice = -1;
		idx = -1;
		shop = new Shop();
		guild = new Guild();
		getHeroList = GetHeroList.getInstance();
		party = new Party();
		stage = new PrintStage();
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
		System.out.println("[1] 길드");
		System.out.println("[2] 파티");
		System.out.println("[3] 상점");
		System.out.println("[4] 전투");
		System.out.println("[0] 게임 끝내기");
	}

	public void run() {
		while (true) {

			// 메뉴 확인
			this.printMainMenu();
			menu = checkInteger();

			// 길드
			if (menu == 1) {
				while (true) {
					guild.printGuildMenu();
					sel = checkInteger();

					// 길드원 확인
					if (sel == 1) {
						guild.printGuildMember();
					}
					// 장비 장착
					else if (sel == 2) {
						guild.printGetEquipmentMember();
						choice = checkInteger();
						idx = choice - 1; // 장비를 장착할 길드원의 인덱스
						if(choice == 0) {
							continue;
						}
						
						guild.printEquipments();
						int num = checkInteger() - 1; // 장비 인덱스
						Equipment equipment = guild.getEquipEquipment(num);

						if (equipment != null) {
							getHeroList.getEquipEquipment(idx, equipment);
						} else {
							System.out.println("존재하지 않는 장비 번호입니다.");
						}

					}
					// 장비 해제
					else if (sel == 3) {
						guild.printMemberToReleaseEquipment();
						choice = checkInteger();
						idx = choice - 1;
						
						guild.printPart();
						int part = checkInteger();
						
						guild.releaseEquipment(idx, part);
					}
					// 뒤로 가기
					else if (sel == 0) {
						break;
					}
				}
			}
			// 파티
			else if (menu == 2) {
				while(true) {
					party.printPartyMenu();
					sel = checkInteger();
					
					// 파티원 출력
					if(sel == 1) {
						party.printPartyMembers();
					}
					// 파티원 가입
					else if(sel == 2) {
						party.printGuildMembers();
						choice = checkInteger(); // Hero인덱스
						idx = choice - 1;
						party.joinParty(idx);
					}
					// 파티원 탈퇴
					else if(sel == 3) {
						party.printPartyMembers();
						choice = checkInteger();
						idx = choice - 1;
						party.exitParty(idx);
					}
					// 뒤로가기
					else if(sel == 0) {
						break;
					}
							
				}
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
						idx = choice - 1;
						Equipment equipment = shop.getEquipment(sel, idx);
						if (equipment != null) {
							shop.buyEquipment(equipment);
						} else if (choice != 0) {
							System.out.println("해당 번호의 장비가 없습니다.");
						}
					}

					// 물약 구매
					else if (sel == 4) {
						// 물약 메뉴 출력
						shop.printPotionMenu();
						choice = checkInteger();
						idx = choice - 1;
						Potion potion = shop.getPotion(idx);
						if (potion != null) {
							shop.buyPotion(potion);
						} else if (choice != 0) {
							System.out.println("해당 번호의 물약이 없습니다.");
						}
					}
					// 물건 판매
					else if (sel == 5) {
						shop.sellEquipmentMenu();
						choice = checkInteger();
						idx = choice - 1;
						Equipment equipment = shop.getSellEquipment(idx);
						if (equipment != null) {
							shop.sellEquipment(equipment);
						} else if (choice != 0) {
							System.out.println("해당 번호의 장비가 없습니다.");
						}

					}
					// 뒤로 가기
					else if (sel == 0) {
						break;
					}
				}

			}
			// 전투하기
			else if (menu == 4) {
				
				if(Hero.getPartyMembers().size() == 0) {
					System.out.println("파티원이 없습니다.");
					continue;
				}

				sel = -1;
				while(sel < 0 || sel > 4) {
					stage.printStage();
					sel = checkInteger();
				}
				
				if(sel == 1) {
					stage1 = new Dungeon1();
					stage1.fight();
				}else if(sel == 2) {
					stage2 = new Dungeon2();
					stage2.fight();
				}else if(sel == 3) {
					stage3 = new Dungeon3();
					stage3.fight();
				}else if(sel == 4) {
					bossStage = new BossDungeon();
					bossStage.fight();
				}
				
			}
			// 종료하기
			else if (menu == 0) {
				System.out.println("게임을 종료합니다.");
				break;
			}
		}

	}
}
