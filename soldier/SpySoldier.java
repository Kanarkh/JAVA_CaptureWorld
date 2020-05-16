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
								// this.frame.labelLandColorChange(this.currentLandNumber.landNumber,
								// Color.white);
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
								// this.frame.labelLandColorChange(this.currentLandNumber.landNumber,
								// Color.white);
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

					if (this.currentLandNumber.defensivePlayer.size() != 0) {
						if (this.plotRevolt(this.targetLandNumber)) {
							// 반란성공 적군을 아군으로 만들기
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
					// 우리팀땅
					this.currentLandNumber.defensivePlayer.add(this);
					if (this.currentLandNumber.showBattleStatus()) {
						if (this.plotRevolt(this.targetLandNumber)) {
							// 반란성공 적군을 아군으로 만들기
							addLandSupplyRouteVerification();
							for (int i = 0; i < this.currentLandNumber.offensivePlayer.size(); i++) {
								tempSoldier = (Soldier) this.currentLandNumber.offensivePlayer.get(i);
								tempSoldier.belongKing = this.belongKing;
								this.currentLandNumber.defensivePlayer
										.add(this.currentLandNumber.offensivePlayer.get(i));
							}
							this.currentLandNumber.offensivePlayer.clear();
							this.currentLandNumber.chagebattleStatus(false);
							// 이렇게 하면 전투진행중인 쓰레드가 알아서 처리를 할것이다.
							// 변수 상황에 어떻게 대처하지? ( 적이 우리팀땅을 점령하고 위치를 바꾸는 도중 이게 실행되는 경우) 등
						} else
							this.setLife(0);
					}
					onDuty = false;
					break f;
				} else if (thisLandOurs() == 2) {
					// 적군땅

					if (this.currentLandNumber.offensivePlayer.size() <= 0) {
						// 해당 땅에 살아있는 유일한 쓰레드 점령까지 해줘야한다.
						if (this.plotRevolt(this.targetLandNumber)) {
							subtractLandSupplyRouteVerification();
							// 반란성공 적군을 아군으로 만들기
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
							// 반란성공 적군을 아군으로 만들기
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
							// 이렇게 하면 전투진행중인 쓰레드가 알아서 처리를 할것이다.

						} else
							this.setLife(0);

						break f;
					}

				}
				break f;
			} // end 목적지

		} // end while
		soldierCount();
		System.out.println("end Spy");

	}// end run

