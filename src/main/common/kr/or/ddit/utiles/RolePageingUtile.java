package kr.or.ddit.utiles;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RolePageingUtile {
	
	private int currentPage;		
	private int totalCount;			
	private int blockCount = 10;	
	private int totalPage;			
	private int blockPage = 5;
	private int startPage;
	private int endPage;
	private int startCount;
	private int endCount;
	private StringBuffer pagingHtmls;
	private HttpServletRequest request;
	private Map<String, String> parameterMap;
	private String parameter;
	
	public RolePageingUtile(HttpServletRequest request, 
							int totalCount, 
							int currentPage, 
							int blockCount, 
							Map<String, String> parameterMap){
		this.blockCount = blockCount;
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.request = request;
		this.parameterMap = parameterMap;
		
		pagingHtmls = new StringBuffer();
		makeParameter();
		makePagination();
	}
	
	private void makeParameter(){
		
		parameterMap.remove("currentPage");
		parameterMap.remove("startPage");
		parameterMap.remove("endPage");
		
		StringBuffer parameterBuffer = new StringBuffer();
		
		Iterator<String> it = parameterMap.keySet().iterator();
		
		while(it.hasNext()){
			String key = it.next();
			String value = parameterMap.get(key);
			
			if(value == null) continue;
			
			parameterBuffer.append("&");
			parameterBuffer.append(key);
			parameterBuffer.append("=");
			parameterBuffer.append(value);
		}
		
		this.parameter = parameterBuffer.toString();
		
	}
	
	private void makePagination() {
		this.totalPage = (int)Math.ceil(this.totalCount / (double)this.blockCount); 
		if(this.totalPage == 0){
			this.totalPage = 1;
		}
		this.startCount = this.totalCount - (this.currentPage-1) * this.blockCount;
		this.endCount = this.startCount - this.blockCount +1;
		if(this.endCount < 0){
			this.endCount=1;
		}
		
		this.startPage = ((this.currentPage -1) / this.blockPage * this.blockPage) +1;
		this.endPage = this.startPage + this.blockPage-1;
		if(this.endPage > this.totalPage){
			this.endPage = this.totalPage;
		}
		this.pagingHtmls.append("<div class= 'text-center'>");
		this.pagingHtmls.append("<ul class='pagination mtm mbm'>");
		
		if((this.currentPage-1)==0){
			this.pagingHtmls.append("<li class = 'disabled'><a href='#'>&laquo;</a></li>");	
		}else{
			this.pagingHtmls.append("<li><a href='"+ this.request.getRequestURI() +"?currentPage="+ (this.currentPage-1) + parameter +"'>&laquo;</a></li>");	
		}
		for (int i = this.startPage; i <= this.endPage; i++) {
			if(this.currentPage == i){
				this.pagingHtmls.append(" <li class='active'><a href='#'>" + i + "</a></li>");
			}else{
				this.pagingHtmls.append("<li><a href='"+ this.request.getRequestURI() +"?currentPage="+ i + parameter +"'>"+i+"</a></li>");	
			}
		}
		if(this.currentPage < this.totalPage){
			this.pagingHtmls.append("<li><a href='"+ this.request.getRequestURI() +"?currentPage="+ (this.currentPage+ 1) + parameter +"'>&raquo;</a></li>");	
		}else{
			this.pagingHtmls.append("<li class = 'disabled'><a href='#'>&raquo;</a></li>");	
		}
		this.pagingHtmls.append("</ul></div>");
		
	}

	public int getStartCount() {
		return startCount;
	}

	public int getEndCount() {
		return endCount;
	}

	public String getPagingHtmls() {
		return pagingHtmls.toString();
	}
	
	


}
