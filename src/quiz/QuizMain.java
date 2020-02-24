package quiz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class QuizMain extends JFrame implements ActionListener {
	JLabel firstLabel;
	JLabel secondlabel;
	JLabel thirdlabel;
	JLabel fourlabel;
	JLabel quizLabel;
	
	int randomIndex=0;
	QuizVO vo;
	String item;
	int aCount;
	int wCount;
	
	ArrayList<Integer> list;
	ArrayList<String> list2;
	int arr[] ={0,1,2,3};
	int an;

	int choice = 0;
	public QuizMain() {
		
//		패널에 이미지 올림
		MainPanel panel = new MainPanel(new ImageIcon(".\\src\\images\\quiz11.png").getImage()); 
		getContentPane().add(panel, BorderLayout.NORTH);	
		
//		첫번째 선택지
		firstLabel = new JLabel("");
		firstLabel.setHorizontalAlignment(JLabel.CENTER);
		firstLabel.setFont(new Font("굴림",Font.BOLD,15));
		firstLabel.setBounds(144, 244, 328, 64);
		firstLabel.setOpaque(true);
		firstLabel.setBackground(Color.white);
		panel.add(firstLabel);
		 
//		두번째 선택지
		secondlabel = new JLabel("");
		secondlabel.setHorizontalAlignment(JLabel.CENTER);
		secondlabel.setFont(new Font("굴림",Font.BOLD,15));
		secondlabel.setOpaque(true);
		secondlabel.setBackground(Color.white);
		secondlabel.setBounds(144, 342, 328, 64);
		panel.add(secondlabel);
		
//		세번째 선택지
		thirdlabel = new JLabel("");
		thirdlabel.setHorizontalAlignment(JLabel.CENTER);
		thirdlabel.setFont(new Font("굴림",Font.BOLD,15));
		thirdlabel.setOpaque(true);
		thirdlabel.setBackground(Color.white);
		thirdlabel.setBounds(144, 438, 328, 64);
		panel.add(thirdlabel);
		
//		네번째 선택지
		fourlabel = new JLabel("");
		fourlabel.setHorizontalAlignment(JLabel.CENTER);
		fourlabel.setFont(new Font("굴림",Font.BOLD,15));
		fourlabel.setOpaque(true);
		fourlabel.setBackground(Color.white);
		fourlabel.setBounds(144, 535, 328, 64);
		panel.add(fourlabel);
		
//		제출버튼
		JButton inputBtn = new JButton("제출");
		inputBtn.addActionListener(this);
		inputBtn.setBounds(563, 548, 117, 71);
		inputBtn.setBackground(new Color(15844367));
		inputBtn.setFont(new Font("D2Coding", Font.BOLD, 25));
		inputBtn.setBorder(null);
		panel.add(inputBtn);
		
//		첫번째 펭수
		JLabel iconLabel = new JLabel("");
		iconLabel.setBounds(46, 222, 71, 110);
		panel.add(iconLabel);
		
//		두번째 펭수
		JLabel siconLabel = new JLabel("");
		siconLabel.setBounds(46, 319, 71, 110);
		panel.add(siconLabel);
		
//		세번째 펭수
		JLabel ticonLabel = new JLabel("");
		ticonLabel.setBounds(46, 417, 71, 110);
		panel.add(ticonLabel);
		
//		네번째 펭수
		JLabel ficonLabel = new JLabel("");
		ficonLabel.setBounds(46, 510, 71, 110);
		panel.add(ficonLabel);

//		문제 출력		
	    quizLabel = new JLabel("");
	    quizLabel.setOpaque(true);
	    quizLabel.setBackground(Color.white);
	    quizLabel.setFont(new Font("굴림",Font.BOLD,22));
	    quizLabel.setHorizontalAlignment(JLabel.CENTER);
		quizLabel.setBounds(36, 45, 495, 134);
		panel.add(quizLabel);
		
//		첫번째 선택지가 클릭되었을때 첫번째 펭수가 나옴, 나머지는 사라짐
		firstLabel.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				
				iconLabel.setIcon(new ImageIcon(".\\src\\images\\minipengsu.png"));
				siconLabel.setIcon(new ImageIcon(""));
				ticonLabel.setIcon(new ImageIcon(""));
				ficonLabel.setIcon(new ImageIcon(""));
				choice = 1;

			}
			
		});
//		두번째 선택지가 클릭되었을때 두번째 펭수가 나옴, 나머지는 사라짐
		secondlabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				iconLabel.setIcon(new ImageIcon(""));
				siconLabel.setIcon(new ImageIcon(".\\src\\images\\minipengsu.png"));
				ticonLabel.setIcon(new ImageIcon(""));
				ficonLabel.setIcon(new ImageIcon(""));
				choice = 2;
			}
			
		});
//		세번째 선택지가 클릭되었을때 세번째 펭수가 나옴, 나머지는 사라짐
		thirdlabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				iconLabel.setIcon(new ImageIcon(""));
				siconLabel.setIcon(new ImageIcon(""));
				ticonLabel.setIcon(new ImageIcon(".\\src\\images\\minipengsu.png"));
				ficonLabel.setIcon(new ImageIcon(""));
				choice = 3;
			}
			
		});
		
//		네번째 선택지가 클릭되었을때 네번째 펭수가 나옴, 나머지는 사라짐
		fourlabel.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				iconLabel.setIcon(new ImageIcon(""));
				siconLabel.setIcon(new ImageIcon(""));
				ticonLabel.setIcon(new ImageIcon(""));
				ficonLabel.setIcon(new ImageIcon(".\\src\\images\\minipengsu.png"));
				choice = 4;
			}
			
		});
		
		setLocation(550, 100);
		setSize(panel.getDim());
		setPreferredSize(panel.getDim()); 
		pack();
		setResizable(false);
		setVisible(true);
		
