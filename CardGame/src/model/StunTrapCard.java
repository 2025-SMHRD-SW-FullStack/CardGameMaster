package model;

public class StunTrapCard extends TrapCard{
	String info;
	

	
	
	
	public StunTrapCard(String name, int value, String info2) {
		super(name, value);
		this.info = info2;
	}


	public void use(Player self, Player bot) {
		bot.stunned =  true;
		System.out.println(info);
	}
}
