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

		// ������� �߾ӿ� ����
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		this.setLocation((screenSize.width / 2) - 300, (screenSize.height / 2) - 300);

		// ȭ�鼼��
		this.setTitle("�����Ա�");
		this.setSize(600, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panel = new JPanel();
		labelTime = new JLabel("�����ð� : " + "0");
		labelGold = new JLabel("��� :" + player.holdingGold);
		labelGoldAi = new JLabel("��� :" + aiPlayer[0].holdingGold);
		button1 = new JButton("����¡��");
		button2 = new JButton("�����Ʒ�");
		button3 = new JButton("�ձ�����");
		button4 = new JButton("��ų����ϱ�");
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

	public void labelTime(String text) { // ���� �����ð�
		this.labelTime.setText(text);
	}

	public void labelLandColorChange(int landNum, Color color) { // �� �ٲٱ�.
		this.labelLand[landNum].setBackground(color);
//		this.fb[landNum].setLandColor(color);

	}

	class Listener implements ActionListener {
		FrameSoldier fs;
		FrameSkill fskill;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == button1) { // ����¡��
				FrameSoldier frameSoldier = new FrameSoldier();
				fs = frameSoldier;
			}
			if (e.getSource() == button2) {
				player.kingTraining();
				labelGold.setText("��� :" + player.holdingGold);
			}
			if (e.getSource() == button3) {
				player.kingdomUpgrade();
				labelGold.setText("��� :" + player.holdingGold);
			}
			if (e.getSource() == button4) {
				FrameSkill frameSkill = new FrameSkill();
				fskill = frameSkill;
				labelGold.setText("��� :" + player.holdingGold);
			}
			if (e.getSource() == button5) { // ����¡��
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

		String[] soldierName = { "����1", "����2", "����3", "����4", "����5" };
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

			// ������� �߾ӿ� ����
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			this.setLocation((screenSize.width / 2) - 650, (screenSize.height / 2) - 300);

			this.setTitle("�������");
			this.setSize(365, 200);

			for (int i = 0; i < land.length; i++)
				landVector.add(land[i].landNumber + "����");
			panel = new JPanel();
			labe1 = new JLabel("");
			labe2 = new JLabel("");
			labe3 = new JLabel("");
			labe4 = new JLabel("");
			labe4 = new JLabel("");
			button1 = new JButton("����Ȯ��");

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

				if (e.getSource() == button1) { // ����¡��
					int intTemp, targetLand;
					Block tempBlock;
//					Soldier tempSoldier;
//					intTemp = soldierCombo.getSelectedIndex();
					targetLand = landCombo.getSelectedIndex();

					tempBlock = player.nationalMap.findBlock(targetLand);

					if (tempBlock.preBlock0 != null) {
						labe1.setText("\""+tempBlock.preBlock0.land.landNumber + "�� �����\\                      ");
					} else {
						labe1.setText("������Ʈ\\                      ");
					}
					
					if (tempBlock.preBlock1 != null) {
						labe2.setText(tempBlock.preBlock1.land.landNumber + "�� �����\\                      ");
					} else {
						labe2.setText("������Ʈ\\                      ");
					}
					
					if (tempBlock.nextBlock0 != null) {
						labe3.setText(tempBlock.nextBlock0.land.landNumber + "�� �����\\                      ");
					} else {
						labe3.setText("������Ʈ\\                      ");
					}
					if (tempBlock.nextBlock1 != null) {
						labe4.setText(tempBlock.nextBlock1.land.landNumber + "�� �����\\                      ");
					} else {
						labe4.setText("������Ʈ\\                      ");
					}

				}

			}
		}
	}// end FrameSoldier

	class FrameSoldier extends JFrame {

		String[] soldierName = { "����1", "����2", "����3", "����4", "����5" };
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

			// ������� �߾ӿ� ����
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			this.setLocation((screenSize.width / 2) - 650, (screenSize.height / 2) - 300);

			this.setTitle("�������");
			this.setSize(365, 200);

			for (int i = 1; i < land.length; i++)
				landVector.add(land[i].landNumber + "����");
			panel = new JPanel();
			label = new JLabel("1������ :������/���ݷ� : 2/ü�� : 4/�̵��ӵ� : 1/���� : 2G)");
			labe2 = new JLabel("2������ :���к�/���ݷ� : 1/ü�� : 5/�̵��ӵ� : 1/���� : 3G)");
			labe3 = new JLabel("3������ :�⡡��/���ݷ� : 3/ü�� : 3/�̵��ӵ� : 2/���� : 4G)");
			labe4 = new JLabel("4������ :��ź��/���ݷ� : 2/ü�� : 1/�̵��ӵ� : 1/���� : 8G)");
			labe4 = new JLabel("5������ :���ۿ�/���ݷ� : 1/ü�� : 1/�̵��ӵ� : 1/���� : 10G)");
			button1 = new JButton("������ȯ");

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

				if (e.getSource() == button1) { // ����¡��
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
					labelGold.setText("��� :" + player.holdingGold);
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

			// ������� �߾ӿ� ����
			Toolkit kit = Toolkit.getDefaultToolkit();
			Dimension screenSize = kit.getScreenSize();
			this.setLocation((screenSize.width / 2) - 650, (screenSize.height / 2) - 100);

			this.setTitle("�� ��ų ����ϱ�");
			this.setSize(365, 200);

			for (int i = 0; i < land.length; i++)
				landVector.add(land[i].landNumber + "����");

			for (int i = 0; i < aiPlayer.length; i++) {
				if (aiPlayer[i] != null)
					kingVector.add(aiPlayer[i].name + "��");
			}

			panel = new JPanel();
			label = new JLabel("1������ :������/���ݷ� : 2/ü�� : 4/�̵��ӵ� : 1/���� : 2G)");
			labe2 = new JLabel("2������ :���к�/���ݷ� : 1/ü�� : 5/�̵��ӵ� : 1/���� : 3G)");
			labe3 = new JLabel("3������ :�⡡��/���ݷ� : 3/ü�� : 3/�̵��ӵ� : 2/���� : 4G)");
			labe4 = new JLabel("4������ :��ź��/���ݷ� : 2/ü�� : 1/�̵��ӵ� : 1/���� : 5G)");
			labe4 = new JLabel("5������ :���ۿ�/���ݷ� : 1/ü�� : 1/�̵��ӵ� : 1/���� : 10G)");
			button1 = new JButton("��ų���");

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
				button1.setText("�� ��ġ��");
			} else if (player.origin.contentEquals("Merchant")) {
				panel.add(kingCombo);
				button1.setText("��������");
			} else if (player.origin.equals("Knight")) {
				panel.add(kingCombo);
				button1.setText("�ϱ���");
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

				if (e.getSource() == button1) { // ��ų���
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
