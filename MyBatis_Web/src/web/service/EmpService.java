package web.service;

import java.util.List;

import web.dto.Emp;

public interface EmpService {

	public List<Emp> getList();

	public Emp getEmpByEmpno(int empno);

}
