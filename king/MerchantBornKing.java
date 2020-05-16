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
			System.out.println(this.name + "�� ��������!!");
			if (this.currentLandNumber.turnGold > targetKing.currentLandNumber.turnGold) {
				System.out.println(this.name + "�� �¸��߽��ϴ�." +targetKing.name +"�� ���� ������ 2��ŭ �������ϴ�.");
				if (targetKing.currentLandNumber.turnGold < 2) {
					targetKing.currentLandNumber.turnGold = 0;
				} else
					targetKing.currentLandNumber.turnGold -= 2;

			} else {
				System.out.println(targetKing.name + "�� �¸��߽��ϴ�."+this.name+"�� ���� ������ 1��ŭ �������ϴ�.");
				if (this.currentLandNumber.turnGold < 1) {

				} else
					this.currentLandNumber.turnGold -= 1;
			}
			
			this.holdingGold-=20;
		} else
			System.out.println("��ȭ�� �����մϴ�");
		
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
			this.setAttack(3 + (this.greed / 3)); // greed�� 3�� attack 1 ����
		} else
			System.out.println("��ȭ�� �����մϴ�");
	}

}
