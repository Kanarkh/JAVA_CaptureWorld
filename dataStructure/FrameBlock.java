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
		this.landNum.setText("����ȣ : " + landNum);
		this.preLandNum=landNum;
	}

	public void setLandOwner(String owner) {
		this.landOwner.setText("������ : " + owner);
		this.preLandOwner=owner;
	}

	public void setLandGold(int gold) {
		this.landGold.setText("��   �� : " + gold);
		this.preGold=gold;
	}

	public void setLandTopography(String topography) {
		this.landTopography.setText("��   �� : " + topography);
		this.preTopography=topography;
	}

	public void setSupply(boolean state) {
		if (state == false)
			this.supply.setText("���޷� : �����������");
		else if (state == true)
			this.supply.setText("���޷� : �����");
		
		this.preSupplyState=state;
	}
	public void setBuilding(boolean state,String name) {
		if (state == false)
			this.building.setText("��   �� : ����");
		else if (state == true)
			this.building.setText("��   �� : "+name);
	}
	public void setDefensive(int num) {
		this.defensive.setText("���� ��:"+num);
	}
	public void setOffensive(int num) {
		this.offensive.setText("���ݺ��� ��:"+num);
	}
}
