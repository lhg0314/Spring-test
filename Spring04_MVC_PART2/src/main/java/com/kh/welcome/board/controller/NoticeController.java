package com.kh.welcome.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.kh.welcome.board.model.service.NoticeService;
import com.kh.welcome.board.model.service.NoticeServiceImpl;
import com.kh.welcome.board.model.vo.Notice;
import com.kh.welcome.member.vo.Member;

import common.util.FileUtil;

@Controller
public class NoticeController {

	@Autowired
	NoticeService noticeService;


	@RequestMapping("board/boardform")
	public String boardForm() {
		return "board/boardForm";
	}

	//추후에 수정될 메서드
	//다중파일 업로드이므로 여러개의 multipart를 담기위한 LIST생성
	//사용자가 첨부한 파일이 없어도 List<MultipartFile>의 size()가 1로 잡힌다.
	//이때 첨부한 파일의 이름은 공백이다
	@RequestMapping("notice/noticeupload")
	public String noticeUpload(@RequestParam List<MultipartFile> files,HttpSession session,Notice notice) {

		ModelAndView mav=new ModelAndView();


		String root=session.getServletContext().getRealPath("/");
		Member sessonMember=(Member) session.getAttribute("logInInfo");

		if(sessonMember !=null) {
			notice.setUserId(sessonMember.getUserId());
		}else {
			notice.setUserId("비회원");
		}
		noticeService.insertNotice(notice, files, root);
		mav.setViewName("board/boardList");
		return "redirect:/notice/noticelist";
	}


	@RequestMapping("notice/noticelist")
	//@requestParam required:필수파라미터 여부 지정
	//defaultValue :파라미터가 없을 때 기본값으로 지정할 값
	public ModelAndView noticeList(@RequestParam(required = false,defaultValue = "1") int cPage) {
		ModelAndView mav=new ModelAndView();
		int cntPerPage=10;
		Map<String ,Object>commandMap=noticeService.selectNoticeList(cPage, cntPerPage);

		mav.addObject("paging",commandMap.get("paging"));
		mav.addObject("noticeData",commandMap);
		mav.setViewName("board/boardList");
		return mav;
	}

	@RequestMapping("notice/noticedetail")
	public ModelAndView noticeDetail(int nIdx) {
		ModelAndView mav=new ModelAndView();
		Map<String,Object> commandMap=noticeService.selectNoticeDetail(nIdx);
		//해당 게시글이 존재하는지 여부 판단
		//반환되는 map은 null일수 없다
		//map안의 notice객체가 null인지 여부로 판단
		if(commandMap.get("notice") !=null) {
			mav.setViewName("board/boardView");
			mav.addObject("data",commandMap);
		}else {
			mav.addObject("alertMsg","해당 게시물이 존재하지 않습니다");
			mav.addObject("url","board/boardList");
			mav.setViewName("common/result");
		}
		return mav;

	}

	@RequestMapping("notice/noticeModify")
	public ModelAndView noticeModify(int nIdx,String userId) {
		ModelAndView mav=new ModelAndView();
		Map<String,Object> commandMap=noticeService.selectNoticeDetail(nIdx);
		if(commandMap.get("notice") !=null) {
			mav.setViewName("board/boardModify");
			mav.addObject("data",commandMap);
		}else {
			mav.addObject("alertMsg","해당 게시물이 존재하지 않습니다");
			mav.addObject("url","board/boardList");
			mav.setViewName("common/result");
		}
		return mav;
	}

	@RequestMapping("notice/modifyUpload")
	public String modifyUpload(@RequestParam List<MultipartFile> files,HttpSession session,Notice notice,int nIdx) {
		String root=session.getServletContext().getRealPath("/");
		notice.setnIdx(nIdx);
		System.out.println(notice);
		Member sessonMember=(Member) session.getAttribute("logInInfo");

		if(sessonMember !=null) {
			notice.setUserId(sessonMember.getUserId());
		}else {
			notice.setUserId("비회원");
		}
		noticeService.updateNotice(notice, files, root);

		return "redirect:/notice/noticedetail?nIdx="+nIdx;
	}

	@RequestMapping("notice/fileDelete")
	@ResponseBody
	public String fileDelete(int fIdx,HttpSession session) {

		Map<Object,Object> commandMap = noticeService.selectFile(fIdx);

		String path = session.getServletContext().getRealPath("/resources/upload")+"/"+commandMap.get("renameFileName");
		
		System.out.println(path);
		new FileUtil().deleteFile(path);//해당 파일 삭제
		noticeService.deleteFile2(fIdx);//db의 내용 삭제

		return "1";
	}

	//	@RequestMapping("notice/noticedownload")
	//	public void noticeDownload(HttpServletResponse resp,HttpSession session,String ofname,String rfname) {
	//		//response header 지정을 위한 response 
	//		//파일경로 지정을 위한 session
	//		
	//		String readFolder=session.getServletContext().getRealPath("/resources/upload");
	//		System.out.println(readFolder+"/"+rfname);
	//		//file 객체 생성
	//		File downFile=new File(readFolder+"/"+rfname);
	//		OutputStream downOut=null;
	//		BufferedInputStream bis=null;
	//		
	//		try {
	//			//response header
	//			resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(ofname, "UTF-8"));
	//			
	//			//응답해야하는 대상과 연결되는 outputStream
	//			downOut=resp.getOutputStream();
	//			//파일 데이터를 읽어옴
	//			bis=new BufferedInputStream(new FileInputStream(downFile));
	//			
	//			int read=0;
	//			while((read=bis.read())!=-1) {
	//				
	//				downOut.write(read);
	//				downOut.flush();
	//			}
	//			
	//			
	//		} catch (IOException e) {
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//	}

	@RequestMapping("notice/noticedownload")//파일 다운로드
	@ResponseBody
	public FileSystemResource noticeDownload(HttpServletResponse resp,HttpSession session,String ofname,String rfname) {
		String readFolder=session.getServletContext().getRealPath("/resources/upload");

		FileSystemResource downFile=new FileSystemResource(readFolder+"/"+rfname);

		try {
			resp.setHeader("Content-Disposition", "attachment; filename="+URLEncoder.encode(ofname, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return downFile;
	}
	
	@RequestMapping("notice/noticedelete")//파일 삭제하는 메소드
	public String NoticeDelete(int nIdx,HttpSession session) {
		Map<String,Object> commandMap=noticeService.selectNoticeDetail(nIdx);
		System.out.println(commandMap.get("flist"));
		String path=session.getServletContext().getRealPath("/resources/upload")+"/";
		ArrayList fileList=(ArrayList) commandMap.get("flist");
		for (Object object : fileList) {
			String map=((Map<String, String>)object).get("renameFileName");
			System.out.println(map);
			String filePath=path+map;
			new FileUtil().deleteFile(filePath);//파일 삭제
			//noticeService.deleteFile(fIdx);//db에서 파일 삭제
		}
		noticeService.deleteFile(nIdx);
		noticeService.deleteNotice(nIdx);
		
		
		return "redirect:/notice/noticelist";
	}
	
	

}
