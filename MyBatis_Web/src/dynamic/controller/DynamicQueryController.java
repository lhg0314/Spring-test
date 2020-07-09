package dynamic.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import dynamic.dao.DynamicQueryDao;
import mybatis.MybatisConnectionFactory;
import web.dto.Emp;


@WebServlet("/dynamic/query")
public class DynamicQueryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SqlSessionFactory sqlSessionFactory = MybatisConnectionFactory.getSqlSessionFactory();
	private SqlSession sqlSession;
	//DAO객체
	private DynamicQueryDao dynamicQueryDao;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		sqlSession=sqlSessionFactory.openSession(true);
		dynamicQueryDao=sqlSession.getMapper(DynamicQueryDao.class);
		
		//---------------------------------------------------------------
		
//		List list = dynamicQueryDao.select();
//		for(Object object : list) {
//			System.out.println(object);
//		}
//		
//		req.setAttribute("list", list);
//		req.getRequestDispatcher("/WEB-INF/views/dynamic/query.jsp").forward(req, resp);
//		
		//---------------------------------------------------------------
		
//		List res2=dynamicQueryDao.select2("S");
//		System.out.println(res2);
		
		//---------------------------------------------------------------
		
		Emp emp=new Emp();
		emp.setEmpno(7369);
		emp.setEname("KING");
		List res3=dynamicQueryDao.select3(emp);
		System.out.println(res3);
		
	}

}
