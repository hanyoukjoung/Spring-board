<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
$(function(){
	$('input[type=button]').click(function(){
		$.ajax({
			type : 'post'
		   ,url : '${pageContext.request.contextPath}/user/member/idCheck.do'
		   ,data : { mem_id : $('input[name=mem_id]').val() }
		   ,dataType : 'json'
		   ,error : function(result){
			   		alert(result.status);
		   },
		   success : function(result){
					alert(result.flag);			   
		   }
		});
	});
});
</script>
</head>
<body>
<form action='${pageContext.request.contextPath }/user/member/insertMember.do' method='post'>   
	<table>                                                  
		<tr>                                                 
			<td>아이디</td>                                  
			<td>
				<input type='text' name='mem_id'/>
				<input type="button" value="아이디중복검사" />
			</td>      
		</tr>                                                
		<tr>                                                 
			<td>패스워드</td>                                
			<td><input type='text'  name='mem_pass'/></td>   
		</tr>                                                
		<tr>                                                 
			<td>성명</td>                                    
			<td><input type='text'  name='mem_name'/></td>   
		</tr>                                                
		<tr>                                                 
			<td>주민등록번호</td>                            
			<td>                                             
				<input type='text'  name='mem_regno1'/>-     
				<input type='text'  name='mem_regno2'/>      
			</td>                                            
		</tr>                                                
		<tr>                                                 
			<td>생년월일(yyyy-MM-dd hh24:mi:ss)</td>         
			<td><input type='text'  name='mem_bir'/></td>    
		</tr>                                                
		<tr>                                                 
			<td>집전화번호</td>                              
			<td><input type='text'  name='mem_hometel'/></td>
		</tr>                                                
		<tr>                                                 
			<td>회사전화번호</td>                            
			<td><input type='text'  name='mem_comtel'/></td> 
		</tr>                                                
		<tr>                                                 
			<td>휴대폰</td>                                  
			<td><input type='text'  name='mem_hp'/></td>     
		</tr>                                                
		<tr>                                                 
			<td>이메일</td>                                  
			<td><input type='text'  name='mem_mail'/></td>   
		</tr>                                                
		<tr>                                                 
			<td>우편번호(111-111)</td>                       
			<td>                                             
				<input type='text'  name='mem_zip'/>         
			</td>                                            
		</tr>                                                
		<tr>                                                 
			<td>주소</td>                                    
			<td>                                             
			   <input type='text'  name='mem_add1'/>-        
			   <input type='text'  name='mem_add2'/>         
			</td>                                            
		</tr>                                                
		<tr>                                                 
			<td>직업</td>                                    
			<td><input type='text'  name='mem_job'/></td>    
		</tr>                                                
		<tr>                                                 
			<td>취미</td>                                    
			<td><input type='text'  name='mem_like'/></td>   
		</tr>                                                
		<tr>                                                 
			<td>                                             
				<input type='submit' value='회원가입' />     
			</td>                                            
		</tr>                                                
	</table>                                                 
</form>  
</body>
</html>