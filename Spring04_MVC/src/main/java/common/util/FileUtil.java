package common.util;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public List<Map<String,String>> fileUpload(List<MultipartFile> files,String root){
		List<Map<String,String>> fileData=new ArrayList<Map<String,String>>();
		//rename 뒤에 붙여줄 index
		int idx=0;
		for (MultipartFile mf : files) {
			String savePath=root+"resources/upload/";
			//사용자가 올린 파일이름
			String originFileaName=mf.getOriginalFilename();
			String renameFileName=getRenameFileName(originFileaName, idx);
			//저장경로
			savePath+=renameFileName;
			Map<String,String> map=new HashMap<String, String>();
			map.put("originFileName", originFileaName);
			map.put("renameFileName", renameFileName);
			map.put("savePath", savePath);
			fileData.add(map);
			saveFile(mf,savePath);
			//파일명 변경을 위한 인덱스 값 증가시키기
			idx++;


		}

		return fileData;

	}
	public String getRenameFileName(String originFileName,int idx) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddhhmmss");
		String renameFileName=sdf.format(new Date(System.currentTimeMillis()))+idx+"."+originFileName.substring(
				originFileName.lastIndexOf(".")+1);
		return renameFileName;
	}

	public void saveFile(MultipartFile mf,String savePath) {
		//파일을 옮겨담을 파일 객체 생성
		File fileDate=new File(savePath);
		try {
			mf.transferTo(fileDate);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void deleteFile(String path) {
		File file=new File(path);
		file.delete();
	}

}
