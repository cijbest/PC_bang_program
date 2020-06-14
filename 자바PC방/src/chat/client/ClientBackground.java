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
			
			System.out.println("���� �����");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF("�ȳ��ϼ���. ���� Ŭ���̾�Ʈ �Դϴ�.");
			System.out.println("Ŭ���̾�Ʈ �޼��� ���� �Ϸ�");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
