<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action='${pageContext.request.contextPath }/user/member/updateMember.do' method='post'>   
	<input type="hidden" name="mem_id" value="${memberInfo.mem_id }">
	<table>                                                  
		<tr>                                                 
			<td>아이디</td>                                  
			<td><input type='text' name='mem_id' value="${memberInfo.mem_id }" disabled="disabled"/></td>      
		</tr>                                                
		<tr>                                                 
			<td>패스워드</td>                                
			<td><input type='text'  name='mem_pass' value="${memberInfo.mem_pass }" /></td>   
		</tr>                                                
		<tr>                                                 
			<td>성명</td>                                    
			<td><input type='text'  name='mem_name'  value="${memberInfo.mem_name }" disabled="disabled"/></td>   
		</tr>                                                
		<tr>                                                 
			<td>주민등록번호</td>                            
			<td>                                             
				<input type='text'  name='mem_regno1'  value="${memberInfo.mem_regno1 }" disabled="disabled"/>-     
				<input type='text'  name='mem_regno2'  value="${memberInfo.mem_regno2 }" disabled="disabled"/>      
			</td>                                            
		</tr>                                                
		<tr>                                                 
			<td>생년월일(yyyy-MM-dd hh24:mi:ss)</td>         
			<td><input type='text'  name='mem_bir'  value="${memberInfo.mem_bir }" disabled="disabled"/></td>    
		</tr>                                                
		<tr>                                                 
			<td>집전화번호</td>                              
			<td><input type='text'  name='mem_hometel' value="${memberInfo.mem_hometel }" /></td>
		</tr>                                                
		<tr>                                                 
			<td>회사전화번호</td>                            
			<td><input type='text'  name='mem_comtel' value="${memberInfo.mem_comtel }" /></td> 
		</tr>                                                
		<tr>                                                 
			<td>휴대폰</td>                                  
			<td><input type='text'  name='mem_hp' value="${memberInfo.mem_hp }" /></td>     
		</tr>                                                
		<tr>                                                 
			<td>이메일</td>                                  
			<td><input type='text'  name='mem_mail' value="${memberInfo.mem_mail }" /></td>   
		</tr>                                                
		<tr>                                                 
			<td>우편번호(111-111)</td>                       
			<td>                                             
				<input type='text'  name='mem_zip' value="${memberInfo.mem_zip }" />         
			</td>                                            
		</tr>                                                
		<tr>                                                 
			<td>주소</td>                                    
			<td>                                             
			   <input type='text'  name='mem_add1' value="${memberInfo.mem_add1 }" />-        
			   <input type='text'  name='mem_add2' value="${memberInfo.mem_add2 }" />         
			</td>                                            
		</tr>                                                
		<tr>                                                 
			<td>직업</td>                                    
			<td><input type='text'  name='mem_job' value="${memberInfo.mem_job }" /></td>    
		</tr>                                                
		<tr>                                                 
			<td>취미</td>                                    
			<td><input type='text'  name='mem_like' value="${memberInfo.mem_like }" /></td>   
		</tr>                                                
		<tr>                                                 
			<td>                                             
				<input type='submit' value='회원정보수정' />     
			</td>                                            
		</tr>                                                
	</table>                                                 
</form>  
</body>
</html>






