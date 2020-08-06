package strategy.client;

import java.util.Scanner;

import none.library.vo.Dept;
import strategy.library.DeptDao;


public class Run {
	public static void main(String[] args) {
Scanner sc=new Scanner(System.in);
		
		ClientDeptDao deptDao=new ClientDeptDao();
		Dept dept = new Dept();
		
		System.out.println("==========부서정보 등록==========");
		System.out.println("부서번호를 입력하세오");
		dept.setDeptno(sc.nextInt());
		System.out.println("부서이름을 입력하세요");
		sc.nextLine();
		dept.setDname(sc.next());
		System.out.println("부서지역을 입력하세요");
		dept.setLoc(sc.next());
		
		deptDao.insertDeot(dept);
		Dept res=deptDao.selectDept(dept.getDeptno());
		
		System.out.println(res);
	}
	

}