//		======================================================================================
		//데이터베이스의 전체문제의 idx를 얻어와서 list에 idx를 저장한다.
		list = QuizDAO.readIdx();
		//idx의 순서를 섞어준다.
		for(int i=0; i<1000; i++) {
			int ran = new Random().nextInt(list.size());
			int temp = list.get(0);
			list.set(0,list.get(ran));
			list.set(ran,temp);
		}
		
		//문제를 읽어오는 메소드를 랜덤한 idx를 던져줘서 quizeLabel에 SetText한다.
		rePrintText();
		
	}// 생성자 끝
	
	private void rePrintText() {
		list2 = new ArrayList<String>();
		
		if(randomIndex < list.size()) {
			vo = QuizDAO.readQuiz(list.get(randomIndex));
		}
		
		list2.add(vo.getAnswer());
		list2.add(vo.getwrong1());
		list2.add(vo.getwrong2());
		list2.add(vo.getwrong3());
		
		for(int i=0; i<1000; i++) {
			int ran = new Random().nextInt(4);
			int temp = arr[0];
			arr[0] = arr[ran];
			arr[ran] = temp;
		}
		
		
		for(int j=0; j<list2.size(); j++) {
			if(list2.get(j).length()>19) {
				StringBuffer sb = new StringBuffer(list2.get(j));
				sb.insert(20, "<br>");
			}
		}
//		문제를 얻어올때 20글자마다 <br>을해줘야 띄워쓰기로 얻어와진다.
		String realQuiz = vo.getQuiz().trim();
		StringBuffer quiz1 = new StringBuffer(realQuiz);
		for (int i = 1; i <= (int)realQuiz.length() / 20; i++) {
			if(i == 1) {
				quiz1.insert(20 * i, "<br>");
			}else {
				quiz1.insert(20 * i + 4 * (i-1), "<br>");
				
			}
		}
		realQuiz = quiz1 + "";
		quizLabel.setText("<html>"+realQuiz+"</html>");
		
		firstLabel.setText("<html>"+list2.get(arr[0])+"</html>");
		secondlabel.setText("<html>"+list2.get(arr[1])+"</html>");
		thirdlabel.setText("<html>"+list2.get(arr[2])+"</html>");
		fourlabel.setText("<html>"+list2.get(arr[3])+"</html>");
	}
	
	// 제출 버튼 클릭 -> 정답 시 호출되는 메소드
	private int answerMSG() {
		String[]  btnString = {"채점하기","다음문제"};
		int result = JOptionPane.showOptionDialog(null, "정답입니다!!!", "정답", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null, btnString, btnString[0]);
		if(result == 0) {
			ScoreFrame cp = new ScoreFrame(aCount, wCount);
			cp.setVisible(true);
			setVisible(false);
		}else if(result==1) {
			randomIndex++;
			if(randomIndex < list.size()) {
				rePrintText();
			}else {
				JOptionPane.showMessageDialog(null, "모든 문제를 푸셨습니다.");
				ScoreFrame cp = new ScoreFrame(aCount, wCount);
				cp.setVisible(true);
				list.clear();
				list2.clear();
				randomIndex=0;
				setVisible(false);
			}
		}
		return result;
	}
	
	// 제출 버튼 클릭 -> 오답 시 호출되는 메소드
	private int noAnswerMSG() {
		String[]  btnString = {"채점하기","다음문제"};
		int result = JOptionPane.showOptionDialog(null, "오답입니다 ㅠㅠ", "오답", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, 
				null, btnString, btnString[0]);
		if(result == 0) {
			ScoreFrame cp = new ScoreFrame(aCount, wCount);
			cp.setVisible(true);
			setVisible(false);
		}else if(result==1) {
			randomIndex++;
			if(randomIndex < list.size()) {
				rePrintText();
			}else {
				JOptionPane.showMessageDialog(null, "모든 문제를 푸셨습니다.");
				ScoreFrame cp = new ScoreFrame(aCount, wCount);
				cp.setVisible(true);
				list.clear();
				list2.clear();
				randomIndex=0;
				setVisible(false);
			}
		}                               
		return result;
	}
	
//	제출 버튼 이벤트 처리
	@Override
	public void actionPerformed(ActionEvent e) {
		String ans, str1, str2, str3, str4;
		ans = vo.getAnswer();
		str1 = firstLabel.getText().replace("<html>", "");
		str1 = str1.replace("<br>", "");
		str1 = str1.replace("</html>", "");
		str2 = secondlabel.getText().replace("<html>", "");
		str2 = str2.replace("<br>", "");
		str2 = str2.replace("</html>", "");
		str3 = thirdlabel.getText().replace("<html>", "");
		str3 = str3.replace("<br>", "");
		str3 = str3.replace("</html>", "");
		str4 = fourlabel.getText().replace("<html>", "");
		str4 = str4.replace("<br>", "");
		str4 = str4.replace("</html>", "");
		switch (choice) {
		case 1:
			if(ans.equals(str1)) {
				aCount++;
				int result = answerMSG();
			}else {
				wCount++;
				int result = noAnswerMSG();
			}
			break;
		case 2:
			if(ans.equals(str2)) {
				aCount++;
				int result = answerMSG();
			}else {
				wCount++;
				int result = noAnswerMSG();
			}
			break;	
		case 3:
			if(ans.equals(str3)) {
				aCount++;
				int result = answerMSG();
			}else {
				wCount++;
				int result = noAnswerMSG();
			}
			break;	
		case 4:
			if(ans.equals(str4)) {
				aCount++;
				int result = answerMSG();
			}else {
				wCount++;
				int result = noAnswerMSG();
			}
			break;	
		}
		
	}

}
