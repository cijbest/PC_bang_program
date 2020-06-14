package chat.server;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class ServerGui extends JFrame implements ActionListener{
	
	private JTextArea jta = new JTextArea(40, 25); /* 여러 줄의 문자여을 입력받을 수 있는 창, 스크롤바 지원X, JScrollPane 객체를 삽입하여 스크롤바 지원 */
	private JTextField jtf = new JTextField(25);  /* 한 줄의 문자열을 입력받는 창, 텍스트 입력 도중 enter 키가 입력되면 action 이벤트 발생*/
	// 연동부분
	private ServerBackground server = new ServerBackground();

	public ServerGui() {
		
		add(jta, BorderLayout.CENTER);  /* 입력한 글이 올라가는 부분*/
		add(jtf, BorderLayout.SOUTH);  /* 채팅 입력부분 */
		jtf.addActionListener(this);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(200, 100, 400, 600);
		setTitle("서버부분");
		server.setGui(this);
		server.setting();
	}
	
	public static void main(String[] args) {
		new ServerGui();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = jtf.getText()+"\n";
		jta.append(msg);  /* 텍스트 영역 끝에 문자열을 추가 */
		System.out.print(msg);

		jtf.setText("");  /* action이 끝난 후에 입력창 상태 설정 */
		
	}

	public void appendMsg(String msg) {
		jta.append(msg);
		System.out.print("날아온 메세지 : " + msg);
		
	}
}
