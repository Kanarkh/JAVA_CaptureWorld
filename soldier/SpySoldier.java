import java.awt.Color;

public class SpySoldier extends Soldier implements Runnable {

	King enemyKing;
	MerchantBornKing enemyMerchant = null;

	int giftOfGab;
	int chance;

	public SpySoldier(String soldierName, King belngKing) {
		this.name = soldierName;
		this.belongKing = belngKing;
		this.giftOfGab = 1;
		this.chance = 1;
		this.frameBlock = belongKing.frameBlock;
		this.movingSpeed = 2;
	}

	public void run() {
		boolean onDuty = true;
		int tempBattle;
		Soldier tempSoldier;
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
						this.currentLandNumber.offensivePlayer.add(this);
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
//								System.out.println("6");
								soldierCount();
								continue;
							} else if (tempBattle == 1) { // �츮�� �¸�
								// �̰����� �������� �ش� ���� ���Ҽ����� �����.

								this.subtractLandSupplyRouteVerification(); // �������޷� ��ȭ

								this.currentLandNumber.landOwner = null;
								this.currentLandNumber.supplyRoute = false;
								// this.frame.labelLandColorChange(this.currentLandNumber.landNumber,
								// Color.white);
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
								// this.frame.labelLandColorChange(this.currentLandNumber.landNumber,
								// Color.white);
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
						this.currentLandNumber.defensivePlayer.add(this);
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

					if (this.currentLandNumber.defensivePlayer.size() != 0) {
						if (this.plotRevolt(this.targetLandNumber)) {
							// �ݶ����� ������ �Ʊ����� �����
							for (int i = 0; i < this.currentLandNumber.defensivePlayer.size(); i++) {
								tempSoldier = (Soldier) this.currentLandNumber.defensivePlayer.get(i);
								tempSoldier.belongKing = this.belongKing;
								this.currentLandNumber.offensivePlayer
										.add(this.currentLandNumber.defensivePlayer.get(i));

							}
							this.currentLandNumber.defensivePlayer.clear();
							this.captureLand();
							// this.frame.labelLandColorChange(this.currentLandNumber.landNumber,
							// this.belongKing.color);
							this.currentLandNumber.chagebattleStatus(false);
							setGui(this.belongKing.color, this.belongKing.name);
							addLandSupplyRouteVerification();

						} else {
							this.setLife(0);

						}
					} else {
						this.currentLandNumber.landOwner = this.belongKing;
						this.currentLandNumber.defensivePlayer.add(this);
						// this.frame.labelLandColorChange(this.targetLandNumber,
						// this.belongKing.color);
						setGui(this.belongKing.color, this.belongKing.name);
						this.currentLandNumber.chagebattleStatus(false);
					}

					onDuty = false;
					break f;
				} else if (thisLandOurs() == 1) {
					// �츮����
					this.currentLandNumber.defensivePlayer.add(this);
					if (this.currentLandNumber.showBattleStatus()) {
						if (this.plotRevolt(this.targetLandNumber)) {
							// �ݶ����� ������ �Ʊ����� �����
							addLandSupplyRouteVerification();
							for (int i = 0; i < this.currentLandNumber.offensivePlayer.size(); i++) {
								tempSoldier = (Soldier) this.currentLandNumber.offensivePlayer.get(i);
								tempSoldier.belongKing = this.belongKing;
								this.currentLandNumber.defensivePlayer
										.add(this.currentLandNumber.offensivePlayer.get(i));
							}
							this.currentLandNumber.offensivePlayer.clear();
							this.currentLandNumber.chagebattleStatus(false);
							// �̷��� �ϸ� ������������ �����尡 �˾Ƽ� ó���� �Ұ��̴�.
							// ���� ��Ȳ�� ��� ��ó����? ( ���� �츮������ �����ϰ� ��ġ�� �ٲٴ� ���� �̰� ����Ǵ� ���) ��
						} else
							this.setLife(0);
					}
					onDuty = false;
					break f;
				} else if (thisLandOurs() == 2) {
					// ������

					if (this.currentLandNumber.offensivePlayer.size() <= 0) {
						// �ش� ���� ����ִ� ������ ������ ���ɱ��� ������Ѵ�.
						if (this.plotRevolt(this.targetLandNumber)) {
							subtractLandSupplyRouteVerification();
							// �ݶ����� ������ �Ʊ����� �����
							for (int i = 0; i < this.currentLandNumber.defensivePlayer.size(); i++) {
								tempSoldier = (Soldier) this.currentLandNumber.defensivePlayer.get(i);
								tempSoldier.belongKing = this.belongKing;
//								this.currentLandNumber.offensivePlayer
//										.add(this.currentLandNumber.defensivePlayer.get(i));

							}
							for (int i = 0; i < this.currentLandNumber.offensivePlayer.size(); i++) {
								tempSoldier = (Soldier) this.currentLandNumber.offensivePlayer.get(i);
								this.currentLandNumber.defensivePlayer.add(tempSoldier);
							}
							this.currentLandNumber.offensivePlayer.clear();
							this.captureLand();
							// this.frame.labelLandColorChange(this.currentLandNumber.landNumber,
							// this.belongKing.color);
							setGui(this.belongKing.color, this.belongKing.name);
							addLandSupplyRouteVerification();
							this.currentLandNumber.chagebattleStatus(false);

						} else {
							this.setLife(0);
						}
						break f;
					} else {
						if (this.plotRevolt(this.targetLandNumber)) {
							// �ݶ����� ������ �Ʊ����� �����
							subtractLandSupplyRouteVerification();
							for (int i = 0; i < this.currentLandNumber.defensivePlayer.size(); i++) {
								tempSoldier = (Soldier) this.currentLandNumber.defensivePlayer.get(i);
								tempSoldier.belongKing = this.belongKing;
//								this.currentLandNumber.offensivePlayer
//										.add(this.currentLandNumber.defensivePlayer.get(i));

							}
							for (int i = 0; i < this.currentLandNumber.offensivePlayer.size(); i++) {
								tempSoldier = (Soldier) this.currentLandNumber.offensivePlayer.get(i);
								this.currentLandNumber.defensivePlayer.add(tempSoldier);
							}
							this.currentLandNumber.offensivePlayer.clear();
							this.captureLand();
							// this.frame.labelLandColorChange(this.currentLandNumber.landNumber,
							// this.belongKing.color);
							setGui(this.belongKing.color, this.belongKing.name);
							addLandSupplyRouteVerification();
							this.currentLandNumber.chagebattleStatus(false);
							// �̷��� �ϸ� ������������ �����尡 �˾Ƽ� ó���� �Ұ��̴�.

						} else
							this.setLife(0);

						break f;
					}

				}
				break f;
			} // end ������

		} // end while
		soldierCount();
		System.out.println("end Spy");

	}// end run

