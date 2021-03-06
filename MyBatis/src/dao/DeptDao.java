package dao;

import java.util.List;

import dto.Dept;

public interface DeptDao {
	
	public Dept selectByDeptno(int deptno);
	
	public List<Dept> selectAll();
	
	public Dept selectByDname(String str);
	
	public Dept selectByDept(Dept dept);
	
	public void insert(Dept dept);

}
