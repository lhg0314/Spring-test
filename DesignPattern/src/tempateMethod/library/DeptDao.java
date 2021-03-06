package tempateMethod.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import none.library.dao.JDBCTemplate;
import none.library.vo.Dept;

public abstract class DeptDao {
	//부모클래스에서 기본적인 로직의 흐름을 만들고
	//그 기능의 일부를 추상 메서드로 만들어
	//자식클래스에서 기능을 변경할수 있도록 하는 패턴
	//OJDBC 
		private Connection conn = null;
		private PreparedStatement ps = null;
		private ResultSet rs = null;



		public void insertDeot(Dept dept) {
			Connection conn=getConnection();


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

			Connection conn=getConnection();

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
		
		
		public abstract Connection getConnection();

}
