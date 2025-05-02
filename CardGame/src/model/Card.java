package model;

public abstract class Card {
	String name; //카드이름
	int power; // 카드 효과 수치(데미지 또는 힐량)
	
	public Card(String name, int power) {
		super();
		this.name = name;
		this.power = power;
	}
	
	 public abstract void use(Player self, Player opponent);
}
	
