<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 클라이언트(브라우저)측 파일을 업로드 처리
	//   1. 라이브러리 
	//        http://commons.apache.org
	//        다운로드   commons-fileupload-1.2.2.jar
	//                  commons-io-2.3.jar
	//   2. WEB-INF/lib   import
	System.out.println("fileUploadForm.jsp응답처리 완료");
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
$(function(){
// 	$('form').submit(function(){
// 		var flag = true;
// 		$('input[type=file]').each(function(idx, targetTag){
// 			if(!/\.(jpg|jpeg|gif|png)/.test($(targetTag).val().toLowerCase())){
// 				alert('이미지 파일만 업로드할 수 있어요.');
// 				flag = false;
// 			}
// 		});
// 		return flag;
// 	});
});
</script>
</head>
<body>
<!-- 
	파일 업로드 시 : form 태그 파일 업로드 가능
	               ajax 파일 업로드 가능
	               규칙 - 1. 전송방식 method=POST
	                    2. 컨텐츠 타입 multipart/form-data 선언
 -->
<form action="${pageContext.request.contextPath }/user/file/fileUpload.do" method="post"
	enctype="multipart/form-data">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="mem_id" /> </td>
		</tr>
		<tr>
			<td>패스워드</td>
			<td><input type="text" name="mem_pass" /> </td>
		</tr>
		<tr>
			<td>성명</td>
			<td><input type="text" name="mem_name" /> </td>
		</tr>
		<tr>
			<td>파일1</td>
			<td><input type="file" name="files" /> </td>
		</tr>
		<tr>
			<td>파일2</td>
			<td><input type="file" name="files" /> </td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="전송" />
			</td>
		</tr>
	</table>
</form>
<img alt="" src="/files/${param.fileName}" width="200px" height="150px"
	onclick="javascript:location.href='${pageContext.request.contextPath}/user/file/fileDownload.do?fileName=${param.fileName}';">
</body>
</html>



