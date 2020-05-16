import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField_0;
	private JPasswordField textField_1;
	LinkedList<String> readList = new LinkedList<String>();

	Ch te;

	/**
	 * Create the frame.
	 */
	public LoginFrame(Ch te) {
		this.te = te;
		setTitle("\uC804\uB7B5 \uB545\uB530\uBA39\uAE30(\uB85C\uADF8\uC778)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 314, 304);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("\uB85C\uADF8\uC778");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				System.out.println("button1  " + textField_1.getText());
//				te.i = 1;
				try {

					RandomAccessFile readFile = new RandomAccessFile("dataTest.txt", "r");
					while (readFile.getFilePointer() != readFile.length()) {
						readList.add(readFile.readLine());
					}

					for (int i = 0; i < readList.size(); i++) {
						int idx = readList.get(i).indexOf("@");
//						System.out.println(idx);
						String id = readList.get(i).substring(0, idx);

						if (id.equals(textField_0.getText())) {
							System.out.println("동일한 ID가 있습니다");
							int pidx = readList.get(i).indexOf("#");
							int widx = readList.get(i).indexOf("!");
							String pw = readList.get(i).substring(idx + 1, pidx);
							String name = readList.get(i).substring(pidx + 1, widx);
							te.loginId = id;
							if (pw.contentEquals(textField_1.getText())) {
								te.loginId = id;
								System.out.println(te.loginId);
								te.name = name;
								readFile.close();
								te.i = 1;
								break;
							} else {
//								System.out.println("비밀번호를 확인해주세요");
							}

						} else {
//							System.out.println("아이디를 확인해주세요");
						}

					}

				} catch (IOException z) {
					z.printStackTrace();
				}
			}

		});
		btnNewButton.setBounds(46, 229, 97, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\uD68C\uC6D0\uAC00\uC785");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinMembership joinMember = new JoinMembership();
			}
		});
		btnNewButton_1.setBounds(175, 229, 97, 29);
		contentPane.add(btnNewButton_1);

		textField_0 = new JTextField();
		textField_0.setBounds(108, 103, 118, 21);
		contentPane.add(textField_0);
		textField_0.setColumns(10);

		JLabel lblId = new JLabel("I D : ");
		lblId.setBounds(67, 106, 29, 15);
		contentPane.add(lblId);

		JLabel lblPw = new JLabel("PW :");
		lblPw.setBounds(67, 149, 29, 15);
		contentPane.add(lblPw);

		textField_1 = new JPasswordField();
		textField_1.setBounds(108, 146, 118, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JLabel label = new JLabel("\uC804\uB7B5 \uB545\uB530\uBA39\uAE30");
		label.setFont(new Font("휴먼둥근헤드라인", Font.BOLD, 23));
		label.setBounds(73, 27, 199, 59);
		contentPane.add(label);
		this.setVisible(true);

	}

}
