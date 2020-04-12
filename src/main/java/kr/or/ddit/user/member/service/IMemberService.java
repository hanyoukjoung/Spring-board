package kr.or.ddit.user.member.service;

import java.util.List;
import java.util.Map;








import kr.or.ddit.qualifer.service.IService;
import kr.or.ddit.vo.MemberVO;
//인터페이스 
public interface IMemberService extends IService{
	public void insertMemberInfo(MemberVO memberInfo) throws Exception;
	public void updateMemberInfo(MemberVO memberInfo) throws Exception;
	public void deleteMemberInfo(Map<String, String> params) throws Exception;
	public MemberVO memberInfo(Map<String, String> params) throws Exception;
	public List<MemberVO> memberList() throws Exception;
}








