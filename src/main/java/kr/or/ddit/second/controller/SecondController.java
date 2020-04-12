package kr.or.ddit.second.controller;

import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// http://localhost/SpringToddler/second/hello.second
@Controller
@RequestMapping("/second/")
//public class SecondController implements ApplicationContextAware{
public class SecondController{
	
	@RequestMapping("hello.second")
	public String secondMethod(){
		return "second/hello";
	}

	// 해당 빈 클래스의 인스턴스화 직후 콜백
	// ApplicationContext(WebApplicationContext) 주입
	// ApplicationContextAware.setApplicationContext()
//	@Override
//	public void setApplicationContext(ApplicationContext applicationContext)
//			throws BeansException {
//		// 취득하고자 하는 빈 클래스의 빈 등록시의 id 또는 name속성값 
//		MemberVO memberInfo1 = (MemberVO) applicationContext.getBean("memberVO");
//		MemberVO memberInfo2 = (MemberVO) applicationContext.getBean("m1");
//		MemberVO memberInfo3 = (MemberVO) applicationContext.getBean("m2");
//		MemberVO memberInfo4 = (MemberVO) applicationContext.getBean("m3");
//		
//		System.out.println("memberInfo1 : " + System.identityHashCode(memberInfo1));
//		System.out.println("memberInfo2 : " + System.identityHashCode(memberInfo2));
//		System.out.println("memberInfo3 : " + System.identityHashCode(memberInfo3));
//		System.out.println("memberInfo4 : " + System.identityHashCode(memberInfo4));
//	}
}









