package dept.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.dao.DeptDao;
import dept.dao.DeptDaoImpl;
import dept.dto.Dept;


@WebServlet("/dept/list")
public class DeptListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptDao deptDao=new DeptDaoImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//System.out.println("/dept/list[GET]");
		List<Dept> deptList =deptDao.selectAll();
		
		//Model값 전달
		
		req.setAttribute("deptlist", deptList);
		
		req.getRequestDispatcher("/WEB-INF/views/dept/list.jsp").forward(req, resp);
	}

}
