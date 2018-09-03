<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<style type="text/css">
	.a{
		position:relative;
		left:171px;
	}
	.tdcolor{
		text-align:center;
		background-color:#F9F9F9;
		border-botton:1px solid #dbdbdb;
	}
	.b{
		border-botton:1px solid #dbdbdb;
	}

	.table2{
		border-top:1px solid #dbdbdb;
		border-botto:1px solid #dbdbdb;
	}	
	
	/* 버튼 속성  */
	
	.ab{
		width:136px;
		height:64px;
		line-height:61px;
		font-size:14px;
	}
	.ab:hover{
		background:#6c6c6c;
	}
	.ab2{
		color:#fff;
		background:black;
		border:1px solid #black;
	}
	.ac{
		width:136px;
		height:64px;
		line-height:61px;
		font-size:14px;
	}
	.ac2{
		color:black;
		background:#fff;
		border:1px solid #black;
	}
</style>
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
	/* 취소 버튼 */
	function back(){
		location.href="${pageContext.request.contextPath}/index.jsp";
		
	}	
</script>
 
<div class="main_slider">
	<div>
		<div class="a">
			<div class="col-sm-8">
				<div class="my-box">
					<h2>회원정보수정</h2>
					<form method="post"
						action="${pageContext.request.contextPath }/front" id="idForm"
						onsubmit="return registerCheck()">
						<input type="hidden" name="command" value="MemberUpdate">
						<table class="table" style="border-bottom:1px solid #dbdbdb" style="border-top:1px solid #dbdbdb">
							<tr>
								<td class="tdcolor">아이디</td>
								<td>${requestScope.im.id}</td>																	
							</tr>
							<tr>
								<td></td>
								<td colspan="2"></td>
							</tr>
							<tr>
								<td class="tdcolor">비밀번호</td>
								<td><input type="password" name="password" value="${requestScope.im.password}" id="pass"></td>
								
							</tr>
							<tr>
								<td></td>
								<td colspan="2"><span id="passcheckResultView"></span></td>
							</tr>
							<tr>
								<td class="tdcolor">비밀번호확인</td>
								<td colspan="2"><input type="password" value="${requestScope.im.password}" id="passcheck"></td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td class="tdcolor">이름</td>
								<td colspan="2">${requestScope.im.name}</td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td class="tdcolor">주소</td>
								<td colspan="2"><input type="text" name="address" value="${requestScope.im.address}"></td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td class="tdcolor">주민등록번호</td>
								<td colspan="2">${requestScope.im.ssn}</td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td class="tdcolor">이메일</td>
								<td colspan="2"><input type="text" name="email" value="${requestScope.im.email}"></td>								
							</tr>							
						</table>
						<!-- <tr align="center"> -->
						<div align="center">
								<input class="ab ab2" type="submit" value="수정">&nbsp;<input class="ac ac2" type="button" value="취소" onclick="back()">
								<span id="registerCheck"></span>
						</div>		
						<!-- 	</tr> -->
					</form>
				</div>
			</div>
		</div>
	</div>
</div>