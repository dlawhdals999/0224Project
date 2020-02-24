package myPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import board.DBUtil;
import login.MemberInfoVO;
import javax.swing.JButton;

public class ModifyFrame extends JFrame implements ActionListener{

	MemberInfoVO vo = new MemberInfoVO();
	private JTextField nickNameFiled;
	private JPasswordField PWFiled;
	private JPasswordField newPWfield;
	private JPasswordField PWCheckFiled;
	
	
	public static void main(String[] args) {
		
		ModifyFrame window = new ModifyFrame();
				
	}

	public ModifyFrame() {
		
//		vo 확인용
//		vo.setUserID("aaaa");
//		vo.setUserPW("1111");
//		vo.setNickName("동에번쩍");
		
		setLocation(600, 100);

//		이미지 삽입
		MainPanel modifyPage = new MainPanel(new ImageIcon(".\\src\\images\\ModifyPage2.png").getImage());
		getContentPane().add(modifyPage, BorderLayout.SOUTH);
		
//		아이디 라벨
		JLabel idLabel = new JLabel();
		idLabel.setBounds(190, 98, 193, 22);
		idLabel.setFont(new Font("D2Coding", Font.PLAIN, 20));
		idLabel.setText(vo.getUserID());
		modifyPage.add(idLabel);

//		닉네임 라벨
		nickNameFiled = new JTextField();
		nickNameFiled.setBounds(190, 300, 193, 22);
		nickNameFiled.setBorder(null);
		modifyPage.add(nickNameFiled);
		nickNameFiled.setColumns(10);
		
//		비밀번호 라벨
		PWFiled = new JPasswordField();
		PWFiled.setBorder(null);
		PWFiled.setBounds(190, 150, 193, 22);
		modifyPage.add(PWFiled);
		
//		새 비밀번호 라벨
		newPWfield = new JPasswordField();
		newPWfield.setBorder(null);
		newPWfield.setBounds(190, 202, 193, 22);
		modifyPage.add(newPWfield);
		
//		비밀번호 체크 라벨
		PWCheckFiled = new JPasswordField();
		PWCheckFiled.setBorder(null);
		PWCheckFiled.setBounds(190, 252, 193, 22);
		modifyPage.add(PWCheckFiled);
		
//		수정하기 버튼
		JButton modifyBtn = new JButton("수정하기");
		modifyBtn.setBounds(289, 399, 94, 46);
		modifyBtn.setBackground(new Color(2050162));
		modifyBtn.setFont(new Font("D2Coding", Font.BOLD, 23));
		modifyBtn.setForeground(Color.white);
		modifyBtn.setBorder(null);
		modifyPage.add(modifyBtn);
		
//		크기조정
		setSize(new Dimension(modifyPage.getDim()));
		setPreferredSize(new Dimension(modifyPage.getDim()));
		pack();
		setVisible(true);
		modifyBtn.addActionListener(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		비밀번호를 입력하지 않았으면 안넘어감
		if(PWFiled.getText().trim().length() == 0) {
			JOptionPane.showMessageDialog(null, "기존 비밀번호를 입력하지 않았습니다. 다시 입력해주세요");
//		비밀번호를 입력했지만 일치하지 않으면 안넘어감
		}else if(!PWFiled.getText().trim().equals(vo.getUserPW())){
			JOptionPane.showMessageDialog(null, "기존 비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
			PWFiled.setText("");
//		기존 비밀번호를 입력했을때
		}else {
//			새 비밀번호에 입력하였을때
			if(newPWfield.getText().trim().length() > 0) {
//				새 비밀번호에 입력하였는데 비밀번호 확인에는 입력하지 않았을때
				if(PWCheckFiled.getText().trim().length() == 0) {
					JOptionPane.showMessageDialog(null, "새 비밀번호 확인이 입력되지 않았습니다. 새비밀번호 확인을 입력해주세요");
//				새 비밀번호와 비밀번호 확인이 일치하지 않으면 안넘어감
				}else if(!newPWfield.getText().trim().equals(PWCheckFiled.getText().trim())) {
					JOptionPane.showMessageDialog(null, "새 비밀번호와 새비밀번호 확인이 일치하지 않습니다. 다시 입력해주세요.");
					newPWfield.setText("");
					PWCheckFiled.setText("");
//				잘 넘어오면 비밀번호가 바뀜
				}else {
					vo.setUserPW(newPWfield.getText().trim());
				}
			}
//			닉네임에 바꿀 닉네임 입력했을때
			if(nickNameFiled.getText().trim().length() > 0) {
//				닉네임 바꿔줌
				vo.setNickName(nickNameFiled.getText().trim());
			}
			
//			데이터베이스에 저장
			try {
				Connection conn = DBUtil.getMySQLConnection();
				String sql = "UPDATE SIGN SET PASSWORD = ?, nickName = ?  WHERE id = ?";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getUserPW());
				pstmt.setString(2, vo.getNickName());
				pstmt.setString(3, vo.getUserID());
				pstmt.executeUpdate();
				DBUtil.close(conn);
				DBUtil.close(pstmt);
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "수정이 완료 되었습니다.");
			System.out.println(vo.getUserID());
			System.out.println(vo.getUserPW());
			System.out.println(vo.getNickName());
		}
		
		
	}
}