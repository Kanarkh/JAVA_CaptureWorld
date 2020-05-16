import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.SwingConstants;

//import FrameSkill.Listener;

public class GameFrame_1 extends JFrame {
	private int mapSize;
	FrameBlock[] fb; // 0 ~ 24;
	private JPanel contentPane;
	String[] soldierName = { "º¸  º´", "¹æÆÐº´", "±â  º´", "ÆøÅºº´", "°øÀÛ¿ø" };
	JComboBox comboBox;

	ExplainFrame explainFrame = null;

	final String pressCk = "v";

	// game
	King player;
	King aiPlayer;
	Land[] land;
	ThreadGroup tg1;
	String loginId;
	String idName;
	String win, lose;
	Ch tee;
	private JLabel label_246;
	private JLabel label_247;
	private JLabel label_253;
	private JLabel label_250;
	private JLabel label_251;
	private JLabel label_254;

	private JTextField txtName;
	private JTextField txtBouts;
	private JTextField txtWins;
	private JTextField textLose;
	private JTextField textField_1;
	private JTextField textField_2;

	LinkedList<String> readList = new LinkedList<String>();
	private JTextField textField;
	private JTextField textField_3;

	public void initGameGui(King king, King aiPlayer, Land[] land, ThreadGroup tg1) {
		this.player = king;
		this.aiPlayer = aiPlayer;
		this.land = land;
		this.tg1 = tg1;
	}

	public GameFrame_1(FrameBlock[] fb, Ch te) {
		this.tee = te;
		this.mapSize = 5;
		this.fb = fb;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1164, 912);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

//		jlp.add(contentPane,new Integer(0));

//		contentPaneUnder = new JPanel(new FlowLayout());
//		contentPaneUnder.setBorder(new EmptyBorder(5, 5, 5, 5));
////		setContentPane(contentPaneUnder);
//		contentPaneUnder.setLayout(null);

		// idÁ¤º¸

		//

		JLabel lblLandNum = new JLabel("¶¥ÁÖÀÎ : ");
		lblLandNum.setBounds(12, 39, 150, 15);
		contentPane.add(lblLandNum);

		JLabel lblNewLabel = new JLabel("\uC9C0   \uD615 : @");
		lblNewLabel.setBounds(12, 72, 150, 15);
		contentPane.add(lblNewLabel);

		JLabel label = new JLabel("\uBCF4\uAE09\uB85C : #");
		label.setBounds(12, 88, 150, 15);
		contentPane.add(label);

