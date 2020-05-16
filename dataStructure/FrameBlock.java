import java.awt.Color;

import javax.swing.JLabel;

public class FrameBlock {
	JLabel landNum;
	JLabel landOwner;
	JLabel landGold;
	JLabel landTopography;
	JLabel supply;
	JLabel building;
	JLabel defensive;
	JLabel offensive;
	JLabel landColor;
	
	private int preLandNum;
	private String preLandOwner;
	private int preGold;
	private String preTopography;
	private boolean preSupplyState;
	
	public FrameBlock() {
		
	}
	
	public void setLandColor(Color color) {
		this.landColor.setBackground(color);
		this.setLandNum(this.preLandNum);
		this.setLandOwner(this.preLandOwner);
		this.setLandGold(this.preGold);
		this.setLandTopography(this.preTopography);
		this.setSupply(this.preSupplyState);
//		this.landColor.transferFocusBackward();
//		this.landColor.isBackgroundSet();
//		this.landColor.repaint();
//		this.landNum.repaint();
//		this.landOwner.repaint();
//		this.landGold.repaint();
//		this.landTopography.repaint();
//		this.supply.repaint();
	}
	public void setLandNum(int landNum) {
		this.landNum.setText("땅번호 : " + landNum);
		this.preLandNum=landNum;
	}

	public void setLandOwner(String owner) {
		this.landOwner.setText("땅주인 : " + owner);
		this.preLandOwner=owner;
	}

	public void setLandGold(int gold) {
		this.landGold.setText("세   금 : " + gold);
		this.preGold=gold;
	}

	public void setLandTopography(String topography) {
		this.landTopography.setText("지   형 : " + topography);
		this.preTopography=topography;
	}

	public void setSupply(boolean state) {
		if (state == false)
			this.supply.setText("보급로 : 연결되지않음");
		else if (state == true)
			this.supply.setText("보급로 : 연결됨");
		
		this.preSupplyState=state;
	}
	public void setBuilding(boolean state,String name) {
		if (state == false)
			this.building.setText("건   물 : 없음");
		else if (state == true)
			this.building.setText("건   물 : "+name);
	}
	public void setDefensive(int num) {
		this.defensive.setText("방어병력 수:"+num);
	}
	public void setOffensive(int num) {
		this.offensive.setText("공격병력 수:"+num);
	}
}
