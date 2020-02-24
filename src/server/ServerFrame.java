package server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ServerFrame extends JFrame implements ActionListener, Runnable {

	JButton startBtn;
	JButton chatBtn;
	JLabel screenLabel = null;
	JLabel statusLabel = new JLabel();
	JPanel panel;
	
	Socket socket;
	PrintWriter printWriter;
	Scanner scanner;
	String message = "";
	static Thread chatThread;
	static boolean serverFlag = false;
	boolean chatFlag = false;
	
	public static void main(String[] args) {
		ServerFrame window = new ServerFrame();
		chatThread = new Thread(window);
	}

	public ServerFrame() {
		setTitle("1:1 채팅 프로그램(서버)");
		MainPanel mainpanel = new MainPanel(new ImageIcon(".\\src\\images\\server.png").getImage());
		getContentPane().add(mainpanel, BorderLayout.SOUTH);

//		상태 메세지
		statusLabel.setOpaque(true);
		statusLabel.setBackground(Color.white);
		
		statusLabel.setBounds(33, 33, 570, 40);
		statusLabel.setHorizontalAlignment(JLabel.CENTER);
		statusLabel.setFont(new Font("D2Coding", Font.BOLD, 30));
		statusLabel.setText("서버를 시작해주세요");
		statusLabel.setBackground(new Color(200217));
		statusLabel.setForeground(Color.white);
		mainpanel.add(statusLabel);
		
		// 서버 시작,종료 버튼
		startBtn = new JButton("서버시작");
		startBtn.addActionListener(this);
		startBtn.setBounds(24, 429, 590, 46);
		startBtn.setBackground(new Color(15844367));
		startBtn.setFont(new Font("D2Coding", Font.PLAIN, 20));
		startBtn.setForeground(new Color(1586501));
		startBtn.setBorder(null);
		mainpanel.add(startBtn);

		// 라벨
		screenLabel = new JLabel("");
		screenLabel.setBounds(36, 37, 565, 356);
		mainpanel.add(screenLabel);
		screenLabel.setVerticalAlignment(JLabel.BOTTOM);
		screenLabel.setHorizontalAlignment(JLabel.LEFT);
		screenLabel.setFont(new Font("D2Coding", Font.PLAIN, 13));
		screenLabel.setForeground(Color.white);

		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(132, 431));
		setPreferredSize(mainpanel.getDim());
		pack();
		setResizable(false);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ServerThread serverThread = new ServerThread();
		
		if (e.getActionCommand().equals("서버시작")) {
			chatFlag = true;
			serverFlag=true;
			getStartBtn().setText("서버종료");
			serverThread.start();
			chatThread.start();
			
			statusLabel.setText("서버가 실행중입니다.");
		} // end if

		if (e.getActionCommand().equals("서버종료")) {
			chatFlag = false;
			serverFlag= false;
			getStartBtn().setText("서버시작");
			chatThread = new Thread();
			try {
				ServerThread.serverSocket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			message = "";
			screenLabel.setText("");
			statusLabel.setText("서버가 종료되었습니다.");
		} // end if

	}

	@Override
	public void run() {
		
		while(chatFlag) {
			try {
				socket = new Socket("192.168.7.25", 10009);
				screenLabel.setText("<html>" + message + "</html>");
				printWriter = new PrintWriter(socket.getOutputStream());
				scanner = new Scanner(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
			while(socket != null) {
				String str = "";
				try {
					str = scanner.nextLine().trim();
				} catch(NoSuchElementException e) {
					break;
				}
				if(str.length() > 0) {
					
					message = message  + str+ "<br>";
					screenLabel.setText("<html>" + message + "</html>");
//				서버 채팅 창이 닫히거나 "bye"를 전송받으면 채팅을 종료해야 하므로 반복을 탈출한다.
					if(str.toLowerCase().equals("bye")) {
						break;
					}
				}
				try { Thread.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
			}
		}

//		채팅에 사용한 모든 객체를 닫는다.
		if(socket != null) { try { socket.close(); } catch (IOException e) { e.printStackTrace(); } }
		if(printWriter != null) { try { printWriter.close(); } catch (Exception e) { e.printStackTrace(); } }
		if(scanner != null) { try { scanner.close(); } catch (Exception e) { e.printStackTrace(); } }
	}
		
	
	public JButton getStartBtn() {
		return startBtn;
	}

	public void setStartBtn(JButton startBtn) {
		this.startBtn = startBtn;
	}
	
	

}