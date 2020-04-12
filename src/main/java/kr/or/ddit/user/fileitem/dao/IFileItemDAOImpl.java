package kr.or.ddit.user.fileitem.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.vo.FileItemVO;

@Repository
public class IFileItemDAOImpl implements IFileItemDAO {
	@Autowired
	private SqlMapClient client;
	
	@Override
	public void insertFileItem(List<FileItemVO> fileItemList)
			throws Exception {
		// 첨부 파일 * 2 == insert 질의 * 2
		// 트랜잭션
		//      첨부파일 insert 질의1 O
		//      첨부파일 insert 질의2 X
		try{
			client.startTransaction();
			
			for(FileItemVO fileItemInfo : fileItemList){
				client.insert("fileitem.insertFileItem", fileItemInfo);
			}
			client.commitTransaction();
		} finally {
			client.endTransaction();
		}
	}

	@Override
	public FileItemVO fileitemInfo(Map<String, String> params)
			throws Exception {
		return (FileItemVO) client.queryForObject("fileitem.fileitemInfo", params);
	}

}






