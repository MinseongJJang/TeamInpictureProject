<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- <h3> 아이디 찾기 </h3>
<table class=main>
	<tr>
</table> -->
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
<script type="text/javascript">
	$(document).ready(function(){
		
		$("#findId").click(function(){
			var name = $("#login_text1").val();
			var ssn = $("#login_text2").val();
			if(name == "" ){
				alert("이름을 입력하세요");
				return false;
			}else if(ssn == ""){
				alert("주민번호를 입력하세요");
				return false;
			}else{
				$("#loginForm").submit();
			}
		})
	
		$("#login_text2").keydown(function(key){
			var name = $("#login_text1").val();
			var ssn = $("#login_text2").val();
			if(key.keyCode ==13){
				if(name == ""  ){
					alert("이름을 입력하세요");
					return false;
				}else if(ssn == ""){
					alert("주민번호를 입력하세요");
					return false;
				}else{
					$("#loginForm").submit();
				}
			}
		})
		
		$("#login_text1").keydown(function(key){
			var name = $("#login_text1").val();
			var ssn = $("#login_text2").val();
			if(key.keyCode==13){
				if(name == ""  ){
					alert("이름을 입력하세요");
					return false;
				}else if(ssn == ""){
					alert("주민번호를 입력하세요");
					return false;
				}else{
					$("#loginForm").submit();
				}
			}
		})
	})
</script>
<div class="container" >
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6 text-center " 
			style="margin-top: 100px; padding-bottom: 100px;">
			<div style="margin-top: 200px; text-align:center;" align="center">
				<table class="test" >
					<tr>
						<td>
							<h3>아이디 찾기</h3>
						<form method="post" id="loginForm" action="${pageContext.request.contextPath}/front">
							<input type="hidden" name="command" value="FindId">
							<table>
								<tr>
									<td><input type="text" name="name" placeholder="이름" size="12" id="login_text1" required="required"></td>
									<td rowspan="2">
										<input type="button" id="findId" style="WIDTH: 70pt; HEIGHT: 60pt; margin-bottom:10px;" class="newsletter_submit_btn" value="검색">
									</td>
								</tr>
								<tr>
									<td><input type="password" name="ssn"  placeholder="주민등록번호" size="12" id="login_text2" required="required"></td>
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