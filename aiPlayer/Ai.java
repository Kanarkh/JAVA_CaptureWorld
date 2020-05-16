import java.util.ArrayList;

public class Ai implements Runnable {
	King aiPlayer;
	King player;
	int mapSize;
	GraphMap graphMap;
	Land[] land;
//	Frame frame;

	ArrayList<Block> targetNumList = new ArrayList<Block>(); // ��ǥ�� : �����Ѷ�
	ArrayList<DutyBlock> occupyList = new ArrayList<DutyBlock>();
	ArrayList<DutyBlock> dutyList = new ArrayList<DutyBlock>();

	// AI ����
	double goldTuning = (int) (Math.random() * 4) + 1; // �������� ����, �ݿ�����
	double enemyTuning = (int) (Math.random() * 4) + 1; // �������� ����, ��� ������ �Ÿ�
	double oursTuning = (int) (Math.random() * 4) + 1; // �������� ����, �츮 ������ �Ÿ�
	double ownerScore = (int) (Math.random() * 4) + 1;
	
	ThreadGroup tg1;

	public Ai(King me, King player, Land[] land, int mapSize) {
		this.aiPlayer = me;
		this.player = player;
		this.mapSize = mapSize;
		this.land = land;
//		this.frame = frame;
		this.graphMap = aiPlayer.nationalMap;// new GraphMap(this.aiPlayer.currentLandNumber.landNumber, this.land,
												// mapSize);

		// ó�� ���ɸ�ǥ�� ����, null���� ������ ����.
		targetNumList.add(graphMap.startBlock.nextBlock0);
		targetNumList.add(graphMap.startBlock.nextBlock1);
	}

