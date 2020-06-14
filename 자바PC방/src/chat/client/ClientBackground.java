package chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientBackground {
	
	private Socket socket;
	private DataInputStream in;
	private DataOutputStream out;


	public void setGui(ClientGui gui) {
		this.gui = gui;
	}

	private ClientGui gui;
	
	public void connect() {
		try {
			socket = new Socket("127.0.0.1", 7777);
			
			System.out.println("서버 연결됨");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF("안녕하세요. 나는 클라이언트 입니다.");
			System.out.println("클라이언트 메세지 전송 완료");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
