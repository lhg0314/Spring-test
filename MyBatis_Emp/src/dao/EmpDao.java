package dao;

import java.util.List;

import dto.Emp;

public interface EmpDao {

	//전체조회
	public List<Emp> selectAll();

	//사원 삽입
	public void insertEmp(Emp emp);

	//사원번호로 사원정보 조회
	public Emp selectByEmpNo(Emp emp);

	//사원번호로 사원정보 삭제
	public void deleteByEmpNo(Emp emp);

}
