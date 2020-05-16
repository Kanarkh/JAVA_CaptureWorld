import java.awt.Color;

public class KnightBornKing extends King {
	int superbia; // ���� 1�� ���ݷ��� 1�� �����Ѵ�, ������ �ö󰥼��� ���ӿ��� �й��� Ȯ���� ����.

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
			System.out.println(this.name + "�� �ϱ��� ��û!!");
			if (this.showAttackData() > targetKing.showAttackData()) {
				System.out.println(this.name + "�� �¸��߽��ϴ�."+targetKing.name +"�� ü���� 2��ŭ �������ϴ�.");
				
					targetKing.changeLife(-2);

			} else {
				System.out.println(targetKing.name + "�� �¸��߽��ϴ�."+this.name+"�� ü���� 1��ŭ �������ϴ�.");
				
				this.changeLife(-1);
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
			this.superbia++;
			this.changeLife(1);
			this.setAttack(3 + (this.superbia));
		} else {
			System.out.println("��尡 �����մϴ�.");
		}
	}
	
	

}
