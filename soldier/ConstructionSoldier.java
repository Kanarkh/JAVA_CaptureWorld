import java.awt.Color;

public class ConstructionSoldier extends Soldier implements Runnable {

	
	public ConstructionSoldier(String soldierName, King belngKing) {
		this.name = soldierName;
		this.belongKing = belngKing;
	}

	@Override
	public void run() {
		boolean onDuty = true;
		int tempBattle;

		this.mapSize = this.belongKing.nationalMap.mapSize;
		this.setMoveLength(this.mapSize, this.targetLandNumber, this.currentLandNumber.landNumber);

		f: while (onDuty) {
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				break f;
			}

			if (this.currentLandNumber.landNumber != this.targetLandNumber) {
				// �������� �������� ���ߴ�
				if (thisLandOurs() == 2) {
					// ������
					if (this.currentLandNumber.showBattleStatus() == true) {

						// �Ʊ��� �������϶� ���������� �Բ� ������ �����ߴٰ� ���������� �ִ�.
						this.currentLandNumber.offensivePlayer.add(this);
						while (this.currentLandNumber.showBattleStatus()) {
							try { // ������ ���������� Ȯ���ϸ� ��ٸ���.
								Thread.sleep(1000);

							} catch (Exception e) {
								// e.printStackTrace();
								break f;
							} // �׳� ���� CPUȮ �ö󰣴�.
						}

						if (this.showLifeData() <= 0) {// ������ ����ϸ� �ӹ���
							break f;
						} else { // ������ ������ �ڽ��� �ӹ��� �����Ѵ�.
							if (this.currentLandNumber.offensivePlayer.contains(this)) {
								this.currentLandNumber.offensivePlayer.remove(this);
							}
							if (this.currentLandNumber.defensivePlayer.contains(this)) {
								this.currentLandNumber.defensivePlayer.remove(this);
							}
						}

						try {
							if (this.movingSpeed == 1)
								Thread.sleep(1000);
							else if (this.movingSpeed == 2)
								Thread.sleep(500);
						} catch (Exception e) {
							break f;
						}
						this.currentLandNumber = this.land[nextLand()];

					} else {
						// �Ʊ��� ���µ� �������̴�. �̰ܾ� �������� �ִ�.
						// ������ �����ؼ� �̰ܾ� ��������.
						this.currentLandNumber.chagebattleStatus(true);
						this.currentLandNumber.offensivePlayer.add(this);
						// ������ ����������.
						while (this.currentLandNumber.showBattleStatus()) {
							tempBattle = inBattle(this.currentLandNumber.offensivePlayer,
									this.currentLandNumber.defensivePlayer);

							if (tempBattle == 0) { // ���º�
								// �����̸� ����ϴµ� ��� ���� ����� �غ���.
								// ������ �߿� �շ��� ����� �ڿ������� ����Ʈ�� �߰��Ǵϱ� ������ ������?
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									break f;
								}
								System.out.println("6");
								continue;
							} else if (tempBattle == 1) { // �츮�� �¸�
								// �̰����� �������� �ش� ���� ���Ҽ����� �����.

								this.subtractLandSupplyRouteVerification(); // �������޷� ��ȭ

								this.currentLandNumber.landOwner = null;
//								this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
								try {
									if (this.movingSpeed == 1)
										Thread.sleep(1000);
									else if (this.movingSpeed == 2)
										Thread.sleep(500);
								} catch (Exception e) {
									break f;
								}
								this.currentLandNumber.chagebattleStatus(false);
								this.currentLandNumber = this.land[nextLand()];
								continue f;
							} else if (tempBattle == 2) { // ���� �¸�
								// �׾����� �ӹ��� ������.
								this.currentLandNumber.chagebattleStatus(false);
								break f;
							} else if (tempBattle == 3) { // ���� ����
								// �׾����� �ӹ��� ������.
								// ���浵 ���� ��ų�� ������ ���� ���Ҽ�ȭ �Ѵ�.
								this.subtractLandSupplyRouteVerification(); // ���޷� ��ȭ
								this.currentLandNumber.landOwner = null;
//								this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
								this.currentLandNumber.chagebattleStatus(false);
								break f;
							}
						}
					}

				} else { // �츮�� or ����ִ¶�

					if (this.currentLandNumber.showBattleStatus()) { // �ش� ���� �������̴�
						this.currentLandNumber.defensivePlayer.add(this);
						while (this.currentLandNumber.showBattleStatus()) {
							try {
								Thread.sleep(1000);

							} catch (Exception e) {
								break f;
							} // �׳� ���� CPUȮ �ö󰣴�.
						}
						;
						if (this.showLifeData() <= 0) {
							break f;
						} else {
							if (this.currentLandNumber.offensivePlayer.contains(this)) {
								this.currentLandNumber.offensivePlayer.remove(this);
							}
							if (this.currentLandNumber.defensivePlayer.contains(this)) {
								this.currentLandNumber.defensivePlayer.remove(this);
							}
						}
					}

					try {
						if (this.movingSpeed == 1)
							Thread.sleep(2000);
						else if (this.movingSpeed == 2)
							Thread.sleep(1000);
					} catch (Exception e) {
						break f;
					}
					this.currentLandNumber = this.land[nextLand()];
				}

			} else {
				// �������� �����ߴ�.
				if (thisLandOurs() == 0) {
					// ���ξ��� ��
					this.currentLandNumber.landOwner = this.belongKing;
					this.currentLandNumber.defensivePlayer.add(this);
//					this.frame.labelLandColorChange(this.targetLandNumber, this.belongKing.color);
					this.addLandSupplyRouteVerification();
					
					///�ǹ����� �ൿ///
					
					onDuty = false;
					break f;
				} else if (thisLandOurs() == 1) {
					// �츮����
					this.currentLandNumber.defensivePlayer.add(this);
					
					///�ǹ����� �ൿ///
					
					onDuty = false;
					break f;
				} else if (thisLandOurs() == 2) { 
					// ������
					// offensivePlayer�� �츮���� ������ �ڽ��� �� ����Ʈ�� �����ϰ� ������ -> ������ ���� ������ �̸� ��� �����Ѵ�. -> �̰�
					// ���� �߸��ϸ� ���׳��ڴµ�? �о�������� ������ �� �������µ� ������� ���µ� ������ ���������� ���ڳ�.
					if (this.currentLandNumber.offensivePlayer.size() > 0) {
						// ������ �ο���ִ� ������ �ִ�.
						this.currentLandNumber.offensivePlayer.add(this);
						onDuty = false;
						break f;
					} else {
						// ���� ����� ���� ȥ�ڴ�.
						this.currentLandNumber.offensivePlayer.add(this);
						this.currentLandNumber.chagebattleStatus(true);
						// ������ ����������.
						while (this.currentLandNumber.showBattleStatus()) {

							tempBattle = inBattle(this.currentLandNumber.offensivePlayer,
									this.currentLandNumber.defensivePlayer);

							if (tempBattle == 0) { // ���º�
								// �����̸� ����ϴµ� ��� ���� ����� �غ���.
								// ������ �߿� �շ��� ����� �ڿ������� ����Ʈ�� �߰��Ǵϱ� ������ ������?
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									break f;
								}
								continue;

							} else if (tempBattle == 1) { // �츮�� �¸�
								// ������ ������ �¸��ߴ�
								this.subtractLandSupplyRouteVerification();
								this.currentLandNumber.landOwner = this.belongKing;
//								this.frame.labelLandColorChange(this.targetLandNumber, this.belongKing.color);

								// ��ġ�� �ű��.
								this.currentLandNumber.defensivePlayer.clear();
								for (int i = this.currentLandNumber.offensivePlayer.size() - 1; i >= 0; i--) {
									this.currentLandNumber.defensivePlayer
											.add(this.currentLandNumber.offensivePlayer.get(i));
									this.currentLandNumber.offensivePlayer.remove(i);
								}
								this.currentLandNumber.offensivePlayer.clear();
								this.addLandSupplyRouteVerification();
								this.currentLandNumber.chagebattleStatus(false);
								break f;
							} else if (tempBattle == 2) { // ���� �¸�
								// �׾����� �ӹ��� ������.
								this.currentLandNumber.chagebattleStatus(false);
								break f;
							} else if (tempBattle == 3) { // ���� ����
								// �׾����� �ӹ��� ������.
								// ���浵 ���� ��ų�� ������ ���� ���Ҽ�ȭ �Ѵ�.
								this.subtractLandSupplyRouteVerification();
								this.currentLandNumber.landOwner = null;
//								this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
								this.currentLandNumber.chagebattleStatus(false);
								this.addLandSupplyRouteVerification();
								break f;
							}
						}
					}

				}
			}

		} // end while
//		System.out.println("end");

	}// end run
	public void ConstructionBuilding() {
		// ��ɵ� ��ġ�� �ǹ��� �����.

	}
}
