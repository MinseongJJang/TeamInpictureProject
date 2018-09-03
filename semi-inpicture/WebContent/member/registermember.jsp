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
	/* 가입 눌렀을때   */
	/* 	function registerCheck(){
	
	 var idCheck = document.getElementById("checkResult");
	 var passCheck = document.getElementById("passcheckResultView");
	
	 if(idcheck.innerHTML=="중복" || idcheck.innerHTML=="아이디는 4자이상 10자이하만 가능!" || passCheck.innerHTML=="비밀번호 불일치 글자수 확인 필요" || passCheck.innerHTML=="비밀번호 불일치 기입내용 확인필요"){
	 alert("아이디 중복확인 또는 비밀번호를 다시 확인해주세요!");
	 return false;
	 }else{
	 return;
	 }
	 return flag;
	 } */
</script>

<style type="text/css">
	.a{
		position:relative;
		left:171px;
	}
	.tdcolor{
		text-align:center;
		background-color:#e8e8e8;
		border-botto:1px solid #dbdbdb;
	}
	.b{
		border-botto:1px solid #dbdbdb;
	}

	.table2{
		border-top:1px solid #dbdbdb;
		border-botto:1px solid #dbdbdb;
	}
</style>

<div class="main_slider">
	<div>
		<div class="a">
			<div class="col-sm-8">
				<div class="my-box">
					<h2>회원가입</h2>
					<form method="post"
						action="${pageContext.request.contextPath }/front" id="idForm"
						onsubmit="return registerCheck()">
						<input type="hidden" name="command" value="RegisterMember">
						<table class="table table2">
							<tr>
								<td class="tdcolor">아이디</td>
								<td><input type="text" name="id" id="id" placeholder="아이디"
									required="required"></td>
								<td colspan="2"><sapn id="checkResult"></sapn></td>
							</tr>
							<tr>
								<td class="tdcolor">비밀번호</td>
								<td><input type="password" name="password" id="pass"
									placeholder="비밀번호" required="required"></td>
								<td><span id="passcheckResultView"></span></td>
							</tr>
							<tr>
								<td class="tdcolor">비밀번호확인</td>
								<td><input type="password" id="passcheck"
									placeholder="비밀번호확인"></td>
							</tr>
							<tr>
								<td class="tdcolor">이름</td>
								<td><input type="text" name="name" placeholder="이름"
									required="required"></td>
							</tr>
							<tr>
								<td class="tdcolor">주소</td>
								<td><input type="text" name="address" placeholder="주소"
									required="required"></td>
							</tr>
							<tr>
								<td class="tdcolor">주민등록번호</td>
								<td><input type="text" name="ssn" required="required"
									placeholder="주민등록번호"></td>
							</tr>
							<tr>
								<td class="tdcolor">이메일</td>
								<td><input type="text" name="email" placeholder="이메일"
									required="required"></td>
							</tr>
							<tr>
								<td><input type="submit" value="가입"></td>
								<td><span id="registerCheck"></span></td>
							</tr>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
