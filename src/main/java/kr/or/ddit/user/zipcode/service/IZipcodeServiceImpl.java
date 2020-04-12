package kr.or.ddit.user.zipcode.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.ddit.user.zipcode.dao.IZipcodeDao;
import kr.or.ddit.user.zipcode.dao.IZipcodeDaoImpl;
import kr.or.ddit.vo.ZipcodeVO;

@Service
public class IZipcodeServiceImpl implements IZipcodeService {
	@Autowired
	private IZipcodeDao dao;
	
	@Override
	public List<ZipcodeVO> zipcodeList(Map<String, String> params) throws Exception{
		List<ZipcodeVO> zipcodeList = null;
	
			zipcodeList = dao.zipcodeList(params);
		return zipcodeList;
	}

}
