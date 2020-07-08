package web.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import mybatis.MybatisConnectionFactory;
import web.dao.EmpDao;
import web.dto.Emp;

public class EmpServiceImpl implements EmpService {
	
	//마이바티스 연결 객체 
	private SqlSessionFactory factory=MybatisConnectionFactory.getSqlSessionFactory();
	//DAO객체
	private EmpDao empDao;

	@Override
	public List<Emp> getList() {
		SqlSession sqlSession=factory.openSession();
		empDao=sqlSession.getMapper(EmpDao.class);
		
		List<Emp> list=empDao.getList();
		return list;
	}

	@Override
	public Emp getEmpByEmpno(int empno) {
		SqlSession sqlSession=factory.openSession();
		empDao=sqlSession.getMapper(EmpDao.class);
		
		Emp emp=empDao.getEmpByEmpno(empno);
		return emp;
	}

}
