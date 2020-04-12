package kr.or.ddit.utiles;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;

import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.global.GlobalConstant;
import kr.or.ddit.vo.FileItemVO;

public class FileDownloadView extends AbstractView{
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
											HttpServletRequest request,
											HttpServletResponse response) throws Exception {
		
		String targetFileName =(String) model.get("targetFileName");
		File downloadFile = new File(GlobalConstant.FILE_PATH, targetFileName);
		
		String fileName = "";
		if(downloadFile.exists()){
			try {
				fileName = URLEncoder.encode(targetFileName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
			response.setContentType("application/octet-stream");
			response.setContentLength((int)downloadFile.length());
			
			byte[] buffer = new byte[(int)downloadFile.length()];
			
			BufferedInputStream inputStream = null;
			BufferedOutputStream outputStream = null;
			try {
				inputStream = new BufferedInputStream(
				                     new FileInputStream(
				                       		downloadFile));
				outputStream = new BufferedOutputStream(
				                     response.getOutputStream());
				int readCNT = 0;
				while((readCNT = inputStream.read(buffer)) != -1){
					outputStream.write(buffer);
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					inputStream.close();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
		}
		
	}
}
