/**************************************************************************
 + layeredPane ���� panel�� text, button ���� �÷��� GUI�� ����
 + �α��� ��ư�� ActionListener�� ó��
 + ���̵�, ��й�ȣ �Է�ĭ�� ��ĭ�̸� -> �Է¿䱸 �޼��� ���
 					 ��ĭ�� �ƴϸ� -> �α��� ó��
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
	
	BufferedImage img = null;  /* ���ȭ�� �г� */
	JTextField loginTextField;  /* ���̵� �ؽ�Ʈ */
	JPasswordField passwordField;  /* ��й�ȣ �ؽ�Ʈ */
	JButton bnt;
	
	// ����
	public static void main(String[] args) {
		new LoginFrame();
	}
	
	// ������
	public LoginFrame() {
		
		// ������ ��� �̸�
		setTitle("�α��� �׽�Ʈ");
		
		// ������ ������
		setSize(1600,900);
		
		// ������ 'X'��ư ���� ���� ���
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); /* dispose�� ���ϴ� â�� ��������� ��, exit�� ��� â ���� */
		
		// ������� ���̾ƿ� ��� ����
		setLayout(null); /* null : ����ڰ� ������ ���밪���� ����ϰڴ� */
		
		// �̹��� �ҷ�����
		try {
			img = ImageIO.read(new File("img/login.png"));
		} catch (Exception e) {
			System.out.println("�̹��� �ҷ����� ����");
			System.exit(0); /* ���α׷� ���� */
		}
		
		// ���̾ƿ� ���� - ���̵�, ��й�ȣ ȭ�� ����
		JLayeredPane layeredPane = new JLayeredPane(); /* ��������ó�� ó���� ���ִ� Ŭ���� */
		layeredPane.setBounds(0, 0, 1600, 900);
		layeredPane.setLayout(null);
		
		// �г�1 - ���ȭ��
		MyPanel panel = new MyPanel();
		panel.setBounds(0, 0, 1600, 900); /* �̹����� ��ġ�� ũ�⸦ ���� */
		
		// ���̵� �ʵ�
		loginTextField = new JTextField(15);
		loginTextField.setBounds(731, 399, 288, 30);
		loginTextField.setOpaque(false);  /* ���� ó�� */
		loginTextField.setForeground(Color.green);  /* �۾����� �ʷϻ����� ���� */
		loginTextField.setBorder(javax.swing.BorderFactory.createEmptyBorder());  /* ��輱 ���� */
		
		layeredPane.add(loginTextField);
		
		// �н����� �ʵ�
		passwordField = new JPasswordField(15);
		passwordField.setBounds(731, 529, 288, 30);
		passwordField.setOpaque(false);
		passwordField.setForeground(Color.green);
		passwordField.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		
		layeredPane.add(passwordField);
		
		// ��ư �߰�
		bnt = new JButton(new ImageIcon("img/btLogin_hud.png"));
		bnt.setBounds(755, 689, 104, 48);
		bnt.setBorderPainted(false);  /* ��輱 ���� */
		bnt.setFocusPainted(false);  /* ��ư�� ����(focus)�Ǿ��� �� ����� �׵θ� ������  */
		bnt.setContentAreaFilled(false);  /* ��ư ���뿵�� ä���� ���� */
		bnt.addActionListener(this);  /* Ŭ���� ���ο� �ִ� actionListener�� ���� */
		
		layeredPane.add(bnt);
		
		// ������ �߰���
		layeredPane.add(panel); // panel�� layeredPane�� ���� ��
		add(layeredPane);
		
		// ȭ�鿡 ǥ��
		setVisible(true);
	}
	
	class MyPanel extends JPanel{
		public void paint(Graphics g) {
			/* drawImage�� �̹����� �� �׷����� �̹����� �ε��Ѵ�. observer�� �̹����� �ε��� ������ ����Ű�Ƿ� ���ʿ� ���� ������ null �Ǵ� this(�ڱ��ڽ�)���� ���� */
			g.drawImage(img, 0, 0, this); 
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String id = loginTextField.getText();
		char[] pass = passwordField.getPassword();  /* password�� �迭 ���·� �ްԵ� */
		String password = new String(pass);
		
		if(id.equals("") || password.equals("")) {
			// �޼����� ���
			JOptionPane.showMessageDialog(null, "��ĭ�� �ֳ׿�");
		}else {
			// �α��� ��, ���� ���θ� �Ǵ�
			boolean existLogin = false;
			try {
				existLogin = LoginService.loginTest(id, password);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}finally {
				if(existLogin) { 
					// �α��� �������� ���
					JOptionPane.showMessageDialog(null, "�α��� ����");
				}else {
					// �α��� �������� ���
					JOptionPane.showMessageDialog(null, "�α��� ����");
				}
			}
			
			
		}
	}
	
	

}
