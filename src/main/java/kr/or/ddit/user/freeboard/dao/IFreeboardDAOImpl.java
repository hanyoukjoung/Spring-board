package kr.or.ddit.user.freeboard.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.vo.FreeboardVO;

@Repository
public class IFreeboardDAOImpl implements IFreeboardDAO {
	@Autowired
	private SqlMapClient client; 
	
	@Override
	public FreeboardVO freeboardInfo(Map<String, String> params)
			throws Exception {
		FreeboardVO freeboardInfo = null;
		try{
			client.startTransaction();

			client.update("freeboard.updateHIT", params);
			freeboardInfo = (FreeboardVO) client.queryForObject("freeboard.freeboardInfo", params);
			
			client.commitTransaction();
		}finally{
			client.endTransaction();
		}
		return freeboardInfo;
	}

	@Override
	public List<FreeboardVO> freeboardList(Map<String, String> params) throws Exception {
		return client.queryForList("freeboard.freeboardList", params);
	}

	@Override
	public String insertFreeboardInfo(FreeboardVO freeboardInfo)
			throws Exception {
		return (String) client.insert("freeboard.insertFreeboard", freeboardInfo);
	}

	@Override
	public void updateFreeboardInfo(FreeboardVO freeboardInfo)
			throws Exception {
		client.update("freeboard.updateFreeboard", freeboardInfo);
	}

	@Override
	public void deleteFreeboardInfo(Map<String, String> params)
			throws Exception {
		client.update("freeboard.deletefreeboard", params);
	}

	@Override
	public void insertFreeboardReply(FreeboardVO freeboardInfo)
			throws Exception {
		// 파라메터 freeboardInfo  부모글(루트,댓글,대댓글) 
		// bo_group, bo_seq, bo_depth 포함.
		try{
			client.startTransaction();
			
			// insert 대상 게시글의 bo_seq값
			String bo_seq;
			if("0".intern() == freeboardInfo.getBo_seq().intern()){
				// insert 대상 댓글의 부모가 루트 게시글
				bo_seq = (String) client.queryForObject("freeboard.incrementSEQ", freeboardInfo);
			}else{
				// insert 대상 게시글의 부모가 댓글, 대댓글...
				client.update("freeboard.updateSEQ", freeboardInfo);
				bo_seq = String.valueOf(Integer.parseInt(freeboardInfo.getBo_seq()) + 1);
			}
			freeboardInfo.setBo_seq(bo_seq);
			
			String bo_depth = String.valueOf(Integer.parseInt(freeboardInfo.getBo_depth()) + 1);
			freeboardInfo.setBo_depth(bo_depth);
			
			client.insert("freeboard.insertFreeboardReply", freeboardInfo);
			
			client.commitTransaction();
		} finally {
			client.endTransaction();
		}
	}

	@Override
	public String totalCount(Map<String, String> params) throws Exception {
		return (String) client.queryForObject("freeboard.totalCount", params);
	}
	
	
}









