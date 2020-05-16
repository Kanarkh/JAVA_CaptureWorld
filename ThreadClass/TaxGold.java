public class TaxGold implements Runnable {
	Land[] land;
	private int Taxtime;
	int currentTime;
	int totalPlayTime;
	King player0, player1;
//	Frame frame;
	FrameBlock[] fb;
	GameFrame_1 gameFrame;

	public TaxGold(King player0, King player1, Land[] land, GameFrame_1 gf) {
		this.land = land;
		this.player0 = player0;
		this.player1 = player1;
		this.currentTime = 0;
		this.totalPlayTime = 0;
		this.Taxtime = 5;
		this.gameFrame = gf;
//		this.frame = frame;
	}

	public void SetFramBlock(FrameBlock[] frameBlock) {
		this.fb = frameBlock;
	}

	public void run() {
		// 일정시간이 지나면 돈을 받는다.
		// 땅을 가지고 있고, 보급로가 연결된 땅 주인에게 세금을 준다.
		int i;
		int player0G, player1G;
		MerchantBornKing tempMerchantKing0 = null, tempMerchantKing1 = null;
		ThiefBornKing tempThiefKing0 = null, tempThiefKing1 = null;
		if (player0.origin.equals("Merchant")) {
			tempMerchantKing0 = (MerchantBornKing) player0;
		} else if (player0.origin.equals("Thief")) {
			tempThiefKing0 = (ThiefBornKing) player0;
		}

		if (player1.origin.equals("Merchant")) {
			tempMerchantKing1 = (MerchantBornKing) player1;
		} else if (player1.origin.equals("Thief")) {
			tempThiefKing1 = (ThiefBornKing) player1;
		}

		while (true) {
			try {
				Thread.sleep(1000);
				currentTime++;
				totalPlayTime++;

			} catch (InterruptedException e) {
				break;
			}

			if (currentTime % 1 == 0) {
//				frame.labelTime("남은시간 : " + String.valueOf(5 - currentTime));
				this.gameFrame.setTime(this.totalPlayTime);
				this.gameFrame.setTexTime(currentTime);
			}
			if (currentTime >= Taxtime) {
				currentTime = 0;
				player0G = 0;
				player1G = 0;

				for (i = 0; i < land.length; i++) {
					if (land[i].supplyRoute) { // 보급로가 연결되있을시에만

						if (land[i].landOwner == player0) {
							if (tempThiefKing1 == null) {
								if (player0.origin.equals("Merchant")) {
									if ((int) (Math.random() * 50) <= tempMerchantKing0.greed) {
										player0G += (land[i].turnGold * 2);
										System.out.println(land[i].landNumber + "번 땅에서 세금을 두 배로 걷었다!");
									} else {
										player0G += land[i].turnGold;
									}
								} else
									player0G += land[i].turnGold;
							} else {
								if (tempThiefKing1.blockTaxState() == false) {
									if (player0.origin.equals("Merchant")) {
										if ((int) (Math.random() * 50) <= tempMerchantKing0.greed) {
											player0G += (land[i].turnGold * 2);
											System.out.println(land[i].landNumber + "번 땅에서 세금을 두 배로 걷었다!");
										} else {
											player0G += land[i].turnGold;
										}
									} else
										player0G += land[i].turnGold;
								}
							}

						} else if (land[i].landOwner == player1) {
							if (tempThiefKing0 == null) {
								if (player1.origin.equals("Merchant")) {
									if ((int) (Math.random() * 50) <= tempMerchantKing1.greed) {
										player1G += (land[i].turnGold * 2);
									} else {
										player1G += land[i].turnGold;
									}

								} else
									player1G += land[i].turnGold;
							} else {
								if (tempThiefKing0.blockTaxState() == false) {
									if (player1.origin.equals("Merchant")) {
										if ((int) (Math.random() * 50) <= tempMerchantKing1.greed) {
											player1G += (land[i].turnGold * 2);
										} else {
											player1G += land[i].turnGold;
										}

									} else
										player1G += land[i].turnGold;
								}
							}
						}
					}
				}
				player0.changeHoldingGold(player0G);
				player1.changeHoldingGold(player1G);
				System.out.println(player1G);

				this.gameFrame.setPlayerGold(player0.holdingGold);
//				frame.labelGold("골드 :" + player0.holdingGold);
//				frame.labelGoldAi("골드 :" + player1.holdingGold);

			}
//			if (this.totalPlayTime == 1080) {
//				this.player0.SetskillAvailable(true);
//				this.player1.SetskillAvailable(true);
//			}

		}
	}
}
