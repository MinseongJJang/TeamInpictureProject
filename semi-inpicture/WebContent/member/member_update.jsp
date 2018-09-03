<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<script type="text/javascript">
	$(document).ready(function() {
		 $('#passcheck').on('keyup',function(){
	     	var passcheck = $('#passcheck').val();
	        var pass=$('#pass').val();
	        if(pass.length!=0){
              	if(passcheck==pass){
                	$('#passcheckResultView').html("<font color='green' size='0.5em'>비밀번호 일치</font>");
              	}else if(passcheck!=pass){
                	if(pass.length>passcheck.length&&passcheck.length>0){
                    	$('#passcheckResultView').html("<font color='red' size='0.5em'>비밀번호 불일치 글자수 확인 필요</font>");  
                 	}else if(pass.length==passcheck.length&&passcheck.length>0){
                    	$('#passcheckResultView').html("<font color='red' size='0.5em'>비밀번호 불일치 기입내용 확인필요</font>");
                 	}else if(passcheck.length==0){
                     	$('#passcheckResultView').html("");
              		}
              	}
              }//if
	        })//keyup
	});//ready
	
</script>
 <div class="main_slider">
		<div class="container fill_height">			
			<div class="row align-items-center fill_height">
				<div class="col-sm-8">
					<div class="main_slider_content">
					<h2>회원정보수정</h2>
						<form method="post" action="${pageContext.request.contextPath }/front" id="idForm">
						<input type="hidden" name="command" value="MemberUpdate">
						<table class="table">
    						<tr>
    							<td>
    								아이디
    							</td>
    							<td>    								     								
     								${requestScope.im.id}
    							</td>    							
    						</tr>   
    						<tr>
    							<td>
    								비밀번호
    							</td>
     							<td>     								 
     								<input type="password" name="password" id="pass">
     							</td>  
     							<td>
     								<span id="passcheckResultView"></span>
     							</td>  							
    						</tr> 
    						<tr>
    							<td>
    								비밀번호확인
    							</td>
    							<td>
    								<input type="password" id="passcheck">
    							</td>
    						</tr>   						 
    						<tr>
    							<td>
    								이름
    							</td>
     							<td>     								 
     								${requestScope.im.name}
     							</td>     							
    						</tr>
    						<tr>
    							<td>
    								주소
    							</td>
     							<td>     								 
     								<input type="text" name="address" >
     								val="${requestScope.imDTO.address }"
     							</td>     							
    						</tr>
    						<tr>
    							<td>
    								주민등록번호
    							</td>
     							<td>      								 
     								${requestScope.im.ssn}
     							</td>     							
    						</tr> 
    						<tr>
    							<td>
    								이메일
    							</td>
     							<td>       								
     								<input type="text" name="email">
     							</td>     							
    						</tr>  
    						<tr>
    							<td>
    								보유포인트
    							</td>
     							<td>       								
     								${requestScope.im.point}
     							</td>     							
    						</tr>  
    						<tr>
    							<td>
    								멤버타입
    							</td>
     							<td>       								
     								${requestScope.im.memberType}
     							</td>     							
    						</tr>  
    						<tr>
     							<td>         								
     								<input type="submit" value="수정">
     							</td>     							
    						</tr>  
     						</table>    
     					</form>       					  					
					</div>
				</div>
			</div>
		</div>
	</div>  