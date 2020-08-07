package com.kh.welcome.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import common.exception.CustomException;
import common.exception.ErrorCode;

@ControllerAdvice(basePackages = {"com.kh.welcome"})//지정한 패키지내의 모든 클래스가 @ExceptionHandler 이 핸들러를 공유한다 모든 에러가 여기로 날아온다
@Controller
public class ExceptionController {
	
	@ExceptionHandler(CustomException.class)
	public ModelAndView ex(CustomException e) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("alertMsg",ErrorCode.valueOf(e.getMessage()).getMessage());
		mav.addObject("url",ErrorCode.valueOf(e.getMessage()).getURL());
		mav.setViewName("common/result");
		return mav;
	}
	
	
	@ExceptionHandler(Exception.class)//모든 예외처리는 여기에서 처리된다
	//예외처리를 일괄적으로 할수 있게 도와주는 어노테이션
	//controller에서만 사용가능하다
	public ModelAndView ex(Exception e) {
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("alertMsg","에러가 발생했습니다");
		mav.addObject("url","login");
		mav.setViewName("common/result");
		return mav;
		
	}

}
