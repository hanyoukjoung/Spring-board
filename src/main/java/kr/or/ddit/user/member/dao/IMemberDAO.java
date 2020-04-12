package kr.or.ddit.user.member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.vo.MemberVO;

public interface IMemberDAO {
	public void insertMemberInfo(MemberVO memberInfo) throws Exception;
	public void updateMemberInfo(MemberVO memberInfo) throws Exception;
	public void deleteMemberInfo(Map<String, String> params) throws Exception;
	public MemberVO memberInfo(Map<String, String> params) throws Exception;
	public List<MemberVO> memberList() throws Exception;
}






