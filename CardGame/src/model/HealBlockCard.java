package model;

public class HealBlockCard extends TrapCard{

	public HealBlockCard(String name, int power) {
		super(name, power);

	}
	
	public void use(Player self, Player bot) {
		bot.healBlocked =  true;
	}

}
