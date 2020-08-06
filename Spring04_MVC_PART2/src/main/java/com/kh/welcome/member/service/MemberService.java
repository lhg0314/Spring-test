package com.kh.welcome.member.service;

import java.util.Map;

import com.kh.welcome.member.vo.Member;

public interface MemberService {
	
	public int insertMember(Member member);
	
	public Member selectMember(Map<String,Object> commendMap);
	public int updateMember(Member commendMap);
	
	public int deleteMember(Member mem);
	
	public int idCheck(String userId);
	
	public void mailSending(Member member,String urlPath);

	public void leavemailSending(Member member);

}
