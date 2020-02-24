package board;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import chatting.MultiChatClient;
import login.LoginMain;
import login.MemberInfoVO;
import login.SignUpMain;
import myPage.MyPageFrame;
import quiz.EnteringDAO;
import quiz.EnteringMain;
import quiz.QuizDAO;
import quiz.QuizMain;
import quiz.QuizVO;

public class BoardMain extends JFrame implements ActionListener{
	
	
//	로그인 된 객체 저장하는 변수
	static MemberInfoVO mvo = new MemberInfoVO();
	
//	마우스에서 클릭했을때 정보를 얻어오기위해 사용되는 변수
	int row, col;
	int choice;
	String passwordCheck;
	
//	버튼객체 생성
	static JButton solveButton = new JButton("문제풀기");
	static JButton inputButton = new JButton("문제입력");
	static JButton updateButton = new JButton("문제수정");
	static JButton deleteButton = new JButton("문제삭제");
	static JButton chatButton = new JButton("채팅창");
	static JButton loginButton = new JButton("로그인");
	static JButton joinButton = new JButton("회원가입");
	static JButton resetButton = new JButton();
	static JButton profileButton = new JButton(" ");
	
//	JTable 
	String[] columnNames = { "번호", "문제", "작성일" };
	DefaultTableModel model = new DefaultTableModel(columnNames,0);
	
//	JTable에 올릴 quiz배열 생성
	ArrayList<QuizVO> quizvo = new ArrayList<QuizVO>();
	
	
//	mainboard 생성자 
	public BoardMain() {
		
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPanel MainPanel = new MainPanel(new ImageIcon(".\\src\\images\\main.png").getImage());
		getContentPane().add(MainPanel);
		setLayout(null);
		
		
		JTable table = new JTable(model);
		JScrollPane sc = new JScrollPane(table);
		sc.setBounds(150,132,690,283);
		MainPanel.add(sc);
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
//		quizvo 객체배열로 가져오기
		quizvo = QuizDAO.setquiz();

//		quizvo 테이블에 올리기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd(E) H:mm:ss");
		String[] rowData = new String[3];
		for (QuizVO data : quizvo) {
				rowData[0] = data.getIdx() + "";
				rowData[1] = data.getQuiz();
				rowData[2] = sdf.format(data.getWriteDate());
				model.addRow(rowData);
			}
//		 테이블에 마우스클릭하면 인덱스 가져오기
		table.addMouseListener(new MouseAdapter() {
//			마우스로 클릭했을때 정보를얻어온다.
			@Override
			public void mouseClicked(MouseEvent e) {
				row = table.getSelectedRow();
				col = table.getSelectedColumn();
				choice = Integer.parseInt((String) table.getValueAt(row, 0));
			}});
		
//		새로고침 버튼
		ImageIcon reset1 = new ImageIcon(".\\src\\images\\reset1.png");
		ImageIcon reset2 = new ImageIcon(".\\src\\images\\reset2.png");
		ImageIcon reset3 = new ImageIcon(".\\src\\images\\reset3.png");
		resetButton = new JButton(reset1);
		
		resetButton.setBounds(798, 35, 50, 60);
		resetButton.setBorderPainted(false);
		resetButton.setContentAreaFilled(false);
		resetButton.setFocusPainted(false);
		MainPanel.add(resetButton);
		resetButton.addActionListener(this);
		resetButton.setRolloverIcon(reset2);
		resetButton.setPressedIcon(reset3);
		
//		프로필 버튼
		profileButton.setBounds(878, 38, 64, 61);
		profileButton.setBorderPainted(false);
		profileButton.setContentAreaFilled(false);
		profileButton.setFocusPainted(false);
		MainPanel.add(profileButton);
		profileButton.addActionListener(this);
		
		
//		채팅버튼
		chatButton.setBorder(null);
		chatButton.setBounds(463, 537, 146, 88);
		chatButton.setBackground(new Color(15248986));
		chatButton.setForeground(new Color(9803));
		chatButton.setFont(new Font("굴림", Font.BOLD, 30));
		MainPanel.add(chatButton);
		chatButton.addActionListener(this);
		
//		문제풀기버튼
		solveButton.setForeground(new Color(15248986));
		solveButton.setFont(new Font("굴림", Font.BOLD, 15));
		solveButton.setBorder(null);
		solveButton.setBackground(new Color(9803));
		solveButton.setBounds(500, 438, 72, 44);
		MainPanel.add(solveButton);
		solveButton.addActionListener(this);
		
//		문제 입력버튼
		inputButton.setForeground(new Color(15248986));
		inputButton.setFont(new Font("굴림", Font.BOLD, 15));
		inputButton.setBorder(null);
		inputButton.setBackground(new Color(9803));
		inputButton.setBounds(588, 438, 72, 44);
		MainPanel.add(inputButton);
		inputButton.addActionListener(this);
		
//		문제 수정버튼
		updateButton.setForeground(new Color(15248986));
		updateButton.setFont(new Font("굴림", Font.BOLD, 15));
		updateButton.setBorder(null);
		updateButton.setBackground(new Color(9803));
		updateButton.setBounds(672, 438, 72, 44);
		MainPanel.add(updateButton);
		updateButton.addActionListener(this);
		
//		문제삭제 버튼
		deleteButton.setForeground(new Color(232, 174, 90));
		deleteButton.setFont(new Font("굴림", Font.BOLD, 15));
		deleteButton.setBorder(null);
		deleteButton.setBackground(new Color(0, 38, 75));
		deleteButton.setBounds(756, 438, 72, 44);
		MainPanel.add(deleteButton);
		deleteButton.addActionListener(this);
		
//		로그인버튼
		loginButton.setBorder(null);
		loginButton.setBounds(458, 540, 146, 88);
		loginButton.setBackground(new Color(15248986));
		loginButton.setForeground(new Color(9803));
		loginButton.setBounds(629, 537, 146, 88);
		loginButton.setFont(new Font("굴림", Font.BOLD, 30));
		MainPanel.add(loginButton);
		loginButton.addActionListener(this);
		
//		회원가입버튼
		joinButton.setBorder(null);
		joinButton.setBounds(798, 537, 146, 88);
		joinButton.setBackground(new Color(15248986));
		joinButton.setForeground(new Color(9803));
		joinButton.setFont(new Font("굴림", Font.BOLD, 30));
		MainPanel.add(joinButton);
		joinButton.addActionListener(this);
		
//		비로그인시 채팅,문제입력,문제수정,삭제 버튼 비활성화
		chatButton.setEnabled(false);
		inputButton.setEnabled(false);
		updateButton.setEnabled(false);
		deleteButton.setEnabled(false);
		
//		이미지에 맞춰 메인 프레임 크기지정
		setSize(MainPanel.getDim());
		setPreferredSize(MainPanel.getDim()); 
		
//		프레임 크기 조절안되게하는 메소드
		setResizable(false);
		setVisible(true);
	}
		
//	메인 메소드################################
	public static void main(String[] args) {
		BoardMain window = new BoardMain();
	}

//	버튼 액션리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch (e.getActionCommand()) {
			case "" :	
				reset();
				break;
				
