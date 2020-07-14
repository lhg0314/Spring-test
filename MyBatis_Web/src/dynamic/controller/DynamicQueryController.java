package dynamic.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.tribes.util.Arrays;
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
//		
//		Emp emp=new Emp();
//		emp.setEmpno(7369);
//		emp.setEname("KING");
//		List res3=dynamicQueryDao.select3(emp);
//		System.out.println(res3);
		
		//---------------------------------------------------------------
		
//		HashMap<String,Object> map=new HashMap<>();
//		map.put("cate","ename");
//		map.put("keyword","SMITH");
//		
//		List<Emp> res4=dynamicQueryDao.select4(map);
//		
//		System.out.println(res4);
//		
//		System.out.println("----------");
//		
//		
//		map.put("cate","job");
//		map.put("keyword","SALESMAN");
//		
//		List<Emp> res5=dynamicQueryDao.select4(map);
//		
//		System.out.println(res5);
		
		//---------------------------------------------------------------
		
		req.getRequestDispatcher("/WEB-INF/views/dynamic/queryCheckbox.jsp").forward(req, resp);
		
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		sqlSession=sqlSessionFactory.openSession(true);
		dynamicQueryDao=sqlSession.getMapper(DynamicQueryDao.class);
		//전달 파라미터가 하나의 값일때만 getParameter를 써야함
//		String data=req.getParameter("deptno");
//		System.out.println(data);//전달된 데ㅣ터 중 한 개반 꺼내옴
		//---------------------------------------------------------------
		
		String[] data=req.getParameterValues("deptno");
		System.out.println(Arrays.toString(data));
		
		HashMap<String,Object>map=new HashMap<>();
		
		map.put("deptnoArr",data);
		
		List<Emp> list=dynamicQueryDao.selectCheckbox(map);
		
		for (Emp emp : list) {
			System.out.println(emp);
		}
		
		sqlSession.clearCache();
		req.setAttribute("list",list);
		req.getRequestDispatcher("/WEB-INF/views/dynamic/resultCheckbox.jsp").forward(req, resp);
		
			
	}

}
