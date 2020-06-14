/******************************************************************
 - ����� �ְ���� �� ��Ʈ���� �ְ� ����
 [���μ���] ������ ���� -> Ŭ���̾�Ʈ�� ��ٸ��� -> Ŭ���̾�Ʈ�� ���ͼ� �޼����� ������ 
 		  -> ������ Ŭ���̾�Ʈ�� �޼����� �о���δ� -> gui�� �޼����� �о���̱� ��Ź�Ѵ�.
 1. �޼����� �ְ� �ޱ�
 2. GUI �����
 3. ����
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
	private Socket socket;  /* ��� �� */
	private DataInputStream in;
	private DataOutputStream out;
	private ServerGui gui;
	private String msg;
	
	
	public final void setGui(ServerGui gui) {
		this.gui = gui;
	}
	
	public void setting() {
		try {
			serverSocket = new ServerSocket(7777); /* 7777�� ������ ���ڴ�, ���� ���� */
			System.out.println("���� �����...");
			socket = serverSocket.accept();  /* Ŭ���̾�Ʈ�� ���� ������ ��� */
			System.out.println(socket.getInetAddress()+"���� �����߽��ϴ�.");
			
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
			
			msg = in.readUTF();
			System.out.println("Ŭ���̾�Ʈ�κ����� �޼��� : " + msg);
			gui.appendMsg(msg);  /* gui���ٰ� �޼��� �÷��� ���� ��Ź�� */
			
			while(in != null) {  // ��� �޼����� �о����
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

	public void sendMessage(String msg) { // Ŭ���̾�Ʈ��׶���� �޼��� ����
		try {
			out.writeUTF("���� : " + msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