//	public void run() {
//		boolean onDuty = true;
//		int tempBattle;
//		Soldier tempSoldier;
//		// 목적지 땅에 위치하는지 조사한다.
//		// 목적지 땅이 아니라면 다음 위치로 이동한다
//
//		// 목적지에 도착할때까지 계속 반복한다.
//		f: while (onDuty) {
//			try {
//				Thread.sleep(1);
//			}catch(Exception e) {
//				break f;
//			}
//			if (this.currentLandNumber.landNumber != this.targetLandNumber) {
//				// 목적지에 도착하지 못했다
//				if (thisLandOurs() == 2) {
//					// 적군땅
//					if (this.currentLandNumber.showBattleStatus() == true) {
//						// 아군이 적과 전투중 지나간다
//						this.currentLandNumber.offensivePlayer.add(this);
//						while(this.currentLandNumber.showBattleStatus()) {
//							try {
//									Thread.sleep(1000);
//									
//							} catch (Exception e) {
//								break f;
//							} //그냥 돌면 CPU확 올라간다. 
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
//						// 아군은 없는데 적군땅이다. 이겨야 지나갈수 있다.
//						// 적군과 전투해서 이겨야 지나간다.
//						this.currentLandNumber.chagebattleStatus(true);
//						this.currentLandNumber.offensivePlayer.add(this);
//						// 전투가 끝날때까지.
//						while (this.currentLandNumber.showBattleStatus()) {
//							tempBattle = inBattle(this.currentLandNumber.offensivePlayer,
//									this.currentLandNumber.defensivePlayer);
//
//							if (tempBattle == 0) { // 무승부
//								// 딜레이를 줘야하는데 어떻게 할지 고민좀 해보자.
//								// 딜레이 중에 합류한 병사는 자연스럽게 리스트에 추가되니까 괜찮지 않을까?
//								try {
//									Thread.sleep(1000);
//								} catch (Exception e) {
//									break f;
//								}
//								System.out.println("6");
//								continue;
//							} else if (tempBattle == 1) { // 우리팀 승리
//								// 이겼으니 지나가고 해당 땅을 무소속으로 만든다.
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
//							} else if (tempBattle == 2) { // 적팀 승리
//								// 죽었으니 임무가 끝났다.
//								this.currentLandNumber.chagebattleStatus(false);
//								break f;
//							} else if (tempBattle == 3) { // 두팀 전멸
//								// 죽었으니 임무가 끝나고.
//								// 상대방도 땅을 지킬수 없으니 땅을 무소속화 한다.
//								this.currentLandNumber.landOwner = null;
//								//this.frame.labelLandColorChange(this.currentLandNumber.landNumber, Color.white);
//								this.currentLandNumber.chagebattleStatus(false);
//								break f;
//							}
//						}
//					}
//
//				} else { // 우리땅 or 비어있는땅
//					// 지나간다
//					if(this.currentLandNumber.showBattleStatus()) {
//						this.currentLandNumber.defensivePlayer.add(this);
//						while(this.currentLandNumber.showBattleStatus()) {
//							try {
//									Thread.sleep(1000);
//									
//							} catch (Exception e) {
//								break f;
//							} //그냥 돌면 CPU확 올라간다. 
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
//				// 목적지에 도착했다.
//				if (thisLandOurs() == 0) {
//					// 주인없는 땅
//
//					if (this.currentLandNumber.defensivePlayer.size() != 0) {
//						if (this.plotRevolt(this.targetLandNumber)) {
//							// 반란성공 적군을 아군으로 만들기
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
//					// 우리팀땅
//					this.currentLandNumber.defensivePlayer.add(this);
//					if (this.currentLandNumber.showBattleStatus()) {
//						if (this.plotRevolt(this.targetLandNumber)) {
//							// 반란성공 적군을 아군으로 만들기
//
//							for (int i = 0; i < this.currentLandNumber.offensivePlayer.size(); i++) {
//								tempSoldier = (Soldier) this.currentLandNumber.offensivePlayer.get(i);
//								tempSoldier.belongKing = this.belongKing;
//								this.currentLandNumber.defensivePlayer
//										.add(this.currentLandNumber.offensivePlayer.get(i));
//							}
//							this.currentLandNumber.offensivePlayer.clear();
//							this.currentLandNumber.chagebattleStatus(false);
//							// 이렇게 하면 전투진행중인 쓰레드가 알아서 처리를 할것이다.
//							// 변수 상황에 어떻게 대처하지? ( 적이 우리팀땅을 점령하고 위치를 바꾸는 도중 이게 실행되는 경우) 등
//						} else
//							this.setLife(0);
//					}
//					onDuty = false;
//					break f;
//				} else if (thisLandOurs() == 2) {
//					// 적군땅
//
//					if (this.currentLandNumber.offensivePlayer.size() <= 0) {
//						// 해당 땅에 살아있는 유일한 쓰레드 점령까지 해줘야한다.
//						if (this.plotRevolt(this.targetLandNumber)) {
//							// 반란성공 적군을 아군으로 만들기
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
//							// 반란성공 적군을 아군으로 만들기
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
//							// 이렇게 하면 전투진행중인 쓰레드가 알아서 처리를 할것이다.
//
//						} else
//							this.setLife(0);
//
//						break f;
//					}
//					
//				}
//				break f;
//			}//end 목적지
//		} // end while
//		System.out.println("end Spy");
//	}

	private boolean plotRevolt(int targetLandNum) {
		boolean temp = false;
		// 상황에 따라 다르게 처리해야한다.
		// 상대방 땅에서 전투중이라면 이미 다른 쓰레드가 작동하고 있다. 그러니 그 쓰레드를 죽이고 이 쓰레드가 이벤트처리하는게 아니라 원하는 결과가
		// 나타나도록 상황을 바꿔주면된다.

		if (this.chanceCalculation() && targetLandNum != 0 && targetLandNum != 24) {
			// 반란에 성공하다.
			System.out.println("반란에 성공했다");
			temp = true;
		} else {
			// 반란에 실패하다.
			System.out.println("반란에 실패했다");
			temp = false;
		}

		return temp;
		// 반란에 실패하면 공작원이 죽고 끝이난다.
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
