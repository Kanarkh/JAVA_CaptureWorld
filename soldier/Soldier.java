import java.awt.Color;
import java.util.LinkedList;

public class Soldier extends Character implements Runnable {
	King belongKing;
	int targetLandNumber; // ���� ��ȣ�� �ʿ��ϴ�.
	int movingSpeed;
	int xMoveLength, yMoveLength;
	Land[] land;
	//Frame frame;
	ThreadGroup tg1;
	FrameBlock[] frameBlock;

	protected int mapSize;

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	public static final String ANSI_BACK = "\u001B[47m"; // ������ ���
	public static final String ANSI_BOLD = "\u001B[47m"; // ����

	public Soldier() {

	}

//	public Soldier(String soldierName, King belngKing, int soldierAttack, int soldierLife, int movingSpeed) {
//		this.name = soldierName;
//		this.belongKing = belngKing;
//		this.setAttack(soldierAttack);
//		this.setLife(soldierLife);
//		this.movingSpeed = movingSpeed;
//	}

	public Soldier(String soldierName, King belngKing) {
		this.name = soldierName;
		this.belongKing = belngKing;
		this.frameBlock = belongKing.frameBlock;
	}

	public Soldier(int attack, int life) { // �����ο�
		this.setAttack(attack);
		this.setLife(life);
	}

	public void setThreadGroup(ThreadGroup tg) {
		this.tg1 = tg;
	}

