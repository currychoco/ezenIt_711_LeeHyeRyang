package dungeon;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import booty.Booty;
import moster.Bat;
import moster.Boss;
import moster.Ghost;
import moster.Zombi;
import potion.Elixir;
import potion.RedPotion;
import potion.WhitePotion;
import unit.Hero;
import unit.Monster;

public class Dungeon {
	private ArrayList<Monster> monsterList;
	private ArrayList<Hero> heroList;
	private Monster monster;
	private int potionNum;
	private int deadMonsterCnt;
	private int deadHeroCnt;
	private Booty booty;
	private Random random;
	private RedPotion redPotion;
	private WhitePotion whitePotion;
	private Elixir elixir;
	private final int PER_OF_MONSTER = 5;
	private final int FIXED_MOSTER = 3;
	private final int FIXED_POTION = 10;
	private Scanner sc;

	protected Dungeon(Monster monster) {
		this.monster = monster;
		monsterList = new ArrayList<Monster>();
		this.heroList = Hero.getPartyMembers();
		this.random = new Random();
		potionNum = FIXED_POTION;
		redPotion = new RedPotion();
		whitePotion = new WhitePotion();
		elixir = new Elixir();
		this.booty = new Booty(monster.getExp(), monster.getGold());
		sc = new Scanner(System.in);
		this.deadHeroCnt = 0;
		this.deadMonsterCnt = 0;
	}
	
	
	// 몬스터 수 랜덤 생성
	private void createMonster() {
		int rNum = random.nextInt(PER_OF_MONSTER) + FIXED_MOSTER;
		if(monster instanceof Bat) {
			for(int i =0;i<rNum;i++) {
				monsterList.add(new Bat());
			}
		}else if(monster instanceof Zombi) {
			for(int i =0;i<rNum;i++) {
				monsterList.add(new Zombi());
			}
		}else if(monster instanceof Ghost) {
			for(int i =0;i<rNum;i++) {
				monsterList.add(new Ghost());
			}
		}else {
			for(int i =0;i<rNum;i++) {
				monsterList.add(new Boss());
			}
		}
	}

	// 전투 선택지
	private void printFightMenu() {
		System.out.println("1. 공격");
		System.out.println("2. 물약 마시기");
		System.out.println("3. 아무것도 하지 않기");
		System.out.println("4. 도망가기");
		System.out.print("입력 : ");
	}

	// 몬스터 출력
	private void printMonsterList() {
		int idx = 1;
		for (Monster m : monsterList) {
			System.out.printf("%d. [Lv.%d : %s] hp %d/%d\n", idx, m.getLevel(), m.getName(), m.getHp(), m.getMAX_HP());
			idx++;
		}
		System.out.print("입력 : ");
	}

	// 몬스터의 파티원 공격
	private void monsterAttack(Monster monster) {
		int rIdx = -1;
		boolean heroAlive = false;
		while(!heroAlive) {
			 rIdx = random.nextInt(heroList.size());
			 if(!heroList.get(rIdx).getAlive()) {
				 heroAlive = false;
			 }else {
				 heroAlive = true;
			 }
		}
		
		monster.attack(heroList.get(rIdx));

		if (heroList.get(rIdx).getHp() <= 0) {
			System.out.printf("%s가 쓰러졌습니다!!!\n", heroList.get(rIdx).getName());
			heroList.get(rIdx).setAlive(false);
			deadHeroCnt++;
		}
	}

	// 파티원의 몬스터 공격
	private void heroAttack(Hero hero) {
		int idx = -1;
		while(true) {
			while (idx < 0 || idx >= monsterList.size()) {
				this.printMonsterList();
				idx = sc.nextInt() - 1;
			}
			
			if(!monsterList.get(idx).getAlive()) {
				System.out.println("이미 처리된 몬스터입니다.");
				idx = -1;
				continue;
			}else {
				break;
			}
		}
		
		hero.attack(monsterList.get(idx));
		if (monsterList.get(idx).getHp() <= 0) {
			System.out.printf("%s(%d)을(를) 쓰러뜨렸습니다!\n", monsterList.get(idx).getName(), (idx + 1));
			monster.setAlive(false);
			deadMonsterCnt++;
			for (Hero h : heroList) {
				h.killMoster(booty);
			}
		}
	}

