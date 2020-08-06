package com.kh.welcome.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.welcome.board.model.vo.Notice;

import common.util.Paging;

@Repository
public class NoticeDao {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	public int insertNotice(Notice notice) {
		return sqlSession.insert("Notice.insertNotice",notice);
	}
	
	public int insertFile(Map<String,String> fileInfo) {
		return sqlSession.insert("Notice.insertFile",fileInfo);
	}
	public List<Notice> selectNoticeList(Paging page){
		return sqlSession.selectList("Notice.selectNoticeList",page);
	}
	
	public int selectContentCnt() {
		return sqlSession.selectOne("Notice.selectContentCnt");
	}
	
	public Notice selectNoticeDetail(int nIdx) {
		return sqlSession.selectOne("Notice.selectNoticeDetail",nIdx);
	}
	
	public List<Map<String,String>> selectFileWithNotice(int nIdx){
		return sqlSession.selectList("Notice.selectFileWithNotice",nIdx);
	}
	
	public Map<Object,Object> selectFile(int fIdx){
		return sqlSession.selectOne("Notice.selectFile",fIdx);
	}
	
	public int deleteFile(int fIdx) {
		return sqlSession.delete("Notice.deleteFile",fIdx);
	}
	
	public int updateBoard(Notice notice) {
		return sqlSession.update("Notice.updateBoard",notice);
	}
	public int insertFile2(Map<String,String> fileInfo) {
		return sqlSession.insert("Notice.insertFile2",fileInfo);
	}
	
	public int deleteNotice(int nIdx) {//게시물 삭제
		return sqlSession.delete("Notice.deleteNotice",nIdx);
	}
	
	public int deleteFile2(int fIdx) {
		return sqlSession.delete("Notice.deleteFile2",fIdx);
	}

}
