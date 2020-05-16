import java.awt.Color;
import java.util.LinkedList;

public class Soldier extends Character implements Runnable {
	King belongKing;
	int targetLandNumber; // 땅의 번호만 필요하다.
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
	public static final String ANSI_BACK = "\u001B[47m"; // 바탕색 흰색
	public static final String ANSI_BOLD = "\u001B[47m"; // 굵게

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

	public Soldier(int attack, int life) { // 토착민용
		this.setAttack(attack);
		this.setLife(life);
	}

	public void setThreadGroup(ThreadGroup tg) {
		this.tg1 = tg;
	}

	// land는 다음 목적지로 이동하는데 필요하고, frame은 점령후 화면에 표시하기 위해 필요하다.
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
				// 목적지에 도착하지 못했다
				if (thisLandOurs() == 2) {
					// 적군땅
					if (this.currentLandNumber.showBattleStatus() == true) {

						// 아군이 전투중일때 안지나가고 함께 전투에 참여했다가 지나갈수도 있다.
						this.currentLandNumber.offensivePlayer.add(this);
						soldierCount();
						while (this.currentLandNumber.showBattleStatus()) {
							try { // 전투가 끝날때까지 확인하며 기다린다.
								Thread.sleep(1000);

							} catch (Exception e) {
								// e.printStackTrace();
								break f;
							} // 그냥 돌면 CPU확 올라간다.
						}

						if (this.showLifeData() <= 0) {// 전투시 사망하면 임무끝
							break f;
						} else { // 전투가 끝나면 자신의 임무를 진행한다.
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
						// 아군은 없는데 적군땅이다. 이겨야 지나갈수 있다.
						// 적군과 전투해서 이겨야 지나간다.
						this.currentLandNumber.chagebattleStatus(true);
						this.currentLandNumber.offensivePlayer.add(this);
						// 전투가 끝날때까지.
						while (this.currentLandNumber.showBattleStatus()) {

							tempBattle = inBattle(this.currentLandNumber.offensivePlayer,
									this.currentLandNumber.defensivePlayer);

							if (tempBattle == 0) { // 무승부
								// 딜레이를 줘야하는데 어떻게 할지 고민좀 해보자.
								// 딜레이 중에 합류한 병사는 자연스럽게 리스트에 추가되니까 괜찮지 않을까?
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									break f;
								}
//								System.out.println("6");
								soldierCount();
								continue;
							} else if (tempBattle == 1) { // 우리팀 승리
								// 이겼으니 지나가고 해당 땅을 무소속으로 만든다.

								this.subtractLandSupplyRouteVerification(); // 적군보급로 변화

								this.currentLandNumber.landOwner = null;
								this.currentLandNumber.supplyRoute = false;
								//this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
//							
								setGui(Color.white, "주인없음");

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
							} else if (tempBattle == 2) { // 적팀 승리
								// 죽었으니 임무가 끝났다.
								this.currentLandNumber.chagebattleStatus(false);
								soldierCount();
								break f;
							} else if (tempBattle == 3) { // 두팀 전멸
								// 죽었으니 임무가 끝나고.
								// 상대방도 땅을 지킬수 없으니 땅을 무소속화 한다.
								this.subtractLandSupplyRouteVerification(); // 보급로 변화
								this.currentLandNumber.landOwner = null;
								//this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
								this.frameBlock[this.currentLandNumber.landNumber].setLandColor(Color.white);
								this.currentLandNumber.supplyRoute = false;

								setGui(Color.white, "주인없음");

								this.currentLandNumber.chagebattleStatus(false);
								break f;
							}
						}
					}

				} else { // 우리땅 or 비어있는땅

					if (this.currentLandNumber.showBattleStatus()) { // 해당 땅이 전투중이다
						this.currentLandNumber.defensivePlayer.add(this);
						soldierCount();
						while (this.currentLandNumber.showBattleStatus()) {
							try {
								Thread.sleep(1000);

							} catch (Exception e) {
								break f;
							} // 그냥 돌면 CPU확 올라간다.
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
				// 목적지에 도착했다.
				if (thisLandOurs() == 0) {
					// 주인없는 땅
					this.currentLandNumber.landOwner = this.belongKing;
					this.currentLandNumber.defensivePlayer.add(this);
					//this.frame.labelLandColorChange(this.targetLandNumber, this.belongKing.color);
					this.setGui(this.belongKing.color, this.belongKing.name);

					this.addLandSupplyRouteVerification();
					onDuty = false;
					break f;
				} else if (thisLandOurs() == 1) {
					// 우리팀땅
					this.currentLandNumber.defensivePlayer.add(this);
					soldierCount();
					onDuty = false;
					break f;
				} else if (thisLandOurs() == 2) {
					// 적군땅
					// offensivePlayer에 우리팀이 있으면 자신을 그 리스트에 포함하고 끝낸다 -> 전투는 최초 도착한 이만 계속 유지한다. -> 이거
					// 접근 잘못하면 버그나겠는데? 읽어왔을때는 전투가 안 끝났었는데 여기까지 오는데 전투가 끝났을수도 있자너.
					if (this.currentLandNumber.offensivePlayer.size() > 0) {
						// 기존에 싸우고있는 병력이 있다.
						this.currentLandNumber.offensivePlayer.add(this);
						soldierCount();
						onDuty = false;
						break f;
					} else {
						// 땅에 사람이 없다 혼자다.
						this.currentLandNumber.offensivePlayer.add(this);
						soldierCount();
						this.currentLandNumber.chagebattleStatus(true);
						// 전투가 끝날때까지.
						while (this.currentLandNumber.showBattleStatus()) {

							tempBattle = inBattle(this.currentLandNumber.offensivePlayer,
									this.currentLandNumber.defensivePlayer);

							if (tempBattle == 0) { // 무승부
								// 딜레이를 줘야하는데 어떻게 할지 고민좀 해보자.
								// 딜레이 중에 합류한 병사는 자연스럽게 리스트에 추가되니까 괜찮지 않을까?
								soldierCount();
								try {
									Thread.sleep(1000);
								} catch (Exception e) {
									break f;
								}
								continue;

							} else if (tempBattle == 1) { // 우리팀 승리
								// 목적지 도착후 승리했다
								this.subtractLandSupplyRouteVerification();
								this.currentLandNumber.landOwner = this.belongKing;
								//this.frame.labelLandColorChange(this.targetLandNumber, this.belongKing.color);
								this.frameBlock[this.currentLandNumber.landNumber].setLandColor(this.belongKing.color);

								// 위치를 옮긴다.
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
							} else if (tempBattle == 2) { // 적팀 승리
								// 죽었으니 임무가 끝났다.
								soldierCount();
								this.currentLandNumber.chagebattleStatus(false);
								break f;
							} else if (tempBattle == 3) { // 두팀 전멸
								// 죽었으니 임무가 끝나고.
								// 상대방도 땅을 지킬수 없으니 땅을 무소속화 한다.
								this.subtractLandSupplyRouteVerification();
								this.currentLandNumber.landOwner = null;
								//this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
								this.currentLandNumber.supplyRoute = false;
//								this.frameBlock[this.currentLandNumber.landNumber].setLandColor(Color.white);
								setGui(Color.white, "주인없음");

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

	// 이땅이 우리건지 확인한다
	// 0:주인없음, 1:우리팀땅, 2:적군땅
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

	// 다음땅 위치 찾기
	protected int nextLand() {
		int temp_cl, temp_tl;
		int targetLand = this.currentLandNumber.landNumber;
		int xpLandScore = 9999; // 점수가 가장 낮은 방향으로 간다.
		int xmLandScore = 9999;
		int ypLandScore = 9999;
		int ymLandScore = 9999;

		Land tempLand;
//		Block currentBlock = this.belongKing.nationalMap.findBlock(this.currentLandNumber.landNumber);
//		Block tempBlock;

		if (this.xMoveLength != 0) {
			if (this.xMoveLength > 0) {
				// 오른쪽으로 이동해야한다.
				tempLand = this.land[this.currentLandNumber.landNumber + 1]; // 오른쪽 땅
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
				// 왼쪽으로 이동해야한다
				tempLand = this.land[this.currentLandNumber.landNumber - 1]; // 왼쪽 땅
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
				// 위쪽으로 이동해야한다.
				tempLand = this.land[this.currentLandNumber.landNumber - this.mapSize]; // 위쪽땅
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
				// 아래쪽으로 이동해야한다.
				tempLand = this.land[this.currentLandNumber.landNumber + this.mapSize]; // 아래쪽땅
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
			} else if (this.xMoveLength < 0) { // 아래쪽으로
				targetLand = this.currentLandNumber.landNumber - 1;
				this.xMoveLength += 1;
			}

		} else if (this.xMoveLength == 0) {

			if (this.yMoveLength > 0) {// 위쪽으로
				targetLand = this.currentLandNumber.landNumber - this.mapSize;
				this.yMoveLength -= 1;
			} else if (this.yMoveLength < 0) { // 아래쪽으로
				targetLand = this.currentLandNumber.landNumber + this.mapSize;
				this.yMoveLength += 1;
			}

		} else if (this.xMoveLength != 0 && this.yMoveLength != 0) {

			if (this.xMoveLength > 0 && this.yMoveLength > 0) {
				if (xpLandScore < ypLandScore) { // 점수가 낮은쪽으로 간다
					targetLand = this.currentLandNumber.landNumber + 1;
					this.xMoveLength -= 1;
				}
				if (xpLandScore >= ypLandScore) {
					targetLand = this.currentLandNumber.landNumber - this.mapSize;
					this.yMoveLength -= 1;
				}
			} else if (this.xMoveLength > 0 && this.yMoveLength < 0) {
				if (xpLandScore < ypLandScore) { // 점수가 낮은쪽으로 간다
					targetLand = this.currentLandNumber.landNumber + 1;
					this.xMoveLength -= 1;
				}
				if (xpLandScore >= ypLandScore) {
					targetLand = this.currentLandNumber.landNumber + this.mapSize;
					this.yMoveLength += 1;
				}
			} else if (this.xMoveLength < 0 && this.yMoveLength > 0) {
				if (xpLandScore < ypLandScore) { // 점수가 낮은쪽으로 간다
					targetLand = this.currentLandNumber.landNumber - 1;
					this.xMoveLength += 1;
				}
				if (xpLandScore >= ypLandScore) {
					targetLand = this.currentLandNumber.landNumber - this.mapSize;
					this.yMoveLength -= 1;
				}
			} else if (this.xMoveLength < 0 && this.yMoveLength < 0) {
				if (xpLandScore < ypLandScore) { // 점수가 낮은쪽으로 간다
					targetLand = this.currentLandNumber.landNumber - 1;
					this.xMoveLength += 1;
				}
				if (xpLandScore >= ypLandScore) {
					targetLand = this.currentLandNumber.landNumber + this.mapSize;
					this.yMoveLength += 1;
				}
			}

		} else {
			System.out.println("다음 길찾기 논리 오류");
		}

		temp_cl = this.currentLandNumber.landNumber;
		temp_tl = targetLand;
		if (temp_cl != temp_tl) {
			if (this.belongKing.name.contentEquals("AI_1"))
				System.out.print(ANSI_BOLD + ANSI_BACK + ANSI_BLUE + this.name + ANSI_RESET + "이 " + temp_cl + "에서,");
			else
				System.out.print(ANSI_BOLD + ANSI_BACK + ANSI_RED + this.name + ANSI_RESET + "이 " + temp_cl + "에서,");

			System.out.println(temp_tl + "으로 이동합니다.");
		}

		return targetLand;
	}

	// 수색하기.
	// 병사가 위치한 현 위치에 다른 병사가 있는지 확인후 있다면 참조주소를 리턴하고
	// 없으면 null을 리턴한다.
	protected LinkedList<Character> reconnaissance() {
		LinkedList<Character> findCharacterList = null;

		// 왕이 같은지 확인한다.
		if (this.currentLandNumber.landOwner != this.belongKing) {
			// 땅 위에 적군이 있는지 확인한다.
			if (this.currentLandNumber.defensivePlayer.size() != 0) {
				findCharacterList = this.currentLandNumber.defensivePlayer;
			}
		}
		return findCharacterList;
	}

	// 집단 전투시 적용, 출력결과 : 0 이번턴 무승부, 1 our 승리, 2 enemy 승리, 3 두 진영 모두 전멸
	// 메소드 내부에서 전투후 체력이 없는 병사는 없앴다.
	protected int inBattle(LinkedList<Character> ourCharacterList, LinkedList<Character> enemyCharacterList) {
		int i;
		int result = 0;
		int ourAttack = 0, enemyAttack = 0;

		for (i = 0; i < ourCharacterList.size(); i++) {
			if (ourCharacterList.get(i).name.contentEquals("보병"))
				if (ourCharacterList.get(i).currentLandNumber.topography.equals("Woods")) {
					ourAttack += 1;
					enemyAttack -= 1;
				}

			if (ourCharacterList.get(i).name.contentEquals("기병"))
				if (ourCharacterList.get(i).currentLandNumber.topography.equals("Plains"))
					ourAttack += 2;

			if (ourCharacterList.get(i).name.contentEquals("방패병"))
				if (ourCharacterList.get(i).currentLandNumber.topography.equals("Canyon"))
					enemyAttack -= 2;

			ourAttack += ourCharacterList.get(i).showAttackData();
		}
		for (i = 0; i < enemyCharacterList.size(); i++) {

			if (enemyCharacterList.get(i).name.contentEquals("보병"))
				if (enemyCharacterList.get(i).currentLandNumber.topography.equals("Woods")) {
					enemyAttack += 1;
					ourAttack -= 1;
				}

			if (enemyCharacterList.get(i).name.contentEquals("기병"))
				if (enemyCharacterList.get(i).currentLandNumber.topography.equals("Plains"))
					enemyAttack += 2;

			if (enemyCharacterList.get(i).name.contentEquals("방패병"))
				if (enemyCharacterList.get(i).currentLandNumber.topography.equals("Canyon"))
					ourAttack -= 2;

			enemyAttack += enemyCharacterList.get(i).showAttackData();
		}
		// 총합 Attack으로 체럭 깍기.
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
			result = 3; // 둘다 전멸
		else if (ourCharacterList.size() == 0 && enemyCharacterList.size() != 0)
			result = 2; // 적군승리
		else if (ourCharacterList.size() != 0 && enemyCharacterList.size() == 0) {
			result = 1;
		} // 아군승리
		else if (ourCharacterList.size() != 0 && enemyCharacterList.size() != 0)
			result = 0; // 이번턴 무승부

		return result;
	}

	// 점령하기.
	protected void captureLand() {
		this.subtractLandSupplyRouteVerification();

		this.currentLandNumber.landOwner = this.belongKing;
		// this.currentLandNumber.defensivePlayer.add(this);

		this.addLandSupplyRouteVerification();

		if (this.belongKing.name.contentEquals("AI_1"))
			System.out.println(ANSI_BOLD + ANSI_BACK + ANSI_BLUE + this.name + ANSI_RESET + "이,"
					+ this.currentLandNumber.landNumber + "번 땅을 점령했습니다.");
		else
			System.out.println(ANSI_BOLD + ANSI_BACK + ANSI_RED + this.name + ANSI_RESET + "이,"
					+ this.currentLandNumber.landNumber + "번 땅을 점령했습니다.");
	}

	protected void addLandSupplyRouteVerification() {
		// 점령후에 사용한다
		FindingWay fw = new FindingWay(this.belongKing, this.currentLandNumber);
		//fw.frame = this.frame;
		Thread fwT = new Thread(this.tg1, fw);
		fwT.start();
	}

	protected void subtractLandSupplyRouteVerification() {
		// 점령전에 사용한다.
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
		int temp = 0; // 임시변수
		int i, j;
		int currentX = 0, currentY = 0;
		int targetX = 0, targetY = 0;
		int[][] map = new int[x][y]; // 가상의 맵 생성

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
			// 좌 & 우 이동 확인
			if ((currentX % mapSize) == (targetX % mapSize)) {
				// 같은 Y축에 있다. 이동없음.
				break;
			} else if ((currentX % mapSize) > (targetX % mapSize)) {
				// 현 위치가 더 크다, 목표보다 오른쪽에 있다, 왼쪽으로 이동
				this.xMoveLength -= 1;
				currentX -= 1;
			} else if ((currentX % mapSize) < (targetX % mapSize)) {
				// 현 위치가 더 작다, 목표보다 왼쪽에 있다, 오른쪽으로 이동
				this.xMoveLength += 1;
				currentX += 1;
			}
		}
		while (true) {
			if (currentY == targetY) {
				// 같은 x축에 있다. 이동없음
				break;

			} else if (currentY > targetY) {
				// 현위치가 더 크다, 목표보다 아래 있다, 위로 이동
				this.yMoveLength += 1;
				currentY -= 1;

			} else if (currentY < targetY) {
				// 현위치가 더 작다, 목표보다 위에 있다, 아래로 이동
				this.yMoveLength -= 1;
				currentY += 1;
			}
		}

	} // end navigationOnlyLandNum

}
