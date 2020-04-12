package kr.or.ddit.user.freeboard.controller;

import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import kr.or.ddit.user.freeboard.service.IFreeboardService;
import kr.or.ddit.utiles.AttachFileMapper;
import kr.or.ddit.utiles.CryptoGenerator;
import kr.or.ddit.utiles.RolePageingUtile;
import kr.or.ddit.vo.FileItemVO;
import kr.or.ddit.vo.FreeboardVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;


@Controller
@RequestMapping("/user/freeboard/")
public class FreeboardController {
	@Autowired
	private AttachFileMapper fileMapper;
	
	@Autowired
	private IFreeboardService service;
	
	// user/freeboard/freeboardList
	@RequestMapping("freeboardList")
	public Model freeboardList(HttpSession session,
								HttpServletRequest request,
								Model model,
								String currentPage,
								String search_keycode,
								String search_keyword,
								String paginationHTML,
								Map<String,String> params) throws Exception{
		
		
		 params = CryptoGenerator.getGeneratePairKey(session);
		
		currentPage = currentPage == null ? "1" : currentPage;
		
		params.put("currentPage", currentPage);
		params.put("search_keycode", search_keycode);
		params.put("search_keyword", search_keyword);
		
		String totalCount = service.totalCount(params);
		int blockCount=10;
		RolePageingUtile pagination = new RolePageingUtile(
				request, 
				Integer.parseInt(totalCount), 
				Integer.parseInt(currentPage),
				blockCount,
				params);
		
		String startCount = String.valueOf(pagination.getStartCount());
		String endCount = String.valueOf(pagination.getEndCount());
		
		params.put("startCount", startCount);
		params.put("endCount", endCount);
		
		paginationHTML = pagination.getPagingHtmls();

		List<FreeboardVO> freeboardList = this.service.freeboardList(params);
		model.addAttribute("publicKeyMap", params);
		model.addAttribute("paginationHTML", paginationHTML);
		model.addAttribute("freeboardList", freeboardList);
		
		
		return model;
	}
	
//	@RequestMapping("freeboardList")
//	public ModelAndView freeboardList(HttpServletRequest request,
//			ModelAndView andView,
//			String currentPage,
//			String search_keycode,
//			String search_keyword,
//			String paginationHTML,
//			Map<String,String> params
//			) throws Exception{
//		
//		currentPage = currentPage == null ? "1" : currentPage;
//		
//		params.put("currentPage", currentPage);
//		params.put("search_keycode", search_keycode);
//		params.put("search_keyword", search_keyword);
//		
//		String totalCount = service.totalCount(params);
//		int blockCount=10;
//		RolePageingUtile pagination = new RolePageingUtile(
//				request, 
//				Integer.parseInt(totalCount), 
//				Integer.parseInt(currentPage),
//				blockCount,
//				params);
//		
//		String startCount = String.valueOf(pagination.getStartCount());
//		String endCount = String.valueOf(pagination.getEndCount());
//		
//		params.put("startCount", startCount);
//		params.put("endCount", endCount);
//		
//		paginationHTML = pagination.getPagingHtmls();
//		
//		List<FreeboardVO> freeboardList = this.service.freeboardList(params);
//		andView.addObject("paginationHTML", paginationHTML);
//		andView.addObject("freeboardList", freeboardList);
//		
//		andView.setViewName("/user/freeboard/freeboardList.do");
//		
//		return andView;
//	}
	
	@RequestMapping("freeboardView")
	@ModelAttribute("freeboardInfo")
	public FreeboardVO freeboardView(String bo_no,
									 Map<String,String> params,
									 FreeboardVO freeboardInfo) throws Exception{
		params.put("bo_no", bo_no);
		
		freeboardInfo = this.service.freeboardInfo(params);
		
		
		return freeboardInfo;
		
	}
	
	@RequestMapping("updateFreeboard")
	public String updateFreeboard(FreeboardVO freeboardInfo,
									RedirectAttributes redirectAttributes) throws Exception{
		this.service.updateFreeboardInfo(freeboardInfo);
		
		String message = URLEncoder.encode("수정이 완료되었습니다.", "UTF-8");

	
		return "redirect:/user/freeboard/freeboardList.do?message="+message;
	}
	
	@RequestMapping("deleteFreeboard")
	public String deleteFreeboard(String bo_no,
								  Map<String,String> params
								  ) throws Exception{
		params.put("bo_no", bo_no);
		
	    this.service.deleteFreeboardInfo(params);
	    String message = URLEncoder.encode("삭제가 완료되었습니다.", "UTF-8");
	    
	    return "redirect:/user/freeboard/freeboardList.do?message="+message;
	}
	
	@RequestMapping("freeboardForm")
	public void freeboardForm() throws Exception{};
	
	@RequestMapping("insertFreeboard")
	public String insertFreeboard(FreeboardVO freeboardInfo,
									@RequestParam("files") MultipartFile[] items,
			 						String bo_no) throws Exception{
//		List<FileItemVO> fileItems = this.fileMapper.mapper(items, bo_no);
		this.service.insertFreeboardInfo(freeboardInfo, items);
		return "redirect:/user/freeboard/freeboardList.do?";
		
	}
	
	@RequestMapping("insertFreeboardReply")
	public String insertFreeboardReply(FreeboardVO freeboardInfo) throws Exception{
		this.service.insertFreeboardReply(freeboardInfo);
		return "redirect:/user/freeboard/freeboardList.do";
	}
	
	@RequestMapping("freeboardReplyForm")
	public ModelAndView freeboardReplyForm(FreeboardVO boardInfo,
											Map<String,String> params,
											ModelAndView andView,
											String bo_no) throws Exception{
		params.put("bo_no", bo_no);
		boardInfo = this.service.freeboardInfo(params);
		andView.addObject("boardInfo", boardInfo);
		andView.setViewName("user/freeboard/freeboardReplyForm");
		return andView;
	}
	
	
	
	
}
