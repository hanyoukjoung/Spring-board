package kr.or.ddit.user.freeboard.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.qualifer.service.IService;
import kr.or.ddit.vo.FreeboardVO;
//인터페이스 
public interface IFreeboardService extends IService{
	public FreeboardVO freeboardInfo(Map<String, String> params) throws Exception;
	public List<FreeboardVO> freeboardList(Map<String, String> params) throws Exception;
	public void insertFreeboardInfo(FreeboardVO freeboardInfo, MultipartFile[] items) throws Exception;
	public void insertFreeboardReply(FreeboardVO freeboardInfo) throws Exception;
	public void updateFreeboardInfo(FreeboardVO freeboardInfo) throws Exception;
	public void deleteFreeboardInfo(Map<String, String> params) throws Exception;
	public String totalCount(Map<String, String> params) throws Exception;
}