		JLabel lblNewLabel_1 = new JLabel("\uAC74   \uBB3C :  $");
		lblNewLabel_1.setBounds(12, 104, 150, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("\uC138   \uAE08 : !");
		lblNewLabel_2.setBounds(12, 55, 150, 15);
		contentPane.add(lblNewLabel_2);

		JLabel label_1 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218: %");
		label_1.setBounds(12, 137, 150, 15);
		contentPane.add(label_1);

		JLabel label_2 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:^");
		label_2.setBounds(12, 157, 150, 15);
		contentPane.add(label_2);

		JLabel label_3 = new JLabel("\uB545\uBC88\uD638 : 0");
		label_3.setBounds(12, 21, 150, 15);
		contentPane.add(label_3);

		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setBounds(134, 10, 60, 60);
		lblNewLabel_3.setOpaque(true);
		contentPane.add(lblNewLabel_3);

		if (this.mapSize == 5) {
			this.fb[0].landNum = label_3;
			this.fb[0].landOwner = lblLandNum;
			this.fb[0].landGold = lblNewLabel_2;
			this.fb[0].landTopography = lblNewLabel;
			this.fb[0].supply = label;
			this.fb[0].building = lblNewLabel_1;
			this.fb[0].defensive = label_1;
			this.fb[0].offensive = label_2;
			this.fb[0].landColor = lblNewLabel_3;
		}
		///////////////////////////////

		JLabel label_4 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:^");
		label_4.setBounds(206, 157, 150, 15);
		contentPane.add(label_4);

		JLabel label_5 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:%");
		label_5.setBounds(206, 137, 150, 15);
		contentPane.add(label_5);

		JLabel label_6 = new JLabel("\uAC74   \uBB3C : $");
		label_6.setBounds(206, 104, 150, 15);
		contentPane.add(label_6);

		JLabel label_7 = new JLabel("\uBCF4\uAE09\uB85C : #");
		label_7.setBounds(206, 88, 150, 15);
		contentPane.add(label_7);

		JLabel label_8 = new JLabel("\uC9C0   \uD615 : @");
		label_8.setBounds(206, 72, 150, 15);
		contentPane.add(label_8);

		JLabel label_9 = new JLabel("\uC138   \uAE08 : !");
		label_9.setBounds(206, 55, 150, 15);
		contentPane.add(label_9);

		JLabel label_10 = new JLabel("¶¥ÁÖÀÎ : ");
		label_10.setBounds(206, 39, 150, 15);
		contentPane.add(label_10);

		JLabel label_11 = new JLabel("¶¥¹øÈ£ : 1");
		label_11.setBounds(206, 21, 150, 15);
		contentPane.add(label_11);

		JLabel label_12 = new JLabel("");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_12.setOpaque(true);
		label_12.setBackground(Color.WHITE);
		label_12.setBounds(325, 10, 60, 60);
		contentPane.add(label_12);

		if (this.mapSize == 5) {
			this.fb[1].landNum = label_11;
			this.fb[1].landOwner = label_10;
			this.fb[1].landGold = label_9;
			this.fb[1].landTopography = label_8;
			this.fb[1].supply = label_7;
			this.fb[1].building = label_6;
			this.fb[1].defensive = label_5;
			this.fb[1].offensive = label_4;
			this.fb[1].landColor = label_12;
		}
		///////////////////////////////

		JLabel label_13 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_13.setBounds(400, 157, 150, 15);
		contentPane.add(label_13);

		JLabel label_14 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_14.setBounds(400, 137, 150, 15);
		contentPane.add(label_14);

		JLabel label_15 = new JLabel("\uAC74   \uBB3C : ");
		label_15.setBounds(400, 104, 150, 15);
		contentPane.add(label_15);

		JLabel label_16 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_16.setBounds(400, 88, 150, 15);
		contentPane.add(label_16);

		JLabel label_17 = new JLabel("\uC9C0   \uD615 :");
		label_17.setBounds(400, 72, 150, 15);
		contentPane.add(label_17);

		JLabel label_18 = new JLabel("\uC138   \uAE08 :");
		label_18.setBounds(400, 55, 150, 15);
		contentPane.add(label_18);

		JLabel label_19 = new JLabel("¶¥ÁÖÀÎ : ");
		label_19.setBounds(400, 39, 150, 15);
		contentPane.add(label_19);

		JLabel label_20 = new JLabel("¶¥¹øÈ£ : 2");
		label_20.setBounds(400, 21, 150, 15);
		contentPane.add(label_20);

		JLabel label_21 = new JLabel("");
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_21.setOpaque(true);
		label_21.setBackground(Color.WHITE);
		label_21.setBounds(519, 10, 60, 60);
		contentPane.add(label_21);

		if (this.mapSize == 5) {
			this.fb[2].landNum = label_20;
			this.fb[2].landOwner = label_19;
			this.fb[2].landGold = label_18;
			this.fb[2].landTopography = label_17;
			this.fb[2].supply = label_16;
			this.fb[2].building = label_15;
			this.fb[2].defensive = label_14;
			this.fb[2].offensive = label_13;
			this.fb[2].landColor = label_21;
		}
		///////////////////////////////

		JLabel label_22 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_22.setBounds(594, 157, 150, 15);
		contentPane.add(label_22);

		JLabel label_23 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_23.setBounds(594, 137, 150, 15);
		contentPane.add(label_23);

		JLabel label_24 = new JLabel("\uAC74   \uBB3C : ");
		label_24.setBounds(594, 104, 150, 15);
		contentPane.add(label_24);

		JLabel label_25 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_25.setBounds(594, 88, 150, 15);
		contentPane.add(label_25);

		JLabel label_26 = new JLabel("\uC9C0   \uD615 :");
		label_26.setBounds(594, 72, 150, 15);
		contentPane.add(label_26);

		JLabel label_27 = new JLabel("\uC138   \uAE08 :");
		label_27.setBounds(594, 55, 150, 15);
		contentPane.add(label_27);

		JLabel label_28 = new JLabel("¶¥ÁÖÀÎ : ");
		label_28.setBounds(594, 39, 150, 15);
		contentPane.add(label_28);

		JLabel label_29 = new JLabel("¶¥¹øÈ£ : 3");
		label_29.setBounds(594, 21, 150, 15);
		contentPane.add(label_29);

		JLabel label_30 = new JLabel("");
		label_30.setHorizontalAlignment(SwingConstants.CENTER);
		label_30.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_30.setOpaque(true);
		label_30.setBackground(Color.WHITE);
		label_30.setBounds(713, 10, 60, 60);
		contentPane.add(label_30);

		if (this.mapSize == 5) {
			this.fb[3].landNum = label_29;
			this.fb[3].landOwner = label_28;
			this.fb[3].landGold = label_27;
			this.fb[3].landTopography = label_26;
			this.fb[3].supply = label_25;
			this.fb[3].building = label_24;
			this.fb[3].defensive = label_23;
			this.fb[3].offensive = label_22;
			this.fb[3].landColor = label_30;
		}
		///////////////////////////////

		JLabel label_31 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_31.setBounds(788, 157, 150, 15);
		contentPane.add(label_31);

		JLabel label_32 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_32.setBounds(788, 137, 150, 15);
		contentPane.add(label_32);

		JLabel label_33 = new JLabel("\uAC74   \uBB3C : ");
		label_33.setBounds(788, 104, 150, 15);
		contentPane.add(label_33);

		JLabel label_34 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_34.setBounds(788, 88, 150, 15);
		contentPane.add(label_34);

		JLabel label_35 = new JLabel("\uC9C0   \uD615 :");
		label_35.setBounds(788, 72, 150, 15);
		contentPane.add(label_35);

		JLabel label_36 = new JLabel("\uC138   \uAE08 :");
		label_36.setBounds(788, 55, 150, 15);
		contentPane.add(label_36);

		JLabel label_37 = new JLabel("¶¥ÁÖÀÎ : ");
		label_37.setBounds(788, 39, 150, 15);
		contentPane.add(label_37);

		JLabel label_38 = new JLabel("¶¥¹øÈ£ : 4");
		label_38.setBounds(788, 21, 150, 15);
		contentPane.add(label_38);

		JLabel label_39 = new JLabel("");
		label_39.setHorizontalAlignment(SwingConstants.CENTER);
		label_39.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_39.setOpaque(true);
		label_39.setBackground(Color.WHITE);
		label_39.setBounds(907, 10, 60, 60);
		contentPane.add(label_39);

		if (this.mapSize == 5) {
			this.fb[4].landNum = label_38;
			this.fb[4].landOwner = label_37;
			this.fb[4].landGold = label_36;
			this.fb[4].landTopography = label_35;
			this.fb[4].supply = label_34;
			this.fb[4].building = label_33;
			this.fb[4].defensive = label_32;
			this.fb[4].offensive = label_31;
			this.fb[4].landColor = label_39;
		}
		///////////////////////////////

		JLabel label_40 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_40.setBounds(9, 329, 150, 15);
		contentPane.add(label_40);

		JLabel label_41 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_41.setBounds(9, 309, 150, 15);
		contentPane.add(label_41);

		JLabel label_42 = new JLabel("\uAC74   \uBB3C : ");
		label_42.setBounds(12, 276, 150, 15);
		contentPane.add(label_42);

		JLabel label_43 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_43.setBounds(12, 260, 150, 15);
		contentPane.add(label_43);

		JLabel label_44 = new JLabel("\uC9C0   \uD615 :");
		label_44.setBounds(12, 244, 150, 15);
		contentPane.add(label_44);

		JLabel label_45 = new JLabel("\uC138   \uAE08 :");
		label_45.setBounds(12, 227, 150, 15);
		contentPane.add(label_45);

		JLabel label_46 = new JLabel("¶¥ÁÖÀÎ : ");
		label_46.setBounds(12, 211, 150, 15);
		contentPane.add(label_46);

		JLabel label_47 = new JLabel("¶¥¹øÈ£ : 5");
		label_47.setBounds(12, 193, 150, 15);
		contentPane.add(label_47);

		JLabel label_48 = new JLabel("");
		label_48.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_48.setHorizontalAlignment(SwingConstants.CENTER);
		label_48.setOpaque(true);
		label_48.setBackground(Color.WHITE);
		label_48.setBounds(131, 182, 60, 60);
		contentPane.add(label_48);

		if (this.mapSize == 5) {
			this.fb[5].landNum = label_47;
			this.fb[5].landOwner = label_46;
			this.fb[5].landGold = label_45;
			this.fb[5].landTopography = label_44;
			this.fb[5].supply = label_43;
			this.fb[5].building = label_42;
			this.fb[5].defensive = label_41;
			this.fb[5].offensive = label_40;
			this.fb[5].landColor = label_48;
		}
		///////////////////////////////

		JLabel label_49 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_49.setBounds(206, 329, 150, 15);
		contentPane.add(label_49);

		JLabel label_50 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_50.setBounds(206, 309, 150, 15);
		contentPane.add(label_50);

		JLabel label_51 = new JLabel("\uAC74   \uBB3C : ");
		label_51.setBounds(209, 276, 150, 15);
		contentPane.add(label_51);

		JLabel label_52 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_52.setBounds(209, 260, 150, 15);
		contentPane.add(label_52);

		JLabel label_53 = new JLabel("\uC9C0   \uD615 :");
		label_53.setBounds(209, 244, 150, 15);
		contentPane.add(label_53);

		JLabel label_54 = new JLabel("\uC138   \uAE08 :");
		label_54.setBounds(209, 227, 150, 15);
		contentPane.add(label_54);

		JLabel label_55 = new JLabel("¶¥ÁÖÀÎ : ");
		label_55.setBounds(209, 211, 150, 15);
		contentPane.add(label_55);

		JLabel label_56 = new JLabel("¶¥¹øÈ£ : 6");
		label_56.setBounds(209, 193, 150, 15);
		contentPane.add(label_56);

		JLabel label_57 = new JLabel("");
		label_57.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_57.setHorizontalAlignment(SwingConstants.CENTER);
		label_57.setOpaque(true);
		label_57.setBackground(Color.WHITE);
		label_57.setBounds(325, 182, 60, 60);
		contentPane.add(label_57);

		if (this.mapSize == 5) {
			this.fb[6].landNum = label_56;
			this.fb[6].landOwner = label_55;
			this.fb[6].landGold = label_54;
			this.fb[6].landTopography = label_53;
			this.fb[6].supply = label_52;
			this.fb[6].building = label_51;
			this.fb[6].defensive = label_50;
			this.fb[6].offensive = label_49;
			this.fb[6].landColor = label_57;
		}
		///////////////////////////////

		JLabel label_58 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_58.setBounds(400, 329, 150, 15);
		contentPane.add(label_58);

		JLabel label_59 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_59.setBounds(400, 309, 150, 15);
		contentPane.add(label_59);

		JLabel label_60 = new JLabel("\uAC74   \uBB3C : ");
		label_60.setBounds(403, 276, 150, 15);
		contentPane.add(label_60);

		JLabel label_61 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_61.setBounds(403, 260, 150, 15);
		contentPane.add(label_61);

		JLabel label_62 = new JLabel("\uC9C0   \uD615 :");
		label_62.setBounds(403, 244, 150, 15);
		contentPane.add(label_62);

		JLabel label_63 = new JLabel("\uC138   \uAE08 :");
		label_63.setBounds(403, 227, 150, 15);
		contentPane.add(label_63);

		JLabel label_64 = new JLabel("¶¥ÁÖÀÎ : ");
		label_64.setBounds(403, 211, 150, 15);
		contentPane.add(label_64);

		JLabel label_65 = new JLabel("¶¥¹øÈ£ : 7");
		label_65.setBounds(403, 193, 150, 15);
		contentPane.add(label_65);

		JLabel label_66 = new JLabel("");
		label_66.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_66.setHorizontalAlignment(SwingConstants.CENTER);
		label_66.setOpaque(true);
		label_66.setBackground(Color.WHITE);
		label_66.setBounds(522, 182, 60, 60);
		contentPane.add(label_66);

		if (this.mapSize == 5) {
			this.fb[7].landNum = label_65;
			this.fb[7].landOwner = label_64;
			this.fb[7].landGold = label_63;
			this.fb[7].landTopography = label_62;
			this.fb[7].supply = label_61;
			this.fb[7].building = label_60;
			this.fb[7].defensive = label_59;
			this.fb[7].offensive = label_58;
			this.fb[7].landColor = label_66;
		}
		///////////////////////////////

		JLabel label_67 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_67.setBounds(594, 329, 150, 15);
		contentPane.add(label_67);

		JLabel label_68 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_68.setBounds(594, 309, 150, 15);
		contentPane.add(label_68);

		JLabel label_69 = new JLabel("\uAC74   \uBB3C : ");
		label_69.setBounds(597, 276, 150, 15);
		contentPane.add(label_69);

		JLabel label_70 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_70.setBounds(597, 260, 150, 15);
		contentPane.add(label_70);

		JLabel label_71 = new JLabel("\uC9C0   \uD615 :");
		label_71.setBounds(597, 244, 150, 15);
		contentPane.add(label_71);

		JLabel label_72 = new JLabel("\uC138   \uAE08 :");
		label_72.setBounds(597, 227, 150, 15);
		contentPane.add(label_72);

		JLabel label_73 = new JLabel("¶¥ÁÖÀÎ : ");
		label_73.setBounds(597, 211, 150, 15);
		contentPane.add(label_73);

		JLabel label_74 = new JLabel("¶¥¹øÈ£ : 8");
		label_74.setBounds(597, 193, 150, 15);
		contentPane.add(label_74);

		JLabel label_75 = new JLabel("");
		label_75.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_75.setHorizontalAlignment(SwingConstants.CENTER);
		label_75.setOpaque(true);
		label_75.setBackground(Color.WHITE);
		label_75.setBounds(716, 182, 60, 60);
		contentPane.add(label_75);

		if (this.mapSize == 5) {
			this.fb[8].landNum = label_74;
			this.fb[8].landOwner = label_73;
			this.fb[8].landGold = label_72;
			this.fb[8].landTopography = label_71;
			this.fb[8].supply = label_70;
			this.fb[8].building = label_69;
			this.fb[8].defensive = label_68;
			this.fb[8].offensive = label_67;
			this.fb[8].landColor = label_75;
		}
		///////////////////////////////

		JLabel label_76 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_76.setBounds(788, 329, 150, 15);
		contentPane.add(label_76);

		JLabel label_77 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_77.setBounds(788, 309, 150, 15);
		contentPane.add(label_77);

		JLabel label_78 = new JLabel("\uAC74   \uBB3C : ");
		label_78.setBounds(791, 276, 150, 15);
		contentPane.add(label_78);

		JLabel label_79 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_79.setBounds(791, 260, 150, 15);
		contentPane.add(label_79);

		JLabel label_80 = new JLabel("\uC9C0   \uD615 :");
		label_80.setBounds(791, 244, 150, 15);
		contentPane.add(label_80);

		JLabel label_81 = new JLabel("\uC138   \uAE08 :");
		label_81.setBounds(791, 227, 150, 15);
		contentPane.add(label_81);

		JLabel label_82 = new JLabel("¶¥ÁÖÀÎ : ");
		label_82.setBounds(791, 211, 150, 15);
		contentPane.add(label_82);

		JLabel label_83 = new JLabel("¶¥¹øÈ£ : 9");
		label_83.setBounds(791, 193, 150, 15);
		contentPane.add(label_83);

		JLabel label_84 = new JLabel("");
		label_84.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_84.setHorizontalAlignment(SwingConstants.CENTER);
		label_84.setOpaque(true);
		label_84.setBackground(Color.WHITE);
		label_84.setBounds(910, 182, 60, 60);
		contentPane.add(label_84);

		if (this.mapSize == 5) {
			this.fb[9].landNum = label_83;
			this.fb[9].landOwner = label_82;
			this.fb[9].landGold = label_81;
			this.fb[9].landTopography = label_80;
			this.fb[9].supply = label_79;
			this.fb[9].building = label_78;
			this.fb[9].defensive = label_77;
			this.fb[9].offensive = label_76;
			this.fb[9].landColor = label_84;
		}
		///////////////////////////////

		JLabel label_85 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_85.setBounds(12, 501, 150, 15);
		contentPane.add(label_85);

		JLabel label_86 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_86.setBounds(12, 481, 150, 15);
		contentPane.add(label_86);

		JLabel label_87 = new JLabel("\uAC74   \uBB3C : ");
		label_87.setBounds(15, 448, 150, 15);
		contentPane.add(label_87);

		JLabel label_88 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_88.setBounds(15, 432, 150, 15);
		contentPane.add(label_88);

		JLabel label_89 = new JLabel("\uC9C0   \uD615 :");
		label_89.setBounds(15, 416, 150, 15);
		contentPane.add(label_89);

		JLabel label_90 = new JLabel("\uC138   \uAE08 :");
		label_90.setBounds(15, 399, 150, 15);
		contentPane.add(label_90);

		JLabel label_91 = new JLabel("¶¥ÁÖÀÎ : ");
		label_91.setBounds(15, 383, 150, 15);
		contentPane.add(label_91);

		JLabel label_92 = new JLabel("¶¥¹øÈ£ : 10");
		label_92.setBounds(15, 365, 150, 15);
		contentPane.add(label_92);

		JLabel label_93 = new JLabel("");
		label_93.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_93.setHorizontalAlignment(SwingConstants.CENTER);
		label_93.setOpaque(true);
		label_93.setBackground(Color.WHITE);
		label_93.setBounds(131, 354, 60, 60);
		contentPane.add(label_93);

		if (this.mapSize == 5) {
			this.fb[10].landColor = label_93;
			this.fb[10].landNum = label_92;
			this.fb[10].landOwner = label_91;
			this.fb[10].landGold = label_90;
			this.fb[10].landTopography = label_89;
			this.fb[10].supply = label_88;
			this.fb[10].building = label_87;
			this.fb[10].defensive = label_86;
			this.fb[10].offensive = label_85;
		}
		///////////////////////////////

		JLabel label_94 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_94.setBounds(206, 501, 150, 15);
		contentPane.add(label_94);

		JLabel label_95 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_95.setBounds(206, 481, 150, 15);
		contentPane.add(label_95);

		JLabel label_96 = new JLabel("\uAC74   \uBB3C : ");
		label_96.setBounds(209, 448, 150, 15);
		contentPane.add(label_96);

		JLabel label_97 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_97.setBounds(209, 432, 150, 15);
		contentPane.add(label_97);

		JLabel label_98 = new JLabel("\uC9C0   \uD615 :");
		label_98.setBounds(209, 416, 150, 15);
		contentPane.add(label_98);

		JLabel label_99 = new JLabel("\uC138   \uAE08 :");
		label_99.setBounds(209, 399, 150, 15);
		contentPane.add(label_99);

		JLabel label_100 = new JLabel("¶¥ÁÖÀÎ : ");
		label_100.setBounds(209, 383, 150, 15);
		contentPane.add(label_100);

		JLabel label_101 = new JLabel("¶¥¹øÈ£ : 11");
		label_101.setBounds(209, 365, 150, 15);
		contentPane.add(label_101);

		JLabel label_102 = new JLabel("");
		label_102.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_102.setHorizontalAlignment(SwingConstants.CENTER);
		label_102.setOpaque(true);
		label_102.setBackground(Color.WHITE);
		label_102.setBounds(328, 354, 60, 60);
		contentPane.add(label_102);

		if (this.mapSize == 5) {
			this.fb[11].landColor = label_102;
			this.fb[11].landNum = label_101;
			this.fb[11].landOwner = label_100;
			this.fb[11].landGold = label_99;
			this.fb[11].landTopography = label_98;
			this.fb[11].supply = label_97;
			this.fb[11].building = label_96;
			this.fb[11].defensive = label_95;
			this.fb[11].offensive = label_94;
		}
		///////////////////////////////

		JLabel label_103 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_103.setBounds(400, 501, 150, 15);
		contentPane.add(label_103);

		JLabel label_104 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_104.setBounds(400, 481, 150, 15);
		contentPane.add(label_104);

		JLabel label_105 = new JLabel("\uAC74   \uBB3C : ");
		label_105.setBounds(403, 448, 150, 15);
		contentPane.add(label_105);

		JLabel label_106 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_106.setBounds(403, 432, 150, 15);
		contentPane.add(label_106);

		JLabel label_107 = new JLabel("\uC9C0   \uD615 :");
		label_107.setBounds(403, 416, 150, 15);
		contentPane.add(label_107);

		JLabel label_108 = new JLabel("\uC138   \uAE08 :");
		label_108.setBounds(403, 399, 150, 15);
		contentPane.add(label_108);

		JLabel label_109 = new JLabel("¶¥ÁÖÀÎ : ");
		label_109.setBounds(403, 383, 150, 15);
		contentPane.add(label_109);

		JLabel label_110 = new JLabel("¶¥¹øÈ£ : 12");
		label_110.setBounds(403, 365, 150, 15);
		contentPane.add(label_110);

		JLabel label_111 = new JLabel("");
		label_111.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_111.setHorizontalAlignment(SwingConstants.CENTER);
		label_111.setOpaque(true);
		label_111.setBackground(Color.WHITE);
		label_111.setBounds(522, 354, 60, 60);
		contentPane.add(label_111);

		if (this.mapSize == 5) {
			this.fb[12].landColor = label_111;
			this.fb[12].landNum = label_110;
			this.fb[12].landOwner = label_109;
			this.fb[12].landGold = label_108;
			this.fb[12].landTopography = label_107;
			this.fb[12].supply = label_106;
			this.fb[12].building = label_105;
			this.fb[12].defensive = label_104;
			this.fb[12].offensive = label_103;
		}
		///////////////////////////////

		JLabel label_112 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_112.setBounds(594, 501, 150, 15);
		contentPane.add(label_112);

		JLabel label_113 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_113.setBounds(594, 481, 150, 15);
		contentPane.add(label_113);

		JLabel label_114 = new JLabel("\uAC74   \uBB3C : ");
		label_114.setBounds(597, 448, 150, 15);
		contentPane.add(label_114);

		JLabel label_115 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_115.setBounds(597, 432, 150, 15);
		contentPane.add(label_115);

		JLabel label_116 = new JLabel("\uC9C0   \uD615 :");
		label_116.setBounds(597, 416, 150, 15);
		contentPane.add(label_116);

		JLabel label_117 = new JLabel("\uC138   \uAE08 :");
		label_117.setBounds(597, 399, 150, 15);
		contentPane.add(label_117);

		JLabel label_118 = new JLabel("¶¥ÁÖÀÎ : ");
		label_118.setBounds(597, 383, 150, 15);
		contentPane.add(label_118);

		JLabel label_119 = new JLabel("¶¥¹øÈ£ : 13");
		label_119.setBounds(597, 365, 150, 15);
		contentPane.add(label_119);

		JLabel label_120 = new JLabel("");
		label_120.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_120.setHorizontalAlignment(SwingConstants.CENTER);
		label_120.setOpaque(true);
		label_120.setBackground(Color.WHITE);
		label_120.setBounds(716, 354, 60, 60);
		contentPane.add(label_120);

		if (this.mapSize == 5) {
			this.fb[13].landColor = label_120;
			this.fb[13].landNum = label_119;
			this.fb[13].landOwner = label_118;
			this.fb[13].landGold = label_117;
			this.fb[13].landTopography = label_116;
			this.fb[13].supply = label_115;
			this.fb[13].building = label_114;
			this.fb[13].defensive = label_113;
			this.fb[13].offensive = label_112;
		}
		///////////////////////////////

		JLabel label_121 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_121.setBounds(788, 501, 150, 15);
		contentPane.add(label_121);

		JLabel label_122 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_122.setBounds(788, 481, 150, 15);
		contentPane.add(label_122);

		JLabel label_123 = new JLabel("\uAC74   \uBB3C : ");
		label_123.setBounds(791, 448, 150, 15);
		contentPane.add(label_123);

		JLabel label_124 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_124.setBounds(791, 432, 150, 15);
		contentPane.add(label_124);

		JLabel label_125 = new JLabel("\uC9C0   \uD615 :");
		label_125.setBounds(791, 416, 150, 15);
		contentPane.add(label_125);

		JLabel label_126 = new JLabel("\uC138   \uAE08 :");
		label_126.setBounds(791, 399, 150, 15);
		contentPane.add(label_126);

		JLabel label_127 = new JLabel("¶¥ÁÖÀÎ : ");
		label_127.setBounds(791, 383, 150, 15);
		contentPane.add(label_127);

		JLabel label_128 = new JLabel("¶¥¹øÈ£ : 14");
		label_128.setBounds(791, 365, 150, 15);
		contentPane.add(label_128);

		JLabel label_129 = new JLabel("");
		label_129.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_129.setHorizontalAlignment(SwingConstants.CENTER);
		label_129.setOpaque(true);
		label_129.setBackground(Color.WHITE);
		label_129.setBounds(910, 354, 60, 60);
		contentPane.add(label_129);

		if (this.mapSize == 5) {
			this.fb[14].landColor = label_129;
			this.fb[14].landNum = label_128;
			this.fb[14].landOwner = label_127;
			this.fb[14].landGold = label_126;
			this.fb[14].landTopography = label_125;
			this.fb[14].supply = label_124;
			this.fb[14].building = label_123;
			this.fb[14].defensive = label_122;
			this.fb[14].offensive = label_121;
		}
		///////////////////////////////

		JLabel label_130 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_130.setBounds(12, 673, 150, 15);
		contentPane.add(label_130);

		JLabel label_131 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_131.setBounds(12, 653, 150, 15);
		contentPane.add(label_131);

		JLabel label_132 = new JLabel("\uAC74   \uBB3C : ");
		label_132.setBounds(15, 620, 150, 15);
		contentPane.add(label_132);

		JLabel label_133 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_133.setBounds(15, 604, 150, 15);
		contentPane.add(label_133);

		JLabel label_134 = new JLabel("\uC9C0   \uD615 :");
		label_134.setBounds(15, 588, 150, 15);
		contentPane.add(label_134);

		JLabel label_135 = new JLabel("\uC138   \uAE08 :");
		label_135.setBounds(15, 571, 150, 15);
		contentPane.add(label_135);

		JLabel label_136 = new JLabel("¶¥ÁÖÀÎ : ");
		label_136.setBounds(15, 555, 150, 15);
		contentPane.add(label_136);

		JLabel label_137 = new JLabel("¶¥¹øÈ£ : 15");
		label_137.setBounds(15, 537, 150, 15);
		contentPane.add(label_137);

		JLabel label_138 = new JLabel("");
		label_138.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_138.setHorizontalAlignment(SwingConstants.CENTER);
		label_138.setEnabled(false);
		label_138.setOpaque(true);
		label_138.setBackground(Color.WHITE);
		label_138.setBounds(134, 526, 60, 60);
		contentPane.add(label_138);
		if (this.mapSize == 5) {
			this.fb[15].landColor = label_138;
			this.fb[15].landNum = label_137;
			this.fb[15].landOwner = label_136;
			this.fb[15].landGold = label_135;
			this.fb[15].landTopography = label_134;
			this.fb[15].supply = label_133;
			this.fb[15].building = label_132;
			this.fb[15].defensive = label_131;
			this.fb[15].offensive = label_130;
		}
		///////////////////////////////

		JLabel label_139 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_139.setBounds(206, 673, 150, 15);
		contentPane.add(label_139);

		JLabel label_140 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_140.setBounds(206, 653, 150, 15);
		contentPane.add(label_140);

		JLabel label_141 = new JLabel("\uAC74   \uBB3C : ");
		label_141.setBounds(209, 620, 150, 15);
		contentPane.add(label_141);

		JLabel label_142 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_142.setBounds(209, 604, 150, 15);
		contentPane.add(label_142);

		JLabel label_143 = new JLabel("\uC9C0   \uD615 :");
		label_143.setBounds(209, 588, 150, 15);
		contentPane.add(label_143);

		JLabel label_144 = new JLabel("\uC138   \uAE08 :");
		label_144.setBounds(209, 571, 150, 15);
		contentPane.add(label_144);

		JLabel label_145 = new JLabel("¶¥ÁÖÀÎ : ");
		label_145.setBounds(209, 555, 150, 15);
		contentPane.add(label_145);

		JLabel label_146 = new JLabel("¶¥¹øÈ£ : 16");
		label_146.setBounds(209, 537, 150, 15);
		contentPane.add(label_146);

		JLabel label_147 = new JLabel("");
		label_147.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_147.setHorizontalAlignment(SwingConstants.CENTER);
		label_147.setOpaque(true);
		label_147.setBackground(Color.WHITE);
		label_147.setBounds(328, 526, 60, 60);
		contentPane.add(label_147);

		if (this.mapSize == 5) {
			this.fb[16].landColor = label_147;
			this.fb[16].landNum = label_146;
			this.fb[16].landOwner = label_145;
			this.fb[16].landGold = label_144;
			this.fb[16].landTopography = label_143;
			this.fb[16].supply = label_142;
			this.fb[16].building = label_141;
			this.fb[16].defensive = label_140;
			this.fb[16].offensive = label_139;
		}
		///////////////////////////////

		JLabel label_148 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_148.setBounds(400, 673, 150, 15);
		contentPane.add(label_148);

		JLabel label_149 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_149.setBounds(400, 653, 150, 15);
		contentPane.add(label_149);

		JLabel label_150 = new JLabel("\uAC74   \uBB3C : ");
		label_150.setBounds(403, 620, 150, 15);
		contentPane.add(label_150);

		JLabel label_151 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_151.setBounds(403, 604, 150, 15);
		contentPane.add(label_151);

		JLabel label_152 = new JLabel("\uC9C0   \uD615 :");
		label_152.setBounds(403, 588, 150, 15);
		contentPane.add(label_152);

		JLabel label_153 = new JLabel("\uC138   \uAE08 :");
		label_153.setBounds(403, 571, 150, 15);
		contentPane.add(label_153);

		JLabel label_154 = new JLabel("¶¥ÁÖÀÎ : ");
		label_154.setBounds(403, 555, 150, 15);
		contentPane.add(label_154);

		JLabel label_155 = new JLabel("¶¥¹øÈ£ : 17");
		label_155.setBounds(403, 537, 150, 15);
		contentPane.add(label_155);

		JLabel label_156 = new JLabel("");
		label_156.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_156.setHorizontalAlignment(SwingConstants.CENTER);
		label_156.setOpaque(true);
		label_156.setBackground(Color.WHITE);
		label_156.setBounds(522, 526, 60, 60);
		contentPane.add(label_156);

		if (this.mapSize == 5) {
			this.fb[17].landColor = label_156;
			this.fb[17].landNum = label_155;
			this.fb[17].landOwner = label_154;
			this.fb[17].landGold = label_153;
			this.fb[17].landTopography = label_152;
			this.fb[17].supply = label_151;
			this.fb[17].building = label_150;
			this.fb[17].defensive = label_149;
			this.fb[17].offensive = label_148;
		}
		///////////////////////////////

		JLabel label_157 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_157.setBounds(594, 673, 150, 15);
		contentPane.add(label_157);

		JLabel label_158 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_158.setBounds(594, 653, 150, 15);
		contentPane.add(label_158);

		JLabel label_159 = new JLabel("\uAC74   \uBB3C : ");
		label_159.setBounds(597, 620, 150, 15);
		contentPane.add(label_159);

		JLabel label_160 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_160.setBounds(597, 604, 150, 15);
		contentPane.add(label_160);

		JLabel label_161 = new JLabel("\uC9C0   \uD615 :");
		label_161.setBounds(597, 588, 150, 15);
		contentPane.add(label_161);

		JLabel label_162 = new JLabel("\uC138   \uAE08 :");
		label_162.setBounds(597, 571, 150, 15);
		contentPane.add(label_162);

		JLabel label_163 = new JLabel("¶¥ÁÖÀÎ : ");
		label_163.setBounds(597, 555, 150, 15);
		contentPane.add(label_163);

		JLabel label_164 = new JLabel("¶¥¹øÈ£ : 18");
		label_164.setBounds(597, 537, 150, 15);
		contentPane.add(label_164);

		JLabel label_165 = new JLabel("");
		label_165.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_165.setHorizontalAlignment(SwingConstants.CENTER);
		label_165.setOpaque(true);
		label_165.setBackground(Color.WHITE);
		label_165.setBounds(716, 526, 60, 60);
		contentPane.add(label_165);

		if (this.mapSize == 5) {
			this.fb[18].landColor = label_165;
			this.fb[18].landNum = label_164;
			this.fb[18].landOwner = label_163;
			this.fb[18].landGold = label_162;
			this.fb[18].landTopography = label_161;
			this.fb[18].supply = label_160;
			this.fb[18].building = label_159;
			this.fb[18].defensive = label_158;
			this.fb[18].offensive = label_157;
		}
		///////////////////////////////

		JLabel label_166 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_166.setBounds(788, 673, 150, 15);
		contentPane.add(label_166);

		JLabel label_167 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_167.setBounds(788, 653, 150, 15);
		contentPane.add(label_167);

		JLabel label_168 = new JLabel("\uAC74   \uBB3C : ");
		label_168.setBounds(791, 620, 150, 15);
		contentPane.add(label_168);

		JLabel label_169 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_169.setBounds(791, 604, 150, 15);
		contentPane.add(label_169);

		JLabel label_170 = new JLabel("\uC9C0   \uD615 :");
		label_170.setBounds(791, 588, 150, 15);
		contentPane.add(label_170);

		JLabel label_171 = new JLabel("\uC138   \uAE08 :");
		label_171.setBounds(791, 571, 150, 15);
		contentPane.add(label_171);

		JLabel label_172 = new JLabel("¶¥ÁÖÀÎ : ");
		label_172.setBounds(791, 555, 150, 15);
		contentPane.add(label_172);

		JLabel label_173 = new JLabel("¶¥¹øÈ£ : 19");
		label_173.setBounds(791, 537, 150, 15);
		contentPane.add(label_173);

		JLabel label_174 = new JLabel("");
		label_174.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_174.setHorizontalAlignment(SwingConstants.CENTER);
		label_174.setOpaque(true);
		label_174.setBackground(Color.WHITE);
		label_174.setBounds(910, 526, 60, 60);
		contentPane.add(label_174);

		if (this.mapSize == 5) {
			this.fb[19].landColor = label_174;
			this.fb[19].landNum = label_173;
			this.fb[19].landOwner = label_172;
			this.fb[19].landGold = label_171;
			this.fb[19].landTopography = label_170;
			this.fb[19].supply = label_169;
			this.fb[19].building = label_168;
			this.fb[19].defensive = label_167;
			this.fb[19].offensive = label_166;
		}
		///////////////////////////////

		JLabel label_175 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_175.setBounds(12, 845, 150, 15);
		contentPane.add(label_175);

		JLabel label_176 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_176.setBounds(12, 825, 150, 15);
		contentPane.add(label_176);

		JLabel label_177 = new JLabel("\uAC74   \uBB3C : ");
		label_177.setBounds(15, 792, 150, 15);
		contentPane.add(label_177);

		JLabel label_178 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_178.setBounds(15, 776, 150, 15);
		contentPane.add(label_178);

		JLabel label_179 = new JLabel("\uC9C0   \uD615 :");
		label_179.setBounds(15, 760, 150, 15);
		contentPane.add(label_179);

		JLabel label_180 = new JLabel("\uC138   \uAE08 :");
		label_180.setBounds(15, 743, 150, 15);
		contentPane.add(label_180);

		JLabel label_181 = new JLabel("¶¥ÁÖÀÎ : ");
		label_181.setBounds(15, 727, 150, 15);
		contentPane.add(label_181);

		JLabel label_182 = new JLabel("¶¥¹øÈ£ : 20");
		label_182.setBounds(15, 709, 150, 15);
		contentPane.add(label_182);

		JLabel label_183 = new JLabel("");
		label_183.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_183.setHorizontalAlignment(SwingConstants.CENTER);
		label_183.setOpaque(true);
		label_183.setBackground(Color.WHITE);
		label_183.setBounds(134, 698, 60, 60);
		contentPane.add(label_183);

		if (this.mapSize == 5) {
			this.fb[20].landColor = label_183;
			this.fb[20].landNum = label_182;
			this.fb[20].landOwner = label_181;
			this.fb[20].landGold = label_180;
			this.fb[20].landTopography = label_179;
			this.fb[20].supply = label_178;
			this.fb[20].building = label_177;
			this.fb[20].defensive = label_176;
			this.fb[20].offensive = label_175;
		}
		///////////////////////////////

		JLabel label_184 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_184.setBounds(206, 845, 150, 15);
		contentPane.add(label_184);

		JLabel label_185 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_185.setBounds(206, 825, 150, 15);
		contentPane.add(label_185);

		JLabel label_186 = new JLabel("\uAC74   \uBB3C : ");
		label_186.setBounds(209, 792, 150, 15);
		contentPane.add(label_186);

		JLabel label_187 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_187.setBounds(209, 776, 150, 15);
		contentPane.add(label_187);

		JLabel label_188 = new JLabel("\uC9C0   \uD615 :");
		label_188.setBounds(209, 760, 150, 15);
		contentPane.add(label_188);

		JLabel label_189 = new JLabel("\uC138   \uAE08 :");
		label_189.setBounds(209, 743, 150, 15);
		contentPane.add(label_189);

		JLabel label_190 = new JLabel("¶¥ÁÖÀÎ : ");
		label_190.setBounds(209, 727, 150, 15);
		contentPane.add(label_190);

		JLabel label_191 = new JLabel("¶¥¹øÈ£ : 21");
		label_191.setBounds(209, 709, 150, 15);
		contentPane.add(label_191);

		JLabel label_192 = new JLabel("");
		label_192.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_192.setHorizontalAlignment(SwingConstants.CENTER);
		label_192.setOpaque(true);
		label_192.setBackground(Color.WHITE);
		label_192.setBounds(328, 698, 60, 60);
		contentPane.add(label_192);

		if (this.mapSize == 5) {
			this.fb[21].landColor = label_192;
			this.fb[21].landNum = label_191;
			this.fb[21].landOwner = label_190;
			this.fb[21].landGold = label_189;
			this.fb[21].landTopography = label_188;
			this.fb[21].supply = label_187;
			this.fb[21].building = label_186;
			this.fb[21].defensive = label_185;
			this.fb[21].offensive = label_184;
		}
		///////////////////////////////

		JLabel label_193 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_193.setBounds(400, 845, 150, 15);
		contentPane.add(label_193);

		JLabel label_194 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_194.setBounds(400, 825, 150, 15);
		contentPane.add(label_194);

		JLabel label_195 = new JLabel("\uAC74   \uBB3C : ");
		label_195.setBounds(403, 792, 150, 15);
		contentPane.add(label_195);

		JLabel label_196 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_196.setBounds(403, 776, 150, 15);
		contentPane.add(label_196);

		JLabel label_197 = new JLabel("\uC9C0   \uD615 :");
		label_197.setBounds(403, 760, 150, 15);
		contentPane.add(label_197);

		JLabel label_198 = new JLabel("\uC138   \uAE08 :");
		label_198.setBounds(403, 743, 150, 15);
		contentPane.add(label_198);

		JLabel label_199 = new JLabel("¶¥ÁÖÀÎ : ");
		label_199.setBounds(403, 727, 150, 15);
		contentPane.add(label_199);

		JLabel label_200 = new JLabel("¶¥¹øÈ£ : 22");
		label_200.setBounds(403, 709, 150, 15);
		contentPane.add(label_200);

		JLabel label_201 = new JLabel("");
		label_201.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_201.setHorizontalAlignment(SwingConstants.CENTER);
		label_201.setOpaque(true);
		label_201.setBackground(Color.WHITE);
		label_201.setBounds(522, 698, 60, 60);
		contentPane.add(label_201);

		if (this.mapSize == 5) {
			this.fb[22].landColor = label_201;
			this.fb[22].landNum = label_200;
			this.fb[22].landOwner = label_199;
			this.fb[22].landGold = label_198;
			this.fb[22].landTopography = label_197;
			this.fb[22].supply = label_196;
			this.fb[22].building = label_195;
			this.fb[22].defensive = label_194;
			this.fb[22].offensive = label_193;
		}
		///////////////////////////////

		JLabel label_202 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_202.setBounds(594, 845, 150, 15);
		contentPane.add(label_202);

		JLabel label_203 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_203.setBounds(594, 825, 150, 15);
		contentPane.add(label_203);

		JLabel label_204 = new JLabel("\uAC74   \uBB3C : ");
		label_204.setBounds(597, 792, 150, 15);
		contentPane.add(label_204);

		JLabel label_205 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_205.setBounds(597, 776, 150, 15);
		contentPane.add(label_205);

		JLabel label_206 = new JLabel("\uC9C0   \uD615 :");
		label_206.setBounds(597, 760, 150, 15);
		contentPane.add(label_206);

		JLabel label_207 = new JLabel("\uC138   \uAE08 :");
		label_207.setBounds(597, 743, 150, 15);
		contentPane.add(label_207);

		JLabel label_208 = new JLabel("¶¥ÁÖÀÎ : ");
		label_208.setBounds(597, 727, 150, 15);
		contentPane.add(label_208);

		JLabel label_209 = new JLabel("¶¥¹øÈ£ : 23");
		label_209.setBounds(597, 709, 150, 15);
		contentPane.add(label_209);

		JLabel label_210 = new JLabel("");
		label_210.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_210.setHorizontalAlignment(SwingConstants.CENTER);
		label_210.setOpaque(true);
		label_210.setBackground(Color.WHITE);
		label_210.setBounds(716, 698, 60, 60);
		contentPane.add(label_210);

		if (this.mapSize == 5) {
			this.fb[23].landColor = label_210;
			this.fb[23].landNum = label_209;
			this.fb[23].landOwner = label_208;
			this.fb[23].landGold = label_207;
			this.fb[23].landTopography = label_206;
			this.fb[23].supply = label_205;
			this.fb[23].building = label_204;
			this.fb[23].defensive = label_203;
			this.fb[23].offensive = label_202;
		}
		///////////////////////////////

		JLabel label_211 = new JLabel("\uACF5\uACA9\uBCD1\uB825 \uC218:");
		label_211.setBounds(788, 845, 150, 15);
		contentPane.add(label_211);

		JLabel label_212 = new JLabel("\uBC29\uC5B4\uBCD1\uB825 \uC218:");
		label_212.setBounds(788, 825, 150, 15);
		contentPane.add(label_212);

		JLabel label_213 = new JLabel("\uAC74   \uBB3C : ");
		label_213.setBounds(791, 792, 150, 15);
		contentPane.add(label_213);

		JLabel label_214 = new JLabel("\uBCF4\uAE09\uB85C :");
		label_214.setBounds(791, 776, 150, 15);
		contentPane.add(label_214);

		JLabel label_215 = new JLabel("\uC9C0   \uD615 :");
		label_215.setBounds(791, 760, 150, 15);
		contentPane.add(label_215);

		JLabel label_216 = new JLabel("\uC138   \uAE08 :");
		label_216.setBounds(791, 743, 150, 15);
		contentPane.add(label_216);

		JLabel label_217 = new JLabel("¶¥ÁÖÀÎ : ");
		label_217.setBounds(791, 727, 150, 15);
		contentPane.add(label_217);

		JLabel label_218 = new JLabel("¶¥¹øÈ£ : 24");
		label_218.setBounds(791, 709, 150, 15);
		contentPane.add(label_218);

		JLabel label_219 = new JLabel("");
		label_219.setFont(new Font("±¼¸²", Font.PLAIN, 40));
		label_219.setHorizontalAlignment(SwingConstants.CENTER);
		label_219.setOpaque(true);
		label_219.setBackground(Color.WHITE);
		label_219.setBounds(910, 698, 60, 60);
		contentPane.add(label_219);

		if (this.mapSize == 5) {
			this.fb[24].landColor = label_219;
			this.fb[24].landNum = label_218;
			this.fb[24].landOwner = label_217;
			this.fb[24].landGold = label_216;
			this.fb[24].landTopography = label_215;
			this.fb[24].supply = label_214;
			this.fb[24].building = label_213;
			this.fb[24].defensive = label_212;
			this.fb[24].offensive = label_211;
		}
		///////////////////////////////

		this.comboBox = new JComboBox(soldierName);
		comboBox.setBounds(1004, 698, 97, 23);
		contentPane.add(comboBox);

		//////////////////////////////

		JLabel label_220 = new JLabel(""); // land 0
		label_220.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
//				makeSoldier(0);

			}

