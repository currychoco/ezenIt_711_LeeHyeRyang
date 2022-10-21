package party;

import java.util.ArrayList;

import unit.Hero;

public class Party {
	private ArrayList<Hero> list;
	
	public Party() {
		list = Hero.getPartyMembers();
	}
	
	// 파티 멤버 리스트
	public void printPartyMembers() {
		System.out.println("--------------");
		for(int i = 0;i<list.size();i++) {
			System.out.printf("%d. [Lv.%d : %s] 공격력 : %d 방어력 : %d 크리티컬 : %d", (i+1),list.get(i).getLevel());
		}
	}
	
	// 파티에 참여할 때
	public void joinParty(Hero hero) {
		list.add(hero);
		hero.setParty(true);
	}
	
	// 파티에 참여하지 않을 때
}
