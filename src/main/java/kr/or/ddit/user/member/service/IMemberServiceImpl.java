package kr.or.ddit.user.member.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedIOException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import kr.or.ddit.user.member.dao.IMemberDAO;
import kr.or.ddit.user.member.dao.IMemberDAOImpl;
import kr.or.ddit.vo.MemberVO;

// High Cohesion Loose Coupling
// (응집도는 높게.. 결합도는 낮게...)
// * 프로그램 < 라이브러리|모듈 < 애플리케이션 < 소프트웨어(메뉴얼, 팩키징 도구)
//             런타임시 모듈->컴포넌트
// 1. 모듈 단위 대상의 프로그래밍에서 지향하는 관습의 일부로 모듈은 독립적이어야하며,
//    모듈은 재활용 가능해야하며, 결합과 분리가 용이해야하고, 모듈 간에는 단순한 
//    데이터의 전달로 결합되어야함.
// 2. 응집도
//    모듈은 특정 관심, 관점에의해 분리되어 개발되어야하며(단일역할 원칙), 
//    단일역할의 충실도를 응집도로 정의
// 3. 결합도(의존성)
//    모듈은 다른 모듈들과 단순한 데이터의 전달과 취득과정만으로 독립적으로 
//    실행되어야하며, 다른 모듈과의 의존성 정도 결합도 정의
// <bean name=memberService class=kr.or.ddit.user.member.service.IMemberServiceImpl/>
@Service("memberService")
public class IMemberServiceImpl implements IMemberService{
	@Autowired
	private IMemberDAO dao;
	
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor={Exception.class})
	@Override
	public void insertMemberInfo(MemberVO memberInfo) throws Exception{
		
			dao.insertMemberInfo(memberInfo);
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor={Exception.class})
	@Override
	public void updateMemberInfo(MemberVO memberInfo) throws Exception{
		
			dao.updateMemberInfo(memberInfo);
		
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW,rollbackFor={Exception.class})
	@Override
	public void deleteMemberInfo(Map<String, String> params) throws Exception{
		
			dao.deleteMemberInfo(params);
	
	}
	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly= true)
	@Override
	public MemberVO memberInfo(Map<String, String> params)throws Exception {
		MemberVO memberInfo = null;
		
			memberInfo = dao.memberInfo(params);
		return memberInfo;
	}
 	@Transactional(propagation=Propagation.REQUIRES_NEW, readOnly= true)
	@Override
	public List<MemberVO> memberList() throws Exception{
		List<MemberVO> memberList = null;
	
			memberList = dao.memberList();
		return memberList;
	}

	/////////////////////// 삭제 //////////////////////////
	// autowire=byName
//	public void setMemberDAO(IMemberDAO dao){
//		this.dao = dao;
//	}
	// autowire=byType
//	public void setMerdden(IMemberDAO dao){
//		this.dao = dao;
//	}
	// autowire=constructor
//	public IMemberServiceImpl(IMemberDAO dao){
//		this.dao = dao;
//	}
}









