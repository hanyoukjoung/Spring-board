package kr.or.ddit.user.join.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import kr.or.ddit.user.member.service.IMemberService;
import kr.or.ddit.utiles.CryptoGenerator;
import kr.or.ddit.vo.MemberVO;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

// http://localhost/SpringToddler/user/join/loginForm.do
// http://localhost/SpringToddler/user/join/loginCheck.do
// http://localhost/SpringToddler/user/join/logout.do

// <bean name=joinController class="kr.or.ddit.user.join.controller.JoinController"/>
@Controller
@RequestMapping("/user/join/")
public class JoinController{
	@Value("#{jdbcConnection['driver']}")
	public String jdbcDriver;
	@Value("#{jdbcConnection['url']}")
	public String jdbcURL;
	@Value("#{jdbcConnection['username']}")
	public String jdbcUserName;
	@Value("#{jdbcConnection['password']}")
	public String jdbcPWD;
	
	@Autowired
	private IMemberService service;
	
	@Autowired
	private MessageSourceAccessor accessor;
	
	@RequestMapping("loginForm")
	public ModelAndView loginForm(HttpSession session,
							HttpServletRequest request,
			               Model model,
			               ModelMap modelMap,
			               ModelAndView andView){
		
		//특정 컨트롤러 클래스 내  컨트롤러 매서드의 리다이렉트 처리로
		//RedirectAttributes를 통해 전송되는 값을 취득
		Map<String, ?> raParam =RequestContextUtils.getInputFlashMap(request);
		if(raParam != null){
			String value= (String) raParam.get("message");
		}
		
		
		Map<String, String> publicKeyMap = CryptoGenerator.getGeneratePairKey(session);
		
//		Model model = new ExtendedModelMap();
//		model.addAttribute("publicKeyMap", publicKeyMap);
//		return model;
		
//		ModelMap modelMap = new ModelMap();
//		modelMap.addAttribute("publicKeyMap", publicKeyMap);
//		return modelMap;
		
//		ModelAndView andView = new ModelAndView();
		andView.addObject("publicKeyMap", publicKeyMap);
//		andView.setViewName("redirect: or forward:을 통해 do 확장자 응답 제어 가능");
		andView.setViewName("user/join/loginForm");
		return andView;
		
//		return "user/join/loginForm";
	}
	
//	@RequestMapping("loginForm")
//	public void loginForm(){}
	
	// /user/join/loginCheck.do
	//    mem_id=a001&mem_pass=asdfasdf
	@RequestMapping("loginCheck")
	public String loginCheck(HttpServletRequest request,
			                HttpServletResponse response,
			                HttpSession session,
			                Map<String, String> params,
			                String mem_id,
			                String mem_pass,
			                MemberVO memberInfoMapping) throws Exception{
		
//		Map<String, String> params = new HashMap<String, String>();
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);
		
		MemberVO memberInfo = this.service.memberInfo(params);
		
		if(memberInfo == null){
			// 리다이렉트 처리(컨텍스트 루트 불포함)
			String message=this.accessor.getMessage("fail.common.join", Locale.KOREA);
			message = URLEncoder.encode( message, "UTF-8");
			return "redirect:/user/join/loginForm.do?message=" + message;
		}else{
			session.setAttribute("LOGIN_MEMBERINFO", memberInfo);
			// 포워드 처리
			return "forward:/user/member/memberList.do";
		}
		
	}
	@RequestMapping("loginCheck1")
	public String loginCheck1(HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			Map<String, String> params,
			String mem_id,
			String mem_pass,
			MemberVO memberInfoMapping) throws Exception{
		
		PrivateKey privateKey = (PrivateKey)session.getAttribute("privateKey");
		mem_id = CryptoGenerator.decryptRSA(privateKey, mem_id);
		mem_pass= CryptoGenerator.decryptRSA(privateKey, mem_pass);
		
		params.put("mem_id", mem_id);
		params.put("mem_pass", mem_pass);
		
		MemberVO memberInfo = this.service.memberInfo(params);
		
		if(memberInfo == null){
			// 리다이렉트 처리(컨텍스트 루트 불포함)
			String message = URLEncoder.encode("회원이 아닙니다.", "UTF-8");
			return "redirect:/user/freeboard/freeboardList.do?message=" + message;
		}else{
			session.setAttribute("LOGIN_MEMBERINFO", memberInfo);
			// 포워드 처리
			return "forward:/user/freeboard/freeboardList.do";
		}
		
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session) throws Exception{
		
		//ServletActionContext.getRequest().getSession().invalidate();
		String message =URLEncoder.encode("로그아웃되었습니다.", "UTF-8");
		session.invalidate();
		return "redirect:/user/join/loginForm.do?message="+message;
	}
	
	@RequestMapping("logoutfreeboard")
	public String logoutfreeboard(HttpSession session) throws Exception{
		
		//ServletActionContext.getRequest().getSession().invalidate();
		String message =URLEncoder.encode("로그아웃되었습니다.", "UTF-8");
		session.invalidate();
		return "redirect:/user/freeboard/freeboardList.do?message="+message;
	}

	@PreDestroy
	public void destroy() throws Exception{
		System.out.println("해당 빈 클래스의 GC 직전 콜백");
	}
	
	@PostConstruct
	public void init() throws Exception{
		System.out.println("해당 빈 클래스의 인스턴스 직후(생성자 호출 후) 콜백");
	}
	
	//////////////////// 삭제 //////////////////////
	// autowire=byName
//	public void setMemberService(IMemberService service){
//		this.service = service;
//	}
	// autowire=byType
//	public void setAmugerna(IMemberService service){
//		this.service = service;
//	}
	// autowire=constructor + byType 개념 추가
//	public JoinController(IMemberService service){
//		this.service = service;
//	}
}











