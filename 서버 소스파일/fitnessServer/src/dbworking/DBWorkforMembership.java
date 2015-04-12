package dbworking;

import info.MemberInfo;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;

public class DBWorkforMembership extends DatabaseWork {
	
	public DBWorkforMembership(){
		this.url = "jdbc:mysql://localhost:3306/fitness_member";
		this.dbId = "root";
		this.passwd = "tjrwls";
		try {
			Class.forName("com.mysql.jdbc.Driver");		//mysql 드라이버 로드
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean dbConnect() {
		boolean result = false;
		try {
			this.conn = DriverManager.getConnection(this.url, this.dbId, this.passwd);		// DBMS �젒�냽
			if(conn.isClosed()==false)		//isClosed 호출해서 false를 받으면 연결 끊음.
				result = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}	//DBMS 접속 함수
	
	public boolean disConnect() {
		boolean result = true;
		
		try {
			this.conn.close();		//DBMS 연결 해제
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = false;
			e.printStackTrace();
		}
		
		return result;
	}	//DBMA 접속 해제 함수
	
	public int getTotalMemberCnt(){
		int num = 0;
		String sql = "select count(name) from member_info";
		Statement smt = null;
		ResultSet rs = null;
		
		try {
			smt = this.conn.createStatement();
			rs = smt.executeQuery(sql);
			if(rs.first()){
				num = rs.getInt("count(name)");
			}
			
			rs.close();
			smt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return num;
	}
	
	public boolean tryLogin(String name, String password){
		System.out.println("tryLogin in param - "+ name + ", "+password);
		boolean result = false;
		String sql = "select name, password from member_info where name=? and password=?";
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			psmt = this.conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setString(2, password);
			
			rs = psmt.executeQuery();
			if(rs.first())
				result = true;
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public ArrayList<MemberInfo> showAllMemberList(Integer startIdx, Integer cnt){
		System.out.println("showMemberlist call");
		ArrayList<MemberInfo> memberlist = new ArrayList<MemberInfo>();
		String sql = "select * from member_info where isAdmin=0 limit "+startIdx+", "+cnt;
		
		Statement smt = null;
		ResultSet rs = null;
		
		try {
			smt = this.conn.createStatement();
			rs = smt.executeQuery(sql);
			
			while(rs.next()){
				MemberInfo mI = new MemberInfo();
				mI.setAge(rs.getInt("age"));
				mI.setCont_term(rs.getInt("cont_term"));
				mI.setEmail_addr(rs.getString("email_addr"));
				mI.setIp_addr(rs.getString("ip_addr"));
				mI.setIs_infoAllow(rs.getBoolean("is_infoAllow"));
				mI.setLockerroom_num(rs.getInt("lockerroom_num"));
				mI.setName(rs.getString("name"));
				mI.setPhone_num(rs.getString("phone_num"));
				mI.setPT(rs.getBoolean("isPT"));
				memberlist.add(mI);
				System.out.println("name - "+mI.getName()+", "+rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				smt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return memberlist;
	}
	
	public ArrayList<MemberInfo> searchedMemberList(String name, Integer startIdx, Integer cnt){
		System.out.println("showMemberlist call");
		ArrayList<MemberInfo> memberlist = new ArrayList<MemberInfo>();
		String sql = "SELECT * FROM member_info WHERE name LIKE ? limit "+startIdx+", "+cnt+";";
		
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		try {
			psmt = this.conn.prepareStatement(sql);
			psmt.setString(1, "%"+name+"%");
			rs = psmt.executeQuery();
			
			while(rs.next()){
				MemberInfo mI = new MemberInfo();
				mI.setAge(rs.getInt("age"));
				mI.setCont_term(rs.getInt("cont_term"));
				mI.setEmail_addr(rs.getString("email_addr"));
				mI.setIp_addr(rs.getString("ip_addr"));
				mI.setIs_infoAllow(rs.getBoolean("is_infoAllow"));
				mI.setLockerroom_num(rs.getInt("lockerroom_num"));
				mI.setName(rs.getString("name"));
				mI.setPhone_num(rs.getString("phone_num"));
				mI.setPT(rs.getBoolean("isPT"));
				memberlist.add(mI);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				rs.close();
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return memberlist;
	} 
	
	public boolean insertMember(String name, Integer age, String phone, String email, Boolean PT, Integer contterm, Integer lockerRoomNum, Boolean isInfoAllow){
		boolean result = false;
		String sql = "insert into member_info(name, age, phone_num, email_addr, isPT, cont_term, lockerroom_num, is_infoAllow, isAdmin) values(?,?,?,?,?,?,?,?,0);";
		
		PreparedStatement psmt = null;
		
		try {
			psmt = this.conn.prepareStatement(sql);
			psmt.setString(1, name);
			psmt.setInt(2, age);
			psmt.setString(3, phone);
			psmt.setString(4, email);
			psmt.setBoolean(5, PT);
			psmt.setInt(6, contterm);
			psmt.setInt(7, lockerRoomNum);
			psmt.setBoolean(8, isInfoAllow);
			
			if(psmt.executeUpdate()>0)
				result = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		return result;
	}
	
	public boolean updateMemberInfo(String phone){
		boolean result = false;
		
		return result;
	}
	
	public boolean deleteMember(String phoneNum){
		System.out.println("in deleteMember");
		boolean result = false;
		String sql = "delete from member_info where phone_num=?";
		PreparedStatement psmt = null;
		
		try {
			psmt = this.conn.prepareStatement(sql);
			psmt.setString(1, phoneNum);
			if(psmt.executeUpdate()>0)
				result = true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				psmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
}
