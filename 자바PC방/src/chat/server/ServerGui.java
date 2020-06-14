package chat.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ServerGui extends JFrame implements ActionListener{
	
	private JTextArea jta = new JTextArea(40, 25); /* ���� ���� ���ڿ��� �Է¹��� �� �ִ� â, ��ũ�ѹ� ����X, JScrollPane ��ü�� �����Ͽ� ��ũ�ѹ� ���� */
	private JTextField jtf = new JTextField(25);  /* �� ���� ���ڿ��� �Է¹޴� â, �ؽ�Ʈ �Է� ���� enter Ű�� �ԷµǸ� action �̺�Ʈ �߻�*/
	// �����κ�
	private ServerBackground server = new ServerBackground();

	public ServerGui() {
		
		add(jta, BorderLayout.CENTER);  /* �Է��� ���� �ö󰡴� �κ�*/
		add(jtf, BorderLayout.SOUTH);  /* ä�� �Էºκ� */
		jtf.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(200, 100, 400, 600);
		setTitle("�����κ�");
		server.setGui(this);
		server.setting();
	}
	
	public static void main(String[] args) {
		new ServerGui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = jtf.getText()+"\n";
		jta.append("���� : " + msg);  /* �ؽ�Ʈ ���� ���� ���ڿ��� �߰� */
		System.out.print(msg);
		
		server.sendMessage(msg);  /* �������� Ŭ���̾�Ʈ�� �޼��� �����޶�� ��û */

		jtf.setText("");  /* action�� ���� �Ŀ� �Է�â ���� ���� */
	}

	public void appendMsg(String msg) {  // ������ �о� �� �޼����� ȭ�鿡 �ø�
		jta.append(msg);
		System.out.print("���ƿ� �޼��� : " + msg);
		
	}
}
