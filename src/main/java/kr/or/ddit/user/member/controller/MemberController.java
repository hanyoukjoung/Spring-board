package kr.or.ddit.user.member.controller;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.qualifer.service.IService;
import kr.or.ddit.user.member.service.IMemberService;
import kr.or.ddit.vo.MemberVO;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user/member/")
public class MemberController {
	
	//class IMemberServiceImpl implements IMemberService extends IMemberService
//	@Autowired
//	@Qualifier("memberService")
//	private IService service;
	@Autowired
	private IMemberService service;
	 
	@RequestMapping("memberList")
	public Model memberList(Model model,
			                @RequestHeader("Accept") String accept,
			                @RequestHeader(value="Users", required=false, defaultValue="null의 대체값") String agent,
			                @CookieValue("JSESSIONID") String sessionID,
			                @CookieValue(value="mem_name", required=false, defaultValue="null 대체값") String mem_name) throws Exception{
		List<MemberVO> memberList = this.service.memberList();
		
		model.addAttribute("memberList", memberList);
		return model;
	}
	// /user/member/memberView.do
	// 반환값 : void,Model:인터페이스 ,ModelMap
	// InternalResourceViewResolver = user/member/memberView 전달
	// prefix :/WEB-INF/views
	// suffix : .jsp
	// * /WEB-INF/views/user/member/memberView.jsp
	
	// /user/member/memberView/a001.do
//	@RequestMapping("memberView/{user_id}")
//	public ModelAndView memberView(@PathVariable("user_id") String mem_id,
//							Map<String,String> params,
//							ModelAndView andView) throws Exception{
	
	
	 //@Transational(propagation=Propagation.REQUIRES_NEW,readOnly =true)
		@RequestMapping("memberView")
		@ModelAttribute("memberInfo")
		public MemberVO memberView(String mem_id,
									Map<String,String> params,
									MemberVO memberInfo) throws Exception{
		params.put("mem_id", mem_id);
		
		memberInfo = this.service.memberInfo(params);
		

		return memberInfo;
		
	}
	 	
		@RequestMapping("memberForm")
		public void memberForm(){}
		
		
		//MemberVO memberInfo 커맨드 오브젝트: 클라이언트의 쿼리스트링의 값 맴핑 처리 객체 
		@RequestMapping("insertMember")
		public String insertMember(@RequestBody String queryString,
									MemberVO memberInfo,
									RedirectAttributes redirectAttributes) throws Exception{
			//this.service.insertMemberInfo(memberInfo);
			
			String message = URLEncoder.encode("회원가입이 완료되었습니다", "UTF-8");
			
//			return "redirect:/user/join/loginForm.do?message="+message;
			redirectAttributes.addFlashAttribute("message", "회원 가입 완료");
			
			return "redirect:/user/join/loginForm.do";
			
		}
		
//		@RequestMapping("idCheck")
//		@ResponseBody
//		public String idCheck(String mem_id,
//							  Map<String,String> params) throws Exception{
//			params.put("mem_id", mem_id);
//			
//			MemberVO memberInfo=this.service.memberInfo(params);
//			
//			Map<String, String> jsonMap = new HashMap<String, String>();
//			if(memberInfo==null){
//				jsonMap.put("flag", "true");
//			}else{
//				jsonMap.put("flag", "false");
//			}
//			ObjectMapper mapper = new ObjectMapper();
//			String jsonData = mapper.writeValueAsString(jsonMap);
//			
//			//{"flag":"true"}=>/WEB-INF/views/{"flag":"true"}.jsp
//			return jsonData;
//			
//		}
		 
		
		@RequestMapping("idCheck")
		public ModelAndView idCheck(String mem_id,
									Map<String,String> params,
									ModelAndView andView) throws Exception{
			params.put("mem_id", mem_id);
			MemberVO memberInfo = this.service.memberInfo(params);
			
			andView.addObject("memberInfo", memberInfo);
			//andView.setViewName("/user/member/memberList");
			//andView.setViewName("redirect:/user/member/memberForm.do");
			//andView.setViewName("forward:/user/member/memberForm.do");
			//<bean id="jsonConvertView" class="MappingJackson2......"/>
			andView.setViewName("jsonConvertView");
			
			return andView;
			
		}
		
		
	
}






