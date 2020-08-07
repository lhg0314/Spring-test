package com.kh.welcome.member.service;

import java.util.Map;

import com.kh.welcome.member.vo.Member;

import common.exception.MailException;

public interface MemberService {
	
	public int insertMember(Member member);
	
	public Member selectMember(Map<String,Object> commendMap);
	public int updateMember(Member commendMap);
	
	public int deleteMember(Member mem);
	
	public int idCheck(String userId);
	
	public void mailSending (Member member,String urlPath) throws MailException;

	public void leavemailSending(Member member);

}