//	public void run() {
//		boolean onDuty = true;
//		int tempBattle;
//		Soldier tempSoldier;
//		// ������ ���� ��ġ�ϴ��� �����Ѵ�.
//		// ������ ���� �ƴ϶�� ���� ��ġ�� �̵��Ѵ�
//
//		// �������� �����Ҷ����� ��� �ݺ��Ѵ�.
//		f: while (onDuty) {
//			try {
//				Thread.sleep(1);
//			}catch(Exception e) {
//				break f;
//			}
//			if (this.currentLandNumber.landNumber != this.targetLandNumber) {
//				// �������� �������� ���ߴ�
//				if (thisLandOurs() == 2) {
//					// ������
//					if (this.currentLandNumber.showBattleStatus() == true) {
//						// �Ʊ��� ���� ������ ��������
//						this.currentLandNumber.offensivePlayer.add(this);
//						while(this.currentLandNumber.showBattleStatus()) {
//							try {
//									Thread.sleep(1000);
//									
//							} catch (Exception e) {
//								break f;
//							} //�׳� ���� CPUȮ �ö󰣴�. 
//						};
//						if(this.showLifeData()<=0) {
//							break f;
//						}
//						else {
//							if(this.currentLandNumber.offensivePlayer.contains(this))
//							{
//								this.currentLandNumber.offensivePlayer.remove(this);
//							}
//							if(this.currentLandNumber.defensivePlayer.contains(this)) 
//							{
//								this.currentLandNumber.defensivePlayer.remove(this);
//							}
//						}
//						
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
//							tempBattle = inBattle(this.currentLandNumber.offensivePlayer,
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
//								System.out.println("6");
//								continue;
//							} else if (tempBattle == 1) { // �츮�� �¸�
//								// �̰����� �������� �ش� ���� ���Ҽ����� �����.
//								this.currentLandNumber.landOwner = null;
//								//this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
//								
//								try {
//									if (this.movingSpeed == 1)
//										Thread.sleep(1000);
//									else if (this.movingSpeed == 2)
//										Thread.sleep(500);
//								} catch (Exception e) {
//									break f;
//								}
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
//								//this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
//								this.currentLandNumber.chagebattleStatus(false);
//								break f;
//							}
//						}
//					}
//
//				} else { // �츮�� or ����ִ¶�
//					// ��������
//					if(this.currentLandNumber.showBattleStatus()) {
//						this.currentLandNumber.defensivePlayer.add(this);
//						while(this.currentLandNumber.showBattleStatus()) {
//							try {
//									Thread.sleep(1000);
//									
//							} catch (Exception e) {
//								break f;
//							} //�׳� ���� CPUȮ �ö󰣴�. 
//						};
//						if(this.showLifeData()<=0) {
//							break f;
//						}
//						else {
//							if(this.currentLandNumber.offensivePlayer.contains(this))
//							{
//								this.currentLandNumber.offensivePlayer.remove(this);
//							}
//							if(this.currentLandNumber.defensivePlayer.contains(this)) 
//							{
//								this.currentLandNumber.defensivePlayer.remove(this);
//							}
//						}
//						
//					}
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
//
//					if (this.currentLandNumber.defensivePlayer.size() != 0) {
//						if (this.plotRevolt(this.targetLandNumber)) {
//							// �ݶ����� ������ �Ʊ����� �����
//							for (int i = 0; i < this.currentLandNumber.defensivePlayer.size(); i++) {
//								tempSoldier = (Soldier) this.currentLandNumber.defensivePlayer.get(i);
//								tempSoldier.belongKing = this.belongKing;
//								this.currentLandNumber.offensivePlayer
//										.add(this.currentLandNumber.defensivePlayer.get(i));
//
//							}
//							this.currentLandNumber.defensivePlayer.clear();
//							this.captureLand();
//							//this.frame.labelLandColorChange(this.currentLandNumber.landNumber, this.belongKing.color);
//							this.currentLandNumber.chagebattleStatus(false);
//
//						} else {
//							this.setLife(0);
//
//						}
//					} else {
//						this.currentLandNumber.landOwner = this.belongKing;
//						this.currentLandNumber.defensivePlayer.add(this);
//						//this.frame.labelLandColorChange(this.targetLandNumber, this.belongKing.color);
//						this.currentLandNumber.chagebattleStatus(false);
//					}
//
//					onDuty = false;
//					break f;
//				} else if (thisLandOurs() == 1) {
//					// �츮����
//					this.currentLandNumber.defensivePlayer.add(this);
//					if (this.currentLandNumber.showBattleStatus()) {
//						if (this.plotRevolt(this.targetLandNumber)) {
//							// �ݶ����� ������ �Ʊ����� �����
//
//							for (int i = 0; i < this.currentLandNumber.offensivePlayer.size(); i++) {
//								tempSoldier = (Soldier) this.currentLandNumber.offensivePlayer.get(i);
//								tempSoldier.belongKing = this.belongKing;
//								this.currentLandNumber.defensivePlayer
//										.add(this.currentLandNumber.offensivePlayer.get(i));
//							}
//							this.currentLandNumber.offensivePlayer.clear();
//							this.currentLandNumber.chagebattleStatus(false);
//							// �̷��� �ϸ� ������������ �����尡 �˾Ƽ� ó���� �Ұ��̴�.
//							// ���� ��Ȳ�� ��� ��ó����? ( ���� �츮������ �����ϰ� ��ġ�� �ٲٴ� ���� �̰� ����Ǵ� ���) ��
//						} else
//							this.setLife(0);
//					}
//					onDuty = false;
//					break f;
//				} else if (thisLandOurs() == 2) {
//					// ������
//
//					if (this.currentLandNumber.offensivePlayer.size() <= 0) {
//						// �ش� ���� ����ִ� ������ ������ ���ɱ��� ������Ѵ�.
//						if (this.plotRevolt(this.targetLandNumber)) {
//							// �ݶ����� ������ �Ʊ����� �����
//							for (int i = 0; i < this.currentLandNumber.defensivePlayer.size(); i++) {
//								tempSoldier = (Soldier) this.currentLandNumber.defensivePlayer.get(i);
//								tempSoldier.belongKing = this.belongKing;
//								this.currentLandNumber.offensivePlayer
//										.add(this.currentLandNumber.defensivePlayer.get(i));
//
//							}
//							this.currentLandNumber.defensivePlayer.clear();
//							this.captureLand();
//							//this.frame.labelLandColorChange(this.currentLandNumber.landNumber, this.belongKing.color);
//							this.currentLandNumber.chagebattleStatus(false);
//							
//						} else {
//							this.setLife(0);
//						}
//						break f;
//					} else {
//						if (this.plotRevolt(this.targetLandNumber)) {
//							// �ݶ����� ������ �Ʊ����� �����
//							for (int i = 0; i < this.currentLandNumber.defensivePlayer.size(); i++) {
//								tempSoldier = (Soldier) this.currentLandNumber.defensivePlayer.get(i);
//								tempSoldier.belongKing = this.belongKing;
//								this.currentLandNumber.offensivePlayer
//										.add(this.currentLandNumber.defensivePlayer.get(i));
//
//							}
//							this.currentLandNumber.defensivePlayer.clear();
//							this.captureLand();
//							//this.frame.labelLandColorChange(this.currentLandNumber.landNumber, this.belongKing.color);
//							this.currentLandNumber.chagebattleStatus(false);
//							// �̷��� �ϸ� ������������ �����尡 �˾Ƽ� ó���� �Ұ��̴�.
//
//						} else
//							this.setLife(0);
//
//						break f;
//					}
//					
//				}
//				break f;
//			}//end ������
//		} // end while
//		System.out.println("end Spy");
//	}

	private boolean plotRevolt(int targetLandNum) {
		boolean temp = false;
		// ��Ȳ�� ���� �ٸ��� ó���ؾ��Ѵ�.
		// ���� ������ �������̶�� �̹� �ٸ� �����尡 �۵��ϰ� �ִ�. �׷��� �� �����带 ���̰� �� �����尡 �̺�Ʈó���ϴ°� �ƴ϶� ���ϴ� �����
		// ��Ÿ������ ��Ȳ�� �ٲ��ָ�ȴ�.

		if (this.chanceCalculation() && targetLandNum != 0 && targetLandNum != 24) {
			// �ݶ��� �����ϴ�.
			System.out.println("�ݶ��� �����ߴ�");
			temp = true;
		} else {
			// �ݶ��� �����ϴ�.
			System.out.println("�ݶ��� �����ߴ�");
			temp = false;
		}

		return temp;
		// �ݶ��� �����ϸ� ���ۿ��� �װ� ���̳���.
	}

	private boolean chanceCalculation() {
		boolean result = false;
		int temp = (int) (Math.random() * 100) + 1;

		if (this.enemyKing != null) {
			if (this.enemyKing.origin.equals("Merchant")) {
				this.enemyMerchant = (MerchantBornKing) this.enemyKing;

				if (temp <= (this.chance + this.giftOfGab + this.enemyMerchant.greed))
					result = true;
				else
					result = false;// result = false;

			}
		} else {
			if (temp <= (this.chance + this.giftOfGab))
				result = true;
			else
				result = false;// result = false;
		}

		return result;
	}
}
