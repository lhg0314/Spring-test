package com.kh.welcome.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kh.welcome.member.service.MemberService;
import com.kh.welcome.member.vo.Member;

@Controller
@RequestMapping("member")
public class MemberController {
	
	//해당 메서드를 어떤 url에 매핑시킬지 설정
	//value속성에 url을 지정할수 있다. 단 지정할 속성이 value밖에 없다면 생략가능하다
	//
//	@RequestMapping(value="/join")
	//requestMapping을 class에 적용하면 해당클래스의 모든 메서드에 저굥되는
	//url을 지정할수 있다 이 url은 메서드에 붙는 url의 앞경로를 의미한다
	/*public String join() {
		//void:컨트롤러에서return type이 void라면 요청은 url과 동일한jsp를 찾아서 사용자에게 보내준다
		
		//string이 반환형이면 String으로 반환되는 문자열이 jsp의 경로가 된다
		return "/member/join";
	}*/
	@Autowired
	MemberService memberService;
	
	@RequestMapping(value = "/join",method = RequestMethod.GET)
	public ModelAndView join() {
		//ModelAndView : model객체에 값을 담고
		//viewName을 보내고싶은 jsp의 경로로 지정
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("/member/join");
		return mav;
	}
	@RequestMapping(value = "/login")
	public String login() {
		return "member/login";
	}
	
	@RequestMapping("/joinimpl")
	public ModelAndView joinImpl(@RequestParam Map<String,Object> commendMap
			,@ModelAttribute Member member) {
		//@RequestParam: http요청파라미터를 메서드의 파라미터로 전달받을때
		//타입을 자동으로 변환해준다 int로 지정했다면 String을 int로 변환
		//단 변경이 불가능한 경우에는 400응답코드를 반환한다
		
		//@ModelAttribute
		//vo에 http요청파라미터를 전달할때 사용하는 annotion
		//html 태그의 name속성값과vo의 필드변수명이 일치해야 mapping이 이루어진다
		
		
		ModelAndView mav=new ModelAndView();
//		System.out.println(member);
//		System.out.println(commendMap);
		int res=memberService.insertMember(member);
		
		if(res>0) {//회원가입성공
			mav.addObject("alertMsg","회원가입에 성공하였습니다");
			mav.addObject("url","/member/login");
			mav.setViewName("/common/result");
		}else {
			mav.addObject("alterMsg","회원가입에 실패하였습니다");
			mav.addObject("url","/member/join");
			mav.setViewName("/common/result");
			
		}
		return mav;
	}
	@RequestMapping("/loginimpl")
	public ModelAndView loginImpl(@RequestParam Map<String,Object> commendMap,HttpSession session,HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		Member res=memberService.selectMember(commendMap);
		System.out.println(res);
		if(res !=null) {
			session.setAttribute("logInInfo", res);
			mav.addObject("alertMsg","로그인에 성공하였습니다");
			mav.addObject("url","/member/mypage");
			
		}else {
			mav.addObject("alertMsg","로그인에 실패하였습니다");
			mav.addObject("url","/member/login");
		}
		mav.setViewName("/common/result");
		return mav;
		
	}
	
	@RequestMapping("/mypage")
	public void mypage() {
		
	}
	
	@RequestMapping("/modify")
	   public String modify(
	         Member member
	         ,HttpSession session
	         ,Model model) {
	      
		System.out.println(member);
	      Member sessionMember 
	      = (Member) session.getAttribute("logInInfo");
	      
	      member.setUserId(sessionMember.getUserId());
	      int res = memberService.updateMember(member);
	      
	      if(res > 0) {
	         model.addAttribute("alertMsg"
	               , "회원정보수정이 성공했습니다.");
	         model.addAttribute("url", "mypage.do");
	         
	         sessionMember.setPassword(member.getPassword());
	         sessionMember.setEmail(member.getEmail());
	         sessionMember.setTell(member.getTell());
	      }else {
	         model.addAttribute("alertMsg"
	               , "회원정보수정에 실패하였습니다.");
	         model.addAttribute("url", "mypage.do");
	      }
	      
	      return "common/result";
	   }
	   
	
	@RequestMapping("/leave")
	public ModelAndView leave(HttpSession session,HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		Member mem=(Member)session.getAttribute("logInInfo");
		int res=memberService.deleteMember(mem);
		String urlpath=req.getServerName()+":"+req.getServerPort()+req.getContextPath();
		
		if(res>0) {//탈퇴성공
			session.removeAttribute("logInInfo");
			memberService.leavemailSending(mem);
			mav.addObject("alertMsg","탈퇴 이메일이 전송 되었습니다");
			mav.addObject("url","/member/login");
			mav.setViewName("/common/result");
		}else {
			mav.addObject("alterMsg","탈퇴에 실패하였습니다");
			mav.addObject("url","/member/mypage");
			mav.setViewName("/common/result");
			
		}
		return mav;
	}
	
	@RequestMapping("/idcheck")
	@ResponseBody
	//메소드에서 리턴하는 값을 viewResolver를 거쳐서 출력히지 않고
	//http Response Body에 직접쓰게된다
	public String idCheck(String userId) {
		System.out.println(userId);
		System.out.println("idCheck메서드가 호출되었습니다");
		int res=memberService.idCheck(userId);
		if(res>0) {//중복되는 아이디가 있을때
			return userId;
			
		}else {//사용가능할때
			return "";
		}
	}
	@RequestMapping("/joinemailcheck")
	public ModelAndView joinEmailCheck(Member member,HttpServletRequest req) {
		ModelAndView mav=new ModelAndView();
		String urlpath=req.getServerName()+":"+req.getServerPort()+req.getContextPath();
		memberService.mailSending(member, urlpath);
		
		mav.addObject("alertMsg","이메일로 확인메일이 발송되었습니다");
		mav.addObject("url","login");
		mav.setViewName("common/result");
		
		
		
		
		return mav;
	}
	
	

}
