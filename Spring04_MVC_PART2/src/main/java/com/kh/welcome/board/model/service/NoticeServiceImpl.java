package com.kh.welcome.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kh.welcome.board.model.dao.NoticeDao;
import com.kh.welcome.board.model.vo.Notice;

import common.util.FileUtil;
import common.util.Paging;

@Service
public class NoticeServiceImpl implements NoticeService{
	@Autowired
	NoticeDao noticeDao;
//	@Transactional//스프링에게 트랜잭션관리를 위임하는 어노테이션
	//메소드 또는 클래스에 작성할수 있다
	//Class에 지정할 경우,해당클래스의 모든 메서드가
	//스프링에게 트랜잭션 관리를 위임하게 된다
	public int insertNotice(Notice notice,List<MultipartFile> files,String root) {
		int res=noticeDao.insertNotice(notice);
		//에러발생을 위한 코드
		int errNum=10/0;
		if(!(files.size()==1 &&files.get(0).getOriginalFilename().equals(""))) {//첨부파일이 있을때
			//파일업로드를 위해FileUtil.fileUpload()호출
			List<Map<String,String>> filedata=new FileUtil().fileUpload(files, root);
			for (Map<String, String> map : filedata) {
				noticeDao.insertFile(map);
			}
		}
		return res;
		
	}
	
	public int insertFile(Map<String,String> fileInfo) {
		return noticeDao.insertFile(fileInfo);
	}
	
	public void updateNotice(Notice notice,List<MultipartFile> files,String root) {
		int res=noticeDao.updateBoard(notice);//게시물 업데이트
		if(!(files.size()==1 &&files.get(0).getOriginalFilename().equals(""))) {//첨부파일이 있을때
			List<Map<String,String>> filedata=new FileUtil().fileUpload(files, root);
			for (Map<String, String> map : filedata) {
				map.put("nIdx", notice.getnIdx()+"");
				noticeDao.insertFile2(map);
			}
		}
	}
	
												//현재 페이지 //페이지당 노출할 게시글 수
	public Map<String,Object> selectNoticeList(int curPage,int cntPerPage){
		
		Map<String,Object> commandMap=new HashMap<String, Object>();
		
		Paging p=new Paging(noticeDao.selectContentCnt(),curPage,cntPerPage);
		//현재페이지에 필요한 게시물 목록
		List<Notice> nlist=noticeDao.selectNoticeList(p);
		commandMap.put("nlist", nlist);
		commandMap.put("paging", p);
		return commandMap;
	}
	
	//게시물 상세
	public Map<String,Object> selectNoticeDetail(int nIdx){
		Map<String,Object> commendMap=new HashMap<String, Object>();
		
		Notice notice=noticeDao.selectNoticeDetail(nIdx);
		List<Map<String,String>> flist=noticeDao.selectFileWithNotice(nIdx);
		commendMap.put("notice", notice);
		commendMap.put("flist", flist);
		return commendMap;
	}
	public Map<Object,Object>selectFile(int fIdx){
		Map<Object,Object> commandMap=noticeDao.selectFile(fIdx);
		System.out.println(commandMap);
		return commandMap;
	}
	public void deleteFile(int nIdx) {
		int res=noticeDao.deleteFile(nIdx);
		System.out.println(nIdx);
		System.out.println(res);
		
	}
	
	public void deleteFile2(int fIdx) {
		int res=noticeDao.deleteFile2(fIdx);
		System.out.println(fIdx);
		System.out.println(res);
		
	}
	
	public void deleteNotice(int nIdx) {
		noticeDao.deleteNotice(nIdx);
	}
	

}