	// land�� ���� �������� �̵��ϴµ� �ʿ��ϰ�, frame�� ������ ȭ�鿡 ǥ���ϱ� ���� �ʿ��ϴ�.
	public void setLandAndFrame(Land[] land, Frame frame) {
		this.land = land;
		//this.frame = frame;
	}
	public void setLand(Land[] land) {
		this.land = land;
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
					this.currentLandNumber.landOwner = this.belongKing;
					this.currentLandNumber.defensivePlayer.add(this);
					//this.frame.labelLandColorChange(this.targetLandNumber, this.belongKing.color);
					this.setGui(this.belongKing.color, this.belongKing.name);

					this.addLandSupplyRouteVerification();
					onDuty = false;
					break f;
				} else if (thisLandOurs() == 1) {
					// �츮����
					this.currentLandNumber.defensivePlayer.add(this);
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

							tempBattle = inBattle(this.currentLandNumber.offensivePlayer,
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
		soldierCount();
//		System.out.println("end");

	}// end run

	protected void setGui(Color color, String name) {
		int i = this.currentLandNumber.landNumber;
		this.frameBlock[i].setLandColor(color);
		this.frameBlock[i].setLandOwner(name);
		this.frameBlock[i].setDefensive(this.currentLandNumber.defensivePlayer.size());
		this.frameBlock[i].setOffensive(this.currentLandNumber.offensivePlayer.size());
		this.frameBlock[i].setSupply(this.currentLandNumber.supplyRoute);
	}

	protected void soldierCount() {
		int i = this.currentLandNumber.landNumber;
		this.frameBlock[i].setDefensive(this.currentLandNumber.defensivePlayer.size());
		this.frameBlock[i].setOffensive(this.currentLandNumber.offensivePlayer.size());
		this.frameBlock[i].setSupply(this.currentLandNumber.supplyRoute);
	}

	// �̶��� �츮���� Ȯ���Ѵ�
	// 0:���ξ���, 1:�츮����, 2:������
	protected int thisLandOurs() {
		int thisLandIs = 0;
		if (this.currentLandNumber.landOwner == null)
			thisLandIs = 0;
		else if (this.currentLandNumber.landOwner == this.belongKing)
			thisLandIs = 1;
		else if (this.currentLandNumber.landOwner != this.belongKing)
			thisLandIs = 2;

		return thisLandIs;
	}

	// ������ ��ġ ã��
	protected int nextLand() {
		int temp_cl, temp_tl;
		int targetLand = this.currentLandNumber.landNumber;
		int xpLandScore = 9999; // ������ ���� ���� �������� ����.
		int xmLandScore = 9999;
		int ypLandScore = 9999;
		int ymLandScore = 9999;

		Land tempLand;
//		Block currentBlock = this.belongKing.nationalMap.findBlock(this.currentLandNumber.landNumber);
//		Block tempBlock;

		if (this.xMoveLength != 0) {
			if (this.xMoveLength > 0) {
				// ���������� �̵��ؾ��Ѵ�.
				tempLand = this.land[this.currentLandNumber.landNumber + 1]; // ������ ��
				if (tempLand.landOwner != null) {
					if (tempLand.landOwner == this.belongKing) {
						xpLandScore -= 200;
					} else if (tempLand.landOwner.name.contentEquals("noName")) {
						xpLandScore -= 100;
					} else {
						xpLandScore -= (50 - tempLand.defensivePlayer.size());
					}

				} else {
					xpLandScore -= 200;
				}

			} else if (this.xMoveLength < 0) {
				// �������� �̵��ؾ��Ѵ�
				tempLand = this.land[this.currentLandNumber.landNumber - 1]; // ���� ��
				if (tempLand.landOwner != null) {
					if (tempLand.landOwner == this.belongKing) {
						xmLandScore -= 200;
					} else if (tempLand.landOwner.name.contentEquals("noName")) {
						xmLandScore -= 80;
					} else {
						xmLandScore -= (50 - tempLand.defensivePlayer.size());
					}

				} else {
					xmLandScore -= 200;
				}
			}
		}

		if (this.yMoveLength != 0) {
			if (this.yMoveLength > 0) {
				// �������� �̵��ؾ��Ѵ�.
				tempLand = this.land[this.currentLandNumber.landNumber - this.mapSize]; // ���ʶ�
				if (tempLand.landOwner != null) {
					if (tempLand.landOwner == this.belongKing) {
						ypLandScore -= 200;
					} else if (tempLand.landOwner.name.contentEquals("noName")) {
						ypLandScore -= 80;
					} else {
						ypLandScore -= (50 - tempLand.defensivePlayer.size());
					}

				} else {
					ypLandScore -= 200;
				}

			} else if (this.yMoveLength < 0) {
				// �Ʒ������� �̵��ؾ��Ѵ�.
				tempLand = this.land[this.currentLandNumber.landNumber + this.mapSize]; // �Ʒ��ʶ�
				if (tempLand.landOwner != null) {
					if (tempLand.landOwner == this.belongKing) {
						ymLandScore -= 200;
					} else if (tempLand.landOwner.name.contentEquals("noName")) {
						ymLandScore -= 80;
					} else {
						ymLandScore -= (50 - tempLand.defensivePlayer.size());
					}

				} else {
					ymLandScore -= 200;
				}
			}
		}

		if (this.xMoveLength == 0 && this.yMoveLength == 0) {
			targetLand = this.currentLandNumber.landNumber;
		} else if (this.yMoveLength == 0) {

			if (this.xMoveLength > 0) {
				targetLand = this.currentLandNumber.landNumber + 1;
				this.xMoveLength -= 1;
			} else if (this.xMoveLength < 0) { // �Ʒ�������
				targetLand = this.currentLandNumber.landNumber - 1;
				this.xMoveLength += 1;
			}

		} else if (this.xMoveLength == 0) {

			if (this.yMoveLength > 0) {// ��������
				targetLand = this.currentLandNumber.landNumber - this.mapSize;
				this.yMoveLength -= 1;
			} else if (this.yMoveLength < 0) { // �Ʒ�������
				targetLand = this.currentLandNumber.landNumber + this.mapSize;
				this.yMoveLength += 1;
			}

		} else if (this.xMoveLength != 0 && this.yMoveLength != 0) {

			if (this.xMoveLength > 0 && this.yMoveLength > 0) {
				if (xpLandScore < ypLandScore) { // ������ ���������� ����
					targetLand = this.currentLandNumber.landNumber + 1;
					this.xMoveLength -= 1;
				}
				if (xpLandScore >= ypLandScore) {
					targetLand = this.currentLandNumber.landNumber - this.mapSize;
					this.yMoveLength -= 1;
				}
			} else if (this.xMoveLength > 0 && this.yMoveLength < 0) {
				if (xpLandScore < ypLandScore) { // ������ ���������� ����
					targetLand = this.currentLandNumber.landNumber + 1;
					this.xMoveLength -= 1;
				}
				if (xpLandScore >= ypLandScore) {
					targetLand = this.currentLandNumber.landNumber + this.mapSize;
					this.yMoveLength += 1;
				}
			} else if (this.xMoveLength < 0 && this.yMoveLength > 0) {
				if (xpLandScore < ypLandScore) { // ������ ���������� ����
					targetLand = this.currentLandNumber.landNumber - 1;
					this.xMoveLength += 1;
				}
				if (xpLandScore >= ypLandScore) {
					targetLand = this.currentLandNumber.landNumber - this.mapSize;
					this.yMoveLength -= 1;
				}
			} else if (this.xMoveLength < 0 && this.yMoveLength < 0) {
				if (xpLandScore < ypLandScore) { // ������ ���������� ����
					targetLand = this.currentLandNumber.landNumber - 1;
					this.xMoveLength += 1;
				}
				if (xpLandScore >= ypLandScore) {
					targetLand = this.currentLandNumber.landNumber + this.mapSize;
					this.yMoveLength += 1;
				}
			}

		} else {
			System.out.println("���� ��ã�� �� ����");
		}

		temp_cl = this.currentLandNumber.landNumber;
		temp_tl = targetLand;
		if (temp_cl != temp_tl) {
			if (this.belongKing.name.contentEquals("AI_1"))
				System.out.print(ANSI_BOLD + ANSI_BACK + ANSI_BLUE + this.name + ANSI_RESET + "�� " + temp_cl + "����,");
			else
				System.out.print(ANSI_BOLD + ANSI_BACK + ANSI_RED + this.name + ANSI_RESET + "�� " + temp_cl + "����,");

			System.out.println(temp_tl + "���� �̵��մϴ�.");
		}

		return targetLand;
	}

	// �����ϱ�.
	// ���簡 ��ġ�� �� ��ġ�� �ٸ� ���簡 �ִ��� Ȯ���� �ִٸ� �����ּҸ� �����ϰ�
	// ������ null�� �����Ѵ�.
	protected LinkedList<Character> reconnaissance() {
		LinkedList<Character> findCharacterList = null;

		// ���� ������ Ȯ���Ѵ�.
		if (this.currentLandNumber.landOwner != this.belongKing) {
			// �� ���� ������ �ִ��� Ȯ���Ѵ�.
			if (this.currentLandNumber.defensivePlayer.size() != 0) {
				findCharacterList = this.currentLandNumber.defensivePlayer;
			}
		}
		return findCharacterList;
	}

	// ���� ������ ����, ��°�� : 0 �̹��� ���º�, 1 our �¸�, 2 enemy �¸�, 3 �� ���� ��� ����
	// �޼ҵ� ���ο��� ������ ü���� ���� ����� ���ݴ�.
	protected int inBattle(LinkedList<Character> ourCharacterList, LinkedList<Character> enemyCharacterList) {
		int i;
		int result = 0;
		int ourAttack = 0, enemyAttack = 0;

		for (i = 0; i < ourCharacterList.size(); i++) {
			if (ourCharacterList.get(i).name.contentEquals("����"))
				if (ourCharacterList.get(i).currentLandNumber.topography.equals("Woods")) {
					ourAttack += 1;
					enemyAttack -= 1;
				}

			if (ourCharacterList.get(i).name.contentEquals("�⺴"))
				if (ourCharacterList.get(i).currentLandNumber.topography.equals("Plains"))
					ourAttack += 2;

			if (ourCharacterList.get(i).name.contentEquals("���к�"))
				if (ourCharacterList.get(i).currentLandNumber.topography.equals("Canyon"))
					enemyAttack -= 2;

			ourAttack += ourCharacterList.get(i).showAttackData();
		}
		for (i = 0; i < enemyCharacterList.size(); i++) {

			if (enemyCharacterList.get(i).name.contentEquals("����"))
				if (enemyCharacterList.get(i).currentLandNumber.topography.equals("Woods")) {
					enemyAttack += 1;
					ourAttack -= 1;
				}

			if (enemyCharacterList.get(i).name.contentEquals("�⺴"))
				if (enemyCharacterList.get(i).currentLandNumber.topography.equals("Plains"))
					enemyAttack += 2;

			if (enemyCharacterList.get(i).name.contentEquals("���к�"))
				if (enemyCharacterList.get(i).currentLandNumber.topography.equals("Canyon"))
					ourAttack -= 2;

			enemyAttack += enemyCharacterList.get(i).showAttackData();
		}
		// ���� Attack���� ü�� ���.
		for (i = 0; i < ourCharacterList.size(); i++) {

			if (enemyAttack <= 0)
				break;

			if (ourCharacterList.get(i).showLifeData() > enemyAttack) {
				ourCharacterList.get(i).changeLife(enemyAttack * -1);
				enemyAttack = 0;
			} else {
				enemyAttack -= ourCharacterList.get(i).showLifeData();
				ourCharacterList.get(i).setLife(0);
			}
		}

		for (i = 0; i < enemyCharacterList.size(); i++) {
			if (ourAttack <= 0)
				break;

			if (enemyCharacterList.get(i).showLifeData() > ourAttack) {
				enemyCharacterList.get(i).changeLife(ourAttack * -1);
				ourAttack = 0;
			} else {
				ourAttack -= enemyCharacterList.get(i).showLifeData();
				enemyCharacterList.get(i).setLife(0);
			}
		}

		for (i = (ourCharacterList.size() - 1); i >= 0; i--) {
			if (ourCharacterList.get(i).showLifeData() <= 0)
				ourCharacterList.remove(i);
		}
		for (i = enemyCharacterList.size() - 1; i >= 0; i--) {
			if (enemyCharacterList.get(i).showLifeData() <= 0)
				enemyCharacterList.remove(i);
		}

		if (ourCharacterList.size() == 0 && enemyCharacterList.size() == 0)
			result = 3; // �Ѵ� ����
		else if (ourCharacterList.size() == 0 && enemyCharacterList.size() != 0)
			result = 2; // �����¸�
		else if (ourCharacterList.size() != 0 && enemyCharacterList.size() == 0) {
			result = 1;
		} // �Ʊ��¸�
		else if (ourCharacterList.size() != 0 && enemyCharacterList.size() != 0)
			result = 0; // �̹��� ���º�

		return result;
	}

	// �����ϱ�.
	protected void captureLand() {
		this.subtractLandSupplyRouteVerification();

		this.currentLandNumber.landOwner = this.belongKing;
		// this.currentLandNumber.defensivePlayer.add(this);

		this.addLandSupplyRouteVerification();

		if (this.belongKing.name.contentEquals("AI_1"))
			System.out.println(ANSI_BOLD + ANSI_BACK + ANSI_BLUE + this.name + ANSI_RESET + "��,"
					+ this.currentLandNumber.landNumber + "�� ���� �����߽��ϴ�.");
		else
			System.out.println(ANSI_BOLD + ANSI_BACK + ANSI_RED + this.name + ANSI_RESET + "��,"
					+ this.currentLandNumber.landNumber + "�� ���� �����߽��ϴ�.");
	}

	protected void addLandSupplyRouteVerification() {
		// �����Ŀ� ����Ѵ�
		FindingWay fw = new FindingWay(this.belongKing, this.currentLandNumber);
		//fw.frame = this.frame;
		Thread fwT = new Thread(this.tg1, fw);
		fwT.start();
	}

	protected void subtractLandSupplyRouteVerification() {
		// �������� ����Ѵ�.
		Block tempBlock;
		King formerOwner;

		if (this.currentLandNumber.landOwner != null) {
			formerOwner = this.currentLandNumber.landOwner;
			if (formerOwner.name.contentEquals("noName") == false) {

				tempBlock = formerOwner.nationalMap.findBlock(this.currentLandNumber.landNumber);
				if (tempBlock.preBlock0 != null)
					if (tempBlock.preBlock0.land.landOwner == formerOwner) {
						FindingWay fw = new FindingWay(formerOwner, tempBlock.preBlock0.land);
						//fw.frame = this.frame;
						Thread fwT = new Thread(this.tg1, fw);
						fwT.start();
					}
				if (tempBlock.preBlock1 != null)
					if (tempBlock.preBlock1.land.landOwner == formerOwner) {
						FindingWay fw = new FindingWay(formerOwner, tempBlock.preBlock1.land);
						//fw.frame = this.frame;
						Thread fwT = new Thread(this.tg1, fw);
						fwT.start();
					}
				if (tempBlock.nextBlock0 != null)
					if (tempBlock.nextBlock0.land.landOwner == formerOwner) {
						FindingWay fw = new FindingWay(formerOwner, tempBlock.nextBlock0.land);
						//fw.frame = this.frame;
						Thread fwT = new Thread(this.tg1, fw);
						fwT.start();
					}
				if (tempBlock.nextBlock1 != null)
					if (tempBlock.nextBlock1.land.landOwner == formerOwner) {
						FindingWay fw = new FindingWay(formerOwner, tempBlock.nextBlock1.land);
						//fw.frame = this.frame;
						Thread fwT = new Thread(this.tg1, fw);
						fwT.start();
					}
			}

		}
	}

	public void setMoveLength(int mapSize, int targetNum, int currentNum) {
		int nextLandNum = currentNum;
		int x = mapSize, y = mapSize;
		int temp = 0; // �ӽú���
		int i, j;
		int currentX = 0, currentY = 0;
		int targetX = 0, targetY = 0;
		int[][] map = new int[x][y]; // ������ �� ����

		this.xMoveLength = 0;
		this.yMoveLength = 0;

		for (j = 0; j < y; j++) {
			for (i = 0; i < x; i++) {
				map[i][j] = temp;
				if (temp == currentNum) {
					currentX = i;
					currentY = j;
				}
				if (temp == targetNum) {
					targetX = i;
					targetY = j;
				}
				temp++;
			}
		}

		while (true) {
			// �� & �� �̵� Ȯ��
			if ((currentX % mapSize) == (targetX % mapSize)) {
				// ���� Y�࿡ �ִ�. �̵�����.
				break;
			} else if ((currentX % mapSize) > (targetX % mapSize)) {
				// �� ��ġ�� �� ũ��, ��ǥ���� �����ʿ� �ִ�, �������� �̵�
				this.xMoveLength -= 1;
				currentX -= 1;
			} else if ((currentX % mapSize) < (targetX % mapSize)) {
				// �� ��ġ�� �� �۴�, ��ǥ���� ���ʿ� �ִ�, ���������� �̵�
				this.xMoveLength += 1;
				currentX += 1;
			}
		}
		while (true) {
			if (currentY == targetY) {
				// ���� x�࿡ �ִ�. �̵�����
				break;

			} else if (currentY > targetY) {
				// ����ġ�� �� ũ��, ��ǥ���� �Ʒ� �ִ�, ���� �̵�
				this.yMoveLength += 1;
				currentY -= 1;

			} else if (currentY < targetY) {
				// ����ġ�� �� �۴�, ��ǥ���� ���� �ִ�, �Ʒ��� �̵�
				this.yMoveLength -= 1;
				currentY += 1;
			}
		}

	} // end navigationOnlyLandNum

}
