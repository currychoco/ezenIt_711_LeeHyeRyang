package unit;

import java.util.Random;

public class Unit {
	private int level;
	private String name;
	private int max_hp;
	private int hp;
	private int damage;
	private int defense;
	private int critical;
	private int exp;
	private boolean alive;

	public Unit(int level, String name, int hp, int damage, int defense, int critical, int exp) {
		this.level = level;
		this.name = name;
		this.max_hp = hp;
		this.damage = damage;
		this.defense = defense;
		this.critical = critical;
		this.exp = exp;
		this.alive = true;
	}

	public void setMaxHp(int hp) {
		max_hp = hp;
	}
	
	public int getLevel() {
		return level;
	}

	public String getName() {
		return name;
	}

	public int getMAX_HP() {
		return max_hp;
	}

	public int getHp() {
		return hp;
	}

	public int getDamage() {
		return damage;
	}

	public int getDefense() {
		return defense;
	}

	public int getCritical() {
		return critical;
	}

	public int getExp() {
		return exp;
	}

	public boolean getAlive() {
		return alive;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public void setCritical(int critical) {
		this.critical = critical;
	}

	public void setExp(int exp) {
		this.exp = exp;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	// 공격
	public void attack(Unit unit) {
		boolean critical = criticalHit();
		int damage = this.damage + this.level;
		if (critical) {
			damage *= 2;
		}
		unit.setHp(unit.getHp() - damage - unit.defense);
		if (critical) {
			System.out.printf("%s의 치명적인 공격!", this.getName());
		}
		System.out.printf("%s(이)가 %s(을)에게 %d만큼의 데미지를 입혔습니다.", this.getName(), unit.getName(), damage);

		if (unit.hp <= 0) {
			unit.setAlive(false);
		}
	}

	// 크리티컬 공격 유무
	public boolean criticalHit() {
		boolean critical = false;
		Random random = new Random();
		int rNum = random.nextInt(11) + this.getCritical();
		if (rNum > 10) {
			critical = true;
		}
		return critical;
	}

}