			@Override
			public void mousePressed(MouseEvent e) {
				lblNewLabel_3.setText("¹èÄ¡ºÒ°¡");
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				lblNewLabel_3.setText("");
			}
		});
		label_220.setOpaque(true);
		label_220.setBackground(Color.WHITE);
		label_220.setBounds(12, 10, 182, 162);
		contentPane.add(label_220);

		JLabel label_221 = new JLabel(""); // land 1
		label_221.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(1);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_12.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_12.setText("");
			}
		});
		label_221.setOpaque(true);
		label_221.setBackground(Color.WHITE);
		label_221.setBounds(203, 10, 182, 162);
		contentPane.add(label_221);

		JLabel label_222 = new JLabel("");
		label_222.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(2);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_21.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_21.setText("");
			}
		});
		label_222.setOpaque(true);
		label_222.setBackground(Color.WHITE);
		label_222.setBounds(397, 10, 182, 162);
		contentPane.add(label_222);

		JLabel label_223 = new JLabel("");
		label_223.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(3);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_30.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_30.setText("");
			}
		});
		label_223.setOpaque(true);
		label_223.setBackground(Color.WHITE);
		label_223.setBounds(591, 10, 182, 162);
		contentPane.add(label_223);

		JLabel label_224 = new JLabel("");
		label_224.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(4);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_39.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_39.setText("");
			}
		});
		label_224.setOpaque(true);
		label_224.setBackground(Color.WHITE);
		label_224.setBounds(785, 10, 182, 162);
		contentPane.add(label_224);

		JLabel label_225 = new JLabel("");
		label_225.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(5);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_48.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_48.setText("");
			}
		});
		label_225.setOpaque(true);
		label_225.setBackground(Color.WHITE);
		label_225.setBounds(9, 182, 182, 162);
		contentPane.add(label_225);

		JLabel label_226 = new JLabel("");
		label_226.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(6);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_57.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_57.setText("");
			}
		});
		label_226.setOpaque(true);
		label_226.setBackground(Color.WHITE);
		label_226.setBounds(206, 182, 182, 162);
		contentPane.add(label_226);

		JLabel label_227 = new JLabel("");
		label_227.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(7);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_66.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_66.setText("");
			}
		});
		label_227.setOpaque(true);
		label_227.setBackground(Color.WHITE);
		label_227.setBounds(400, 182, 182, 162);
		contentPane.add(label_227);

		JLabel label_228 = new JLabel("");
		label_228.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(8);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_75.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_75.setText("");
			}
		});
		label_228.setOpaque(true);
		label_228.setBackground(Color.WHITE);
		label_228.setBounds(594, 182, 182, 162);
		contentPane.add(label_228);

		JLabel label_229 = new JLabel("");
		label_229.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				makeSoldier(9);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_84.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_84.setText("");
			}
		});
		label_229.setOpaque(true);
		label_229.setBackground(Color.WHITE);
		label_229.setBounds(788, 182, 182, 162);
		contentPane.add(label_229);

		JLabel label_230 = new JLabel("");
		label_230.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(10);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_93.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_93.setText("");
			}
		});
		label_230.setOpaque(true);
		label_230.setBackground(Color.WHITE);
		label_230.setBounds(9, 354, 182, 162);
		contentPane.add(label_230);

		JLabel label_231 = new JLabel("");
		label_231.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(11);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_102.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_102.setText("");
			}
		});
		label_231.setOpaque(true);
		label_231.setBackground(Color.WHITE);
		label_231.setBounds(206, 354, 182, 162);
		contentPane.add(label_231);

		JLabel label_232 = new JLabel("");
		label_232.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(12);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_111.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_111.setText("");
			}
		});
		label_232.setOpaque(true);
		label_232.setBackground(Color.WHITE);
		label_232.setBounds(400, 354, 182, 162);
		contentPane.add(label_232);

		JLabel label_233 = new JLabel("");
		label_233.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(13);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_120.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_120.setText("");
			}
		});
		label_233.setOpaque(true);
		label_233.setBackground(Color.WHITE);
		label_233.setBounds(594, 354, 182, 162);
		contentPane.add(label_233);

		JLabel label_234 = new JLabel("");
		label_234.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(14);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_129.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_129.setText("");
			}
		});
		label_234.setOpaque(true);
		label_234.setBackground(Color.WHITE);
		label_234.setBounds(788, 354, 182, 162);
		contentPane.add(label_234);

		JLabel label_235 = new JLabel("");
		label_235.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(15);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_138.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_138.setText("");
			}
		});
		label_235.setOpaque(true);
		label_235.setBackground(Color.WHITE);
		label_235.setBounds(9, 526, 182, 162);
		contentPane.add(label_235);

		JLabel label_236 = new JLabel("");
		label_236.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(16);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_147.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_147.setText("");
			}
		});
		label_236.setOpaque(true);
		label_236.setBackground(Color.WHITE);
		label_236.setBounds(206, 526, 182, 162);
		contentPane.add(label_236);

		JLabel label_237 = new JLabel("");
		label_237.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(17);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_156.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_156.setText("");
			}
		});
		label_237.setOpaque(true);
		label_237.setBackground(Color.WHITE);
		label_237.setBounds(400, 526, 182, 162);
		contentPane.add(label_237);

		JLabel label_238 = new JLabel("");
		label_238.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(18);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_165.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_165.setText("");
			}
		});
		label_238.setOpaque(true);
		label_238.setBackground(Color.WHITE);
		label_238.setBounds(591, 526, 182, 162);
		contentPane.add(label_238);

		JLabel label_239 = new JLabel("");
		label_239.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(19);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_174.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_174.setText("");
			}
		});
		label_239.setOpaque(true);
		label_239.setBackground(Color.WHITE);
		label_239.setBounds(788, 526, 182, 162);
		contentPane.add(label_239);

		JLabel label_240 = new JLabel("");
		label_240.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(20);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_183.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_183.setText("");
			}
		});
		label_240.setOpaque(true);
		label_240.setBackground(Color.WHITE);
		label_240.setBounds(12, 698, 182, 162);
		contentPane.add(label_240);

		JLabel label_241 = new JLabel("");
		label_241.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(21);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_192.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_192.setText("");
			}
		});
		label_241.setOpaque(true);
		label_241.setBackground(Color.WHITE);
		label_241.setBounds(206, 698, 182, 162);
		contentPane.add(label_241);

		JLabel label_242 = new JLabel("");
		label_242.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(22);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_201.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_201.setText("");
			}
		});
		label_242.setOpaque(true);
		label_242.setBackground(Color.WHITE);
		label_242.setBounds(400, 698, 182, 162);
		contentPane.add(label_242);

		JLabel label_243 = new JLabel("");
		label_243.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(23);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_210.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_210.setText("");
			}
		});
		label_243.setOpaque(true);
		label_243.setBackground(Color.WHITE);
		label_243.setBounds(594, 698, 182, 162);
		contentPane.add(label_243);

		JLabel label_244 = new JLabel("");
		label_244.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				makeSoldier(24);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				label_219.setText(pressCk);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				label_219.setText("");
			}
		});
		label_244.setOpaque(true);
		label_244.setBackground(Color.WHITE);
		label_244.setBounds(788, 698, 182, 162);
		contentPane.add(label_244);

		JButton btnNewButton = new JButton("\uAD6D\uC655\uD6C8\uB828");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				player.kingTraining();
				setPlayerStat(player);
				setPlayerGold(player.holdingGold);
			}
		});
		btnNewButton.setBounds(1004, 760, 97, 23);
		contentPane.add(btnNewButton);

		JButton button = new JButton("\uC655\uAD6D\uBC1C\uC804");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				player.kingdomUpgrade();
				fb[0].setLandGold(land[0].turnGold);
				setPlayerGold(player.holdingGold);
			}
		});
		button.setBounds(1004, 793, 97, 23);
		contentPane.add(button);

		JButton btnNewButton_1 = new JButton("\uC2A4\uD0AC\uC0AC\uC6A9");
		btnNewButton_1.addActionListener(new ActionListener() {
			KnightBornKing knight = null;
			MerchantBornKing merchant = null;
			ThiefBornKing thief = null;

			public void actionPerformed(ActionEvent e) {

				if (player.origin.equals("Thief")) {
					thief = (ThiefBornKing) player;
					thief.stealLand(aiPlayer);
				} else if (player.origin.contentEquals("Merchant")) {
					merchant = (MerchantBornKing) player;
					merchant.tradeWar(aiPlayer);
				} else if (player.origin.equals("Knight")) {
					knight = (KnightBornKing) player;
					knight.fightManToMan(aiPlayer);
				}
				setPlayerGold(player.holdingGold);
			}
		});
		btnNewButton_1.setBounds(1004, 826, 97, 23);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_4 = new JLabel("<KingName>");
		lblNewLabel_4.setBounds(984, 104, 75, 15);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("<Record>");
		lblNewLabel_5.setBounds(984, 157, 57, 15);
		contentPane.add(lblNewLabel_5);

		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.CENTER);
		txtName.setEditable(false);
		txtName.setText(this.idName);
		txtName.setBounds(985, 129, 116, 21);
		contentPane.add(txtName);
		txtName.setColumns(10);

		txtBouts = new JTextField();
		txtBouts.setHorizontalAlignment(SwingConstants.CENTER);
		txtBouts.setEditable(false);
		txtBouts.setText((this.win + this.lose));
		txtBouts.setBounds(982, 173, 48, 21);
		contentPane.add(txtBouts);
		txtBouts.setColumns(10);

		txtWins = new JTextField();
		txtWins.setHorizontalAlignment(SwingConstants.CENTER);
		txtWins.setEditable(false);
		txtWins.setText(this.win);
		txtWins.setBounds(982, 205, 48, 21);
		contentPane.add(txtWins);
		txtWins.setColumns(10);

		textLose = new JTextField();
		textLose.setHorizontalAlignment(SwingConstants.CENTER);
		textLose.setEditable(false);
		textLose.setText(this.lose);
		textLose.setBounds(1066, 205, 48, 21);
		contentPane.add(textLose);
		textLose.setColumns(10);

		JLabel label_245 = new JLabel("<Player>");
		label_245.setBounds(984, 239, 57, 15);
		contentPane.add(label_245);

		label_246 = new JLabel("\uACF5\uACA9\uB825 : ");
		label_246.setBounds(987, 259, 102, 15);
		contentPane.add(label_246);

		label_247 = new JLabel("\uCCB4 \uB825 :");
		label_247.setBounds(987, 277, 100, 15);
		contentPane.add(label_247);

		JLabel label_248 = new JLabel("<\uBCD1\uC0AC\uC18C\uC9D1>");
		label_248.setBounds(1004, 680, 97, 15);
		contentPane.add(label_248);

		JLabel label_249 = new JLabel("<Ai Player>");
		label_249.setBounds(984, 361, 72, 15);
		contentPane.add(label_249);

		label_250 = new JLabel("\uACF5\uACA9\uB825 : ");
		label_250.setBounds(987, 381, 129, 15);
		contentPane.add(label_250);

		label_251 = new JLabel("\uCCB4 \uB825 :");
		label_251.setBounds(987, 399, 114, 15);
		contentPane.add(label_251);

		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setEditable(false);
		textField_1.setBounds(985, 27, 48, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel label_252 = new JLabel("\uBD84");
		label_252.setBounds(1039, 30, 20, 15);
		contentPane.add(label_252);

		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.CENTER);
		textField_2.setEditable(false);
		textField_2.setBounds(1059, 27, 48, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("\uCD08");
		lblNewLabel_6.setBounds(1116, 30, 20, 15);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("<\uAC8C\uC784 \uD50C\uB808\uC774 \uC2DC\uAC04>");
		lblNewLabel_7.setBounds(984, 10, 116, 15);
		contentPane.add(lblNewLabel_7);

		label_253 = new JLabel("\uD2B9\uC218\uB2A5\uB825\uCE58:");
		label_253.setBounds(987, 295, 150, 15);
		contentPane.add(label_253);

		label_254 = new JLabel("\uD2B9\uC218\uB2A5\uB825\uCE58:");
		label_254.setBounds(987, 417, 150, 15);
		contentPane.add(label_254);

		JLabel lblNewLabel_8 = new JLabel("\uC804");
		lblNewLabel_8.setBounds(1030, 176, 20, 15);
		contentPane.add(lblNewLabel_8);

		JLabel label_255 = new JLabel("\uC2B9");
		label_255.setBounds(1030, 212, 27, 15);
		contentPane.add(label_255);

		JLabel label_256 = new JLabel("\uD328");
		label_256.setBounds(1119, 212, 27, 15);
		contentPane.add(label_256);

		JLabel label_257 = new JLabel("<\uC138\uAE08 \uB0A8\uC740\uC2DC\uAC04>");
		label_257.setBounds(985, 54, 116, 15);
		contentPane.add(label_257);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(1058, 77, 48, 21);
		contentPane.add(textField);

		JLabel label_258 = new JLabel("\uCD08");
		label_258.setBounds(1115, 80, 20, 15);
		contentPane.add(label_258);

		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.CENTER);
		textField_3.setText((String) null);
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(1004, 650, 97, 21);
		contentPane.add(textField_3);

		JLabel label_259 = new JLabel("<\uBCF4\uC720\uACE8\uB4DC>");
		label_259.setBounds(1004, 620, 97, 15);
		contentPane.add(label_259);

		JButton button_1 = new JButton("º´»ç ¼³¸í");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (explainFrame == null)
					explainFrame = new ExplainFrame();
				explainFrame = null;
			}
		});
		button_1.setBounds(1004, 730, 97, 23);
		contentPane.add(button_1);