	public void run() {

		int tempLandom;
		int targetLandNum;
		Soldier tempSoldier;
//		DutyBlock dutyTemp;

		while (true) { // �켱 Daemon����
//			System.out.println(aiPlayer.holdingGold +", "+aiPlayer.howMuchGoldDoYouHave() );
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				break;
			}

			if (aiPlayer.showLifeData() <= 0)
				break;

			if (aiPlayer.holdingGold >= 20) {
				tempLandom = (int) (Math.random() * 4);
				if (tempLandom == 0) { // ����¡���� �� �����ϱ�
					soldierListUpdate();
					targetLandNum = occupyLand();
					if (targetLandNum != -1) {
						tempLandom = (int) (Math.random() * 3);

						tempSoldier = aiPlayer.createSoldier(tempLandom);
						tempSoldier.setLand(this.land);
						tempSoldier.targetLandNumber = targetLandNum;
						dutyList.add(new DutyBlock(tempSoldier, graphMap.findBlock(targetLandNum).land));

						Thread temp1 = new Thread(tg1, tempSoldier);
						temp1.setDaemon(true);
						temp1.start();

						targetNumList.remove(graphMap.findBlock(targetLandNum));
						tempSoldier = null;
//						System.out.println("�����ȯ 1");
					}

				} else if (tempLandom == 1) { // ���� �Ʒ� �ϱ�
					aiPlayer.kingTraining();
					;
//					System.out.println("�����Ʒ�1");
					aiPlayer.gameFrame.setPlayerStat(aiPlayer);
				} else if (tempLandom == 2) { // �ձ� ���׷��̵� �ϱ�
					aiPlayer.kingdomUpgrade();
					aiPlayer.frameBlock[aiPlayer.currentLandNumber.landNumber]
							.setLandGold(aiPlayer.currentLandNumber.turnGold);
//					System.out.println("�ձ� ���׷��̵�1");
				} else if (tempLandom == 3) {

					if (aiPlayer.origin.equals("Merchant")) {
						if (aiPlayer.currentLandNumber.turnGold > player.currentLandNumber.turnGold)
							if (player.currentLandNumber.turnGold != 0)
								this.kingSkill();
					} else if (aiPlayer.origin.equals("Knight")) {
						if (aiPlayer.showAttackData() > player.showAttackData())
							this.kingSkill();
					} else if (aiPlayer.origin.equals("Thief")) {
						if (aiPlayer.showLifeData() >= 6)
							this.kingSkill();
					}
				}

			} else if (aiPlayer.holdingGold >= 10) { // (aiPlayer.howMuchGoldDoYouHave() >= 10) {
				// ���� 10�� �̻��ִ�.

				tempLandom = (int) (Math.random() * 3);
				if (tempLandom == 0) { // ����¡���� �� �����ϱ�
					soldierListUpdate();
					targetLandNum = occupyLand();
					if (targetLandNum != -1) {
						tempLandom = (int) (Math.random() * 3);

						tempSoldier = aiPlayer.createSoldier(tempLandom);
						tempSoldier.setLand(this.land);
						tempSoldier.targetLandNumber = targetLandNum;
						dutyList.add(new DutyBlock(tempSoldier, graphMap.findBlock(targetLandNum).land));

						Thread temp1 = new Thread(tg1, tempSoldier);
						temp1.setDaemon(true);
						temp1.start();

						targetNumList.remove(graphMap.findBlock(targetLandNum));
						tempSoldier = null;
//						System.out.println("�����ȯ 1");
					}

				} else if (tempLandom == 1) { // ���� �Ʒ� �ϱ�
					aiPlayer.kingTraining();
					;
//					System.out.println("�����Ʒ�1");
					aiPlayer.gameFrame.setPlayerStat(aiPlayer);
				} else if (tempLandom == 2) { // �ձ� ���׷��̵� �ϱ�
					aiPlayer.kingdomUpgrade();
					aiPlayer.frameBlock[aiPlayer.currentLandNumber.landNumber]
							.setLandGold(aiPlayer.currentLandNumber.turnGold);
//					System.out.println("�ձ� ���׷��̵�1");
				}

			} else if (aiPlayer.holdingGold >= 5) { // if (aiPlayer.howMuchGoldDoYouHave() >= 5) {
				// 10>��>=5

				tempLandom = (int) (Math.random() * 3);
				if (tempLandom == 0) { // ����¡���� �� �����ϱ�
					soldierListUpdate();
					targetLandNum = occupyLand();
					if (targetLandNum != -1) {
						tempLandom = (int) (Math.random() * 3);

						tempSoldier = aiPlayer.createSoldier(tempLandom);
						tempSoldier.setLand(this.land);
						tempSoldier.targetLandNumber = targetLandNum;
						dutyList.add(new DutyBlock(tempSoldier, graphMap.findBlock(targetLandNum).land));

						Thread temp1 = new Thread(tg1, tempSoldier);
						temp1.setDaemon(true);
						temp1.start();

						targetNumList.remove(graphMap.findBlock(targetLandNum));
						tempSoldier = null;
//						System.out.println("�����ȯ 2");
					}

				} else if (tempLandom == 1) { // ���� �Ʒ� �ϱ�
					aiPlayer.kingTraining();
//					System.out.println("�����Ʒ�2");
					aiPlayer.gameFrame.setPlayerStat(aiPlayer);
				}

			} else if (aiPlayer.howMuchGoldDoYouHave() >= 2) {
				// 5>��>=2
				soldierListUpdate();
				targetLandNum = occupyLand();

				if (targetLandNum != -1) {
//					System.out.println("�����ȯ 3");

					tempSoldier = aiPlayer.createSoldier(0);
					tempSoldier.setLand(this.land);
					tempSoldier.targetLandNumber = targetLandNum;
					dutyList.add(new DutyBlock(tempSoldier, graphMap.findBlock(targetLandNum).land));

					Thread temp1 = new Thread(tg1, tempSoldier);
					temp1.setDaemon(true);
					temp1.start();

					targetNumList.remove(graphMap.findBlock(targetLandNum));
					tempSoldier = null;
				}
			}
//			frame.labelGoldAi.setText("��� :" + aiPlayer.holdingGold);
		}
		System.out.println("Ai����");
	}

	private void soldierListUpdate() {
		int i;
		Block tempBlock;
		if (occupyList.size() != 0) {
			// ���ɵ� �� ���� Ȯ��
			for (i = occupyList.size() - 1; i >= 0; i--) {
				if (occupyList.get(i).soldier.showLifeData() <= 0) {
					if (occupyList.get(i).targetLand.landOwner != aiPlayer) {
						// ���� ���Ѱ��. -> Ÿ�ٶ��� ���Խ��Ѿ��Ѵ�.
						if (targetNumList
								.contains(graphMap.findBlock(occupyList.get(i).targetLand.landNumber)) == false) {
							// ��Ͽ� �ش� ���� ���°� Ȯ���Ѵ�.
							targetNumList.add(graphMap.findBlock(occupyList.get(i).targetLand.landNumber));
						}

						// ��ó ���� ��ǥ����Ʈ�� ���� ���� �߰���Ų��.
						tempBlock = graphMap.findBlock(occupyList.get(i).targetLand.landNumber);

						if (tempBlock.nextBlock0 != null)
							if (tempBlock.nextBlock0.land.landOwner != aiPlayer)
								if (targetNumList.contains(tempBlock.nextBlock0) == false)
									targetNumList.add(tempBlock.nextBlock0);

						if (tempBlock.nextBlock1 != null)
							if (tempBlock.nextBlock1.land.landOwner != aiPlayer)
								if (targetNumList.contains(tempBlock.nextBlock1) == false)
									targetNumList.add(tempBlock.nextBlock1);

						if (tempBlock.preBlock0 != null)
							if (tempBlock.preBlock0.land.landOwner != aiPlayer)
								if (targetNumList.contains(tempBlock.preBlock0) == false)
									targetNumList.add(tempBlock.preBlock0);

						if (tempBlock.preBlock1 != null)
							if (tempBlock.preBlock1.land.landOwner != aiPlayer)
								if (targetNumList.contains(tempBlock.preBlock1) == false)
									targetNumList.add(tempBlock.preBlock1);

						occupyList.remove(i);
					} else {
						// ���簡 �׾���, ���� �ٸ����簡 ��Ű���ִ�. �ش� ���縸 ����Ʈ���� �����.
						occupyList.remove(i);
					}
				} else if (occupyList.get(i).soldier.belongKing != aiPlayer) {
					if (targetNumList.contains(graphMap.findBlock(occupyList.get(i).targetLand.landNumber)) == false) {
						// ��Ͽ� �ش� ���� ���°� Ȯ���Ѵ�.
						targetNumList.add(graphMap.findBlock(occupyList.get(i).targetLand.landNumber));
					}
					occupyList.remove(i);
				}
			}
		}

		if (dutyList.size() != 0) {
			// �ӹ� �������� ���縦 Ȯ���Ѵ�.
			for (i = dutyList.size() - 1; i >= 0; i--) {
				if (dutyList.get(i).soldier.showLifeData() <= 0) {
					// ������ �׾���.

					if (dutyList.get(i).soldier.currentLandNumber == dutyList.get(i).targetLand) {
						// �������� �����ؼ� �׾���.
						if (dutyList.get(i).targetLand.landOwner != aiPlayer) {
							if (dutyList.get(i).soldier.currentLandNumber.offensivePlayer.size() == 0) {
								// �������� ���簡 ����.
								if (targetNumList
										.contains(graphMap.findBlock(dutyList.get(i).targetLand.landNumber)) == false)
									targetNumList.add((graphMap.findBlock(dutyList.get(i).targetLand.landNumber)));
							}

							// ��ó ���� ��ǥ����Ʈ�� ���� ���� �߰���Ų��.
							tempBlock = graphMap.findBlock(dutyList.get(i).targetLand.landNumber);

							if (tempBlock.nextBlock0 != null)
								if (tempBlock.nextBlock0.land.landOwner != aiPlayer)
									if (targetNumList.contains(tempBlock.nextBlock0) == false)
										targetNumList.add(tempBlock.nextBlock0);

							if (tempBlock.nextBlock1 != null)
								if (tempBlock.nextBlock1.land.landOwner != aiPlayer)
									if (targetNumList.contains(tempBlock.nextBlock1) == false)
										targetNumList.add(tempBlock.nextBlock1);

							if (tempBlock.preBlock0 != null)
								if (tempBlock.preBlock0.land.landOwner != aiPlayer)
									if (targetNumList.contains(tempBlock.preBlock0) == false)
										targetNumList.add(tempBlock.preBlock0);

							if (tempBlock.preBlock1 != null)
								if (tempBlock.preBlock1.land.landOwner != aiPlayer)
									if (targetNumList.contains(tempBlock.preBlock1) == false)
										targetNumList.add(tempBlock.preBlock1);
							dutyList.remove(i);

						} else {
							// �츮���ε� �׾���?
//							System.out.println("!!!!!!!!!!!�츮���ε� �׾���!!!!!!!");
							dutyList.remove(i);
						}

					} else {
						// �������� �����ϱ� ���� �׾���.
						// �ش� ���� ��ǥ�� �ٽ� �߰���Ų��.
						if (targetNumList.contains(graphMap.findBlock(dutyList.get(i).targetLand.landNumber)) == false)
							targetNumList.add((graphMap.findBlock(dutyList.get(i).targetLand.landNumber)));

						tempBlock = graphMap.findBlock(dutyList.get(i).targetLand.landNumber);

						if (tempBlock.nextBlock0 != null)
							if (tempBlock.nextBlock0.land.landOwner != aiPlayer)
								if (targetNumList.contains(tempBlock.nextBlock0) == false)
									targetNumList.add(tempBlock.nextBlock0);

						if (tempBlock.nextBlock1 != null)
							if (tempBlock.nextBlock1.land.landOwner != aiPlayer)
								if (targetNumList.contains(tempBlock.nextBlock1) == false)
									targetNumList.add(tempBlock.nextBlock1);

						if (tempBlock.preBlock0 != null)
							if (tempBlock.preBlock0.land.landOwner != aiPlayer)
								if (targetNumList.contains(tempBlock.preBlock0) == false)
									targetNumList.add(tempBlock.preBlock0);

						if (tempBlock.preBlock1 != null)
							if (tempBlock.preBlock1.land.landOwner != aiPlayer)
								if (targetNumList.contains(tempBlock.preBlock1) == false)
									targetNumList.add(tempBlock.preBlock1);

						dutyList.remove(i);
					}

				} else {
					// ������ ����ִٸ�
					// ��ġ�� �����ߴ��� Ȯ���Ѵ�.
					if (dutyList.get(i).soldier.currentLandNumber == dutyList.get(i).targetLand) {
						// ����ִµ� �������� �������ִ�.
						if (dutyList.get(i).targetLand.landOwner == aiPlayer) {
							// ���ɿ� �����ߴ�.
							if (occupyList.contains(dutyList.get(i)) == false) {

								tempBlock = graphMap.findBlock(dutyList.get(i).targetLand.landNumber);

								if (tempBlock.nextBlock0 != null)
									if (tempBlock.nextBlock0.land.landOwner != aiPlayer)
										if (targetNumList.contains(tempBlock.nextBlock0) == false)
											targetNumList.add(tempBlock.nextBlock0);

								if (tempBlock.nextBlock1 != null)
									if (tempBlock.nextBlock1.land.landOwner != aiPlayer)
										if (targetNumList.contains(tempBlock.nextBlock1) == false)
											targetNumList.add(tempBlock.nextBlock1);

								if (tempBlock.preBlock0 != null)
									if (tempBlock.preBlock0.land.landOwner != aiPlayer)
										if (targetNumList.contains(tempBlock.preBlock0) == false)
											targetNumList.add(tempBlock.preBlock0);

								if (tempBlock.preBlock1 != null)
									if (tempBlock.preBlock1.land.landOwner != aiPlayer)
										if (targetNumList.contains(tempBlock.preBlock1) == false)
											targetNumList.add(tempBlock.preBlock1);

								targetNumList.remove(tempBlock);
								// ���ɸ���Ʈ�� �ű��
								occupyList.add(dutyList.get(i));
								dutyList.remove(i);

							} else {
								// �̹� ���ɸ���Ʈ�� �ִµ� �ӹ��߸���Ʈ���� ����������
								dutyList.remove(i);
							}
						}
					}
				}

			}
		}

	}

	public void setThreadGroup(ThreadGroup tg1) {
		this.tg1 = tg1;
	}

	// �����ϱ� �ൿ
	private void kingSkill() {
		KnightBornKing knight = null;
		MerchantBornKing merchant = null;
		ThiefBornKing thief = null;

		if (aiPlayer.origin.equals("Thief")) {
			thief = (ThiefBornKing) aiPlayer;
			thief.stealLand(player);
		} else if (aiPlayer.origin.contentEquals("Merchant")) {
			merchant = (MerchantBornKing) aiPlayer;
			merchant.tradeWar(player);
		} else if (aiPlayer.origin.equals("Knight")) {
			knight = (KnightBornKing) aiPlayer;
			knight.fightManToMan(player);

		}
	}

	private int occupyLand() {
		int highNum = 0, nextNum;
		double highScore = -999999, nextScore;

		Block tempBlock;

		int i = 0;
		// targetNumList �� ���°�� -> ����Ʈ�� �ִ� ��� ������ ���� ���ɸ�� ��Ų�� ���ɰ���� ��ٸ��� ������ ����Ʈ�� ����.

		if (targetNumList.size() != 0) {
			for (i = 0; i < targetNumList.size(); i++) {
				tempBlock = targetNumList.get(i);
				nextNum = tempBlock.BlockNum;

				// �� ������ ���߿� ����н��� �Ұ��̴�!!
				// �������� �Ÿ��� �������� ����, �Ʊ������� �Ÿ��� �������� ����
				// �������� ��� �ؾ� ������?
				nextScore = (tempBlock.land.turnGold * goldTuning) + (tempBlock.distanceEnemyCapital * enemyTuning)
						+ (tempBlock.distanceOursCapital * oursTuning);

				if (tempBlock.land.landOwner == null)
					nextScore += ownerScore;

				if (nextScore > highScore) {
					highNum = nextNum;
					highScore = nextScore;
				}
			}
		} else
			highNum = -1; // �������.

		return highNum; // ���� ���� ������ ���� ��
	}
	// ����Ȯ���� �ش�

}
