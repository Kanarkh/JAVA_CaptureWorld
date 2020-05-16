import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinFrame extends JFrame {

	private JPanel contentPane;

	Ch te;
	public WinFrame(Ch te) {
		this.te=te;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 309, 432);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\ANArKH\\eclipse-workspace_5W\\CaptureWorld_extends_Thread\\victory.jpeg"));
		lblNewLabel.setBounds(12, 61, 256, 256);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC2B9\uB9AC\uD558\uC600\uC2B5\uB2C8\uB2E4!");
		lblNewLabel_1.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 36));
		lblNewLabel_1.setBounds(12, 10, 264, 42);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("\uC0C8\uB85C\uC6B4 \uAC8C\uC784");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				te.i=5;
			}
		});
		btnNewButton.setBounds(27, 346, 114, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\uADF8\uB9CC\uD558\uAE30");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				te.i=100;
			}
		});
		btnNewButton_1.setBounds(165, 346, 97, 23);
		contentPane.add(btnNewButton_1);
		this.setVisible(true);
	}
}
