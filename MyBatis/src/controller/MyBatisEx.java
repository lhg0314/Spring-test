package controller;

import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dao.DeptDao;
import dto.Dept;
import mybatis.MyBatisConnectionFactory;

public class MyBatisEx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//마이바티스 수행객체
		SqlSession sqlSession;
		
		//DB접속및 SqlSession생성 팩토리 객체
		SqlSessionFactory factory=MyBatisConnectionFactory.getSqlSessionFactory();

		//마이바티스 수행 객체 생성
		//sqlSession=factory.openSession();
		sqlSession=factory.openSession(true);//true면 autoCommit
		
		//----------------------------------------------------------------------------------
		
		//마이바티스의 매퍼와 자바프로그램의 DAO인터페이스 매핑(연결)
		DeptDao deptDao=sqlSession.getMapper(DeptDao.class);
		
		//----------------------------------------------------------------------------------
		
		Scanner sc=new Scanner(System.in);
		System.out.println(">>조회할 부서번호는?");
		Dept res=deptDao.selectByDeptno(sc.nextInt());
		System.out.println(res);
		
		System.out.println("-----------------------------------------------------------------");
		
//		List<Dept> list=deptDao.selectAll();
//		
//		for(Dept dept:list) {
//			System.out.println(dept);
//		}
		
//		System.out.println("------------------------------부서명을 이용한 조회-----------------------------------");
//		sc.nextLine();
//		System.out.println(">>부서명 입력");
//		String str=sc.nextLine();
//		Dept res2=deptDao.selectByDname(str);
//		System.out.println(res2);
		
//		System.out.println("------------------------------부서명을 이용한 조회-----------------------------------");
//		
//		Dept data=new Dept();
//		data.setDname("RESEARCH");
//		
//		Dept res3=deptDao.selectByDept(data);
//		System.out.println(res3);
//		
		System.out.println("------------------------------부서정보 삽입-----------------------------------");
		Dept insertDept=new Dept();
		System.out.println(">>부서번호를 입력하세요");
		insertDept.setDeptno(sc.nextInt());
		sc.nextLine();
		System.out.println(">>부서이름을 입력하세오");
		insertDept.setDname(sc.nextLine());
		System.out.println(">>부서지역을 입력하세요");
		insertDept.setLoc(sc.nextLine());
		deptDao.insert(insertDept);
		
		//트랜잭션 커밋
		//sqlSession.commit();
		
		
		
	}

}
