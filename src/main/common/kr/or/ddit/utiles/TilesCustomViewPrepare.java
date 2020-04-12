package kr.or.ddit.utiles;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.global.GlobalConstant;

import org.apache.tiles.Attribute;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.context.TilesRequestContext;
import org.apache.tiles.preparer.ViewPreparer;

//tiles-def.xml
//	<definition name=user/*/* extends=user/main>
//		<put-attribute name=content value=/WEB-INF/views/user/layout/header.jsp/>
//		<put-attribute name=content value=/WEB-INF/views/user/{1}/{2}.jsp/>
public class TilesCustomViewPrepare implements ViewPreparer {
	
	//컨트롤러 클래스 내 컨트롤러 매서드의 반환값(void,model,modelMap,String,ModelAndView)
	// 타일즈뷰리솔버(order=1) 전달
	// void,model,modelMap,vo = 클라이언트 요청시 서블릿 패스를 기준으로 View(tiles, class, internalresourceviewresolver) 결정
	//							View의 경로가 request.setAttribute("viewName","서블릿패스로 부터 추출한 경로");
	//	ModelAndView = View 경로와 명 설정 
	// View의 경로가 request.setAttribute("viewName","설정된 View의 경로와 명");
	@Override
	public void execute(TilesRequestContext tilesContext,
						AttributeContext attributeContext) {
		HttpServletRequest request = (HttpServletRequest) tilesContext.getRequestObjects()[0];
		
		String tileViewName = (String) request.getAttribute("tilesViewName");
		Attribute attribute = new Attribute(GlobalConstant.PREFIX_PATH+"user/freeboard/freeboardForm"+GlobalConstant.SUFFIX_PATH);
		//1.요번에 접근해야하는 타일즈의 definition 내 선언된 put-attribute의 name속성값
		//2.Attribute :put-attribute name속성값,View의 경로
		//<put-attribute name=content value=/WEB-INF/views/user/freeboard/freeboardList.jsp/>
		//<put-attribute name=content value=/WEB-INF/views/user/layout/header.jsp/>
		attributeContext.putAttribute("content",attribute);
	}

}
