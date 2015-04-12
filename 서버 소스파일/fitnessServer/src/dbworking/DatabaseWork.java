package dbworking;

import java.sql.Connection;

public abstract class DatabaseWork {
	protected String url ;	//DBMS 접속 url
	protected String dbId ;		//DBMS 접속 id
	protected String passwd;		//DBMS 접속 password 
	protected Connection conn;		//DBMS 연결 객체
	
	public abstract boolean dbConnect();	//DBMS 접속 함수
	public abstract boolean disConnect();	//DBMA 접속 해제 함수
}
