package dept.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dept.dto.Dept;
import mybatis.MybatisConnectionFactory;

public class DeptDaoImpl implements DeptDao {
	
	private SqlSessionFactory sqlSessionFactory = MybatisConnectionFactory.getSqlSessionFactory();
	

	@Override
	public List<Dept> selectAll() {
		SqlSession sqlSession=sqlSessionFactory.openSession(true);
		//DB조회결과
		List<Dept> deptList=sqlSession.selectList("dept.dao.DeptDao.selectAll");
		//마이바티스 객체 닫기
		sqlSession.close();
		return deptList;
	}


	@Override
	public Dept seletOne(String deptno) {
		//마이바티스 수행객체
		SqlSession sqlSession=sqlSessionFactory.openSession(true);
		//sql수행
		Dept dept=sqlSession.selectOne("dept.dao.DeptDao.selectOne",deptno);
		//마이바티스 객체 닫기
		sqlSession.close();
		//결과 반환
		return dept;
	}


	//새로운 부서 정보 삽입
	@Override
	public void insert(Dept dept) {
		//마이바티스 수행객체
		SqlSession sqlSession=sqlSessionFactory.openSession(true);
		//sqlsession.insert는 테이블의 영향받은 행 수를 반환한다
		int res=sqlSession.insert("dept.dao.DeptDao.insert",dept);
		
		if(res>0) {
			System.out.println("삽입 성공");
		}else {
			System.out.println("삽입 실패");
		}
		sqlSession.close();
		
	}

}
