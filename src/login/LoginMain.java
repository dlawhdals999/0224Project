package login;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import board.BoardMain;

public class LoginMain extends JFrame implements ActionListener{
	

	JTextField IDField;
	JPasswordField PWField;

	JButton CancleBtn;
	JButton btnNewButton;
	
	public LoginMain() {

		setLocation(600, 300);
		
//		이미지 삽입
		MainPanel loginpanel = new MainPanel(new ImageIcon(".\\src\\images\\login.png").getImage());
		setSize(new Dimension(loginpanel.getDim()));
		setPreferredSize(new Dimension(loginpanel.getDim()));
		
		getContentPane().add(loginpanel, BorderLayout.SOUTH);
		
//		아이디
		IDField = new JTextField();
		IDField.setBounds(304, 106, 187, 31);
		IDField.setFont(new Font("D2Coding", Font.PLAIN, 15));
		IDField.setColumns(10);
		IDField.setBorder(null);
		loginpanel.add(IDField);
		
//		패스워드
		PWField = new JPasswordField();
		PWField.setBounds(304, 157, 187, 31);
		PWField.setFont(new Font("D2Coding", Font.PLAIN, 15));
		PWField.setColumns(10);
		PWField.setBorder(null);
		loginpanel.add(PWField);
		
//		로그인버튼
		JButton LoginBtn = new JButton("로그인");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		LoginBtn.setBounds(245, 208, 90, 39);
		LoginBtn.setFont(new Font("D2Coding", Font.BOLD, 15));
		LoginBtn.setBackground(new Color(15844367));
		LoginBtn.setBorder(null);
		loginpanel.add(LoginBtn);
		
//		취소버튼
		CancleBtn = new JButton("취소");
		CancleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		CancleBtn.setBounds(374, 208, 90, 39);
		CancleBtn.setFont(new Font("D2Coding", Font.BOLD, 15));
		CancleBtn.setBackground(new Color(15844367));
		CancleBtn.setBorder(null);
		loginpanel.add(CancleBtn);
	
//		크기조정
		setSize(loginpanel.getDim());
		setPreferredSize(loginpanel.getDim()); 
		
		pack();
	
		setVisible(true);
		setResizable(false);
		
		LoginBtn.addActionListener(this);
		CancleBtn.addActionListener(this);
	

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String userID = null;
		String userPW= null;
		
		switch (e.getActionCommand()) {
		case "로그인":
			IDField.requestFocus();
			userID = IDField.getText().trim();
			userPW = PWField.getText().trim();
			if(userID.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주세요");
			}else if(userPW.equals("")){
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주세요");
			}else{
				MemberInfoVO vo = LoginDAO.login(userID,userPW);
				if(vo == null) {
					IDField.setText("");
					PWField.setText("");
					IDField.requestFocus();
				}else {
					this.setVisible(false);
					JOptionPane.showMessageDialog(null, vo.getNickName()+ "님 어서오세요, 반갑습니다!");
					BoardMain.getInputButton().setEnabled(true);
					BoardMain.getUpdateButton().setEnabled(true);
					BoardMain.getDeleteButton().setEnabled(true);
					BoardMain.getJoinButton().setEnabled(false);
					BoardMain.getChatButton().setEnabled(true);
					BoardMain.getLoginButton().setEnabled(true);
					BoardMain.getLoginButton().setText("로그아웃");
					BoardMain.setMvo(vo);
					
					
				}
			}
			break;
		case "취소":
			this.setVisible(false);
			BoardMain.getJoinButton().setEnabled(true);
			BoardMain.getLoginButton().setEnabled(true);
			break;
			
		}
		
	}
	

}