			case "문제풀기":
				QuizMain quizmain = new QuizMain();
				break;
				
			case "문제입력":
				EnteringMain entering = new EnteringMain();
				break;
				
			case "문제수정":
				passwordCheck = JOptionPane.showInputDialog("문제의 비밀번호를 입력해주세요");
				if(passwordCheck == null) {
				}else {
					EnteringDAO.check(choice, passwordCheck);
				}
				break;
				
			case "문제삭제":
				passwordCheck = JOptionPane.showInputDialog("문제의 비밀번호를 입력해주세요");
				if(passwordCheck == null) {
				}else {
					EnteringDAO.cancel(choice, passwordCheck);
				}
				break;
				
			case "채팅창":
				MultiChatClient chat = new MultiChatClient();
				Thread thread = new Thread(chat);
				thread.start();
				break;
				
			case "로그인":
				LoginMain loginMain = new LoginMain();
				
//				로그인이 됬을때 버튼 바꾸기
				if(mvo.getUserID() != null) {
					getJoinButton().setEnabled(false);
					getLoginButton().setEnabled(false);
				}
				break;
				
			case "회원가입":
				SignUpMain signUpMain = new SignUpMain();
				break;
				
			case "로그아웃":
				int logout = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
				if(logout == 0) {
//				로그아웃되면 버튼 비활성화
					getInputButton().setEnabled(false);
					getUpdateButton().setEnabled(false);
					getDeleteButton().setEnabled(false);
					getChatButton().setEnabled(false);
					getJoinButton().setEnabled(true);
					getLoginButton().setText("로그인");	
					JOptionPane.showMessageDialog(null, "로그아웃 되었습니다.");
//				mvo(로그인객체) 비워주기
					mvo = new MemberInfoVO();
				}
				break;
			case " ":						//프로필
				if(mvo.getUserID() == null) {
					JOptionPane.showMessageDialog(null, "로그인을 해주세요");
				}else {
					MyPageFrame myPageFrame = new MyPageFrame();
				}
				break;
			
		}
	}
	
	private void reset() {
//					quizvo 객체배열로 가져오기
					quizvo = QuizDAO.setquiz();
//					테이블에 올려진 quiz객체 비워주기
					for (int i = model.getRowCount() - 1; i >=0 ; i--) {
						model.removeRow(i);
					}
//					quizvo 테이블에 올리기
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd(E) H:mm:ss");
					String[] rowData = new String[3];
					for (QuizVO data : quizvo) {
							rowData[0] = data.getIdx() + "";
							rowData[1] = data.getQuiz();
							rowData[2] = sdf.format(data.getWriteDate());
							model.addRow(rowData);
						}
	}

	public static MemberInfoVO getMvo() {
		return mvo;
	}

	public static void setMvo(MemberInfoVO mvo) {
		BoardMain.mvo = mvo;
	}

	public static JButton getSolveButton() {
		return solveButton;
	}

	public static void setSolveButton(JButton solveButton) {
		BoardMain.solveButton = solveButton;
	}

	public static JButton getInputButton() {
		return inputButton;
	}

	public static void setInputButton(JButton inputButton) {
		BoardMain.inputButton = inputButton;
	}

	public static JButton getUpdateButton() {
		return updateButton;
	}

	public static void setUpdateButton(JButton updateButton) {
		BoardMain.updateButton = updateButton;
	}

	public static JButton getDeleteButton() {
		return deleteButton;
	}

	public static void setDeleteButton(JButton deleteButton) {
		BoardMain.deleteButton = deleteButton;
	}

	public static JButton getChatButton() {
		return chatButton;
	}

	public static void setChatButton(JButton chatButton) {
		BoardMain.chatButton = chatButton;
	}

	public static JButton getLoginButton() {
		return loginButton;
	}

	public static void setLoginButton(JButton loginButton) {
		BoardMain.loginButton = loginButton;
	}

	public static JButton getJoinButton() {
		return joinButton;
	}

	public static void setJoinButton(JButton joinButton) {
		BoardMain.joinButton = joinButton;
	}



}
