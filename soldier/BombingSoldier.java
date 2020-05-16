import java.awt.Color;
import java.util.LinkedList;

public class BombingSoldier extends Soldier implements Runnable {

	private int explosivePower;

	public BombingSoldier(String soldierName, King belngKing) {
		this.name = soldierName;
		this.belongKing = belngKing;
		this.explosivePower = 2;
		this.frameBlock = belongKing.frameBlock;
		this.movingSpeed=2;
	}

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
			soldierCount();
			if (this.currentLandNumber.landNumber != this.targetLandNumber) {
				// �������� �������� ���ߴ�
				if (thisLandOurs() == 2) {
					// ������
					if (this.currentLandNumber.showBattleStatus() == true) {

						// �Ʊ��� �������϶� ���������� �Բ� ������ �����ߴٰ� ���������� �ִ�.
						tempBattle = suicideBombing(this.currentLandNumber.offensivePlayer,
								this.currentLandNumber.defensivePlayer);
						//this.currentLandNumber.offensivePlayer.add(this);
						soldierCount();
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
						soldierCount();
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

							tempBattle = suicideBombing(this.currentLandNumber.offensivePlayer,
									this.currentLandNumber.defensivePlayer);
							
							if (tempBattle == 0) { // ���º�
								// �����̸� ����ϴµ� ��� ���� ����� �غ���.
								// ������ �߿� �շ��� ����� �ڿ������� ����Ʈ�� �߰��Ǵϱ� ������ ������?
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									break f;
								}
//								System.out.println("6");
								soldierCount();
								continue;
							} else if (tempBattle == 1) { // �츮�� �¸�
								// �̰����� �������� �ش� ���� ���Ҽ����� �����.

								this.subtractLandSupplyRouteVerification(); // �������޷� ��ȭ

								this.currentLandNumber.landOwner = null;
								this.currentLandNumber.supplyRoute = false;
								//this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
//							
								setGui(Color.white, "���ξ���");

								if (this.currentLandNumber.offensivePlayer.contains(this)) {
									this.currentLandNumber.offensivePlayer.remove(this);
								}
								if (this.currentLandNumber.defensivePlayer.contains(this)) {
									this.currentLandNumber.defensivePlayer.remove(this);
								}

								try {
									if (this.movingSpeed == 1)
										Thread.sleep(1000);
									else if (this.movingSpeed == 2)
										Thread.sleep(500);
								} catch (Exception e) {
									break f;
								}
								soldierCount();
								this.currentLandNumber.chagebattleStatus(false);
								this.currentLandNumber = this.land[nextLand()];
								continue f;
							} else if (tempBattle == 2) { // ���� �¸�
								// �׾����� �ӹ��� ������.
								this.currentLandNumber.chagebattleStatus(false);
								soldierCount();
								break f;
							} else if (tempBattle == 3) { // ���� ����
								// �׾����� �ӹ��� ������.
								// ���浵 ���� ��ų�� ������ ���� ���Ҽ�ȭ �Ѵ�.
								this.subtractLandSupplyRouteVerification(); // ���޷� ��ȭ
								this.currentLandNumber.landOwner = null;
								//this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
								this.frameBlock[this.currentLandNumber.landNumber].setLandColor(Color.white);
								this.currentLandNumber.supplyRoute = false;

								setGui(Color.white, "���ξ���");

								this.currentLandNumber.chagebattleStatus(false);
								break f;
							}
						}
					}

				} else { // �츮�� or ����ִ¶�

					if (this.currentLandNumber.showBattleStatus()) { // �ش� ���� �������̴�
						tempBattle = suicideBombing(this.currentLandNumber.offensivePlayer,
								this.currentLandNumber.defensivePlayer);
						//this.currentLandNumber.defensivePlayer.add(this);
						soldierCount();
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
					soldierCount();
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
					//this.frame.labelLandColorChange(this.targetLandNumber, this.belongKing.color);
					this.setGui(this.belongKing.color, this.belongKing.name);

					this.addLandSupplyRouteVerification();
					onDuty = false;
					break f;
				} else if (thisLandOurs() == 1) {
					// �츮����
					//this.currentLandNumber.defensivePlayer.add(this);
					tempBattle = suicideBombing(this.currentLandNumber.offensivePlayer,
							this.currentLandNumber.defensivePlayer);
					soldierCount();
					onDuty = false;
					break f;
				} else if (thisLandOurs() == 2) {
					// ������
					// offensivePlayer�� �츮���� ������ �ڽ��� �� ����Ʈ�� �����ϰ� ������ -> ������ ���� ������ �̸� ��� �����Ѵ�. -> �̰�
					// ���� �߸��ϸ� ���׳��ڴµ�? �о�������� ������ �� �������µ� ������� ���µ� ������ ���������� ���ڳ�.
					if (this.currentLandNumber.offensivePlayer.size() > 0) {
						// ������ �ο���ִ� ������ �ִ�.
						this.currentLandNumber.offensivePlayer.add(this);
						soldierCount();
						onDuty = false;
						break f;
					} else {
						// ���� ����� ���� ȥ�ڴ�.
						this.currentLandNumber.offensivePlayer.add(this);
						soldierCount();
						this.currentLandNumber.chagebattleStatus(true);
						// ������ ����������.
						while (this.currentLandNumber.showBattleStatus()) {

							tempBattle = suicideBombing(this.currentLandNumber.offensivePlayer,
									this.currentLandNumber.defensivePlayer);

							if (tempBattle == 0) { // ���º�
								// �����̸� ����ϴµ� ��� ���� ����� �غ���.
								// ������ �߿� �շ��� ����� �ڿ������� ����Ʈ�� �߰��Ǵϱ� ������ ������?
								soldierCount();
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
								//this.frame.labelLandColorChange(this.targetLandNumber, this.belongKing.color);
								this.frameBlock[this.currentLandNumber.landNumber].setLandColor(this.belongKing.color);

								// ��ġ�� �ű��.
								this.currentLandNumber.defensivePlayer.clear();
								for (int i = this.currentLandNumber.offensivePlayer.size() - 1; i >= 0; i--) {
									this.currentLandNumber.defensivePlayer
											.add(this.currentLandNumber.offensivePlayer.get(i));
									this.currentLandNumber.offensivePlayer.remove(i);
								}
								this.currentLandNumber.offensivePlayer.clear();
								this.addLandSupplyRouteVerification();

								this.setGui(this.belongKing.color, this.belongKing.name);
								this.currentLandNumber.chagebattleStatus(false);
								break f;
							} else if (tempBattle == 2) { // ���� �¸�
								// �׾����� �ӹ��� ������.
								soldierCount();
								this.currentLandNumber.chagebattleStatus(false);
								break f;
							} else if (tempBattle == 3) { // ���� ����
								// �׾����� �ӹ��� ������.
								// ���浵 ���� ��ų�� ������ ���� ���Ҽ�ȭ �Ѵ�.
								this.subtractLandSupplyRouteVerification();
								this.currentLandNumber.landOwner = null;
								//this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
								this.currentLandNumber.supplyRoute = false;
//								this.frameBlock[this.currentLandNumber.landNumber].setLandColor(Color.white);
								setGui(Color.white, "���ξ���");

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
	
//	public void run() {
//		boolean onDuty = true;
//		int tempBattle;
//		// ������ ���� ��ġ�ϴ��� �����Ѵ�.
//		// ������ ���� �ƴ϶�� ���� ��ġ�� �̵��Ѵ�
//
//		// �������� �����Ҷ����� ��� �ݺ��Ѵ�.
//		f: while (onDuty) {
//			try {
//				Thread.sleep(1);
//			} catch (Exception e) {
//				break f;
//			}
//			if (this.currentLandNumber.landNumber != this.targetLandNumber) {
//				// �������� �������� ���ߴ�
//				if (thisLandOurs() == 2) {
//					// ������
//					if (this.currentLandNumber.showBattleStatus() == true) {
//						// �Ʊ��� ���� ������ ��������
//						this.currentLandNumber.offensivePlayer.add(this);
//						while (this.currentLandNumber.showBattleStatus()) {
//							try {
//								Thread.sleep(1000);
//
//							} catch (Exception e) {
//								break f;
//							} // �׳� ���� CPUȮ �ö󰣴�.
//						}
//						;
//						if (this.showLifeData() <= 0) {
//							break f;
//						} else {
//							if (this.currentLandNumber.offensivePlayer.contains(this)) {
//								this.currentLandNumber.offensivePlayer.remove(this);
//							}
//							if (this.currentLandNumber.defensivePlayer.contains(this)) {
//								this.currentLandNumber.defensivePlayer.remove(this);
//							}
//						}
//
//						try {
//							if (this.movingSpeed == 1)
//								Thread.sleep(1000);
//							else if (this.movingSpeed == 2)
//								Thread.sleep(500);
//						} catch (Exception e) {
//							break f;
//						}
//						this.currentLandNumber = this.land[nextLand()];
//
//					} else {
//						// �Ʊ��� ���µ� �������̴�. �̰ܾ� �������� �ִ�.
//						// ������ �����ؼ� �̰ܾ� ��������.
//						this.currentLandNumber.chagebattleStatus(true);
//						this.currentLandNumber.offensivePlayer.add(this);
//						// ������ ����������.
//						while (this.currentLandNumber.showBattleStatus()) {
//							tempBattle = this.suicideBombing(this.currentLandNumber.offensivePlayer,
//									this.currentLandNumber.defensivePlayer);
//
//							if (tempBattle == 0) { // ���º�
//								this.currentLandNumber.chagebattleStatus(false);
//								break f; // ���ºΰ� ���渮 ����.
//							} else if (tempBattle == 1) { // �츮�� �¸�
//								// �̰����� �������� �ش� ���� ���Ҽ����� �����.
//								// �̱渮�� ����
//								this.currentLandNumber.landOwner = null;
//
//								this.currentLandNumber.chagebattleStatus(false);
//								this.currentLandNumber = this.land[nextLand()];
//								continue f;
//							} else if (tempBattle == 2) { // ���� �¸�
//								// �׾����� �ӹ��� ������.
//								this.currentLandNumber.chagebattleStatus(false);
//								break f;
//							} else if (tempBattle == 3) { // ���� ����
//								// �׾����� �ӹ��� ������.
//								// ���浵 ���� ��ų�� ������ ���� ���Ҽ�ȭ �Ѵ�.
//								this.currentLandNumber.landOwner = null;
////								this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
//								this.currentLandNumber.chagebattleStatus(false);
//								break f;
//							}
//						}
//					}
//
//				} else { // �츮�� or ����ִ¶�
//					// ��������
//					if (this.currentLandNumber.showBattleStatus()) {
//						this.currentLandNumber.defensivePlayer.add(this);
//						while (this.currentLandNumber.showBattleStatus()) {
//							try {
//								Thread.sleep(1000);
//
//							} catch (Exception e) {
//								break f;
//							} // �׳� ���� CPUȮ �ö󰣴�.
//						}
//						;
//						if (this.showLifeData() <= 0) {
//							break f;
//						} else {
//							if (this.currentLandNumber.offensivePlayer.contains(this)) {
//								this.currentLandNumber.offensivePlayer.remove(this);
//							}
//							if (this.currentLandNumber.defensivePlayer.contains(this)) {
//								this.currentLandNumber.defensivePlayer.remove(this);
//							}
//						}
//
//					}
//
//					try {
//						if (this.movingSpeed == 1)
//							Thread.sleep(2000);
//						else if (this.movingSpeed == 2)
//							Thread.sleep(1000);
//					} catch (Exception e) {
//						break f;
//					}
//					this.currentLandNumber = this.land[nextLand()];
//				}
//
//			} else {
//				// �������� �����ߴ�.
//				if (thisLandOurs() == 0) {
//					// ���ξ��� ��
//					this.currentLandNumber.landOwner = this.belongKing;
//					this.currentLandNumber.defensivePlayer.add(this);
////					this.frame.labelLandColorChange(this.targetLandNumber, this.belongKing.color);
//					onDuty = false;
//					break f;
//				} else if (thisLandOurs() == 1) {
//					// �츮����
//					this.currentLandNumber.defensivePlayer.add(this);
//					if (this.currentLandNumber.showBattleStatus()) {
//						tempBattle = this.suicideBombing(this.currentLandNumber.defensivePlayer,
//								this.currentLandNumber.offensivePlayer);
//
//						if (tempBattle == 0) { // ���º�
//							this.currentLandNumber.chagebattleStatus(false);
//							break f; // ���������� �� ������ ������� ������ �Ѵ�.
//						} else if (tempBattle == 1) { // �츮�� �¸�
//
//							this.currentLandNumber.chagebattleStatus(false);
//							continue f;
//						} else if (tempBattle == 2) { // ���� �¸�
//
//							this.currentLandNumber.chagebattleStatus(false);
//							break f;
//						} else if (tempBattle == 3) { // ���� ����
//							// �׾����� �ӹ��� ������.
//							// ���浵 ���� ��ų�� ������ ���� ���Ҽ�ȭ �Ѵ�.
//							this.currentLandNumber.landOwner = null;
////							this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
//							this.currentLandNumber.chagebattleStatus(false);
//							break f;
//						}
//					}
//					onDuty = false;
//					break f;
//				} else if (thisLandOurs() == 2) {
//					// ������
//					// offensivePlayer�� �츮���� ������ �ڽ��� �� ����Ʈ�� �����ϰ� ������ -> ������ ���� ������ �̸� ��� �����Ѵ�. -> �̰�
//					// ���� �߸��ϸ� ���׳��ڴµ�? �о�������� ������ �� �������µ� ������� ���µ� ������ ���������� ���ڳ�.
//					if (this.currentLandNumber.offensivePlayer.size() > 0) {
//						// ������ �ο���ִ� ������ �ִ�
//
//						tempBattle = this.suicideBombing(this.currentLandNumber.defensivePlayer,
//								this.currentLandNumber.offensivePlayer);
//						onDuty = false;
//						break f;
//					} else {
//						// �������� ȥ�� �����ߴ�/
//						this.currentLandNumber.offensivePlayer.add(this);
//						this.currentLandNumber.chagebattleStatus(true);
//						// ������ ����������.
//						while (this.currentLandNumber.showBattleStatus()) {
//
//							tempBattle = this.suicideBombing(this.currentLandNumber.offensivePlayer,
//									this.currentLandNumber.defensivePlayer);
//
//							if (tempBattle == 0) { // ���º�
//								// �����̸� ����ϴµ� ��� ���� ����� �غ���.
//								// ������ �߿� �շ��� ����� �ڿ������� ����Ʈ�� �߰��Ǵϱ� ������ ������?
//								try {
//									Thread.sleep(1000);
//								} catch (Exception e) {
//									break f;
//								}
//								continue;
//
//							} else if (tempBattle == 1) { // �츮�� �¸�
//								// ������ ������ �¸��ߴ� // ������ �Ѵ� �ױ� ������ �Ұ����ϴ�.
//								System.out.println("��ź���� �¸��ߴ�");
//								this.currentLandNumber.landOwner = this.belongKing;
////								this.frame.labelLandColorChange(this.targetLandNumber, this.belongKing.color);
//
//								// ������ġ ���� ������ġ�� �Ʊ��� �ű��.
//								this.currentLandNumber.defensivePlayer.clear();
//								for (int i = this.currentLandNumber.offensivePlayer.size() - 1; i >= 0; i--) {
//									this.currentLandNumber.defensivePlayer
//											.add(this.currentLandNumber.offensivePlayer.get(i));
//									this.currentLandNumber.offensivePlayer.remove(i);
//								}
//								this.currentLandNumber.offensivePlayer.clear();
//
//								this.currentLandNumber.chagebattleStatus(false);
//								break f;
//							} else if (tempBattle == 2) { // ���� �¸�
//								// �׾����� �ӹ��� ������.
//								this.currentLandNumber.chagebattleStatus(false);
//								break f;
//							} else if (tempBattle == 3) { // ���� ����
//								// �׾����� �ӹ��� ������.
//								// ���浵 ���� ��ų�� ������ ���� ���Ҽ�ȭ �Ѵ�.
//								this.currentLandNumber.landOwner = null;
////								this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
//								this.currentLandNumber.chagebattleStatus(false);
//								break f;
//							}
//						}
//					}
//
//				}
//			}
//		} // end while
//		
//		System.out.println("Bom! end");
//
//	}// end run

	// ���� ������ ����, ��°�� : 0 �̹��� ���º�, 1 our �¸�, 2 enemy �¸�, 3 �� ���� ��� ����
	// �޼ҵ� ���ο��� ������ ü���� ���� ����� ���ݴ�.
	public int suicideBombing(LinkedList<Character> ourCharacterList, LinkedList<Character> enemyCharacterList) {
		LinkedList<Character> tempList;
		int result = 0;
		int i;
		this.setLife(0);
		tempList = ourCharacterList;
		for (i = tempList.size() - 1; i >= 0; i--) {
			if (this.currentLandNumber.topography.equals("Grassland"))
				tempList.get(i).changeLife((this.explosivePower + 1) * -1);
			else
				tempList.get(i).changeLife(this.explosivePower * -1);

			if (tempList.get(i).showLifeData() <= 0)
				tempList.remove(i);
		}

		tempList = enemyCharacterList;
		for (i = tempList.size() - 1; i >= 0; i--) {
			if (this.currentLandNumber.topography.equals("Grassland"))
				tempList.get(i).changeLife((this.explosivePower + 1) * -1);
			else
				tempList.get(i).changeLife(this.explosivePower * -1);

			if (tempList.get(i).showLifeData() <= 0)
				tempList.remove(i);
		}
		System.out.println("BOOM");
		

		if (ourCharacterList.size() == 0 && enemyCharacterList.size() == 0)
			result = 3; // �Ѵ� ����
		else if (ourCharacterList.size() == 0 && enemyCharacterList.size() != 0)
			result = 2; // �����¸�
		else if (ourCharacterList.size() != 0 && enemyCharacterList.size() == 0)
			result = 1;// �Ʊ��¸�
		else if (ourCharacterList.size() != 0 && enemyCharacterList.size() != 0)
			result = 0; // �̹��� ���º�

		return result;

	}
}
