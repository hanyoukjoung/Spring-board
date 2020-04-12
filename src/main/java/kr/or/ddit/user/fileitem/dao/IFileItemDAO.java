package kr.or.ddit.user.fileitem.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.FileItemVO;

public interface IFileItemDAO {
	public void insertFileItem(List<FileItemVO> fileItemList) throws Exception;
	public FileItemVO fileitemInfo(Map<String, String> params) throws Exception;
}
