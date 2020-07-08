package web.dao;

import java.util.List;

import web.dto.Emp;

public interface EmpDao {

	public List<Emp> getList();

	public Emp getEmpByEmpno(int empno);

}
