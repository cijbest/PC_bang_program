/***********************************************
 
 1. 요금은 10초당 100원
 2. 관리자용 관리프로그램
 3. 자리 50석, 네트워크 관리서버
 4. 클라이언트 로그인
 5. 사용종료 기능
 
 Tip => 이클립스 Get/Set 자동 생성 단축키 Shift+ Alt + S
 
************************************************/

package model;

public class Member { // 회원정보
	private int num;
	private String id;
	private String password;
	private int age;
	private int mileage;
	private String phone;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
