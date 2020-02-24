package quiz;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;




public class EnteringMain extends JFrame implements ActionListener{

//	텍스트 필드
	JTextArea quizField = new JTextArea();
	JTextField answerField = new JTextField();
	JTextField wrong1Field = new JTextField();
	JTextField wrong2Field = new JTextField();
	JTextField wrong3Field = new JTextField();
	JTextField quizPasswordField = new JPasswordField();
	JTextField password2Field = new JPasswordField();
//	버튼 필드	
	JButton saveButton = new JButton("저장");
	JButton cancelButton = new JButton("나가기");
	QuizVO vo = new QuizVO();
	
	public EnteringMain() {
		entering();
	}

	public EnteringMain(QuizVO vo) {
		this.vo = vo; 
		entering();
		quizField.setText(vo.getQuiz());
		answerField.setText(vo.getAnswer());
		wrong1Field.setText(vo.getwrong1());
		wrong2Field.setText(vo.getwrong2());
		wrong3Field.setText(vo.getwrong3());
		quizPasswordField.setText(vo.getquizPassword());
		quizPasswordField.setEnabled(false);
		password2Field.setText(vo.getquizPassword());
		password2Field.setEnabled(false);
	
	}

	private void entering() {
		setTitle("문제입력");
//		이미지
		MainPanel panel = new MainPanel(new ImageIcon(".\\src\\images\\inputquiz1.png").getImage());
		getContentPane().add(panel);		
		
//		스크롤
		JScrollPane inputscroll = new JScrollPane();
		inputscroll.setBounds(338, 89, 404, 152);
		inputscroll.setBorder(null);
		panel.add(inputscroll);
		
//		문제입력
		quizField = new JTextArea();
		inputscroll.setViewportView(quizField);
		quizField.setFont(new Font("D2Coding", Font.PLAIN, 17));
		quizField.setBorder(null);
		
		
//		정답
		answerField = new JTextField();
		answerField.setBounds(338, 290, 404, 39);
		answerField.setFont(new Font("D2Coding", Font.PLAIN, 15));
		answerField.setBorder(null);
		panel.add(answerField);
		answerField.setColumns(10);
		
//		오답1
		wrong1Field = new JTextField();
		wrong1Field.setBounds(338, 377, 404, 39);
		wrong1Field.setFont(new Font("D2Coding", Font.PLAIN, 15));
		wrong1Field.setBorder(null);
		panel.add(wrong1Field);
		wrong1Field.setColumns(10);
		
//		오답2
		wrong2Field = new JTextField();
		wrong2Field.setBounds(338, 426, 404, 39);
		wrong2Field.setFont(new Font("D2Coding", Font.PLAIN, 15));
		wrong2Field.setBorder(null);
		panel.add(wrong2Field);
		wrong2Field.setColumns(10);
		
//		오답3
		wrong3Field = new JTextField();
		wrong3Field.setBounds(338, 483, 404, 39);
		wrong3Field.setFont(new Font("D2Coding", Font.PLAIN, 15));
		wrong3Field.setBorder(null);
		panel.add(wrong3Field);
		wrong3Field.setColumns(10);
		
//		비밀번호
		quizPasswordField = new JPasswordField();
		quizPasswordField.setBounds(338, 571, 168, 39);
		quizPasswordField.setBorder(null);
		panel.add(quizPasswordField);
		quizPasswordField.setColumns(10);
		
//		비밀번호 확인
		password2Field = new JPasswordField();
		password2Field.setColumns(10);
		password2Field.setBounds(574, 571, 168, 39);
		password2Field.setBorder(null);
		panel.add(password2Field);
		
//		저장 버튼
		
		saveButton.setBounds(383, 658, 123, 44);
		saveButton.setBackground(new Color(9605538));
		saveButton.setForeground(new Color(16777215));
		saveButton.setFont(new Font("D2Coding", Font.BOLD, 20));
		saveButton.setBorder(null);
		saveButton.addActionListener(this);
		panel.add(saveButton);
		
//		나가기 버튼
		
		cancelButton.setBounds(574, 658, 123, 44);
		cancelButton.setBackground(new Color(9605538));
		cancelButton.setForeground(new Color(16777215));
		cancelButton.setFont(new Font("D2Coding", Font.BOLD, 20));
		cancelButton.setBorder(null);
		cancelButton.addActionListener(this);
		panel.add(cancelButton);

//		크기조정
		setSize(panel.getDim());
		setPreferredSize(panel.getDim()); 
		setResizable(false);
		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String quiz = "", answer = "", wrong1 = "", wrong2 = "", wrong3 = "", quizPassword = "", password2 = "";
		
		switch(e.getActionCommand()) {
		case "저장" :
			
//			텍스트 필드들에서 입력받은 데이터를 각 저장공간에 저장시켜준다.
			quiz = quizField.getText().trim();
			answer = answerField.getText().trim();
			wrong1 = wrong1Field.getText().trim();
			wrong2 = wrong2Field.getText().trim();
			wrong3 = wrong3Field.getText().trim();
			quizPassword = quizPasswordField.getText().trim();
			password2 = password2Field.getText().trim();
			
//			비밀번호 확인
			if(quizPassword.equals(password2)) {
				
		
			vo.setQuiz(quiz);
			vo.setAnswer(answer);
			vo.setwrong1(wrong1);
			vo.setwrong2(wrong2);
			vo.setwrong3(wrong3);
			vo.setquizPassword(quizPassword);
			
//			데이터베이스에 저장해준다.
			boolean flag = EnteringDAO.insert(vo);
			
//			저장된 후에 텍스트 필드들에 있는 글들을 다 지워준다.
//			flag값은 EnteringDAO에서 얻어온다.
//			빈칸을 1개라도 입력하지 않으면 저장이 안되는데, 만약 저장해달라고 메세지를 띄우고
//			이제까지 쓴 글들이 다 지워지면 빡치니까, flag로 다 저장됬을때만 true를 반환해서
//			쓴 글들을 지워준다.
			if(flag) {
				quizField.setText("");
				answerField.setText("");
				wrong1Field.setText("");
				wrong2Field.setText("");
				wrong3Field.setText("");
				quizPasswordField.setText("");
				password2Field.setText("");
			}
			
//			퀴즈필드에 포커스를 옮겨준다.
			quizField.requestFocus();
			
			}
			else {
				JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인해주세요");
			}
			
			break;
		case "나가기" :
			this.setVisible(false);
			break;
			
		case "수정" :
//			텍스트 필드들에서 입력받은 데이터를 각 저장공간에 저장시켜준다.
			quiz = quizField.getText().trim();
			answer = answerField.getText().trim();
			wrong1 = wrong1Field.getText().trim();
			wrong2 = wrong2Field.getText().trim();
			wrong3 = wrong3Field.getText().trim();
			quizPassword = quizPasswordField.getText().trim();
			password2 = password2Field.getText().trim();
//			비밀번호 확인
			if(quizPassword.equals(password2)) {
			
			
			vo.setQuiz(quiz);
			vo.setAnswer(answer);
			vo.setwrong1(wrong1);
			vo.setwrong2(wrong2);
			vo.setwrong3(wrong3);
			vo.setquizPassword(quizPassword);
			
//			데이터베이스에 저장해준다.
			boolean flag = EnteringDAO.update(vo);
			this.setVisible(false);
			
			
			}
			else {
				JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인해주세요");
			}
			
			break;
	
		}
	}
	public JButton getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(JButton saveButton) {
		this.saveButton = saveButton;
	}
	
	
}