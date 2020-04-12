<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<thead>
		<tr>
			<th>아이디</th>
			<th>성명</th>
			<th>집전화번호</th>
			<th>직업</th>
			<th>취미</th>
		</tr>
	</thead>
	<tbody id="memberListTBY">
	<c:forEach items="${memberList }" var="memberInfo">
		<tr>
			<td>${memberInfo.mem_id }</td>
			<td>${memberInfo.mem_name }</td>
			<td>${memberInfo.mem_hometel}</td>
			<td>${memberInfo.mem_job}</td>
			<td>${memberInfo.mem_like}</td>
		</tr>
	</c:forEach>
	</tbody>
</table>
<input type="button" value="로그아웃" />
</body>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
$(function(){
	$('input[type=button]').click(function(){
		$(location).attr('href', '${pageContext.request.contextPath}/user/join/logout.do');
	});
	
	$('#memberListTBY tr').click(function(){
		var mem_id = $(this).find('td:eq(0)').text();			// /user/member/memberView/a001.do
		location.href = '${pageContext.request.contextPath}/user/member/memberView.do?mem_id='+mem_id;
	});
});
</script>
</html>











