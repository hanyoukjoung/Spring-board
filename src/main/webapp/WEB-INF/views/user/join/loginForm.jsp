<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/admin.css" type="text/css">
<title><spring:message code="mgr.loginForm.title"></spring:message></title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
$(function(){
	// loginForm.do?message=회원이 아닙니다.
    //    포워딩 처리  : loginForm.jsp
	if(eval('${!empty param.message}')){
		alert('${param.message}');
	}
	$('.loginBtn').click(function(){
		$('form[name=loginForm]').submit();
	});
});
</script>
</head>
<body>
${publicKeyMap['publicModulus'] } | ${publicKeyMap['publicExponent'] } 
<form name="loginForm" action="${pageContext.request.contextPath }/user/join/loginCheck.do"
    method="post">
	<table width="770" border="0" align="center" cellpadding="0"
		cellspacing="0" style="margin: 90px;">
		<tr>
			<td height="150" align="center"><img src="${pageContext.request.contextPath }/image/p_login.gif" /></td>
		</tr>
		<tr>
			<td height="174"
				style="background: url(${pageContext.request.contextPath }/image/login_bg.jpg); border: 1px solid #e3e3e3;">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td width="260" height="110" align="center"
							style="border-right: 1px dotted #736357;">
							<img src="${pageContext.request.contextPath }/image/logo.jpg" />
						</td>
						<td>
							<table border="0" align="center" cellpadding="5"
								cellspacing="0">
								<tr>
									<td><b><spring:message code="cop.memid"></spring:message></b></td>
									<td><input type="text" name="mem_id" class="box" tabindex="3" height="18" /></td>
									<td rowspan="2">
										<img src="${pageContext.request.contextPath }/image/login.gif" class="loginBtn"/>
									</td>
								</tr>
								<tr>
									<td><b><spring:message code="cop.mempass"></spring:message></b></td>
									<td><input type="password" name="mem_pass" class="box" tabindex="3" height="18" /></td>
								</tr>
							</table>
							<a href="${pageContext.request.contextPath }/user/member/memberForm.do"><spring:message code="button.regist.member"></spring:message></a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</form>	
</body>
</html>
