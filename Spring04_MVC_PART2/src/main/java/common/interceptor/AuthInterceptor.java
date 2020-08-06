package common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

//handler를 구현해 인터페이스로 해당클래스를 활용
public class AuthInterceptor implements HandlerInterceptor{
	//interceptor
	//dispathcherSerlvet이 controller의 메서드를 호출할 때
	//중간에서 요청을 가로채서 필요한 선작업을 할수있다
	
	//filter
	//Servlet Contaoner가 Servlet을 호출하기 전에
	//요청을 가로채는 역할
	//servlet container >filter > dispatcher-Servlet
	//interceptor >controller
	
	@Override
	//컨트롤러로 요청이 가기전에 실행되는 메서드
	//return true:컨트롤러의 메소드가 실행되지 않음
	//return false:컨트롤러의 메서드가 실행되지 않음
	//Object handler : preHandle을 수행하고 수행될 컨트롤러 메서드에 대한 정보
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getRequestURI().contains("notice/")
				&& request.getSession().getAttribute("logInInfo")==null) {
			request.setAttribute("alertMsg", "비회원은 접근할 권한이 없습니다");
			request.setAttribute("url", request.getContextPath()+"/member/login");
			RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/common/result.jsp");
			rd.forward(request, response);
			return false;
		}
		return true;
	}
}
