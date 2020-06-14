/******************************************************************
 - 통신을 주고받을 때 스트림을 주고 받음
 [프로세스] 서버를 연다 -> 클라이언트를 기다린다 -> 클라이언트가 들어와서 메세지를 보낸다 
 		  -> 서버가 클라이언트의 메세지를 읽어들인다 -> gui에 메세지를 읽어들이길 부탁한다.
 1. 메세지를 주고 받기
 2. GUI 만들기
 3. 연동
 ******************************************************************/

package chat.server;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerBackground {

	private ServerSocket serverSocket;
	private Socket socket;  /* 듣는 귀 */
	private DataInputStream in;
	private DataOutputStream out;
	private ServerGui gui;
	private String msg;
	
	
	public final void setGui(ServerGui gui) {
		this.gui = gui;
	}
	
	public void setting() {
		try {
			serverSocket = new ServerSocket(7777); /* 7777번 소켓을 쓰겠다, 서버 열림 */
			System.out.println("서버 대기중...");
			socket = serverSocket.accept();  /* 클라이언트가 들어올 때까지 대기 */
			System.out.println(socket.getInetAddress()+"에서 접속했습니다.");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			msg = in.readUTF();
			System.out.println("클라이언트로부터의 메세지 : " + msg);
			gui.appendMsg(msg);  /* gui에다가 메세지 올려줄 것을 부탁함 */
			
			while(in != null) {  // 계속 메세지를 읽어들임
				msg = in.readUTF();
				gui.appendMsg(msg);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		ServerBackground serverBackground = new ServerBackground();
		serverBackground.setting();
	}

	public void sendMessage(String msg) { // 클라이언트백그라운드로 메세지 전송
		try {
			out.writeUTF("서버 : " + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
