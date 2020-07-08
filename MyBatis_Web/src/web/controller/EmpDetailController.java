package web.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.dto.Emp;
import web.service.EmpService;
import web.service.EmpServiceImpl;


@WebServlet("/emp/detail")
public class EmpDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpService empService=new EmpServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int empno=Integer.parseInt(req.getParameter("empno"));
		
		//사원번호 파라미터 값을 이용해 사원 조회
		Emp emp=empService.getEmpByEmpno(empno);
		
		//System.out.println("사원번호"+emp);
		//조회 결과를 req컨텍스트영역에 추가
		req.setAttribute("emp", emp);
		//view지정 및 forward
		req.getRequestDispatcher("/WEB-INF/views/emp/detail.jsp").forward(req, resp);
		
	}

}