//		fb[0].landColor=label_220;
//		fb[1].landColor=label_221;
//		fb[2].landColor=label_222;
//		fb[3].landColor=label_223;
//		fb[4].landColor=label_224;
//		fb[5].landColor=label_225;
//		fb[6].landColor=label_226;
//		fb[7].landColor=label_227;
//		fb[8].landColor=label_228;
//		fb[9].landColor=label_229;
//		fb[10].landColor=label_230;
//		fb[11].landColor=label_231;
//		fb[12].landColor=label_232;
//		fb[13].landColor=label_233;
//		fb[14].landColor=label_234;
//		fb[15].landColor=label_235;
//		fb[16].landColor=label_236;
//		fb[17].landColor=label_237;
//		fb[18].landColor=label_238;
//		fb[19].landColor=label_239;
//		fb[20].landColor=label_240;
//		fb[21].landColor=label_241;
//		fb[22].landColor=label_242;
//		fb[23].landColor=label_243;
//		fb[24].landColor=label_244;

		this.setVisible(true);

		try {
			String tempString;
			RandomAccessFile readFile = new RandomAccessFile("dataTest.txt", "r");

			while (readFile.getFilePointer() != readFile.length()) {

				tempString = readFile.readLine();
				if (tempString.length() != 0)
					readList.add(readFile.readLine());
			}

			for (int i = 0; i < readList.size(); i++) {
				int idx = readList.get(i).indexOf("@");
				String id = readList.get(i).substring(0, idx);
//				System.out.println(tee.loginId + "rr");
				this.loginId = tee.loginId;
				if (id.equals(loginId)) {
					System.out.println("Ã£±â¼º°ø");
					int pidx = readList.get(i).indexOf("#");
					int widx = readList.get(i).indexOf("!");
					int lidx = readList.get(i).indexOf("%");

					this.idName = readList.get(i).substring(pidx + 1, widx);
					this.win = readList.get(i).substring(widx + 1, lidx);
					this.lose = readList.get(i).substring(lidx + 1);

					this.txtName.setText(this.idName);
					this.txtBouts.setText(String.valueOf(Integer.parseInt(this.win) + Integer.parseInt(this.lose)));
					this.txtWins.setText(this.win);
					this.textLose.setText(this.lose);

					readFile.close();
					break;
				}

			}

		} catch (IOException z) {
			z.printStackTrace();
		}

		for (int i = 0; i < fb.length; i++) {
			fb[i].building.hide();
		}

	}

	public void makeSoldier(int landNum) {
		int intTemp, targetLand;
		boolean goldGood = false;
		Soldier tempSoldier;
		intTemp = comboBox.getSelectedIndex();

		if (intTemp == 0) {
			if (player.holdingGold >= 2)
				goldGood = true;
			else
				System.out.println("±ÝÀÌ ºÎÁ·ÇÕ´Ï´Ù.");
		} else if (intTemp == 1) {
			if (player.holdingGold >= 3)
				goldGood = true;
			else
				System.out.println("±ÝÀÌ ºÎÁ·ÇÕ´Ï´Ù.");
		} else if (intTemp == 2) {
			if (player.holdingGold >= 4)
				goldGood = true;
			else
				System.out.println("±ÝÀÌ ºÎÁ·ÇÕ´Ï´Ù.");
		} else if (intTemp == 3) {
			if (player.holdingGold >= 8)
				goldGood = true;
			else
				System.out.println("±ÝÀÌ ºÎÁ·ÇÕ´Ï´Ù.");
		} else if (intTemp == 4) {
			if (player.holdingGold >= 10)
				goldGood = true;
			else
				System.out.println("±ÝÀÌ ºÎÁ·ÇÕ´Ï´Ù.");
		}

		if (goldGood) {
			targetLand = landNum;
			tempSoldier = player.createSoldier(intTemp);
			tempSoldier.setLand(land);
			tempSoldier.targetLandNumber = targetLand;
			Thread tempThread = new Thread(tg1, tempSoldier);
			tempThread.start();
			this.setPlayerGold(player.holdingGold);
		}

	}

	public void setPlayerStat(King king) {
		KnightBornKing knight = null;
		MerchantBornKing merchant = null;
		ThiefBornKing thief = null;

		if (king.aiOption == false) {
			label_246.setText("°ø°Ý·Â :" + king.showAttackData());
			label_247.setText("»ý¸í·Â :" + king.showLifeData());

			if (king.origin.equals("Thief")) {
				thief = (ThiefBornKing) king;
				label_253.setText("½Ã  ±â :" + thief.invidia);
			} else if (king.origin.contentEquals("Merchant")) {
				merchant = (MerchantBornKing) king;
				label_253.setText("Å½  ¿å :" + merchant.greed);
			} else if (king.origin.equals("Knight")) {
				knight = (KnightBornKing) king;
				label_253.setText("¿À  ¸¸ :" + knight.superbia);
			}
		} else {
			label_250.setText("°ø°Ý·Â :" + king.showAttackData());
			label_251.setText("»ý¸í·Â :" + king.showLifeData());

			if (king.origin.equals("Thief")) {
				thief = (ThiefBornKing) king;
				label_254.setText("½Ã  ±â :" + thief.invidia);
			} else if (king.origin.contentEquals("Merchant")) {
				merchant = (MerchantBornKing) king;
				label_254.setText("Å½  ¿å :" + merchant.greed);
			} else if (king.origin.equals("Knight")) {
				knight = (KnightBornKing) king;
				label_254.setText("¿À  ¸¸ :" + knight.superbia);
			}
		}
		// this.setPlayerGold(player.holdingGold);
	}

	public void setTime(int sec) {
		textField_1.setText(String.valueOf(sec / 60));
		textField_2.setText(String.valueOf(sec % 60));
	}

	public void setTexTime(int sec) {
		textField.setText(String.valueOf(5 - sec));
	}

	public void setPlayerGold(int gold) {
		textField_3.setText(String.valueOf(gold));
	}
}
