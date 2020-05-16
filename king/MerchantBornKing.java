import java.awt.Color;

public class MerchantBornKing extends King {
	int greed;

	public MerchantBornKing(String kingName, Land currentLandNumber, Color color) {
		this.name = kingName;
		this.currentLandNumber = currentLandNumber;
		this.KingLevel = 1;
		this.holdingGold = 1;
		this.setAttack(3);
		this.setLife(10);
		this.color = color;
		this.greed = 1;
		this.setOrigin("Merchant");
	}

	public void tradeWar(King targetKing) {
		if (this.holdingGold >= 20) {
			System.out.println(this.name + "의 무역전쟁!!");
			if (this.currentLandNumber.turnGold > targetKing.currentLandNumber.turnGold) {
				System.out.println(this.name + "이 승리했습니다." +targetKing.name +"의 수도 발전이 2만큼 낮아집니다.");
				if (targetKing.currentLandNumber.turnGold < 2) {
					targetKing.currentLandNumber.turnGold = 0;
				} else
					targetKing.currentLandNumber.turnGold -= 2;

			} else {
				System.out.println(targetKing.name + "이 승리했습니다."+this.name+"의 수도 발전이 1만큼 낮아집니다.");
				if (this.currentLandNumber.turnGold < 1) {

				} else
					this.currentLandNumber.turnGold -= 1;
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
			this.greed++;
			this.changeLife(1);
			this.setAttack(3 + (this.greed / 3)); // greed가 3당 attack 1 증가
		} else
			System.out.println("금화가 부족합니다");
	}

}
