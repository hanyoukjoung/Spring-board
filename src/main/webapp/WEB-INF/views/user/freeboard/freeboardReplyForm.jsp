<%@ page language="JAVA" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시글 댓글등록</title>
<!-- <script type="text/javascript" src="/js/common/validation.js"></script> -->
<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script> -->
<!-- <!-- include libraries(jQuery, bootstrap) --> -->
<!-- <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet"> -->
<!-- <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script>  -->
<!-- <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script>  -->

<!-- <!-- include summernote css/js--> -->
<!-- <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet"> -->
<!-- <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script> -->
<script>
$(function(){
	// 섬머노트를 div를 활용한 textarea에 추가.
	// http://summernote.org 활용
    $('#bo_content').summernote({
			height: 150,
			codemirror: {
			theme: 'monokai'
		}
    });
    
    
    $('form[name=replyForm]').submit(function(){
    	
    	if($('#bo_title').val() == '') {
    		alert('제목이 비어있습니다.');
    		return false;
    	}
    	
    	if($('#bo_nickname').val() == '') {
    		alert('대화명 항목이 비어있습니다.');
    		return false;
    	}
    	
    	if($('#bo_pwd').val() != '${LOGIN_MEMBERINFO.mem_pass}'){
    		alert('비밀번호가 일치하지 않습니다.');
    		return false;
    	}
    	
    	$(this).append('<input type="hidden" name="bo_content" value="' + $('.panel-body').html() + '"/>');
    	$(this).append('<input type="hidden" name="bo_writer" value="${LOGIN_MEMBERINFO.mem_id}"/>');
    	$(this).append('<input type="hidden" name="bo_ip" value="${pageContext.request.remoteAddr}"/>');
    	$(this).append('<input type="hidden" name="bo_group" value="${param.bo_group}"/>');
    	$(this).append('<input type="hidden" name="bo_seq" value="${param.bo_seq}"/>');
    	$(this).append('<input type="hidden" name="bo_depth" value="${param.bo_depth}"/>');
    	
    	$(this).attr('action','${pageContext.request.contextPath }/user/freeboard/insertFreeboardReply.do');
    });
    
});
</script>
</head>
<body>
<hr />
<form name="replyForm" class="form-horizontal" role="form" action="/user/freeboard/insertFreeboardReply.do" method="post">
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_title">제목:</label>
		<div class="col-sm-10">
			<input type="text" class="form-control" id="bo_title" name="bo_title" >
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_nickname">작성자 대화명:</label>
		<div class="col-sm-10"> 
			<input type="text" class="form-control" id="bo_nickname" name="bo_nickname" >
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_pwd">패스워드:</label>
		<div class="col-sm-10"> 
			<input type="password" class="form-control" id="bo_pwd" name="bo_pwd" >
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-sm-2" for="bo_content">내용:</label>
		<div class="col-sm-10"> 
			<div id="bo_content"><p></p></div>
		</div>
	</div>
	<div class="form-group"> 
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-success" style="float: right;">답글등록</button>
			<button type="button" class="btn btn-danger">취소</button>
			<button type="button" class="btn btn-info">목록</button>
		</div>
	</div>
</form>
</body>
</html>