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
			System.out.println(this.name + "왕이 상대방 세금징수를 방해합니다," + timeTemp + "ms동안 작전을 수행합니다.");
			Thread.sleep(timeTemp);
			System.out.print(this.name + "왕의 세금징수 방해 작전이 완료됬습니다.");
			System.out.println("그러나 작전도중 부상으로" + this.name + "왕의 체력이 5만큼 감소했습니다");
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
				System.out.println(this.name + "은 이미 방해작전 수행중 입니다.");
			}
			// int raTemp = (int) (Math.random() * 10) + 20;
//			int afTemp;
//
//			System.out.println(this.name + "왕년에 대도둑 발동!!!");
//			
//
//			if (targetKing.holdingGold < 2) {
//				System.out.println(
//						"작전실패, 첩보에 의하면 "+targetKing.name+"왕이 가진돈이 없다합니다.");
//			} else if (targetKing.holdingGold < raTemp) {
//
//				afTemp = targetKing.holdingGold;
//				targetKing.holdingGold = 0;
//				this.holdingGold += afTemp;
//				System.out.println(
//						"작전성공, 상대방의 돈 \"" + afTemp + "를 훔쳤습니다. 그러나 작전도중 부상으로" + this.name + "왕의 체력이 5만큼 감소했습니다");
//				this.life -= 5;
//			} else {
//				targetKing.holdingGold -= raTemp;
//				this.holdingGold += raTemp;
//				System.out.println(
//						"작전성공, 상대방의 돈 \"" + raTemp + "를 훔쳤습니다. 그러나 작전도중 부상으로" + this.name + "왕의 체력이 5만큼 감소했습니다");
//				this.life -= 5;
//			}

		} else
			System.out.println("왕이 작전을 하기엔 체력이 없습니다.");
	}

	@Override
	public void kingTraining() {
		if (this.holdingGold >= 5) {
			this.holdingGold -= 5;
			this.KingLevel++;
			this.invidia++;
			this.changeLife(1);
			this.setAttack(3 + (this.invidia / 2)); // greed가 3당 attack 1 증가
		} else
			System.out.println("금화가 부족합니다");
	}

	public boolean blockTaxState() {
		return this.blockTax;
	}
}
