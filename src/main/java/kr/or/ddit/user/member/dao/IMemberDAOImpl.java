package kr.or.ddit.user.member.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.vo.MemberVO;

// <bean name=iMemberDAOImpl class=kr.or.ddit.user.member.dao.IMemberDAOImpl/>
@Repository
public class IMemberDAOImpl implements IMemberDAO {
	@Autowired
	private SqlMapClient client;
	
	@Override
	public void insertMemberInfo(MemberVO memberInfo) throws Exception {
		client.insert("member.insertMember", memberInfo);
	}

	@Override
	public void updateMemberInfo(MemberVO memberInfo) throws Exception {
		// SqlMapClient.update() : UPDATE 질의
		//                         CREATE TABLE|SEQUENCE|SYNONIM|VIEW|TRIGGER.....
		//                         Procedure, Funciton 호출
		client.update("member.updateMember", memberInfo);
	}

	@Override
	public void deleteMemberInfo(Map<String, String> params)
			throws Exception {
		client.update("member.deleteMember", params);
	}

	@Override
	public MemberVO memberInfo(Map<String, String> params) throws Exception {
		return (MemberVO) client.queryForObject("member.memberInfo", params);
	}

	@Override
	public List<MemberVO> memberList() throws Exception {
		return client.queryForList("member.memberList");
	}

	////////////////////////// 삭제 ///////////////////////
	// autowire=byName
//	public void setSqlMapClient(SqlMapClient client){
//		this.client = client;
//	}
	// autowire=byType
//	public void setClient(SqlMapClient client){
//		this.client = client;
//	}
	// autowire=constructor
//	public IMemberDAOImpl(SqlMapClient client){
//		this.client = client;
//	}
}





