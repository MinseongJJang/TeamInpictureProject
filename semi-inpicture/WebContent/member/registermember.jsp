<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	
	$(document).ready(function(){
		$("#id").keyup(function(){
			//alert("keyup");
			var idValue = $(this).val();
			if (idValue.length<4 || idValue.length>10) {
				$("#checkResult").html("아이디는 4자이상 10자이하만 가능!").css("color", "red");
			} else {
				$.ajax({
					type : "post",
					url : "${pageContext.request.contextPath}/front?command=IdCheck",
					data : $("#idForm").serialize(),
					success : function(result) {
						if (result == "ok") {
							$("#checkResult").html("사용가능").css("color","blue");
						} else {
							$("#checkResult").html("중복").css("color","red")
						}
					}
				});
			}
		});//id keyup

		$('#passcheck').on('keyup',function(){
			var passcheck = $('#passcheck').val();
			var pass = $('#pass').val();
			if (pass.length != 0) {
				if (passcheck == pass) {
					$('#passcheckResultView').html("<font color='green' size='0.5em'>비밀번호 일치</font>");
				} else if (passcheck != pass) {
					if (pass.length > passcheck.length || passcheck.length > pass.length) {
							$('#passcheckResultView').html("<font color='red' size='0.5em'>비밀번호 불일치 글자수 확인 필요</font>");
					} else if (pass.length == passcheck.length && pass == passcheck) {
							$('#passcheckResultView').html("<font color='red' size='0.5em'>비밀번호 불일치 기입내용 확인필요</font>");
					} else if (passcheck.length == 0) {
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
<style>
.text_option {
		text-align:"center";
		font-size:"15px";
}
</style> 
<div class="main1">
	<h2>회원 가입</h2>
			<div class="content">
				<form method="post"	action="${pageContext.request.contextPath }/front" id="idForm"	onsubmit="return registerCheck()">
				<input type="hidden" name="command" value="RegisterMember">
					<table class="table" style="border-bottom:1px solid #dbdbdb" style="border-top:1px solid #dbdbdb" >
							<tr >
								<td >아이디</td>
								<td><input type="text" name="id" placeholder="아이디" required="required"></td>																	
							</tr>
							<tr>
								<td></td>
								<td colspan="2"><span id="checkResult"></sapn></td>
							</tr>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="password" id="pass"
									placeholder="비밀번호" required="required"></td>
								
							</tr>
							<tr>
								<td></td>
								<td colspan="2"><span id="passcheckResultView"></span></td>
							</tr>
							<tr>
								<td>비밀번호확인</td>
								<td colspan="2"><input type="password" id="passcheck"
									placeholder="비밀번호확인"></td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>이름</td>
								<td colspan="2"><input type="text" name="name" placeholder="이름"
									required="required"></td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>주소</td>
								<td colspan="2"><input type="text" name="address" placeholder="주소"
									required="required"></td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>주민등록번호</td>
								<td colspan="2"><input type="text" name="ssn" required="required"
									placeholder="주민등록번호"></td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>이메일</td>
								<td colspan="2"><input type="text" name="email" placeholder="이메일"
									required="required"></td>								
							</tr>							
						</table>
						<div align="center">
								<input class="newsletter_submit_btn" type="submit" value="가입">&nbsp;<input class="newsletter_submit_btn" type="button" value="취소" onclick="back()">
								<span id="registerCheck"></span>
						</div>		
				</form>
		</div>
</div>