import java.awt.Color;

public class KnightBornKing extends King {
	int superbia; // 오만 1당 공격력이 1씩 증가한다, 오만이 올라갈수록 게임에서 패배할 확률이 높다.

	public KnightBornKing(String kingName, Land currentLandNumber, Color color) {
		this.name = kingName;
		this.currentLandNumber = currentLandNumber;
		this.KingLevel = 1;
		this.holdingGold = 1;
		this.superbia=1;
		this.setAttack(3+this.superbia);
		this.setLife(10);
		this.color = color;
		this.setOrigin("Knight");
	}
	
	public void fightManToMan(King targetKing) {
		if (this.holdingGold >= 20) {
			System.out.println(this.name + "의 일기토 신청!!");
			if (this.showAttackData() > targetKing.showAttackData()) {
				System.out.println(this.name + "이 승리했습니다."+targetKing.name +"의 체력이 2만큼 낮아집니다.");
				
					targetKing.changeLife(-2);

			} else {
				System.out.println(targetKing.name + "이 승리했습니다."+this.name+"의 체력이 1만큼 낮아집니다.");
				
				this.changeLife(-1);
			}
			
			this.holdingGold-=20;
		} else
			System.out.println("금화가 부족합니다");
		
		this.frameBlock[0].setLandGold(this.currentLandNumber.turnGold);
		this.frameBlock[24].setLandGold(targetKing.currentLandNumber.turnGold);
	}
	
	@Override
	public void kingTraining() {
		if (this.holdingGold >= 5) {
			this.holdingGold -= 5;
			this.KingLevel++;
			this.superbia++;
			this.changeLife(1);
			this.setAttack(3 + (this.superbia));
		} else {
			System.out.println("골드가 부족합니다.");
		}
	}
	
	

}
