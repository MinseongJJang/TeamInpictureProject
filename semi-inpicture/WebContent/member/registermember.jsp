<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=APIKEY&libraries=services,clusterer,drawing"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
		var idflag = false;
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
							idflag = true;
							$("#checkResult").html("사용가능").css("color","blue");
						} else {
							idflag = false; 	
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
		$("#idForm").submit(function() {
			var passcheck = $('#passcheck').val();
			var pass = $('#pass').val();
			if(passcheck != pass){
				$("#registerCheck").html("<font color='red' size='0.5em'>비밀번호를 확인해주세요!</font>")
				$("#pass").val("");
				$("#passcheck").val("");
				return false;
			}else if(idflag==false){
				$("#registerCheck").html("<font color='red' size='0.5em'>아이디를 확인해주세요!</font>")
				return false;
			}else {
				alert("회원가입 되었습니다!\n\n이제 로그인하세요~");
			}
			
		});
	});//ready
	/* 취소 버튼 */
	function back(){
		alert(1);
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
								<td><input type="text" name="id" id="id" size="30" placeholder="아이디" required="required"></td>																	
							</tr>
							<tr>
								<td></td>
								<td colspan="2"><span id="checkResult"></sapn></td>
							</tr>
							<tr>
								<td>비밀번호</td>
								<td><input type="password" name="password" id="pass"
									size="30" placeholder="비밀번호" required="required"></td>
								
							</tr>
							<tr>
								<td></td>
								<td colspan="2"><span id="passcheckResultView"></span></td>
							</tr>
							<tr>
								<td>비밀번호확인</td>
								<td colspan="2"><input type="password" id="passcheck"
									size="30" placeholder="비밀번호확인"></td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>이름</td>
								<td colspan="2"><input type="text" name="name" placeholder="이름"
									size="30" required="required"></td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>주소</td>
								<td> <input type="text" name="address" id="sample4_address" placeholder="주소" size="30"></td><td><span><input type="button" onclick="sample4_execDaumPostcode()" class="btn btn-red btn-lg pull-left newsletter_submit_btn" value="주소검색" style="height: 90%"></span>
								<span id="guide" style="color:#999"></span>
								</td>
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>주민등록번호</td>
								<td colspan="2"><input type="text" name="ssn" required="required"
									size="30" placeholder="주민등록번호"></td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>이메일</td>
								<td colspan="2"><input type="text" name="email1" placeholder="이메일"
									size="30" required="required"> @
								<select name="email2">
									<option selected="selected">---------</option>
									<option value="naver.com">naver.com</option>
									<option value="daum.net">daum.net</option>
									<option value="gmail.com">gmail.com</option>
									<option value="yahoo.co.kr">yahoo.co.kr</option>
									<option value="hanmail.net">hanmail.net</option>
									<option value="hotmail.com">hotmail.com</option>		
					</select>	
								</td>		
							</tr>							
						</table>
						<div align="center">
								<input class="newsletter_submit_btn" type="submit" value="가입" onclick="loginback()">&nbsp;<input class="newsletter_submit_btn" type="button" value="취소" onclick="back()">
								<span id="registerCheck"></span>
						</div>		
				</form>


		</div>
</div>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 도로명 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullRoadAddr = data.roadAddress; // 도로명 주소 변수
                var extraRoadAddr = ''; // 도로명 조합형 주소 변수

                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 도로명, 지번 조합형 주소가 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraRoadAddr !== ''){
                    extraRoadAddr = ' (' + extraRoadAddr + ')';
                }
                // 도로명, 지번 주소의 유무에 따라 해당 조합형 주소를 추가한다.
                if(fullRoadAddr !== ''){
                    fullRoadAddr += extraRoadAddr;
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById("sample4_address").value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById("sample4_address").value = fullRoadAddr;
                document.getElementById("sample4_address").value = data.jibunAddress;

                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
                if(data.autoRoadAddress) {
                    //예상되는 도로명 주소에 조합형 주소를 추가한다.
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    document.getElementById('guide').innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';

                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    document.getElementById('guide').innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';

                } else {
                    document.getElementById('guide').innerHTML = '';
                }
            }
        }).open();
    }
</script>