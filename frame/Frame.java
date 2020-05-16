import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Frame extends JFrame {
	ThreadGroup tg1;

	private JButton button1;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JPanel panel;
	private JLabel labelTime;
	private JLabel labelGold;
	JLabel labelGoldAi;
	JLabel[] labelLand = new JLabel[25];

	King player;
	King[] aiPlayer = { null, null, null };
	Land[] land;
	Frame mainFrame;
	FrameBlock[] fb;

	private Listener listener = new Listener();
	
	public void setThreadGroup(ThreadGroup tg1) {
		this.tg1 = tg1;
	}
	
	public void SetFramBlock(FrameBlock[] frameBlock) {
		this.fb = frameBlock;
	}

	public Frame(King player, King aiPlayer0, King aiPlayer1, King aiPlayer2, Land[] land) {
		this.player = player;
		this.aiPlayer[0] = aiPlayer0;
		this.aiPlayer[1] = aiPlayer1;
		this.aiPlayer[2] = aiPlayer2;
		this.land = land;
		this.mainFrame = this;

		// 모니터의 중앙에 띄우기
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		this.setLocation((screenSize.width / 2) - 300, (screenSize.height / 2) - 300);

		// 화면세팅
		this.setTitle("땅따먹기");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		labelTime = new JLabel("남은시간 : " + "0");
		labelGold = new JLabel("골드 :" + player.holdingGold);
		labelGoldAi = new JLabel("골드 :" + aiPlayer[0].holdingGold);
		button1 = new JButton("병사징집");
		button2 = new JButton("국왕훈련");
		button3 = new JButton("왕국발전");
		button4 = new JButton("스킬사용하기");
		button5 = new JButton("TEST");

		button1.addActionListener(listener);
		button2.addActionListener(listener);
		button3.addActionListener(listener);
		button4.addActionListener(listener);
		button5.addActionListener(listener);
		for (int i = 0; i < labelLand.length; i++) {
			labelLand[i] = new JLabel(String.valueOf(i));
			labelLand[i].setPreferredSize(new Dimension(100, 100));
			labelLand[i].setOpaque(true);
			labelLand[i].setBackground(Color.white);
			panel.add(labelLand[i]);
		}

		this.labelLandColorChange(0, Color.red);
		this.labelLandColorChange(24, Color.ORANGE);

		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);
		panel.add(labelTime);
		panel.add(labelGold);
		panel.add(labelGoldAi);

		this.add(panel);
		this.setVisible(true);
	}

	public void closeFrameAll() {
		listener.closeFrame();
		this.dispose();
	}

	public void labelGold(String text) { 
		this.labelGold.setText(text);
	}

	public void labelGoldAi(String text) { 
		this.labelGoldAi.setText(text);
	}

	public void labelTime(String text) { // 세금 남은시간
		this.labelTime.setText(text);
	}

	public void labelLandColorChange(int landNum, Color color) { // 색 바꾸기.
		this.labelLand[landNum].setBackground(color);
//		this.fb[landNum].setLandColor(color);

	}

	class Listener implements ActionListener {
		FrameSoldier fs;
		FrameSkill fskill;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button1) { // 병사징집
				FrameSoldier frameSoldier = new FrameSoldier();
				fs = frameSoldier;
			}
			if (e.getSource() == button2) {
				player.kingTraining();
				labelGold.setText("골드 :" + player.holdingGold);
			}
			if (e.getSource() == button3) {
				player.kingdomUpgrade();
				labelGold.setText("골드 :" + player.holdingGold);
			}
			if (e.getSource() == button4) {
				FrameSkill frameSkill = new FrameSkill();
				fskill = frameSkill;
				labelGold.setText("골드 :" + player.holdingGold);
			}
			if (e.getSource() == button5) { // 병사징집
				FrameTest frameTest = new FrameTest();
//				fs = frameSoldier;
			}

		}

		public void closeFrame() {
			if (fs != null)
				fs.dispose();
			if (fskill != null)
				fskill.dispose();
		}
	}

	class FrameTest extends JFrame {

		String[] soldierName = { "병사1", "병사2", "병사3", "병사4", "병사5" };
		Vector<String> landVector = new Vector<String>();;

		private JButton button1;

		private JPanel panel;
		private JLabel labe1;
		private JLabel labe2;
		private JLabel labe3;
		private JLabel labe4;

		private Listener listener1 = new Listener();
//		private JComboBox soldierCombo;
		private JComboBox landCombo;

		public FrameTest() {

			// 모니터의 중앙에 띄우기
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			this.setLocation((screenSize.width / 2) - 650, (screenSize.height / 2) - 300);

			this.setTitle("병사소집");
			this.setSize(365, 200);

			for (int i = 0; i < land.length; i++)
				landVector.add(land[i].landNumber + "번땅");
			panel = new JPanel();
			labe1 = new JLabel("");
			labe2 = new JLabel("");
			labe3 = new JLabel("");
			labe4 = new JLabel("");
			labe4 = new JLabel("");
			button1 = new JButton("연결확인");

//			soldierCombo = new JComboBox(soldierName);
			landCombo = new JComboBox(landVector);

			button1.addActionListener(listener1);
			button2.addActionListener(listener1);

			panel.add(labe1);
			panel.add(labe2);
			panel.add(labe3);
			panel.add(labe4);

//			panel.add(soldierCombo);
			panel.add(landCombo);
			panel.add(button1);

			this.add(panel);
			this.setVisible(true);
//			System.out.println(soldierCombo.getActionCommand());

		}

		class Listener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button1) { // 병사징집
					int intTemp, targetLand;
					Block tempBlock;
//					Soldier tempSoldier;
//					intTemp = soldierCombo.getSelectedIndex();
					targetLand = landCombo.getSelectedIndex();

					tempBlock = player.nationalMap.findBlock(targetLand);

					if (tempBlock.preBlock0 != null) {
						labe1.setText("\""+tempBlock.preBlock0.land.landNumber + "과 연결됨\\                      ");
					} else {
						labe1.setText("널포인트\\                      ");
					}
					
					if (tempBlock.preBlock1 != null) {
						labe2.setText(tempBlock.preBlock1.land.landNumber + "과 연결됨\\                      ");
					} else {
						labe2.setText("널포인트\\                      ");
					}
					
					if (tempBlock.nextBlock0 != null) {
						labe3.setText(tempBlock.nextBlock0.land.landNumber + "과 연결됨\\                      ");
					} else {
						labe3.setText("널포인트\\                      ");
					}
					if (tempBlock.nextBlock1 != null) {
						labe4.setText(tempBlock.nextBlock1.land.landNumber + "과 연결됨\\                      ");
					} else {
						labe4.setText("널포인트\\                      ");
					}

				}

			}
		}
	}// end FrameSoldier

	class FrameSoldier extends JFrame {

		String[] soldierName = { "병사1", "병사2", "병사3", "병사4", "병사5" };
		Vector<String> landVector = new Vector<String>();;

		private JButton button1;

		private JPanel panel;
		private JLabel label;
		private JLabel labe2;
		private JLabel labe3;
		private JLabel labe4;

		private Listener listener1 = new Listener();
		private JComboBox soldierCombo;
		private JComboBox landCombo;

		public FrameSoldier() {

			// 모니터의 중앙에 띄우기
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			this.setLocation((screenSize.width / 2) - 650, (screenSize.height / 2) - 300);

			this.setTitle("병사소집");
			this.setSize(365, 200);

			for (int i = 1; i < land.length; i++)
				landVector.add(land[i].landNumber + "번땅");
			panel = new JPanel();
			label = new JLabel("1번병사 :보　병/공격력 : 2/체력 : 4/이동속도 : 1/가격 : 2G)");
			labe2 = new JLabel("2번병사 :방패병/공격력 : 1/체력 : 5/이동속도 : 1/가격 : 3G)");
			labe3 = new JLabel("3번병사 :기　병/공격력 : 3/체력 : 3/이동속도 : 2/가격 : 4G)");
			labe4 = new JLabel("4번병사 :폭탄병/공격력 : 2/체력 : 1/이동속도 : 1/가격 : 8G)");
			labe4 = new JLabel("5번병사 :공작원/공격력 : 1/체력 : 1/이동속도 : 1/가격 : 10G)");
			button1 = new JButton("병서소환");

			soldierCombo = new JComboBox(soldierName);
			landCombo = new JComboBox(landVector);

			button1.addActionListener(listener1);
			button2.addActionListener(listener1);

			panel.add(label);
			panel.add(labe2);
			panel.add(labe3);
			panel.add(labe4);

			panel.add(soldierCombo);
			panel.add(landCombo);
			panel.add(button1);

			this.add(panel);
			this.setVisible(true);
			System.out.println(soldierCombo.getActionCommand());

		}

		class Listener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button1) { // 병사징집
					int intTemp, targetLand;
					Soldier tempSoldier;
					intTemp = soldierCombo.getSelectedIndex();
					targetLand = landCombo.getSelectedIndex() + 1;

					tempSoldier = player.createSoldier(intTemp);
					tempSoldier.setLandAndFrame(land, mainFrame);
					tempSoldier.targetLandNumber = targetLand;
					Thread tempThread = new Thread(tg1, tempSoldier);
					tempThread.start();
					System.out.println(soldierCombo.getSelectedItem());
					labelGold.setText("골드 :" + player.holdingGold);
				}

			}
		}
	}// end FrameSoldier

	class FrameSkill extends JFrame {

		Vector<String> landVector = new Vector<String>();
		Vector<String> kingVector = new Vector<String>();

		private JButton button1;

		private JPanel panel;
		private JLabel label;
		private JLabel labe2;
		private JLabel labe3;
		private JLabel labe4;

		private Listener listener1 = new Listener();
		private JComboBox kingCombo;
		private JComboBox landCombo;

		public FrameSkill() {

			// 모니터의 중앙에 띄우기
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			this.setLocation((screenSize.width / 2) - 650, (screenSize.height / 2) - 100);

			this.setTitle("왕 스킬 사용하기");
			this.setSize(365, 200);

			for (int i = 0; i < land.length; i++)
				landVector.add(land[i].landNumber + "번땅");

			for (int i = 0; i < aiPlayer.length; i++) {
				if (aiPlayer[i] != null)
					kingVector.add(aiPlayer[i].name + "왕");
			}

			panel = new JPanel();
			label = new JLabel("1번병사 :보　병/공격력 : 2/체력 : 4/이동속도 : 1/가격 : 2G)");
			labe2 = new JLabel("2번병사 :방패병/공격력 : 1/체력 : 5/이동속도 : 1/가격 : 3G)");
			labe3 = new JLabel("3번병사 :기　병/공격력 : 3/체력 : 3/이동속도 : 2/가격 : 4G)");
			labe4 = new JLabel("4번병사 :폭탄병/공격력 : 2/체력 : 1/이동속도 : 1/가격 : 5G)");
			labe4 = new JLabel("5번병사 :공작원/공격력 : 1/체력 : 1/이동속도 : 1/가격 : 10G)");
			button1 = new JButton("스킬사용");

			kingCombo = new JComboBox(kingVector);
			landCombo = new JComboBox(landVector);

			button1.addActionListener(listener1);
			button2.addActionListener(listener1);

			panel.add(label);
			panel.add(labe2);
			panel.add(labe3);
			panel.add(labe4);

//			panel.add(soldierCombo);

			if (player.origin.equals("Thief")) {
				panel.add(landCombo);
				button1.setText("땅 훔치기");
			} else if (player.origin.contentEquals("Merchant")) {
				panel.add(kingCombo);
				button1.setText("무역전쟁");
			} else if (player.origin.equals("Knight")) {
				panel.add(kingCombo);
				button1.setText("일기토");
			}

			panel.add(button1);

			this.add(panel);
			this.setVisible(true);
//			System.out.println(soldierCombo.getActionCommand());

		}

		class Listener implements ActionListener {
			KnightBornKing knight = null;
			MerchantBornKing merchant = null;
			ThiefBornKing thief = null;

			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getSource() == button1) { // 스킬사용
					label.setText("rr");
					if (player.origin.equals("Thief")) {
						thief = (ThiefBornKing) player;
						thief.stealLand(aiPlayer[kingCombo.getSelectedIndex()]);
					} else if (player.origin.contentEquals("Merchant")) {
						merchant = (MerchantBornKing) player;
						merchant.tradeWar(aiPlayer[kingCombo.getSelectedIndex()]);
					} else if (player.origin.equals("Knight")) {
						knight = (KnightBornKing) player;
						knight.fightManToMan(aiPlayer[kingCombo.getSelectedIndex()]);
					}
				}

			}
		}
	}// end FrameSkill
}
