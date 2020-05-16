import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoseFrame extends JFrame {

	private JPanel contentPane;

	Ch te;
	public LoseFrame(Ch te) {
		this.te = te;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 432);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ANArKH\\eclipse-workspace_5W\\CaptureWorld_extends_Thread\\skull.jpeg"));
		lblNewLabel.setBounds(12, 61, 256, 256);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uD328\uBC30\uD558\uC600\uC2B5\uB2C8\uB2E4!");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 36));
		lblNewLabel_1.setBounds(12, 10, 264, 42);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\uC0C8\uB85C\uC6B4 \uAC8C\uC784");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				te.i=6;
			}
		});
		btnNewButton.setBounds(27, 346, 110, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uADF8\uB9CC\uD558\uAE30");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				te.i=101;
			}
		});
		btnNewButton_1.setBounds(165, 346, 97, 23);
		contentPane.add(btnNewButton_1);
		
		this.setVisible(true);
	}

}