	// 물약 섭취
	private void drinkPotion(Hero hero) {
		System.out.printf("포션복용 가능 횟수 : %d회\n", potionNum);
		if (potionNum == 0) {
			System.out.println("더이상 포션을 마실 수 없습니다.");
			return;
		}
		int sel = -1;
		while (sel < 0 || sel > 3) {
			System.out.printf("1. 빨간포션 : %d개\n", Hero.getRedPotion());
			System.out.printf("2. 하얀포션 : %d개\n", Hero.getWhitePotion());
			System.out.printf("3. 엘릭서 : %d개\n", Hero.getElixir());
			System.out.println("0. 뒤로가기");
			sel = sc.nextInt();
		}
		if (sel == 1) {
			if (Hero.getRedPotion() == 0) {
				System.out.println("빨간물약이 없습니다.");
			} else {
				hero.setHp(hero.getHp() + redPotion.getHp());
			}
		} else if (sel == 2) {
			if (Hero.getWhitePotion() == 0) {
				System.out.println("하얀물약이 없습니다.");
			} else {
				hero.setHp(hero.getHp() + whitePotion.getHp());
			}
		} else if (sel == 3) {
			if (Hero.getElixir() == 0) {
				System.out.println("엘릭서가 없습니다.");
			} else {
				hero.setHp(hero.getHp() + elixir.getHp());
			}
		}
		potionNum--;
	}

	// 몬스터 전멸 체크
	public boolean allDeadMonster() {
		if (monsterList.size() == deadMonsterCnt) {
			System.out.println("모든 몬스터를 물리쳤습니다!!!");
			return true;
		}
		return false;
	}

	// 파티원 전멸 체크
	public boolean allDeadHero() {
		if (heroList.size() == deadHeroCnt) {
			System.out.println("파티원이 전멸했습니다...");
			return true;
		}
		return false;
	}

	// 던전에서 도망칠 때
	public boolean runAway() {
		int rNum = random.nextInt(monsterList.size());
		if (rNum == 0) {
			return false;
		} else {
			return true;
		}
	}

	// 전투
	public void fight() {
		createMonster();

		boolean allDeadMonster = false;
		boolean allDeadHero = false;
		boolean runAway = false;
		while (true) {
			for (Hero h : heroList) {
				if(!h.getAlive()) {
					continue;
				}
				System.out.println("-------전투-------");
				System.out.printf("[Lv.%d : %s] hp : %d/%d\n", h.getLevel(), h.getName(), h.getHp(), h.getMAX_HP());
				
				int menu = -1;
				while(menu<1 || menu > 4) {
					printFightMenu();
					menu = sc.nextInt();
				}
				
				// 공격
				if (menu == 1) {
					heroAttack(h);
				}
				// 물약마시기
				else if (menu == 2) {
					drinkPotion(h);
				}
				// 아무것도 하지 않기
				else if (menu == 3) {
					System.out.println("다음 기회를 노립니다.");
				}
				// 도망치기
				else if (menu == 4) {
					System.out.println("파티원들과 모두 도망치기로 합의했습니다!");
					runAway = runAway();
					if (runAway) {
						System.out.println("던전에서 벗어납니다.");
						break;
					} else {
						System.out.println("몬스터들이 도망치는 것을 방해했습니다! 던전에서 벗어날 수 없습니다..!");
					}
				}
				if(allDeadMonster()) {
					allDeadMonster = true;
					break;
				}
			}
			if(runAway) {
				break;
			}
			if(allDeadMonster) {
				break;
			}
			
			for(int i =0;i<monsterList.size();i++) {
				if(monsterList.get(i).getAlive()) {
					System.out.printf("%d번 ", (i+1));
					monsterAttack(monsterList.get(i));
					
					if(allDeadHero()) {
						allDeadHero = true;
						break;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			if(allDeadHero) {
				break;
			}
		}
		
		// 영웅 피 초기화
		for(Hero h:heroList) {
			h.setHp(h.getMAX_HP());
		}
	}
}
