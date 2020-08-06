package none.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCTemplate {
		// OJDBC 드라이버 
		public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
		
		//DB 연결 정보
		public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
		public static final String ID = "scott";
		public static final String PW = "tiger";
		
		//OJDBC 객체
		public static Connection conn = null;
		
		//private 생성자 - 외부에서 객체 생성하는 걸 막는 용도
		private JDBCTemplate() {}
		
		// Conncetion 객체 반환 - 싱글톤 패턴 적용 메소드
		public static Connection getConnection() {
			
			if(conn == null) { // 한번 생성된 객체를 유지하게 만듦 
				try {
					//드라이버 로드
					Class.forName(DRIVER);
					
					//디비 연결
					conn = DriverManager.getConnection(URL,ID,PW);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return conn; //DB연결 객체를 반환한다
			
		}
		
		// 커밋 수행 메소드   ****시험****
		public static void commit(Connection conn) {
			
			try {
				
				if(conn != null && !conn.isClosed()) {
					conn.commit();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		//rollback 수행 메소드
		public static void rollback(Connection conn) {
			
			try {
				if(conn != null && !conn.isClosed()) {
					conn.rollback();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//DB 연결 해제
		public static void close(Connection conn) {
			
			try {
				if(conn != null && !conn.isClosed()) {
					conn.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		//sql 수행객체 해제
		public static void close(PreparedStatement ps) {
			
			try {
				if(ps != null && !ps.isClosed()) {
					ps.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		
		//쿼리결과 객체 해제
		public static void close(ResultSet rs) {
			try {
				if(rs != null && !rs.isClosed()) {
					rs.close();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}