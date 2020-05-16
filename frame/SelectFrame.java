import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectFrame extends JFrame {

	private JPanel contentPane;

	JCheckBox checkBox;
	JCheckBox checkBox_1;
	JCheckBox checkBox_2;
	Ch te;
	public SelectFrame(Ch te) {
		this.te=te;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 559);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("< \uCD9C\uC2E0 \uC120\uD0DD >");
		label.setFont(new Font("µ∏øÚ", Font.PLAIN, 30));
		label.setBounds(185, 27, 188, 35);
		contentPane.add(label);

		JLabel label_1 = new JLabel("");
		label_1.setIcon(
				new ImageIcon("C:\\Users\\ANArKH\\eclipse-workspace_5W\\CaptureWorld_extends_Thread\\thief.png"));
		label_1.setBounds(432, 88, 119, 112);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("");
		label_2.setIcon(
				new ImageIcon("C:\\Users\\ANArKH\\eclipse-workspace_5W\\CaptureWorld_extends_Thread\\merchant.jpeg"));
		label_2.setBounds(241, 88, 107, 112);
		contentPane.add(label_2);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(
				new ImageIcon("C:\\Users\\ANArKH\\eclipse-workspace_5W\\CaptureWorld_extends_Thread\\knight2.jpeg"));
		lblNewLabel.setBounds(30, 88, 112, 111);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("-\uAE30\uC0AC\uCD9C\uC2E0-");
		lblNewLabel_1.setFont(new Font("±º∏≤", Font.BOLD, 12));
		lblNewLabel_1.setBounds(53, 210, 72, 15);
		contentPane.add(lblNewLabel_1);

		JLabel label_3 = new JLabel("-\uC0C1\uC778\uCD9C\uC2E0-");
		label_3.setFont(new Font("±º∏≤", Font.BOLD, 12));
		label_3.setBounds(265, 210, 72, 15);
		contentPane.add(label_3);

		JLabel label_4 = new JLabel("-\uB3C4\uC801\uCD9C\uC2E0-");
		label_4.setFont(new Font("±º∏≤", Font.BOLD, 12));
		label_4.setBounds(452, 210, 72, 15);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("\u203B\uC804\uC6A9\uC2A4\uD15F : \uC624\uB9CC");
		label_5.setBounds(35, 223, 107, 15);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("\u203B\uC804\uC6A9\uC2A4\uD15F : \uD0D0\uC695");
		label_6.setBounds(240, 223, 107, 15);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("\u203B\uC804\uC6A9\uC2A4\uD15F : \uC2DC\uAE30");
		label_7.setBounds(432, 223, 107, 15);
		contentPane.add(label_7);

		JLabel lblNewLabel_2 = new JLabel("[\uD0D0\uC695 3\uB2F9 \uACF5\uACA9\uB825 1\uC99D\uAC00]");
		lblNewLabel_2.setBounds(230, 235, 148, 15);
		contentPane.add(lblNewLabel_2);

		JLabel label_8 = new JLabel("[\uC624\uB9CC 1\uB2F9 \uACF5\uACA9\uB825 1\uC99D\uAC00]");
		label_8.setBounds(19, 235, 148, 15);
		contentPane.add(label_8);

		JLabel label_9 = new JLabel("[\uC2DC\uAE30 2\uB2F9 \uACF5\uACA9\uB825 1\uC99D\uAC00]");
		label_9.setBounds(415, 235, 148, 15);
		contentPane.add(label_9);

		JLabel label_10 = new JLabel("\u203B\uC804\uC6A9\uC2A4\uD0AC : \uC77C\uAE30\uD1A0");
		label_10.setBounds(30, 334, 137, 15);
		contentPane.add(label_10);

		JLabel label_11 = new JLabel("\u203B\uC804\uC6A9\uC2A4\uD15F : \uBB34\uC5ED\uC804\uC7C1");
		label_11.setBounds(230, 334, 138, 15);
		contentPane.add(label_11);

		JLabel label_12 = new JLabel("\u203B\uC804\uC6A9\uC2A4\uD0AC : \uC655\uB144\uC758 \uAE30\uC220");
		label_12.setBounds(415, 334, 148, 15);
		contentPane.add(label_12);

		JLabel lbln = new JLabel("\uC218\uD558\uC758 \uBCD1\uC0AC\uB4E4\uC774 \uC624\uB9CC\uC5D0 ");
		lbln.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		lbln.setBounds(30, 248, 132, 15);
		contentPane.add(lbln);

		JLabel label_13 = new JLabel("\uBE44\uB840\uD55C \uD655\uB960\uB85C \uACF5\uACA9\uB825\uC774");
		label_13.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		label_13.setBounds(30, 260, 128, 15);
		contentPane.add(label_13);

		JLabel label_14 = new JLabel("\uC808\uBC18\uC73C\uB85C \uAC10\uC18C\uD55C\uB2E4.");
		label_14.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		label_14.setBounds(40, 273, 104, 15);
		contentPane.add(label_14);

		JLabel lblNewLabel_3 = new JLabel(
				"\uD0D0\uC695\uC5D0 \uBE44\uB840\uD55C \uB9CC\uD07C\uC758 \uD655\uB960\uB85C");
		lblNewLabel_3.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		lblNewLabel_3.setBounds(220, 247, 144, 14);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\uC784\uC758\uC758 \uB545\uC5D0\uC11C \uAC77\uC5B4\uB4E4\uC774\uB294");
		lblNewLabel_4.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		lblNewLabel_4.setBounds(230, 260, 129, 14);
		contentPane.add(lblNewLabel_4);

		JLabel label_15 = new JLabel("\uC138\uAE08\uC774 \uB450 \uBC30\uB85C \uC99D\uAC00\uD558\uC9C0\uB9CC, ");
		label_15.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		label_15.setBounds(226, 273, 140, 14);
		contentPane.add(label_15);

		JLabel lblNewLabel_5 = new JLabel("\uACF5\uC791\uC6D0\uC5D0\uAC8C \uC120\uB3D9\uB2F9\uD560");
		lblNewLabel_5.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		lblNewLabel_5.setBounds(235, 287, 103, 14);
		contentPane.add(lblNewLabel_5);

		JLabel label_16 = new JLabel("\uD655\uB960 \uB610\uD55C \uC99D\uAC00\uD55C\uB2E4");
		label_16.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		label_16.setBounds(240, 298, 96, 14);
		contentPane.add(label_16);

		JLabel lblNewLabel_6 = new JLabel("\uACF5\uC791\uC6D0\uC774 \uC120\uB3D9\uC5D0 \uC131\uACF5\uD560");
		lblNewLabel_6.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		lblNewLabel_6.setBounds(419, 248, 128, 15);
		contentPane.add(lblNewLabel_6);

		JLabel label_17 = new JLabel("\uD655\uB960\uC774 \uC99D\uAC00\uD558\uACE0, \uC804\uC6A9\uC2A4\uD0AC\uC758");
		label_17.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		label_17.setBounds(415, 260, 143, 14);
		contentPane.add(label_17);

		JLabel label_18 = new JLabel("\uC9C0\uC18D\uC2DC\uAC04\uC774 \uB298\uC5B4\uB09C\uB2E4.");
		label_18.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		label_18.setBounds(429, 273, 184, 15);
		contentPane.add(label_18);

		JLabel label_19 = new JLabel("\uAE08 : 20 \uD544\uC694");
		label_19.setBounds(53, 348, 89, 15);
		contentPane.add(label_19);

		JLabel label_20 = new JLabel("\uAE08 : 20 \uD544\uC694");
		label_20.setBounds(263, 348, 85, 15);
		contentPane.add(label_20);

		JLabel label_21 = new JLabel("\uC0DD\uBA85\uB825 : 5 \uC774\uC0C1 \uD544\uC694");
		label_21.setBounds(432, 348, 119, 15);
		contentPane.add(label_21);

		JLabel lblNewLabel_7 = new JLabel(
				"\uC0C1\uB300\uC655\uBCF4\uB2E4 \uACF5\uACA9\uB825\uC774 \uB192\uC744\uACBD\uC6B0");
		lblNewLabel_7.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		lblNewLabel_7.setBounds(20, 375, 164, 15);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("\uC0C1\uB300\uBC29\uC758 \uCCB4\uB825\uC744 2 \uAE4D\uB294\uB2E4");
		lblNewLabel_8.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		lblNewLabel_8.setBounds(21, 388, 138, 15);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel(
				"\uC2E4\uD328\uC2DC \uC790\uC2E0\uC758 \uCCB4\uB825\uC774 1 \uAE4D\uC778\uB2E4");
		lblNewLabel_9.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		lblNewLabel_9.setBounds(20, 400, 166, 15);
		contentPane.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel(
				"\uC0C1\uB300\uBC29 \uC218\uB3C4\uBCF4\uB2E4 \uBC1C\uC804\uC0C1\uD0DC\uAC00");
		lblNewLabel_10.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblNewLabel_10.setBounds(220, 373, 138, 16);
		contentPane.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel(
				"\uC88B\uC744\uACBD\uC6B0 \uC0C1\uB300\uC218\uB3C4\uC758 \uBC1C\uC804\uC744");
		lblNewLabel_11.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblNewLabel_11.setBounds(220, 386, 138, 16);
		contentPane.add(lblNewLabel_11);

		JLabel label_22 = new JLabel("2\uB9CC\uD07C \uB0AE\uCD98\uB2E4, \uC2E4\uD328\uC2DC \uB098\uC758");
		label_22.setFont(new Font("Dialog", Font.PLAIN, 11));
		label_22.setBounds(220, 397, 128, 16);
		contentPane.add(label_22);

		JLabel lblNewLabel_12 = new JLabel(
				"\uC218\uB3C4\uC758 \uBC1C\uC804\uC7741\uB9CC\uD07C \uB0AE\uC544\uC9C4\uB2E4");
		lblNewLabel_12.setFont(new Font("Dialog", Font.PLAIN, 11));
		lblNewLabel_12.setBounds(220, 411, 144, 16);
		contentPane.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("\uB79C\uB364\uD55C \uC2DC\uAC04\uB3D9\uC548 \uC0C1\uB300\uBC29\uC774");
		lblNewLabel_13.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		lblNewLabel_13.setBounds(421, 373, 140, 15);
		contentPane.add(lblNewLabel_13);

		JLabel lblNewLabel_14 = new JLabel(
				"\uC138\uAE08\uC744 \uAC77\uC9C0 \uBABB\uD558\uAC8C \uBC29\uD574\uD55C\uB2E4");
		lblNewLabel_14.setFont(new Font("±º∏≤", Font.PLAIN, 11));
		lblNewLabel_14.setBounds(417, 387, 176, 15);
		contentPane.add(lblNewLabel_14);

		checkBox = new JCheckBox("\uAE30\uC0AC \uCD9C\uC2E0 \uC655 \uC120\uD0DD");
		checkBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkBox.setSelected(true);
				checkBox_1.setSelected(false);
				checkBox_2.setSelected(false);
			}
		});
		checkBox.setBounds(30, 437, 137, 23);
		contentPane.add(checkBox);

		checkBox_1 = new JCheckBox("\uC0C1\uC778 \uCD9C\uC2E0 \uC655 \uC120\uD0DD");
		checkBox_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkBox.setSelected(false);
				checkBox_1.setSelected(true);
				checkBox_2.setSelected(false);
			}
		});
		checkBox_1.setBounds(231, 437, 132, 23);
		contentPane.add(checkBox_1);

		checkBox_2 = new JCheckBox("\uB3C4\uC801 \uCD9C\uC2E0 \uC655 \uC120\uD0DD");
		checkBox_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				checkBox.setSelected(false);
				checkBox_1.setSelected(false);
				checkBox_2.setSelected(true);
			}
		});
		checkBox_2.setBounds(430, 437, 132, 23);
		contentPane.add(checkBox_2);

		JButton btnNewButton = new JButton("\uCD9C \uC804  \uD558 \uAE30");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if (checkBox.isSelected() && checkBox_1.isSelected()) {
					System.out.println("«œ≥™∏∏ º±≈√«ÿ ¡÷ººø‰");
				} else if (checkBox.isSelected() && checkBox_2.isSelected()) {
					System.out.println("«œ≥™∏∏ º±≈√«ÿ ¡÷ººø‰");
				} else if (checkBox_1.isSelected() && checkBox_2.isSelected()) {
					System.out.println("«œ≥™∏∏ º±≈√«ÿ ¡÷ººø‰");
				} else if (checkBox.isSelected()) {
					System.out.println("±‚ªÁ √‚Ω≈ ø’ º±≈√");
					te.origin="Knight";
					te.i=2;
				} else if (checkBox_1.isSelected()) {
					System.out.println("ªÛ¿Œ √‚Ω≈ ø’ º±≈√");
					te.origin="Merchant";
					te.i=2;
				} else if (checkBox_2.isSelected()) {
					System.out.println("µµ¿˚ √‚Ω≈ ø’ º±≈√");
					te.origin="Thief";
					te.i=2;
				}
			}
		});
		btnNewButton.setFont(new Font("±º∏≤", Font.PLAIN, 15));
		btnNewButton.setBounds(209, 479, 165, 31);
		contentPane.add(btnNewButton);
		this.setVisible(true);
	}
}
