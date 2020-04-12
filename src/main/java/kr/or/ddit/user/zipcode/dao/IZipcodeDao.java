package kr.or.ddit.user.zipcode.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.ZipcodeVO;

public interface IZipcodeDao {
	public List<ZipcodeVO> zipcodeList(Map<String, String> params) throws Exception;
}
