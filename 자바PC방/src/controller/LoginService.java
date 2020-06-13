package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

	public static void main(String[] args) throws SQLException {
		boolean test = loginTest("test", "1234");
		System.out.println("�α��� ��� " + test);

	}
	
	public static boolean loginTest(String id, String password) throws SQLException {
		boolean flag = false;
		
		
		Connection conn = null; /* �����ͺ��̽��� �����ϴ� Ŭ���� */
		Statement stmt = null; /* ���� �ۼ��ؼ� �����ϴ� Ŭ���� */
		ResultSet rs = null;  /* ó���� ����� Ȯ���ϰ� ����ϴ� Ŭ���� */
		String sql = null;
		String getPass = null;
		
		try {
			// ����̹� �ε�
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// �����ϱ�
			String url = "jdbc:mysql://localhost:3306/pcbang?serverTimezone=UTC"; /* DB����/����� �����ͺ��̽� �̸�/����ǥ�ؽð� */ /* �츮���� ǥ�ؽø� ����Ϸ��� '?serverTimezone=Asia/Seoul' */
			conn = DriverManager.getConnection(url, "root", "1234");
			
			// ���� ������ ���� Statement ��ü ����
			stmt = conn.createStatement(); /* �� ������ �ϳ� ������� */
			
			// ���� �ۼ�
			sql = "select password from member";
			
			// ����, ��� ����
			rs = stmt.executeQuery(sql);
			
			// ��� Ž��
			if(rs.next()) {
				// �н����带 �о��
				getPass = rs.getString("password");
				
				// �����ͺ��̽����� �о�� ���ڿ��� ����ڰ� �Է��� ��й�ȣ�� ���� ��쿡 true�� ��ȯ
				if(getPass.equals(password)) {
					System.out.println("�޾ƿ� ��й�ȣ : " + getPass);
					flag = true;
				}
			}
	
		}catch(Exception e) {
			e.printStackTrace(); /* ���� �޼����� �߻� �ٿ����� ã�Ƽ� �ܰ躰�� ������ ��� */
			
		}finally {
			// �������� �ݾ��� (������ ������ ���� �� �����Ƿ� �ݵ�� ó���� ��)
			rs.close();
			stmt.close();
			conn.close();	
		}
		
		// ����� �ݳ�
		return flag;
	}

}
