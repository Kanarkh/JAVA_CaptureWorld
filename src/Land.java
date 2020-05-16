import java.util.LinkedList;

public class Land {
	int landNumber;
	int turnGold;
	King landOwner;
//	Frame frame;
	String topography;

	LinkedList<Character> offensivePlayer = new LinkedList<Character>();
	LinkedList<Character> defensivePlayer = new LinkedList<Character>();

	boolean buildingPresence;
	boolean supplyRoute; // false = The supply line is not connected
	private boolean battleStatus; // false = Be not in Battle , true = Be in battle

	public Land(int landNumber, int turnGold) {
		this.landNumber = landNumber;
		this.turnGold = turnGold;
		this.supplyRoute = false;
		this.battleStatus = false;

		this.buildingPresence = false;
		this.setRandomTopography();
	}

	public void setRandomTopography() {
		int temp = (int) (Math.random() * 4);

		if (temp == 0) {
			this.topography = "Grassland";
		} else if (temp == 1) {
			this.topography = "Plains";
		} else if (temp == 2) {
			this.topography = "Woods";
		} else if (temp == 3) {
			this.topography = "Canyon";
		}
	}

	public void chageSupplyRoute(boolean status) {
		this.supplyRoute = status;
	}

	public boolean showSupplyRouteStatus() {
		return this.supplyRoute;
	}

	public void chagebattleStatus(boolean status) {
		this.battleStatus = status;
	}

	public boolean showBattleStatus() {
		return this.battleStatus;
	}

	public void ChangeTurnGold(int turnGold) {
		this.turnGold = turnGold;
	}

	public void turnGold() {
		this.landOwner.holdingGold += this.turnGold;
	}

	// synchronized
	public boolean ReadWriteSupplyRoute(String RorW, boolean changeValue) {
//		boolean preResult = this.supplyRoute;
		boolean result = false;

		if (RorW.contentEquals("R")) {
			result = this.supplyRoute;
		} else if (RorW.contentEquals("W")) {
			this.supplyRoute = changeValue;
			result = this.supplyRoute;
		}
		/*
		 * if (preResult != result) { if (result == false) { //
		 * System.out.println(this.landNumber+"번땅의 보급로가 끊어졌습니다.");
		 * this.frame.labelLand[this.landNumber].setText("보급로 끊어짐"); } else {
		 * this.frame.labelLand[this.landNumber].setText("보급로 연결됨"); //
		 * System.out.println(this.landNumber+"번땅의 보급로가 연결됬습니다."); } }
		 */
		return result;
	}
}
