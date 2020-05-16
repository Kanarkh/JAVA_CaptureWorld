import java.util.ArrayList;

public class Ai implements Runnable {
	King aiPlayer;
	King player;
	int mapSize;
	GraphMap graphMap;
	Land[] land;
//	Frame frame;

	ArrayList<Block> targetNumList = new ArrayList<Block>(); // 목표땅 : 인접한땅
	ArrayList<DutyBlock> occupyList = new ArrayList<DutyBlock>();
	ArrayList<DutyBlock> dutyList = new ArrayList<DutyBlock>();

	// AI 성격
	double goldTuning = (int) (Math.random() * 4) + 1; // 높을수록 고점, 금에대한
	double enemyTuning = (int) (Math.random() * 4) + 1; // 낮을수록 고점, 상대 수도와 거리
	double oursTuning = (int) (Math.random() * 4) + 1; // 낮을수록 고점, 우리 수도와 거리
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

		// 처음 점령목표를 설정, null값이 어차피 없다.
		targetNumList.add(graphMap.startBlock.nextBlock0);
		targetNumList.add(graphMap.startBlock.nextBlock1);
	}

	public void run() {

		int tempLandom;
		int targetLandNum;
		Soldier tempSoldier;
//		DutyBlock dutyTemp;

		while (true) { // 우선 Daemon으로
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
				if (tempLandom == 0) { // 병사징집후 땅 점령하기
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
//						System.out.println("병사소환 1");
					}

				} else if (tempLandom == 1) { // 국왕 훈련 하기
					aiPlayer.kingTraining();
					;
//					System.out.println("국왕훈련1");
					aiPlayer.gameFrame.setPlayerStat(aiPlayer);
				} else if (tempLandom == 2) { // 왕국 업그레이드 하기
					aiPlayer.kingdomUpgrade();
					aiPlayer.frameBlock[aiPlayer.currentLandNumber.landNumber]
							.setLandGold(aiPlayer.currentLandNumber.turnGold);
//					System.out.println("왕국 업그레이드1");
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
				// 돈이 10원 이상있다.

				tempLandom = (int) (Math.random() * 3);
				if (tempLandom == 0) { // 병사징집후 땅 점령하기
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
//						System.out.println("병사소환 1");
					}

				} else if (tempLandom == 1) { // 국왕 훈련 하기
					aiPlayer.kingTraining();
					;
//					System.out.println("국왕훈련1");
					aiPlayer.gameFrame.setPlayerStat(aiPlayer);
				} else if (tempLandom == 2) { // 왕국 업그레이드 하기
					aiPlayer.kingdomUpgrade();
					aiPlayer.frameBlock[aiPlayer.currentLandNumber.landNumber]
							.setLandGold(aiPlayer.currentLandNumber.turnGold);
//					System.out.println("왕국 업그레이드1");
				}

			} else if (aiPlayer.holdingGold >= 5) { // if (aiPlayer.howMuchGoldDoYouHave() >= 5) {
				// 10>돈>=5

				tempLandom = (int) (Math.random() * 3);
				if (tempLandom == 0) { // 병사징집후 땅 점령하기
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
//						System.out.println("병사소환 2");
					}

				} else if (tempLandom == 1) { // 국왕 훈련 하기
					aiPlayer.kingTraining();
//					System.out.println("국왕훈련2");
					aiPlayer.gameFrame.setPlayerStat(aiPlayer);
				}

			} else if (aiPlayer.howMuchGoldDoYouHave() >= 2) {
				// 5>돈>=2
				soldierListUpdate();
				targetLandNum = occupyLand();

				if (targetLandNum != -1) {
//					System.out.println("병사소환 3");

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
//			frame.labelGoldAi.setText("골드 :" + aiPlayer.holdingGold);
		}
		System.out.println("Ai정지");
	}

	private void soldierListUpdate() {
		int i;
		Block tempBlock;
		if (occupyList.size() != 0) {
			// 점령된 땅 병사 확인
			for (i = occupyList.size() - 1; i >= 0; i--) {
				if (occupyList.get(i).soldier.showLifeData() <= 0) {
					if (occupyList.get(i).targetLand.landOwner != aiPlayer) {
						// 땅을 빼앗겼다. -> 타겟땅에 포함시켜야한다.
						if (targetNumList
								.contains(graphMap.findBlock(occupyList.get(i).targetLand.landNumber)) == false) {
							// 목록에 해당 땅이 없는걸 확인한다.
							targetNumList.add(graphMap.findBlock(occupyList.get(i).targetLand.landNumber));
						}

						// 근처 땅중 목표리스트에 없는 땅을 추가시킨다.
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
						// 병사가 죽었다, 땅은 다른병사가 지키고있다. 해당 병사만 리스트에서 지운다.
						occupyList.remove(i);
					}
				} else if (occupyList.get(i).soldier.belongKing != aiPlayer) {
					if (targetNumList.contains(graphMap.findBlock(occupyList.get(i).targetLand.landNumber)) == false) {
						// 목록에 해당 땅이 없는걸 확인한다.
						targetNumList.add(graphMap.findBlock(occupyList.get(i).targetLand.landNumber));
					}
					occupyList.remove(i);
				}
			}
		}

		if (dutyList.size() != 0) {
			// 임무 수행중인 병사를 확인한다.
			for (i = dutyList.size() - 1; i >= 0; i--) {
				if (dutyList.get(i).soldier.showLifeData() <= 0) {
					// 유닛이 죽었다.

					if (dutyList.get(i).soldier.currentLandNumber == dutyList.get(i).targetLand) {
						// 목적지에 도착해서 죽었다.
						if (dutyList.get(i).targetLand.landOwner != aiPlayer) {
							if (dutyList.get(i).soldier.currentLandNumber.offensivePlayer.size() == 0) {
								// 공격중인 병사가 없다.
								if (targetNumList
										.contains(graphMap.findBlock(dutyList.get(i).targetLand.landNumber)) == false)
									targetNumList.add((graphMap.findBlock(dutyList.get(i).targetLand.landNumber)));
							}

							// 근처 땅중 목표리스트에 없는 땅을 추가시킨다.
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
							// 우리땅인데 죽었다?
//							System.out.println("!!!!!!!!!!!우리땅인데 죽었다!!!!!!!");
							dutyList.remove(i);
						}

					} else {
						// 목적지에 도착하기 전에 죽었다.
						// 해당 땅을 목표에 다시 추가시킨다.
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
					// 유닛이 살아있다면
					// 위치에 도착했는지 확인한다.
					if (dutyList.get(i).soldier.currentLandNumber == dutyList.get(i).targetLand) {
						// 살아있는데 목적지에 도착해있다.
						if (dutyList.get(i).targetLand.landOwner == aiPlayer) {
							// 점령에 성공했다.
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
								// 점령리스트로 옮긴다
								occupyList.add(dutyList.get(i));
								dutyList.remove(i);

							} else {
								// 이미 점령리스트에 있는데 임무중리스트에서 안지워졌다
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

	// 점령하기 행동
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
		// targetNumList 가 없는경우 -> 리스트에 있던 모든 땅들을 전부 점령명령 시킨후 점령결과를 기다리는 동안은 리스트가 없다.

		if (targetNumList.size() != 0) {
			for (i = 0; i < targetNumList.size(); i++) {
				tempBlock = targetNumList.get(i);
				nextNum = tempBlock.BlockNum;

				// 이 점수로 나중에 기계학습을 할것이다!!
				// 적기지와 거리는 낮을수록 좋고, 아군땅과의 거리도 낮을수록 좋다
				// 가산점을 어떻게 해야 좋을까?
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
			highNum = -1; // 비어있음.

		return highNum; // 가장 높은 점수를 가진 땅
	}
	// 점령확인후 해당

}
