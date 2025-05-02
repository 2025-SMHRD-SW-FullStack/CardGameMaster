package model;

public class HealCard extends Card{
	String type;
	String info;
	
	public HealCard(String name, int power) {
		super(name, power);

	}
	@Override
    public void use(Player self, Player opponent) {
		
		if(self.healBlocked) {
			System.out.println("힐 차단 함정 발동! 회복량 만큼 대신 피해를 받습니다.");
			self.hp -= power;
			self.healBlocked = false;
			return;
		}
		
        self.hp += power;  // 체력 증가
        System.out.println(name + " 카드 사용! " + power + "만큼 힐");
		System.out.println("현재 hp가" + self.hp + "가 남았습니다.");
        
    }
	
	
}
