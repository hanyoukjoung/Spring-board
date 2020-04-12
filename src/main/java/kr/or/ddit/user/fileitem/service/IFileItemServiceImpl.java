package kr.or.ddit.user.fileitem.service;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.user.fileitem.dao.IFileItemDAO;
import kr.or.ddit.user.fileitem.dao.IFileItemDAOImpl;
import kr.or.ddit.vo.FileItemVO;

@Service
public class IFileItemServiceImpl implements IFileItemService {
	@Autowired
	private IFileItemDAO dao;
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly= true)
	@Override
	public FileItemVO fileitemInfo(Map<String, String> params) throws Exception{
		FileItemVO fileitemInfo = null;
	
			fileitemInfo = dao.fileitemInfo(params);
		return fileitemInfo;
	}

}



