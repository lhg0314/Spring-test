package com.kh.welcome.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.welcome.member.vo.Member;

//repository: dao역할을하는 Bean으로 등록한다
//sqlExeption을 DataAccessException으로 전환한다
//select 쿼리
//insert 쿼리 sqlSessionTemplate.insert(네임스페이스.태그id,매핑시킬 객체)
//update 쿼리 sqlSessionTemplate.update(네임스페이스.태그id,매핑시킬 객체)
//delete 쿼리 sqlSessionTemplate.delete(네임스페이스.태그id,매핑시킬 객체)
//매핑시킬 객체가 필요하지 않으면 두번째 매개변수를 생략

//select쿼리 1.결과가 하나인 경우-selectOne
//		   2.결과가 여러개인 경우-selectList
@Repository
public class MemberDao {
	@Autowired
	SqlSessionTemplate session;
	
	public int insertMember(Member member) {
		//MEMBER.insertMember :Mapper의 NameSpace이름.태그의 id속성값
		return session.insert("MEMBER.insertMember",member);
	}
	
	public Member selectMember(Map<String,Object> commandMap) {
		return session.selectOne("MEMBER.selectMember",commandMap);
	}
	
	public int updateMember(Member commendmap) {
		return session.update("MEMBER.updateMember", commendmap);
	}
	
	public int deleteMember(Member mem) {
		return session.delete("MEMBER.deleteMember",mem);
	}
	
	public int selectId(String userId) {
		return session.selectOne("MEMBER.selectId",userId);
	}
	
}
