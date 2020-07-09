package selectKey.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import mybatis.MybatisConnectionFactory;
import selectKey.dao.SelectKeyDao;
import selectKey.dto.TestMember;


@WebServlet("/mybatis/selectKey")
public class SelectKeyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static SqlSessionFactory sqlSessionFactory = MybatisConnectionFactory.getSqlSessionFactory();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/loginform.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SqlSession sqlSession = sqlSessionFactory.openSession(true);
		SelectKeyDao selectKey=sqlSession.getMapper(SelectKeyDao.class);
		
		req.setCharacterEncoding("UTF-8");
		
		String id=req.getParameter("id");
		String pw=req.getParameter("pw");
		
		TestMember mem=new TestMember();
		mem.setId(id);
		mem.setPw(pw);
		
		selectKey.insert(mem);
		
		req.setAttribute("mem", mem);
		req.getRequestDispatcher("/WEB-INF/views/loginformresult.jsp").forward(req, resp);
		
		
		
		
		
	}

}
