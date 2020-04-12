<%@ page language="JAVA" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시글 등록</title>
<script type="text/javascript">
$(function(){
    $('#bo_content').summernote({
    		lang: 'ko-KR',
			height: 150,
			codemirror: {
			theme: 'monokai'
		}
    });
	$('form[name=freeboardForm]').submit(function(){
		
		if(eval('${!empty freeboardInfo.bo_pass}')){
    		if('${LOGIN_MEMBERINFO.mem_pass}'!= $('input[name=bo_pass]').val()){
   		 	    return alert("비밀번호가 일치하지 않습니다.");
    		}
    	}
    	if( $('input[name=bo_nick]').val() == "" ){
    		return alert("대화명을 입력해주세요");
    	}
    	if( $('input[name=bo_title]').val() == "" ){
    		return alert("제목을 입력해주세요");
    	}
		
		
		var bo_content = $('#bo_content').summernote('code');
		$(this).append('<input type="hidden" name="bo_content" value="'+bo_content+'"/>');
		$(this).append('<input type="hidden" name="bo_writer" value="${LOGIN_MEMBERINFO.mem_id}"/>');
		$(this).append('<input type="hidden" name="bo_ip" value="${pageContext.request.remoteAddr}"/>');
		
		$(this).attr('action','${pageContext.request.contextPath }/user/freeboard/insertFreeboard.do');
		return true;


});
});
</script>
</head>
<body>
<form name="freeboardForm" class="form-horizontal" role="form" action="" method="post" enctype="multipart/form-data">
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_title">제목:</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="bo_title" name="bo_title" placeholder="제목 입력...">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_nickname">작성자 대화명:</label>
		<div class="col-sm-10"> 
			<input type="text" class="form-control" id="bo_nickname" name="bo_nickname" placeholder="대화명 입력..." value="${freeboardInfo.bo_nick}">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_pass">패스워드:</label>
		<div class="col-sm-10"> 
			<input type="password" class="form-control" id="bo_pwd" name="bo_pwd" placeholder="패스워드 입력...">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_content">내용:</label>
		<div class="col-sm-10"> 
			<div id="bo_content"></div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="files">첨부파일1:</label>
		<div class="col-sm-10">
			 <input type="file" class="filestyle" id="files" name="files" data-buttonName="btn-primary">
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="files">첨부파일2:</label>
		<div class="col-sm-10">
			 <input type="file" class="filestyle" id="files" name="files" data-buttonName="btn-primary">
		</div>
	</div>
	<div class="form-group"> 
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-info" style="float: right">등록</button>
			<button type="button" class="btn btn-danger" style="float: right">취소</button>
			<button type="button" class="btn btn-primary" style="float: right">목록</button>
		</div>
	</div>
</form>
</body>
</html>