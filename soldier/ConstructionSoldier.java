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
				// 목적지에 도착하지 못했다
				if (thisLandOurs() == 2) {
					// 적군땅
					if (this.currentLandNumber.showBattleStatus() == true) {

						// 아군이 전투중일때 안지나가고 함께 전투에 참여했다가 지나갈수도 있다.
						this.currentLandNumber.offensivePlayer.add(this);
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
								System.out.println("6");
								continue;
							} else if (tempBattle == 1) { // 우리팀 승리
								// 이겼으니 지나가고 해당 땅을 무소속으로 만든다.

								this.subtractLandSupplyRouteVerification(); // 적군보급로 변화

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
							} else if (tempBattle == 2) { // 적팀 승리
								// 죽었으니 임무가 끝났다.
								this.currentLandNumber.chagebattleStatus(false);
								break f;
							} else if (tempBattle == 3) { // 두팀 전멸
								// 죽었으니 임무가 끝나고.
								// 상대방도 땅을 지킬수 없으니 땅을 무소속화 한다.
								this.subtractLandSupplyRouteVerification(); // 보급로 변화
								this.currentLandNumber.landOwner = null;
//								this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
								this.currentLandNumber.chagebattleStatus(false);
								break f;
							}
						}
					}

				} else { // 우리땅 or 비어있는땅

					if (this.currentLandNumber.showBattleStatus()) { // 해당 땅이 전투중이다
						this.currentLandNumber.defensivePlayer.add(this);
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
//					this.frame.labelLandColorChange(this.targetLandNumber, this.belongKing.color);
					this.addLandSupplyRouteVerification();
					
					///건물짓기 행동///
					
					onDuty = false;
					break f;
				} else if (thisLandOurs() == 1) {
					// 우리팀땅
					this.currentLandNumber.defensivePlayer.add(this);
					
					///건물짓기 행동///
					
					onDuty = false;
					break f;
				} else if (thisLandOurs() == 2) { 
					// 적군땅
					// offensivePlayer에 우리팀이 있으면 자신을 그 리스트에 포함하고 끝낸다 -> 전투는 최초 도착한 이만 계속 유지한다. -> 이거
					// 접근 잘못하면 버그나겠는데? 읽어왔을때는 전투가 안 끝났었는데 여기까지 오는데 전투가 끝났을수도 있자너.
					if (this.currentLandNumber.offensivePlayer.size() > 0) {
						// 기존에 싸우고있는 병력이 있다.
						this.currentLandNumber.offensivePlayer.add(this);
						onDuty = false;
						break f;
					} else {
						// 땅에 사람이 없다 혼자다.
						this.currentLandNumber.offensivePlayer.add(this);
						this.currentLandNumber.chagebattleStatus(true);
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
								continue;

							} else if (tempBattle == 1) { // 우리팀 승리
								// 목적지 도착후 승리했다
								this.subtractLandSupplyRouteVerification();
								this.currentLandNumber.landOwner = this.belongKing;
//								this.frame.labelLandColorChange(this.targetLandNumber, this.belongKing.color);

								// 위치를 옮긴다.
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
							} else if (tempBattle == 2) { // 적팀 승리
								// 죽었으니 임무가 끝났다.
								this.currentLandNumber.chagebattleStatus(false);
								break f;
							} else if (tempBattle == 3) { // 두팀 전멸
								// 죽었으니 임무가 끝나고.
								// 상대방도 땅을 지킬수 없으니 땅을 무소속화 한다.
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
		// 명령된 위치에 건물을 만들다.

	}
}
