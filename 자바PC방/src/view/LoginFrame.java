/**************************************************************************
 + layeredPane 위에 panel과 text, button 등을 올려서 GUI를 제작
 + 로그인 버튼을 ActionListener로 처리
 + 아이디, 비밀번호 입력칸이 빈칸이면 -> 입력요구 메세지 띄움
 					 빈칸이 아니면 -> 로그인 처리
 **************************************************************************/

package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.LoginService;

public class LoginFrame extends JFrame implements ActionListener{
	
	BufferedImage img = null;  /* 배경화면 패널 */
	JTextField loginTextField;  /* 아이디 텍스트 */
	JPasswordField passwordField;  /* 비밀번호 텍스트 */
	JButton bnt;
	
	// 메인
	public static void main(String[] args) {
		new LoginFrame();
	}
	
	// 생성자
	public LoginFrame() {
		
		// 프레임 상단 이름
		setTitle("로그인 테스트");
		
		// 프레임 사이즈
		setSize(1600,900);
		
		// 프레임 'X'버튼 누를 때의 기능
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); /* dispose는 원하는 창만 사라지도록 함, exit는 모든 창 종료 */
		
		// 사용자의 레이아웃 방식 설정
		setLayout(null); /* null : 사용자가 지정한 절대값으로 사용하겠다 */
		
		// 이미지 불러오기
		try {
			img = ImageIO.read(new File("img/login.png"));
		} catch (Exception e) {
			System.out.println("이미지 불러오기 실해");
			System.exit(0); /* 프로그램 종료 */
		}
		
		// 레이아웃 설정 - 아이디, 비밀번호 화면 설정
		JLayeredPane layeredPane = new JLayeredPane(); /* 셀로판지처럼 처리를 해주는 클래스 */
		layeredPane.setBounds(0, 0, 1600, 900);
		layeredPane.setLayout(null);
		
		// 패널1 - 배경화면
		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1600, 900); /* 이미지의 위치와 크기를 지정 */
		
		// 아이디 필드
		loginTextField = new JTextField(15);
		loginTextField.setBounds(731, 399, 288, 30);
		loginTextField.setOpaque(false);  /* 투명 처리 */
		loginTextField.setForeground(Color.green);  /* 글씨색을 초록색으로 설정 */
		loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());  /* 경계선 제거 */
		
		layeredPane.add(loginTextField);
		
		// 패스워드 필드
		passwordField = new JPasswordField(15);
		passwordField.setBounds(731, 529, 288, 30);
		passwordField.setOpaque(false);
		passwordField.setForeground(Color.green);
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		layeredPane.add(passwordField);
		
		// 버튼 추가
		bnt = new JButton(new ImageIcon("img/btLogin_hud.png"));
		bnt.setBounds(755, 689, 104, 48);
		bnt.setBorderPainted(false);  /* 경계선 제거 */
		bnt.setFocusPainted(false);  /* 버튼이 선택(focus)되었을 때 생기는 테두리 사용안함  */
		bnt.setContentAreaFilled(false);  /* 버튼 내용영역 채우지 않음 */
		bnt.addActionListener(this);  /* 클래스 내부에 있는 actionListener에 연결 */
		
		layeredPane.add(bnt);
		
		// 마지막 추가들
		layeredPane.add(panel); // panel을 layeredPane에 넣은 것
		add(layeredPane);
		
		// 화면에 표시
		setVisible(true);
	}
	
	class MyPanel extends JPanel{
		public void paint(Graphics g) {
			/* drawImage는 이미지가 다 그려지면 이미지를 로드한다. observer는 이미지가 로딩된 시점을 가르키므로 알필요 없기 때문에 null 또는 this(자기자신)으로 설정 */
			g.drawImage(img, 0, 0, this); 
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = loginTextField.getText();
		char[] pass = passwordField.getPassword();  /* password는 배열 형태로 받게됨 */
		String password = new String(pass);
		
		if(id.equals("") || password.equals("")) {
			// 메세지를 띄움
			JOptionPane.showMessageDialog(null, "빈칸이 있네요");
		}else {
			// 로그인 참, 거짓 여부를 판단
			boolean existLogin = false;
			try {
				existLogin = LoginService.loginTest(id, password);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}finally {
				if(existLogin) { 
					// 로그인 성공했을 경우
					JOptionPane.showMessageDialog(null, "로그인 성공");
				}else {
					// 로그인 실패했을 경우
					JOptionPane.showMessageDialog(null, "로그인 실패");
				}
			}
			
			
		}
	}
	
	

}
