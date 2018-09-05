<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#checkBtn").click(function(){
			 $.ajax({
				 type:"post",
				 url:"${pageContext.request.contextPath}/front?command="+$("#hidden").val(),
				 data:$("#loginForm").serialize(),
				 success:function(result){
					if(result == "fail"){
						$("#loginCheck").text("아이디 또는 비밀번호를 확인하세요").css("color","red");
					}else if(result == "ok"){
						$("#hidden").val("Login");
						$("#loginForm").submit();
						
					}
				 }//success
			 });//ajax
		});//click
		
		$(".passwordEnter").keydown(function(key){
			if(key.keyCode == 13){
				 $.ajax({
					 type:"post",
					 url:"${pageContext.request.contextPath}/front?command="+$("#hidden").val(),
					 data:$("#loginForm").serialize(),
					 success:function(result){
						if(result == "fail"){
							$("#loginCheck").text("아이디 또는 비밀번호를 확인하세요").css("color","red");
							$("#login_text").focus().val("");
							$(".passwordEnter").val("");
						}else if(result == "ok"){
							$("#hidden").val("Login");
							$("#loginForm").submit();
						}
					 }//success
				 });//ajax
			}
		});//click
	});//ready
</script>
<style>
.test{
	 border-top:1px solid #000;
	 border-bottom:1px solid #000;
	 border-left:1px solid #000;
	 border-right:1px solid #000;
	
}

table td{
	padding:20px;
}
</style>
<div class="container" >
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6 text-center " 
			style="margin-top: 100px; padding-bottom: 100px;">
			<div style="margin-top: 200px; text-align:center;" align="center">
		
				<table class="test" >
					<tr>
						<td>
							<h3>회원 로그인</h3>
						<form method="post" id="loginForm" action="${pageContext.request.contextPath}/front">
							<input type="hidden" name="command" value="LoginCheck" id="hidden">
							<table >
								<tr>
									<td><input type="text" name="id" placeholder="아이디" size="12" id="login_text"></td>
									<td rowspan="2">
										<input type="button" style="WIDTH: 70pt; HEIGHT: 60pt; margin-bottom:10px;" class="newsletter_submit_btn" id="checkBtn" value="로그인"></td>
									</tr>
									<tr>
										<td><input type="password" name="password" placeholder="비밀번호" size="12" id="login_text" class="passwordEnter"></td>
									</tr>
									<tr>
										<td colspan="2"> <span id="loginCheck"></span></td>
									</tr>
									<tr>
				               			<td colspan="3" align="right">아직 회원이 아니시라면? <a href="${pageContext.request.contextPath }/front?command=RegisterMemberForm">인픽쳐 회원가입</a></td>
									</tr>
									<tr>
				                		<td colspan="3" align="right"><a href="">아이디 찾기</a>&nbsp;&nbsp;<a href="">비밀번호 찾기</a></td>
									</tr>
								</table>
							</form>
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>

