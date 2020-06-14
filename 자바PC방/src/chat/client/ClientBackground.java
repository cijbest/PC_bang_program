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
			socket = new Socket("127.0.0.1", 7777); /* 7777������ ���ڴ� */
			
			System.out.println("���� �����");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			out.writeUTF("�ȳ��ϼ���. ���� Ŭ���̾�Ʈ �Դϴ�.\n");
			System.out.println("Ŭ���̾�Ʈ �޼��� ���� �Ϸ�");
			
			while(in != null) {  // ��� �޼����� �о����
				msg = in.readUTF();
				gui.appendMsg(msg);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void sandMessage(String msg2) {  // ������׶���� �޼��� ����
		try {
			out.writeUTF("Ŭ���̾�Ʈ : " + msg2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
