package dept.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.dao.DeptDao;
import dept.dao.DeptDaoImpl;
import dept.dto.Dept;


@WebServlet("/dept/insert")
public class DeptInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptDao deptDao=new DeptDaoImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/dept/insert.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Dept dept=new Dept();
		dept.setDeptno(Integer.parseInt(req.getParameter("deptno")));
		dept.setDname(req.getParameter("dname"));
		dept.setLoc(req.getParameter("loc"));
		
		//System.out.println(dept);
		//데이터 삽입
		deptDao.insert(dept);
		//목록페이지로 리다이렉션
		resp.sendRedirect("/dept/list");
		
		
	}

}
