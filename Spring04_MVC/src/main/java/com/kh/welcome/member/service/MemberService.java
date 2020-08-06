package com.kh.welcome.member.service;

import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import com.kh.welcome.member.dao.MemberDao;
import com.kh.welcome.member.vo.Member;

//@service : bean츠로 등록시켜주는 기능외에는 별다른 기능이 없다
//component롸 동일 단 가독성을 위해 service 어노테이션을 사용한다
@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	JavaMailSender mailSender;
	
	public int insertMember(Member member) {
		return memberDao.insertMember(member);
	}
	
	public Member selectMember(Map<String,Object> commendMap) {
		return memberDao.selectMember(commendMap);
	}
	public int updateMember(Member commendMap) {
		return memberDao.updateMember(commendMap);
	}
	
	public int deleteMember(Member mem) {
		return memberDao.deleteMember(mem);
	}
	
	public int idCheck(String userId) {
		return memberDao.selectId(userId);
	}
	
	public void mailSending(Member member,String urlPath) {
		
		String setfrom="lee1009522@naver.com";
		String tomail=member.getEmail();
		String title="회원가입을 환영합니다";
		String htmlBody="<form "
		         + "action='http://"+urlPath+"/member/joinimpl'"
		         +" method='post'>"
		         + "<h3>회원가입을 환영합니다</h3>"
		         + "<input type='hidden' value='" 
		               + member.getUserId() 
		               + "' name='userId'>"
		         + "<input type='hidden' value='"
		               + member.getPassword()
		               +"' name='password'>"
		         + "<input type='hidden' value='"
		               + member.getTell()
		               +"' name='tell'>"
		         + "<input type='hidden' value='"
		               + member.getEmail()
		               +"' name='email'>"
		         + "<button type='submit'>전송하기</button></form>";
		
		mailSender.send(new MimeMessagePreparator() {
			   public void prepare(MimeMessage mimeMessage) throws MessagingException {
			     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			     message.setFrom(setfrom);//보내는 사람
			     message.setTo(tomail);//받는 사람
			     message.setSubject(title);//메일 제목
			     message.setText(htmlBody, true);//메일내용
			     
			   }
			 });
	}

	public void leavemailSending(Member member) {
		String setfrom="lee1009522@naver.com";
		String tomail=member.getEmail();
		String title="회원탈퇴 되었습니다";
		String htmlBody="<h1>회원탈퇴 처리가 완료되었습니다</h1>";
		
		mailSender.send(new MimeMessagePreparator() {
			   public void prepare(MimeMessage mimeMessage) throws MessagingException {
			     MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			     message.setFrom(setfrom);//보내는 사람
			     message.setTo(tomail);//받는 사람
			     message.setSubject(title);//메일 제목
			     message.setText(htmlBody, true);//메일내용
			     
			   }
			 });
		
	}

}
