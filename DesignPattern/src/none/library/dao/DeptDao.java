package none.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import none.library.vo.Dept;

public class DeptDao {

	//OJDBC 
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;



	public void insertDeot(Dept dept) {
		Connection conn=JDBCTemplate.getConnection();


		try {

			

			String sql="INSERT INTO dept(deptno,dname,loc)";
			sql+=" VALUES(?,?,?)";
			ps=conn.prepareStatement(sql);

			ps.setInt(1, dept.getDeptno());
			ps.setString(2, dept.getDname());
			ps.setString(3, dept.getLoc());

			ps.executeUpdate();




		}  catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			JDBCTemplate.close(ps);
		}
	}

	public Dept selectDept(int deptno) {

		Connection conn=JDBCTemplate.getConnection();

		Dept dept=null;
		try {

			String sql="SELECT * FROM dept";
			sql+=" WHERE deptno= ?";
			ps=conn.prepareStatement(sql);

			ps.setInt(1, deptno);

			rs=ps.executeQuery();

			while(rs.next()) {
				dept=new Dept();
				dept.setDeptno(rs.getInt("deptno"));
				dept.setDname(rs.getString("dname"));
				dept.setLoc(rs.getString("loc"));
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {

			JDBCTemplate.close(rs);
			JDBCTemplate.close(ps);

		}
		return dept;
	}


//	public Connection getConnection() {
//		Connection conn=null;
//
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","scott","tiger");
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return conn;
//	}

}
