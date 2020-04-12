package kr.or.ddit.file.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.utiles.AttachFileMapper;
import kr.or.ddit.vo.FileItemVO;
import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/user/file/")
public class FileController {
	
	@Autowired
	private AttachFileMapper fileMapper;
	
	@RequestMapping("fileUploadForm")
	public void fileUploadForm(){}
	
	@RequestMapping("fileUpload")
	public String fileUpload(MemberVO memberInfo,
							 @RequestParam("files") MultipartFile[] items) throws Exception{
		List<FileItemVO> fileItems=this.fileMapper.mapper(items, "1");
		
		FileItemVO fileItemInfo = fileItems.get(0);
		
		String fileName = fileItemInfo.getFile_save_name();
		
		return "redirect:/user/file/fileUploadForm.do?fileName="+fileName;
	}
	
	@RequestMapping("fileDownload")
	public ModelAndView fileDownload(String fileName,
							ModelAndView andView) throws Exception{
		andView.addObject("targetFileName", fileName);
		andView.setViewName("fileDownloadView");
		
		return andView;
	}
	
	
	

}
