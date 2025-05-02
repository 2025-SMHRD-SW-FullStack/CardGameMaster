package model;

public class AttackCard extends Card{
	String info;
	String type;
	
	public AttackCard(String name, int power) {
		super(name, power);
		
	}

	 @Override
	    public void use(Player self, Player opponent) {
	        opponent.hp -= power;  // 체력 감소
	        System.out.println(name + " 카드 사용! " + power + " 데미지!");
		 	System.out.println("상대방의 hp가 " + opponent.hp + "가 남았습니다.");
	    }
	
	public String getType() {
        return type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
