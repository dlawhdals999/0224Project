package myPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import board.BoardMain;
import board.DBUtil;
import login.MemberInfoVO;

public class MyPageFrame extends JFrame implements ActionListener{
	JButton ModifyBtn = new JButton("회원정보수정");
	JLabel idLabel = new JLabel();
	JLabel nameLabel = new JLabel();
	JLabel genderLabel = new JLabel();
	static JLabel nickNameLabel = new JLabel();
	JLabel answerLabel = new JLabel();
	JLabel wrongAnswerLabel = new JLabel();
	JLabel answerRateLabel = new JLabel();
	
	MemberInfoVO vo = BoardMain.getMvo();
	
//	수정하면 다시찍어주기위한 게터세터
	public static JLabel getNickNameLabel() {
		return nickNameLabel;
	}

	public static void setNickNameLabel(JLabel nickNameLabel) {

		MyPageFrame.nickNameLabel = nickNameLabel;
	}
	
	
	public MyPageFrame() {
		MainPanel mainpanel = new MainPanel(new ImageIcon(".\\src\\images\\mypage9.png").getImage());
		getContentPane().add(mainpanel, BorderLayout.SOUTH);
		

		
//		회원정보 수정 버튼
		ModifyBtn.setBounds(396, 500, 114, 37);
		ModifyBtn.setBackground(new Color(3447003));
		ModifyBtn.setBorder(null);
		ModifyBtn.setForeground(Color.white);
		ModifyBtn.setFont(new Font("D2Coding", Font.BOLD, 15));
		
		mainpanel.add(ModifyBtn);
		ModifyBtn.addActionListener(this);
		
//		아이디라벨
		idLabel.setBounds(179, 126, 225, 26);
		idLabel.setFont(new Font("D2Coding", Font.PLAIN, 17));
		mainpanel.add(idLabel);
		
//		이름라벨
		nameLabel.setBounds(179, 169, 225, 26);
		nameLabel.setFont(new Font("D2Coding", Font.PLAIN, 17));
		mainpanel.add(nameLabel);
		
//		성별라벨
		genderLabel.setBounds(179, 214, 225, 26);
		genderLabel.setFont(new Font("D2Coding", Font.PLAIN, 17));
		mainpanel.add(genderLabel);
		
//		닉네임라벨
		nickNameLabel.setBounds(179, 259, 225, 26);
		nickNameLabel.setFont(new Font("D2Coding", Font.PLAIN, 17));
		mainpanel.add(nickNameLabel);
		
//		정답라벨
		answerLabel.setBounds(179, 336, 225, 26);
		answerLabel.setFont(new Font("D2Coding", Font.PLAIN, 17));
		mainpanel.add(answerLabel);
		
//		오답라벨
		wrongAnswerLabel.setBounds(179, 380, 225, 26);
		wrongAnswerLabel.setFont(new Font("D2Coding", Font.PLAIN, 17));
		mainpanel.add(wrongAnswerLabel);
		
//		정답률라벨
		answerRateLabel.setBounds(179, 423, 225, 26);
		answerRateLabel.setFont(new Font("D2Coding", Font.PLAIN, 17));
		mainpanel.add(answerRateLabel);
		
		setSize(mainpanel.getDim());
		setPreferredSize(mainpanel.getDim()); 
		pack();
		setVisible(true);
		idLabel.setText(vo.getUserID());
		nameLabel.setText(vo.getUserName());
		genderLabel.setText(vo.getGender() ? "남자" : "여자");
		nickNameLabel.setText(vo.getNickName());
		
		try {
			Connection conn = DBUtil.getMySQLConnection();
			String sql = "SELECT * FROM memberinfo WHERE userID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserID());
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			answerLabel.setText("" + rs.getInt("answerCount"));
			wrongAnswerLabel.setText("" + rs.getInt("wrongCount"));
			Double rate = rs.getDouble("answerCount") / rs.getDouble("wrongCount");
			DecimalFormat form = new DecimalFormat("#%");
			answerRateLabel.setText("" + form.format(rate));
			
			DBUtil.close(conn);
			DBUtil.close(pstmt);
			DBUtil.close(rs);
		} catch(SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "회원정보수정":
			ModifyFrame modifyFrame = new ModifyFrame();
			
			break;
		}
	}

}