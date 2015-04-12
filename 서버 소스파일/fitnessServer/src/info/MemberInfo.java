package info;

public class MemberInfo {
	private String name;
	private int age;
	private String phone_num;
	private String email_addr;
	private String phone_unique_id;
	private String ip_addr;
	private boolean isPT;
	private int cont_term;
	private int lockerroom_num;
	private boolean is_infoAllow;
	private String password;
	
	public MemberInfo(){}	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getEmail_addr() {
		return email_addr;
	}
	public void setEmail_addr(String email_addr) {
		this.email_addr = email_addr;
	}
	public String getPhone_unique_id() {
		return phone_unique_id;
	}
	public void setPhone_unique_id(String phone_unique_id) {
		this.phone_unique_id = phone_unique_id;
	}
	public String getIp_addr() {
		return ip_addr;
	}
	public void setIp_addr(String ip_addr) {
		this.ip_addr = ip_addr;
	}
	public boolean getisPT() {
		return isPT;
	}
	public void setPT(boolean isPT) {
		this.isPT = isPT;
	}
	public int getCont_term() {
		return cont_term;
	}
	public void setCont_term(int cont_term) {
		this.cont_term = cont_term;
	}
	public int getLockerroom_num() {
		return lockerroom_num;
	}
	public void setLockerroom_num(int lockerroom_num) {
		this.lockerroom_num = lockerroom_num;
	}
	public boolean getIs_infoAllow() {
		return is_infoAllow;
	}
	public void setIs_infoAllow(boolean is_infoAllow) {
		this.is_infoAllow = is_infoAllow;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
