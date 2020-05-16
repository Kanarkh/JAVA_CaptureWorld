import java.awt.Color;
import java.util.LinkedList;

public class King extends Character {
	int KingLevel;
	int holdingGold;
	Color color;
	String origin;
	King enemyKing;
	boolean skillAvailable;
	boolean aiOption=false;
	GraphMap nationalMap;
	LinkedList<Land> listLandHeld = new LinkedList<Land>();
	FrameBlock[] frameBlock;
	GameFrame_1 gameFrame;
	
	
	public King() {
		this.skillAvailable = false;
		
	}
	public void setGameFrame(GameFrame_1 gameFrame) {
		this.gameFrame = gameFrame;
		this.gameFrame.setPlayerStat(this);
	}
	
	public void SetFramBlock(FrameBlock[] frameBlock) {
		this.frameBlock = frameBlock;
	}
	
	public synchronized void changeHoldingGold(int pmGold) {
		this.holdingGold += pmGold;
	}

	public synchronized int howMuchGoldDoYouHave() {
		return this.holdingGold;
	}

	public void setNationlMap(int startPoint, Land[] land, int mapSize) {
		this.nationalMap = new GraphMap(startPoint, land, mapSize);
		if(this.nationalMap.startBlock.land != this.currentLandNumber) {
			System.out.println("GraphMap 오류");
		}
		
	}
	
	@Override
	public synchronized void changeLife(int num) {
		this.life += num;
		this.gameFrame.setPlayerStat(this);
	}

	public King(String kingName, Land currentLandNumber, Color color) {
		this.name = kingName;
		this.currentLandNumber = currentLandNumber;
		this.KingLevel = 1;
		this.holdingGold = 1;
		this.setAttack(3);
		this.setLife(10);
		this.skillAvailable = false;
		this.color = color;
		this.setOrigin("King");
	}

	public Soldier createSoldier(int soldierNum) {
		String soldierName = "";
		Soldier soldier = null;
		SpySoldier tempSpy;
		ThiefBornKing thief;

		int newLife = 0, newAttack = 0, newSpeed = 0;
		if (soldierNum == 0) {
			soldierName = "보병";
			newAttack = 2;
			newLife = 4;
			newSpeed = 1;
			this.holdingGold -= 2;
		} else if (soldierNum == 1) {
			soldierName = "방패병";
			newAttack = 1;
			newLife = 7;
			newSpeed = 1;
			this.holdingGold -= 3;
		} else if (soldierNum == 2) {
			soldierName = "기병";
			newAttack = 3;
			newLife = 5;
			newSpeed = 2;
			this.holdingGold -= 4;
		} else if (soldierNum == 3) {
			soldierName = "폭탄병";
			newAttack = 2;
			newLife = 2;
			newSpeed = 1;
			this.holdingGold -= 8;
		} else if (soldierNum == 4) {
			soldierName = "공작원";
			newAttack = 1;
			newLife = 1;
			newSpeed = 1;
			this.holdingGold -= 10;
		} 
//		else if (soldierNum == 5) {
//			soldierName = "기술자";
//			newAttack = 0;
//			newLife = 1;
//			newSpeed = 1;
//			this.holdingGold -= 10;
//		}

		if (soldierNum < 3)
			soldier = new Soldier(soldierName, this);
		else if (soldierNum == 3)
			soldier = new BombingSoldier(soldierName, this);
		else if (soldierNum == 4) { // 공작원
			soldier = new SpySoldier(soldierName, this);
			tempSpy = (SpySoldier) soldier;
			tempSpy.chance = 10;
			if(this.origin.equals("Thief")) {
				thief = (ThiefBornKing) this;
				tempSpy.giftOfGab += thief.invidia;
			}	
			else {
				tempSpy.giftOfGab = 1;
			}
			
			
			tempSpy = null;
		} else if (soldierNum == 5);
			

		soldier.setLife(newLife);
		soldier.setAttack(newAttack);
		soldier.movingSpeed = newSpeed;
		soldier.currentLandNumber = this.currentLandNumber;
		soldier.belongKing = this;

		return soldier;
	}

	public void TransferCommandSoldier(Soldier soldier, int targetLandNum) {
		soldier.targetLandNumber = targetLandNum;
	}

	public void kingTraining() {
		if (this.holdingGold >= 5) {
			this.KingLevel++;
			this.changeAttack(1);
			this.changeLife(3);
			this.holdingGold -= 5;
		} else
			System.out.println("금화가 부족합니다");
	}
	
	public void kingdomUpgrade() {
		if (this.holdingGold >= 10) {
			this.currentLandNumber.turnGold += 1;
			this.holdingGold -= 10;
		} else
			System.out.println("금화가 부족합니다");
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void SetskillAvailable(boolean available) {
		this.skillAvailable = available;
	}
}
