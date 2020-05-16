import java.awt.Color;

public class NativeCharacter extends Character implements Runnable {
	private int chance;
	private int frequncy;
	private Land[] land;
//	private Frame frame;
	private King nonExistentKing;
	private FrameBlock[] fb;
	
	public NativeCharacter(int chance, int frequncy, Land[] land) {
		this.chance = chance; // 1~100±îÁöÀÇ È®·ü
		this.frequncy = frequncy; // ºóµµ¼ö * 1000ms
		this.land = land;
//		this.frame = frame;
		this.setLife(1);
		this.setAttack(1);

		this.nonExistentKing = new King();
		this.nonExistentKing.color = Color.LIGHT_GRAY;
		this.nonExistentKing.name = "noName";
	}

	public void SetFramBlock(FrameBlock[] frameBlock) {
		this.fb = frameBlock;
	}
	
	public void run() {
		while (true) {
			try {
				Thread.sleep(1000 * this.frequncy);
				this.nativeCharacterGenerate();
			} catch (Exception e) {
				break;
			}
		}
	}

	// º´»ç¸¦ ¸¸µç´Ù.
	private void nativeCharacterGenerate() {
		int i;
		for (i = 0; i < land.length; i++) {
			if (land[i].landOwner == null || land[i].landOwner == this.nonExistentKing) {
				if (this.chanceCalculation()) {
					Soldier tempSoldier = new Soldier(1,1);
					tempSoldier.name = "ÅäÂø¹Î";
					this.land[i].defensivePlayer.add(tempSoldier);
					this.land[i].landOwner = this.nonExistentKing;
//					this.frame.labelLandColorChange(i, this.nonExistentKing.color);
					this.landDevelopment(land[i]);
					
					this.fb[i].setLandGold(land[i].turnGold);
					this.fb[i].setLandOwner("ÅäÂø¹Î");
					this.fb[i].setDefensive(this.land[i].defensivePlayer.size());
					this.fb[i].setOffensive(this.land[i].offensivePlayer.size());
					this.fb[i].setLandColor(this.nonExistentKing.color);
					
				}
			}
		}
	}

	// È®·üÀ» °è»êÇÑ´Ù.
	private boolean chanceCalculation() {
		boolean result = false;
		int temp = (int) (Math.random() * 100) + 1;

		if (temp >= 100-this.chance)
			result = true;
		else
			result = false;

		return result;
	}
	
	private void landDevelopment(Land targetLand) {
		targetLand.turnGold +=1;
	}
}
