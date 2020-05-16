import java.awt.Color;

public class ThiefBornKing extends King implements Runnable {
	int invidia;
	int skillN;
	boolean blockTax;
	Thread kingTh = null;

	public ThiefBornKing(String kingName, Land currentLandNumber, Color color) {
		this.name = kingName;
		this.currentLandNumber = currentLandNumber;
		this.KingLevel = 1;
		this.holdingGold = 1;
		this.setAttack(3);
		this.setLife(10);
		this.color = color;
		this.invidia = 1;
		blockTax = false;
		this.setOrigin("Thief");
	}

	@Override
	public void run() {
		int timeTemp;
		try {

			this.blockTax = true;
			timeTemp = 5000 + (100 * this.skillN);
			System.out.println(this.name + "���� ���� ����¡���� �����մϴ�," + timeTemp + "ms���� ������ �����մϴ�.");
			Thread.sleep(timeTemp);
			System.out.print(this.name + "���� ����¡�� ���� ������ �Ϸ����ϴ�.");
			System.out.println("�׷��� �������� �λ�����" + this.name + "���� ü���� 5��ŭ �����߽��ϴ�");
			this.blockTax = false;
			this.changeLife(-5);
		} catch (Exception e) {

		}
		this.kingTh = null;
	}

	public void stealLand(King targetKing) {
		if (this.life > 5) {
			if (kingTh == null) {
				this.skillN = (int) (int) (Math.random() * 10) + 1 + this.invidia;

				kingTh = new Thread(this);
				kingTh.start();

			} else {
				System.out.println(this.name + "�� �̹� �������� ������ �Դϴ�.");
			}
			// int raTemp = (int) (Math.random() * 10) + 20;
//			int afTemp;
//
//			System.out.println(this.name + "�ճ⿡ �뵵�� �ߵ�!!!");
//			
//
//			if (targetKing.holdingGold < 2) {
//				System.out.println(
//						"��������, ø���� ���ϸ� "+targetKing.name+"���� �������� �����մϴ�.");
//			} else if (targetKing.holdingGold < raTemp) {
//
//				afTemp = targetKing.holdingGold;
//				targetKing.holdingGold = 0;
//				this.holdingGold += afTemp;
//				System.out.println(
//						"��������, ������ �� \"" + afTemp + "�� ���ƽ��ϴ�. �׷��� �������� �λ�����" + this.name + "���� ü���� 5��ŭ �����߽��ϴ�");
//				this.life -= 5;
//			} else {
//				targetKing.holdingGold -= raTemp;
//				this.holdingGold += raTemp;
//				System.out.println(
//						"��������, ������ �� \"" + raTemp + "�� ���ƽ��ϴ�. �׷��� �������� �λ�����" + this.name + "���� ü���� 5��ŭ �����߽��ϴ�");
//				this.life -= 5;
//			}

		} else
			System.out.println("���� ������ �ϱ⿣ ü���� �����ϴ�.");
	}

	@Override
	public void kingTraining() {
		if (this.holdingGold >= 5) {
			this.holdingGold -= 5;
			this.KingLevel++;
			this.invidia++;
			this.changeLife(1);
			this.setAttack(3 + (this.invidia / 2)); // greed�� 3�� attack 1 ����
		} else
			System.out.println("��ȭ�� �����մϴ�");
	}

	public boolean blockTaxState() {
		return this.blockTax;
	}
}
