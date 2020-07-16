package web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import web.dto.Dept;


public interface DeptService {

	//부서정보 전체조회
	public List<Dept> getList();
	
	

}
