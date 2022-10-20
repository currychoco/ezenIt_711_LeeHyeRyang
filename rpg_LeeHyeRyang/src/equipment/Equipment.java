package equipment;

import java.util.ArrayList;

public class Equipment {
	private static ArrayList<Equipment> equipmentList = new ArrayList<>();
	private String name;
	private int price;

	public Equipment(String name, int price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public static ArrayList<Equipment> getEquipmentList(){
		return equipmentList;
	}
	
	public static void addEquipmentList(Equipment equipment) {
		equipmentList.add(equipment);
	}
}
