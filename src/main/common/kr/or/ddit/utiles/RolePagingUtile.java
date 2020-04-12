package kr.or.ddit.utiles;

import javax.servlet.http.HttpServletRequest;

// 페이지네이션 UI 작성
public class RolePagingUtile {
	private int currentPage;		// 현재 페이지 번호
	private int totalCount;			// 전체 게시글 수
	private int blockCount = 10;	// 페이지별 출력 게시글 수
	private int totalPage;			// 전체 페이지 수
	private int blockPage = 5;		// 페이지별 출력 페이지네이션 넘버 수
	private int startPage;			// 페이지별 출력 페이지네이션 넘버 시작 페이지 번호
	private int endPage;			// 페이지별 출력 페이지네이션 넘버 끝 페이지 번호
	private int startCount; 		// 페이지별 출력 게시글 시작번호(rnum)			
	private int endCount;	 		// 페이지별 출력 게시글 끝번호(rnum)
	private StringBuffer pagingHtmls;
	private HttpServletRequest request;
	
	public RolePagingUtile(HttpServletRequest request,
						   int totalCount,
						   int currentPage) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		this.request = request;
		
		pagingHtmls = new StringBuffer();
		
		makePagination();
	}

	private void makePagination() {
		// 전체 게시글 수(검색 O|X)
		this.totalPage =  (int)Math.ceil(this.totalCount / (double)this.blockCount);
		if(this.totalPage == 0){
			this.totalPage = 1;
		}
		
		// 현재페이지(currentPage)에 출력될 게시글의 시작번호, 끝번호
		this.startCount = this.totalCount - (this.currentPage -1) * this.blockCount;
		this.endCount = this.startCount - this.blockCount + 1;
		if(this.endCount < 0){
			this.endCount = 1;
		}
		
		// 페이지별 출력 페이지네이션 넘버의 시작, 끝 페이지 번호
		this.startPage = ((this.currentPage - 1) / this.blockPage * this.blockPage) + 1;
		this.endPage = this.startPage + this.blockPage - 1;
		if(this.endPage > this.totalPage){
			this.endPage = this.totalPage;
		}
		
//            <ul class="pagination mtm mbm">
//                <li><a href="#">&laquo;</a></li>
//                <li><a href="#">1</a></li>
//                <li><a href="#">2</a></li>
//                <li><a href="#">3</a></li>
//                <li><a href="#">4</a></li>
//                <li><a href="#">5</a></li>
//                <li><a href="#">&raquo;</a></li>
//            </ul>
		this.pagingHtmls.append("<div class='text-center'>");
		this.pagingHtmls.append("<ul class='pagination mtm mbm'>");
		
		String requestURI = this.request.getRequestURI();
		
		// 이전|1|2|3|4|5|다음
		if((this.currentPage - 1) == 0){
			this.pagingHtmls.append("<li class='disabled'><a href='#'>&laquo;</a></li>");
		}else{
			this.pagingHtmls.append("<li><a href='" + requestURI + "?currentPage=" + (this.currentPage - 1) + "'>&laquo;</a></li>");
		}
		// |1|2|3|4|5|
		for(int i=this.startPage; i<=this.endPage; i++){
			if(this.currentPage == i){
				this.pagingHtmls.append("<li class='active'><a href='#'>" + i + "</a></li>");
			}else{
				this.pagingHtmls.append("<li><a href='" + requestURI + "?currentPage=" + i + "'>" + i + "</a></li>");
			}
		}
		// 다음
		if(this.currentPage < this.totalPage){
			this.pagingHtmls.append("<li><a href='" + requestURI + "?currentPage=" + (this.currentPage + 1) + "'>&raquo;</a></li>");
		}else{
			this.pagingHtmls.append("<li class='disabled'><a href='#'>&raquo;</a></li>");
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







