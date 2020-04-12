package kr.or.ddit.interceptor;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

// 클라이언트 요청
// /user/member/memberList.do
// 1. 동기식 요청
//		1.1 preHandle(){ return true;}
//		1.2 컨트롤러 클래스 내 해당 요청을 처리할 컨트롤러 매서드가 콜백(컨트롤러 매서드 종료)
//		1.3 postHandle() 콜백 
//		1.4 afterCompletion() 콜백 
//      1.5 인터셉터 처리 종료

// 2. 비동기식 요청(ajax)
//		2.1 preHandle(){ return true;}
//		2.2 컨트롤러 클래스 내 해당 요청을 처리할 컨트롤러 매서드가 콜백(컨트롤러 매서드 종료)
//		2.3 AfterConcurrentHadlingStarted()콜백
//		2.4 인터셉터 처리 종료 

public class IPCheckInterceptor extends HandlerInterceptorAdapter {
	private static Map<String, String> ipAddrMap;
	
	static {
		ipAddrMap = new HashMap<String, String>();
		ipAddrMap.put("127.0.0.1", "A");
		ipAddrMap.put("0:0:0:0:0:0:0:1", "A");
		ipAddrMap.put("192.168.203.2", "A");
		ipAddrMap.put("192.168.203.20", "F");
	}
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response,
							Object handler) throws Exception {
		String clientIP = request.getRemoteAddr();
		
		boolean flag = true;
		if(StringUtils.isNotEmpty(clientIP) && ipAddrMap.containsKey(clientIP)){
			// "A".equals("A")
			if("A".intern() == ipAddrMap.get(clientIP).intern()){
	
			}else{
				flag = false;
				
			}
		}else{
			flag =false;
		}
		if(!flag){
				response.setCharacterEncoding("UTF-8");
				response.setContentType("text/html; charset=UTF-8");
				
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<body>");
				out.println("<font color='red'>관리자에게 문의해주세요.</font>");
				out.println("</body>");
				out.println("</html>");
		}
		// return true : 동기식 또는 비동기식 
		// return false: 인터셉터 종료 
		return flag;
	}
	
}
