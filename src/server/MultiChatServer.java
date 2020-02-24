package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiChatServer {

	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(10009);
			System.out.println("서버가 시작되었습니다.");
			
			while (true) {
				Socket socket = serverSocket.accept();
				Thread thread = new ChatThread(socket);
				thread.start();
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {	
			if (serverSocket != null) {	try {serverSocket.close();} catch (IOException e) {	e.printStackTrace();}}
		}

	}
}
