<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#checkBtn").click(function() {
			$.ajax({
				type:"post",
				url:"${pageContext.request.contextPath}/front?command="+$("#hidden").val(),
				data:$("#passwordForm").serialize(),
				success:function(result){
					if(result=="fail"){
						$("#emailAlam").text("아이디 혹은 이메일이 잘못되었습니다!!").css("color","red");
						$("#login_text").focus.val("");
						$(".emailEnter").val("");
					}else if(result=="ok"){
						$("#emailAlam").text("이메일이 발송되었습니다!!").css("color","blue");
						$("#hidden").val("FindMemberPassword");
						$("#passwordForm").submit();
					}
				}
			})
		})
		
	})
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
							<h3>비밀번호 찾기</h3>
							<p>가입 하신 이메일을 입력하세요.</p>
						<form method="post" id="passwordForm" action="${pageContext.request.contextPath}/front">
							<input type="hidden" name="command" value="emailCheck" id="hidden">
							<table >
								<tr>
									<td>
										<span id="emailAlam"></span> 
									</td>
									
								</tr>
								<tr>
									<td><input type="text" name="id" placeholder="아이디" size="12" id="login_text"></td>
									<td rowspan="2">
										<input type="button" style="margin-bottom:10px;" class="newsletter_submit_btn" id="checkBtn" value="보내기"></td>
									</tr>
									<tr>
										<td><input type="text" name="email" placeholder="이메일" size="12" id="login_text" class="emailEnter"></td>
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