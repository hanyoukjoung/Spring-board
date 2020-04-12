package kr.or.ddit.user.fileitem.service;

import java.util.Map;

import kr.or.ddit.qualifer.service.IService;
import kr.or.ddit.vo.FileItemVO;

public interface IFileItemService {
	public FileItemVO fileitemInfo(Map<String, String> params) throws Exception;
}
