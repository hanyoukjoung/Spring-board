package kr.or.ddit.user.freeboard.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.user.fileitem.dao.IFileItemDAO;
import kr.or.ddit.user.fileitem.dao.IFileItemDAOImpl;
import kr.or.ddit.user.freeboard.dao.IFreeboardDAO;
import kr.or.ddit.user.freeboard.dao.IFreeboardDAOImpl;
import kr.or.ddit.utiles.AttachFileMapper;
import kr.or.ddit.vo.FileItemVO;
import kr.or.ddit.vo.FreeboardVO;

@Service
public class IFreeboardServiceImpl implements IFreeboardService {
	@Autowired
	private IFreeboardDAO freeboardDAO;
	@Autowired
	private IFileItemDAO fileitemDAO;
	@Autowired
	private AttachFileMapper fileMapper;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly= true)
	@Override
	public FreeboardVO freeboardInfo(Map<String, String> params) throws Exception{
		FreeboardVO freeboardInfo = null;
	
			freeboardInfo = freeboardDAO.freeboardInfo(params) ;
		return freeboardInfo;
	}

	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly= true)
	@Override
	public List<FreeboardVO> freeboardList(Map<String, String> params) throws Exception{
		List<FreeboardVO> freeboardList = null;
		
			freeboardList = freeboardDAO.freeboardList(params);
		
		return freeboardList;
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor={Exception.class})
	@Override
	public void insertFreeboardInfo(FreeboardVO freeboardInfo,MultipartFile[] items) throws Exception{
	
			String bo_no = freeboardDAO.insertFreeboardInfo(freeboardInfo);
			// 첨부파일의 파일 정보를 FileItemVO에 맵핑 및 저장
			List<FileItemVO> fileItemList = this.fileMapper.mapper(items, bo_no);
			fileitemDAO.insertFileItem(fileItemList);
		
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor={Exception.class})
	@Override
	public void updateFreeboardInfo(FreeboardVO freeboardInfo) throws Exception{
		try {
			freeboardDAO.updateFreeboardInfo(freeboardInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor={Exception.class})
	@Override
	public void deleteFreeboardInfo(Map<String, String> params) throws Exception{
	
			freeboardDAO.deleteFreeboardInfo(params);
	
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor={Exception.class})
	@Override
	public void insertFreeboardReply(FreeboardVO freeboardInfo) throws Exception{
		try {
			freeboardDAO.insertFreeboardReply(freeboardInfo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly= true)
	@Override
	public String totalCount(Map<String, String> params) throws Exception{
		String totalCount = "0";
		
			totalCount = freeboardDAO.totalCount(params);
		
		return totalCount;
	}

	
}





