package dept.dao;

import java.util.List;

import dept.dto.Dept;

public interface DeptDao {
	
	//전체 조회
	public List<Dept> selectAll();

	public Dept seletOne(String deptno);

	public void insert(Dept dept);

}
