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

public class JoinMembership extends JFrame {

	LinkedList<String> readList = new LinkedList<String>();
	boolean overlapTest = false;
	boolean overlapOk = false;

	private JPanel contentPane;
	private JTextField textField_0;
	private JPasswordField textField_1;
	private JPasswordField textField_2;
	private JTextField textField_3;

	JoinMembership me;

	public JoinMembership() {
		this.me = this;

		try {
			RandomAccessFile file = new RandomAccessFile("dataTest.txt", "rw");
		} catch (Exception e) {

		}

		setBounds(100, 100, 333, 264);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblId = new JLabel("ID         :");
		lblId.setBounds(26, 89, 57, 15);
		contentPane.add(lblId);

		JLabel lblPw = new JLabel("PW       :");
		lblPw.setBounds(26, 114, 57, 15);
		contentPane.add(lblPw);

		JLabel lblName = new JLabel("NAME   :");
		lblName.setBounds(26, 159, 57, 15);
		contentPane.add(lblName);

		textField_0 = new JTextField(); // id
		textField_0.setBounds(95, 86, 116, 21);
		contentPane.add(textField_0);
		textField_0.setColumns(10);

		textField_1 = new JPasswordField();
		textField_1.setBounds(95, 111, 116, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JPasswordField();
		textField_2.setBounds(95, 136, 116, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel label = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		label.setFont(new Font("굴림", Font.BOLD, 34));
		label.setBounds(79, 10, 162, 40);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\"영어와 숫자만 사용해주세요\"");
//		label_1.setFont(new Font("굴림", Font.BOLD, 34));
		label_1.setBounds(60, 50, 180, 40);
		contentPane.add(label_1);
		
		JButton btnNewButton = new JButton("\uC911\uBCF5\uAC80\uC0AC");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 중복검사
				try {
					me.overlapTest = true;
					me.overlapOk = true;
					RandomAccessFile readFile = new RandomAccessFile("dataTest.txt", "r");
					while (readFile.getFilePointer() != readFile.length()) {
						readList.add(readFile.readLine());
					}

					for (int i = 0; i < readList.size(); i++) {
						int idx = readList.get(i).indexOf("@");
						String id = readList.get(i).substring(0, idx);

						if (id.equals(textField_0.getText())) {
							System.out.println("동일한 ID가 있습니다");
							me.overlapOk = false;
							break;
						}
					}
					if (me.overlapOk == true && textField_0.getText().length()!=0) {
						System.out.println("사용 가능한 ID 입니다.");
						readFile.close();
					}else if(textField_0.getText().length()==0) {
						System.out.println("ID를 입력해주세요");
					}

				} catch (IOException z) {
					z.printStackTrace();
				}

			}
		});
		btnNewButton.setFont(new Font("굴림", Font.PLAIN, 12));
		btnNewButton.setBounds(212, 85, 93, 23);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("\uAC00\uC785\uD558\uAE30");
		btnNewButton_1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				// 회원가입
				if (me.overlapTest == false) {
					System.out.println("ID 중복검사를 해주세요");

				} else {
					if (me.overlapOk == false) {
						System.out.println("ID 중복검사를 해주세요");
					} else {

						if (textField_0.getText().length() != 0 && textField_1.getText().length() != 0
								&& textField_2.getText().length() != 0 && textField_3.getText().length() != 0) {

							if (textField_1.getText().equals(textField_2.getText())) {
								try {

									RandomAccessFile file = new RandomAccessFile("dataTest.txt", "rw");
									file.seek(file.length());
									file.writeBytes("\r\n");
									file.writeBytes(textField_0.getText());
									file.writeBytes("@");
									file.writeBytes(textField_1.getText());
									file.writeBytes("#");
									file.writeBytes(textField_3.getText());
									file.writeBytes("!");
									file.writeBytes("0");
									file.writeBytes("%");
									file.writeBytes("0");
										
									
									file.close();
									
								} catch (IOException epr) {

								}
								System.out.println("가입성공");
								me.dispose();
							} else {
								System.out.println("비밀번호가 다릅니다.");
							}
						} else {
							System.out.println("전부 기입해주세요");
						}

					}
				}

			}
		});
		btnNewButton_1.setBounds(44, 187, 93, 23);
		contentPane.add(btnNewButton_1);

		JButton button = new JButton("\uAC00\uC785\uCDE8\uC18C");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 가입취소
				me.dispose();
			}
		});
		button.setBounds(169, 187, 93, 23);
		contentPane.add(button);

		JLabel lblPw_1 = new JLabel("PW \uD655\uC778:");
		lblPw_1.setBounds(26, 139, 57, 15);
		contentPane.add(lblPw_1);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(95, 160, 116, 21);
		contentPane.add(textField_3);
		this.setVisible(true);

	}

}
