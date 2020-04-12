package kr.or.ddit.first.controller;

import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FirstController{
	// http://localhost/SpringToddler/first/hello.first
	@RequestMapping("/first/hello.first")
	public String firstMethod(){
		// 반환값 : View
		//   jsp를 대상으로 응답 제어(동적인 컨텐츠 작성 및 클라이언트 대상 전송)
		//   InternalResourceViewResolver 자원(jsp 대상 View 제어)
		return "first/hello";
	}

}
