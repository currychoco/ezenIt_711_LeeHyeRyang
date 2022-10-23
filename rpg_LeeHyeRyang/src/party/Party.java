package party;

import java.util.ArrayList;

import guild.Guild;
import hero.GetHeroList;
import unit.Hero;

public class Party {
	private ArrayList<Hero> list;
	private GetHeroList getHeroList;
	private ArrayList<Hero> guildMembers;

	public Party() {
		list = Hero.getPartyMembers();
		getHeroList = GetHeroList.getInstance();
		guildMembers = getHeroList.getHeroList();
	}

	// 파티 메뉴 출력
	public void printPartyMenu() {
		System.out.println("--------------");
		System.out.println("(1) 파티원 보기");
		System.out.println("(2) 파티원 가입");
		System.out.println("(3) 파티원 탈퇴");
		System.out.println("(0) 뒤로가기");
	}

	// 파티 멤버 리스트
	public void printPartyMembers() {
		if (list.size() == 0) {
			System.out.println("파티원이 없습니다.");
			return;
		}
		System.out.println("--------------");
		int idx = 1;
		for(Hero h : list) {
			System.out.printf("%d. [Lv.%d : %s] hp : %d 공격력 : %d 방어력 : %d 크리티컬 : %d\n", idx, h.getLevel(), h.getName(),
					h.getMAX_HP(), h.getDamage(), h.getDefense(), h.getCritical());
			idx++;
		}
	}

	// 파티에 참여할 길드원 출력
	public void printGuildMembers() {
		int idx = 1;
		for (Hero h : guildMembers) {
			System.out.printf("%d. [Lv.%d : %s] hp : %d 공격력 : %d 방어력 : %d 크리티컬 : %d\n", idx, h.getLevel(), h.getName(),
					h.getMAX_HP(), h.getDamage(), h.getDefense(), h.getCritical());
			idx++;
		}
		System.out.println("0. 뒤로가기");
	}

	// 파티에 참여할 때
	public void joinParty(int idx) {
		
		if(idx < 0 || idx >= guildMembers.size()) {
			System.out.println("해당 번호의 길드원이 없습니다.");
			return;
		}
		
		boolean isExistParty = false;
		for(Hero h : list) {
			if(guildMembers.get(idx).equals(h)) {
				isExistParty=true;
				break;
			}
		}
		if(isExistParty) {
			System.out.println("이미 파티에 존재하는 길드원입니다.");
			return;
		}
		
		if (list.size() == 4) {
			System.out.println("파티원을 더 추가할 수 없습니다.");
			return;
		}
		list.add(guildMembers.get(idx));
		guildMembers.get(idx).setParty(true);
	}

	// 파티에서 나갈 때
	public void exitParty(int idx) {
		if (idx < 0 || idx >= list.size()) {
			System.out.println("존재하지 않는 파티원 번호입니다.");
			return;
		}
		list.remove(idx);
	}
}
