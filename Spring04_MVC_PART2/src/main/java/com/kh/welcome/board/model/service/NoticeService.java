package com.kh.welcome.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.kh.welcome.board.model.vo.Notice;

import common.exception.FileException;

public interface NoticeService {
	
	public int insertNotice(Notice notice,List<MultipartFile> files,String root) throws FileException;
	
	public int insertFile(Map<String,String> fileInfo);
	
	public void updateNotice(Notice notice,List<MultipartFile> files,String root) throws FileException;
	
												//현재 페이지 //페이지당 노출할 게시글 수
	public Map<String,Object> selectNoticeList(int curPage,int cntPerPage);
	
	//게시물 상세
	public Map<String,Object> selectNoticeDetail(int nIdx);
	public Map<Object,Object>selectFile(int fIdx);
	public void deleteFile(int nIdx);
	
	public void deleteFile2(int fIdx);
	
	public void deleteNotice(int nIdx);

}
