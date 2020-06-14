package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBackground {
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;
	private String msg;
	

	public void setGui(ClientGui gui) {
		this.gui = gui;
	}

	private ClientGui gui;
	
	public void connect() {
		try {
			socket = new Socket("127.0.0.1", 7777);  /* 7777소켓을 쓰겠다 */
			
			System.out.println("서버 연결됨");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF("안녕하세요. 나는 클라이언트 입니다.\n");
			System.out.println("클라이언트 메세지 전송 완료");
			
			while(in != null) {  // 계속 메세지를 읽어들임
				msg = in.readUTF();
				gui.appendMsg(msg);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void sandMessage(String msg2) {  // 서버백그라운드로 메세지 전송
		try {
			out.writeUTF("클라이언트 : " + msg2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
