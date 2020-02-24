package chatting;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import board.BoardMain;
import login.MemberInfoVO;

public class MultiChatClient extends Frame implements ActionListener, Runnable {

	
	Socket socket;
	PrintWriter printWriter;
	Scanner scanner;
	String message = "";		// 서버와 클라이언트의 대화 내용을 저장했다가 대화 내용이 출력되는 텍스트 영역에 뿌려줄 때 사용할 변수
	
	JTextField textField;
	JButton sendBtn = new JButton("전송");
	JLabel Label = new JLabel("");
	static MemberInfoVO mvo = new MemberInfoVO();
	
	public MultiChatClient() {
		setTitle("1:1 채팅 프로그램(클라이언트)");
		setBounds(800, 50, 500, 700);
		addWindowListener(new WindowAdapter() {
//			클라이언트 채팅창이 닫힐 때 서버에게 나간다고 알려준다. => 통신을 종료한다.
			@Override
			public void windowClosing(WindowEvent e) {
				int result = JOptionPane.showConfirmDialog(Label, "채팅을 종료하겠습니까?", "채팅 종료", JOptionPane.YES_NO_OPTION);
				if(result == 0) {
//					
					if(socket != null) { try { socket.close(); } catch (IOException e1) { e1.printStackTrace(); } }
					if(printWriter != null) { try { printWriter.close(); } catch (Exception e1) { e1.printStackTrace(); } }
					if(scanner != null) { try { scanner.close(); } catch (Exception e1) { e1.printStackTrace(); } }
					setVisible(false);
				}
			}
		});
		
		mvo = BoardMain.getMvo();
		
		MainPanel cattingpanel = new MainPanel(new ImageIcon(".\\src\\images\\chatting.png").getImage());
		add(cattingpanel, BorderLayout.NORTH);	
//		전송버튼
		sendBtn.setBounds(440, 715, 63, 41);
		sendBtn.setFont(new Font("D2Coding", Font.BOLD, 20));
		sendBtn.setBackground(new Color(15248986));
		sendBtn.setForeground(new Color(9803));
		sendBtn.setBorder(null);
		cattingpanel.add(sendBtn);
		
//		텍스트필드 입력
		textField = new JTextField();
		textField.setBounds(3, 715, 438, 41);
		textField.setBorder(null);
		textField.setFont(new Font("D2Coding", Font.PLAIN, 20));
		cattingpanel.add(textField);
		textField.setColumns(10);
		
//		입력한 글씨들이 올라갈 큰 라벨
		Label.setVerticalAlignment(JLabel.BOTTOM);
		Label.setBounds(0, 0, 503, 709);
		Label.setForeground(Color.white);
		cattingpanel.add(Label);
		
		setLocation(550, 100);
		setSize(cattingpanel.getDim());
		setPreferredSize(cattingpanel.getDim()); 
		
		
		
//		텍스트 필드와 전송 버튼에 ActionListener를 걸어준다.
		textField.addActionListener(this);
		sendBtn.addActionListener(this);
		
		setResizable(false);
		
		setVisible(true);
		try {
//			서버에 접속한다.
			socket = new Socket("192.168.7.25", 10009);
			Label.setText("<html>" + message + "</html>");
			
//			서버에 접속했으므로 텍스트 필드와 전송 버튼을 활성화 시키과 메시지를 입력할 수 있게 텍스트 필드로 포커스를 이동시킨다.
			textField.requestFocus();
			
//			서버와 메시지를 주고받기 위해서 데이터 전송에 사용할 객체를 생성한다.
			printWriter = new PrintWriter(socket.getOutputStream());
			scanner = new Scanner(socket.getInputStream());
			
//			클라이언트에서 전송되는 메시지를 받는 스레드를 실행한다.
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	


//	텍스트 필드와 전송 버튼에 ActionListener를 걸어서 서버로 데이터를 전송한다.
	@Override
	public void actionPerformed(ActionEvent e) {
		
//		텍스트 필드에 입력된 메시지를 받는다.
		String str = textField.getText().trim();
//		텍스트 필드에 데이터가 입력된 상태일 경우 메시지를 클라이언트 채팅창에 표시하고 서버로 전송한다.
		if(str.length() > 0) {
//			입력한 메시지를 서버로 전송한다.
			if(printWriter != null) {
				printWriter.write(str + "\n");
				printWriter.flush();
			}
		}
//		서버로 메시지를 전송했으면 다음 메시지를 입력받기 위해 텍스트 필드의 메시지를 지우고 포커스를 옮겨준다.
		textField.setText("");
		textField.requestFocus();
		
	}

//	서버에서 언제 메시지를 보내올지 모르기 때문에 통신이 종료될 때 까지 반복하며 서버에서 전송되는 메시지를 스레드를 실행해서 받는다.
	@Override
	public void run() {
		
		String nickname = mvo.getNickName();
		if(nickname.length() > 0) {
			if(printWriter != null) {
				printWriter.write(nickname + "\n");
				printWriter.flush();
			}
		}
		
		
//		서버와 통신이 유지되고 있는 동안 반복한다. => 통신 소켓이 null이 아닌 동안 반복한다.
		while(socket != null) {
//			서버에서 전송된 메시지를 받는다.
			String str = "";
			try {
				str = scanner.nextLine().trim();
			} catch(NoSuchElementException e) {
				break;
			}
		
//			서버에서 전송된 메시지를 서버 채팅창에 표시한다.
			if(str.length() > 0) {
				message = message  + str+ "<br>";
				Label.setText("<html>" + message + "</html>");
//				서버 채팅 창이 닫히거나 "bye"를 전송받으면 채팅을 종료해야 하므로 반복을 탈출한다.
				if(str.toLowerCase().equals("bye")) {
					break;
				}
			}
			try { Thread.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
		}
//		서버와 채팅이 종료되면 메시지를 입력할 수 없도록 텍스트 필드와 전송 버튼을 비활성화 시킨다.
		textField.setEnabled(false);
		sendBtn.setEnabled(false);
//		채팅에 사용한 모든 객체를 닫는다.
		if(socket != null) { try { socket.close(); } catch (IOException e) { e.printStackTrace(); } }
		if(printWriter != null) { try { printWriter.close(); } catch (Exception e) { e.printStackTrace(); } }
		if(scanner != null) { try { scanner.close(); } catch (Exception e) { e.printStackTrace(); } }
	}

}







 









