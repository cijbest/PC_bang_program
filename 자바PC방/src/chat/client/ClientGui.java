package chat.client;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chat.server.ServerGui;

public class ClientGui extends JFrame implements ActionListener{
	
	private JTextArea jta = new JTextArea(40, 25);
	private JTextField jtf = new JTextField(25);
	private ClientBackground client = new ClientBackground();

	public ClientGui() {
		
		add(jta, BorderLayout.CENTER);
		add(jtf, BorderLayout.SOUTH);
		jtf.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(800, 100, 400, 600);
		setTitle("Ŭ���̾�Ʈ �κ�");
		
		client.setGui(this);
		client.connect();
	}
	
	public static void main(String[] args) {
		new ClientGui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = jtf.getText() + "\n";
		jta.append("Ŭ���̾�Ʈ : " + msg);
		System.out.print(msg);
		
		client.sandMessage(msg); /* Ŭ���̾�Ʈ���� ������ �޼��� �����޶�� ��û */
		
		jtf.setText("");
		
	}

	public void appendMsg(String msg) {  // Ŭ���̾�Ʈ�� �о� �� �޼����� ȭ�鿡 �ø�
		jta.append(msg);
		
	}
}
