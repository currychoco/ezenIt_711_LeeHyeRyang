package potion;

import java.util.ArrayList;

public class GetPotionList {
	private ArrayList<Potion> potionList;

	private static GetPotionList instance = new GetPotionList();

	public static GetPotionList getInstance() {
		return instance;
	}

	private GetPotionList() {
		potionList = new ArrayList<>();
		potionList.add(new RedPotion());
		potionList.add(new WhitePotion());
		potionList.add(new Elixir());

	}

	// 물약 리스트
	public ArrayList<Potion> getPotionList() {
		return potionList;
	}
}
