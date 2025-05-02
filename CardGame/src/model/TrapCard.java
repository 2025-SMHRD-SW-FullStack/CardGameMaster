package model;

public class TrapCard extends Card{

	public TrapCard(String name, int power) {
		super(name, power);
		// TODO Auto-generated constructor stub
	}
	
	void trapEffect() {
		System.out.println("특수효과");
	}
	@Override
    public void use(Player self, Player opponent) {
        
      
    }
}
