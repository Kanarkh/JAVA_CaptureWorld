import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;

public class ExplainFrame extends JFrame {

	private JPanel contentPane;


	public ExplainFrame() {
	
		setBounds(100, 100, 499, 236);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\uC6D0\uD558\uB294 \uBCD1\uC0AC\uB97C \uB9AC\uC2A4\uD2B8 \uBC15\uC2A4\uC5D0\uC11C \uC120\uD0DD\uD55C \uD6C4 \uC67C\uCABD \uB545\uC744 \uD074\uB9AD\uD558\uC2DC\uBA74 \uC18C\uD658\uC774 \uB429\uB2C8\uB2E4.");
		label.setBounds(25, 35, 453, 15);
		contentPane.add(label);
		
		JLabel lblNewLabel = new JLabel("<\uC0AC\uC6A9\uBC29\uBC95>");
		lblNewLabel.setBounds(24, 10, 70, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(Color.ORANGE);
		lblNewLabel_1.setBounds(0, 60, 490, 4);
		lblNewLabel_1.setOpaque(true);
		contentPane.add(lblNewLabel_1);
		
		JLabel label_1 = new JLabel("");
		label_1.setBounds(10, 77, 57, 15);
		contentPane.add(label_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uBCF4\uBCD1 : Attack 2  Life 4 Speed 1  Gold 2");
		lblNewLabel_2.setBounds(10, 77, 314, 15);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblAttack = new JLabel("\uBC29\uD328\uBCD1 : Attack 1  Life 7 Speed 1  Gold 3");
		lblAttack.setBounds(10, 100, 314, 15);
		contentPane.add(lblAttack);
		
		JLabel lblAttack_1 = new JLabel("\uAE30\uBCD1 : Attack 3  Life 5 Speed 2  Gold 4");
		lblAttack_1.setBounds(10, 125, 314, 15);
		contentPane.add(lblAttack_1);
		
		JLabel lblAttack_2 = new JLabel("\uD3ED\uD0C4\uBCD1 : Attack 2  Life 2 Speed 1  Gold 8, \uC804\uD22C\uB300\uC2E0 \uD3ED\uBC1C\uD55C\uB2E4(\uBAA8\uB450\uC5D0\uAC8C \uD53C\uD574)");
		lblAttack_2.setBounds(10, 150, 445, 15);
		contentPane.add(lblAttack_2);
		
		JLabel lblAttack_3 = new JLabel("\uACF5\uC791\uC6D0 : Attack 1  Life 1 Speed 1  Gold 10, \uC77C\uC815 \uD655\uB960\uB85C \uBAA9\uD45C\uC9C0\uC810\uC5D0 \uBC18\uB780\uC744 \uC77C\uC73C\uD0A8\uB2E4.");
		lblAttack_3.setBounds(10, 175, 500, 15);
		contentPane.add(lblAttack_3);
		
		
		
		this.setVisible(true);
	}
}
