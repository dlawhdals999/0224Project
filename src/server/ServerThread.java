package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
	static ServerSocket serverSocket = null;
	
	@Override
	public void run() {
		while(ServerFrame.serverFlag) {
			
			try {
				serverSocket = new ServerSocket(10009);
				while (true) {
					try {
						Socket socket = serverSocket.accept();
						Thread thread = new ChatThread(socket);
						thread.start();
						
					}catch (Exception e) {
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			
			} 
		}
		if (socket != null) {	try {socket.close();} catch (IOException e) {	e.printStackTrace();}}
		if (serverSocket != null) {	try {serverSocket.close();} catch (IOException e) {	e.printStackTrace();}}
		
	}

	
}
