package kr.or.ddit.user.zipcode.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.vo.ZipcodeVO;

@Repository
public class IZipcodeDaoImpl implements IZipcodeDao {
	@Autowired
	private SqlMapClient client;
	
	@Override
	public List<ZipcodeVO> zipcodeList(Map<String, String> params) throws Exception{
		return client.queryForList("zipcode.zipcodeList", params);
	}

}
