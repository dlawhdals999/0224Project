package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class SignUpMain extends JFrame implements ActionListener, ItemListener {

	MemberInfoVO vo = new MemberInfoVO();
	boolean gender;
	JRadioButton man, woman;
	ButtonGroup group = new ButtonGroup();

	JFrame frame;
	JTextField idText;
	JTextField passwordText;
	JTextField passwordCheckText;
	JTextField nameText;
	JTextField nickNameText;
//	JRadioButton radioButton;
	JButton CompleteBtn;
	JButton button;

	public SignUpMain() {

		setBounds(100, 100, 450, 300);
		SignUpPanel SignUpPanel = new SignUpPanel(new ImageIcon(".\\src\\images\\signup.png").getImage());
		getContentPane().add(SignUpPanel, BorderLayout.SOUTH);

		idText = new JTextField();
		idText.setBounds(474, 162, 237, 28);
		idText.setBorder(null);
		SignUpPanel.add(idText);
		idText.setColumns(10);

		passwordText = new JPasswordField();
		passwordText.setColumns(10);
		passwordText.setBorder(null);
		passwordText.setBounds(474, 223, 237, 28);
		SignUpPanel.add(passwordText);

		passwordCheckText = new JPasswordField();
		passwordCheckText.setColumns(10);
		passwordCheckText.setBorder(null);
		passwordCheckText.setBounds(474, 280, 237, 28);
		SignUpPanel.add(passwordCheckText);

		nameText = new JTextField();
		nameText.setColumns(10);
		nameText.setBorder(null);
		nameText.setBounds(474, 339, 237, 28);
		SignUpPanel.add(nameText);

		nickNameText = new JTextField();
		nickNameText.setColumns(10);
		nickNameText.setBorder(null);
		nickNameText.setBounds(474, 400, 237, 28);
		SignUpPanel.add(nickNameText);

		man = new JRadioButton("");
		man.setBounds(505, 453, 20, 23);
		man.setBackground(new Color(9803));
		man.addItemListener(this);
		SignUpPanel.add(man);

		woman = new JRadioButton("");
		woman.setBackground(new Color(0, 38, 75));
		woman.setBounds(605, 453, 20, 23);
		woman.addItemListener(this);
		SignUpPanel.add(woman);

		group.add(man);
		group.add(woman);

		JButton insertButton = new JButton("가입");
		insertButton.setBounds(451, 516, 109, 50);
		insertButton.setFont(new Font("D2Coding", Font.BOLD, 15));
		insertButton.setBackground(new Color(15844367));
		SignUpPanel.add(insertButton);
		insertButton.addActionListener(this);

		JButton cancelButton = new JButton("취소");
		cancelButton.setFont(new Font("D2Coding", Font.BOLD, 15));
		cancelButton.setBackground(new Color(241, 196, 15));
		cancelButton.setBounds(584, 516, 93, 50);
		SignUpPanel.add(cancelButton);
		cancelButton.addActionListener(this);

		setSize(SignUpPanel.getDim());
		setPreferredSize(SignUpPanel.getDim());

		setResizable(false);
		setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object object = e.getSource();
		JRadioButton radio = (JRadioButton) object;

		if (radio == man) {
			gender = true;
		} else if (radio == woman) {
			gender = false;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String userID = "", userPW = "", userPWCheck = "", userName = "", nickname = "";
		userID = idText.getText().trim();
		userPW = passwordText.getText().trim();
		userPWCheck = passwordCheckText.getText().trim();
		userName = nameText.getText().trim();
		nickname = nickNameText.getText().trim();

		switch (e.getActionCommand()) {
		case "가입":
			if (userID.length() == 0) {
				JOptionPane.showMessageDialog(null, "아이디가 입력되지 않았습니다.");
			} else if (userPW.length() == 0) {
				JOptionPane.showMessageDialog(null, "비밀번호가 입력되지 않았습니다.");
			} else if (userPWCheck.length() == 0) {
				JOptionPane.showMessageDialog(null, "비밀번호확인을 입력해주세요.");
			} else if (!userPW.equals(userPWCheck)) {
				JOptionPane.showMessageDialog(null, "비밀번호와 비밀번호 확인이 일치하지 않습니다.");
			} else if (userName.length() == 0) {
				JOptionPane.showMessageDialog(null, "이름을 입력하지 않았습니다.");
			} else if (nickname.length() == 0) {
				JOptionPane.showMessageDialog(null, "닉네임을 입력하지 않았습니다.");
			} else if (!man.isSelected() && !woman.isSelected()) {
				JOptionPane.showMessageDialog(null, "성별을 선택해주세요");
			} else {
				vo.setUserID(userID);
				vo.setUserPW(userPW);
				vo.setUserName(userName);
				vo.setGender(gender);
				vo.setNickName(nickname);
				SignUpDAO.insert(vo);
				setVisible(false);
			}

			break;
		case "취소":
			setVisible(false);
			break;
		}

	}

}
