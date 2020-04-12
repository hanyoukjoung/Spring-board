package kr.or.ddit.user.freeboard.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.FreeboardVO;

public interface IFreeboardDAO {
	public FreeboardVO freeboardInfo(Map<String, String> params) throws Exception;
	public List<FreeboardVO> freeboardList(Map<String, String> params) throws Exception;
	public String insertFreeboardInfo(FreeboardVO freeboardInfo) throws Exception;
	public void insertFreeboardReply(FreeboardVO freeboardInfo) throws Exception;
	public void updateFreeboardInfo(FreeboardVO freeboardInfo) throws Exception;
	public void deleteFreeboardInfo(Map<String, String> params) throws Exception;
	public String totalCount(Map<String, String> params) throws Exception;
}
