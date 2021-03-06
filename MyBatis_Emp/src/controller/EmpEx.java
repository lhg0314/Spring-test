package controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import dao.EmpDao;
import dto.Emp;
import mybatis.MybatisConnectionFactory;

public class EmpEx {

	public static void main(String[] args) {

		SqlSession sqlSession;


		SqlSessionFactory factory=MybatisConnectionFactory.getSqlSessionFactory();


		sqlSession=factory.openSession(true);//true면 autoCommit

		EmpDao empDao=sqlSession.getMapper(EmpDao.class);
		
//		sqlSession.selectOne();
//		sqlSession.selectList();
//		sqlSession.selectMap();
//		
//		sqlSession.insert();
//		sqlSession.update();
//		sqlSession.delete();
		
		sqlSession.commit();
		sqlSession.rollback();
		
//		sqlSession.close();

		List<Emp> list=empDao.selectAll(); //전체 출력,selectList
		
		for(Emp emp:list) {
			System.out.println(emp);
	}
		
		Emp emp=new Emp();
		emp.setEmpno(346);
		emp.setEname("json");
		emp.setJob("CLERK");
		emp.setMgr(7782);
		emp.setSal(790);
		emp.setComm(500);
		emp.setDeptno(10);
		Emp newemp=new Emp();
		//empDao.insertEmp(emp);
		newemp=empDao.selectByEmpNo(emp); //selectOne
		System.out.println("-----------부서번호로 출력하기--------");
		System.out.println(newemp);
		empDao.deleteByEmpNo(emp);
		

	}

}
