package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {

	public static void main(String[] args) throws SQLException {
		boolean test = loginTest("test", "1234");
		System.out.println("로그인 결과 " + test);

	}
	
	public static boolean loginTest(String id, String password) throws SQLException {
		boolean flag = false;
		
		
		Connection conn = null; /* 데이터베이스에 연결하는 클래스 */
		Statement stmt = null; /* 쿼리 작성해서 실행하는 클래스 */
		ResultSet rs = null;  /* 처리한 결과를 확인하고 사용하는 클래스 */
		String sql = null;
		String getPass = null;
		
		try {
			// 드라이버 로딩
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// 연결하기
			String url = "jdbc:mysql://localhost:3306/pcbang?serverTimezone=UTC"; /* DB서버/사용할 데이터베이스 이름/국제표준시간 */ /* 우리나라 표준시를 사용하려면 '?serverTimezone=Asia/Seoul' */
			conn = DriverManager.getConnection(url, "root", "1234");
			
			// 쿼리 수행을 위한 Statement 객체 생성
			stmt = conn.createStatement(); /* 빈 문장이 하나 만들어짐 */
			
			// 쿼리 작성
			sql = "select password from member";
			
			// 실행, 결과 리턴
			rs = stmt.executeQuery(sql);
			
			// 결과 탐색
			if(rs.next()) {
				// 패스워드를 읽어옴
				getPass = rs.getString("password");
				
				// 데이터베이스에서 읽어온 문자열과 사용자가 입력한 비밀번호가 같을 경우에 true를 반환
				if(getPass.equals(password)) {
					System.out.println("받아온 비밀번호 : " + getPass);
					flag = true;
				}
			}
	
		}catch(Exception e) {
			e.printStackTrace(); /* 에러 메세지의 발생 근원지를 찾아서 단계별로 에러를 출력 */
			
		}finally {
			// 나머지는 닫아줌 (서버에 문제가 생길 수 있으므로 반드시 처리할 것)
			rs.close();
			stmt.close();
			conn.close();	
		}
		
		// 결과값 반납
		return flag;
	}

}
