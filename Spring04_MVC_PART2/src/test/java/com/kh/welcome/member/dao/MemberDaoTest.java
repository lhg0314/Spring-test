package com.kh.welcome.member.dao;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.kh.welcome.member.vo.Member;

@WebAppConfiguration
//프로젝트의 web.xml이 아니라 테스트를 위해
//자동으로 생성되는 가상의 web.xml을 사용하겠다는 의미
@RunWith(SpringJUnit4ClassRunner.class)
//runwith : junit프레임워크의 테스트 실행 방법을 변경할때 지정
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*-context.xml"})
//자동으로 생성되는 applicationContext의 설정파일 위치를 지정
public class MemberDaoTest {
	//SpringJunit4ClassRunner 를 사용해 만든 applicationContext로 부터 
	//의존성 주입을 받음
	
	@Autowired
	MemberDao memberDao;
	
	@Test
	public void test1() {
		assertThat(memberDao,is(notNullValue()));
	}
	
	@Test
	//데이터베이스에 존재하는 특정 회원을 조회
	//예상값과 결과르 반환되는 memeber객체의 userId값이  일치하는지 확인
	//equalTo()
	public void test2() {
		Map<String,Object> commandMap=new HashMap<String, Object>();
		commandMap.put("id","user2");
		Member member=memberDao.selectMember(commandMap);
		//실패 케이스
//		assertThat(member.getUserId(),equalTo(commandMap.get("pw")));
		assertThat(member.getUserId(),equalTo(commandMap.get("id")));
		
	}
	
	@Test
	public void test3() {
		Map<String,Object> commandMap=new HashMap<String, Object>();
		commandMap.put("id","user4");
		Member member=memberDao.selectMember(commandMap);

		//결과값이 null이면 통과 아니면 실패
		assertThat(member,is(nullValue()));
	}

}
